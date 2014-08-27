package game.framework.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	
	protected String tag;
	protected int id;
	protected Vector2 dimensions;
	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 acceleration;
	protected Vector2 scale;
	protected float rotation;
	protected Interactor interactor;
	
	public GameObject(String tag, int id) {
		this.tag = tag;
		this.id = id;
		
		dimensions = new Vector2(1, 1);
		position = new Vector2(0, 0);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
		scale = new Vector2(1, 1);
		rotation = 0;
		
		interactor = new Interactor(this);
	}
	
	public void setDimensions(float width, float height) {
		dimensions.set(width, height);
	}
	
	public void setDimensions(Vector2 dimensions) {
		this.dimensions.add(dimensions);
	}
	
	public void setPosition(float x, float y) {
		position.set(x, y);
	}
	
	public void setPosition(Vector2 position) {
		this.position.set(position);
	}
	
	public void setVelocity(float x, float y) {
		velocity.set(x, y);
	}
	
	public void setVelocity(Vector2 position) {
		this.velocity.set(velocity);
	}
	
	public void setAcceleration(float x, float y) {
		acceleration.set(x, y);
	}
	
	public void setAcceleration(Vector2 acceleration) {
		this.acceleration.set(acceleration);
	}
	
	public void setRotation(float angle) {
		rotation = angle;
	}
	
	public String getTag() {
		return tag;
	}
	
	public int getId() {
		return id;
	}
	
	public Vector2 getDimensions() {
		return dimensions.cpy();
	}
	
	public Vector2 getPosition() {
		return position.cpy();
	}
	
	public Vector2 getVelocity() {
		return velocity.cpy();
	}
	
	public Vector2 getAcceleration() {
		return acceleration.cpy();
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public Interactor getInteractor() {
		return interactor;
	}
	
	public abstract void update(float delta);
	
	public abstract void draw(SpriteBatch spriteBatch, float runtime);
	
	public abstract void draw(ShapeRenderer shapeRenderer);
}
