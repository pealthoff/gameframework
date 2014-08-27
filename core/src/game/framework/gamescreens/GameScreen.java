package game.framework.gamescreens;

import game.framework.gamerenderer.CameraHelper;
import game.framework.gamerenderer.GameRenderer;
import game.framework.layers.input.InputHelper;
import game.framework.layers.interaction.InteractionHelper;
import game.framework.layers.interaction.InteractionView;
import game.framework.layers.options.Options;
import game.framework.layers.options.OptionsHelper;
import game.framework.layers.options.OptionsView;
import game.framework.layers.world.GameWorld;
import game.framework.layers.world.WorldHelper;
import game.framework.layers.world.WorldView;
import game.framework.player.Player;
import game.framework.player.PlayerHelper;
import game.framework.utils.Constants;
import game.framework.view.ViewArray;
import game.framework.view.View.ViewType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {

	private Player player;
	private PlayerHelper playerHelper;
	
	private CameraHelper cameraHelper;
	
	private ViewArray viewArray;
	
	private GameWorld gameWorld;
	private WorldView gameWorldView;
	private WorldHelper gameWorldHelper;
	
	private Options options;
	private OptionsView optionsView;
	private OptionsHelper optionsHelper;
	
	private InteractionView interactionView;
	private InteractionHelper interactionHelper;
	
	private InputHelper inputHelper;
	
	private GameRenderer renderer;
	
	private float runtime;
	
	public GameScreen() {
		// Create Player and PlayerHelper
		player = new Player();
		playerHelper = new PlayerHelper(player);
		
		// Camera Helper
		cameraHelper = new CameraHelper();
		cameraHelper.setPosition(new Vector2(Constants.viewportWidth / 2.0f, Constants.viewportHeight / 2.0f));
		
		// Create View Array
		viewArray = new ViewArray();
		
		// Create Renderer
		renderer = new GameRenderer(viewArray);
		renderer.setCameraHelper(cameraHelper);
		
		// Create World Layer
		gameWorld = new GameWorld();
		gameWorld.setPlayer(player);
		gameWorldView = new WorldView(gameWorld, ViewType.SPRITE_VIEW);
		gameWorldHelper = new WorldHelper(gameWorld);
		viewArray.addView(gameWorldView);
		
		// Create Options Layer
		options = new Options();
		optionsView = new OptionsView(ViewType.SPRITE_VIEW);
		optionsHelper = new OptionsHelper(options);
		viewArray.addView(optionsView);
		
		// Create Interaction Layer
		interactionView = new InteractionView(gameWorld, ViewType.SHAPE_VIEW);
		interactionView.setPlayerHelper(playerHelper);
		interactionHelper = new InteractionHelper();
		interactionHelper.setCameraHelper(cameraHelper);
		interactionHelper.setPlayerHelper(playerHelper);
		interactionHelper.setWorldHelper(gameWorldHelper);
		viewArray.addView(interactionView);
		
		// Create Input Processor Layer
		inputHelper = new InputHelper(interactionHelper);
		Gdx.input.setInputProcessor(inputHelper);
	}
	
	@Override
	public void render(float delta) {
		// Debug Message
		//System.out.println("GameScreen - Render");
		
		runtime += delta;
		gameWorld.update(delta * Options.velocityFactor);
		renderer.render(runtime);
	}

	@Override
	public void resize(int width, int height) {
		// Debug Message
		System.out.println("GameScreen - Resize");
		Constants.windowWidth = width;
		Constants.windowHeight = height;
		Constants.aspectRatio = ((float) height) / width;
		cameraHelper.resize(width, height);
	}

	@Override
	public void show() {
		// Debug Message
		System.out.println("GameScreen - Show");
	}

	@Override
	public void hide() {
		// Debug Message
		System.out.println("GameScreen - Hide");
	}

	@Override
	public void pause() {
		// Debug Message
		System.out.println("GameScreen - Pause");
	}

	@Override
	public void resume() {
		// Debug Message
		System.out.println("GameScreen - Resume");
	}

	@Override
	public void dispose() {
		// Debug Message
		System.out.println("GameScreen - Dispose");
	}
}
