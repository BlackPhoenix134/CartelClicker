package sf.cartel.core.StateMachines;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Globals;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.PathNode;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.gameObjects.AnimatedSpriteDrawableObject;
import sf.cartel.gameObjects.ExplosionObject;
import sf.cartel.gameObjects.GameObjectManager;
import sf.cartel.gameObjects.SkyscraperObject;
import sf.cartel.gameObjects.StateMachineObject;

public class RoguePlaneStateMachine extends StateMachine {
    protected PathNode currPathNode;
    protected SkyscraperObject target;
    protected float travelSpeed = 100;
    protected float scaleMin = 1;
    protected float scaleMax = 1;
    protected GameObjectManager gameObjectManager;

    private StateMachineObject gameObject;

    public RoguePlaneStateMachine(StateMachineObject gameObject, GameObjectManager gameObjectManager, PathNode startNode, SkyscraperObject target, float scaleMin, float scaleMax) {
        this.gameObject = gameObject;
        this.gameObjectManager = gameObjectManager;
        this.scaleMin = scaleMin;
        this.scaleMax = scaleMax;
        this.currPathNode = startNode;
        this.target = target;
        gameObject.setSprite(new Sprite());
        gameObject.getSprite().setPosition(startNode.getPosition().x, startNode.getPosition().y);
        gameObject.getSprite().setRotation(GoodMath.sub(Sprites.position(target.getSprite()), currPathNode.getPosition()).angleDeg());
        gameObject.getSprite().setScale(scaleMin);
    }

    public void spawnExplosions() {
        for (int i = 0; i < 5; i++) {
            ExplosionObject explosion = gameObjectManager.create(ExplosionObject.class);
            explosion.init(new AnimationController(Assets.getAsset(AssetDescriptors.EXPLOSION1_SHEET), 5, 3, Globals.getRandomFloat(0.075f, 0.015f)));

            explosion.setDrawOrder(Globals.DRAW_ORDER_WORLD_OBJECTS + 2);
            explosion.getSprite().setScale(Globals.getRandomFloat(0.015f, 0.03f));
            Color color = explosion.getSprite().getColor();
            explosion.getSprite().setColor(color.r, color.g, color.b, 0.5f);

            int mult = 1;
            if(Globals.RANDOM.nextBoolean())
                mult = -1;
            explosion.getSprite().setRotation(Globals.getRandomFloat(0, 5) * mult);

            mult = 1;
            if(Globals.RANDOM.nextBoolean())
                mult = -1;



            float xOffset = mult * Globals.getRandomFloat(5, 50);
            float yOffset = mult * Globals.getRandomFloat(5, 50);
            explosion.getSprite().setPosition(
                    gameObject.getSprite().getX() + xOffset,
                    gameObject.getSprite().getY() + yOffset
            );

        }
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

    public SkyscraperObject getTarget() {
        return target;
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
