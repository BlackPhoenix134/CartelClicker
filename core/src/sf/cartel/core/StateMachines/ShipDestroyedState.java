package sf.cartel.core.StateMachines;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Globals;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.PathNode;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.core.clickHandler.ObjectClickBinding;
import sf.cartel.rendering.RenderPipeline;

public class ShipDestroyedState extends State<TravelStateMachine> {
    private ObjectClickBinding clickBinding;
    private AnimationController animationController;

    public ShipDestroyedState(TravelStateMachine stateMachine, ObjectClickBinding clickBinding, AnimationController animationController) {
        super(stateMachine);
        this.clickBinding = clickBinding;
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
        animationController.addDelta(delta);
        if(animationController.getCurrentFrameIdx() == animationController.getMaxAnimationIdx()) {
            clickBinding.unsubscribe();
            stateMachine.getGameObject().setAlive(false);
        }
    }


    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        stateMachine.getGameObject().getSprite().setRegion(animationController.getCurrentFrame());
        pipeline.add(stateMachine.getGameObject().getSprite(), Globals.DRAW_ORDER_WORLD + 2);
    }
}