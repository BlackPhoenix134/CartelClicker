package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.Text;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Consumer;
import sf.cartel.core.DrugType;
import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Gameplay;
import sf.cartel.core.Globals;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.PlayerData;
import sf.cartel.core.clickHandler.ObjectClickBinding;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.rendering.RenderPipeline;

public class DrugSelectorUiObject extends GameObject {
    private PlayerData playerData;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private int drawOrder;
    private List<ObjectClickBinding> objectClickBindings = new ArrayList<>();
    private ObjectClickHandler objectClickHandler;
    private Gameplay gameplay;
    private Consumer<DrugType> onDrugSelected;

    DrugSelectorUiObject(String uuid) {
        super(uuid);
    }

    public void init(PlayerData playerData, ObjectClickHandler objectClickHandler, Gameplay gameplay, Sprite dialogBackground, int drawOrder, Consumer<DrugType> onDrugSelected) {
        this.playerData = playerData;
        this.objectClickHandler = objectClickHandler;
        this.drawOrder = drawOrder;
        this.gameplay = gameplay;
        this.onDrugSelected = onDrugSelected;

        Sprite drugSelectorBackground = createBackground(dialogBackground);
        createSelectors(drugSelectorBackground);
    }

    private Sprite createBackground(Sprite dialogBackground) {
        SpriteDrawableObject bgObject = gameObjectManager.create(SpriteDrawableObject.class);
        bgObject.setDrawOrder(drawOrder + 1);
        bgObject.setUiObject(true);
        Sprite bgSprite = new Sprite(Assets.getAsset(AssetDescriptors.UI_UPGRADE_SELECTOR_BACKGROUND));
        bgSprite.setScale(dialogBackground.getWidth() / bgSprite.getWidth() * 0.19f, dialogBackground.getHeight() / bgSprite.getHeight() * .8f);
        bgObject.setSprite(bgSprite);
        bgSprite.setPosition(Sprites.getScaledWidth(dialogBackground) * 0.11f, Sprites.getScaledHeight(dialogBackground) * 0.45f);
        return bgSprite;
    }

    private void createSelectors(Sprite backgroundSprite) {
        float offset = 5;
        float offsetHeight = offset; // TODO why does this change/bug the background??! (if set to  offset*10f)
        float boxSpacingRatio = (backgroundSprite.getWidth() / 2) - offset * 2;

        ClickableSpriteDrawableObject obj = createSelectorObject(Assets.getAsset(AssetDescriptors.ICON_WEED));
        Sprite sprite = obj.getSprite();
        sprite.setScale(GoodMath.ratio(sprite.getWidth(), boxSpacingRatio));
        sprite.setPosition(
                backgroundSprite.getX() - offset - Sprites.getScaledWidth(sprite) / 2,
                backgroundSprite.getY() + Sprites.getScaledHeight(backgroundSprite) / 2
                        - offsetHeight - Sprites.getScaledHeight(sprite) / 2);
        obj.setOnClicked(clickedObj -> onDrugSelected.call(DrugType.Weed));

        obj = createSelectorObject(Assets.getAsset(AssetDescriptors.ICON_PILLS));
        sprite = obj.getSprite();
        sprite.setScale(GoodMath.ratio(sprite.getWidth(), boxSpacingRatio));
        sprite.setPosition(
                backgroundSprite.getX() + offset + Sprites.getScaledWidth(sprite) / 2,
                backgroundSprite.getY() + Sprites.getScaledHeight(backgroundSprite) / 2
                        - offsetHeight - Sprites.getScaledHeight(sprite) / 2);
        obj.setOnClicked(clickedObj -> onDrugSelected.call(DrugType.Pills));

        obj = createSelectorObject(Assets.getAsset(AssetDescriptors.ICON_COKE));
        sprite = obj.getSprite();
        sprite.setScale(GoodMath.ratio(sprite.getWidth(), boxSpacingRatio));
        sprite.setPosition(
                backgroundSprite.getX() - offset - Sprites.getScaledWidth(sprite) / 2,
                backgroundSprite.getY());
        obj.setOnClicked(clickedObj -> onDrugSelected.call(DrugType.Coke));

        obj = createSelectorObject(Assets.getAsset(AssetDescriptors.ICON_OXY));
        sprite = obj.getSprite();
        sprite.setScale(GoodMath.ratio(sprite.getWidth(), boxSpacingRatio));
        sprite.setPosition(
                backgroundSprite.getX() + offset + Sprites.getScaledWidth(sprite) / 2,
                backgroundSprite.getY());
        obj.setOnClicked(clickedObj -> onDrugSelected.call(DrugType.Oxy));

        obj = createSelectorObject(Assets.getAsset(AssetDescriptors.ICON_HEROIN));
        sprite = obj.getSprite();
        sprite.setScale(GoodMath.ratio(sprite.getWidth(), boxSpacingRatio));
        sprite.setPosition(
                backgroundSprite.getX() - offset - Sprites.getScaledWidth(sprite) / 2,
                backgroundSprite.getY() - Sprites.getScaledHeight(backgroundSprite) / 2
                        + offsetHeight + Sprites.getScaledHeight(sprite) / 2);
        obj.setOnClicked(clickedObj -> onDrugSelected.call(DrugType.Heroin));
    }

    private ClickableSpriteDrawableObject createSelectorObject(Texture texture) {
        ClickableSpriteDrawableObject obj = gameObjectManager.create(ClickableSpriteDrawableObject.class);
        obj.setDrawOrder(drawOrder + 2);
        obj.setUiObject(true);
        Sprite sprite = new Sprite(texture);
        obj.setSprite(sprite);
        objectClickBindings.add(objectClickHandler.addTouchUpClickable(obj, Globals.CLICK_ORDER_UI_DIALOG + 100, true));
        return obj;
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
