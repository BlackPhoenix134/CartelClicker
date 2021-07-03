package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.DrugType;
import sf.cartel.core.Globals;
import sf.cartel.core.PlayerData;
import sf.cartel.rendering.RenderPipeline;

public class TopBarUiObject extends GameObject {
    private PlayerData playerData;
    private Sprite sprite;

    private int DRAW_ORDER_UI = Globals.DRAW_ORDER_UI;



    TopBarUiObject(String uuid) {
        super(uuid);
    }

    public void setPlayerData(PlayerData playerData) {
        this.playerData = playerData;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.addUi(sprite, DRAW_ORDER_UI);
        float spriteWidth = sprite.getWidth() * sprite.getScaleX();
        float height = Gdx.graphics.getHeight() - (sprite.getHeight() * sprite.getScaleY() * 0.04f);
        float initalX = spriteWidth * 0.3475f;
        float inc = 194 * sprite.getScaleX();
        pipeline.addUi(playerData.money.toString(), new Vector2(spriteWidth * 0.16f, height), 3.5f,DRAW_ORDER_UI+1);
        pipeline.addUi(playerData.getDrug(DrugType.Weed).toString(), new Vector2(initalX, height), 3.5f,DRAW_ORDER_UI+1);
        pipeline.addUi(playerData.getDrug(DrugType.Pills).toString(), new Vector2(initalX + inc * 1, height), 3.5f,DRAW_ORDER_UI+1);
        pipeline.addUi(playerData.getDrug(DrugType.Coke).toString(), new Vector2(initalX + inc * 2, height), 3.5f,DRAW_ORDER_UI+1);
        pipeline.addUi(playerData.getDrug(DrugType.Oxy).toString(), new Vector2(initalX + inc * 3, height), 3.5f,DRAW_ORDER_UI+1);
        pipeline.addUi(playerData.getDrug(DrugType.Heroin).toString(), new Vector2(initalX + inc * 4, height), 3.5f,DRAW_ORDER_UI+1);
    }
}
