package sf.cartel.core;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SellFunction implements MathFunction {
    float basePrice;
    float increase;

    public SellFunction(float increase, float basePrice) {
        this.increase = increase;
        this.basePrice = basePrice;
    }

    /*
    Calculates the Sell Multiplier
    */
    public BigDecimal calc(int owned) {
        return new BigDecimal(1+owned*increase);
    }

    public float getBaseValue() {
        return basePrice;
    }

}
