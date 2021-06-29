package sf.cartel.rendering;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;

public class DrawableItemText extends DrawableItem {
    private BitmapFont font;
    private String text;
    private Vector2 position;
    private float scale;

    public DrawableItemText() {

    }

    public void set(String text, Vector2 position, float scale, int drawLayer, BitmapFont bitmapFont, ShaderProgram shader) {
        super.set(drawLayer, shader);
        this.text = text;
        this.position = position;
        this.scale = scale;
        this.font = bitmapFont;
    }

    public DrawableItemText(String text, Vector2 position, float scale, int drawLayer, ShaderProgram shader, BitmapFont bitmapFont) {
        super(drawLayer, shader);
        this.text = text;
        this.position = position;
        this.scale = scale;
        this.font = bitmapFont;
    }

    @Override
    public void render(SpriteBatch batch) {
        font.getData().setScale(scale);
        font.draw(batch, text, position.x, position.y);
    }
}
