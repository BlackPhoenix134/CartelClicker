package sf.cartel.core.StateMachines;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Globals;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.core.clickHandler.ObjectClickBinding;
import sf.cartel.rendering.RenderPipeline;

public class BuildingBuildState extends State<SkyscraperStateMachine> {
    private AnimationController animationController;

    public BuildingBuildState(SkyscraperStateMachine stateMachine, AnimationController animationController) {
        super(stateMachine);
        this.animationController = animationController;
    }

    @Override
    public void transitionIn() {
        animationController.setFrameTo(0);
        Texture texture = animationController.getAnimSheet();
        Sprites.resetTexture(stateMachine.getGameObject().getSprite(), texture);
    }

    @Override
    public void transitionOut() {

    }

    @Override
    public void update(float delta) {
        if(animationController.getCurrentFrameIdx() != animationController.getMaxAnimationIdx()) {
            animationController.addDelta(delta);
        }
    }


    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        stateMachine.getGameObject().getSprite().setRegion(animationController.getCurrentFrame());
        pipeline.add(stateMachine.getGameObject().getSprite(), Globals.DRAW_ORDER_WORLD + 2);
    }
}