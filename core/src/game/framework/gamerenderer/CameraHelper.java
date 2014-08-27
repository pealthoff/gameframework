package game.framework.gamerenderer;

import game.framework.utils.Constants;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class CameraHelper {
	
	private Vector2 position;
	private float angle = 0.0f;
	private float aspectRatio;
	private float zoom = 1.0f;
	private float zoomInc = 0.1f;
	private float maxZoom = 1.0f;
	private float minZoom = 0.1f;
	
	private float viewportWidth;
	private float viewportHeight;
	
	public CameraHelper() {
		position = new Vector2(0,0);
	}
	
	public void applyTo(OrthographicCamera camera) {
		camera.position.x = position.x;
		camera.position.y = position.y;
		
		camera.setToOrtho(true, viewportWidth, viewportHeight);
		camera.zoom = zoom;
		camera.rotate(angle);
	}
	
	public void resize(int width, int height) {
		viewportWidth = Constants.viewportWidth;
		viewportHeight = viewportWidth * Constants.aspectRatio;
	}
	
	public void incrementZoom() {
		zoom += zoomInc;
		
		if ( zoom > maxZoom ) {
			zoom = maxZoom;
		}
	}
	
	public void decrementZoom() {
		zoom -= zoomInc;
		
		if ( zoom < minZoom ) {
			zoom = minZoom;
		}
	}
	
	public void setPosition(Vector2 position) {
		this.position.set(position);
	}
	
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
}
