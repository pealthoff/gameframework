package game.framework.assets;

import game.framework.utils.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AssetsLoader {
	
	public static AssetsLoader instance = new AssetsLoader();
	
	private TextureAtlas textureAtlas;
	
	public Animation ant1WalkingAnimation;
	public Array<TextureRegion> ant1Walking;
	
	public Animation ant2WalkingAnimation;
	public Array<TextureRegion> ant2Walking;
	
	private AssetsLoader() {
		// Debug Message
		System.out.println("Assets Loader created");
	}
	
	public void loadAssets() {
		// Debug Message
		System.out.println("Assets Loaded");
		
		textureAtlas = new TextureAtlas(Gdx.files.internal(Constants.TX_ANTS_MAP));

		for (Texture t : textureAtlas.getTextures()) {
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}

		ant1Walking = new Array<TextureRegion>();
		ant2Walking = new Array<TextureRegion>();
		
		TextureRegion tx;
		
		for (int i = 1; i < 4; i++) {
			tx = textureAtlas.findRegion("bug01" + i);
			tx.flip(false, true);
			ant1Walking.add(tx);
			
			tx = textureAtlas.findRegion("bug02" + i);
			tx.flip(false, true);
			ant2Walking.add(tx);
		}
		
		TextureRegion[] ant01 = {ant1Walking.get(0),ant1Walking.get(1),ant1Walking.get(2),ant1Walking.get(1)};
		TextureRegion[] ant02 = {ant2Walking.get(0),ant2Walking.get(1),ant2Walking.get(2),ant2Walking.get(1)};
		
		ant1WalkingAnimation = new Animation(0.1f, ant01);
		ant1WalkingAnimation.setPlayMode(Animation.PlayMode.LOOP);
		
		ant2WalkingAnimation = new Animation(0.1f, ant02);
		ant2WalkingAnimation.setPlayMode(Animation.PlayMode.LOOP);
	}
	
	public void dispose() {
		// Debug Message
		System.out.println("Assets loader - Dispose");
		textureAtlas.dispose();
	}
}
