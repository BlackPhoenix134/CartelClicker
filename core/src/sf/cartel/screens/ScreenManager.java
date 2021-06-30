package sf.cartel.screens;

import com.badlogic.gdx.Game;

import java.util.HashMap;
import java.util.Map;

// TODO SAVE PREVIOUS SCREEN IN ORDER TO GO BACK EASILY

public class ScreenManager {
    private Game game;
    private Map<Class, AbstractScreen> screens = new HashMap<>();

    public ScreenManager(Game game) {
        this. game = game;
    }

    public void addScreen(Class screenClass, AbstractScreen screen) {
        screens.put(screenClass, screen);
    }

    public void addScreen(AbstractScreen screen) {
        screens.put(screen.getClass(), screen);
    }

    public void showScreen(final Class screenClass) {
        final AbstractScreen currentScreen = (AbstractScreen) game.getScreen();
        final AbstractScreen newScreen = screens.get(screenClass);
        newScreen.buildStage();

        game.setScreen(newScreen);

//        if(currentScreen != null){
//            currentScreen.transitionOut(new Runnable(){
//                @Override
//                public void run() {
//                    game.setScreen(newScreen);
//                    newScreen.transitionIn();
//                    currentScreen.dispose();
//                }
//            });
//        }
//        else{
//            game.setScreen(newScreen);
//            newScreen.transitionIn();
//        }
    }


}
