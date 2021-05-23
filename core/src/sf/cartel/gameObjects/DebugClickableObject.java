package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


import sf.cartel.core.Clickable;
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
      Gdx.app.log("CLICKED", this.getUuid());
    }

    @Override
    public Vector2 getPosition() {
        return null;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {

    }
}
