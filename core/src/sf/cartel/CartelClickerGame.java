package sf.cartel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import sf.cartel.assets.SYAssetManager;
import sf.cartel.assets.ShaderManager;
import sf.cartel.rendering.RenderPipeline;
import sf.cartel.screens.GameScreen;
import sf.cartel.screens.MainMenuScreen;
import sf.cartel.screens.ScreenManager;

public class CartelClickerGame extends Game {
	private ScreenManager screenManager;
	private RenderPipeline renderPipeline;
	private ExtendViewport viewport;
	private OrthographicCamera camera;

	@Override
	public void create() {
		SYAssetManager.loadAssets();
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(5000, 3000, camera);
		renderPipeline = new RenderPipeline(new SpriteBatch(), new ShaderManager(), camera, viewport);
		screenManager = new ScreenManager(this);
		screenManager.addScreen(new MainMenuScreen(renderPipeline, camera, screenManager));
		screenManager.addScreen(new GameScreen(renderPipeline, camera, screenManager));
		screenManager.showScreen(MainMenuScreen.class);
	}

	@Override
	public void dispose() {
		SYAssetManager.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		renderPipeline.updateBatchMatrix();
	}
}
