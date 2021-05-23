package sf.cartel.core;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sf.cartel.input.InputEvent;
import sf.cartel.input.InputEventType;
import sf.cartel.input.InputHandler;

//ToDo: needs a QuadTree here
public class ObjectClickHandler {
    private final int objectClickPriority = 50;
    private List<ObjectClickInformation> touchDownClickables = new ArrayList<>();
    private List<ObjectClickInformation> touchUpClickables = new ArrayList<>();
    private InputHandler inputHandler;
    private CameraData cameraData;
    private ObjectClickInformationComparator objectClickInformationComparator = new ObjectClickInformationComparator();

    public ObjectClickHandler(CameraData cameraData, InputHandler inputHandler) {
        this.cameraData = cameraData;
        this.inputHandler = inputHandler;
    }

    public void subscribeEvents() {
        this.inputHandler.addListener(InputEventType.TOUCH_DOWN, objectClickPriority, new Consumer<InputEvent>() {
            @Override
            public void call(InputEvent inputEvent) {
                invokeClicked(touchDownClickables, inputEvent);
            }
        });
        this.inputHandler.addListener(InputEventType.TOUCH_UP, objectClickPriority, new Consumer<InputEvent>() {
            @Override
            public void call(InputEvent inputEvent) {
                invokeClicked(touchUpClickables, inputEvent);
            }
        });
    }

    public void addTouchDownClickable(Clickable clickable, Rectangle boundingBox, int priority, boolean isUiSpace) {
        touchDownClickables.add(new ObjectClickInformation(clickable, boundingBox, priority, isUiSpace));
        Collections.sort(touchDownClickables, objectClickInformationComparator);
    }

    public void addTouchUpClickable(Clickable clickable, Rectangle boundingBox, int priority, boolean isUiSpace) {
        touchUpClickables.add(new ObjectClickInformation(clickable, boundingBox, priority, isUiSpace));
        Collections.sort(touchUpClickables, objectClickInformationComparator);
    }

    private void invokeClicked(List<ObjectClickInformation> clickables, InputEvent inputEvent) {
        int idx = 0;
        while(idx < clickables.size() && !inputEvent.isConsumed()) {
            clickables.get(idx).getClickable().onClicked(inputEvent);
            idx++;
        }
    }

    private static class ObjectClickInformationComparator implements Comparator<ObjectClickInformation> {
        @Override
        public int compare(ObjectClickInformation t1, ObjectClickInformation t2) {
            return Integer.compare(t1.getPriority(), t2.getPriority());
        }
    }
}
