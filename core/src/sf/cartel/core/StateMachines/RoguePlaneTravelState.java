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

public class RoguePlaneTravelState extends State<RoguePlaneStateMachine> {
    private AnimationController animationController;
    private float initialDistance;

    public RoguePlaneTravelState(RoguePlaneStateMachine stateMachine, AnimationController animationController) {
        super(stateMachine);
        this.animationController = animationController;

        PathNode currNode = stateMachine.getCurrPathNode();
        SkyscraperObject target = stateMachine.getTarget();

        initialDistance = Vector2.dst(currNode.getPosition().x, currNode.getPosition().y,
                target.getSprite().getX(),    target.getSprite().getY());
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
        Sprite sprite = stateMachine.getGameObject().getSprite();
        Vector2 targetPos = Sprites.position(stateMachine.getTarget().getSprite());

        Vector2 currentPos = new Vector2(sprite.getX(), sprite.getY());
        Vector2 newPos = GoodMath.moveTowards(currentPos, targetPos, delta * stateMachine.travelSpeed);
        sprite.setPosition(newPos.x, newPos.y);

        float distanceToTarget = Vector2.dst(sprite.getX(), sprite.getY(), targetPos.x, targetPos.y);
        float distancePercent = distanceToTarget / initialDistance;

        float newScale;
        if(distancePercent < 0.2f)
            newScale = GoodMath.map(distancePercent, 0, 0.2f, stateMachine.scaleMin, stateMachine.scaleMax);
        else
            newScale = stateMachine.scaleMax;
        sprite.setScale(newScale);
        if(GoodMath.equals(newPos, targetPos, 5)) {
            stateMachine.getTarget().destroy();
            stateMachine.spawnExplosions();
            stateMachine.transition(StateMachineStates.RoguePlaneDestroyed.ordinal());
        }
    }


    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        stateMachine.getGameObject().getSprite().setRegion(animationController.getCurrentFrame());
        pipeline.add(stateMachine.getGameObject().getSprite(), Globals.DRAW_ORDER_WORLD_OBJECTS + 1);
    }
}
