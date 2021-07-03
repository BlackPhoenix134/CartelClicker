package sf.cartel.core;

import java.util.HashMap;
import java.util.Map;

public class PlayerUpgrades {
    private Map<DrugType, Upgrade> sellUpgrade = new HashMap<>();
    private Map<DrugType, Upgrade> productionUpgrades = new HashMap<>();
    private Map<DrugType, Upgrade> distributionUpgrades = new HashMap<>();

    public PlayerUpgrades() {

        productionUpgrades.put(DrugType.Weed, new Upgrade(new MathFunction(0.5f, 1.5f, 1), new MathFunction(0.6f, 1.5f, 10)));
//        productionUpgrades.put(DrugType.Pills, new Upgrade(new MathFunction(0.6f, 1.5f, 1), new MathFunction(0.7f, 1.5f, 150)));
//        productionUpgrades.put(DrugType.Coke, new Upgrade(new MathFunction(0.7f, 1.5f, 1), new MathFunction(0.8f, 1.5f, 225)));
//        productionUpgrades.put(DrugType.Oxy, new Upgrade(new MathFunction(0.8f, 1.5f, 1), new MathFunction(0.9f, 1.5f, 300)));
//        productionUpgrades.put(DrugType.Heroin, new Upgrade(new MathFunction(0.9f, 1.5f, 1), new MathFunction(1f, 1.5f, 500)));

        distributionUpgrades.put(DrugType.Weed, new Upgrade(new MathFunction(0.5f, 1.5f, 1), new MathFunction(0.6f, 1.5f, 100)));
//        distributionUpgrades.put(DrugType.Pills, new Upgrade(new MathFunction(0.6f, 1.5f, 1), new MathFunction(0.7f, 1.5f, 150)));
//        distributionUpgrades.put(DrugType.Coke, new Upgrade(new MathFunction(0.7f, 1.5f, 1), new MathFunction(0.8f, 1.5f, 225)));
//        distributionUpgrades.put(DrugType.Oxy, new Upgrade(new MathFunction(0.8f, 1.5f, 1), new MathFunction(0.9f, 1.5f, 300)));
//        distributionUpgrades.put(DrugType.Heroin, new Upgrade(new MathFunction(0.9f, 1.5f, 1), new MathFunction(1f, 1.5f, 500)));

        sellUpgrade.put(DrugType.Weed, new Upgrade(new MathFunction(1.05f, 1.5f, 1), new MathFunction(1.06f, 1.5f, 10)));
//        sellUpgrade.put(DrugType.Pills, new Upgrade(new MathFunction(1.06f, 1.5f, 1), new MathFunction(1.07f, 1.5f, 150)));
//        sellUpgrade.put(DrugType.Coke, new Upgrade(new MathFunction(1.07f, 1.5f, 1), new MathFunction(1.08f, 1.5f, 225)));
//        sellUpgrade.put(DrugType.Oxy, new Upgrade(new MathFunction(1.08f, 1.5f, 1), new MathFunction(1.09f, 1.5f, 300)));
//        sellUpgrade.put(DrugType.Heroin, new Upgrade(new MathFunction(1.09f, 1.5f, 1), new MathFunction(1.1f, 1.5f, 500)));

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

    public Upgrade getDistributionUpgrade(DrugType drugType) {
        return distributionUpgrades.get(drugType);
    }


}
