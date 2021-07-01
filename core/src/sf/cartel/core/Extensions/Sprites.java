package sf.cartel.core.Extensions;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Sprites {
    public static float getScaledWidth(Sprite sprite) {
        return sprite.getWidth() * sprite.getScaleX();
    }

    public static float  getScaledHeight(Sprite sprite) {
        return sprite.getHeight() * sprite.getScaleY();
    }
}
