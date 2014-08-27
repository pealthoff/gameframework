package game.framework.gamerenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;

import game.framework.utils.Constants;
import game.framework.view.View;
import game.framework.view.View.ViewType;
import game.framework.view.ViewArray;

public class GameRenderer {
	
	ViewArray viewArray;
	
	private OrthographicCamera camera;
	private CameraHelper cameraHelper;
	
	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	private Box2DDebugRenderer b2dRenderer;
	
	public GameRenderer(ViewArray viewArray) {
		// Debug Message
		System.out.println("Game Renderer Created!");
		
		this.viewArray = viewArray;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, Constants.viewportWidth, Constants.viewportHeight);
		
		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		b2dRenderer = new Box2DDebugRenderer();
	}

	public void render(float runtime) {
		//System.out.println("Game Renderer - Render");
		
		if (cameraHelper != null) {
			cameraHelper.applyTo(camera);
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Draw backgroung color
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(1, 1, Constants.viewportWidth - 2  , Constants.viewportHeight - 2);
		shapeRenderer.end();
		
		Array<View> views = viewArray.getViews();
		
		for (View view : views) {
			ViewType type = view.getType();
			
			if (type.equals(ViewType.SPRITE_VIEW)) {
				spriteBatch.setProjectionMatrix(camera.combined);
				spriteBatch.begin();
				view.draw(spriteBatch, runtime);
				spriteBatch.end();
				view.draw(b2dRenderer, camera.combined);
			} else if (type.equals(ViewType.SHAPE_VIEW)) {
				shapeRenderer.setProjectionMatrix(camera.combined);
				view.draw(shapeRenderer);
			} else if (type.equals(ViewType.PHYSICS_VIEW)) {
				view.draw(b2dRenderer, camera.combined);
			}
		}
	}
	
	public void setCameraHelper(CameraHelper cameraHelper) {
		this.cameraHelper = cameraHelper;
	}
}
