package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Clickable;
import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Globals;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.PathNode;
import sf.cartel.core.Physics.Area2D;
import sf.cartel.core.Physics.Rectangle;
import sf.cartel.input.InputEvent;
import sf.cartel.rendering.RenderPipeline;

public class StickmanObject extends GameObject {
    private Sprite sprite;
    private CatmullRomSpline<Vector2> trackSpline;
    private float splineT;
    private float precision;
    private Vector2[] trackPoints;
    private TrainStickmanEventObject trainStickmanEventObject;
    private GameObjectManager gameObjectManager;

    StickmanObject(String uuid) {
        super(uuid);
    }

    public void init(Sprite sprite, Vector2[] trackPoints, TrainStickmanEventObject trainStickmanEventObject, GameObjectManager gameObjectManager) {
        this.sprite = sprite;
        this.trackPoints = trackPoints;
        this.trainStickmanEventObject = trainStickmanEventObject;
        this.gameObjectManager = gameObjectManager;

        trackSpline = new CatmullRomSpline<>(trackPoints, true);
        int precisionOverLength = 5;
        float length = trackSpline.approxLength(trackPoints.length);
        precision = precisionOverLength / length;

        for (float t = 0f; t <= 1f; t += precision ) {
            Vector2 point = new Vector2();
            trackSpline.valueAt(point, t);
        }
        Vector2 point = new Vector2();
        trackSpline.valueAt(point, 0f);
        sprite.setPosition(point.x, point.y);
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void update(float delta) {
        splineT += precision * delta * 3;
        Vector2 destination = new Vector2();
        trackSpline.valueAt(destination, splineT);
        sprite.setPosition(destination.x, destination.y);
        if(splineT > 1f)
            splineT = 0;
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.add(sprite, Globals.DRAW_ORDER_WORLD + 6);
    }

    public Area2D getArea2D() {
        float scaledWidth = Sprites.scaledWidth(sprite);
        float scaledHeight = Sprites.scaledHeight(sprite);

        return new Rectangle(sprite.getX() - scaledWidth/2, sprite.getY() - scaledHeight/2,
                scaledWidth, scaledHeight);
    }

    public void kill() {
        if(isAlive()) {
            trainStickmanEventObject.returnSpline(this, trackPoints);
            BloodSplatter1Object splatter1Object = gameObjectManager.create(BloodSplatter1Object.class);
            splatter1Object.getSprite().setPosition(sprite.getX(), sprite.getY());
            splatter1Object.getSprite().setScale(0.05f);

            BloodSplatter2Object splatter2Object = gameObjectManager.create(BloodSplatter2Object.class);
            splatter2Object.getSprite().setPosition(sprite.getX(), sprite.getY());
            splatter2Object.getSprite().setScale(0.015f);

            this.setAlive(false);
        }
    }
}
