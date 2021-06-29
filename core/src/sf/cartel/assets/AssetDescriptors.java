package sf.cartel.assets;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import sf.cartel.core.Annotations.AssetDescriptions;


@AssetDescriptions
public final class AssetDescriptors {
    private static final String UI = "ui";
    private static final String ICONS = "icons";
    private static final String ENVIRONMENT = "environment";

    public static final AssetDescriptor<Texture> BUTTON_START = new AssetDescriptor<>(UI + "/ButtonStartNEW.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_OPTIONS = new AssetDescriptor<>(UI + "/ButtonOptionsNEW.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_EXIT = new AssetDescriptor<>(UI + "/ButtonExitNEW.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_BACK = new AssetDescriptor<>(UI + "/ButtonBack.png", Texture.class);

    public static final AssetDescriptor<Texture> SQUARE_40x40 = new AssetDescriptor<>(ENVIRONMENT + "/Square40x40.png", Texture.class);
    public static final AssetDescriptor<Texture> MAP = new AssetDescriptor<>(ENVIRONMENT + "/map.png", Texture.class);
    public static final AssetDescriptor<Texture> MAP_PART1 = new AssetDescriptor<>(ENVIRONMENT + "/map_part1.png", Texture.class);
    public static final AssetDescriptor<Texture> MAP_PART2 = new AssetDescriptor<>(ENVIRONMENT + "/map_part2.png", Texture.class);
    public static final AssetDescriptor<Texture> MAP_PART3 = new AssetDescriptor<>(ENVIRONMENT + "/map_part3.png", Texture.class);
    public static final AssetDescriptor<Texture> MAP_PART4 = new AssetDescriptor<>(ENVIRONMENT + "/map_part4.png", Texture.class);
    public static final AssetDescriptor<Texture> MAP_PART5 = new AssetDescriptor<>(ENVIRONMENT + "/map_part5.png", Texture.class);
    public static final AssetDescriptor<Texture> TOP_BAR = new AssetDescriptor<>(UI + "/TopBar.png", Texture.class);
    public static final AssetDescriptor<Texture> UI_UPGRADE_BACKGROUND = new AssetDescriptor<>(UI + "/InGameMenu.png", Texture.class);



//    public static final AssetDescriptor<Texture> MAP_PART6 = new AssetDescriptor<>(ENVIRONMENT + "/map_part6.png", Texture.class);
//    public static final AssetDescriptor<Texture> MAP_PART7 = new AssetDescriptor<>(ENVIRONMENT + "/map_part7.png", Texture.class);
//    public static final AssetDescriptor<Texture> MAP_PART8 = new AssetDescriptor<>(ENVIRONMENT + "/map_part8.png", Texture.class);
//    public static final AssetDescriptor<Texture> MAP_PART9 = new AssetDescriptor<>(ENVIRONMENT + "/map_part9.png", Texture.class);

}
