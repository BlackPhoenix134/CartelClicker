package sf.cartel.core;

import java.util.HashMap;
import java.util.Map;

public class PlayerUpgrades {
    private Map<DrugType, Upgrade> sellUpgrade = new HashMap<>();
    private Map<DrugType, Upgrade> productionUpgrades = new HashMap<>();
    private Map<DrugType, Upgrade> distributionUpgrades = new HashMap<>();

    public PlayerUpgrades() {
        sellUpgrade.put(DrugType.Weed, new Upgrade(new MathFunction(0.5f, 1, 1), new MathFunction(0.5f, 1, 10)));
        sellUpgrade.put(DrugType.Pills, new Upgrade(new MathFunction(0.5f, 1, 1), new MathFunction(0.5f, 1, 10)));
        sellUpgrade.put(DrugType.Coke, new Upgrade(new MathFunction(0.5f, 1, 1), new MathFunction(0.5f, 1, 10)));
        sellUpgrade.put(DrugType.Oxy, new Upgrade(new MathFunction(0.5f, 1, 1), new MathFunction(0.5f, 1, 10)));
        sellUpgrade.put(DrugType.Heroin, new Upgrade(new MathFunction(0.5f, 1, 1), new MathFunction(0.5f, 1, 10)));

        productionUpgrades.put(DrugType.Weed, new Upgrade(new MathFunction(0.5f, 1, 1), new MathFunction(0.5f, 1, 10)));
        productionUpgrades.put(DrugType.Pills, new Upgrade(new MathFunction(0.5f, 1, 1), new MathFunction(0.5f, 1, 10)));
        productionUpgrades.put(DrugType.Coke, new Upgrade(new MathFunction(0.5f, 1, 1), new MathFunction(0.5f, 1, 10)));
        productionUpgrades.put(DrugType.Oxy, new Upgrade(new MathFunction(0.5f, 1, 1), new MathFunction(0.5f, 1, 10)));
        productionUpgrades.put(DrugType.Heroin, new Upgrade(new MathFunction(0.5f, 1, 1), new MathFunction(0.5f, 1, 10)));
    }

    public void incrementSellUpgrade(DrugType drugType) {
        sellUpgrade.get(drugType).incrementNr();
    }

    public void incrementProductionUpgrade(DrugType drugType) {
        productionUpgrades.get(drugType).incrementNr();
    }

    public Upgrade getSellUpgrade(DrugType drugType) {
        return sellUpgrade.get(drugType);
    }
    public Upgrade getProductionUpgrade(DrugType drugType) {
        return productionUpgrades.get(drugType);
    }
}
