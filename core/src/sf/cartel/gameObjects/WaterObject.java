package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.assets.ShaderManager;
import sf.cartel.rendering.RenderPipeline;

public class WaterObject extends GameObject {
    private Sprite sprite;
    private int drawLayer = 0;
    private ShaderProgram shader = ShaderManager.defaultShader;
    private FrameBuffer frameBuffer;
    private boolean enabled;
    float timer = 0;
    WaterObject(String uuid) {
        super(uuid);

    }

    public void init(ShaderManager shaderManager, Sprite sprite) {
        this.sprite = sprite;
        shader = shaderManager.loadShader("flowmap.vert.glsl", "flowmap.frag.glsl");
        ShaderProgram.pedantic = false;
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), true);
    }

    public int getDrawLayer() {
        return drawLayer;
    }

    public void setDrawLayer(int drawLayer) {
        this.drawLayer = drawLayer;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector2 getPosition() {
        return new Vector2(sprite.getX(), sprite.getY());
    }


    @Override
    public void update(float delta) {


    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        if(enabled) {
            Vector2 v =  new Vector2(100, 100);
            v.x = v.x / Gdx.graphics.getWidth();
            v.y = v.y / Gdx.graphics.getHeight();
            shader.setUniformf("time", delta);
            shader.setUniformf("center", v);
        }
        pipeline.add(sprite, drawLayer, shader);
    }

    private float[] floatArrayOf() {
        return new float[] { 0.006f, 0.007f };
    }
}
