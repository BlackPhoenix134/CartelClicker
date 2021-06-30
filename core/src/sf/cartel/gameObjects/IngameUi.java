package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.PlayerData;
import sf.cartel.input.InputHandler;
import sf.cartel.rendering.RenderPipeline;
import sf.cartel.screens.AbstractScreen;
import sf.cartel.screens.MainMenuScreen;
import sf.cartel.ui.AliveButton;

public class IngameUi  {
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private PlayerData playerData;
    private SpriteDrawableObject topBarUi;
    private SpriteDrawableObject upgradeButtonUi;
    private SpriteDrawableObject menuButtonUi;


    public IngameUi(PlayerData playerData) {
        this.playerData = playerData;


        topBarUi = gameObjectManager.create(SpriteDrawableObject.class);
        Sprite sprite = new Sprite(Assets.getAsset(AssetDescriptors.TOP_BAR));
        sprite.setScale(Gdx.graphics.getWidth() / sprite.getWidth(), Gdx.graphics.getHeight() / sprite.getHeight());
        sprite.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        topBarUi.setSprite(sprite);
        topBarUi.setUiObject(true);


        // TODO CLICKABLE FOR BUTTONS
        upgradeButtonUi = gameObjectManager.create(SpriteDrawableObject.class);
        sprite = new Sprite(Assets.getAsset(AssetDescriptors.BUTTON_UPGRADE));

        sprite.setScale(Gdx.graphics.getWidth() / sprite.getWidth() * 0.4f, Gdx.graphics.getHeight() / sprite.getHeight() * 0.4f);
        sprite.setPosition(Gdx.graphics.getWidth() *.92f, Gdx.graphics.getHeight() *.06f);
        upgradeButtonUi.setSprite(sprite);
        upgradeButtonUi.setUiObject(true);

        menuButtonUi = gameObjectManager.create(SpriteDrawableObject.class);
        sprite = new Sprite(Assets.getAsset(AssetDescriptors.BUTTON_MENU));

        sprite.setScale(Gdx.graphics.getWidth() / sprite.getWidth() * 0.4f, Gdx.graphics.getHeight() / sprite.getHeight() * 0.4f);
        sprite.setPosition(Gdx.graphics.getWidth() *.1f, Gdx.graphics.getHeight() *.06f);
        menuButtonUi.setSprite(sprite);
        menuButtonUi.setUiObject(true);


    }

    public void update(float delta) {
        gameObjectManager.update(delta);
    }

    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
        Sprite topBarSprite = topBarUi.getSprite();
        float spriteWidth = topBarSprite.getWidth() * topBarSprite.getScaleX();
        float height = Gdx.graphics.getHeight() - (topBarSprite.getHeight() * topBarSprite.getScaleY() * 0.02f);
        pipeline.addUi(playerData.weed.toString(), new Vector2(spriteWidth * 0.32f, height), 3.5f,10000);
        pipeline.addUi(playerData.pills.toString(), new Vector2(spriteWidth * 0.46f, height), 3.5f,10000);
        pipeline.addUi(playerData.coke.toString(), new Vector2(spriteWidth * 0.61f, height), 3.5f,10000);
        pipeline.addUi(playerData.oxy.toString(), new Vector2(spriteWidth * 0.76f, height), 3.5f,10000);
        pipeline.addUi(playerData.heroin.toString(), new Vector2(spriteWidth * 0.90f, height), 3.5f,10000);
    }
}
