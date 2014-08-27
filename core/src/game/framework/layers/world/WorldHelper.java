package game.framework.layers.world;

import game.framework.gameobjects.GameObject;

import com.badlogic.gdx.utils.Array;

public class WorldHelper {
	
	private GameWorld world;
	
	public WorldHelper(GameWorld world) {
		// Debug Message
		System.out.println("World Helper Created!");
		
		this.world = world;
	}
	
	public Array<GameObject> getWorldObjects() {
		return world.getGameObjects();
	}
}
