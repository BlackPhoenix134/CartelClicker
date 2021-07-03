package sf.cartel.core;

import java.math.BigDecimal;

public class Upgrade {
    private int nr;
    private MathFunction outFunction;
    private MathFunction priceFunction;

    public Upgrade(MathFunction outFunction, MathFunction priceFunction) {
        this.outFunction = outFunction;
        this.priceFunction = priceFunction;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getNextUpgradePrice() {
        return getUpgradePrice();
    }

    //get upgrade price for next upgrade
    public int getUpgradePrice() {
        return (int)priceFunction.calc(nr).intValue();
    }

    //get production
    public int getProductionAmount() {
        return (int) outFunction.calc(nr).intValue();
    }

    //get getSaleMultiplier
    public BigDecimal getSaleMultiplier() {
        return outFunction.calc(nr);
    }

    //get getSaleMultiplier
    public float getBasePrice() {
        return outFunction.getBaseValue();
    }

    public void incrementNr() {
        nr++;
    }
}
