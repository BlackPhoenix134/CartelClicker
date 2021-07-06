package sf.cartel.core.StateMachines;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Globals;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.PathNode;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.rendering.RenderPipeline;
import sun.security.jgss.GSSCaller;

public class ShipTravelState extends State<TravelStateMachine> {
    private AnimationController animationController;
    private PathNode firstNode;

    public ShipTravelState(TravelStateMachine stateMachine, AnimationController animationController) {
        super(stateMachine);
        this.animationController = animationController;
    }

    @Override
    public void transitionIn() {
        animationController.setFrameTo(0);
        Texture texture = animationController.getAnimSheet();
        Sprites.resetTexture(stateMachine.getGameObject().getSprite(), texture);
        stateMachine.getGameObject().getSprite().setScale(stateMachine.scaleMax);
        stateMachine.getGameObject().getSprite().setRotation(0);
        stateMachine.travelSpeed = 25;
        firstNode = stateMachine.currPathNode;
    }

    @Override
    public void transitionOut() {

    }

    @Override
    public void update(float delta) {
        animationController.addDelta(delta);
        Sprite sprite = stateMachine.getGameObject().getSprite();

        Vector2 currentPos = new Vector2(sprite.getX(), sprite.getY());
        Vector2 newPos = GoodMath.moveTowards(currentPos, stateMachine.nextPathNode.getPosition(), delta * stateMachine.travelSpeed);
        sprite.setPosition(newPos.x, newPos.y);

        if(newPos.x < stateMachine.nextPathNode.getPosition().x)
            sprite.flip(false, false);
        else
            sprite.flip(true, false);


        float distanceToFirstNode = Vector2.dst(sprite.getX(), sprite.getY(),
                firstNode.getPosition().x, firstNode.getPosition().y);
        float distanceToNextNode = Vector2.dst(sprite.getX(), sprite.getY(),
                stateMachine.nextPathNode.getPosition().x, stateMachine.nextPathNode.getPosition().y);

        float newScale = stateMachine.scaleMax;
        if(distanceToFirstNode < 60)
            newScale = GoodMath.map(distanceToFirstNode, 0, 60, stateMachine.scaleMin, stateMachine.scaleMax);
        else if(!stateMachine.nextPathNode.hasNextNode() && distanceToNextNode < 60)
            newScale = GoodMath.map(distanceToNextNode, 0, 60, stateMachine.scaleMin, stateMachine.scaleMax);
        stateMachine.getGameObject().getSprite().setScale(newScale);

        if(GoodMath.equals(newPos, stateMachine.nextPathNode.getPosition(), 0.005f)) {
            if(stateMachine.nextPathNode.hasNextNode()) {
                stateMachine.currPathNode = stateMachine.nextPathNode;
                stateMachine.nextPathNode = stateMachine.currPathNode.getRandomNextNode();
            } else {
                stateMachine.getGameObject().setAlive(false);
            }
        }
    }


    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        stateMachine.getGameObject().getSprite().setRegion(animationController.getCurrentFrame());
        pipeline.add(stateMachine.getGameObject().getSprite(), Globals.DRAW_ORDER_WORLD + 2);
    }
}