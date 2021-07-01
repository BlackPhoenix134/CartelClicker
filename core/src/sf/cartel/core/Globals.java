package sf.cartel.core;

import java.util.Random;

public abstract class Globals {
    private static PlayerData playerData;

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
