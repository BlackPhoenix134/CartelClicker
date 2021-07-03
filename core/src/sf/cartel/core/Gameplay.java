package sf.cartel.core;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.math.BigInteger;

import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Physics.Polygon;
import sf.cartel.gameObjects.ClickerObject;
import sf.cartel.gameObjects.GameObjectManager;
import sf.cartel.gameObjects.SpriteRenderObject;

public class Gameplay {
    private GameObjectManager gameObjectManager;
    private ObjectClickHandler objectClickHandler;
    private PlayerData playerData;

    private int DRAW_ORDER_WORLD = Globals.DRAW_ORDER_WORLD;


    public Gameplay(GameObjectManager gameObjectManager, ObjectClickHandler objectClickHandler, PlayerData playerData) {
        this.gameObjectManager = gameObjectManager;
        this.objectClickHandler = objectClickHandler;
        this.playerData = playerData;
    }

    public void initialize() {
        SpriteRenderObject mapObj = gameObjectManager.create(SpriteRenderObject.class);
        mapObj.setSprite(new Sprite(Assets.getAsset(AssetDescriptors.MAP)));
        mapObj.setDrawLayer(DRAW_ORDER_WORLD);


        ClickerObject obj = createClickerObject(createJamaycaPolygon(), AssetDescriptors.MAP_PART1, (clickerObj) -> { addPlayerWeed(); });
        obj.setUnlocked(playerData.getUnlocks().isMap1Unlocked());

        obj = createClickerObject(createQuakamolePolygon(), AssetDescriptors.MAP_PART2, (clickerObj) -> { addPlayerPills(); });
        obj.setUnlocked(playerData.getUnlocks().isMap2Unlocked());

        obj = createClickerObject(createBelizePolygon(), AssetDescriptors.MAP_PART3, (clickerObj) -> { });
        obj.setUnlocked(playerData.getUnlocks().isMap3Unlocked());

        obj = createClickerObject(createElSalvadorPolygon(), AssetDescriptors.MAP_PART4, (clickerObj) -> { });
        obj.setUnlocked(playerData.getUnlocks().isMap4Unlocked());

        obj = createClickerObject(createHondurasPolygon(), AssetDescriptors.MAP_PART5, (clickerObj) -> { });
        obj.setUnlocked(playerData.getUnlocks().isMap5Unlocked());

       // createClickerObject(-266.06906f,119.20669f);
      //  createClickerObject(-104.98304f,97.85793f);
      //  createClickerObject(-211.07986f,42.86872f);
      //  createClickerObject(-11.824852f,7.287426f);
      //  createClickerObject(31.519592f,-150.56396f);
      //  createClickerObject(172.55075f,-239.84058f);
      //  createClickerObject(354.33862f,253.76854f);
    }

    public void addPlayerWeed() {
        int valueToAdd = (int)(1);
        playerData.weed = playerData.weed.add(new BigInteger(String.valueOf(valueToAdd)));
    }

    public void addPlayerPills() {
        int valueToAdd = (int)(1);
        playerData.pills = playerData.pills.add(new BigInteger(String.valueOf(valueToAdd)));
    }

    public void sellAllDrugs() {
        int weedPrice = AvailableUpgrades.getWeedPrice(playerData.getUpgrades().getWeedNr());
        int pillsPrice = AvailableUpgrades.getWeedPrice(playerData.getUpgrades().getPillsNr());
        int cokePrice = AvailableUpgrades.getWeedPrice(playerData.getUpgrades().getCokeNr());
        int oxyPrice = AvailableUpgrades.getWeedPrice(playerData.getUpgrades().getOxyNr());
        int heroinPrice = AvailableUpgrades.getWeedPrice(playerData.getUpgrades().getHeroinNr());

        playerData.money = playerData.money
        .add(playerData.weed.multiply(new BigInteger(String.valueOf(weedPrice))))
        .add(playerData.pills.multiply(new BigInteger(String.valueOf(pillsPrice))))
                .add(playerData.coke.multiply(new BigInteger(String.valueOf(cokePrice))))
                        .add(playerData.oxy.multiply(new BigInteger(String.valueOf(oxyPrice))))
                                .add(playerData.heroin.multiply(new BigInteger(String.valueOf(heroinPrice))));

        playerData.weed = BigInteger.ZERO;
        playerData.pills = BigInteger.ZERO;
        playerData.coke = BigInteger.ZERO;
        playerData.oxy = BigInteger.ZERO;
        playerData.heroin = BigInteger.ZERO;
    }

    private Polygon createJamaycaPolygon() {
        return new Polygon(    new Vector2(388.87363f, 235.94214f),
                new Vector2(355.67294f, 225.1507f),
                new Vector2(297.9081f, 257.81918f),
                new Vector2(304.29037f, 268.63657f),
                new Vector2(339.87955f, 269.93466f),
                new Vector2(374.17065f, 262.90338f),
                new Vector2(395.69724f, 251.76146f),
                new Vector2(396.34628f, 240.40321f),
                new Vector2(387.47604f, 233.69643f),
                new Vector2(371.24997f, 237.48251f)
        );

    }

    private Polygon createQuakamolePolygon() {
        return new Polygon(    new Vector2(-220.76593f, 179.62904f),
                new Vector2(-259.7599f, 50.36963f),
                new Vector2(-358.58014f, 92.07549f),
                new Vector2(-350.77313f, 122.68718f),
                new Vector2(-357.9638f, 134.19225f),
                new Vector2(-333.31012f, 173.43274f),
                new Vector2(-270.03226f, 170.96736f),
                new Vector2(-316.4634f, 228.9036f),
                new Vector2(-292.83694f, 230.54715f),
                new Vector2(-289.34433f, 258.89893f),
                new Vector2(-202.44f, 249.85922f),
                new Vector2(-209.01433f, 156.58603f),
                new Vector2(-190.3186f, 151.65529f),
                new Vector2(-159.09058f, 143.84828f),
                new Vector2(-210.86336f, 111.18211f),
                new Vector2(-209.63066f, 87.761086f),
                new Vector2(-260.99255f, 47.90426f)
        );

    }

    private Polygon createBelizePolygon() {
        return new Polygon(
                new Vector2(-191.21075f, 151.66747f),
                new Vector2(-210.03201f, 151.66747f),
                new Vector2(-200.95352f, 249.31664f),
                new Vector2(-200.95352f, 256.84515f),
                new Vector2(-191.21075f, 258.39514f),
                new Vector2(-187.88934f, 251.30948f),
                new Vector2(-168.84666f, 279.8735f),
                new Vector2(-150.02539f, 269.46646f),
                new Vector2(-160.65387f, 212.33838f),
                new Vector2(-159.76817f, 205.917f),
                new Vector2(-163.97527f, 184.43863f),
                new Vector2(-173.05376f, 167.6102f),
                new Vector2(-185.67508f, 164.06737f),
                new Vector2(-191.21075f, 158.5317f),
                new Vector2(-191.21075f, 150.1175f),
                new Vector2(-208.70345f, 152.33176f)
        );

    }

    private Polygon createElSalvadorPolygon() {
        return new Polygon(
                new Vector2(-148.9891f, 11.70813f),
                new Vector2(-242.43115f, 34.7365f),
                new Vector2(-257.26672f, 48.0221f),
                new Vector2(-243.98114f, 63.300537f),
                new Vector2(-239.10977f, 60.42199f),
                new Vector2(-236.2312f, 68.8362f),
                new Vector2(-227.81699f, 68.17193f),
                new Vector2(-231.35983f, 77.914696f),
                new Vector2(-220.95276f, 80.79324f),
                new Vector2(-210.32428f, 76.58614f),
                new Vector2(-206.78146f, 80.12896f),
                new Vector2(-178.21744f, 51.3435f),
                new Vector2(-169.80322f, 51.3435f),
                new Vector2(-167.14609f, 57.76487f),
                new Vector2(-157.40332f, 54.88633f),
                new Vector2(-155.18906f, 50.67922f),
                new Vector2(-146.11057f, 51.3435f),
                new Vector2(-142.56775f, 47.13639f),
                new Vector2(-145.44629f, 32.522232f),
                new Vector2(-143.23203f, 26.322289f),
                new Vector2(-150.31766f, 24.993721f),
                new Vector2(-148.9891f, 14.365242f)

        );
    }

    private Polygon createHondurasPolygon() {
        return new Polygon(
                new Vector2(-160.72472f, 140.79987f),
                new Vector2(-198.58868f, 112.900116f),
                new Vector2(-206.11719f, 112.900116f),
                new Vector2(-208.33145f, 92.52886f),
                new Vector2(-220.06705f, 84.11465f),
                new Vector2(-215.85995f, 80.12896f),
                new Vector2(-210.98859f, 80.12896f),
                new Vector2(-206.78146f, 81.45753f),
                new Vector2(-178.21744f, 52.229202f),
                new Vector2(-166.48181f, 56.214886f),
                new Vector2(-157.40332f, 56.214886f),
                new Vector2(-153.19623f, 52.893482f),
                new Vector2(-140.5749f, 50.67922f),
                new Vector2(-144.11774f, 34.072216f),
                new Vector2(-141.23918f, 26.986565f),
                new Vector2(-126.625015f, 26.986565f),
                new Vector2(-125.07503f, 6.8367386f),
                new Vector2(-106.25376f, 5.286751f),
                new Vector2(-102.932365f, 17.908073f),
                new Vector2(-92.30389f, 17.24379f),
                new Vector2(-97.17528f, 38.279327f),
                new Vector2(-94.51815f, 43.814995f),
                new Vector2(-75.6969f, 41.600723f),
                new Vector2(-61.525597f, 56.214886f),
                new Vector2(-46.247147f, 43.814995f),
                new Vector2(-17.018818f, 77.25041f),
                new Vector2(-5.7260666f, 90.53601f),
                new Vector2(-0.85469055f, 92.52886f),
                new Vector2(18.63086f, 82.1218f),
                new Vector2(77.30891f, 96.73596f),
                new Vector2(70.444695f, 110.02157f),
                new Vector2(43.209213f, 110.02157f),
                new Vector2(31.252182f, 123.97145f),
                new Vector2(45.866333f, 122.64288f),
                new Vector2(18.63086f, 141.46414f),
                new Vector2(-41.37577f, 151.20692f),
                new Vector2(-134.15353f, 148.99265f),
                new Vector2(-159.39618f, 140.79987f)
        );
    }

    private Polygon createNicaraguaPolygon() {
        return new Polygon(
                new Vector2(84.645805f, -66.97414f),
                new Vector2(80.28391f, 93.61635f),
                new Vector2(64.86887f, 97.32738f),
                new Vector2(61.157837f, 92.75993f),
                new Vector2(22.620224f, 81.05594f),
                new Vector2(5.492401f, 85.62335f),
                new Vector2(0.92498016f, 90.7617f),
                new Vector2(-11.6354065f, 79.343155f),
                new Vector2(-17.915611f, 77.34491f),
                new Vector2(-17.915611f, 65.6409f),
                new Vector2(-48.460228f, 43.089268f),
                new Vector2(-63.018898f, 54.793262f),
                new Vector2(-73.01011f, 42.23286f),
                new Vector2(-96.418144f, 42.23286f),
                new Vector2(-94.4199f, 14.542892f),
                new Vector2(-101.84197f, 17.11206f),
                new Vector2(-102.69836f, 8.2626915f),
                new Vector2(-125.24998f, 5.4080467f),
                new Vector2(-128.67554f, -0.87215424f),
                new Vector2(-139.52316f, 6.264431f),
                new Vector2(-144.0906f, 0.8406334f),
                new Vector2(-55.0259f, -95.36066f),
                new Vector2(-44.178276f, -87.93861f),
                new Vector2(-12.491814f, -103.35364f),
                new Vector2(3.4941711f, -94.50427f),
                new Vector2(38.60621f, -115.91405f),
                new Vector2(52.308464f, -110.490234f),
                new Vector2(39.748055f, -89.936844f),
                new Vector2(52.308464f, -74.521805f),
                new Vector2(78.28567f, 56.791523f),
                new Vector2(81.99668f, 90.7617f)
        );
    }

    private Polygon createCostaRicaPolygon() {
        return new Polygon(
                new Vector2(50.561462f, -107.87538f),
                new Vector2(51.417862f, -112.442795f),
                new Vector2(37.71559f, -117.866615f),
                new Vector2(22.586014f, -110.73001f),
                new Vector2(20.587784f, -104.449814f),
                new Vector2(10.0256195f, -102.73702f),
                new Vector2(4.601822f, -98.16961f),
                new Vector2(-9.956863f, -102.73702f),
                new Vector2(-42.21425f, -89.03476f),
                new Vector2(-51.34909f, -95.314964f),
                new Vector2(-50.492706f, -101.59517f),
                new Vector2(-61.340317f, -103.59343f),
                new Vector2(-59.34207f, -106.1626f),
                new Vector2(-47.638046f, -111.586395f),
                new Vector2(-58.485687f, -137.56361f),
                new Vector2(-33.36487f, -159.25885f),
                new Vector2(-27.084671f, -172.67564f),
                new Vector2(-1.6784058f, -170.96286f),
                new Vector2(48.56321f, -206.0749f),
                new Vector2(43.139412f, -221.20448f),
                new Vector2(60.267235f, -232.0521f),
                new Vector2(74.825874f, -236.61954f),
                new Vector2(86.5299f, -232.90851f),
                new Vector2(87.386284f, -222.34634f),
                new Vector2(83.675255f, -217.7789f),
                new Vector2(93.66649f, -206.9313f),
                new Vector2(82.81887f, -198.93831f),
                new Vector2(87.386284f, -176.38667f),
                new Vector2(105.3705f, -173.53203f),
                new Vector2(53.13063f, -109.87363f)
                );
    }

    private Polygon createPanamaPolygon() {
        return new Polygon(
                new Vector2(105.47322f, -174.36559f),
                new Vector2(101.762184f, -179.7894f),
                new Vector2(89.20178f, -174.36559f),
                new Vector2(84.919815f, -179.7894f),
                new Vector2(82.92157f, -197.77362f),
                new Vector2(93.769196f, -204.05382f),
                new Vector2(85.776215f, -215.75783f),
                new Vector2(87.48898f, -229.46008f),
                new Vector2(75.78499f, -235.7403f),
                new Vector2(80.3524f, -242.87689f),
                new Vector2(103.76044f, -242.87689f),
                new Vector2(143.43988f, -250.01347f),
                new Vector2(146.86545f, -265.42853f),
                new Vector2(163.99327f, -275.13428f),
                new Vector2(173.98451f, -260.86108f),
                new Vector2(180.26471f, -292.26212f),
                new Vector2(206.24191f, -293.9749f),
                new Vector2(227.93715f, -286.83832f),
                new Vector2(207.0983f, -249.15709f),
                new Vector2(244.20857f, -226.60545f),
                new Vector2(255.9126f, -208.62123f),
                new Vector2(272.18405f, -215.75783f),
                new Vector2(296.44843f, -227.46185f),
                new Vector2(305.29782f, -239.16586f),
                new Vector2(319.00012f, -236.59668f),
                new Vector2(303.58505f, -256.29367f),
                new Vector2(309.00885f, -284.26913f),
                new Vector2(328.70587f, -295.97314f),
                new Vector2(338.69708f, -272.56512f),
                new Vector2(342.4081f, -269.71048f),
                new Vector2(345.83368f, -275.9907f),
                new Vector2(359.2505f, -272.56512f),
                new Vector2(365.81613f, -260.86108f),
                new Vector2(363.8179f, -247.4443f),
                new Vector2(354.96854f, -232.88565f),
                new Vector2(359.2505f, -224.03629f),
                new Vector2(323.28204f, -190.63702f),
                new Vector2(275.60962f, -179.7894f),
                new Vector2(245.06499f, -175.50745f),
                new Vector2(189.11409f, -206.90846f),
                new Vector2(159.42586f, -214.04504f),
                new Vector2(141.44165f, -206.05206f),
                new Vector2(138.01607f, -196.91722f),
                new Vector2(133.44865f, -206.05206f),
                new Vector2(123.45743f, -205.19568f),
                new Vector2(118.890015f, -199.77187f),
                new Vector2(116.32085f, -192.34981f),
                new Vector2(112.60981f, -189.78064f),
                new Vector2(120.88826f, -188.92424f),
                new Vector2(123.45743f, -184.35681f),
                new Vector2(118.890015f, -180.93126f),
                new Vector2(110.897026f, -180.93126f),
                new Vector2(108.32785f, -176.36383f),
                new Vector2(104.61682f, -176.36383f)
        );
    }

    private ClickerObject createClickerObject(Polygon polygon, AssetDescriptor<Texture> assetDescriptor, Consumer<ClickerObject> onClicked) {
        ClickerObject obj = gameObjectManager.create(ClickerObject.class);
        obj.setOnClicked(onClicked);
        obj.setArea2D(polygon);
        obj.setSprite(new Sprite(Assets.getAsset(assetDescriptor)));
        objectClickHandler.addTouchDownClickable(obj, 10, false);
        return obj;
    }

    public boolean canAffort(int price) {
        return playerData.money.longValue() > price;
    }

    public void buyWeedSellUpgrade() {
        int price = AvailableUpgrades.getWeedUpgradePrice(playerData.getUpgrades().getWeedNr() + 1);
        if(canAffort(price)) {
            playerData.money = playerData.money.subtract(new BigInteger(String.valueOf(price)));
            playerData.getUpgrades().setWeedNr(playerData.getUpgrades().getWeedNr()+1);
        }
    }

    public void buyPillsSellUpgrade() {
        int price = AvailableUpgrades.getPillsUpgradePrice(playerData.getUpgrades().getPillsNr() + 1);
        if(canAffort(price)) {
            playerData.money = playerData.money.subtract(new BigInteger(String.valueOf(price)));
            playerData.getUpgrades().setPillsNr(playerData.getUpgrades().getPillsNr()+1);
        }
    }

    public void buyCokeSellUpgrade() {

    }

    public void buyOxySellUpgrade() {

    }

    public void buyHeroinSellUpgrade() {

    }
}
