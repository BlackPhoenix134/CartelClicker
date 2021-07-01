package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Clickable;
import sf.cartel.core.Consumer;
import sf.cartel.core.Physics.Area2D;
import sf.cartel.core.Physics.Rectangle;
import sf.cartel.input.InputEvent;
import sf.cartel.rendering.RenderPipeline;

public class ClickableSpriteDrawableObject extends GameObject implements Clickable {
    private Sprite sprite;
    private int drawOrder;
    private boolean isUiObject;
    private boolean consumeClickedInput = true;
    private Consumer<ClickableSpriteDrawableObject> onClicked;

    ClickableSpriteDrawableObject(String uuid) {
        super(uuid);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
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

    public void setOnClicked(Consumer<ClickableSpriteDrawableObject> onClicked) {
        this.onClicked = onClicked;
    }

    public boolean isConsumeClickedInput() {
        return consumeClickedInput;
    }

    public void setConsumeClickedInput(boolean consumeClickedInput) {
        this.consumeClickedInput = consumeClickedInput;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        if(isUiObject)
            pipeline.addUi(sprite, drawOrder);
        else
            pipeline.add(sprite, drawOrder);
    }

    @Override
    public void onClicked(InputEvent inputEvent) {
        if(onClicked != null) {
            inputEvent.setConsumed(consumeClickedInput);
            onClicked.call(this);
        }
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(sprite.getX(), sprite.getY());
    }

    @Override
    public Area2D getArea2D() {
        float scaledWidth = sprite.getWidth() * sprite.getScaleX();
        float scaledHeight = sprite.getHeight() * sprite.getScaleY();
        return new Rectangle(sprite.getX() - scaledWidth/2, sprite.getY() - scaledHeight/2,
                scaledWidth, scaledHeight);
    }
}
