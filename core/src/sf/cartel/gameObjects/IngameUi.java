package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Clickable;
import sf.cartel.core.Physics.Area2D;
import sf.cartel.core.PlayerData;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.input.InputEvent;
import sf.cartel.input.InputHandler;
import sf.cartel.rendering.RenderPipeline;
import sf.cartel.screens.AbstractScreen;
import sf.cartel.screens.MainMenuScreen;
import sf.cartel.ui.AliveButton;
import sf.cartel.ui.UpgradeDialog;

public class IngameUi  {
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private PlayerData playerData;
    private ObjectClickHandler objectClickHandler;

    public IngameUi(PlayerData playerData, ObjectClickHandler objectClickHandler) {
        this.playerData = playerData;
        this.objectClickHandler = objectClickHandler;
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

        upgradeSprite.setScale(Gdx.graphics.getWidth() / upgradeSprite.getWidth() * 0.4f, Gdx.graphics.getHeight() / upgradeSprite.getHeight() * 0.4f);
        upgradeSprite.setPosition(Gdx.graphics.getWidth() *.92f, Gdx.graphics.getHeight() *.06f);
        upgradeButtonUi.setSprite(upgradeSprite);
        upgradeButtonUi.setUiObject(true);

        ClickableSpriteDrawableObject menuButtonUi = gameObjectManager.create(ClickableSpriteDrawableObject.class);
        Sprite menuSprite = new Sprite(Assets.getAsset(AssetDescriptors.BUTTON_MENU));

        menuSprite.setScale(Gdx.graphics.getWidth() / menuSprite.getWidth() * 0.4f, Gdx.graphics.getHeight() / menuSprite.getHeight() * 0.4f);
        menuSprite.setPosition(Gdx.graphics.getWidth() *.1f, Gdx.graphics.getHeight() *.06f);
        menuButtonUi.setSprite(menuSprite);
        menuButtonUi.setUiObject(true);

        objectClickHandler.addTouchUpClickable(upgradeButtonUi, 1000000, true);

        upgradeButtonUi.setOnClicked(obj -> {
            new UpgradeDialog(gameObjectManager, objectClickHandler, playerData);
        });
    }

    public void update(float delta) {
        gameObjectManager.update(delta);
    }

    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);

    }


}
