package sf.cartel.core.StateMachines;

import com.badlogic.gdx.graphics.Texture;

import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Globals;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.core.clickHandler.ObjectClickBinding;
import sf.cartel.rendering.RenderPipeline;

public class BuildingDestroyedState extends State<SkyscraperStateMachine> {
    private AnimationController animationController;
    private float destroyTimer = 0;

    public BuildingDestroyedState(SkyscraperStateMachine stateMachine, AnimationController animationController) {
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
        } else {
            destroyTimer += delta;
            if(destroyTimer >= 2f)
                stateMachine.getGameObject().setAlive(false);
        }
    }


    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        stateMachine.getGameObject().getSprite().setRegion(animationController.getCurrentFrame());
        pipeline.add(stateMachine.getGameObject().getSprite(), Globals.DRAW_ORDER_WORLD + 2);
    }
}