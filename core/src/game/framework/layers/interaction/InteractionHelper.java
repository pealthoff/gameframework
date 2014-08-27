package game.framework.layers.interaction;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import game.framework.gameobjects.Ant;
import game.framework.gameobjects.GameObject;
import game.framework.gamerenderer.CameraHelper;
import game.framework.layers.world.WorldHelper;
import game.framework.player.PlayerHelper;
import game.framework.player.PlayerHelper.GamePlayStates;
import game.framework.utils.Constants;

public class InteractionHelper {
	
	private CameraHelper cameraHelper;
	private PlayerHelper playerHelper;
	private WorldHelper worldHelper;
	
	private Rectangle interactionRect;
	public static float interactionLength;
	
	public InteractionHelper() {
		// Debug Message
		System.out.println("Interaction Helper Created!");
		
		interactionRect = new Rectangle();
		interactionLength = 0.25f;
	}
	
	public void setCameraHelper(CameraHelper cameraHelper) {
		this.cameraHelper = cameraHelper;
	}
	
	public void setPlayerHelper(PlayerHelper playerHelper) {
		this.playerHelper = playerHelper;
	}
	
	public void setWorldHelper(WorldHelper worldHelper) {
		this.worldHelper = worldHelper;
	}
	
	public void selection(float x, float y) {
		float vx, vy;
		float wx, wy;
		boolean flag = false;
		
		// T1 - Screen to viewport
		vx = ((float) Constants.viewportWidth * x ) / Constants.windowWidth;
		vy = ((float) Constants.viewportHeight * y ) / Constants.windowHeight;
		
		// T2 - Viewport to world
		wx = cameraHelper.getX() + vx - Constants.viewportWidth / 2.0f;
		wy = cameraHelper.getY() + vy - Constants.viewportHeight / 2.0f;
		
		playerHelper.setClickedPoint(wx, wy);
		
		interactionRect.set(wx - interactionLength / 2, wy - interactionLength / 2, 
							interactionLength, interactionLength);
		
		Array<GameObject> worldObjects;
		
		worldObjects = worldHelper.getWorldObjects();
		
		for (GameObject sobj : worldObjects) {
			if (sobj.getInteractor().isTouched(interactionRect)) {
				playerHelper.addToSelectedObjects((Ant)sobj);
				flag = true;
			}
		}
		
		if (flag) {
			playerHelper.changeState(GamePlayStates.SELECTED);
		} else {
			playerHelper.changeState(GamePlayStates.GO_TO_SOLDIERS);
		}
		
		playerHelper.doAction();
	}
	
	public void multipleSelection() {
		boolean flag = false;
		
		Array<GameObject> worldObjects;
		
		worldObjects = worldHelper.getWorldObjects();
		
		for (GameObject sobj : worldObjects) {
			if (sobj.getInteractor().isTouched(interactionRect)) {
				playerHelper.addToSelectedObjects((Ant)sobj);
				flag = true;
			}
		}
		
		if (flag) {
			playerHelper.changeState(GamePlayStates.SELECTED);
		} else {
			playerHelper.changeState(GamePlayStates.GO_TO_SOLDIERS);
		}
		
		playerHelper.doAction();
	}
}
