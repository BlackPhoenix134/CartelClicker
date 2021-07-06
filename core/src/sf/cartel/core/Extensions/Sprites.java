package sf.cartel.core.Extensions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Sprites {
    public static float getScaledWidth(Sprite sprite) {
        return sprite.getWidth() * sprite.getScaleX();
    }

    public static float getScaledHeight(Sprite sprite) {
        return sprite.getHeight() * sprite.getScaleY();
    }

    public static Sprite resetTexture(Sprite sprite, Texture texture) {
        sprite.setTexture(texture);
        sprite.setSize(texture.getWidth(), texture.getHeight());
        sprite.setOrigin(texture.getWidth() / 2f, texture.getHeight() / 2f);
        return sprite;
    }
}
