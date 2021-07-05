package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.DrugType;
import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Gameplay;
import sf.cartel.core.Globals;
import sf.cartel.core.Math.Formatter;
import sf.cartel.core.PlayerData;
import sf.cartel.core.Upgrade;
import sf.cartel.core.clickHandler.ObjectClickBinding;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.rendering.RenderPipeline;

public class UpgradeButtonsUiObject extends GameObject {
    private PlayerData playerData;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private int drawOrder;
    private List<ObjectClickBinding> objectClickBindings = new ArrayList<>();
    private ObjectClickHandler objectClickHandler;
    private Gameplay gameplay;
    private Upgrade productionUpgrade;
    private Upgrade distributionUpgrade;
    private Upgrade sellUpgrade;
    private ClickableSpriteDrawableObject button1;
    private ClickableSpriteDrawableObject button1x10;
    private ClickableSpriteDrawableObject button1xMAX;
    private ClickableSpriteDrawableObject button2;
    private ClickableSpriteDrawableObject button2x10;
    private ClickableSpriteDrawableObject button2xMAX;
    private ClickableSpriteDrawableObject button3;
    private ClickableSpriteDrawableObject button3x10;
    private ClickableSpriteDrawableObject button3xMAX;
    private DrugType drugType;
    private Sprite backgroundSprite;

    UpgradeButtonsUiObject(String uuid) {
        super(uuid);
    }

    public void init(PlayerData playerData, ObjectClickHandler objectClickHandler, Gameplay gameplay,
                     Sprite backgroundSprite, int drawOrder, DrugType drugType) {
        this.playerData = playerData;
        this.objectClickHandler = objectClickHandler;
        this.drawOrder = drawOrder;
        this.gameplay = gameplay;
        this.backgroundSprite = backgroundSprite;
        this.drugType = drugType;

        this.productionUpgrade = playerData.getUpgrades().getProductionUpgrade(drugType);
        this.distributionUpgrade     = playerData.getUpgrades().getDistributionUpgrade(drugType);
        this.sellUpgrade   =      playerData.getUpgrades().getSellUpgrade(drugType);

        Texture textureX1 = Assets.getAsset(AssetDescriptors.BUTTON_BUY_1x);
        Texture textureX10 = Assets.getAsset(AssetDescriptors.BUTTON_BUY_10x);
        Texture textureMAX = Assets.getAsset(AssetDescriptors.BUTTON_BUY_MAX);

        button1 = createUpgradeButton(textureX1);
        Sprite sprite = button1.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.5f);

        button1x10 = createUpgradeButton(textureX10);
        sprite = button1x10.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.3f);

        button1xMAX = createUpgradeButton(textureMAX);
        sprite = button1xMAX.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.1f);

        button2 = createUpgradeButton(textureX1);
        sprite = button2.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.60f, Sprites.getScaledHeight(backgroundSprite)  * 0.5f);

        button2x10 = createUpgradeButton(textureX10);
        sprite = button2x10.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.60f, Sprites.getScaledHeight(backgroundSprite)  * 0.3f);

        button2xMAX = createUpgradeButton(textureMAX);
        sprite = button2xMAX.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.60f, Sprites.getScaledHeight(backgroundSprite)  * 0.1f);

        button3 = createUpgradeButton(textureX1);
        sprite = button3.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.85f, Sprites.getScaledHeight(backgroundSprite)  * 0.5f);

        button3x10 = createUpgradeButton(textureX10);
        sprite = button3x10.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.85f, Sprites.getScaledHeight(backgroundSprite)  * 0.3f);

        button3xMAX = createUpgradeButton(textureMAX);
        sprite = button3xMAX.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.85f, Sprites.getScaledHeight(backgroundSprite)  * 0.1f);

        createUpgradeClicks();
    }


    private void createUpgradeClicks() {
        button1.setOnClicked(obj -> {
            if (buyProductionUpgrades(1))
                Globals.SOUND_BUTTON_PRESS.play();
        });
        button1x10.setOnClicked(obj -> {
            if (buyProductionUpgrades(10))
                Globals.SOUND_BUTTON_PRESS.play();
        });
        button1xMAX.setOnClicked(obj -> {
            if (buyProductionUpgrades(Integer.MAX_VALUE))
                Globals.SOUND_BUTTON_PRESS.play();
        });
        button2.setOnClicked(obj -> {
            if (buyDistributionUpgrades(1))
                Globals.SOUND_BUTTON_PRESS.play();
        });
        button2x10.setOnClicked(obj -> {
            if (buyDistributionUpgrades(10))
                Globals.SOUND_BUTTON_PRESS.play();
        });
        button2xMAX.setOnClicked(obj -> {
            if (buyDistributionUpgrades(Integer.MAX_VALUE))
                Globals.SOUND_BUTTON_PRESS.play();
        });
        button3.setOnClicked(obj -> {
            if (buySellUpgrades(1))
                Globals.SOUND_BUTTON_PRESS.play();
        });
        button3x10.setOnClicked(obj -> {
            if (buySellUpgrades(10))
                Globals.SOUND_BUTTON_PRESS.play();
        });
        button3xMAX.setOnClicked(obj -> {
            if (buySellUpgrades(Integer.MAX_VALUE))
                Globals.SOUND_BUTTON_PRESS.play();
        });
    }

    private boolean buyProductionUpgrades(int amount) {
        int count = 0;
        while(count < amount && gameplay.buyProductionUpgrade(drugType))
            count ++;
        return count > 0;
    }

    private boolean buyDistributionUpgrades(int amount) {
        int count = 0;
        while(count < amount && gameplay.buyDistributionUpgrade(drugType))
            count ++;
        return count > 0;
    }

    private boolean buySellUpgrades(int amount) {
        int count = 0;
        while(count < amount && gameplay.buySellUpgrade(drugType))
            count ++;
        return count > 0;
    }


    private ClickableSpriteDrawableObject createUpgradeButton(Texture texture) {
        ClickableSpriteDrawableObject upgradeBtn = gameObjectManager.create(ClickableSpriteDrawableObject.class);
        objectClickBindings.add(objectClickHandler
                .addTouchUpClickable(upgradeBtn, Globals.CLICK_ORDER_UI_DIALOG + 100, true));
        upgradeBtn.setCenterTextSize(4);
        upgradeBtn.setDrawOrder(drawOrder + 1);
        upgradeBtn.setUiObject(true);
        Sprite sprite = new Sprite(texture);
        sprite.setScale(Gdx.graphics.getWidth() / sprite.getWidth() * 0.15f, Gdx.graphics.getHeight() / sprite.getHeight() * 0.1f);
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
        pipeline.addUi("Cost: " + productionUpgrade.getNextUpgradePrice(), new Vector2(button1.getPosition().x , button1.getPosition().y + 200), 4, drawOrder + 2);
        pipeline.addUi("Cost: " + distributionUpgrade.getNextUpgradePrice(), new Vector2(button2.getPosition().x , button1.getPosition().y + 200), 4,  drawOrder + 2);
        pipeline.addUi("Cost: " + sellUpgrade.getNextUpgradePrice(), new Vector2(button3.getPosition().x , button1.getPosition().y + 200), 4,  drawOrder + 2);


        pipeline.addUi("Level: " +  playerData.getUpgrades().getProductionUpgrade(drugType).getNr(), new Vector2(button1.getPosition().x , button1.getPosition().y + 300), 4,  drawOrder + 2);
        pipeline.addUi("Level: " +  playerData.getUpgrades().getDistributionUpgrade(drugType).getNr(), new Vector2(button2.getPosition().x , button2.getPosition().y + 300), 4,  drawOrder + 2);
        pipeline.addUi("Level: " +  playerData.getUpgrades().getSellUpgrade(drugType).getNr(), new Vector2(button3.getPosition().x , button3.getPosition().y + 300), 4,  drawOrder + 2);
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
