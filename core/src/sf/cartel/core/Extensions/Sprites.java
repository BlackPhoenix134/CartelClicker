package sf.cartel.core.Extensions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Sprites {
    public static float scaledWidth(Sprite sprite) {
        return sprite.getWidth() * sprite.getScaleX();
    }

    public static float scaledHeight(Sprite sprite) {
        return sprite.getHeight() * sprite.getScaleY();
    }

    public static Sprite resetTexture(Sprite sprite, Texture texture) {
        sprite.setTexture(texture);
        sprite.setSize(texture.getWidth(), texture.getHeight());
        sprite.setOrigin(texture.getWidth() / 2f, texture.getHeight() / 2f);
        return sprite;
    }

    public static Vector2 position(Sprite sprite) {
        return new Vector2(sprite.getX(), sprite.getY());
    }
}
