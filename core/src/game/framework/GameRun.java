package game.framework;

import game.framework.assets.AssetsLoader;
import game.framework.gamescreens.GameScreen;
import com.badlogic.gdx.Game;

public class GameRun extends Game {

	@Override
	public void create() {
		// Debug Message
		System.out.println("Game Run - Game Created");
		
		AssetsLoader.instance.loadAssets();
		
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose() {
		// Debug Message
		System.out.println("Game Run - Dispose");
		
		super.dispose();
		
		AssetsLoader.instance.dispose();
	}
}
