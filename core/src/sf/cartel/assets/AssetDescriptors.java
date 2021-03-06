package sf.cartel.assets;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import sf.cartel.core.Annotations.AssetDescriptions;


@AssetDescriptions
public final class AssetDescriptors {
    private static final String UI = "ui";
    private static final String ICONS = "icons";
    private static final String ENVIRONMENT = "environment";
    private static final String SOUNDS = "sounds";


    public static final AssetDescriptor<Texture> BUTTON_START = new AssetDescriptor<>(UI + "/ButtonStart.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_OPTIONS = new AssetDescriptor<>(UI + "/ButtonOptions.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_EXIT = new AssetDescriptor<>(UI + "/ButtonExit.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_BACK = new AssetDescriptor<>(UI + "/ButtonBack.png", Texture.class);

    public static final AssetDescriptor<Texture> SQUARE_40x40 = new AssetDescriptor<>(ENVIRONMENT + "/Square40x40.png", Texture.class);
    public static final AssetDescriptor<Texture> MAP = new AssetDescriptor<>(ENVIRONMENT + "/map.png", Texture.class);
    public static final AssetDescriptor<Texture> WATER = new AssetDescriptor<>(ENVIRONMENT + "/water.png", Texture.class);
    public static final AssetDescriptor<Texture> MAP_PART1 = new AssetDescriptor<>(ENVIRONMENT + "/map_part1.png", Texture.class);
    public static final AssetDescriptor<Texture> MAP_PART2 = new AssetDescriptor<>(ENVIRONMENT + "/map_part2.png", Texture.class);
    public static final AssetDescriptor<Texture> MAP_PART3 = new AssetDescriptor<>(ENVIRONMENT + "/map_part3.png", Texture.class);
    public static final AssetDescriptor<Texture> MAP_PART4 = new AssetDescriptor<>(ENVIRONMENT + "/map_part4.png", Texture.class);
    public static final AssetDescriptor<Texture> MAP_PART5 = new AssetDescriptor<>(ENVIRONMENT + "/map_part5.png", Texture.class);

    public static final AssetDescriptor<Texture> SHIP1_SHEET = new AssetDescriptor<>(ENVIRONMENT + "/ship1Sheet.png", Texture.class);


    public static final AssetDescriptor<Texture> SHIP2_SHEET = new AssetDescriptor<>(ENVIRONMENT + "/ship2Sheet.png", Texture.class);
    public static final AssetDescriptor<Texture> SHIP2_DESTROYED_SHEET = new AssetDescriptor<>(ENVIRONMENT + "/ship2DestroyedSheet.png", Texture.class);
    public static final AssetDescriptor<Texture> PLANE1_SHEET = new AssetDescriptor<>(ENVIRONMENT + "/plane1Sheet.png", Texture.class);
    public static final AssetDescriptor<Texture> PLANE1_DESTROYED_SHEET = new AssetDescriptor<>(ENVIRONMENT + "/plane1DestroyedSheet.png", Texture.class);

    public static final AssetDescriptor<Texture> BUILDING1_BUILD_SHEET = new AssetDescriptor<>(ENVIRONMENT + "/building1BuildSheet.png", Texture.class);
    public static final AssetDescriptor<Texture> BUILDING1_DESTROYED_SHEET = new AssetDescriptor<>(ENVIRONMENT + "/building1DestroyedSheet.png", Texture.class);

    public static final AssetDescriptor<Texture> EXPLOSION1_SHEET = new AssetDescriptor<>(ENVIRONMENT + "/explosion1Sheet.png", Texture.class);

    public static final AssetDescriptor<Texture> TRAIN_4DIR_SHEET = new AssetDescriptor<>(ENVIRONMENT + "/train4BatchSheet.png", Texture.class);
    public static final AssetDescriptor<Texture> STICKMAN = new AssetDescriptor<>(ENVIRONMENT + "/stickman.png", Texture.class);
    public static final AssetDescriptor<Texture> STICKMAN_DON = new AssetDescriptor<>(ENVIRONMENT + "/stickmanDon.png", Texture.class);
    public static final AssetDescriptor<Texture> STICKMAN_JOE = new AssetDescriptor<>(ENVIRONMENT + "/stickmanJoe.png", Texture.class);



    public static final AssetDescriptor<Texture> BLOOD_SPLATTER1_SHEET = new AssetDescriptor<>(ENVIRONMENT + "/bloodSplatter1Sheet.png", Texture.class);
    public static final AssetDescriptor<Texture> BLOOD_SPLATTER2_SHEET = new AssetDescriptor<>(ENVIRONMENT + "/bloodSplatter2Sheet.png", Texture.class);


    public static final AssetDescriptor<Texture> TOP_BAR = new AssetDescriptor<>(UI + "/TopBar.png", Texture.class);
    public static final AssetDescriptor<Texture> UI_INFO_BOX = new AssetDescriptor<>(UI + "/InfoBox.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_MENU = new AssetDescriptor<>(UI + "/ButtonMenu.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_UPGRADE = new AssetDescriptor<>(UI + "/ButtonUpgrade.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_SELL_ALL = new AssetDescriptor<>(UI + "/ButtonSellAll.png", Texture.class);
    public static final AssetDescriptor<Texture> UI_UPGRADE_BACKGROUND = new AssetDescriptor<>(UI + "/InGameMenu.png", Texture.class);
    public static final AssetDescriptor<Texture> UI_UPGRADE_SELECTOR_BACKGROUND = new AssetDescriptor<>(UI + "/SelectorBackground.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_BUY = new AssetDescriptor<>(UI + "/ButtonBuy.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_BUY_1x = new AssetDescriptor<>(UI + "/ButtonBuy1x.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_BUY_10x = new AssetDescriptor<>(UI + "/ButtonBuy10x.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_BUY_MAX = new AssetDescriptor<>(UI + "/ButtonBuyMax.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_UNLOCK = new AssetDescriptor<>(UI + "/ButtonUnlock.png", Texture.class);



    public static final AssetDescriptor<Texture> ICON_WEED = new AssetDescriptor<>(ICONS + "/WeedLeaf.png", Texture.class);
    public static final AssetDescriptor<Texture> ICON_PILLS = new AssetDescriptor<>(ICONS + "/pill.png", Texture.class);
    public static final AssetDescriptor<Texture> ICON_COKE = new AssetDescriptor<>(ICONS + "/Coke.png",Texture.class);
    public static final AssetDescriptor<Texture> ICON_OXY = new AssetDescriptor<>(ICONS + "/pill2.png",Texture.class);
    public static final AssetDescriptor<Texture> ICON_HEROIN = new AssetDescriptor<>(ICONS + "/syringe.png",Texture.class);

    public static final AssetDescriptor<Texture> ICON_WEED_SHEET = new AssetDescriptor<>(ICONS + "/weedSheet.png",Texture.class);
    public static final AssetDescriptor<Texture> ICON_COKE_SHEET = new AssetDescriptor<>(ICONS + "/cokeSheet.png",Texture.class);
    public static final AssetDescriptor<Texture> ICON_HEROIN_SHEET = new AssetDescriptor<>(ICONS + "/heroinSheet.png",Texture.class);
    public static final AssetDescriptor<Texture> ICON_PILLS_SHEET = new AssetDescriptor<>(ICONS + "/pillsSheet.png",Texture.class);
    public static final AssetDescriptor<Texture> ICON_PILLS2_SHEET = new AssetDescriptor<>(ICONS + "/pills2Sheet.png",Texture.class);
    public static final AssetDescriptor<Texture> BLACK = new AssetDescriptor<>(ICONS + "/BlackSheet.png",Texture.class);



    public static final AssetDescriptor<Texture> BUTTON_SELL = new AssetDescriptor<>(UI + "/ButtonSell.png", Texture.class);


    public static final AssetDescriptor<Sound> SOUND_BUTTON = new AssetDescriptor<>(SOUNDS + "/buttonSound.mp3", Sound.class);





}
