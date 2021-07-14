package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Globals;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.Math.Polygon;
import sf.cartel.core.Visuals.FourDirSheet;
import sf.cartel.rendering.RenderPipeline;

public class TrainObject extends GameObject {
    private CatmullRomSpline<Vector2> trackSpline;
    private Sprite sprite;
    private float splineT;
    private float precision;
    private FourDirSheet fourDirSheet;

    TrainObject(String uuid) {
        super(uuid);
    }

    public void init(FourDirSheet fourDirSheet, Vector2[] trackPoints) {
        this.fourDirSheet = fourDirSheet;
        this.sprite = new Sprite(fourDirSheet.getSheet());

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
        splineT += precision * delta * 10;
        Vector2 destination = new Vector2();
        trackSpline.valueAt(destination, splineT);
        Vector2 oldPos = Sprites.position(sprite);

        Vector2 newPos = destination;
                //GoodMath.moveTowards(oldPos, destination, delta * 100);

        Vector2 dirVector = GoodMath.sub(newPos, oldPos).nor();
       float angle = GoodMath.angle(dirVector);

        sprite.setRegion(fourDirSheet.getRegion(angle));
        sprite.setPosition(newPos.x, newPos.y);
        if(splineT > 1f)
            splineT = 0;
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.add(sprite, Globals.DRAW_ORDER_WORLD + 10);
    }
}
