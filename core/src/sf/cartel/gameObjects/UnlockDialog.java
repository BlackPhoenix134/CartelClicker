package sf.cartel.gameObjects;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Clickable;
import sf.cartel.core.DrugType;
import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Gameplay;
import sf.cartel.core.Globals;
import sf.cartel.core.Physics.Area2D;
import sf.cartel.core.Physics.Rectangle;
import sf.cartel.core.PlayerData;
import sf.cartel.core.clickHandler.ObjectClickBinding;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.input.InputEvent;
import sf.cartel.rendering.RenderPipeline;

public class UnlockDialog extends GameObject {
    private PlayerData playerData;
    private List<ObjectClickBinding> bindings = new ArrayList<>();
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private ObjectClickHandler objectClickHandler;
    private int dialogDrawOrder = Globals.DRAW_ORDER_DIALOG;
    private int dialogClickPriority = Globals.CLICK_ORDER_UI_DIALOG;
    private Sound soundButton = Assets.getAsset(AssetDescriptors.SOUND_BUTTON);
    private DrugType drugType;
    private Vector2 mapPosition;
    private Gameplay gameplay;
    private SpriteDrawableObject backgroundObject;
    private Vector2 priceTextPos;

    UnlockDialog(String uuid) {
        super(uuid);
    }

    public void init(ObjectClickHandler objectClickHandler, PlayerData playerData, Gameplay gameplay, DrugType drugType, Vector2 mapPosition) {
        this.objectClickHandler = objectClickHandler;
        this.playerData = playerData;
        this.drugType = drugType;
        this.mapPosition = mapPosition;
        this.gameplay = gameplay;

         backgroundObject = createBackground();
        createClickBlocker(backgroundObject);
        createUnlockButton(backgroundObject);
        Vector2 spritePos = Sprites.position(backgroundObject.getSprite());
        float bgSpriteHeight = Sprites.scaledHeight(backgroundObject.getSprite());
        priceTextPos = new Vector2(spritePos.x, spritePos.y + bgSpriteHeight/2*0.70f);
    }

    private void createClickBlocker(SpriteDrawableObject backgroundObject) {
        bindings.add(objectClickHandler.addTouchUpClickable(new Clickable() {
            @Override
            public void onClicked(InputEvent inputEvent) {
                inputEvent.setConsumed(true);
                hide();
            }

            @Override
            public Vector2 getPosition() {
                return Sprites.position(backgroundObject.getSprite());
            }

            @Override
            public Area2D getArea2D() {
                return new Rectangle(-1000000, -1000000, 2000000, 2000000);
            }
        }, dialogClickPriority+1, false));
    }

    private SpriteDrawableObject createBackground() {
        SpriteDrawableObject dialogBackground = gameObjectManager.create(SpriteDrawableObject.class);
        dialogBackground.setDrawOrder(dialogDrawOrder);
        dialogBackground.setUiObject(false);
        Sprite sprite = new Sprite(Assets.getAsset(AssetDescriptors.UI_INFO_BOX));
        sprite.setScale(0.3f);
        float height = Sprites.scaledHeight(sprite);
        sprite.setPosition(mapPosition.x, mapPosition.y + height/3);
        dialogBackground.setSprite(sprite);
        bindings.add(objectClickHandler.addTouchUpClickable(new Clickable() {
            @Override
            public void onClicked(InputEvent inputEvent) {
                inputEvent.setConsumed(true);
            }

            @Override
            public Vector2 getPosition() {
               return Sprites.position(dialogBackground.getSprite());
            }

            @Override
            public Area2D getArea2D() {
                float width = Sprites.scaledWidth(dialogBackground.getSprite());
                float height = Sprites.scaledHeight(dialogBackground.getSprite());
                return new Rectangle(sprite.getX() - width/2, sprite.getY() - height/2,
                        width, height);
            }
        }, dialogClickPriority+2, false));
        return dialogBackground;
    }

    private void createUnlockButton(SpriteDrawableObject backgroundDrawable) {
        ClickableSpriteDrawableObject buttonObj = gameObjectManager.create(ClickableSpriteDrawableObject.class);
        buttonObj.setDrawOrder(dialogDrawOrder + 1);
        buttonObj.setUiObject(false);
        Sprite btnTexture = new Sprite(Assets.getAsset(AssetDescriptors.BUTTON_UNLOCK));
        btnTexture.setScale(0.6f);
        buttonObj.setSprite(btnTexture);
        Sprite bgSprite = backgroundDrawable.getSprite();
        Vector2 bgSpritePos = Sprites.position(bgSprite);

        Sprite sprite = buttonObj.getSprite();
        sprite.setPosition(bgSpritePos.x, bgSpritePos.y - Sprites.scaledHeight(bgSprite) / 2 * 0.60f);
        buttonObj.setOnClicked((item) -> {
            soundButton.play();
            if(gameplay.canAffordUnlock(drugType)) {
                gameplay.unlock(drugType);
                hide();
            }
        });

        bindings.add(objectClickHandler.addTouchUpClickable(buttonObj, dialogClickPriority + 3, false));
    }

    private void hide() {
        for (ObjectClickBinding binding : bindings)
            binding.unsubscribe();
        gameObjectManager.killAll();
        setAlive(false);
    }

    @Override
    public void update(float delta) {
        gameObjectManager.update(delta);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
        pipeline.add(playerData.getUnlocks().getUnlockPrice(drugType)+"", priceTextPos, dialogDrawOrder + 2);
    }
}