package sf.cartel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.CameraData;
import sf.cartel.input.InputHandler;
import sf.cartel.rendering.RenderPipeline;
import sf.cartel.ui.AliveButton;

public class UpgradeScreen extends AbstractScreen {
    private float              screenWidth, screenHeight;
    private AliveButton backButton;
    private RenderPipeline renderPipeline;
    private CameraData cameraData;
    private ScreenManager      screenManager;
    private InputHandler inputHandler;
    private SpriteBatch batch;

    Texture background = new Texture(Gdx.files.internal("ui/InGameMenu.png"));
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));


    public UpgradeScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager, InputHandler inputHandler) {
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

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        super.render(delta);
    }

    @Override
    public void buildStage() {
//        // TODO change padding to 0.25f and fix click boxes
//        float padding = screenHeight * 0.05f;
//
        Texture backButtonTexture = Assets.getAsset(AssetDescriptors.BUTTON_BACK);
//
//
//
        backButton = new AliveButton(backButtonTexture);
//
//
        Vector2 backButtonSize = Scaling.fillX.apply(backButtonTexture.getWidth(), backButtonTexture.getHeight(), screenWidth * 0.30f, 0);//
//
        backButton.setSize(backButtonSize.x, backButtonSize.y);
//
        backButton.setPosition( -screenWidth*0.075f, screenHeight*.8f);

        backButton.addListener(new AliveButton.AliveButtonListener() {
            @Override
            public void onClick() {
                sound.play();
                screenManager.showScreen(MainMenuScreen.class);
            }
        });
//
//        btnOptions.addListener(new AliveButton.AliveButtonListener(){
//            @Override
//            public void onClick(){
//                sound.play();
//                screenManager.showScreen(OptionsScreen.class);
//            }
//
//        });
//
        addActorsToStage(backButton);
//
//        btnExitGame.addListener(new AliveButton.AliveButtonListener(){
//            @Override
//            public void onClick(){
//                sound.play();
//                Gdx.app.exit();
//            }
//        });


    }



    @Override
    public void hide() {
        this.inputHandler.unsubscribeAll();
    }
}