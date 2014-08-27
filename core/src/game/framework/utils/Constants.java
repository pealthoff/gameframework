package game.framework.utils;

public abstract class Constants {
	
	public static int windowWidth = 800;
	public static int windowHeight = 480;
	
	public static float aspectRatio = ((float) windowHeight) / windowWidth;
	
	public static float viewportWidth = 10;
	public static float viewportHeight = viewportWidth * aspectRatio;
	
	public static final String LVL_01 = "lvl/";
	
	public static final String TX_MAP = "gfx/";
	
	public static final String TX_ANTS_MAP = "gfx/bugpack.pack";
}
