package sf.cartel.core;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CostFunction implements MathFunction{
    private BigDecimal costBase;
    private BigDecimal growthRate;

    public CostFunction(BigDecimal costBase, BigDecimal growthRate) {
        this.costBase = costBase;
        this.growthRate = growthRate;
    }

    /*
    Calculates the cost for the next upgrade
     */
    public BigDecimal calc(int owned){
        BigDecimal growth = growthRate.pow(owned+1);
        return growth.multiply(costBase);
    }

    @Override
    public float getBaseValue() {
        return costBase.floatValue();
    }

}
