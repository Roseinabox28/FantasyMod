package thebetweenlands.client.render.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.common.entity.mobs.EntityEmberling;

@SideOnly(Side.CLIENT)
public class ModelEmberling extends ModelBase {
	ModelRenderer base_rotation;
	ModelRenderer body_base;
	ModelRenderer body2;
	ModelRenderer neckpiece;
	ModelRenderer arm_left1;
	ModelRenderer arm_right1;
	ModelRenderer sidefin_left;
	ModelRenderer sidefin_right;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer hindleg_left1;
	ModelRenderer hindleg_right1;
	ModelRenderer body5;
	ModelRenderer tail1;
	ModelRenderer tail2;
	ModelRenderer tail3;
	ModelRenderer tail4;
	ModelRenderer tail5;
	ModelRenderer tail6;
	ModelRenderer tail7;
	ModelRenderer tailfin;
	ModelRenderer hindleg_left2;
	ModelRenderer hindleg_right2;
	ModelRenderer head_main;
	ModelRenderer jaw_connection;
	ModelRenderer gillthingy_left_upper1;
	ModelRenderer gillthingy_left_lower1;
	ModelRenderer gillthingy_right_upper1;
	ModelRenderer gillthingy_right_lower1;
	ModelRenderer jaw_lower;
	ModelRenderer gillthingy_left_upper2;
	ModelRenderer gillthingy_left_lower2;
	ModelRenderer gillthingy_right_upper2;
	ModelRenderer gillthingy_right_lower2;
	ModelRenderer arm_left2;
	ModelRenderer arm_right2;
	ModelRenderer charstick1;
	ModelRenderer charstick2;
	ModelRenderer charstick3;

	public ModelEmberling() {
		textureWidth = 64;
		textureHeight = 64;
		arm_left1 = new ModelRenderer(this, 20, 14);
		arm_left1.setRotationPoint(4.0F, 5.0F, 2.0F);
		arm_left1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		setRotateAngle(arm_left1, 0.36425021489121656F, -0.27314402793711257F, -0.22759093446006054F);
		sidefin_right = new ModelRenderer(this, 49, 50);
		sidefin_right.setRotationPoint(-4.0F, 1.0F, 5.0F);
		sidefin_right.addBox(0.0F, 0.0F, 0.0F, 0, 4, 3, 0.0F);
		setRotateAngle(sidefin_right, 0.0F, -0.18203784098300857F, 0.0F);
		gillthingy_right_upper2 = new ModelRenderer(this, 51, 23);
		gillthingy_right_upper2.setRotationPoint(0.0F, 0.0F, 3.0F);
		gillthingy_right_upper2.addBox(0.0F, -4.0F, 0.0F, 0, 4, 3, 0.0F);
		setRotateAngle(gillthingy_right_upper2, 0.0F, -0.22759093446006054F, 0.0F);
		charstick2 = new ModelRenderer(this, 25, 59);
		charstick2.setRotationPoint(15.0F, 1.0F, 0.0F);
		charstick2.addBox(0.0F, -1.0F, -1.0F, 2, 1, 1, 0.0F);
		setRotateAngle(charstick2, 0.0F, 0.0F, -0.27314402793711257F);
		tail3 = new ModelRenderer(this, 29, 16);
		tail3.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail3.addBox(-1.5F, -4.0F, 0.0F, 3, 4, 3, 0.0F);
		setRotateAngle(tail3, 0.22759093446006054F, 0.0F, 0.0F);
		gillthingy_left_lower1 = new ModelRenderer(this, 44, 29);
		gillthingy_left_lower1.setRotationPoint(3.0F, 5.0F, -1.0F);
		gillthingy_left_lower1.addBox(0.0F, -3.0F, 0.0F, 0, 3, 2, 0.0F);
		setRotateAngle(gillthingy_left_lower1, -0.22759093446006054F, 0.8651597102135892F, -0.18203784098300857F);
		jaw_connection = new ModelRenderer(this, 44, 9);
		jaw_connection.setRotationPoint(0.0F, 4.0F, 0.0F);
		jaw_connection.addBox(-3.0F, 0.0F, -2.0F, 6, 2, 2, 0.0F);
		neckpiece = new ModelRenderer(this, 44, 0);
		neckpiece.setRotationPoint(0.0F, 0.0F, -1.0F);
		neckpiece.addBox(-3.0F, 0.0F, -2.0F, 6, 6, 2, 0.0F);
		setRotateAngle(neckpiece, 0.22759093446006054F, 0.0F, 0.0F);
		gillthingy_right_lower1 = new ModelRenderer(this, 44, 33);
		gillthingy_right_lower1.setRotationPoint(-3.0F, 5.0F, -1.0F);
		gillthingy_right_lower1.addBox(0.0F, -3.0F, 0.0F, 0, 3, 2, 0.0F);
		setRotateAngle(gillthingy_right_lower1, -0.22759093446006054F, -0.8651597102135892F, 0.18203784098300857F);
		gillthingy_left_upper2 = new ModelRenderer(this, 51, 18);
		gillthingy_left_upper2.setRotationPoint(0.0F, 0.0F, 3.0F);
		gillthingy_left_upper2.addBox(0.0F, -4.0F, 0.0F, 0, 4, 3, 0.0F);
		setRotateAngle(gillthingy_left_upper2, 0.0F, 0.22759093446006054F, 0.0F);
		gillthingy_left_upper1 = new ModelRenderer(this, 44, 18);
		gillthingy_left_upper1.setRotationPoint(3.0F, 2.0F, -1.0F);
		gillthingy_left_upper1.addBox(0.0F, -4.0F, 0.0F, 0, 4, 3, 0.0F);
		setRotateAngle(gillthingy_left_upper1, 0.27314402793711257F, 0.5918411493512771F, 0.091106186954104F);
		arm_right2 = new ModelRenderer(this, 20, 35);
		arm_right2.setRotationPoint(0.0F, 4.0F, 1.0F);
		arm_right2.addBox(-0.99F, 0.0F, -2.0F, 2, 4, 2, 0.0F);
		setRotateAngle(arm_right2, -0.5918411493512771F, 0.0F, 0.0F);
		body4 = new ModelRenderer(this, 0, 36);
		body4.setRotationPoint(0.0F, 0.0F, 3.0F);
		body4.addBox(-3.0F, 0.0F, 0.0F, 6, 6, 3, 0.0F);
		setRotateAngle(body4, -0.22759093446006054F, 0.0F, 0.0F);
		tail1 = new ModelRenderer(this, 29, 0);
		tail1.setRotationPoint(0.0F, 4.0F, 3.0F);
		tail1.addBox(-2.0F, -4.0F, 0.0F, 4, 4, 3, 0.0F);
		setRotateAngle(tail1, 0.22759093446006054F, 0.0F, 0.0F);
		body_base = new ModelRenderer(this, 0, 0);
		body_base.setRotationPoint(0.0F, -7.0F, -6.0F);
		body_base.addBox(-4.0F, 0.0F, -1.0F, 8, 7, 6, 0.0F);
		setRotateAngle(body_base, -0.18203784098300857F, 0.0F, 0.0F);
		gillthingy_left_lower2 = new ModelRenderer(this, 49, 28);
		gillthingy_left_lower2.setRotationPoint(0.0F, 0.0F, 2.0F);
		gillthingy_left_lower2.addBox(0.0F, -3.0F, 0.0F, 0, 3, 3, 0.0F);
		setRotateAngle(gillthingy_left_lower2, 0.0F, 0.31869712141416456F, 0.0F);
		base_rotation = new ModelRenderer(this, 2, 1);
		base_rotation.setRotationPoint(0.0F, 16.3F, 0.0F);
		base_rotation.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
		jaw_lower = new ModelRenderer(this, 44, 14);
		jaw_lower.setRotationPoint(0.0F, 0.0F, -2.0F);
		jaw_lower.addBox(-3.01F, 0.0F, -4.0F, 6, 2, 4, 0.0F);
		setRotateAngle(jaw_lower, 0.40980330836826856F, 0.0F, 0.0F);
		charstick1 = new ModelRenderer(this, 25, 62);
		charstick1.setRotationPoint(6.0F, 3.5F, -1.5F);
		charstick1.addBox(-3.0F, 0.0F, -1.0F, 18, 1, 1, 0.0F);
		setRotateAngle(charstick1, -1.5707963267948966F, 3.141592653589793F, 0.0F);
		body5 = new ModelRenderer(this, 0, 46);
		body5.setRotationPoint(0.0F, 0.0F, 3.0F);
		body5.addBox(-3.01F, 0.0F, 0.0F, 6, 5, 3, 0.0F);
		setRotateAngle(body5, -0.22759093446006054F, 0.0F, 0.0F);
		arm_left2 = new ModelRenderer(this, 20, 21);
		arm_left2.setRotationPoint(0.0F, 4.0F, 1.0F);
		arm_left2.addBox(-1.01F, 0.0F, -2.0F, 2, 4, 2, 0.0F);
		setRotateAngle(arm_left2, -1.1383037381507017F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 0, 14);
		body2.setRotationPoint(0.0F, 0.0F, 5.0F);
		body2.addBox(-3.5F, 0.0F, 0.0F, 7, 7, 3, 0.0F);
		setRotateAngle(body2, -0.22759093446006054F, 0.0F, 0.0F);
		hindleg_left2 = new ModelRenderer(this, 51, 39);
		hindleg_left2.setRotationPoint(0.0F, 4.0F, -1.0F);
		hindleg_left2.addBox(-1.01F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
		setRotateAngle(hindleg_left2, 0.31869712141416456F, 0.0F, 0.0F);
		hindleg_left1 = new ModelRenderer(this, 42, 39);
		hindleg_left1.setRotationPoint(3.0F, 5.0F, 2.0F);
		hindleg_left1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		setRotateAngle(hindleg_left1, 0.5009094953223726F, -0.18203784098300857F, -0.18203784098300857F);
		hindleg_right2 = new ModelRenderer(this, 51, 46);
		hindleg_right2.setRotationPoint(0.0F, 4.0F, -1.0F);
		hindleg_right2.addBox(-0.99F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
		setRotateAngle(hindleg_right2, 0.31869712141416456F, 0.0F, 0.0F);
		sidefin_left = new ModelRenderer(this, 42, 50);
		sidefin_left.setRotationPoint(4.0F, 1.0F, 5.0F);
		sidefin_left.addBox(0.0F, 0.0F, 0.0F, 0, 4, 3, 0.0F);
		setRotateAngle(sidefin_left, 0.0F, 0.18203784098300857F, 0.0F);
		tail7 = new ModelRenderer(this, 29, 47);
		tail7.setRotationPoint(0.0F, 0.0F, 4.0F);
		tail7.addBox(-1.02F, -3.0F, 0.0F, 2, 3, 2, 0.0F);
		setRotateAngle(tail7, 0.40980330836826856F, 0.0F, 0.0F);
		tail6 = new ModelRenderer(this, 29, 39);
		tail6.setRotationPoint(0.0F, 0.0F, 4.0F);
		tail6.addBox(-1.01F, -3.0F, 0.0F, 2, 3, 4, 0.0F);
		charstick3 = new ModelRenderer(this, 32, 59);
		charstick3.setRotationPoint(2.0F, 0.0F, 0.0F);
		charstick3.addBox(0.0F, -1.0F, -1.0F, 2, 1, 1, 0.0F);
		setRotateAngle(charstick3, 0.0F, 0.0F, -0.27314402793711257F);
		tail5 = new ModelRenderer(this, 29, 31);
		tail5.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail5.addBox(-1.0F, -3.0F, 0.0F, 2, 3, 4, 0.0F);
		gillthingy_right_lower2 = new ModelRenderer(this, 49, 32);
		gillthingy_right_lower2.setRotationPoint(0.0F, 0.0F, 2.0F);
		gillthingy_right_lower2.addBox(0.0F, -3.0F, 0.0F, 0, 3, 3, 0.0F);
		setRotateAngle(gillthingy_right_lower2, 0.0F, -0.31869712141416456F, 0.0F);
		tail2 = new ModelRenderer(this, 29, 8);
		tail2.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail2.addBox(-2.01F, -4.0F, 0.0F, 4, 4, 3, 0.0F);
		setRotateAngle(tail2, 0.22759093446006054F, 0.0F, 0.0F);
		tail4 = new ModelRenderer(this, 29, 24);
		tail4.setRotationPoint(0.0F, -1.0F, 3.0F);
		tail4.addBox(-1.51F, -3.0F, 0.0F, 3, 3, 3, 0.0F);
		setRotateAngle(tail4, 0.40980330836826856F, 0.0F, 0.0F);
		hindleg_right1 = new ModelRenderer(this, 42, 46);
		hindleg_right1.setRotationPoint(-3.0F, 5.0F, 2.0F);
		hindleg_right1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		setRotateAngle(hindleg_right1, 0.5009094953223726F, 0.18203784098300857F, 0.18203784098300857F);
		gillthingy_right_upper1 = new ModelRenderer(this, 44, 23);
		gillthingy_right_upper1.setRotationPoint(-3.0F, 2.0F, -1.0F);
		gillthingy_right_upper1.addBox(0.0F, -4.0F, 0.0F, 0, 4, 3, 0.0F);
		setRotateAngle(gillthingy_right_upper1, 0.27314402793711257F, -0.5918411493512771F, -0.091106186954104F);
		body3 = new ModelRenderer(this, 0, 25);
		body3.setRotationPoint(0.0F, 0.0F, 3.0F);
		body3.addBox(-3.51F, 0.0F, 0.0F, 7, 7, 3, 0.0F);
		setRotateAngle(body3, -0.22759093446006054F, 0.0F, 0.0F);
		head_main = new ModelRenderer(this, 0, 54);
		head_main.setRotationPoint(0.0F, 0.0F, -2.0F);
		head_main.addBox(-3.0F, 0.0F, -6.0F, 6, 4, 6, 0.0F);
		setRotateAngle(head_main, 0.18203784098300857F, 0.0F, 0.0F);
		arm_right1 = new ModelRenderer(this, 20, 28);
		arm_right1.setRotationPoint(-4.0F, 5.0F, 2.0F);
		arm_right1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		setRotateAngle(arm_right1, -0.4553564018453205F, -0.136659280431156F, 1.3658946726107624F);
		tailfin = new ModelRenderer(this, 25, 45);
		tailfin.setRotationPoint(0.0F, 0.0F, 0.0F);
		tailfin.addBox(0.0F, -5.0F, -1.0F, 0, 5, 8, 0.0F);
		body_base.addChild(arm_left1);
		body_base.addChild(sidefin_right);
		gillthingy_right_upper1.addChild(gillthingy_right_upper2);
		charstick1.addChild(charstick2);
		tail2.addChild(tail3);
		head_main.addChild(gillthingy_left_lower1);
		head_main.addChild(jaw_connection);
		body_base.addChild(neckpiece);
		head_main.addChild(gillthingy_right_lower1);
		gillthingy_left_upper1.addChild(gillthingy_left_upper2);
		head_main.addChild(gillthingy_left_upper1);
		arm_right1.addChild(arm_right2);
		body3.addChild(body4);
		body5.addChild(tail1);
		base_rotation.addChild(body_base);
		gillthingy_left_lower1.addChild(gillthingy_left_lower2);
		jaw_connection.addChild(jaw_lower);
		arm_right2.addChild(charstick1);
		body4.addChild(body5);
		arm_left1.addChild(arm_left2);
		body_base.addChild(body2);
		hindleg_left1.addChild(hindleg_left2);
		body3.addChild(hindleg_left1);
		hindleg_right1.addChild(hindleg_right2);
		body_base.addChild(sidefin_left);
		tail6.addChild(tail7);
		tail5.addChild(tail6);
		charstick2.addChild(charstick3);
		tail4.addChild(tail5);
		gillthingy_right_lower1.addChild(gillthingy_right_lower2);
		tail1.addChild(tail2);
		tail3.addChild(tail4);
		body3.addChild(hindleg_right1);
		head_main.addChild(gillthingy_right_upper1);
		body2.addChild(body3);
		neckpiece.addChild(head_main);
		body_base.addChild(arm_right1);
		tail7.addChild(tailfin);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw,
			float rotationPitch, float scale) {
		base_rotation.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw,
			float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel,
				entity);

		EntityEmberling emberling = (EntityEmberling) entity;

		float heady = MathHelper.sin((rotationYaw / (180F / (float) Math.PI)) * 0.5F);
		float headx = MathHelper.sin((rotationPitch / (180F / (float) Math.PI)) * 0.5F);

		head_main.rotateAngleY = heady;
		head_main.rotateAngleX = 0.18203784098300857F + headx + emberling.animationTicks;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAngle,
			float partialRenderTicks) {
		EntityEmberling emberling = (EntityEmberling) entity;

		float animation = MathHelper.sin(limbSwing * 0.6F) * limbSwingAngle * 0.5F;

		float flap = MathHelper.sin((emberling.ticksExisted + partialRenderTicks) * 0.2F) * 0.8F;

		hindleg_left1.rotateAngleX = 0.5009094953223726F + animation * 2F;
		hindleg_right1.rotateAngleX = 0.5009094953223726F - animation * 2F;

		hindleg_left2.rotateAngleX = 0.31869712141416456F;
		hindleg_right2.rotateAngleX = 0.31869712141416456F;

		arm_right1.rotateAngleX = -0.4553564018453205F - animation;
		arm_right1.rotateAngleY = -0.136659280431156F - animation * 2F;
		arm_right1.rotateAngleZ = 1.3658946726107624F + emberling.smoothedAngle(partialRenderTicks);

		arm_left1.rotateAngleX = 0.36425021489121656F - animation * 2F;
		arm_left2.rotateAngleX = -1.1383037381507017F + animation;
		arm_left1.rotateAngleY = -0.27314402793711257F + animation;
		arm_left1.rotateAngleZ = -0.22759093446006054F - emberling.smoothedAngle(partialRenderTicks);

		body_base.rotateAngleX = -0.18203784098300857F - animation * 0.5F + flap * 0.025F
				- emberling.smoothedAngle(partialRenderTicks) * 0.125F;

		body3.rotateAngleX = -0.22759093446006054F + animation * 0.5F - flap * 0.05F
				+ emberling.smoothedAngle(partialRenderTicks) * 0.125F;

		jaw_lower.rotateAngleX = 0.40980330836826856F + flap * 0.5F;

		gillthingy_left_upper1.rotateAngleY = 0.5918411493512771F - flap * 0.125F;
		gillthingy_left_upper2.rotateAngleY = 0.22759093446006054F - flap * 0.25F;

		gillthingy_right_upper1.rotateAngleY = -0.5918411493512771F + flap * 0.125F;
		gillthingy_right_upper2.rotateAngleY = -0.22759093446006054F + flap * 0.25F;

		gillthingy_left_lower1.rotateAngleY = 0.8651597102135892F - flap * 0.125F;
		gillthingy_left_lower2.rotateAngleY = 0.31869712141416456F - flap * 0.25F;

		gillthingy_right_lower1.rotateAngleY = -0.8651597102135892F + flap * 0.125F;
		gillthingy_right_lower2.rotateAngleY = -0.31869712141416456F + flap * 0.25F;
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
