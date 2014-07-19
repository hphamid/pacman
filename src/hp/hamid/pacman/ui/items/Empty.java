package hp.hamid.pacman.ui.items;

import hp.hamid.pacman.ui.movingItem.MovingGameItem;

import java.awt.Color;
import java.awt.Graphics;

public class Empty extends GameItem {

	
	public Empty(int x, int y) {
		super(x, y);
	}
	public Empty(){
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(getx() * width, gety() * height, width, height);
	}

	@Override
	public boolean isBlocked(int nowx, int nowy, MovingGameItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GameItem onCollide(GameItem item) {

		return this;
	}

}
