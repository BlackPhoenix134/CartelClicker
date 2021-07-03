package sf.cartel.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class MathFunction {
    private float a;
    private float pow;
    private float d;

    public MathFunction(float a, float pow, int d) {
        this.a = a;
        this.pow = pow;
        this.d = d;
    }


    public float calc(float x) {
        return a * (float)Math.pow(x, pow) + d;
    }
}
