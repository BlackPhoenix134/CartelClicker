package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.rendering.RenderPipeline;


public class AnimatedSpriteDrawableObject extends GameObject{
    private Sprite sprite;
    private int drawOrder;
    private boolean isUiObject;
    private AnimationController animationController;

    AnimatedSpriteDrawableObject(String uuid) {
        super(uuid);
    }

    public void init(Texture texture, int cols, int rows, float frameTime) {
        sprite = new Sprite(texture);
        animationController = new AnimationController(texture, cols, rows, frameTime);
    }

    public AnimatedSpriteDrawableObject() {
        this(null);
    }

    public void setDrawOrder(int drawOrder) {
        this.drawOrder = drawOrder;
    }

    public void setUiObject(boolean uiObject) {
        isUiObject = uiObject;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getDrawOrder() {
        return drawOrder;
    }

    public boolean isUiObject() {
        return isUiObject;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        animationController.addDelta(delta);
        sprite.setRegion(animationController.getCurrentFrame());
        if(isUiObject)
            pipeline.addUi(sprite, drawOrder);
        else
            pipeline.add(sprite, drawOrder);
    }
}
