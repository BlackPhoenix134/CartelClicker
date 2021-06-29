package sf.cartel.core;

import java.math.BigInteger;

public class PlayerData {
    public BigInteger weed = BigInteger.ZERO;
    public BigInteger meth = BigInteger.ZERO;
    public BigInteger coke = BigInteger.ZERO;
    public BigInteger emma = BigInteger.ZERO;
    public BigInteger shrooms = BigInteger.ZERO;

    private PlayerUnlocks unlocks = new PlayerUnlocks();
    private PlayerUpgrades upgrades = new PlayerUpgrades();

    public PlayerUnlocks getUnlocks() {
        return unlocks;
    }

    public void setUnlocks(PlayerUnlocks unlocks) {
        this.unlocks = unlocks;
    }

    public PlayerUpgrades getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(PlayerUpgrades upgrades) {
        this.upgrades = upgrades;
    }
}
