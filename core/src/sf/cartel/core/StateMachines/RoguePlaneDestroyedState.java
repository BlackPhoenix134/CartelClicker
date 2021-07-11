package sf.cartel.core.StateMachines;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Globals;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.PathNode;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.gameObjects.SkyscraperObject;
import sf.cartel.rendering.RenderPipeline;

public class RoguePlaneDestroyedState extends State<RoguePlaneStateMachine> {
    private AnimationController animationController;
    private float destroyTimer = 0;
    private float downscaleTimer = 0;
    private float initialScaleX = 0;

    public RoguePlaneDestroyedState(RoguePlaneStateMachine stateMachine, AnimationController animationController) {
        super(stateMachine);
        this.animationController = animationController;
    }

    @Override
    public void transitionIn() {
        animationController.setFrameTo(0);
        Texture texture = animationController.getAnimSheet();
        Sprites.resetTexture(stateMachine.getGameObject().getSprite(), texture);

        initialScaleX = stateMachine.getGameObject().getSprite().getScaleX();
    }

    @Override
    public void transitionOut() {

    }

    @Override
    public void update(float delta) {
        downscaleTimer += delta;
        Sprite sprite = stateMachine.getGameObject().getSprite();
        float newScale = GoodMath.map(downscaleTimer, 0, 3, initialScaleX, 0.002f);
        sprite.setScale(newScale);

        if(animationController.getCurrentFrameIdx() != animationController.getMaxAnimationIdx())
            animationController.addDelta(delta);
        else {
            destroyTimer += delta;
            if(destroyTimer >= 3f)
                stateMachine.getGameObject().setAlive(false);
        }
    }


    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        stateMachine.getGameObject().getSprite().setRegion(animationController.getCurrentFrame());
        pipeline.add(stateMachine.getGameObject().getSprite(), Globals.DRAW_ORDER_WORLD + 2);
    }
}
