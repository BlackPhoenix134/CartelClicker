package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import sf.cartel.core.Clickable;
import sf.cartel.core.Globals;
import sf.cartel.input.InputEvent;
import sf.cartel.rendering.RenderPipeline;

public class DebugClickableObject extends GameObject implements Clickable {
    private Sprite sprite;

    DebugClickableObject(String uuid) {
        super(uuid);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void onClicked(InputEvent inputEvent) {
        inputEvent.setConsumed(true);
        sprite.setColor(new Color(Globals.getRandomFloat(0, 1), Globals.getRandomFloat(0, 1), Globals.getRandomFloat(0, 1), 255));
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(sprite.getX(), sprite.getY());
    }

    @Override
    public Rectangle getBoundingBox() {
        Rectangle box = sprite.getBoundingRectangle();
        return new Rectangle(box.x - box.width / 2, box.y - box.height / 2, box.width, box.height);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.add(sprite, 100);
    }
}
