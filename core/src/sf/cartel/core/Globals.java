package sf.cartel.core;

import com.badlogic.gdx.audio.Sound;

import java.util.Random;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;

public abstract class Globals {
    private static PlayerData playerData;

    public static final int CLICK_ORDER_WORLD = 10;
    public static final int CLICK_ORDER_UI_WORLD = 100;
    public static final int CLICK_ORDER_UI_DIALOG = 1000;

    public static final int DRAW_ORDER_WORLD = 10;
    public static final int DRAW_ORDER_UI = 100;
    public static final int DRAW_ORDER_DIALOG = 1000;

    public static final Sound SOUND_BUTTON_PRESS = Assets.getAsset(AssetDescriptors.SOUND_BUTTON);



    public static final Random RANDOM = new Random();

    public static PlayerData getPlayerData() {
        if(playerData == null)
            loadPlayerData();
        return playerData;
    }

    public static float getRandomFloat(float min, float max) {
        return min + RANDOM.nextFloat() * (max - min);
    }

    public static int getRandomInt(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }


    private static void loadPlayerData() {
        playerData = new PlayerData(); //ToDo: load player data
    }
}
