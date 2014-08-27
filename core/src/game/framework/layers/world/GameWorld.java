package game.framework.layers.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import game.framework.gameobjects.Ant;
import game.framework.gameobjects.GameObject;
import game.framework.player.Player;

public class GameWorld {
	
	private Player player;
	
	private World physicsWorld;
	
	private Array<GameObject> gameObjects;
	
	public GameWorld() {
		// Debug Message
		System.out.println("World Created!");
		
		physicsWorld = new World(new Vector2(0, 0), true);
		
		gameObjects = new Array<GameObject>();
		
		// Game Objects
		Ant ant1 = new Ant(1, 1); ant1.createBody(physicsWorld);
		Ant ant2 = new Ant(3, 1); ant2.createBody(physicsWorld);
		Ant ant3 = new Ant(1, 3); ant3.createBody(physicsWorld);
		Ant ant4 = new Ant(3, 3); ant4.createBody(physicsWorld);
		
		
		gameObjects.add(ant1);
		gameObjects.add(ant2);
		gameObjects.add(ant3);
		gameObjects.add(ant4);
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public World getPhysicsWorld() {
		return physicsWorld;
	}
	
	public Array<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void update(float delta) {
		physicsWorld.step(delta, 6, 2);
		
		for (GameObject obj : gameObjects) {
			obj.update(delta);
		}
		
		worldLogic();
	}
	
	private void worldLogic() {
		// Debug Message
		//System.out.println("World Logic");
	}
}
