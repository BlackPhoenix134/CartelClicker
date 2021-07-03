package sf.cartel.gameObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import sf.cartel.rendering.RenderPipeline;


public class GameObjectManager {
    private Map<String, GameObject> gameObjects = new HashMap<>();
    private List<GameObject> deadObjects = new ArrayList<>();

    public void update(float delta) {
        for(GameObject obj : gameObjects.values()) {
            if(obj.isAlive())
                obj.update(delta);
            else
                deadObjects.add(obj);
        }

        postUpdate();
    }

    private void postUpdate() {
        for(GameObject obj : deadObjects) {
            gameObjects.remove(obj.getUuid());
            obj.onObjectDestroyed();
        }
        deadObjects.clear();
    }

    public void draw(float delta, RenderPipeline pipeline) {
        for(GameObject obj : gameObjects.values())
            obj.draw(delta, pipeline);
    }

    public <T extends GameObject> T create(Class<T> clazz) {
        String uniqueID = UUID.randomUUID().toString();
        //ToDo: proper exception handling
        try {
            GameObject obj = clazz.getDeclaredConstructor(String.class).newInstance(uniqueID);
            gameObjects.put(obj.getUuid(), obj);
            return (T) obj;
        } catch (Exception _) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends GameObject> T get(String uuid) {
        return (T)gameObjects.get(uuid);
    }
}
