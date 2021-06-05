package sf.cartel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;


import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.CameraData;
import sf.cartel.core.Consumer;
import sf.cartel.core.ObjectClickHandler;
import sf.cartel.gameObjects.DebugClickableObject;
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
    private ObjectClickHandler objectClickHandler;

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
        objectClickHandler = new ObjectClickHandler(cameraData, inputHandler);

        DebugClickableObject obj = gameObjectManager.create(DebugClickableObject.class);
        obj.setSprite(new Sprite(Assets.getAsset(AssetDescriptors.SQUARE_40x40)));
        obj.getSprite().setPosition(100, 100);
        objectClickHandler.addTouchDownClickable(obj,  1000, false);
         obj = gameObjectManager.create(DebugClickableObject.class);
        obj.setSprite(new Sprite(Assets.getAsset(AssetDescriptors.SQUARE_40x40)));
        obj.getSprite().setPosition(120, 120);
        objectClickHandler.addTouchDownClickable(obj, 999, false);
         obj = gameObjectManager.create(DebugClickableObject.class);
        obj.setSprite(new Sprite(Assets.getAsset(AssetDescriptors.SQUARE_40x40)));
        obj.getSprite().setPosition(100, -100);
        objectClickHandler.addTouchDownClickable(obj,1000, false);
         obj = gameObjectManager.create(DebugClickableObject.class);
        obj.setSprite(new Sprite(Assets.getAsset(AssetDescriptors.SQUARE_40x40)));
        obj.getSprite().setPosition(-100, 100);
        objectClickHandler.addTouchDownClickable(obj, 1000, false);

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
        inputHandler.unsubscribeAll();
        objectClickHandler.subscribeEvents();
        this.inputHandler.addListener(InputEventType.TOUCH_DOWN, 100, new Consumer<InputEvent>() {
            @Override
            public void call(InputEvent inputEvent) {
                cameraData.setCurrentScale(cameraData.getZoomValue());
            }
        });
        this.inputHandler.addListener(InputEventType.ZOOM, 100, new Consumer<InputEvent>() {
            @Override
            public void call(InputEvent inputEvent) {
                float ratio = inputEvent.getX1() / inputEvent.getX2();
                cameraData.setZoomValue(cameraData.getCurrentScale() * ratio);
            }
        });
        this.inputHandler.addListener( InputEventType.PAN, 100, new Consumer<InputEvent>() {
            @Override
            public void call(InputEvent inputEvent) {
                cameraData.getDragValue().set(inputEvent.getX3(), inputEvent.getX4());
            }
        });
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
