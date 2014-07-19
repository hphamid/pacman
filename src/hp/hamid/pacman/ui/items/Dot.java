package hp.hamid.pacman.ui.items;

import hp.hamid.pacman.ui.movingItem.GameManager;
import hp.hamid.pacman.ui.movingItem.MovingGameItem;
import hp.hamid.pacman.ui.movingItem.PacMan;

import java.awt.Color;
import java.awt.Graphics;

public class Dot extends GameItem {

	public Dot() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(getx() * width , gety() * height, width, height);
		g.setColor(new Color(255, 255, 255));
		g.fillArc(getx() * width + width /4 , gety() * height + height / 4, width/2, height/2, 0, 360);
	}

	@Override
	public boolean isBlocked(int nowx, int nowy, MovingGameItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GameItem onCollide(GameItem item) {
		if(item instanceof PacMan){
			GameManager.getInstance().eatDot();
			return new Empty(getx(),gety());
		}
		return this;
	}

}
