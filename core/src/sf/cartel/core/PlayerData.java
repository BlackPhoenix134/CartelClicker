package sf.cartel.core;

import java.math.BigInteger;

import sf.cartel.gameObjects.PlayerUnlocks;

public class PlayerData {
    private PlayerUnlocks playerUnlocks = new PlayerUnlocks();

    public PlayerUnlocks getPlayerUnlocks() {
        return playerUnlocks;
    }

    public void setPlayerUnlocks(PlayerUnlocks playerUnlocks) {
        this.playerUnlocks = playerUnlocks;
    }

    public BigInteger weed = BigInteger.ZERO;
    public BigInteger meth = BigInteger.ZERO;
    public BigInteger coke = BigInteger.ZERO;
    public BigInteger emma = BigInteger.ZERO;
    public BigInteger shrooms = BigInteger.ZERO;

}
