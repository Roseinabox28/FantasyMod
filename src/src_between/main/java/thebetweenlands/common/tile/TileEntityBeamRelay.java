package thebetweenlands.common.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.client.render.particle.BLParticles;
import thebetweenlands.client.render.particle.BatchedParticleRenderer;
import thebetweenlands.client.render.particle.DefaultParticleBatches;
import thebetweenlands.client.render.particle.ParticleFactory.ParticleArgs;
import thebetweenlands.client.render.shader.ShaderHelper;
import thebetweenlands.common.block.structure.BlockBeamLensSupport;
import thebetweenlands.common.block.structure.BlockBeamRelay;
import thebetweenlands.common.block.structure.BlockBeamTube;
import thebetweenlands.common.block.structure.BlockDiagonalEnergyBarrier;
import thebetweenlands.common.block.structure.BlockDungeonDoorRunes;
import thebetweenlands.common.block.structure.BlockEnergyBarrierMud;

public class TileEntityBeamRelay extends TileEntity implements ITickable {
	public boolean active;
	public boolean in_down, in_up, in_north, in_south, in_west, in_east;

	private int particleTimer = 0;

	public TileEntityBeamRelay() {
		super();
	}

	@Override
	public void update() {
		if (getWorld().getBlockState(getPos()).getBlock() != null) {
			if (getWorld().getBlockState(getPos()).getValue(BlockBeamRelay.POWERED)) {
				if (!active)
					setActive(true);
			}

			if (!getWorld().getBlockState(getPos()).getValue(BlockBeamRelay.POWERED)) {
				if (active)
					setActive(false);
			}
		}

		if (active)
			activateBlock();
		else
			deactivateBlock();
	}

	@SideOnly(Side.CLIENT)
	private void spawnBeamParticles(Vec3d target) {
		Vec3d dir = target.normalize();
		float beamScale = 2.5F;
		float beamScaleInset = 0.75f;
		Vec3d beamStart = new Vec3d(this.pos.getX() + 0.5 - dir.x * beamScale * beamScaleInset * 0.1f,
				this.pos.getY() + 0.5 - dir.y * beamScale * beamScaleInset * 0.1f,
				this.pos.getZ() + 0.5 - dir.z * beamScale * beamScaleInset * 0.1f);
		Vec3d beamEnd = new Vec3d(target.x + dir.x * beamScale * beamScaleInset * 0.1f * 2,
				target.y + dir.y * beamScale * beamScaleInset * 0.1f * 2,
				target.z + dir.z * beamScale * beamScaleInset * 0.1f * 2);
		BatchedParticleRenderer.INSTANCE.addParticle(DefaultParticleBatches.BEAM,
				BLParticles.PUZZLE_BEAM_2.create(world, beamStart.x, beamStart.y, beamStart.z,
						ParticleArgs.get().withMotion(0, 0, 0).withColor(40F, 220F, 130F, 1F).withScale(beamScale)
								.withData(30, beamEnd)));
		for (int i = 0; i < 3; i++) {
			float offsetLen = this.world.rand.nextFloat();
			Vec3d offset = new Vec3d(target.x * offsetLen + world.rand.nextFloat() * 0.2f - 0.1f,
					target.y * offsetLen + world.rand.nextFloat() * 0.2f - 0.1f,
					target.z * offsetLen + world.rand.nextFloat() * 0.2f - 0.1f);
			float vx = (world.rand.nextFloat() * 2f - 1) * 0.0025f;
			float vy = (world.rand.nextFloat() * 2f - 1) * 0.0025f + 0.008f;
			float vz = (world.rand.nextFloat() * 2f - 1) * 0.0025f;
			float scale = 0.5f + world.rand.nextFloat();
			if (ShaderHelper.INSTANCE.canUseShaders() && world.rand.nextBoolean()) {
				BatchedParticleRenderer.INSTANCE.addParticle(DefaultParticleBatches.HEAT_HAZE_BLOCK_ATLAS,
						BLParticles.SMOOTH_SMOKE.create(world, this.pos.getX() + 0.5 + offset.x,
								this.pos.getY() + 0.5 + offset.y, this.pos.getZ() + 0.5 + offset.z,
								ParticleArgs.get().withMotion(vx, vy, vz).withColor(1, 1, 1, 0.2F).withScale(scale * 8)
										.withData(80, true, 0.0F, true)));
			} else {
				BatchedParticleRenderer.INSTANCE.addParticle(
						DefaultParticleBatches.TRANSLUCENT_GLOWING_NEAREST_NEIGHBOR,
						BLParticles.PUZZLE_BEAM.create(world, this.pos.getX() + 0.5 + offset.x,
								this.pos.getY() + 0.5 + offset.y, this.pos.getZ() + 0.5 + offset.z,
								ParticleArgs.get().withMotion(vx, vy, vz).withColor(40F, 220F, 130F, 1F)
										.withScale(scale).withData(100)));
			}
		}
	}

	public void activateBlock() {
		IBlockState state = getWorld().getBlockState(getPos());
		EnumFacing facing = state.getValue(BlockBeamRelay.FACING);
		BlockPos targetPos = getPos().offset(facing, getDistanceToObstruction(facing));

		if (world.isRemote) {
			if (this.particleTimer++ >= 20) {
				this.particleTimer = 0;
				spawnBeamParticles(new Vec3d(targetPos.getX() - pos.getX(), targetPos.getY() - pos.getY(),
						targetPos.getZ() - pos.getZ()));
			}
		} else {
			IBlockState stateofTarget = getWorld().getBlockState(targetPos);

			if (stateofTarget.getBlock() instanceof BlockBeamRelay) {
				if (getWorld().getTileEntity(targetPos) instanceof TileEntityBeamRelay) {
					TileEntityBeamRelay targetTile = (TileEntityBeamRelay) getWorld().getTileEntity(targetPos);
					targetTile.setTargetIncomingBeam(facing.getOpposite(), true);
					if (!getWorld().getBlockState(targetPos).getValue(BlockBeamRelay.POWERED)) {
						stateofTarget = stateofTarget.cycleProperty(BlockBeamRelay.POWERED);
						getWorld().setBlockState(targetPos, stateofTarget, 3);
					}
				}
			}

			if (stateofTarget.getBlock() instanceof BlockDungeonDoorRunes) {
				if (getWorld().getTileEntity(targetPos) instanceof TileEntityDungeonDoorRunes) {
					TileEntityDungeonDoorRunes targetTile = (TileEntityDungeonDoorRunes) getWorld()
							.getTileEntity(targetPos);
					if (targetTile.is_gate_entrance) {
						targetTile.top_state_prev = targetTile.top_code;
						targetTile.mid_state_prev = targetTile.mid_code;
						targetTile.bottom_state_prev = targetTile.bottom_code;
						getWorld().setBlockState(targetPos, stateofTarget, 3);
					}
				}
			}
		}
	}

	public void deactivateBlock() {
		IBlockState state = getWorld().getBlockState(getPos());
		EnumFacing facing = state.getValue(BlockBeamRelay.FACING);
		BlockPos targetPos = getPos().offset(facing, getDistanceToObstruction(facing));
		IBlockState stateofTarget = getWorld().getBlockState(targetPos);

		if (stateofTarget.getBlock() instanceof BlockBeamRelay) {
			if (getWorld().getTileEntity(targetPos) instanceof TileEntityBeamRelay) {
				TileEntityBeamRelay targetTile = (TileEntityBeamRelay) getWorld().getTileEntity(targetPos);
				targetTile.setTargetIncomingBeam(facing.getOpposite(), false);
				if (!targetTile.isGettingBeamed())
					if (getWorld().getBlockState(targetPos).getValue(BlockBeamRelay.POWERED)) {
						stateofTarget = stateofTarget.cycleProperty(BlockBeamRelay.POWERED);
						getWorld().setBlockState(targetPos, stateofTarget, 3);
					}
			}
		}

	}

	public void setTargetIncomingBeam(EnumFacing facing, boolean state) {
		switch (facing) {
		case DOWN:
			in_down = state;
			break;
		case EAST:
			in_east = state;
			break;
		case NORTH:
			in_north = state;
			break;
		case SOUTH:
			in_south = state;
			break;
		case UP:
			in_up = state;
			break;
		case WEST:
			in_west = state;
			break;
		default:
			break;
		}

	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	public void setActive(boolean isActive) {
		active = isActive;
		getWorld().notifyBlockUpdate(getPos(), getWorld().getBlockState(getPos()), getWorld().getBlockState(getPos()),
				3);
	}

	public int getDistanceToObstruction(EnumFacing facing) {
		int distance = 0;
		for (distance = 1; distance < 14; distance++) {
			IBlockState state = getWorld().getBlockState(getPos().offset(facing, distance));
			if (state != Blocks.AIR.getDefaultState() && !(state.getBlock() instanceof BlockDiagonalEnergyBarrier)
					&& !(state.getBlock() instanceof BlockEnergyBarrierMud)
					&& !(state.getBlock() instanceof BlockBeamLensSupport) && !isValidBeamTubeLens(state, facing))
				break;
		}
		return distance;
	}

	private boolean isValidBeamTubeLens(IBlockState state, EnumFacing facing) {
		if (!(state.getBlock() instanceof BlockBeamTube))
			return false;
		if (state.getValue(BlockBeamTube.FACING) == facing)
			return true;
		if (state.getValue(BlockBeamTube.FACING) == facing.getOpposite())
			return true;
		return false;
	}

	public boolean isGettingBeamed() {
		return in_up ? true
				: in_down ? true : in_north ? true : in_south ? true : in_west ? true : in_east ? true : false;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("active", active);
		nbt.setBoolean("in_down", in_down);
		nbt.setBoolean("in_up", in_up);
		nbt.setBoolean("in_north", in_north);
		nbt.setBoolean("in_south", in_south);
		nbt.setBoolean("in_west)", in_west);
		nbt.setBoolean("in_east", in_east);
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		active = nbt.getBoolean("active");
		in_down = nbt.getBoolean("in_down");
		in_up = nbt.getBoolean("in_up");
		in_north = nbt.getBoolean("in_north");
		in_south = nbt.getBoolean("in_south");
		in_west = nbt.getBoolean("in_west)");
		in_east = nbt.getBoolean("in_east");
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
		return writeToNBT(nbt);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new SPacketUpdateTileEntity(getPos(), 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}

}
