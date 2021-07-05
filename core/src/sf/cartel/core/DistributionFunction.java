package sf.cartel.core;

import java.math.BigDecimal;

public class DistributionFunction implements MathFunction {
    BigDecimal distributionBase;


    public DistributionFunction(BigDecimal distributionBase) {
        this.distributionBase = distributionBase;
    }


    @Override
    public BigDecimal calc(int owned) {
        return distributionBase.multiply(BigDecimal.valueOf(owned));
    }

    @Override
    public float getBaseValue() {
        return distributionBase.floatValue();
    }
}
