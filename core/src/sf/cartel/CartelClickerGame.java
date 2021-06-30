package sf.cartel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import sf.cartel.assets.Assets;
import sf.cartel.assets.ShaderManager;
import sf.cartel.input.InputHandler;
import sf.cartel.rendering.RenderPipeline;
import sf.cartel.screens.GameScreen;
import sf.cartel.screens.MainMenuScreen;
import sf.cartel.screens.OptionsScreen;
import sf.cartel.screens.ScreenManager;
import sf.cartel.screens.UpgradeScreen;

public class CartelClickerGame extends Game {
	private ScreenManager screenManager;
	private RenderPipeline renderPipeline;
	private ExtendViewport viewport;
	private OrthographicCamera camera;
	private InputHandler inputHandler;


	@Override
	public void create() {
		Assets.loadAssets();
		inputHandler = new InputHandler();
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(5000, 3000, camera);
		renderPipeline = new RenderPipeline(new ShaderManager(), camera, viewport);
		screenManager = new ScreenManager(this);

		screenManager.addScreen(new MainMenuScreen(renderPipeline, camera, screenManager, inputHandler));
		screenManager.addScreen(new GameScreen(renderPipeline, camera, screenManager, inputHandler));
		screenManager.addScreen(new OptionsScreen(renderPipeline, camera, screenManager));
		screenManager.addScreen(new UpgradeScreen(renderPipeline, camera, screenManager, inputHandler));

		screenManager.showScreen(MainMenuScreen.class);
	}

	@Override
	public void dispose() {
		Assets.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		renderPipeline.updateBatchMatrix();
	}
}
