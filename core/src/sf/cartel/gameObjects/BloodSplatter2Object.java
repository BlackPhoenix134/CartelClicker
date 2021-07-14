package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Globals;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.rendering.RenderPipeline;

public class BloodSplatter2Object extends GameObject {
    public Sprite sprite;
    private AnimationController animationController;
    private float splatterDuration;


    BloodSplatter2Object(String uuid) {
        super(uuid);


        Texture texture = Assets.getAsset(AssetDescriptors.BLOOD_SPLATTER2_SHEET);
        sprite = new Sprite(texture);
        animationController = new AnimationController(texture, 3, 3, 0.1f);
    }
    public Sprite getSprite() {
        return sprite;
    }
    @Override
    public void update(float delta) {
        if(animationController.getCurrentFrameIdx() != animationController.getMaxAnimationIdx())
            animationController.addDelta(delta);
        else {
            splatterDuration += delta;
            if(splatterDuration >= 30f)
                this.setAlive(false);
        }


    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        sprite.setRegion(animationController.getCurrentFrame());
        pipeline.add(sprite, Globals.DRAW_ORDER_WORLD + 5);
    }
}
