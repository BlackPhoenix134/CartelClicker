package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.List;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.DrugType;
import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Gameplay;
import sf.cartel.core.PlayerData;
import sf.cartel.core.clickHandler.ObjectClickBinding;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.rendering.RenderPipeline;

public class UpgradeButtonsUiObject extends GameObject {
    private PlayerData playerData;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private int drawOrder;
    private List<ObjectClickBinding> objectClickBindings = new ArrayList<>();
    private ClickableSpriteDrawableObject[][] spriteDrawableObjects = new ClickableSpriteDrawableObject[5][3];
    private ObjectClickHandler objectClickHandler;
    private Gameplay gameplay;

    UpgradeButtonsUiObject(String uuid) {
        super(uuid);
    }

    public void init(PlayerData playerData, ObjectClickHandler objectClickHandler, Gameplay gameplay, Sprite backgroundSprite, int drawOrder) {
        this.playerData = playerData;
        this.objectClickHandler = objectClickHandler;
        this.drawOrder = drawOrder;
        this.gameplay = gameplay;

        ClickableSpriteDrawableObject btn = createUpgradeButton(0, 0);
        Sprite sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.80f);
        btn = createUpgradeButton(1, 0);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.65f);
        btn = createUpgradeButton(2, 0);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.50f);
        btn = createUpgradeButton(3, 0);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.34f);
        btn = createUpgradeButton(4, 0);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.21f);

        btn = createUpgradeButton(0, 1);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite)  * 0.80f);
        btn = createUpgradeButton(1, 1);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite)  * 0.65f);
        btn = createUpgradeButton(2, 1);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite)  * 0.50f);
        btn = createUpgradeButton(3, 1);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f,  Sprites.getScaledHeight(backgroundSprite)  * 0.34f);
        btn = createUpgradeButton(4, 1);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f,  Sprites.getScaledHeight(backgroundSprite)  * 0.21f);

        btn = createUpgradeButton(0, 2);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.80f);
        btn = createUpgradeButton(1, 2);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.65f);
        btn = createUpgradeButton(2, 2);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.50f);
        btn = createUpgradeButton(3, 2);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.86f,  Sprites.getScaledHeight(backgroundSprite)  * 0.34f);
        btn = createUpgradeButton(4, 2);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.86f,  Sprites.getScaledHeight(backgroundSprite)  * 0.21f);

        createUpgradeClicks();
        registerClickHandler();
    }


    private void createUpgradeClicks() {
        spriteDrawableObjects[0][0].setOnClicked(obj -> {
            gameplay.buyProductionUpgrade(DrugType.Weed);
        });
        spriteDrawableObjects[0][1].setOnClicked(obj -> {
            gameplay.buyDistributionUpgrade(DrugType.Weed);
        });
        spriteDrawableObjects[0][2].setOnClicked(obj -> {
            gameplay.buySellUpgrade(DrugType.Weed);
        });

    }

    private void registerClickHandler() {
        for (int i = 0; i < spriteDrawableObjects.length; i++) {
            for (int j = 0; j < spriteDrawableObjects[0].length; j++) {
                    objectClickBindings.add(objectClickHandler
                            .addTouchUpClickable(spriteDrawableObjects[i][j], 100000, true));
            }
        }
    }


    private ClickableSpriteDrawableObject createUpgradeButton(int x, int y) {
        ClickableSpriteDrawableObject upgradeBtn = gameObjectManager.create(ClickableSpriteDrawableObject.class);
        spriteDrawableObjects[x][y] = upgradeBtn;
        upgradeBtn.setCenterTextSize(4);
        upgradeBtn.setDrawOrder(drawOrder + 1);
        upgradeBtn.setUiObject(true);
        Sprite sprite = new Sprite(Assets.getAsset(AssetDescriptors.SQUARE_40x40));
        sprite.setScale(3);
        upgradeBtn.setSprite(sprite);
        return upgradeBtn;
    }

    @Override
    public void update(float delta) {
        gameObjectManager.update(delta);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
            //0
            //1
        spriteDrawableObjects[0][0].setCenterText(playerData.getUpgrades().getProductionUpgrade(DrugType.Weed).getNr() + "");
        spriteDrawableObjects[0][1].setCenterText(playerData.getUpgrades().getDistributionUpgrade(DrugType.Weed).getNr() + "");
        spriteDrawableObjects[0][2].setCenterText(playerData.getUpgrades().getSellUpgrade(DrugType.Weed).getNr() + "");
    }

    @Override
    protected void onObjectDestroyed() {
        super.onObjectDestroyed();
        for (ObjectClickBinding binding: objectClickBindings) {
            binding.unsubscribe();
        }
        gameObjectManager.killAll();
    }
}
