package sf.cartel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


import sf.cartel.core.CameraData;
import sf.cartel.core.Gameplay;
import sf.cartel.core.PlayerData;
import sf.cartel.core.SaveGame;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.gameObjects.GameObjectManager;
import sf.cartel.gameObjects.IngameUi;
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
    private IngameUi ingameUi;

    private PlayerData playerData;
    private Gameplay gameplay;

    public GameScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager, InputHandler inputHandler) {
        this.renderPipeline = renderPipeline;
        this.screenManager = screenManager;

        this.cameraData = new CameraData(camera);

        this.inputHandler = inputHandler;
        this.objectClickHandler = new ObjectClickHandler(cameraData, inputHandler);

        playerData = SaveGame.loadPlayerData();
        this.gameplay = new Gameplay(gameObjectManager, objectClickHandler, playerData);

        this.ingameUi = new IngameUi(playerData);
    }

    @Override
    public void buildStage() {

        cameraData.getOrthographicCamera().position.set(0, 0, 0);
        cameraData.setZoomValue(0.15f);

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
        ingameUi.update(delta);
        gameObjectManager.postUpdate();
    }

    private void stepFastUpdate(float delta) {
        renderPipeline.begin();
        gameObjectManager.draw(delta, renderPipeline);
        ingameUi.draw(delta, renderPipeline);

        renderPipeline.end();


        cameraData.update(null);
        renderPipeline.updateBatchMatrix();
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler.getInputMultiplexer());
        inputHandler.unsubscribeAll();
        objectClickHandler.subscribeEvents();
        gameplay.initialize();




        this.inputHandler.addListener(InputEventType.TOUCH_DOWN, 100, inputEvent -> {
            Vector3 worldPos = cameraData.getOrthographicCamera().unproject(new Vector3(inputEvent.getX1(), inputEvent.getX2(), 0f));
            Gdx.app.log("nega", "new Vector2("+worldPos.x+"f, "+worldPos.y+"f), ");

            //TODO
//            screenManager.showScreen(MainMenuScreen.class);

        });

        this.inputHandler.addListener(InputEventType.TOUCH_DOWN, 100,
                inputEvent -> cameraData.setCurrentScale(cameraData.getZoomValue()));

        this.inputHandler.addListener(InputEventType.ZOOM, 100, inputEvent -> {
            float ratio = inputEvent.getX1() / inputEvent.getX2();
            cameraData.setZoomValue(cameraData.getCurrentScale() * ratio);
        });

        this.inputHandler.addListener( InputEventType.PAN, 100,
                inputEvent -> cameraData.getDragValue().set(inputEvent.getX3(), inputEvent.getX4()));

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
