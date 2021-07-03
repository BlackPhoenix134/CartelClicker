package sf.cartel.core;

import java.math.BigInteger;

public class PlayerData {
    public BigInteger money = BigInteger.ZERO;
    public BigInteger weed = BigInteger.ZERO;
    public BigInteger pills = BigInteger.ZERO;
    public BigInteger coke = BigInteger.ZERO;
    public BigInteger oxy = BigInteger.ZERO;
    public BigInteger heroin = BigInteger.ZERO;

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
