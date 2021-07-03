package sf.cartel.core;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductionFunction implements MathFunction{
    BigDecimal productionBase;

    public ProductionFunction(BigDecimal productionBase) {
        this.productionBase = productionBase;
    }

    /*
    Calculate Recources produced in next tick
    */
    public BigDecimal calc(int owned) {
        return productionBase.multiply(BigDecimal.valueOf(owned)).add(productionBase);
    }

    @Override
    public float getBaseValue() {
        return productionBase.floatValue();
    }

}
