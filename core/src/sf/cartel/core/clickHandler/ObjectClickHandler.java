package sf.cartel.core.clickHandler;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sf.cartel.core.CameraData;
import sf.cartel.core.Clickable;
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
    private int subscribeIdCounter = 0;

    public ObjectClickHandler(CameraData cameraData, InputHandler inputHandler) {
        this.cameraData = cameraData;
        this.inputHandler = inputHandler;
    }

    public void subscribeEvents() {
        this.inputHandler.addListener(InputEventType.TOUCH_DOWN, objectClickPriority,
                inputEvent -> invokeClicked(touchDownClickables, inputEvent));
        this.inputHandler.addListener(InputEventType.TOUCH_UP, objectClickPriority,
                inputEvent -> invokeClicked(touchUpClickables, inputEvent));
    }

    public ObjectClickBinding addTouchDownClickable(Clickable clickable, int priority, boolean isUiSpace) {
        int subId = getNextSubscribeId();
        return addClickable(new ObjectClickInformation(subId, clickable, priority, isUiSpace), touchDownClickables);
    }

    public ObjectClickBinding addTouchUpClickable(Clickable clickable, int priority, boolean isUiSpace) {
        int subId = getNextSubscribeId();
        return addClickable(new ObjectClickInformation(subId, clickable, priority, isUiSpace), touchUpClickables);
    }

    private int getNextSubscribeId() {
        subscribeIdCounter++;
        return subscribeIdCounter;
    }

    private ObjectClickBinding addClickable(ObjectClickInformation information, List<ObjectClickInformation> collection) {
        collection.add(information);
        Collections.sort(collection, objectClickInformationComparator);
        return new ObjectClickBinding(information, this);
    }

    private void invokeClicked(List<ObjectClickInformation> objectClickInfos, InputEvent inputEvent) {
        int idx = 0;
        Gdx.app.log("SEXY", "clicked at " + inputEvent.getX1() + " " + inputEvent.getX2());
        while(idx < objectClickInfos.size() && !inputEvent.isConsumed()) {
            ObjectClickInformation currInfo = objectClickInfos.get(idx);
            if(currInfo.contains(inputEvent, cameraData.getOrthographicCamera()))
                 currInfo.getClickable().onClicked(inputEvent);
            idx++;
        }
    }

    public void unsubscribe(ObjectClickBinding objectClickBinding) {
        touchDownClickables.remove(objectClickBinding.getObjectClickInformation());
        touchUpClickables.remove(objectClickBinding.getObjectClickInformation());
    }

    private static class ObjectClickInformationComparator implements Comparator<ObjectClickInformation> {
        @Override
        public int compare(ObjectClickInformation t1, ObjectClickInformation t2) {
            return Integer.compare(t2.getPriority(), t1.getPriority());
        }
    }
}
