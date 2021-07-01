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

//        backBtn.addListener(new AliveButton.AliveButtonListener() {
//            @Override
//            public void onClick() {
//                sound.play();
////                screenManager.showScreen(MainMenuScreen.class);
//            }
//        });

    }

    private void createUpgradeButtons(SpriteDrawableObject backgroundDrawable) {
        //32 58 86
        //21 34 50 65 80
        Sprite backgroundSprite = backgroundDrawable.getSprite();

        ClickableSpriteDrawableObject btn = createUpgradeButton();
        Sprite sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.21f);
         btn = createUpgradeButton();
         sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.34f);
         btn = createUpgradeButton();
         sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.50f);
        btn = createUpgradeButton();
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.65f);
        btn = createUpgradeButton();
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.80f);

        btn = createUpgradeButton();
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite)  * 0.21f);
        btn = createUpgradeButton();
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite)  * 0.34f);
        btn = createUpgradeButton();
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite)  * 0.50f);
        btn = createUpgradeButton();
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite) * 0.65f);
        btn = createUpgradeButton();
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite)  * 0.80f);

        btn = createUpgradeButton();
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.21f);
        btn = createUpgradeButton();
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.34f);
        btn = createUpgradeButton();
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.50f);
        btn = createUpgradeButton();
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.65f);
        btn = createUpgradeButton();
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.80f);
    }

    private ClickableSpriteDrawableObject createUpgradeButton() {
        ClickableSpriteDrawableObject upgradeBtn = gameObjectManager.create(ClickableSpriteDrawableObject.class);
        objects.add(upgradeBtn);
        upgradeBtn.setDrawOrder(dialogDrawOrder + 1);
        upgradeBtn.setUiObject(true);
        Sprite sprite = new Sprite(Assets.getAsset(AssetDescriptors.SQUARE_40x40));
        sprite.setScale(2);
        upgradeBtn.setSprite(sprite);
        return upgradeBtn;
    }
/*
    private void addButtonClickHandler(Sprite sprite, TicketType ticketType) {
        bindings.add(objectClickHandler.addTouchUpClickable(new Clickable() {
            @Override
            public void onClicked(InputEvent inputEvent) {
                inputEvent.setConsumed(true);
                invokeClicked(ticketType);
            }

            @Override
            public Vector2 getPosition() {
                return new Vector2(sprite.getX(), sprite.getY());
            }

            @Override
            public Area2D getArea2D() {
                float scaledWidth = sprite.getWidth() * sprite.getScaleX();
                float scaledHeight = sprite.getHeight() * sprite.getScaleY();
                return new BoundingBox(getPosition().x, getPosition().y, scaledWidth, scaledHeight);
            }
        }, dialogClickPriority+2, true));
    }
*/
    private void invokeClicked() {
        hide();
    }

    private void hide() {
        for (ObjectClickBinding binding : bindings)
            binding.unsubscribe();
        for (GameObject gameObject : objects)
            gameObject.setAlive(false);
    }
}