package sf.cartel.core.StateMachines;


import com.badlogic.gdx.graphics.g2d.Sprite;

import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.PathNode;
import sf.cartel.gameObjects.StateMachineObject;

public class PathObjectStateMachine extends StateMachine {
    protected PathNode currPathNode;
    protected PathNode endPathNode;
    protected float travelSpeed = 100;
    protected float scaleMin = 1;
    protected float scaleMax = 1;

    private StateMachineObject gameObject;

    public PathObjectStateMachine(StateMachineObject gameObject, PathNode startNode, float scaleMin, float scaleMax) {
        this.gameObject = gameObject;
        this.scaleMin = scaleMin;
        this.scaleMax = scaleMax;
        currPathNode = startNode;
        endPathNode = currPathNode.getRandomNextNode();
        gameObject.setSprite(new Sprite());
        gameObject.getSprite().setPosition(startNode.getPosition().x, startNode.getPosition().y);
        gameObject.getSprite().setRotation(GoodMath.sub(endPathNode.getPosition(), currPathNode.getPosition()).angleDeg());
        gameObject.getSprite().setScale(scaleMin);
    }

    public StateMachineObject getGameObject() {
        return gameObject;
    }
    public void setGameObject(StateMachineObject gameObject) {
        this.gameObject = gameObject;
    }

    public PathNode getCurrPathNode() {
        return currPathNode;
    }

    public PathNode getEndPathNode() {
        return endPathNode;
    }

    public float getTravelSpeed() {
        return travelSpeed;
    }

    public float getScaleMin() {
        return scaleMin;
    }

    public float getScaleMax() {
        return scaleMax;
    }

    public void init() {

    }
}
