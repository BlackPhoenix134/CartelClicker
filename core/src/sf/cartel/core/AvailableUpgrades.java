package sf.cartel.core;

public class AvailableUpgrades {
    public static int getWeedPrice(int upgradeNr) {
        return (int)(0.5f * upgradeNr + 1);
    }

    public static int getPillsPrice(int upgradeNr) {
        return (int)(0.6f * upgradeNr + 2);
    }

    public static int getCokePrice(int upgradeNr) {
        return (int)(0.7f * upgradeNr + 4);
    }

    public static int getOxyPrice(int upgradeNr) {
        return (int)(0.8f * upgradeNr + 8);
    }

    public static int getHeroinPrice(int upgradeNr) {
        return (int)(0.9f * upgradeNr + 12);
    }

    public static int getWeedUpgradePrice(int upgradeNr) {
        return (int)(0.2f * upgradeNr + 5);
    }

    public static int getPillsUpgradePrice(int upgradeNr) {
        return (int)(0.3f * upgradeNr + 10);
    }

    public static int getCokeUpgradePrice(int upgradeNr) {
        return (int)(0.4f * upgradeNr + 20);
    }

    public static int getOxyUpgradePrice(int upgradeNr) {
        return (int)(0.5f * upgradeNr + 30);
    }

    public static int getHeroinUpgradePrice(int upgradeNr) {
        return (int)(0.2f * upgradeNr + 40);
    }
}
