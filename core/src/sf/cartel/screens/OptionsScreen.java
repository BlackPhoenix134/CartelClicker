package sf.cartel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Scaling;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.SYAssetManager;
import sf.cartel.rendering.RenderPipeline;
import sf.cartel.ui.AliveButton;


public class OptionsScreen extends AbstractScreen {
    private float screenWidth, screenHeight;
    private AliveButton btnJoin;
    private RenderPipeline renderPipeline;
    private OrthographicCamera camera;
    private ScreenManager screenManager;
    private SpriteBatch batch = new SpriteBatch();
    private TextField playerName;
    private TextField hostIP;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));
    private Skin textfieldSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));




    public OptionsScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager) {
        this.renderPipeline = renderPipeline;
        this.camera = camera;
        this.screenManager = screenManager;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        textfieldSkin.getFont("default-font").getData().setScale(2.75f);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void buildStage() {
        //Gdx.input.setInputProcessor(this);
        float padding = screenHeight * 0.05f;

        Texture optionsTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_OPTIONS);
        btnJoin = new AliveButton(optionsTexture);
        Vector2 btnJoinSize = Scaling.fillX.apply(optionsTexture.getWidth(), optionsTexture.getHeight(), screenWidth*0.30f,0);
        btnJoin.setSize(btnJoinSize.x, btnJoinSize.y);
        btnJoin.setPosition(screenWidth/2 - btnJoin.getWidth()/2, padding);

        btnJoin.addListener(new AliveButton.AliveButtonListener() {
            @Override
            public void onClick() {
                sound.play();
                screenManager.showScreen(GameScreen.class);
            }
        });

        addActorsToStage(btnJoin);

        hostIP = new TextField("",textfieldSkin);
        hostIP.setMessageText("Enter your IP");
        hostIP.setSize(screenWidth*0.3f, screenHeight*0.1f);
        hostIP.setPosition(screenWidth/2 - hostIP.getWidth()/2, screenHeight/2 + (hostIP.getHeight()*2));
        addActorsToStage(hostIP);

        playerName = new TextField("",textfieldSkin);
        playerName.setMessageText("Enter your name..");
        playerName.setSize(screenWidth *0.3f, screenHeight*0.1f);
        playerName.setPosition(screenWidth/2 - playerName.getWidth()/2, screenHeight/2);
        addActorsToStage(playerName);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render(delta);

    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);

    }
}




