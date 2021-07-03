package sf.cartel.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

import java.util.ArrayList;
import java.util.List;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Clickable;
import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Globals;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.Physics.Area2D;
import sf.cartel.core.Physics.BoundingBox;
import sf.cartel.core.Physics.Rectangle;
import sf.cartel.core.PlayerData;
import sf.cartel.core.clickHandler.ObjectClickBinding;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.gameObjects.ClickableSpriteDrawableObject;
import sf.cartel.gameObjects.GameObject;
import sf.cartel.gameObjects.GameObjectManager;
import sf.cartel.gameObjects.SpriteDrawableObject;
import sf.cartel.gameObjects.UpgradeButtonsUiObject;
import sf.cartel.input.InputEvent;

public class UpgradeDialog {
    private PlayerData playerData;
    private List<ObjectClickBinding> bindings = new ArrayList<>();
    private List<GameObject> objects = new ArrayList<>();
    private GameObjectManager gameObjectManager;
    private ObjectClickHandler objectClickHandler;
    private int dialogDrawOrder = 100000;
    private int dialogClickPriority = 100000;

    public UpgradeDialog(GameObjectManager gameObjectManager, ObjectClickHandler objectClickHandler, PlayerData playerData) {
        this.gameObjectManager = gameObjectManager;
        this.objectClickHandler = objectClickHandler;
        this.playerData = playerData;
        createClickBlocker();
        SpriteDrawableObject drawableObject = createBackground();
        createUpgradeButtons(drawableObject);
        createBackButton(drawableObject);
    }

    private void createClickBlocker() {
        bindings.add(objectClickHandler.addTouchUpClickable(new Clickable() {
            @Override
            public void onClicked(InputEvent inputEvent) {
                inputEvent.setConsumed(true);
            }

            @Override
            public Vector2 getPosition() {
                return new Vector2(0, 0);
            }

            @Override
            public Area2D getArea2D() {
                return new Rectangle(-10000, -10000, 20000, 20000);
            }
        }, dialogClickPriority, true));
    }


    private SpriteDrawableObject createBackground() {
        SpriteDrawableObject dialogBackground = gameObjectManager.create(SpriteDrawableObject.class);
        objects.add(dialogBackground);
        Vector2 windowSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        dialogBackground.setDrawOrder(dialogDrawOrder);
        dialogBackground.setUiObject(true);
        Sprite sprite = new Sprite(Assets.getAsset(AssetDescriptors.UI_UPGRADE_BACKGROUND));
        //sprite.setColor(new Color(1, 1, 1, 0.6f));
        sprite.setPosition(windowSize.x / 2f, windowSize.y / 2f);
        sprite.setScale(GoodMath.ratio(sprite.getWidth(), windowSize.x), GoodMath.ratio(sprite.getHeight(), windowSize.y));
        dialogBackground.setSprite(sprite);
        return dialogBackground;
    }

    private void createBackButton(SpriteDrawableObject backgroundDrawable) {
        ClickableSpriteDrawableObject backBtnDrawable = gameObjectManager.create(ClickableSpriteDrawableObject.class);
        objects.add(backBtnDrawable);
        backBtnDrawable.setDrawOrder(dialogDrawOrder + 1);
        backBtnDrawable.setUiObject(true);

        Sprite btnTexture = new Sprite(Assets.getAsset(AssetDescriptors.BUTTON_BACK));
        btnTexture.setScale(2);

        backBtnDrawable.setSprite(btnTexture);

        Sprite backgroundSprite = backgroundDrawable.getSprite();

        Sprite backBtn = backBtnDrawable.getSprite();
        backBtn.setPosition(Sprites.getScaledWidth(backgroundSprite)*0.075f, Sprites.getScaledHeight(backgroundSprite)  *.95f);

        backBtnDrawable.setOnClicked((item) -> {
            hide();
        });

        bindings.add(objectClickHandler.addTouchUpClickable(backBtnDrawable, dialogClickPriority + 1, true));
    }

    private void createUpgradeButtons(SpriteDrawableObject backgroundDrawable) {
        //32 58 86
        //21 34 50 65 80
        Sprite backgroundSprite = backgroundDrawable.getSprite();

        UpgradeButtonsUiObject upgradeButtons = gameObjectManager.create(UpgradeButtonsUiObject.class);
        upgradeButtons.init(Globals.getPlayerData(), backgroundSprite, dialogDrawOrder + 1);
        objects.add(upgradeButtons);
    }



    private void hide() {
        for (ObjectClickBinding binding : bindings)
            binding.unsubscribe();
        for (GameObject gameObject : objects)
            gameObject.setAlive(false);
    }
}