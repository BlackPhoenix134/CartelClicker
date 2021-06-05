package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Clickable;
import sf.cartel.core.Globals;
import sf.cartel.input.InputEvent;
import sf.cartel.rendering.RenderPipeline;

public class SpriteRenderObject extends GameObject {
    private Sprite sprite;
    private int drawLayer = 0;

    SpriteRenderObject(String uuid) {
        super(uuid);
    }

    public int getDrawLayer() {
        return drawLayer;
    }

    public void setDrawLayer(int drawLayer) {
        this.drawLayer = drawLayer;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector2 getPosition() {
        return new Vector2(sprite.getX(), sprite.getY());
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.add(sprite, drawLayer);
    }
}