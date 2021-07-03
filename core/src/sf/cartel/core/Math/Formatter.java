package sf.cartel.core.Math;

import java.math.BigInteger;

public abstract class Formatter {
    public static String toScientificNumber(BigInteger value) {
        String valStr = value.toString();
        if(valStr.length() < 7) {
            return valStr;
        } else {
            char[] valChars = value.toString().toCharArray();
            return valChars[0] + "." + valChars[1] + valChars[2] + valChars[3]
                    + " E+" + (valChars.length - 1);
        }
    }
}
