package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sf.cartel.core.Physics.Rectangle;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Physics.Area2D;
import sf.cartel.core.Clickable;
import sf.cartel.core.Globals;
import sf.cartel.core.PlayerData;
import sf.cartel.input.InputEvent;
import sf.cartel.rendering.RenderPipeline;

public class ClickerObject extends GameObject implements Clickable {
    private Sprite sprite;
    private boolean isUnlocked;
    private PlayerData playerData;
    private Area2D area2D;

    ClickerObject(String uuid) {
        super(uuid);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public void setPlayerData(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public void onClicked(InputEvent inputEvent) {
        //if(isUnlocked)  {
            inputEvent.setConsumed(true);
            sprite.setColor(new Color(Globals.getRandomFloat(0, 1), Globals.getRandomFloat(0, 1), Globals.getRandomFloat(0, 1), 255));

        //}
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(sprite.getX(), sprite.getY());
    }


    public void setArea2D(Area2D area2D) {
        this.area2D = area2D;
    }

    @Override
    public Area2D getArea2D() {
     return area2D;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.add(sprite, 10);
    }
}