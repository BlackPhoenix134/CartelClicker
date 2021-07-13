package sf.cartel.gameObjects;

import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import sf.cartel.core.Math.Polygon;
import sf.cartel.rendering.RenderPipeline;

public class TrainTracksObject extends GameObject {
    private CatmullRomSpline<Vector2> trackSpline;

    TrainTracksObject(String uuid) {
        super(uuid);
    }

    private void init(Vector2[] trackPoints) {
        trackSpline = new CatmullRomSpline<>(trackPoints, true);
        int precisionOverLength = 5;
        float length = trackSpline.approxLength(trackPoints.length);
        float precision = precisionOverLength / length;

        for (float t = 0f; t <= 1f; t += precision ) {
            Vector2 point = new Vector2();
            trackSpline.valueAt(point, t);
        }

       // Polygon poly = new Polygon()

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {

    }
}
