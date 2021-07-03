package sf.cartel.core;

public class Upgrade {
    private int nr;
    private MathFunction outputFunction;
    private MathFunction priceFunction;

    public Upgrade(MathFunction outputFunction, MathFunction priceFunction) {
        this.outputFunction = outputFunction;
        this.priceFunction = priceFunction;
    }

    public Upgrade(MathFunction outputFunction, MathFunction priceFunction, int nr) {
        this(outputFunction, priceFunction);
        this.nr = nr;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }


    public int getNextUpgradePrice() {
        return getUpgradePrice(nr + 1);
    }

    public int getUpgradePrice(int nr) {
        return (int)priceFunction.calc(nr);
    }

    public int getOutputFor(int nr) {
        return (int)outputFunction.calc(nr);
    }

    public int getCurrentOutput() {
        return (int)outputFunction.calc(nr);
    }

    public void incrementNr() {
        nr++;
    }
}
