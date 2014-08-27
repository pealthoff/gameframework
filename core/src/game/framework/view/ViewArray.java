package game.framework.view;

import com.badlogic.gdx.utils.Array;

public class ViewArray {
	
	private Array<View> views;
	
	public ViewArray() {
		views = new Array<View>();
	}
	
	public void addView(View view) {
		views.add(view);
	}
	
	public void removeView(int idx) {
		views.removeIndex(idx);
	}
	
	public void clear() {
		views.clear();
	}
	
	public Array<View> getViews() {
		return views;
	}
}
