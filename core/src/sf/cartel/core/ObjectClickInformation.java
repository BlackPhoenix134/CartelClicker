package sf.cartel.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class ObjectClickInformation {
    private Clickable clickable;
    private Rectangle boundingBox;
    private int priority;
    private boolean isUiSpace;

    public ObjectClickInformation(Clickable clickable, Rectangle boundingBox, int priority, boolean isUiSpace) {
        this.clickable = clickable;
        this.boundingBox = boundingBox;
        this.priority = priority;
        this.isUiSpace = isUiSpace;
    }

    public Clickable getClickable() {
        return clickable;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isUiSpace() {
        return isUiSpace;
    }

    private boolean contains(Vector2 position, OrthographicCamera camera) {
        Vector2 clickedPos = new Vector2();
        if(!isUiSpace) {
            Vector3 unprojectedPos = camera.unproject(new Vector3(position.x, position.y, 0));
            clickedPos.set(unprojectedPos.x, unprojectedPos.y);
        }
        Vector2 rebasedPos = clickedPos.sub(clickable.getPosition());
        return boundingBox.contains(rebasedPos.x, rebasedPos.y);
    }
}
