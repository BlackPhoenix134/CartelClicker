package sf.cartel.gameObjects;

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
    private ClickableSpriteDrawableObject button2;
    private ClickableSpriteDrawableObject button3;
    private DrugType drugType;

    UpgradeButtonsUiObject(String uuid) {
        super(uuid);
    }

    public void init(PlayerData playerData, ObjectClickHandler objectClickHandler, Gameplay gameplay,
                     Sprite backgroundSprite, int drawOrder, DrugType drugType) {
        this.playerData = playerData;
        this.objectClickHandler = objectClickHandler;
        this.drawOrder = drawOrder;
        this.gameplay = gameplay;

        this.drugType = drugType;

        this.productionUpgrade = playerData.getUpgrades().getProductionUpgrade(drugType);
                this.distributionUpgrade     = playerData.getUpgrades().getDistributionUpgrade(drugType);
                this.sellUpgrade   =      playerData.getUpgrades().getSellUpgrade(drugType);

         button1 = createUpgradeButton();
        Sprite sprite = button1.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.5f);

        button2 = createUpgradeButton();
        sprite = button2.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.56f, Sprites.getScaledHeight(backgroundSprite)  * 0.5f);

        button3 = createUpgradeButton();
        sprite = button3.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.80f, Sprites.getScaledHeight(backgroundSprite)  * 0.5f);

        createUpgradeClicks();
    }


    private void createUpgradeClicks() {
        button1.setOnClicked(obj -> {
            gameplay.buyProductionUpgrade(drugType);
        });
        button2.setOnClicked(obj -> {
            gameplay.buyDistributionUpgrade(drugType);
        });
       button3.setOnClicked(obj -> {
            gameplay.buySellUpgrade(drugType);
        });

    }


    private ClickableSpriteDrawableObject createUpgradeButton() {
        ClickableSpriteDrawableObject upgradeBtn = gameObjectManager.create(ClickableSpriteDrawableObject.class);
        objectClickBindings.add(objectClickHandler
                .addTouchUpClickable(upgradeBtn, Globals.CLICK_ORDER_UI_DIALOG + 100, true));
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
        button1.setCenterText(productionUpgrade.getNr() + "");
        button2.setCenterText(distributionUpgrade.getNr() + "");
        button3.setCenterText(sellUpgrade.getNr() + "");

        pipeline.addUi("Cost " + productionUpgrade.getNextUpgradePrice(), new Vector2(button1.getPosition().x , button1.getPosition().y + 200), 4, drawOrder + 2);
        pipeline.addUi("Cost " + distributionUpgrade.getNextUpgradePrice(), new Vector2(button2.getPosition().x , button1.getPosition().y + 200), 4,  drawOrder + 2);
        pipeline.addUi("Cost " + sellUpgrade.getNextUpgradePrice(), new Vector2(button3.getPosition().x , button1.getPosition().y + 200), 4,  drawOrder + 2);
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
