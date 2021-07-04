package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.assets.ShaderManager;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.rendering.RenderPipeline;

public class WaterObject extends GameObject {
    private int drawLayer = 0;
    private AnimationController animationController;
    private Sprite sprite;

    WaterObject(String uuid) {
        super(uuid);
    }

    public void init(Texture texture) {
        sprite = new Sprite(texture);
        animationController = new AnimationController(texture, 3, 2, 0.25f);
    }

    public int getDrawLayer() {
        return drawLayer;
    }

    public void setDrawLayer(int drawLayer) {
        this.drawLayer = drawLayer;
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        animationController.addDelta(delta);
        sprite.setRegion(animationController.getCurrentFrame());
        pipeline.add(sprite, drawLayer);
    }
}
