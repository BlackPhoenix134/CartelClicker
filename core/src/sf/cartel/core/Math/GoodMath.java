package sf.cartel.core.Math;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.math.BigInteger;
import java.util.Random;

public abstract class GoodMath {

    private static Random random = new Random();

    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    public static Vector2 sub(Vector2 a, Vector2 b) {
        return new Vector2(a.x - b.x, a.y - b.y);
    }

    public static Vector2 mul(Vector2 a, Vector2 b) {
        return new Vector2(a.x * b.x, a.y * b.y);
    }

    public static Vector2 div(Vector2 a, Vector2 b) {
        return new Vector2(a.x / b.x, a.y / b.y);
    }

    public static Vector2 add(Vector2 a, float value) {
        return new Vector2(a.x + value, a.y + value);
    }

    public static Vector2 sub(Vector2 a, float value) {
        return new Vector2(a.x - value, a.y - value);
    }

    public static Vector2 mul(Vector2 a, float value) {
        return new Vector2(a.x * value, a.y * value);
    }

    public static Vector2 div(Vector2 a, float value) {
        return new Vector2(a.x / value, a.y / value);
    }

    public static boolean equals(Vector2 a, Vector2 b, float theta) {
        return equals(a.x, b.x, theta) && equals(a.y, b.y, theta);
    }

    public static boolean equals(float a, float b, float theta) {
        return Math.abs(a - b) <= theta;
    }

    public static Vector2 moveTowards(Vector2 from, Vector2 to, float maxDistanceDelta) {
        // avoid vector ops because current scripting backends are terrible at inlining
        float toVectorX = to.x - from.x;
        float toVectorY = to.y - from.y;

        float sqdist = toVectorX * toVectorX + toVectorY * toVectorY;

        if (sqdist == 0 || (maxDistanceDelta >= 0 && sqdist <= maxDistanceDelta * maxDistanceDelta))
            return to;
        float dist = (float) Math.sqrt(sqdist);
        return new Vector2(from.x + toVectorX / dist * maxDistanceDelta,
                from.y + toVectorY / dist * maxDistanceDelta);
    }

    public static float ratio(float a, float b) {
        return b / a;
    }

    public static BigInteger add(BigInteger a, int b) {
        return a.add(new BigInteger(String.valueOf(b)));
    }

    public static BigInteger sub(BigInteger a, int b) {
        return a.subtract(new BigInteger(String.valueOf(b)));
    }

    public static BigInteger mul(BigInteger a, int b) {
        return a.multiply(new BigInteger(String.valueOf(b)));
    }

    public static float map(float value, float a1, float a2, float b1, float b2) {
        return b1 + (value - a1) * (b2 - b1) / (a2 - a1);
    }

    public static float randFloat(float min, float max) {
        return min + random.nextFloat() * (max - min);
    }

    public static int randInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static Vector2 randomOffset(Vector2 vector, float offset) {
        int sign = 1;
        if (random.nextBoolean())
            sign = -1;
        return new Vector2(vector.x + randFloat(0, offset) * sign, vector.y + randFloat(0, offset) * sign);
    }

    public static float angle(Vector2 a) {
        float angle = (float) Math.atan2(a.y, a.x) * MathUtils.radiansToDegrees;
        if (angle < 0)
            angle += 360;
        return angle;
    }
}
