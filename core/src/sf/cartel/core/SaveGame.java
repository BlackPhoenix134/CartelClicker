package sf.cartel.core;

public class SaveGame {
    private static String path = "";

    public static PlayerData loadPlayerData() {
        return new PlayerData();
    }
}
