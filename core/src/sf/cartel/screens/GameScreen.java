package sf.cartel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.SYAssetManager;
import sf.cartel.core.Consumer;
import sf.cartel.gameObjects.GameObjectManager;
import sf.cartel.gameObjects.MapObject;
import sf.cartel.input.InputEvent;
import sf.cartel.input.InputEventType;
import sf.cartel.input.InputHandler;
import sf.cartel.input.PanListener;
import sf.cartel.input.TouchDownListener;
import sf.cartel.input.TouchUpListener;
import sf.cartel.input.ZoomListener;
import sf.cartel.rendering.RenderPipeline;

public class GameScreen extends AbstractScreen {
    private final float TICKS = 1f / 60f;
    private float tickAccumulation = 0;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private RenderPipeline renderPipeline;
    private OrthographicCamera camera;
    private ScreenManager screenManager;
    private InputHandler inputHandler;
    private Vector2 dragValue = new Vector2();
    private Vector2 oldDragValue = new Vector2();
    private float currentScale = 0.25f;
    private float zoomValue = 1f;


    public GameScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager, InputHandler inputHandler) {
        this.renderPipeline = renderPipeline;
        this.camera = camera;
        this.screenManager = screenManager;
        this.inputHandler = inputHandler;
    }

    @Override
    public void buildStage() {
        MapObject mapObject = gameObjectManager.create(MapObject.class);
        mapObject.setSprite(new Sprite(SYAssetManager.getAsset(AssetDescriptors.MAP)));
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
        updateCam();
        renderPipeline.updateBatchMatrix();
    }


    @Override
    public void show() {
        this.inputHandler.unsubscribeAll();
        this.inputHandler.addListener(new Consumer<InputEvent>() {
            @Override
            public void call(InputEvent inputEvent) {
                currentScale = zoomValue;
            }
        }, InputEventType.TOUCH_DOWN, 10);
        this.inputHandler.addListener(new Consumer<InputEvent>() {
            @Override
            public void call(InputEvent inputEvent) {
                float ratio = inputEvent.getX1() / inputEvent.getX2();
                zoomValue = currentScale * ratio;
            }
        }, InputEventType.ZOOM, 10);
        this.inputHandler.addListener(new Consumer<InputEvent>() {
            @Override
            public void call(InputEvent inputEvent) {
                dragValue.x = inputEvent.getX1();
                dragValue.y = inputEvent.getX2();
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

    private void updateCam() {
        camera.zoom = this.zoomValue;
        float scale = this.zoomValue * 2.0f;
        if (oldDragValue.x != dragValue.x || oldDragValue.y != dragValue.y) {
            camera.position.add(-dragValue.x * scale, dragValue.y * scale, 0);
            oldDragValue.x = dragValue.x;
            oldDragValue.y = dragValue.y;

            camera.position.set(clampCam(camera.position, new Rectangle(-500, -500, 500, 500)));
        }
        camera.update();
    }


    private Vector3 clampCam(Vector3 position, Rectangle boundingBox) {
        return new Vector3(clamp(position.x, boundingBox.x, boundingBox.width), clamp(position.y, boundingBox.y, boundingBox.height), position.z);
    }

    private float clamp(float value, float min, float max) {
        if(value < min)
            return min;
        return Math.min(value, max);
    }

    @Override
    public void dispose() {
        renderPipeline.dispose();
    }



}
