package thebetweenlands.client.render.model.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.client.render.model.MowzieModelBase;
import thebetweenlands.client.render.model.MowzieModelRenderer;
import thebetweenlands.common.entity.mobs.EntityBarrishee;

@SideOnly(Side.CLIENT)
public class ModelBarrishee extends MowzieModelBase {
	public MowzieModelRenderer base_rotation_bit;
	public MowzieModelRenderer chest_right;
	public MowzieModelRenderer chest_left;
	public MowzieModelRenderer neck;
	public MowzieModelRenderer belly_1;
	public MowzieModelRenderer shoulder_right;
	public MowzieModelRenderer arm_right_1;
	public MowzieModelRenderer arm_right_2;
	public MowzieModelRenderer hand_right_1;
	public MowzieModelRenderer finger_right_mid;
	public MowzieModelRenderer finger_right_outer;
	public MowzieModelRenderer finger_right_inner;
	public MowzieModelRenderer finger_right_thumb;
	public MowzieModelRenderer finger_right_mid_1;
	public MowzieModelRenderer finger_right_mid_2;
	public MowzieModelRenderer finger_right_outer_1;
	public MowzieModelRenderer finger_right_outer_2;
	public MowzieModelRenderer finger_right_inner_1;
	public MowzieModelRenderer finger_right_inner_2;
	public MowzieModelRenderer finger_right_thumb_1;
	public MowzieModelRenderer finger_right_thumb_2;
	public MowzieModelRenderer shoulder_left;
	public MowzieModelRenderer arm_left_1;
	public MowzieModelRenderer arm_left_2;
	public MowzieModelRenderer hand_left_1;
	public MowzieModelRenderer finger_left_mid;
	public MowzieModelRenderer finger_left_outer;
	public MowzieModelRenderer finger_left_inner;
	public MowzieModelRenderer finger_left_thumb;
	public MowzieModelRenderer finger_left_mid_1;
	public MowzieModelRenderer finger_left_mid_2;
	public MowzieModelRenderer finger_left_outer_1;
	public MowzieModelRenderer finger_left_outer_2;
	public MowzieModelRenderer finger_left_inner_1;
	public MowzieModelRenderer finger_left_inner_2;
	public MowzieModelRenderer finger_left_thumb_1;
	public MowzieModelRenderer finger_left_thumb_2;
	public MowzieModelRenderer head_main;
	public MowzieModelRenderer jaw_back;
	public MowzieModelRenderer teeth_top;
	public MowzieModelRenderer headress_left1;
	public MowzieModelRenderer headress_right1;
	public MowzieModelRenderer jaw1;
	public MowzieModelRenderer teeth_bottom;
	public MowzieModelRenderer headress_left2;
	public MowzieModelRenderer headress_left3;
	public MowzieModelRenderer headress_right2;
	public MowzieModelRenderer headress_right3;
	public MowzieModelRenderer belly_2;
	public MowzieModelRenderer cog1_1;
	public MowzieModelRenderer greeble_back;
	public MowzieModelRenderer cogbeam;
	public MowzieModelRenderer leg_1;
	public MowzieModelRenderer greeble1_1;
	public MowzieModelRenderer greeble2_1;
	public MowzieModelRenderer cog2_1;
	public MowzieModelRenderer cog2_2;
	public MowzieModelRenderer cog2_fill;
	public MowzieModelRenderer cog2_3;
	public MowzieModelRenderer cog2_4;
	public MowzieModelRenderer cog2_5;
	public MowzieModelRenderer cog2_6;
	public MowzieModelRenderer cog2_7;
	public MowzieModelRenderer cog2_8;
	public MowzieModelRenderer cog2_9;
	public MowzieModelRenderer cog2_10;
	public MowzieModelRenderer cog2_11;
	public MowzieModelRenderer cog2_12;
	public MowzieModelRenderer leg_2;
	public MowzieModelRenderer leg_3;
	public MowzieModelRenderer ski_1;
	public MowzieModelRenderer rope_1;
	public MowzieModelRenderer ski_2;
	public MowzieModelRenderer ski_3;
	public MowzieModelRenderer greeble1_2;
	public MowzieModelRenderer greeble2_2;
	public MowzieModelRenderer cog1_2;
	public MowzieModelRenderer cog1_3;
	public MowzieModelRenderer cog1_4;
	public MowzieModelRenderer cog1_5;
	public MowzieModelRenderer cog1_6;
	public MowzieModelRenderer cog1_7;
	public MowzieModelRenderer cog1_8;
	public MowzieModelRenderer cog1_9;
	public MowzieModelRenderer cog1_10;
	public MowzieModelRenderer cog1_11;
	public MowzieModelRenderer cog1_12;

	public ModelBarrishee() {
		textureWidth = 256;
		textureHeight = 256;
		cog2_9 = new MowzieModelRenderer(this, 146, 112);
		cog2_9.setRotationPoint(-0.01F, 0.0F, -4.0F);
		cog2_9.addBox(0.0F, -3.0F, -4.0F, 3, 4, 4, 0.0F);
		setRotateAngle(cog2_9, -0.5235987755982988F, 0.0F, 0.0F);
		finger_left_mid_1 = new MowzieModelRenderer(this, 74, 94);
		finger_left_mid_1.setRotationPoint(0.0F, -1.5F, -5.0F);
		finger_left_mid_1.addBox(-1.49F, 0.0F, -3.0F, 3, 2, 3, 0.0F);
		setRotateAngle(finger_left_mid_1, 0.7285004297824331F, 0.0F, 0.0F);
		cog2_3 = new MowzieModelRenderer(this, 146, 61);
		cog2_3.setRotationPoint(0.01F, 0.0F, -4.0F);
		cog2_3.addBox(0.0F, -3.0F, -4.0F, 3, 4, 4, 0.0F);
		setRotateAngle(cog2_3, -0.5235987755982988F, 0.0F, 0.0F);
		finger_right_mid_2 = new MowzieModelRenderer(this, 30, 94);
		finger_right_mid_2.setRotationPoint(0.0F, 0.0F, -3.0F);
		finger_right_mid_2.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 3, 0.0F);
		setRotateAngle(finger_right_mid_2, -0.5009094953223726F, 0.0F, 0.0F);
		finger_left_thumb = new MowzieModelRenderer(this, 57, 116);
		finger_left_thumb.setRotationPoint(-4.0F, 0.0F, -0.5F);
		finger_left_thumb.addBox(0.0F, -1.5F, -4.0F, 3, 3, 4, 0.0F);
		setRotateAngle(finger_left_thumb, -0.18203784098300857F, 1.0471975511965976F, 0.0F);
		greeble_back = new MowzieModelRenderer(this, 146, 22);
		greeble_back.setRotationPoint(0.0F, -3.0F, 11.0F);
		greeble_back.addBox(-3.0F, 0.0F, -4.0F, 6, 9, 4, 0.0F);
		setRotateAngle(greeble_back, 0.36425021489121656F, 0.0F, 0.0F);
		cog1_7 = new MowzieModelRenderer(this, 169, 94);
		cog1_7.setRotationPoint(5.0F, 0.0F, 0.01F);
		cog1_7.addBox(0.0F, -7.0F, -2.0F, 5, 8, 4, 0.0F);
		setRotateAngle(cog1_7, 0.0F, 0.0F, -0.5235987755982988F);
		cog2_8 = new MowzieModelRenderer(this, 146, 104);
		cog2_8.setRotationPoint(-0.01F, 0.0F, -4.0F);
		cog2_8.addBox(0.0F, -3.0F, -4.0F, 3, 3, 4, 0.0F);
		setRotateAngle(cog2_8, -0.5235987755982988F, 0.0F, 0.0F);
		shoulder_right = new MowzieModelRenderer(this, 0, 27);
		shoulder_right.setRotationPoint(-7.0F, -1.5F, 2.0F);
		shoulder_right.addBox(-6.0F, -4.5F, -4.5F, 7, 9, 9, 0.0F);
		setRotateAngle(shoulder_right, 0.0F, -0.091106186954104F, -0.40980330836826856F);
		teeth_top = new MowzieModelRenderer(this, 0, 211);
		teeth_top.setRotationPoint(0.0F, 0.0F, -5.0F);
		teeth_top.addBox(-5.5F, 1.9F, -8.5F, 11, 2, 8, 0.0F);
		finger_right_thumb_1 = new MowzieModelRenderer(this, 15, 116);
		finger_right_thumb_1.setRotationPoint(-1.5F, -1.5F, -4.0F);
		finger_right_thumb_1.addBox(-1.51F, 0.0F, -3.0F, 3, 2, 3, 0.0F);
		setRotateAngle(finger_right_thumb_1, 0.7285004297824331F, 0.0F, 0.0F);
		headress_right2 = new MowzieModelRenderer(this, 96, 152);
		headress_right2.setRotationPoint(0.0F, -7.0F, -2.0F);
		headress_right2.addBox(-13.0F, -9.0F, 0.0F, 13, 9, 4, 0.0F);
		setRotateAngle(headress_right2, -0.18203784098300857F, 0.0F, 0.0F);
		cog2_1 = new MowzieModelRenderer(this, 146, 44);
		cog2_1.setRotationPoint(5.0F, 0.0F, 0.0F);
		cog2_1.addBox(-1.5F, 4.5F, -2.0F, 3, 4, 4, 0.0F);
		setRotateAngle(cog2_1, 0.18203784098300857F, 0.0F, -0.091106186954104F);
		cog1_4 = new MowzieModelRenderer(this, 169, 69);
		cog1_4.setRotationPoint(5.0F, 0.0F, 0.01F);
		cog1_4.addBox(0.0F, -3.0F, -2.0F, 5, 3, 4, 0.0F);
		setRotateAngle(cog1_4, 0.0F, 0.0F, -0.5235987755982988F);
		finger_right_outer_1 = new MowzieModelRenderer(this, 15, 102);
		finger_right_outer_1.setRotationPoint(-1.5F, -1.5F, -4.0F);
		finger_right_outer_1.addBox(-1.51F, 0.0F, -3.0F, 3, 2, 3, 0.0F);
		setRotateAngle(finger_right_outer_1, 0.7285004297824331F, 0.0F, 0.0F);
		belly_2 = new MowzieModelRenderer(this, 146, 0);
		belly_2.setRotationPoint(0.0F, 0.0F, 6.0F);
		belly_2.addBox(-7.5F, -4.5F, 0.0F, 15, 10, 11, 0.0F);
		setRotateAngle(belly_2, 0.5235987755982988F, 0.0F, -0.08726646259971647F);
		ski_1 = new MowzieModelRenderer(this, 146, 165);
		ski_1.setRotationPoint(-1.0F, 1.5F, 0.0F);
		ski_1.addBox(-2.5F, 0.0F, -12.0F, 5, 3, 24, 0.0F);
		setRotateAngle(ski_1, 0.136659280431156F, -1.3203415791337103F, 0.0F);
		headress_left3 = new MowzieModelRenderer(this, 61, 166);
		headress_left3.setRotationPoint(6.0F, 0.0F, 0.0F);
		headress_left3.addBox(0.0F, 0.0F, -1.99F, 7, 5, 4, 0.0F);
		setRotateAngle(headress_left3, 0.0F, 0.0F, -0.27314402793711257F);
		finger_right_thumb = new MowzieModelRenderer(this, 0, 116);
		finger_right_thumb.setRotationPoint(4.0F, 0.0F, -0.5F);
		finger_right_thumb.addBox(-3.0F, -1.5F, -4.0F, 3, 3, 4, 0.0F);
		setRotateAngle(finger_right_thumb, -0.18203784098300857F, -1.0471975511965976F, 0.0F);
		finger_left_outer_2 = new MowzieModelRenderer(this, 85, 102);
		finger_left_outer_2.setRotationPoint(0.0F, 0.0F, -3.0F);
		finger_left_outer_2.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 3, 0.0F);
		setRotateAngle(finger_left_outer_2, -0.5009094953223726F, 0.0F, 0.0F);
		arm_right_1 = new MowzieModelRenderer(this, 0, 46);
		arm_right_1.setRotationPoint(-6.0F, 0.0F, 0.0F);
		arm_right_1.addBox(-6.0F, -4.0F, -4.0F, 6, 8, 8, 0.0F);
		finger_left_mid_2 = new MowzieModelRenderer(this, 87, 94);
		finger_left_mid_2.setRotationPoint(0.0F, 0.0F, -3.0F);
		finger_left_mid_2.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 3, 0.0F);
		setRotateAngle(finger_left_mid_2, -0.5009094953223726F, 0.0F, 0.0F);
		headress_right1 = new MowzieModelRenderer(this, 96, 140);
		headress_right1.setRotationPoint(0.0F, 1.0F, -9.0F);
		headress_right1.addBox(-13.0F, -7.0F, -2.0F, 7, 7, 4, 0.0F);
		setRotateAngle(headress_right1, 0.0F, 0.18203784098300857F, -0.091106186954104F);
		jaw1 = new MowzieModelRenderer(this, 0, 180);
		jaw1.setRotationPoint(0.0F, 5.0F, 0.0F);
		jaw1.addBox(-5.0F, -1.0F, -11.0F, 10, 4, 12, 0.0F);
		setRotateAngle(jaw1, 0.7853981633974483F, 0.0F, 0.0F);
		finger_left_inner_2 = new MowzieModelRenderer(this, 85, 109);
		finger_left_inner_2.setRotationPoint(0.0F, 0.0F, -3.0F);
		finger_left_inner_2.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 3, 0.0F);
		setRotateAngle(finger_left_inner_2, -0.5009094953223726F, 0.0F, 0.0F);
		cog1_3 = new MowzieModelRenderer(this, 169, 56);
		cog1_3.setRotationPoint(5.0F, 0.0F, 0.01F);
		cog1_3.addBox(0.0F, -7.0F, -2.0F, 5, 8, 4, 0.0F);
		setRotateAngle(cog1_3, 0.0F, 0.0F, -0.5235987755982988F);
		cog1_10 = new MowzieModelRenderer(this, 169, 124);
		cog1_10.setRotationPoint(5.0F, 0.0F, -0.01F);
		cog1_10.addBox(0.0F, -3.0F, -2.0F, 5, 3, 4, 0.0F);
		setRotateAngle(cog1_10, 0.0F, 0.0F, -0.5235987755982988F);
		cog2_5 = new MowzieModelRenderer(this, 146, 78);
		cog2_5.setRotationPoint(0.01F, 0.0F, -4.0F);
		cog2_5.addBox(0.0F, -3.0F, -4.0F, 3, 4, 4, 0.0F);
		setRotateAngle(cog2_5, -0.5235987755982988F, 0.0F, 0.0F);
		arm_left_2 = new MowzieModelRenderer(this, 57, 63);
		arm_left_2.setRotationPoint(4.0F, 0.0F, 0.0F);
		arm_left_2.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F);
		setRotateAngle(arm_left_2, 0.0F, -0.22759093446006054F, -0.27314402793711257F);
		cog2_10 = new MowzieModelRenderer(this, 146, 121);
		cog2_10.setRotationPoint(-0.01F, 0.0F, -4.0F);
		cog2_10.addBox(0.0F, -3.0F, -4.0F, 3, 3, 4, 0.0F);
		setRotateAngle(cog2_10, -0.5235987755982988F, 0.0F, 0.0F);
		cog2_11 = new MowzieModelRenderer(this, 146, 129);
		cog2_11.setRotationPoint(-0.01F, 0.0F, -4.0F);
		cog2_11.addBox(0.0F, -3.0F, -4.0F, 3, 4, 4, 0.0F);
		setRotateAngle(cog2_11, -0.5235987755982988F, 0.0F, 0.0F);
		cog1_8 = new MowzieModelRenderer(this, 169, 107);
		cog1_8.setRotationPoint(5.0F, 0.0F, -0.01F);
		cog1_8.addBox(0.0F, -3.0F, -2.0F, 5, 3, 4, 0.0F);
		setRotateAngle(cog1_8, 0.0F, 0.0F, -0.5235987755982988F);
		jaw_back = new MowzieModelRenderer(this, 0, 167);
		jaw_back.setRotationPoint(0.0F, 1.0F, -2.0F);
		jaw_back.addBox(-6.0F, 0.0F, -3.0F, 12, 6, 6, 0.0F);
		cog1_1 = new MowzieModelRenderer(this, 169, 39);
		cog1_1.setRotationPoint(0.0F, -0.5F, 3.2F);
		cog1_1.addBox(-2.5F, 6.0F, -2.0F, 5, 4, 4, 0.0F);
		setRotateAngle(cog1_1, 0.15707963267948966F, 0.0F, 0.0F);
		finger_left_outer = new MowzieModelRenderer(this, 57, 102);
		finger_left_outer.setRotationPoint(4.0F, 0.0F, -6.0F);
		finger_left_outer.addBox(-3.0F, -1.5F, -4.0F, 3, 2, 4, 0.0F);
		setRotateAngle(finger_left_outer, -0.18203784098300857F, -0.4553564018453205F, 0.0F);
		cog2_7 = new MowzieModelRenderer(this, 146, 95);
		cog2_7.setRotationPoint(0.01F, 0.0F, -4.0F);
		cog2_7.addBox(0.0F, -3.0F, -4.0F, 3, 4, 4, 0.0F);
		setRotateAngle(cog2_7, -0.5235987755982988F, 0.0F, 0.0F);
		cog1_2 = new MowzieModelRenderer(this, 169, 48);
		cog1_2.setRotationPoint(2.5F, 9.0F, 0.01F);
		cog1_2.addBox(0.0F, -3.0F, -2.0F, 5, 3, 4, 0.0F);
		setRotateAngle(cog1_2, 0.0F, 0.0F, -0.5235987755982988F);
		cog2_6 = new MowzieModelRenderer(this, 146, 87);
		cog2_6.setRotationPoint(0.01F, 0.0F, -4.0F);
		cog2_6.addBox(0.0F, -3.0F, -4.0F, 3, 3, 4, 0.0F);
		setRotateAngle(cog2_6, -0.5235987755982988F, 0.0F, 0.0F);
		finger_right_inner = new MowzieModelRenderer(this, 0, 109);
		finger_right_inner.setRotationPoint(-4.0F, 0.0F, -6.0F);
		finger_right_inner.addBox(0.0F, -1.5F, -4.0F, 3, 2, 4, 0.0F);
		setRotateAngle(finger_right_inner, -0.18203784098300857F, 0.4553564018453205F, 0.0F);
		belly_1 = new MowzieModelRenderer(this, 105, 0);
		belly_1.setRotationPoint(0.0F, 1.1F, 8.5F);
		belly_1.addBox(-5.5F, -4.0F, -0.5F, 11, 8, 9, 0.0F);
		setRotateAngle(belly_1, -0.6457718232379019F, 0.0F, -0.08726646259971647F);
		hand_right_1 = new MowzieModelRenderer(this, 0, 82);
		hand_right_1.setRotationPoint(0.0F, 12.0F, 0.0F);
		hand_right_1.addBox(-4.0F, -1.5F, -8.0F, 8, 3, 8, 0.0F);
		setRotateAngle(hand_right_1, 0.36425021489121656F, 0.0F, 0.0F);
		finger_right_inner_2 = new MowzieModelRenderer(this, 28, 109);
		finger_right_inner_2.setRotationPoint(0.0F, 0.0F, -3.0F);
		finger_right_inner_2.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 3, 0.0F);
		setRotateAngle(finger_right_inner_2, -0.5009094953223726F, 0.0F, 0.0F);
		headress_left2 = new MowzieModelRenderer(this, 61, 152);
		headress_left2.setRotationPoint(0.0F, -7.0F, -2.0F);
		headress_left2.addBox(0.0F, -9.0F, 0.0F, 13, 9, 4, 0.0F);
		setRotateAngle(headress_left2, -0.18203784098300857F, 0.0F, 0.0F);
		cog1_12 = new MowzieModelRenderer(this, 169, 145);
		cog1_12.setRotationPoint(5.0F, 0.0F, -0.01F);
		cog1_12.addBox(0.0F, -3.0F, -2.0F, 5, 3, 4, 0.0F);
		setRotateAngle(cog1_12, 0.0F, 0.0F, -0.5235987755982988F);
		finger_right_mid_1 = new MowzieModelRenderer(this, 17, 94);
		finger_right_mid_1.setRotationPoint(0.0F, -1.5F, -5.0F);
		finger_right_mid_1.addBox(-1.51F, 0.0F, -3.0F, 3, 2, 3, 0.0F);
		setRotateAngle(finger_right_mid_1, 0.7285004297824331F, 0.0F, 0.0F);
		leg_3 = new MowzieModelRenderer(this, 203, 35);
		leg_3.setRotationPoint(-5.5F, 1.0F, 3.5F);
		leg_3.addBox(-6.0F, -3.0F, -2.0F, 8, 6, 7, 0.0F);
		setRotateAngle(leg_3, 0.0F, 0.8196066167365371F, 0.0F);
		neck = new MowzieModelRenderer(this, 0, 124);
		neck.setRotationPoint(0.0F, -1.0F, -1.0F);
		neck.addBox(-4.0F, -4.0F, -7.0F, 8, 7, 8, 0.0F);
		base_rotation_bit = new MowzieModelRenderer(this, 0, 0);
		base_rotation_bit.setRotationPoint(0.0F, 11.0F, -3.5F);
		base_rotation_bit.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
		rope_1 = new MowzieModelRenderer(this, 146, 241);
		rope_1.setRotationPoint(-3.0F, 0.0F, 0.0F);
		rope_1.addBox(-1.5F, -3.5F, -2.5F, 2, 7, 8, 0.0F);
		setRotateAngle(rope_1, 0.045553093477052F, 0.0F, -0.091106186954104F);
		leg_2 = new MowzieModelRenderer(this, 203, 19);
		leg_2.setRotationPoint(-5.0F, 0.5F, 0.0F);
		leg_2.addBox(-6.0F, -3.0F, 0.0F, 6, 8, 7, 0.0F);
		setRotateAngle(leg_2, -0.045553093477052F, -0.045553093477052F, 0.045553093477052F);
		head_main = new MowzieModelRenderer(this, 0, 140);
		head_main.setRotationPoint(0.0F, -4.0F, -7.0F);
		head_main.addBox(-7.0F, -8.0F, -14.0F, 14, 10, 16, 0.0F);
		cog2_fill = new MowzieModelRenderer(this, 146, 146);
		cog2_fill.setRotationPoint(0.0F, 0.0F, 0.0F);
		cog2_fill.addBox(-1.0F, -4.5F, -4.5F, 2, 9, 9, 0.0F);
		ski_3 = new MowzieModelRenderer(this, 146, 218);
		ski_3.setRotationPoint(2.5F, 1.0F, 0.0F);
		ski_3.addBox(0.0F, 0.0F, -10.0F, 3, 2, 20, 0.0F);
		setRotateAngle(ski_3, 0.0F, -0.045553093477052F, 0.091106186954104F);
		chest_right = new MowzieModelRenderer(this, 0, 0);
		chest_right.setRotationPoint(0.0F, 1.0F, 0.0F);
		chest_right.addBox(-9.5F, -7.0F, -4.0F, 10, 12, 14, 0.0F);
		setRotateAngle(chest_right, -0.3490658503988659F, 0.0F, 0.18203784098300857F);
		greeble1_2 = new MowzieModelRenderer(this, 169, 32);
		greeble1_2.setRotationPoint(0.0F, -4.0F, 0.0F);
		greeble1_2.addBox(-2.01F, -2.0F, 0.0F, 4, 2, 4, 0.0F);
		setRotateAngle(greeble1_2, -0.136659280431156F, 0.0F, 0.0F);
		cog1_9 = new MowzieModelRenderer(this, 169, 115);
		cog1_9.setRotationPoint(5.0F, 0.0F, -0.01F);
		cog1_9.addBox(0.0F, -3.0F, -2.0F, 5, 4, 4, 0.0F);
		setRotateAngle(cog1_9, 0.0F, 0.0F, -0.5235987755982988F);
		cog1_11 = new MowzieModelRenderer(this, 169, 132);
		cog1_11.setRotationPoint(5.0F, 0.0F, -0.01F);
		cog1_11.addBox(0.0F, -7.0F, -2.0F, 5, 8, 4, 0.0F);
		setRotateAngle(cog1_11, 0.0F, 0.0F, -0.5235987755982988F);
		cog2_4 = new MowzieModelRenderer(this, 146, 70);
		cog2_4.setRotationPoint(0.01F, 0.0F, -4.0F);
		cog2_4.addBox(0.0F, -3.0F, -4.0F, 3, 3, 4, 0.0F);
		setRotateAngle(cog2_4, -0.5235987755982988F, 0.0F, 0.0F);
		shoulder_left = new MowzieModelRenderer(this, 57, 27);
		shoulder_left.setRotationPoint(7.0F, -1.5F, 2.0F);
		shoulder_left.addBox(-1.0F, -4.5F, -4.5F, 7, 9, 9, 0.0F);
		setRotateAngle(shoulder_left, 0.0F, 0.091106186954104F, 0.40980330836826856F);
		finger_left_outer_1 = new MowzieModelRenderer(this, 72, 102);
		finger_left_outer_1.setRotationPoint(-1.5F, -1.5F, -4.0F);
		finger_left_outer_1.addBox(-1.49F, 0.0F, -3.0F, 3, 2, 3, 0.0F);
		setRotateAngle(finger_left_outer_1, 0.7285004297824331F, 0.0F, 0.0F);
		greeble2_1 = new MowzieModelRenderer(this, 186, 22);
		greeble2_1.setRotationPoint(-4.5F, -3.0F, 8.5F);
		greeble2_1.addBox(-2.0F, -4.0F, 0.0F, 4, 6, 4, 0.0F);
		setRotateAngle(greeble2_1, -0.7740535232594852F, 0.0F, -0.4553564018453205F);
		arm_right_2 = new MowzieModelRenderer(this, 0, 63);
		arm_right_2.setRotationPoint(-4.0F, 0.0F, 0.0F);
		arm_right_2.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F);
		setRotateAngle(arm_right_2, 0.0F, 0.22759093446006054F, 0.27314402793711257F);
		finger_right_outer_2 = new MowzieModelRenderer(this, 28, 102);
		finger_right_outer_2.setRotationPoint(0.0F, 0.0F, -3.0F);
		finger_right_outer_2.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 3, 0.0F);
		setRotateAngle(finger_right_outer_2, -0.5009094953223726F, 0.0F, 0.0F);
		finger_right_inner_1 = new MowzieModelRenderer(this, 15, 109);
		finger_right_inner_1.setRotationPoint(1.5F, -1.5F, -4.0F);
		finger_right_inner_1.addBox(-1.51F, 0.0F, -3.0F, 3, 2, 3, 0.0F);
		setRotateAngle(finger_right_inner_1, 0.8196066167365371F, 0.0F, 0.0F);
		finger_left_thumb_2 = new MowzieModelRenderer(this, 85, 116);
		finger_left_thumb_2.setRotationPoint(0.0F, 0.0F, -3.0F);
		finger_left_thumb_2.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 3, 0.0F);
		setRotateAngle(finger_left_thumb_2, -0.5009094953223726F, 0.0F, 0.0F);
		finger_left_mid = new MowzieModelRenderer(this, 57, 94);
		finger_left_mid.setRotationPoint(0.0F, 0.0F, -7.0F);
		finger_left_mid.addBox(-1.5F, -1.5F, -5.0F, 3, 2, 5, 0.0F);
		setRotateAngle(finger_left_mid, -0.18203784098300857F, 0.0F, 0.0F);
		cogbeam = new MowzieModelRenderer(this, 146, 36);
		cogbeam.setRotationPoint(7.0F, 2.5F, 6.0F);
		cogbeam.addBox(0.0F, -1.5F, -1.5F, 8, 3, 3, 0.0F);
		setRotateAngle(cogbeam, 0.0F, 0.0F, -0.136659280431156F);
		greeble2_2 = new MowzieModelRenderer(this, 186, 33);
		greeble2_2.setRotationPoint(0.0F, -4.0F, 0.0F);
		greeble2_2.addBox(-2.01F, -2.0F, 0.0F, 4, 2, 4, 0.0F);
		setRotateAngle(greeble2_2, -0.18203784098300857F, 0.0F, 0.0F);
		finger_right_mid = new MowzieModelRenderer(this, 0, 94);
		finger_right_mid.setRotationPoint(0.0F, 0.0F, -7.0F);
		finger_right_mid.addBox(-1.5F, -1.5F, -5.0F, 3, 2, 5, 0.0F);
		setRotateAngle(finger_right_mid, -0.18203784098300857F, 0.0F, 0.0F);
		finger_right_outer = new MowzieModelRenderer(this, 0, 102);
		finger_right_outer.setRotationPoint(4.0F, 0.0F, -6.0F);
		finger_right_outer.addBox(-3.0F, -1.5F, -4.0F, 3, 2, 4, 0.0F);
		setRotateAngle(finger_right_outer, -0.18203784098300857F, -0.4553564018453205F, 0.0F);
		finger_left_thumb_1 = new MowzieModelRenderer(this, 72, 116);
		finger_left_thumb_1.setRotationPoint(1.5F, -1.5F, -4.0F);
		finger_left_thumb_1.addBox(-1.49F, 0.0F, -3.0F, 3, 2, 3, 0.0F);
		setRotateAngle(finger_left_thumb_1, 0.7285004297824331F, 0.0F, 0.0F);
		headress_left1 = new MowzieModelRenderer(this, 61, 140);
		headress_left1.setRotationPoint(0.0F, 1.0F, -9.0F);
		headress_left1.addBox(6.0F, -7.0F, -2.0F, 7, 7, 4, 0.0F);
		setRotateAngle(headress_left1, 0.0F, -0.22759093446006054F, 0.091106186954104F);
		cog2_12 = new MowzieModelRenderer(this, 146, 138);
		cog2_12.setRotationPoint(-0.01F, 0.0F, -4.0F);
		cog2_12.addBox(0.0F, -3.0F, -4.0F, 3, 3, 4, 0.0F);
		setRotateAngle(cog2_12, -0.5235987755982988F, 0.0F, 0.0F);
		teeth_bottom = new MowzieModelRenderer(this, 0, 197);
		teeth_bottom.setRotationPoint(0.0F, -1.0F, 0.5F);
		teeth_bottom.addBox(-4.5F, -2.0F, -11.0F, 9, 2, 11, 0.0F);
		finger_right_thumb_2 = new MowzieModelRenderer(this, 28, 116);
		finger_right_thumb_2.setRotationPoint(0.0F, 0.0F, -3.0F);
		finger_right_thumb_2.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 3, 0.0F);
		setRotateAngle(finger_right_thumb_2, -0.5009094953223726F, 0.0F, 0.0F);
		arm_left_1 = new MowzieModelRenderer(this, 57, 46);
		arm_left_1.setRotationPoint(6.0F, 0.0F, 0.0F);
		arm_left_1.addBox(0.0F, -4.0F, -4.0F, 6, 8, 8, 0.0F);
		finger_left_inner = new MowzieModelRenderer(this, 57, 109);
		finger_left_inner.setRotationPoint(-4.0F, 0.0F, -6.0F);
		finger_left_inner.addBox(0.0F, -1.5F, -4.0F, 3, 2, 4, 0.0F);
		setRotateAngle(finger_left_inner, -0.18203784098300857F, 0.4553564018453205F, 0.0F);
		ski_2 = new MowzieModelRenderer(this, 146, 193);
		ski_2.setRotationPoint(-2.5F, 1.0F, 0.0F);
		ski_2.addBox(-3.0F, 0.0F, -11.0F, 3, 2, 22, 0.0F);
		setRotateAngle(ski_2, 0.0F, 0.045553093477052F, -0.045553093477052F);
		headress_right3 = new MowzieModelRenderer(this, 96, 166);
		headress_right3.setRotationPoint(-6.0F, 0.0F, 0.0F);
		headress_right3.addBox(-7.0F, 0.0F, -1.99F, 7, 5, 4, 0.0F);
		setRotateAngle(headress_right3, 0.0F, 0.0F, 0.27314402793711257F);
		leg_1 = new MowzieModelRenderer(this, 203, 0);
		leg_1.setRotationPoint(-6.5F, -0.4F, 2.0F);
		leg_1.addBox(-6.0F, -3.0F, -1.0F, 7, 9, 9, 0.0F);
		setRotateAngle(leg_1, 0.0F, 0.5235987755982988F, 0.08726646259971647F);
		cog1_5 = new MowzieModelRenderer(this, 169, 77);
		cog1_5.setRotationPoint(5.0F, 0.0F, 0.01F);
		cog1_5.addBox(0.0F, -3.0F, -2.0F, 5, 4, 4, 0.0F);
		setRotateAngle(cog1_5, 0.0F, 0.0F, -0.5235987755982988F);
		finger_left_inner_1 = new MowzieModelRenderer(this, 72, 109);
		finger_left_inner_1.setRotationPoint(1.5F, -1.5F, -4.0F);
		finger_left_inner_1.addBox(-1.49F, 0.0F, -3.0F, 3, 2, 3, 0.0F);
		setRotateAngle(finger_left_inner_1, 0.7285004297824331F, 0.0F, 0.0F);
		cog2_2 = new MowzieModelRenderer(this, 146, 53);
		cog2_2.setRotationPoint(-1.49F, 7.5F, -2.0F);
		cog2_2.addBox(0.0F, -3.0F, -4.0F, 3, 3, 4, 0.0F);
		setRotateAngle(cog2_2, -0.5235987755982988F, 0.0F, 0.0F);
		chest_left = new MowzieModelRenderer(this, 57, 0);
		chest_left.setRotationPoint(0.0F, 1.0F, 0.0F);
		chest_left.addBox(-0.5F, -7.0F, -4.0F, 10, 12, 14, 0.0F);
		setRotateAngle(chest_left, -0.3490658503988659F, 0.0F, -0.18203784098300857F);
		hand_left_1 = new MowzieModelRenderer(this, 57, 82);
		hand_left_1.setRotationPoint(0.0F, 12.0F, 0.0F);
		hand_left_1.addBox(-4.0F, -1.5F, -8.0F, 8, 3, 8, 0.0F);
		setRotateAngle(hand_left_1, 0.36425021489121656F, 0.0F, 0.0F);
		greeble1_1 = new MowzieModelRenderer(this, 169, 22);
		greeble1_1.setRotationPoint(4.5F, -3.0F, 8.5F);
		greeble1_1.addBox(-2.0F, -4.0F, 0.0F, 4, 5, 4, 0.0F);
		setRotateAngle(greeble1_1, -0.7740535232594852F, 0.0F, 0.22759093446006054F);
		cog1_6 = new MowzieModelRenderer(this, 169, 86);
		cog1_6.setRotationPoint(5.0F, 0.0F, 0.01F);
		cog1_6.addBox(0.0F, -3.0F, -2.0F, 5, 3, 4, 0.0F);
		setRotateAngle(cog1_6, 0.0F, 0.0F, -0.5235987755982988F);
		cog2_8.addChild(cog2_9);
		finger_left_mid.addChild(finger_left_mid_1);
		cog2_2.addChild(cog2_3);
		finger_right_mid_1.addChild(finger_right_mid_2);
		hand_left_1.addChild(finger_left_thumb);
		belly_2.addChild(greeble_back);
		cog1_6.addChild(cog1_7);
		cog2_7.addChild(cog2_8);
		chest_right.addChild(shoulder_right);
		head_main.addChild(teeth_top);
		finger_right_thumb.addChild(finger_right_thumb_1);
		headress_right1.addChild(headress_right2);
		cogbeam.addChild(cog2_1);
		cog1_3.addChild(cog1_4);
		finger_right_outer.addChild(finger_right_outer_1);
		belly_1.addChild(belly_2);
		leg_3.addChild(ski_1);
		headress_left1.addChild(headress_left3);
		hand_right_1.addChild(finger_right_thumb);
		finger_left_outer_1.addChild(finger_left_outer_2);
		shoulder_right.addChild(arm_right_1);
		finger_left_mid_1.addChild(finger_left_mid_2);
		head_main.addChild(headress_right1);
		jaw_back.addChild(jaw1);
		finger_left_inner_1.addChild(finger_left_inner_2);
		cog1_2.addChild(cog1_3);
		cog1_9.addChild(cog1_10);
		cog2_4.addChild(cog2_5);
		arm_left_1.addChild(arm_left_2);
		cog2_9.addChild(cog2_10);
		cog2_10.addChild(cog2_11);
		cog1_7.addChild(cog1_8);
		head_main.addChild(jaw_back);
		belly_1.addChild(cog1_1);
		hand_left_1.addChild(finger_left_outer);
		cog2_6.addChild(cog2_7);
		cog1_1.addChild(cog1_2);
		cog2_5.addChild(cog2_6);
		hand_right_1.addChild(finger_right_inner);
		base_rotation_bit.addChild(belly_1);
		arm_right_2.addChild(hand_right_1);
		finger_right_inner_1.addChild(finger_right_inner_2);
		headress_left1.addChild(headress_left2);
		cog1_11.addChild(cog1_12);
		finger_right_mid.addChild(finger_right_mid_1);
		leg_2.addChild(leg_3);
		base_rotation_bit.addChild(neck);
		leg_3.addChild(rope_1);
		leg_1.addChild(leg_2);
		neck.addChild(head_main);
		cog2_1.addChild(cog2_fill);
		ski_1.addChild(ski_3);
		base_rotation_bit.addChild(chest_right);
		greeble1_1.addChild(greeble1_2);
		cog1_8.addChild(cog1_9);
		cog1_10.addChild(cog1_11);
		cog2_3.addChild(cog2_4);
		chest_left.addChild(shoulder_left);
		finger_left_outer.addChild(finger_left_outer_1);
		belly_2.addChild(greeble2_1);
		arm_right_1.addChild(arm_right_2);
		finger_right_outer_1.addChild(finger_right_outer_2);
		finger_right_inner.addChild(finger_right_inner_1);
		finger_left_thumb_1.addChild(finger_left_thumb_2);
		hand_left_1.addChild(finger_left_mid);
		belly_2.addChild(cogbeam);
		greeble2_1.addChild(greeble2_2);
		hand_right_1.addChild(finger_right_mid);
		hand_right_1.addChild(finger_right_outer);
		finger_left_thumb.addChild(finger_left_thumb_1);
		head_main.addChild(headress_left1);
		cog2_11.addChild(cog2_12);
		jaw1.addChild(teeth_bottom);
		finger_right_thumb_1.addChild(finger_right_thumb_2);
		shoulder_left.addChild(arm_left_1);
		hand_left_1.addChild(finger_left_inner);
		ski_1.addChild(ski_2);
		headress_right1.addChild(headress_right3);
		belly_2.addChild(leg_1);
		cog1_4.addChild(cog1_5);
		finger_left_inner.addChild(finger_left_inner_1);
		cog2_1.addChild(cog2_2);
		base_rotation_bit.addChild(chest_left);
		arm_left_2.addChild(hand_left_1);
		belly_2.addChild(greeble1_1);
		cog1_5.addChild(cog1_6);

		parts = new MowzieModelRenderer[] { base_rotation_bit, chest_right, chest_left, neck, belly_1, shoulder_right,
				arm_right_1, arm_right_2, hand_right_1, finger_right_mid, finger_right_outer, finger_right_inner,
				finger_right_thumb, finger_right_mid_1, finger_right_mid_2, finger_right_outer_1, finger_right_outer_2,
				finger_right_inner_1, finger_right_inner_2, finger_right_thumb_1, finger_right_thumb_2, shoulder_left,
				arm_left_1, arm_left_2, hand_left_1, finger_left_mid, finger_left_outer, finger_left_inner,
				finger_left_thumb, finger_left_mid_1, finger_left_mid_2, finger_left_outer_1, finger_left_outer_2,
				finger_left_inner_1, finger_left_inner_2, finger_left_thumb_1, finger_left_thumb_2, head_main, jaw_back,
				teeth_top, jaw1, teeth_bottom, belly_2, greeble2_1, leg_1, greeble1_1, greeble_back, greeble2_2, leg_2,
				leg_3, ski_1, ski_2, ski_3, greeble1_2 };// cogbeam

		setInitPose();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw,
			float rotationPitch, float scale) {
		head_main.rotateAngleY = (float) Math.toRadians(rotationYaw);
		base_rotation_bit.render(scale);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAngle,
			float partialRenderTicks) {
		EntityBarrishee barrishee = (EntityBarrishee) entity;
		setToInitPose();
		float animation = limbSwing * 0.1F;
		float animation2 = MathHelper.sin((limbSwing) * 0.5F) * 0.4F * limbSwingAngle * 2.5F;
		float animation3 = MathHelper.sin((limbSwing) * 0.5F) * 0.4F * limbSwingAngle * 0.15707963267948966F * 0.5F;
		float standingAngle = barrishee.getSmoothedStandingAngle(partialRenderTicks);
		float flap = MathHelper.sin((barrishee.ticksExisted + partialRenderTicks) * 0.6F) * 0.8F;
		float flap2 = MathHelper.sin((barrishee.ticksExisted + partialRenderTicks) * 0.3F) * 0.8F;

		cog1_1.rotateAngleZ = 0F + animation;
		cog1_1.rotateAngleX = 0 + animation3;

		if ((barrishee.standingAngle > 0)) {
			base_rotation_bit.rotateAngleX = convertDegtoRad(-65F) + (convertDegtoRad(65F) * standingAngle);
			neck.rotateAngleX = convertDegtoRad(60F) - (convertDegtoRad(60F) * standingAngle);
			head_main.rotateAngleX = convertDegtoRad(5F) - (convertDegtoRad(5F) * standingAngle);
			belly_1.rotateAngleX = convertDegtoRad(30F) - (convertDegtoRad(70F) * standingAngle);
		}

		if (barrishee.isScreaming() && barrishee.getScreamTimer() >= 20 && barrishee.getScreamTimer() <= 30) {
			float fudge = barrishee.getScreamTimer() - 20 + partialRenderTicks;

			base_rotation_bit.rotateAngleX = convertDegtoRad(0F) - (convertDegtoRad(10F) * fudge * 0.1F);
			belly_1.rotateAngleX = convertDegtoRad(-40F) + (convertDegtoRad(10F) * fudge * 0.1F);
			neck.rotateAngleX = convertDegtoRad(0F) - (convertDegtoRad(45F) * fudge * 0.1F);
			head_main.rotateAngleX = convertDegtoRad(0F) + (convertDegtoRad(25F) * fudge * 0.1F);

			jaw_back.rotateAngleX = convertDegtoRad(0F) + (convertDegtoRad(10F) * fudge * 0.1F);
			jaw1.rotateAngleX = convertDegtoRad(20F) + (convertDegtoRad(45F) * fudge * 0.1F);

			shoulder_right.rotateAngleX = convertDegtoRad(0F) + (convertDegtoRad(30F) * fudge * 0.1F);
			shoulder_right.rotateAngleZ = convertDegtoRad(-25F) + (convertDegtoRad(-5F) * fudge * 0.1F);

			shoulder_left.rotateAngleX = convertDegtoRad(0F) + (convertDegtoRad(30F) * fudge * 0.1F);
			shoulder_left.rotateAngleZ = convertDegtoRad(25F) + (convertDegtoRad(5F) * fudge * 0.1F);

			hand_right_1.rotateAngleX = convertDegtoRad(25F) - (convertDegtoRad(20F) * fudge * 0.1F);
			hand_left_1.rotateAngleX = convertDegtoRad(25F) - (convertDegtoRad(20F) * fudge * 0.1F);
		}

		if (barrishee.isScreaming() && barrishee.getScreamTimer() > 30 && barrishee.getScreamTimer() < 40) {
			base_rotation_bit.rotateAngleX = convertDegtoRad(-10F);
			belly_1.rotateAngleX = convertDegtoRad(-30F);
			neck.rotateAngleX = convertDegtoRad(-45F);

			jaw_back.rotateAngleX = convertDegtoRad(10F);
			jaw1.rotateAngleX = convertDegtoRad(65F);

			shoulder_right.rotateAngleX = convertDegtoRad(30F);
			shoulder_right.rotateAngleZ = convertDegtoRad(-30F);

			shoulder_left.rotateAngleX = convertDegtoRad(30F);
			shoulder_left.rotateAngleZ = convertDegtoRad(30F);

			hand_right_1.rotateAngleX = convertDegtoRad(5F);
			hand_left_1.rotateAngleX = convertDegtoRad(5F);

			head_main.rotateAngleZ = 0F + animation2 * 0.25F + flap * 0.25F;
		}

		if (barrishee.isScreaming() && barrishee.getScreamTimer() >= 40) {
			float fudge = barrishee.getScreamTimer() - 40 + partialRenderTicks;

			base_rotation_bit.rotateAngleX = convertDegtoRad(-10F) + (convertDegtoRad(10F) * fudge * 0.1F);
			belly_1.rotateAngleX = convertDegtoRad(-30F) - (convertDegtoRad(10F) * fudge * 0.1F);
			neck.rotateAngleX = convertDegtoRad(-45F) + (convertDegtoRad(45F) * fudge * 0.1F);
			head_main.rotateAngleX = convertDegtoRad(25F) - (convertDegtoRad(25F) * fudge * 0.1F);

			jaw_back.rotateAngleX = convertDegtoRad(10F) - (convertDegtoRad(10F) * fudge * 0.1F);
			jaw1.rotateAngleX = convertDegtoRad(65F) - (convertDegtoRad(45F) * fudge * 0.1F);

			shoulder_right.rotateAngleX = convertDegtoRad(30F) - (convertDegtoRad(30F) * fudge * 0.1F);
			shoulder_right.rotateAngleZ = convertDegtoRad(-30F) + (convertDegtoRad(5F) * fudge * 0.1F);

			shoulder_left.rotateAngleX = convertDegtoRad(30F) - (convertDegtoRad(30F) * fudge * 0.1F);
			shoulder_left.rotateAngleZ = convertDegtoRad(30F) - (convertDegtoRad(5F) * fudge * 0.1F);

			hand_right_1.rotateAngleX = convertDegtoRad(5F) + (convertDegtoRad(20F) * fudge * 0.1F);
			hand_left_1.rotateAngleX = convertDegtoRad(5F) + (convertDegtoRad(20F) * fudge * 0.1F);
		}

		if (!barrishee.isAmbushSpawn() && !barrishee.isScreaming() && !barrishee.isSlamming()
				|| barrishee.isAmbushSpawn() && barrishee.standingAngle == 1 && !barrishee.isScreaming()
						&& !barrishee.isSlamming()) {
			cogbeam.rotateAngleX = 0F + animation;
			neck.rotateAngleX = 0F - flap2 * 0.0625F;
			head_main.rotateAngleX = 0F + flap2 * 0.0625F;
			head_main.rotateAngleZ = 0F + animation2 * 0.25F;
			jaw_back.rotateAngleX = 0F + -flap2 * 0.125F;
			jaw1.rotateAngleX = convertDegtoRad(20F) - flap2 * 0.0625F;

			shoulder_left.rotateAngleX = 0F + animation2 * 0.5F;
			shoulder_right.rotateAngleX = 0F - animation2 * 0.5F;
			arm_left_2.rotateAngleX = 0F + animation2;
			arm_right_2.rotateAngleX = 0F - animation2;

			hand_left_1.rotateAngleX = 0.36425021489121656F - shoulder_left.rotateAngleX - arm_left_2.rotateAngleX
					- animation2 * 0.5F;
			hand_right_1.rotateAngleX = 0.36425021489121656F - shoulder_right.rotateAngleX - arm_right_2.rotateAngleX
					+ animation2 * 0.5F;

			finger_left_inner.rotateAngleX = -0.17453292519943295F + animation2;
			finger_left_inner_1.rotateAngleX = 0.7853981633974483F + animation2;
			finger_left_inner_2.rotateAngleX = -0.5235987755982988F - animation2;

			finger_left_mid.rotateAngleX = -0.17453292519943295F + animation2;
			finger_left_mid_1.rotateAngleX = 0.7853981633974483F + animation2;
			finger_left_mid_2.rotateAngleX = -0.5235987755982988F - animation2;

			finger_left_outer.rotateAngleX = -0.17453292519943295F + animation2;
			finger_left_outer_1.rotateAngleX = 0.7853981633974483F + animation2;
			finger_left_outer_2.rotateAngleX = -0.5235987755982988F - animation2;

			finger_right_inner.rotateAngleX = -0.17453292519943295F - animation2;
			finger_right_inner_1.rotateAngleX = 0.7853981633974483F - animation2;
			finger_right_inner_2.rotateAngleX = -0.5235987755982988F + animation2;

			finger_right_mid.rotateAngleX = -0.17453292519943295F - animation2;
			finger_right_mid_1.rotateAngleX = 0.7853981633974483F - animation2;
			finger_right_mid_2.rotateAngleX = -0.5235987755982988F + animation2;

			finger_right_outer.rotateAngleX = -0.17453292519943295F - animation2;
			finger_right_outer_1.rotateAngleX = 0.7853981633974483F - animation2;
			finger_right_outer_2.rotateAngleX = -0.5235987755982988F + animation2;

			base_rotation_bit.rotateAngleX = 0F - animation2 * 0.125F;
			base_rotation_bit.rotateAngleZ = 0F - animation2 * 0.125F;

			belly_1.rotateAngleX = -0.6457718232379019F + animation2 * 0.125F;
			belly_1.rotateAngleZ = -0.08726646259971647F + animation2 * 0.125F;
		}
	}

	public float convertDegtoRad(float angleIn) {
		return angleIn * ((float) Math.PI / 180F);
	}

	public void setRotateAngle(MowzieModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
