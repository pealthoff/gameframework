package game.framework.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Interactor {
	
	private byte mask;
	private int order;
	private boolean isActive;
	private Color color;
	
	private Rectangle interactionRect;
	
	private GameObject parentObject;
	
	public Interactor(GameObject parentObject) {
		mask = 0;
		order = 0;
		isActive = true;
		color = Color.RED;
		
		this.parentObject = parentObject;
		
		Vector2 parentPosition = parentObject.getPosition();
		Vector2 parentDimensions = parentObject.getDimensions();
		
		interactionRect = new Rectangle(parentPosition.x, parentPosition.y, parentDimensions.x, parentDimensions.y);
	}
	
	public void setMask(byte mask) {
		this.mask = mask;
	}
	
	public void setorder(int order) {
		this.order = order;
	}
	
	public void setActive(boolean value) {
		isActive = value;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setPosition(Vector2 position) {
		interactionRect.setPosition(position);
	}
	
	public void setPosition(float x, float y) {
		interactionRect.setPosition(x, y);
	}
	
	public void setDimension(float dimension) {
		Vector2 center = new Vector2();
		interactionRect.getCenter(center);
		interactionRect.x = center.x - dimension / 2;
		interactionRect.y = center.y - dimension / 2;
		interactionRect.width = dimension;
		interactionRect.height = dimension;
	}
	
	public boolean isTouched(Rectangle rect) {
		if (isActive)
		{
			if (rect.overlaps(interactionRect)) {
				System.out.println("Selected");
				color = Color.NAVY;
				return true;
			}
			
			else {
				color = Color.RED;
				
				return false;
			}
		}
		
		return false;
	}
	
	public void updateInteractor() {
		Vector2 parentPosition = parentObject.getPosition();
		Vector2 parentDimension = parentObject.getDimensions();
		
		Vector2 parentCenter = parentPosition.add(parentDimension.scl(0.5f));
		interactionRect.set(parentCenter.x - interactionRect.width / 2 , 
						    parentCenter.y - interactionRect.height / 2,
						    interactionRect.width, interactionRect.height);
	}
	
	public void draw(ShapeRenderer shapeRenderer) {
		// Shape drawed
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(color);
		shapeRenderer.rect(interactionRect.x, interactionRect.y, interactionRect.width, interactionRect.height);
		shapeRenderer.end();
	}
}
