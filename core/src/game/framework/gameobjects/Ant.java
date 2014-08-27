package game.framework.gameobjects;

import game.framework.assets.AssetsLoader;
import game.framework.utils.Constants;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Ant extends GameObject {

	private static int countId;
	
	private TextureRegion tx;
	
	public Vector2 maxVelocity;
	public float maxForce;
	
	private BodyDef antPhysicsDef;
	private FixtureDef antFixture;
	private PolygonShape antShape;
	private Body antBody;
	
	private float runtime;
	
	private Vector2 aim;
	
	private AntState state;
	public enum AntState {IDLE, WALKING};
	
	public Ant(float x, float y) {
		super("Ant", countId++);
		runtime = 0;
		
		position.set(x, y);
		
		interactor.setPosition(x, y);
		interactor.setDimension(0.5f);
		
		state = AntState.IDLE;
		
		maxVelocity = new Vector2(3f, 3f);
		maxForce = 0.5f;
		
		aim = position.cpy();
		
		tx = new TextureRegion();
	}
	
	public void createBody(World world) {
		antPhysicsDef = new BodyDef();
		antPhysicsDef.position.set(position.x, position.y);
		antPhysicsDef.type = BodyType.DynamicBody;
		antFixture = new FixtureDef();
		antFixture.restitution = 0.0f;
		antShape = new PolygonShape();
		antShape.setAsBox(dimensions.x/6, dimensions.y/6);
		antFixture.shape = antShape;
		antBody = world.createBody(antPhysicsDef);
		antBody.createFixture(antFixture).setUserData("Ant Body");
	}

	@Override
	public void update(float delta) {
		// Update Ant
		// position, state, velocity, acceleration ...
		runtime += delta;
		velocity.add(acceleration.add(goTo(aim)));
		velocity.nor().scl(maxVelocity);
		antBody.setLinearVelocity(velocity);
		//position.add(velocity.cpy().scl(delta));
		position.set(antBody.getPosition().cpy().sub(dimensions.cpy().scl(0.5f)));
		
		// Adjust position
		if (position.x > 2 * Constants.viewportWidth){
			position.x = 0;
		} else if (position.x < 0){
			position.x = 2 * Constants.viewportWidth;
		}
		
		if (position.y > 2 * Constants.viewportHeight){
			position.y = 0;
		} else if (position.y < 0){
			position.y = 2 * Constants.viewportHeight;
		}
		
		acceleration.scl(0);
		if (!(velocity.len() == 0)) updateDirection();
		updateState();
		updateSprite();
		// Update Interactor
		interactor.updateInteractor();
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float runtime) {
		//System.out.println("Ant Sprite Drawed!");
		
		spriteBatch.draw(tx,
				   		 position.x       , position.y,
				   		 dimensions.x / 2 , dimensions.y / 2,
				   		 dimensions.x     , dimensions.y,
				   		 scale.x          , scale.y,
				   		 rotation);
	}

	@Override
	public void draw(ShapeRenderer shapeRenderer) {
		//System.out.println("Ant Shape Drawed!");
		interactor.draw(shapeRenderer);
	}
	
	public void setAim(Vector2 aim) {
		this.aim = aim;
	}
	
	private Vector2 goTo(Vector2 aim) {
		Vector2 steer;
		
		Vector2 destiny = aim.cpy().sub(position);
		
		float d = destiny.len();
		
		if (d > 0.5f) {
			destiny.nor();
			destiny.scl(maxVelocity);
			
			steer = destiny.sub(velocity);
			steer.nor().scl(maxForce);
		}
		
		else
		{
			steer = new Vector2(0,0);
			velocity = new Vector2(0,0);
		}
		
		return steer;
	}
	
	public void updateState() {
		if (velocity.len() == 0) {
			state = AntState.IDLE;
		} else {
			state = AntState.WALKING;
		}
	}
	
	public void updateSprite() {
		if (state.equals(AntState.IDLE)) {
			tx = AssetsLoader.instance.ant1Walking.get(1);

		} else if (state.equals(AntState.WALKING)) {
			tx = AssetsLoader.instance.ant1WalkingAnimation.getKeyFrame(runtime);
		}
	}
	
	public void updateDirection() {
		rotation = velocity.angle();
	}
}
