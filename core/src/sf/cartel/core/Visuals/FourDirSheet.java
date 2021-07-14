package sf.cartel.core.Visuals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FourDirSheet {
    Texture sheet;
    TextureRegion[] splitSheet;
    private int currIdx = 0;

    public FourDirSheet(Texture animSheet) {
        this.sheet = animSheet;
        int cols = 2;
        int rows = 2;

        TextureRegion[][] tmp = TextureRegion.split(animSheet,
                animSheet.getWidth() / cols,
                animSheet.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        splitSheet = frames;
    }

    public TextureRegion getRegion(float angle) {
        if(angle < 0)
            angle += 360;

        if( angle >= 90 && angle < 180)
            return splitSheet[0];
        if(angle >= 0 && angle < 90)
            return splitSheet[1];
        if(angle >= 180 && angle < 270)
            return splitSheet[2];
        return splitSheet[3];
    }

    public Texture getSheet() {
        return sheet;
    }

}
