package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.List;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Gameplay;
import sf.cartel.core.PlayerData;
import sf.cartel.core.clickHandler.ObjectClickBinding;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.rendering.RenderPipeline;

public class DrugSelectorUiObject extends GameObject {
    private PlayerData playerData;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private int drawOrder;
    private List<ObjectClickBinding> objectClickBindings = new ArrayList<>();
    private ClickableSpriteDrawableObject[][] spriteDrawableObjects = new ClickableSpriteDrawableObject[5][3];
    private ObjectClickHandler objectClickHandler;
    private Gameplay gameplay;

    DrugSelectorUiObject(String uuid) {
        super(uuid);
    }

    public void init(PlayerData playerData, ObjectClickHandler objectClickHandler, Gameplay gameplay, Sprite backgroundSprite, int drawOrder) {
        this.playerData = playerData;
        this.objectClickHandler = objectClickHandler;
        this.drawOrder = drawOrder;
        this.gameplay = gameplay;


        createBackground(backgroundSprite);
        createSelector(backgroundSprite);

    }

    private void createBackground(Sprite backgroundSprite) {

        SpriteDrawableObject bg = gameObjectManager.create(SpriteDrawableObject.class);
        bg.setDrawOrder(drawOrder + 1);
        bg.setUiObject(true);

        Sprite bgTexture = new Sprite(Assets.getAsset(AssetDescriptors.UI_UPGRADE_SELECTOR_BACKGROUND));

        bgTexture.setScale(Gdx.graphics.getWidth() / bgTexture.getWidth() * 0.19f, Gdx.graphics.getHeight() / bgTexture.getHeight() * .8f);

        bg.setSprite(bgTexture);

        Sprite selectorBox = bg.getSprite();
        selectorBox.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.11f, Sprites.getScaledHeight(backgroundSprite) * 0.45f);


    }

    private void createSelector(Sprite backgroundSprite) {
        //TODO FOR EVERY DRUG IN UNLOCKED DRUGS/REGIONS

//        ClickableSpriteDrawableObject btn = createUpgradeButton(0, 0);
//        Sprite sprite = btn.getSprite();
//        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.12f, Sprites.getScaledHeight(backgroundSprite) * 0.80f);
//
//        createUpgradeClicks();
//        registerClickHandler();

    }


    private void createUpgradeClicks() {

    }

    private void registerClickHandler() {

    }

    @Override
    public void update(float delta) {
        gameObjectManager.update(delta);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);

    }

    @Override
    protected void onObjectDestroyed() {
        super.onObjectDestroyed();
        for (ObjectClickBinding binding : objectClickBindings) {
            binding.unsubscribe();
        }
        gameObjectManager.killAll();
    }
}
