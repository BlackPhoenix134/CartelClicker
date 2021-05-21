package sf.cartel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import sf.cartel.assets.Assets;

public abstract class AbstractScreen extends Stage implements Screen {
    private Image transitionImg;
    private float screenTransitionSpeed = .45f;

    protected AbstractScreen() {
        // super( new StretchViewport(800, 600, new OrthographicCamera()) );
        transitionImg = new Image(Assets.solid1x1);
        transitionImg.setColor(Color.BLACK);
        transitionImg.setVisible(false);

        addActor(transitionImg);
    }

    public void addActorsToStage(Actor...actors){
        for(Actor actor: actors){
            addActor(actor);
        }
    }

    public void transitionIn(){
        transitionImg.toFront();
        transitionImg.setVisible(true);
        transitionImg.addAction(Actions.sequence(
                Actions.fadeOut(screenTransitionSpeed),
                Actions.visible(false)
        ));
    }

    public void transitionOut(Runnable onFinishOutTransition){
        transitionImg.toFront();
        transitionImg.setVisible(true);
        transitionImg.getColor().a = 0;
        transitionImg.addAction(Actions.sequence(
                Actions.fadeIn(screenTransitionSpeed),
                Actions.run(onFinishOutTransition)
        ));
    }

    // Subclasses must load actors in this method
    public abstract void buildStage();

    @Override
    public void render(float delta) {
        super.act(delta);
        super.draw();
        // Clear screen
        /*Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Calling to Stage methods
        super.act(delta);
        super.draw();*/
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height, true);
        transitionImg.setSize(width, height);
    }

    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
}

