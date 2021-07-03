package sf.cartel.core;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PlayerUpgrades {
    private Map<DrugType, Upgrade> sellUpgrade = new HashMap<>();
    private Map<DrugType, Upgrade> productionUpgrades = new HashMap<>();
    private Map<DrugType, Upgrade> distributionUpgrades = new HashMap<>();

    public PlayerUpgrades() {

        // TODO IMPLEMENT FITTING VALUES FOR DISTRIBUTION

        productionUpgrades.put(DrugType.Weed, new Upgrade(new ProductionFunction(new BigDecimal(1.67)), new CostFunction(new BigDecimal(3.738), new BigDecimal(1.07))));
        distributionUpgrades.put(DrugType.Weed, new Upgrade(new ProductionFunction(BigDecimal.ONE), new CostFunction(new BigDecimal(3.738), new BigDecimal(1.07))));
        sellUpgrade.put(DrugType.Weed, new Upgrade(new SellFunction(0.05f, 1), new CostFunction(new BigDecimal(3.738), new BigDecimal(1.07))));

        productionUpgrades.put(DrugType.Pills, new Upgrade(new ProductionFunction(new BigDecimal(20)), new CostFunction(new BigDecimal(60), new BigDecimal(1.15))));
        distributionUpgrades.put(DrugType.Pills, new Upgrade(new ProductionFunction(BigDecimal.ONE), new CostFunction(new BigDecimal(60), new BigDecimal(1.15))));
        sellUpgrade.put(DrugType.Pills, new Upgrade(new SellFunction(0.05f, 3), new CostFunction(new BigDecimal(60), new BigDecimal(1.15))));

        productionUpgrades.put(DrugType.Coke, new Upgrade(new ProductionFunction(new BigDecimal(90)), new CostFunction(new BigDecimal(720), new BigDecimal(1.14))));
        distributionUpgrades.put(DrugType.Coke, new Upgrade(new ProductionFunction(BigDecimal.ONE), new CostFunction(new BigDecimal(720), new BigDecimal(1.14))));
        sellUpgrade.put(DrugType.Coke, new Upgrade(new SellFunction(0.05f, 6), new CostFunction(new BigDecimal(720), new BigDecimal(1.14))));

        productionUpgrades.put(DrugType.Oxy, new Upgrade(new ProductionFunction(new BigDecimal(360)), new CostFunction(new BigDecimal(8640), new BigDecimal(1.13))));
        distributionUpgrades.put(DrugType.Oxy, new Upgrade(new ProductionFunction(BigDecimal.ONE), new CostFunction(new BigDecimal(8640), new BigDecimal(1.13))));
        sellUpgrade.put(DrugType.Oxy, new Upgrade(new SellFunction(0.05f,12), new CostFunction(new BigDecimal(8640), new BigDecimal(1.13))));

        productionUpgrades.put(DrugType.Heroin, new Upgrade(new ProductionFunction(new BigDecimal(2160)), new CostFunction(new BigDecimal(130680), new BigDecimal(1.12))));
        distributionUpgrades.put(DrugType.Heroin, new Upgrade(new ProductionFunction(BigDecimal.ONE), new CostFunction(new BigDecimal(130680), new BigDecimal(1.12))));
        sellUpgrade.put(DrugType.Heroin,new Upgrade(new SellFunction(0.05f,24), new CostFunction(new BigDecimal(130680), new BigDecimal(1.12))));

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
