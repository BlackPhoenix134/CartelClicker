package sf.cartel.core;

import java.math.BigInteger;

public class PlayerUpgrades {
    private float weedMultiplier = 1f;
    private float pillsMultiplier = 1f;
    private float cokeMultiplier = 1f;
    private float oxyMultiplier = 1f;
    private float heroinMultiplier =  1f;

    public float getWeedMultiplier() {
        return weedMultiplier;
    }

    public void setWeedMultiplier(float weedMultiplier) {
        this.weedMultiplier = weedMultiplier;
    }

    public float getOxyMultiplier() {
        return oxyMultiplier;
    }

    public void setOxyMultiplier(float oxyMultiplier) {
        this.oxyMultiplier = oxyMultiplier;
    }

    public float getCokeMultiplier() {
        return cokeMultiplier;
    }

    public void setCokeMultiplier(float cokeMultiplier) {
        this.cokeMultiplier = cokeMultiplier;
    }

    public float getPillsMultiplier() {
        return pillsMultiplier;
    }

    public void setPillsMultiplier(float pillsMultiplier) {
        this.pillsMultiplier = pillsMultiplier;
    }

    public float getHeroinMultiplier() {
        return heroinMultiplier;
    }

    public void setHeroinMultiplier(float heroinMultiplier) {
        this.heroinMultiplier = heroinMultiplier;
    }
}
