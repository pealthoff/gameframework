package game.framework.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public abstract class View {
	
	public enum ViewType {SPRITE_VIEW, SHAPE_VIEW, PHYSICS_VIEW};
	
	private ViewType type;
	
	protected View(ViewType type) {
		this.type = type;
	}
	
	public ViewType getType() {
		return type;
	}
	
	public abstract void draw(SpriteBatch spriteBatch, float runtime);
	
	public abstract void draw(ShapeRenderer shapeRenderer);
	
	public abstract void draw(Box2DDebugRenderer b2dRenderer, Matrix4 projMatrix);
}
