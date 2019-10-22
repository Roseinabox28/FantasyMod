package thebetweenlands.client.render.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTinyWormEggSac extends ModelBase {
	ModelRenderer egg1;
	ModelRenderer egg2;
	ModelRenderer egg3;
	ModelRenderer egg4;
	ModelRenderer egg5;
	ModelRenderer egg6;
	ModelRenderer egg7;
	ModelRenderer egg8;
	ModelRenderer webbing1a;
	ModelRenderer webbing1b;
	ModelRenderer egg2b;
	ModelRenderer webbing2a;
	ModelRenderer webbing2b;
	ModelRenderer webbing2c;
	ModelRenderer webbing2d;
	ModelRenderer webbing2a2;
	ModelRenderer webbing3a;
	ModelRenderer webbing4a;
	ModelRenderer egg5b;
	ModelRenderer webbing5a;
	ModelRenderer webbing5b;
	ModelRenderer webbing5b2;
	ModelRenderer webbing6a;
	ModelRenderer webbing6b;
	ModelRenderer webbing7a;
	ModelRenderer webbing7b;
	ModelRenderer webbing8a;

	public ModelTinyWormEggSac() {
		textureWidth = 128;
		textureHeight = 128;
		webbing1b = new ModelRenderer(this, 26, 0);
		webbing1b.setRotationPoint(-2.0F, -2.0F, 0.0F);
		webbing1b.addBox(0.0F, 0.0F, -2.0F, 0, 3, 4, 0.0F);
		setRotateAngle(webbing1b, -0.091106186954104F, 0.0F, 0.4553564018453205F);
		egg2b = new ModelRenderer(this, 25, 10);
		egg2b.setRotationPoint(0.0F, -6.0F, 0.0F);
		egg2b.addBox(-2.0F, -1.0F, -2.0F, 4, 1, 4, 0.0F);
		webbing2b = new ModelRenderer(this, 80, 10);
		webbing2b.setRotationPoint(0.0F, -3.0F, -3.0F);
		webbing2b.addBox(-3.0F, 0.0F, 0.0F, 5, 4, 0, 0.0F);
		setRotateAngle(webbing2b, -0.40980330836826856F, 0.0F, 0.0F);
		webbing6a = new ModelRenderer(this, 21, 55);
		webbing6a.setRotationPoint(-2.5F, -3.0F, 0.0F);
		webbing6a.addBox(0.0F, 0.0F, -2.5F, 0, 4, 5, 0.0F);
		setRotateAngle(webbing6a, 0.0F, 0.0F, 0.36425021489121656F);
		webbing2c = new ModelRenderer(this, 56, 10);
		webbing2c.setRotationPoint(-3.0F, -3.0F, 0.0F);
		webbing2c.addBox(0.0F, 0.0F, -3.0F, 0, 4, 6, 0.0F);
		setRotateAngle(webbing2c, 0.091106186954104F, 0.0F, 0.27314402793711257F);
		egg5b = new ModelRenderer(this, 25, 42);
		egg5b.setRotationPoint(0.0F, -5.0F, 0.0F);
		egg5b.addBox(-2.0F, -1.0F, -2.0F, 4, 1, 4, 0.0F);
		webbing7a = new ModelRenderer(this, 17, 67);
		webbing7a.setRotationPoint(0.0F, -3.0F, 2.0F);
		webbing7a.addBox(-2.0F, 0.0F, 0.0F, 4, 3, 0, 0.0F);
		setRotateAngle(webbing7a, 0.27314402793711257F, 0.0F, 0.0F);
		egg5 = new ModelRenderer(this, 0, 42);
		egg5.setRotationPoint(3.0F, 24.0F, -4.0F);
		egg5.addBox(-3.0F, -5.0F, -3.0F, 6, 6, 6, 0.0F);
		setRotateAngle(egg5, 0.045553093477052F, -0.7740535232594852F, 0.091106186954104F);
		webbing7b = new ModelRenderer(this, 26, 67);
		webbing7b.setRotationPoint(-2.0F, -3.0F, 0.0F);
		webbing7b.addBox(0.0F, 0.0F, -2.0F, 0, 4, 4, 0.0F);
		setRotateAngle(webbing7b, 0.0F, 0.0F, 0.22759093446006054F);
		egg8 = new ModelRenderer(this, 0, 77);
		egg8.setRotationPoint(6.0F, 24.0F, -0.5F);
		egg8.addBox(-1.5F, -3.0F, -1.5F, 3, 4, 3, 0.0F);
		setRotateAngle(egg8, -0.045553093477052F, 0.0017453292519943296F, 0.136659280431156F);
		webbing2d = new ModelRenderer(this, 69, 10);
		webbing2d.setRotationPoint(0.0F, -3.0F, 3.0F);
		webbing2d.addBox(-2.9F, 0.0F, 0.0F, 5, 4, 0, 0.0F);
		setRotateAngle(webbing2d, 0.31869712141416456F, 0.0F, 0.18203784098300857F);
		webbing8a = new ModelRenderer(this, 13, 77);
		webbing8a.setRotationPoint(0.0F, -2.0F, 1.5F);
		webbing8a.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 0, 0.0F);
		setRotateAngle(webbing8a, 0.31869712141416456F, 0.0F, 0.0F);
		egg4 = new ModelRenderer(this, 0, 34);
		egg4.setRotationPoint(-5.0F, 24.0F, 1.0F);
		egg4.addBox(-1.5F, -3.0F, -1.5F, 3, 4, 3, 0.0F);
		setRotateAngle(egg4, -0.045553093477052F, -0.40980330836826856F, -0.091106186954104F);
		webbing5b2 = new ModelRenderer(this, 62, 42);
		webbing5b2.setRotationPoint(0.0F, 2.0F, 0.0F);
		webbing5b2.addBox(-1.0F, 0.0F, 0.0F, 4, 2, 0, 0.0F);
		setRotateAngle(webbing5b2, -0.27314402793711257F, 0.0F, 0.0F);
		webbing1a = new ModelRenderer(this, 17, 0);
		webbing1a.setRotationPoint(0.0F, -2.0F, 2.0F);
		webbing1a.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 0, 0.0F);
		setRotateAngle(webbing1a, 0.40980330836826856F, 0.0F, -0.091106186954104F);
		webbing5a = new ModelRenderer(this, 42, 42);
		webbing5a.setRotationPoint(0.0F, -3.0F, 3.0F);
		webbing5a.addBox(-3.0F, 0.0F, 0.0F, 6, 4, 0, 0.0F);
		setRotateAngle(webbing5a, 0.36425021489121656F, 0.0F, 0.0F);
		egg7 = new ModelRenderer(this, 0, 67);
		egg7.setRotationPoint(-1.5F, 24.0F, 4.5F);
		egg7.addBox(-2.0F, -4.0F, -2.0F, 4, 5, 4, 0.0F);
		setRotateAngle(egg7, -0.18203784098300857F, -0.4553564018453205F, -0.136659280431156F);
		webbing3a = new ModelRenderer(this, 17, 24);
		webbing3a.setRotationPoint(0.0F, -3.0F, 2.0F);
		webbing3a.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 0, 0.0F);
		setRotateAngle(webbing3a, 0.31869712141416456F, 0.0F, 0.0F);
		webbing2a = new ModelRenderer(this, 42, 10);
		webbing2a.setRotationPoint(0.0F, -4.0F, -3.0F);
		webbing2a.addBox(0.0F, 0.0F, 0.0F, 3, 2, 0, 0.0F);
		setRotateAngle(webbing2a, -0.36425021489121656F, 0.0F, -0.091106186954104F);
		webbing4a = new ModelRenderer(this, 13, 34);
		webbing4a.setRotationPoint(1.5F, -2.0F, 0.0F);
		webbing4a.addBox(0.0F, 0.0F, -1.5F, 0, 3, 3, 0.0F);
		setRotateAngle(webbing4a, 0.0F, 0.0F, -0.31869712141416456F);
		egg1 = new ModelRenderer(this, 0, 0);
		egg1.setRotationPoint(-2.0F, 24.0F, -4.0F);
		egg1.addBox(-2.0F, -4.0F, -2.0F, 4, 5, 4, 0.0F);
		setRotateAngle(egg1, 0.136659280431156F, 0.22759093446006054F, 0.0F);
		egg3 = new ModelRenderer(this, 0, 24);
		egg3.setRotationPoint(2.0F, 24.0F, 2.0F);
		egg3.addBox(-2.0F, -4.0F, -2.0F, 4, 5, 4, 0.0F);
		setRotateAngle(egg3, -0.045553093477052F, -0.36425021489121656F, 0.045553093477052F);
		webbing6b = new ModelRenderer(this, 32, 55);
		webbing6b.setRotationPoint(0.0F, -3.0F, 2.5F);
		webbing6b.addBox(-2.5F, 0.0F, 0.0F, 5, 4, 0, 0.0F);
		setRotateAngle(webbing6b, 0.36425021489121656F, 0.0F, 0.045553093477052F);
		webbing5b = new ModelRenderer(this, 55, 42);
		webbing5b.setRotationPoint(0.0F, -4.0F, -3.0F);
		webbing5b.addBox(0.0F, 0.0F, 0.0F, 3, 2, 0, 0.0F);
		setRotateAngle(webbing5b, -0.22759093446006054F, 0.0F, -0.091106186954104F);
		egg6 = new ModelRenderer(this, 0, 55);
		egg6.setRotationPoint(-2.0F, 24.0F, 0.0F);
		egg6.addBox(-2.5F, -5.0F, -2.5F, 5, 6, 5, 0.0F);
		setRotateAngle(egg6, 0.0F, 0.0F, -0.091106186954104F);
		webbing2a2 = new ModelRenderer(this, 49, 10);
		webbing2a2.setRotationPoint(0.0F, 2.0F, 0.0F);
		webbing2a2.addBox(0.0F, 0.0F, 0.0F, 3, 4, 0, 0.0F);
		setRotateAngle(webbing2a2, -0.18203784098300857F, 0.0F, 0.0F);
		egg2 = new ModelRenderer(this, 0, 10);
		egg2.setRotationPoint(2.0F, 24.0F, 2.0F);
		egg2.addBox(-3.0F, -6.0F, -3.0F, 6, 7, 6, 0.0F);
		setRotateAngle(egg2, -0.045553093477052F, -0.18203784098300857F, 0.0F);
		egg1.addChild(webbing1b);
		egg2.addChild(egg2b);
		egg2.addChild(webbing2b);
		egg6.addChild(webbing6a);
		egg2.addChild(webbing2c);
		egg5.addChild(egg5b);
		egg7.addChild(webbing7a);
		egg7.addChild(webbing7b);
		egg2.addChild(webbing2d);
		egg8.addChild(webbing8a);
		webbing5b.addChild(webbing5b2);
		egg1.addChild(webbing1a);
		egg5.addChild(webbing5a);
		egg3.addChild(webbing3a);
		egg2.addChild(webbing2a);
		egg4.addChild(webbing4a);
		egg6.addChild(webbing6b);
		egg5.addChild(webbing5b);
		webbing2a.addChild(webbing2a2);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw,
			float rotationPitch, float scale) {
		this.render(scale);
	}

	public void render(float scale) {
		egg5.render(scale);
		egg8.render(scale);
		egg4.render(scale);
		egg7.render(scale);
		egg1.render(scale);
		egg3.render(scale);
		egg6.render(scale);
		egg2.render(scale);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
