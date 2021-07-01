package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.PlayerData;
import sf.cartel.rendering.RenderPipeline;

public class TopBarUiObject extends GameObject {
    private PlayerData playerData;
    private Sprite sprite;

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
        pipeline.addUi(sprite, 1000);
        float spriteWidth = sprite.getWidth() * sprite.getScaleX();
        float height = Gdx.graphics.getHeight() - (sprite.getHeight() * sprite.getScaleY() * 0.02f);
        pipeline.addUi(playerData.weed.toString(), new Vector2(spriteWidth * 0.32f, height), 3.5f,10000);
        pipeline.addUi(playerData.pills.toString(), new Vector2(spriteWidth * 0.46f, height), 3.5f,10000);
        pipeline.addUi(playerData.coke.toString(), new Vector2(spriteWidth * 0.61f, height), 3.5f,10000);
        pipeline.addUi(playerData.oxy.toString(), new Vector2(spriteWidth * 0.76f, height), 3.5f,10000);
        pipeline.addUi(playerData.heroin.toString(), new Vector2(spriteWidth * 0.90f, height), 3.5f,10000);
    }
}
