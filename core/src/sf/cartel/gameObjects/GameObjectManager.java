package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import sf.cartel.rendering.RenderPipeline;


public class GameObjectManager {
    private Map<String, GameObject> gameObjects = new HashMap<>();
    private List<GameObject> deadObjects = new ArrayList<>();
    private List<GameObject> objectsToSpawn = new ArrayList<>();

    public Collection<GameObject> getGameObjects() {
        return gameObjects.values();
    }

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
        for(GameObject obj : objectsToSpawn) {
            gameObjects.put(obj.getUuid(), obj);
        }
        objectsToSpawn.clear();
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
            //gameObjects.put(obj.getUuid(), obj);
            objectsToSpawn.add(obj);
            return (T) obj;
        } catch (Exception _) {
            _.printStackTrace();
            Gdx.app.exit();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends GameObject> T get(String uuid) {
        return (T)gameObjects.get(uuid);
    }

    public void killAll() {
        for (GameObject obj: gameObjects.values()) {
            obj.setAlive(false);
        }
    }
}
