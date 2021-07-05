package sf.cartel.gameObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Globals;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.PathNode;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.rendering.RenderPipeline;


public class PathFollowerObject extends GameObject {
    private Sprite sprite;
    private AnimationController animationController;
    private PathNode currPathNode;
    private PathNode nextPathNode;
    private float travelSpeed = 100;

    public PathFollowerObject(String uuid){
        super(uuid);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getTravelSpeed() {
        return travelSpeed;
    }

    public void setTravelSpeed(float travelSpeed) {
        this.travelSpeed = travelSpeed;
    }

    public void init(Texture texture, AnimationController animationController, PathNode startNode) {
        this.sprite = new Sprite(texture);
        this.animationController = animationController;
        setPath(startNode);
        sprite.setPosition(startNode.getPosition().x, startNode.getPosition().y);
    }

    private void setPath(PathNode node) {
        currPathNode = node;
        nextPathNode = getNextPathNode(currPathNode);
    }

    @Override
    public void update(float delta) {
        animationController.addDelta(delta);
        if(currPathNode != null && nextPathNode != null) {
            Vector2 currentPos = new Vector2(sprite.getX(), sprite.getY());
            Vector2 newPos = GoodMath.moveTowards(currentPos, nextPathNode.getPosition(), delta * travelSpeed);
            sprite.setPosition(newPos.x, newPos.y);
            if(GoodMath.equals(newPos, nextPathNode.getPosition(), 50)) {
                currPathNode = nextPathNode;
                nextPathNode = null;
                if(currPathNode.hasNextNode())
                    nextPathNode = currPathNode.getRandomNextNode();
                else
                   setAlive(false);
            }
        }
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        sprite.setRegion(animationController.getCurrentFrame());
        pipeline.add(sprite, Globals.DRAW_ORDER_WORLD + 2);
    }

    private PathNode getNextPathNode(PathNode node) {
        return node.getRandomNextNode();
    }
}
