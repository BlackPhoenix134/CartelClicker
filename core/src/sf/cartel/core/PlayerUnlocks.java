package sf.cartel.core;

public class PlayerUnlocks {
    private boolean isMap1Unlocked = true;
    private boolean isMap2Unlocked = true;
    private boolean isMap3Unlocked;
    private boolean isMap4Unlocked;
    private boolean isMap5Unlocked;

    public boolean isMap1Unlocked() {
        return isMap1Unlocked;
    }

    public void setMap1Unlocked(boolean map1Unlocked) {
        isMap1Unlocked = map1Unlocked;
    }

    public boolean isMap2Unlocked() {
        return isMap2Unlocked;
    }

    public void setMap2Unlocked(boolean map2Unlocked) {
        isMap2Unlocked = map2Unlocked;
    }

    public boolean isMap3Unlocked() {
        return isMap3Unlocked;
    }

    public void setMap3Unlocked(boolean map3Unlocked) {
        isMap3Unlocked = map3Unlocked;
    }

    public boolean isMap4Unlocked() {
        return isMap4Unlocked;
    }

    public void setMap4Unlocked(boolean map4Unlocked) {
        isMap4Unlocked = map4Unlocked;
    }

    public boolean isMap5Unlocked() {
        return isMap5Unlocked;
    }

    public void setMap5Unlocked(boolean map5Unlocked) {
        isMap5Unlocked = map5Unlocked;
    }
}
