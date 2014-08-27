package game.framework.player;

import game.framework.gameobjects.Ant;
import game.framework.layers.interaction.InteractionHelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class PlayerHelper {
	
	private Player player;
	
	private Vector2 clickedPoint;
	private boolean isclicked;
	
	private boolean isDragging;
	
	private GamePlayStates lastPlayerState;
	private GamePlayStates playerState;
	public enum GamePlayStates {SELECTED, NOT_SELECTED, GO_TO_SOLDIERS};
	
	private Array<Ant> selectedObjects;
	
	public PlayerHelper(Player player) {
		this.player = player;

		clickedPoint = new Vector2();
		
		lastPlayerState = GamePlayStates.NOT_SELECTED;
		playerState = GamePlayStates.NOT_SELECTED;
		
		selectedObjects = new Array<Ant>();
	}
	
	public void addToSelectedObjects(Ant ant) {
		selectedObjects.add(ant);
	}

	public void draw(ShapeRenderer shapeRenderer) {
		if (isclicked) {
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(Color.CYAN);
			shapeRenderer.rect(clickedPoint.x - InteractionHelper.interactionLength / 2, 
							   clickedPoint.y - InteractionHelper.interactionLength / 2,
							   InteractionHelper.interactionLength, InteractionHelper.interactionLength);
			shapeRenderer.end();
			isclicked = false;
		} else if (isDragging) {
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(Color.RED);
			shapeRenderer.rect(clickedPoint.x - InteractionHelper.interactionLength / 2, 
							   clickedPoint.y - InteractionHelper.interactionLength / 2,
							   InteractionHelper.interactionLength, InteractionHelper.interactionLength);
			shapeRenderer.end();
		}
		
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.GRAY);
		shapeRenderer.rect(clickedPoint.x - 0.125f, clickedPoint.y - 0.125f, 0.25f, 0.25f);
		shapeRenderer.end();
	}
	
	public void setClickedPoint(float x, float y) {
		clickedPoint.set(x, y);
		isclicked = true;
	}
	
	public void changeState(GamePlayStates state) {
		lastPlayerState = playerState;
		playerState = state;
	}
	
	public void doAction() {
		if (selectedObjects.size != 0 &&
			lastPlayerState.equals(GamePlayStates.SELECTED) && 
			playerState.equals(GamePlayStates.GO_TO_SOLDIERS)) {
			for (Ant ant : selectedObjects) {
				ant.setAim(clickedPoint.cpy());
			}
			
			selectedObjects.clear();
			
			changeState(GamePlayStates.NOT_SELECTED);
		}
	}
	
	public GamePlayStates getState() {
		return playerState;
	}
}
