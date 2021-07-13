package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.DrugType;
import sf.cartel.core.PlayerData;
import sf.cartel.rendering.RenderPipeline;


public abstract class GameObject {
    private String uuid = null;
    private boolean isAlive = true;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getUuid() {
        return uuid;
    }

    GameObject(String uuid) {
        this.uuid = uuid;
    }

    public abstract void update(float delta);
    public abstract void draw(float delta, RenderPipeline pipeline);

    protected void onObjectDestroyed() {
    }

}
