package sf.cartel.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class ObjectClickInformation {
    private Clickable clickable;
    private int priority;
    private boolean isUiSpace;

    public ObjectClickInformation(Clickable clickable, int priority, boolean isUiSpace) {
        this.clickable = clickable;
        this.priority = priority;
        this.isUiSpace = isUiSpace;
    }

    public Clickable getClickable() {
        return clickable;
    }


    public int getPriority() {
        return priority;
    }

    public boolean isUiSpace() {
        return isUiSpace;
    }

    public boolean contains(float x, float y, OrthographicCamera camera) {
        Vector2 clickedPos = new Vector2(x, y);
        if(!isUiSpace) {
            Vector3 unprojectedPos = camera.unproject(new Vector3(x, y, 0));
            clickedPos.set(unprojectedPos.x, unprojectedPos.y);
        }
        return clickable.getBoundingBox().contains(clickedPos.x, clickedPos.y);
    }

    public boolean contains(Vector2 position, OrthographicCamera camera) {
        return contains(position.x, position.y, camera);
    }
}
