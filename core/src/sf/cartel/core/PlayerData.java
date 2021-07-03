package sf.cartel.core;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import sf.cartel.core.Math.GoodMath;

public class PlayerData {
    public BigInteger money = BigInteger.ZERO;

    private Map<DrugType, BigInteger> drugs = new HashMap<>();

    public Map<DrugType, BigInteger> getDrugs() {
        return drugs;
    }

    public BigInteger addMoney(BigInteger amount) {
        money = money.add(amount);
        return money;
    }

    public BigInteger removeMoney(BigInteger amount) {
        money = money.subtract(amount);
        return money;
    }

    public BigInteger addDrug(DrugType drugType, int amount) {
        if(!drugs.containsKey(drugType))
            drugs.put(drugType, BigInteger.ZERO);
        BigInteger currValue = drugs.get(drugType);
        drugs.replace(drugType, GoodMath.add(currValue, amount));
        return getDrug(drugType);
    }

    public BigInteger getDrug(DrugType drugType) {
        if(!drugs.containsKey(drugType))
            drugs.put(drugType, BigInteger.ZERO);
        return drugs.get(drugType);
    }

    public BigInteger setDrug(DrugType drugType, int amount) {
        if(drugs.containsKey(drugType))
            drugs.replace(drugType, new BigInteger(String.valueOf(amount)));
        else
            drugs.put(drugType, new BigInteger(String.valueOf(amount)));
        return getDrug(drugType);
    }

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
