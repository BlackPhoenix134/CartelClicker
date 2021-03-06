package sf.cartel.core;

import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Physics.Area2D;
import sf.cartel.input.InputEvent;

public interface Clickable {
    void onClicked(InputEvent inputEvent);
    Vector2 getPosition();
    Area2D getArea2D();
}
