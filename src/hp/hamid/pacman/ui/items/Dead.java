package hp.hamid.pacman.ui.items;

import hp.hamid.pacman.ui.movingItem.MovingGameItem;

import java.awt.Color;
import java.awt.Graphics;

public class Dead extends GameItem {

	public Dead() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(getx() * width, gety() * height, width, height);
	}

	@Override
	public boolean isBlocked(int nowx, int nowy, MovingGameItem item) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public GameItem onCollide(GameItem item) {

		return this;
	}

}
