package sf.cartel.gameObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Globals;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.PathNode;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.rendering.RenderPipeline;


public class PlanePathFollowerObject extends GameObject {
    private enum PlaneMode { Start, Travel, End }

    private Sprite sprite;
    private AnimationController animationController;
    private PathNode currPathNode;
    private PathNode endPathNode;
    private float travelSpeed = 100;
    private float scaleMin = 1;
    private float scaleMax = 1;

    private float initialDistance;

    private PlaneMode planeMode = PlaneMode.Start;
    private float deltaAccumulator = 0;


    public PlanePathFollowerObject(String uuid){
        super(uuid);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getScaleMin() {
        return scaleMin;
    }

    public void setScaleMin(float scaleMin) {
        this.scaleMin = scaleMin;
    }

    public float getScaleMax() {
        return scaleMax;
    }

    public void setScaleMax(float scaleMax) {
        this.scaleMax = scaleMax;
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

        initialDistance = Vector2.dst(startNode.getPosition().x, startNode.getPosition().y,
                endPathNode.getPosition().x, endPathNode.getPosition().y);

        sprite.setRotation(GoodMath.sub(endPathNode.getPosition(), currPathNode.getPosition()).angleDeg());
        sprite.setScale(scaleMin);
    }


    private void setPath(PathNode node) {
        currPathNode = node;
        endPathNode = getNextPathNode(currPathNode);
    }

    @Override
    public void update(float delta) {
        animationController.addDelta(delta);

        switch (planeMode) {
            case Start:
                deltaAccumulator += delta;
                if(deltaAccumulator <= 1.5f)
                    sprite.setScale(GoodMath.map(deltaAccumulator, 0f, 1.5f, 0, scaleMin));
                if(deltaAccumulator >= 3f) {
                    deltaAccumulator = 0;
                    planeMode = PlaneMode.Travel;
                }
                break;
            case Travel:
                if(currPathNode != null && endPathNode != null) {
                    Vector2 currentPos = new Vector2(sprite.getX(), sprite.getY());
                    Vector2 newPos = GoodMath.moveTowards(currentPos, endPathNode.getPosition(), delta * travelSpeed);
                    sprite.setPosition(newPos.x, newPos.y);

                    float distanceToTarget = Vector2.dst(sprite.getX(), sprite.getY(),
                            endPathNode.getPosition().x, endPathNode.getPosition().y);
                    float distancePercent = distanceToTarget / initialDistance;

                    float newScale;
                    if(distancePercent < 0.2f)
                        newScale = GoodMath.map(distancePercent, 0, 0.2f, scaleMin, scaleMax);
                    else if(distancePercent > 0.8f)
                        newScale = GoodMath.map(distancePercent, 0.8f, 1f, scaleMax, scaleMin);
                    else
                        newScale = scaleMax;
                    sprite.setScale(newScale);
                    if(GoodMath.equals(newPos, endPathNode.getPosition(), 5)) {
                        deltaAccumulator = 0;
                        planeMode = PlaneMode.End;
                    }
                }
                break;
            case End:
                deltaAccumulator += delta;
                if(deltaAccumulator >= 1.5f)
                     sprite.setScale(GoodMath.map(deltaAccumulator, 1.5f, 3, scaleMin, 0));
                if(deltaAccumulator >= 3f) {
                    deltaAccumulator = 0;
                    setAlive(false);
                }
                break;
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
