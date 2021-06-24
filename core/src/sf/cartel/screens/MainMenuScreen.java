package sf.cartel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

import java.awt.Image;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.CameraData;
import sf.cartel.input.InputHandler;
import sf.cartel.rendering.RenderPipeline;
import sf.cartel.ui.AliveButton;

public class MainMenuScreen extends AbstractScreen {
    private float              screenWidth, screenHeight;
    private AliveButton btnStartGame;
    private AliveButton        btnExitGame;
    private AliveButton btnOptions;
    private RenderPipeline renderPipeline;
    private CameraData cameraData;
    private ScreenManager      screenManager;
    private InputHandler inputHandler;
    private SpriteBatch batch;


    Texture background = new Texture(Gdx.files.internal("ui/titleScreen.png"));
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));

    Sound sound2 = Gdx.audio.newSound(Gdx.files.internal("sounds/elevator2.0.mp3"));


    public MainMenuScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager, InputHandler inputHandler) {
        this.renderPipeline = renderPipeline;
        this.cameraData = new CameraData(camera);
        this.screenManager = screenManager;
        this.inputHandler = inputHandler;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        batch = new SpriteBatch();

    }

   @Override
    public void show() {
       this.inputHandler.unsubscribeAll();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

        sound2.play(0.5f);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        super.render(delta); //this render the stage, which is responsible for the screen transitions
    }

    @Override
    public void buildStage() {
        // TODO change padding to 0.25f and fix click boxes
        float padding = screenHeight * 0.05f;

        Texture startGameTexture = Assets.getAsset(AssetDescriptors.BUTTON_START);
        Texture exitGameTexture = Assets.getAsset(AssetDescriptors.BUTTON_EXIT);
        Texture joinGameTexture = Assets.getAsset(AssetDescriptors.BUTTON_OPTIONS);



        btnStartGame = new AliveButton(startGameTexture);
        btnExitGame  = new AliveButton(exitGameTexture);
        btnOptions = new AliveButton(joinGameTexture);


        Vector2 btnStartGameSize = Scaling.fillX.apply(startGameTexture.getWidth(), startGameTexture.getHeight(), screenWidth * 0.30f, 0);
        Vector2 btnJoinGameSize = Scaling.fillX.apply(joinGameTexture.getWidth(), joinGameTexture.getHeight(), screenWidth * 0.30f, 0);
        Vector2 btnExitSize      = Scaling.fillX.apply(exitGameTexture.getWidth(), exitGameTexture.getHeight(), screenWidth * 0.30f, 0);


        btnStartGame.setSize(btnStartGameSize.x, btnStartGameSize.y);
        btnOptions.setSize (btnJoinGameSize.x, btnJoinGameSize.y);
        btnExitGame.setSize(btnExitSize.x, btnExitSize.y);

        btnStartGame.setPosition( screenWidth/2 - btnStartGame.getWidth()/2, screenHeight - padding - btnStartGame.getHeight());
        btnOptions.setPosition( screenWidth/2 - btnOptions.getWidth()/2, screenHeight  * 0.5f - btnOptions.getHeight() * 0.5f);
        btnExitGame.setPosition(screenWidth/2 - btnExitGame.getWidth()/2, padding);

        btnStartGame.addListener(new AliveButton.AliveButtonListener() {
            @Override
            public void onClick() {
                sound.play();
                screenManager.showScreen(GameScreen.class);
            }
        });

        btnOptions.addListener(new AliveButton.AliveButtonListener(){
            @Override
                    public void onClick(){
                sound.play();
                screenManager.showScreen(OptionsScreen.class);
            }

        });

        addActorsToStage(btnStartGame, btnOptions, btnExitGame);

        btnExitGame.addListener(new AliveButton.AliveButtonListener(){
            @Override
            public void onClick(){
                sound.play();
                Gdx.app.exit();
            }
        });


    }



    @Override
    public void hide() {
        this.inputHandler.unsubscribeAll();
    }
}
