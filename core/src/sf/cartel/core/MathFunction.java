package sf.cartel.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public interface MathFunction {

    public BigDecimal calc(int owned);

    public float getBaseValue();

}
