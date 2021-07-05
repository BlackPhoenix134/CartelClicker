package sf.cartel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.assets.ShaderManager;
import sf.cartel.core.CameraData;
import sf.cartel.core.Gameplay;
import sf.cartel.core.Globals;
import sf.cartel.core.PlayerData;
import sf.cartel.core.SaveGame;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.gameObjects.AnimatedSpriteDrawableObject;
import sf.cartel.gameObjects.GameObjectManager;
import sf.cartel.gameObjects.IngameUi;
import sf.cartel.input.InputEventType;
import sf.cartel.input.InputHandler;
import sf.cartel.rendering.RenderPipeline;
import sf.cartel.ui.AliveButton;

public class OptionsScreen extends AbstractScreen {
    private final float TICKS = 1f / 60f;
    private float tickAccumulation = 0;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private RenderPipeline renderPipeline;
    private CameraData cameraData;
    private ScreenManager screenManager;
    private InputHandler inputHandler;
    private ObjectClickHandler objectClickHandler;
    private ShaderManager shaderManager;
    private AliveButton btnStartGame;


    private float passiveDrugsTime;

    public OptionsScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager, InputHandler inputHandler) {
        this.renderPipeline = renderPipeline;
        this.screenManager = screenManager;
        this.shaderManager = new ShaderManager();
        this.cameraData = new CameraData(camera);

        this.inputHandler = inputHandler;
        this.objectClickHandler = new ObjectClickHandler(cameraData, inputHandler);
    }

    @Override
    public void buildStage() {
        cameraData.getOrthographicCamera().position.set(0, 0, 0);
        cameraData.setZoomValue(0.15f);

        AnimatedSpriteDrawableObject animatedSpriteDrawableObject = gameObjectManager.create(AnimatedSpriteDrawableObject.class);
        animatedSpriteDrawableObject.setUiObject(true);
        animatedSpriteDrawableObject.setDrawOrder(5000 + 1);

        animatedSpriteDrawableObject.init(Assets.getAsset(AssetDescriptors.BLACK), 3, 3, .2f);
        animatedSpriteDrawableObject.getSprite().setScale(1f);
        animatedSpriteDrawableObject.getSprite().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);


        //TODO BACK BUTTON
        Texture startGameTexture = Assets.getAsset(AssetDescriptors.BUTTON_BACK);
        btnStartGame = new AliveButton(startGameTexture);
        btnStartGame.setScale(Gdx.graphics.getWidth() / btnStartGame.getWidth() * 0.15f, Gdx.graphics.getHeight() / btnStartGame.getHeight() * 0.1f);
        btnStartGame.setPosition( Gdx.graphics.getWidth()/2, (Gdx.graphics.getHeight()/2));

        btnStartGame.addListener(new AliveButton.AliveButtonListener() {

            @Override
            public void onClick() {
                Globals.SOUND_BUTTON_PRESS.play();
                screenManager.showScreen(MainMenuScreen.class);
            }
        });

        addActorsToStage(btnStartGame);

    }


    @Override
    public void render(float delta) {
        delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
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

        this.inputHandler.addListener(InputEventType.TOUCH_DOWN, 100, inputEvent -> {
            Vector3 worldPos = cameraData.getOrthographicCamera().unproject(new Vector3(inputEvent.getX1(), inputEvent.getX2(), 0f));
            Gdx.app.log("nega", "new Vector2("+worldPos.x+"f, "+worldPos.y+"f), ");

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
