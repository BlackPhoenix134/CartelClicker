package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.PlayerData;
import sf.cartel.rendering.RenderPipeline;

public class IngameUi  {
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private PlayerData playerData;
    private SpriteDrawableObject topBarUi;

    public IngameUi(PlayerData playerData) {
        this.playerData = playerData;
         topBarUi = gameObjectManager.create(SpriteDrawableObject.class);
        Sprite sprite = new Sprite(Assets.getAsset(AssetDescriptors.TOP_BAR));
        sprite.setScale(Gdx.graphics.getWidth() / sprite.getWidth(), Gdx.graphics.getHeight() / sprite.getHeight());
        sprite.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        topBarUi.setSprite(sprite);
        topBarUi.setUiObject(true);
    }

    public void update(float delta) {
        gameObjectManager.update(delta);
    }

    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
        Sprite topBarSprite = topBarUi.getSprite();
        float spriteWidth = topBarSprite.getWidth() * topBarSprite.getScaleX();
        float height = Gdx.graphics.getHeight() - (topBarSprite.getHeight() * topBarSprite.getScaleY() * 0.0190f);
        pipeline.addUi(playerData.weed.toString(), new Vector2(spriteWidth * 0.32f, height), 3.5f,10000);
        pipeline.addUi(playerData.pills.toString(), new Vector2(spriteWidth * 0.46f, height), 3.5f,10000);
        pipeline.addUi(playerData.coke.toString(), new Vector2(spriteWidth * 0.61f, height), 3.5f,10000);
        pipeline.addUi(playerData.oxy.toString(), new Vector2(spriteWidth * 0.76f, height), 3.5f,10000);
        pipeline.addUi(playerData.heroin.toString(), new Vector2(spriteWidth * 0.90f, height), 3.5f,10000);
    }
}
