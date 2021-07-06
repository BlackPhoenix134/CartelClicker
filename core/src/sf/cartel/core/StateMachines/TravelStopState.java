package sf.cartel.core.StateMachines;

import com.badlogic.gdx.graphics.Texture;

import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Globals;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.rendering.RenderPipeline;

public class TravelStopState extends State<TravelStateMachine> {
    private AnimationController animationController;
    private float deltaAccumulator = 0;

    public TravelStopState(TravelStateMachine stateMachine, AnimationController animationController) {
        super(stateMachine);
        this.animationController = animationController;
    }

    @Override
    public void transitionIn() {
        animationController.setFrameTo(0);
        Texture texture = animationController.getAnimSheet();
        Sprites.resetTexture(stateMachine.getGameObject().getSprite(), texture);
      deltaAccumulator = 0;
    }

    @Override
    public void transitionOut() {

    }

    @Override
    public void update(float delta) {
        animationController.addDelta(delta);


        deltaAccumulator += delta;
        if(deltaAccumulator >= 1.5f)
            stateMachine.getGameObject().getSprite().setScale(GoodMath.map(deltaAccumulator, 1.5f, 3, stateMachine.scaleMin, 0));
        if(deltaAccumulator >= 3f) {
            stateMachine.getGameObject().setAlive(false);
        }
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        stateMachine.getGameObject().getSprite().setRegion(animationController.getCurrentFrame());
        pipeline.add(stateMachine.getGameObject().getSprite(), Globals.DRAW_ORDER_WORLD + 2);
    }
}
