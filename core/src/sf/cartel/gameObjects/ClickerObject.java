package sf.cartel.gameObjects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

import sf.cartel.core.Consumer;
import sf.cartel.core.Consumer2;
import sf.cartel.core.DrugType;
import sf.cartel.core.Physics.Rectangle;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Physics.Area2D;
import sf.cartel.core.Clickable;
import sf.cartel.core.Globals;
import sf.cartel.core.PlayerData;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.input.InputEvent;
import sf.cartel.rendering.RenderPipeline;
import sun.security.action.GetLongAction;

public class ClickerObject extends GameObject implements Clickable {
    private Sprite sprite;
    private boolean isUnlocked;
    private Consumer<ClickerObject> onClicked;
    private Area2D area2D;
    private  PlayerData playerData;
    private  DrugType drugType;

    ClickerObject(String uuid) {
        super(uuid);
    }

    public void init(PlayerData playerData, DrugType drugType) {
        this.playerData = playerData;
        this.drugType = drugType;
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

    public void setOnClicked(Consumer<ClickerObject> onClicked) {
        this.onClicked = onClicked;
    }

    @Override
    public void onClicked(InputEvent inputEvent) {
        inputEvent.setConsumed(true);
        onClicked.call(this);
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
        if(playerData.getUnlocks().isUnlocked(drugType))
            sprite.setColor(0f, 0.6f, 1f, 0.5f);
        else
            sprite.setColor(0.8f, 0f, 0f, 0.5f);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.add(sprite, Globals.DRAW_ORDER_WORLD + 2);
    }

}