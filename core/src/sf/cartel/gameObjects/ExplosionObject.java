package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.rendering.RenderPipeline;

public class ExplosionObject extends GameObject {
    private Sprite sprite;
    private int drawOrder;
    private AnimationController animationController;

    ExplosionObject(String uuid) {
        super(uuid);
    }

    public void init(AnimationController animationController) {
        this.animationController = animationController;
        sprite = new Sprite(animationController.getAnimSheet());
    }


    public void setDrawOrder(int drawOrder) {
        this.drawOrder = drawOrder;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getDrawOrder() {
        return drawOrder;
    }

    @Override
    public void update(float delta) {
        animationController.addDelta(delta);
        if(animationController.getCurrentFrameIdx() == animationController.getMaxAnimationIdx())
            setAlive(false);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        sprite.setRegion(animationController.getCurrentFrame());
        pipeline.add(sprite, drawOrder);
    }
}