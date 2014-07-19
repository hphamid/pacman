package hp.hamid.pacman.ui.items;

import hp.hamid.pacman.ui.Drawable;
import hp.hamid.pacman.ui.movingItem.MovingGameItem;

public abstract class GameItem implements Drawable {
	public final static int width = 20;
	public final static int height = 20;
	private int x = 0;
	private int y = 0;

	public GameItem(){
		
	}
	
	public GameItem(int x, int y){
		this();
		this.setPosition(x, y);
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getx() {
		return this.x;
	}

	public int gety() {
		return this.y;

	}

	public int getPositionx() {
		return getx() * width;
	}

	public int getPositiony() {
		return gety() * width;
	}

	public abstract boolean isBlocked(int nowx, int nowy, MovingGameItem item);

	public abstract GameItem onCollide(GameItem item);
}
