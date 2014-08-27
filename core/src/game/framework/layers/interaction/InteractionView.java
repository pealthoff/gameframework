package game.framework.layers.interaction;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import game.framework.gameobjects.GameObject;
import game.framework.layers.world.GameWorld;
import game.framework.player.PlayerHelper;
import game.framework.view.View;

public class InteractionView extends View {

	private GameWorld gameWorld;
	private PlayerHelper playerHelper;
	
	public InteractionView(GameWorld gameWorld, ViewType type) {
		super(type);
		
		this.gameWorld = gameWorld;
		// Debug Message
		System.out.println("Interaction View Created!");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float runtime) {
		// TODO Auto-generated method stub
		
	}

	public void setPlayerHelper(PlayerHelper playerHelper) {
		this.playerHelper = playerHelper;
	}
	
	@Override
	public void draw(ShapeRenderer shapeRenderer) {
		for (GameObject obj : gameWorld.getGameObjects()) {
			obj.getInteractor().draw(shapeRenderer);
		}
		
		playerHelper.draw(shapeRenderer);
	}

	@Override
	public void draw(Box2DDebugRenderer b2dRenderer, Matrix4 projMatrix) {
		// TODO Auto-generated method stub
		
	}

}
