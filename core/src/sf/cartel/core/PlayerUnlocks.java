package sf.cartel.core;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerUnlocks {
    private Map<DrugType, BigInteger> unlockPrice = new HashMap<DrugType, BigInteger>() {{
        put(DrugType.Weed, new BigInteger(String.valueOf(1)));
        put(DrugType.Pills, new BigInteger(String.valueOf(100)));
        put(DrugType.Coke, new BigInteger(String.valueOf(10000)));
        put(DrugType.Oxy, new BigInteger(String.valueOf(1000000)));
        put(DrugType.Heroin, new BigInteger(String.valueOf(10000000)));
    }};
    private Map<DrugType, Boolean> unlockedMap = new HashMap<DrugType, Boolean>() {{
        put(DrugType.Weed, true);
        put(DrugType.Pills,  false);
        put(DrugType.Coke,   false);
        put(DrugType.Oxy,    false);
        put(DrugType.Heroin, false);
    }};

    public boolean isUnlocked(DrugType drugType) {
        return unlockedMap.get(drugType);
    }

    public void setUnlocked(DrugType drugType, boolean value) {
        unlockedMap.replace(drugType, value);
    }

    public BigInteger getUnlockPrice(DrugType drugType) {
        return unlockPrice.get(drugType);
    }
}
