package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Gameplay;
import sf.cartel.core.PlayerData;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.rendering.RenderPipeline;
import sf.cartel.ui.UpgradeDialog;

public class IngameUi  {
    private  Gameplay gameplay;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private PlayerData playerData;
    private ObjectClickHandler objectClickHandler;

    private Sound soundButton = Assets.getAsset(AssetDescriptors.SOUND_BUTTON);


    public IngameUi(PlayerData playerData, ObjectClickHandler objectClickHandler, Gameplay gameplay) {
        this.playerData = playerData;
        this.objectClickHandler = objectClickHandler;
        this.gameplay = gameplay;
        createUi();
    }

    private void createUi() {
        TopBarUiObject topBarUi = gameObjectManager.create(TopBarUiObject.class);
        topBarUi.setPlayerData(playerData);
        Sprite topBarSprite = new Sprite(Assets.getAsset(AssetDescriptors.TOP_BAR));
        topBarSprite.setScale(Gdx.graphics.getWidth() / topBarSprite.getWidth(), Gdx.graphics.getHeight() / topBarSprite.getHeight());
        topBarSprite.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        topBarUi.setSprite(topBarSprite);

        ClickableSpriteDrawableObject upgradeButtonUi = gameObjectManager.create(ClickableSpriteDrawableObject.class);
        Sprite upgradeSprite = new Sprite(Assets.getAsset(AssetDescriptors.BUTTON_UPGRADE));

        upgradeSprite.setScale(Gdx.graphics.getWidth() / upgradeSprite.getWidth() * 0.15f, Gdx.graphics.getHeight() / upgradeSprite.getHeight() * 0.1f);

        upgradeSprite.setPosition(Gdx.graphics.getWidth() *.90f, Gdx.graphics.getHeight() *.06f);
        upgradeButtonUi.setSprite(upgradeSprite);
        upgradeButtonUi.setUiObject(true);

        ClickableSpriteDrawableObject menuButtonUi = gameObjectManager.create(ClickableSpriteDrawableObject.class);
        Sprite menuSprite = new Sprite(Assets.getAsset(AssetDescriptors.BUTTON_MENU));

        menuSprite.setScale(Gdx.graphics.getWidth() / menuSprite.getWidth() * 0.15f, Gdx.graphics.getHeight() / menuSprite.getHeight() * 0.1f);
        menuSprite.setPosition(Gdx.graphics.getWidth() *.1f, Gdx.graphics.getHeight() *.06f);
        menuButtonUi.setSprite(menuSprite);
        menuButtonUi.setUiObject(true);

        ClickableSpriteDrawableObject sellAllButtonUi = gameObjectManager.create(ClickableSpriteDrawableObject.class);
        Sprite sellAllButtonSprite = new Sprite(Assets.getAsset(AssetDescriptors.BUTTON_SELL));

        sellAllButtonSprite.setScale(Gdx.graphics.getWidth() / sellAllButtonSprite.getWidth() * 0.15f, Gdx.graphics.getHeight() / sellAllButtonSprite.getHeight() * 0.1f);

        sellAllButtonSprite.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() *.06f);
        sellAllButtonUi.setSprite(sellAllButtonSprite);
        sellAllButtonUi.setUiObject(true);

        objectClickHandler.addTouchUpClickable(upgradeButtonUi, 1000000, true);
        objectClickHandler.addTouchUpClickable(sellAllButtonUi, 1000000, true);

        upgradeButtonUi.setOnClicked(obj -> {
            soundButton.play();
            new UpgradeDialog(gameObjectManager, gameplay, objectClickHandler, playerData);
        });

        sellAllButtonUi.setOnClicked(obj -> {
            soundButton.play();
            gameplay.sellAllDrugs();
        });
    }

    public void update(float delta) {
        gameObjectManager.update(delta);
    }

    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);

    }


}
