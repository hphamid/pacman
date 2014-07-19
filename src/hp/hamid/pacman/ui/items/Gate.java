package hp.hamid.pacman.ui.items;

import hp.hamid.pacman.ui.movingItem.Ghost;
import hp.hamid.pacman.ui.movingItem.MovingGameItem;

import java.awt.Color;
import java.awt.Graphics;

public class Gate extends GameItem {

	public Gate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(255, 0, 255));
		g.fillRect(getx() * width, gety() * height, width, height);
	}

	@Override
	public boolean isBlocked(int nowx, int nowy, MovingGameItem item) {
		if(item instanceof Ghost && nowy > gety() && ((Ghost)item).isActive() ){
			return false;
		}
		return true;
	}

	@Override
	public GameItem onCollide(GameItem item) {

		return this;
	}

}
