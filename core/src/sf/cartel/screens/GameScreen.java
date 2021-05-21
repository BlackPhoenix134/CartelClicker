package sf.cartel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.CameraData;
import sf.cartel.core.Consumer;
import sf.cartel.gameObjects.GameObjectManager;
import sf.cartel.gameObjects.MapObject;
import sf.cartel.input.InputEvent;
import sf.cartel.input.InputEventType;
import sf.cartel.input.InputHandler;
import sf.cartel.rendering.RenderPipeline;

public class GameScreen extends AbstractScreen {
    private final float TICKS = 1f / 60f;
    private float tickAccumulation = 0;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private RenderPipeline renderPipeline;
    private CameraData cameraData;
    private ScreenManager screenManager;
    private InputHandler inputHandler;

    public GameScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager, InputHandler inputHandler) {
        this.renderPipeline = renderPipeline;
        this.screenManager = screenManager;
        this.cameraData = new CameraData(camera);
        this.inputHandler = inputHandler;
    }

    @Override
    public void buildStage() {
            MapObject mapObject = gameObjectManager.create(MapObject.class);
            mapObject.setSprite(new Sprite(Assets.getAsset(AssetDescriptors.MAP)));
    }


    @Override
    public void render(float delta) {
        delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tickAccumulation += Math.min(delta, 0.25f);
        if (tickAccumulation >= TICKS) {
            tickAccumulation -= TICKS; //takes multi phys misses into account (low fps)
            stepTick(delta);
        }
        stepFastUpdate(delta);
    }

    private void stepTick(float delta) {
        gameObjectManager.update(delta);
        gameObjectManager.postUpdate();
    }

    private void stepFastUpdate(float delta) {
        renderPipeline.begin();
        gameObjectManager.draw(delta, renderPipeline);
        renderPipeline.end();
        cameraData.update(null);
        renderPipeline.updateBatchMatrix();
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler.getInputMultiplexer());
        this.inputHandler.addListener(new Consumer<InputEvent>() {
            @Override
            public void call(InputEvent inputEvent) {
                cameraData.setCurrentScale(cameraData.getZoomValue());
            }
        }, InputEventType.TOUCH_DOWN, 10);
        this.inputHandler.addListener(new Consumer<InputEvent>() {
            @Override
            public void call(InputEvent inputEvent) {
                float ratio = inputEvent.getX1() / inputEvent.getX2();
                cameraData.setZoomValue(cameraData.getCurrentScale() * ratio);
            }
        }, InputEventType.ZOOM, 10);
        this.inputHandler.addListener(new Consumer<InputEvent>() {
            @Override
            public void call(InputEvent inputEvent) {
                cameraData.getDragValue().set(inputEvent.getX3(), inputEvent.getX4());
            }
        }, InputEventType.PAN, 10);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        this.inputHandler.unsubscribeAll();
    }


    @Override
    public void dispose() {
        renderPipeline.dispose();
    }
}
