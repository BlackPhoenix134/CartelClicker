package sf.cartel.core;
import com.badlogic.gdx.graphics.Texture;

public class Drug {

    private String name;
    private int sellPrice;
    private Texture icon;

    public Drug(String name, int sellPrice, Texture icon) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public Texture getIcon() {
        return icon;
    }
}
