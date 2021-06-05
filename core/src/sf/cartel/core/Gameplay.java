package sf.cartel.core;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Physics.Polygon;
import sf.cartel.gameObjects.ClickerObject;
import sf.cartel.gameObjects.GameObjectManager;
import sf.cartel.gameObjects.SpriteRenderObject;

public class Gameplay {
    private GameObjectManager gameObjectManager;
    private ObjectClickHandler objectClickHandler;
    private PlayerData playerData;

    public Gameplay(GameObjectManager gameObjectManager, ObjectClickHandler objectClickHandler, PlayerData playerData) {
        this.gameObjectManager = gameObjectManager;
        this.objectClickHandler = objectClickHandler;
        this.playerData = playerData;
    }


    public void initialize() {
        SpriteRenderObject mapObj = gameObjectManager.create(SpriteRenderObject.class);
        mapObj.setSprite(new Sprite(Assets.getAsset(AssetDescriptors.MAP)));
        mapObj.setDrawLayer(5);
        createClickerObject(createJamaycaPolygon(), AssetDescriptors.MAP_PART2);
       // createClickerObject(-266.06906f,119.20669f);
      //  createClickerObject(-104.98304f,97.85793f);
      //  createClickerObject(-211.07986f,42.86872f);
      //  createClickerObject(-11.824852f,7.287426f);
      //  createClickerObject(31.519592f,-150.56396f);
      //  createClickerObject(172.55075f,-239.84058f);
      //  createClickerObject(354.33862f,253.76854f);
    }

    private Polygon createJamaycaPolygon() {
        return new Polygon(    new Vector2(388.87363f, 235.94214f),


      new Vector2(355.67294f, 225.1507f),
       new Vector2(297.9081f, 257.81918f),
       new Vector2(304.29037f, 268.63657f),
       new Vector2(339.87955f, 269.93466f),
       new Vector2(374.17065f, 262.90338f),
       new Vector2(395.69724f, 251.76146f),
       new Vector2(396.34628f, 240.40321f),
       new Vector2(387.47604f, 233.69643f),
       new Vector2(371.24997f, 237.48251f)
);

    }

    private ClickerObject createClickerObject(Polygon polygon, AssetDescriptor<Texture> assetDescriptor) {
        ClickerObject obj = gameObjectManager.create(ClickerObject.class);
        obj.setPlayerData(playerData);
        obj.setArea2D(polygon);
        obj.setSprite(new Sprite(Assets.getAsset(assetDescriptor)));
        objectClickHandler.addTouchDownClickable(obj, 10, false);
        return obj;
    }
}
