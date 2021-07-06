package sf.cartel.core;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.math.BigDecimal;
import java.math.BigInteger;

import sf.cartel.assets.ShaderManager;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Physics.Polygon;
import sf.cartel.gameObjects.ClickerObject;
import sf.cartel.gameObjects.GameObjectManager;
import sf.cartel.gameObjects.PathFollowerObject;
import sf.cartel.gameObjects.PlaneSpawnerObject;
import sf.cartel.gameObjects.ShipSpawnerObject;
import sf.cartel.gameObjects.SpriteRenderObject;
import sf.cartel.gameObjects.WaterObject;

public class Gameplay {
    private GameObjectManager gameObjectManager;
    private ObjectClickHandler objectClickHandler;
    private PlayerData playerData;
    private ShaderManager shaderManager;

    private static final int DRAW_ORDER_WORLD = Globals.DRAW_ORDER_WORLD;


    public Gameplay(GameObjectManager gameObjectManager, ObjectClickHandler objectClickHandler, PlayerData playerData, ShaderManager shaderManager) {
        this.gameObjectManager = gameObjectManager;
        this.objectClickHandler = objectClickHandler;
        this.playerData = playerData;
        this.shaderManager = shaderManager;
    }

    public void initialize() {
        Texture noiseTexture = new Texture("shader/waterNoise.png");
        noiseTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        WaterObject waterObj = gameObjectManager.create(WaterObject.class);
        waterObj.init(Assets.getAsset(AssetDescriptors.WATER));
        waterObj.setDrawLayer(DRAW_ORDER_WORLD - 1);

        SpriteRenderObject mapObj = gameObjectManager.create(SpriteRenderObject.class);
        mapObj.setSprite(new Sprite(Assets.getAsset(AssetDescriptors.MAP)));
        mapObj.setDrawLayer(DRAW_ORDER_WORLD);

        PathFollowerObject pathFollowerObject = gameObjectManager.create(PathFollowerObject.class);
        Texture shipTexture = Assets.getAsset(AssetDescriptors.SHIP1_SHEET);
        pathFollowerObject.init(shipTexture, new AnimationController(shipTexture, 3, 2, 0.2f), GlobalsPaths.getShipPath1());
        pathFollowerObject.getSprite().setScale(0.015f);
        pathFollowerObject.setTravelSpeed(40);

        PlaneSpawnerObject planeSpawnerObject = gameObjectManager.create(PlaneSpawnerObject.class);
        ShipSpawnerObject shipSpawnerObject = gameObjectManager.create(ShipSpawnerObject.class);

        ClickerObject obj = createClickerObject(GlobalsMapPolygon.createJamaycaPolygon(), AssetDescriptors.MAP_PART1, (clickerObj) -> addDrug(DrugType.Weed));
        obj.setUnlocked(playerData.getUnlocks().isMap1Unlocked());

        obj = createClickerObject(GlobalsMapPolygon.createQuakamolePolygon(), AssetDescriptors.MAP_PART2, (clickerObj) -> addDrug(DrugType.Pills));
        obj.setUnlocked(playerData.getUnlocks().isMap2Unlocked());

        obj = createClickerObject(GlobalsMapPolygon.createBelizePolygon(), AssetDescriptors.MAP_PART3, (clickerObj) -> { });
        obj.setUnlocked(playerData.getUnlocks().isMap3Unlocked());

        obj = createClickerObject(GlobalsMapPolygon.createElSalvadorPolygon(), AssetDescriptors.MAP_PART4, (clickerObj) -> { });
        obj.setUnlocked(playerData.getUnlocks().isMap4Unlocked());

        obj = createClickerObject(GlobalsMapPolygon.createHondurasPolygon(), AssetDescriptors.MAP_PART5, (clickerObj) -> addDrug(DrugType.Heroin));
        obj.setUnlocked(playerData.getUnlocks().isMap5Unlocked());

       // createClickerObject(-266.06906f,119.20669f);
      //  createClickerObject(-104.98304f,97.85793f);
      //  createClickerObject(-211.07986f,42.86872f);
      //  createClickerObject(-11.824852f,7.287426f);
      //  createClickerObject(31.519592f,-150.56396f);
      //  createClickerObject(172.55075f,-239.84058f);
      //  createClickerObject(354.33862f,253.76854f);
    }

    public void addDistributionDrugs() {
        for(DrugType drugType : playerData.getDrugs().keySet()) {
            Upgrade upgrade = playerData.getUpgrades().getDistributionUpgrade(drugType);
            playerData.addDrug(drugType, upgrade.getProductionAmount());
        }
    }

    public void addDrug(DrugType drugType) {
        playerData.addDrug(drugType, (playerData.getUpgrades().getProductionUpgrade(drugType).getProductionAmount()));
    }

    public void sellAllDrugs() {
        playerData.getDrugs().forEach((drugType, amount) -> {

            // Todo clean up and make all values in top bar decimals
            BigDecimal saleMultiplier = playerData.getUpgrades().getSellUpgrade(drugType).getSaleMultiplier();
            BigDecimal basePrice = new BigDecimal(playerData.getUpgrades().getSellUpgrade(drugType).getBasePrice());
            BigDecimal totalValue = saleMultiplier.multiply(new BigDecimal(amount).multiply(basePrice));

            playerData.addMoney(totalValue.toBigInteger());
            playerData.setDrug(drugType, 0);
        });
    }

    private ClickerObject createClickerObject(Polygon polygon, AssetDescriptor<Texture> assetDescriptor, Consumer<ClickerObject> onClicked) {
        ClickerObject obj = gameObjectManager.create(ClickerObject.class);
        obj.setOnClicked(onClicked);
        obj.setArea2D(polygon);
        obj.setSprite(new Sprite(Assets.getAsset(assetDescriptor)));
        objectClickHandler.addTouchDownClickable(obj, 10, false);
        return obj;
    }

    public boolean canAfford(int price) {
        return playerData.money.longValue() > price;
    }

    public boolean buySellUpgrade(DrugType drugType) {
        Upgrade upgrade = playerData.getUpgrades().getSellUpgrade(drugType);
        return buyUpgrade(upgrade);
    }

    public boolean buyProductionUpgrade(DrugType drugType) {
        Upgrade upgrade = playerData.getUpgrades().getProductionUpgrade(drugType);
        return buyUpgrade(upgrade);
    }

    public boolean buyDistributionUpgrade(DrugType drugType) {
        Upgrade upgrade = playerData.getUpgrades().getDistributionUpgrade(drugType);
        return buyUpgrade(upgrade);
    }



    private boolean buyUpgrade(Upgrade upgrade) {
        int price = upgrade.getNextUpgradePrice();
        if(canAfford(price)) {
            playerData.removeMoney(new BigInteger(price+""));
            upgrade.incrementNr();
            return true;
        }
        return false;
    }
}
