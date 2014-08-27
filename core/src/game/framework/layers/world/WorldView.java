package game.framework.layers.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import game.framework.gameobjects.GameObject;
import game.framework.view.View;

public class WorldView extends View {
	
	private GameWorld gameworld;
	
	public WorldView(GameWorld gameworld, ViewType type) {
		super(type);
		// Debug Message
		System.out.println("World View Created!");
		
		this.gameworld = gameworld;
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float runtime) {
		for (GameObject obj : gameworld.getGameObjects()) {
			obj.draw(spriteBatch, runtime);
		}
	}

	@Override
	public void draw(ShapeRenderer shapeRenderer) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Box2DDebugRenderer b2dRenderer, Matrix4 projMatrix) {
		b2dRenderer.render(gameworld.getPhysicsWorld(), projMatrix);
	}
}
