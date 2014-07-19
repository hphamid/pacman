package hp.hamid.pacman.ui.items;

import hp.hamid.pacman.ui.movingItem.GameManager;
import hp.hamid.pacman.ui.movingItem.MovingGameItem;
import hp.hamid.pacman.ui.movingItem.PacMan;

import java.awt.Color;
import java.awt.Graphics;

public class Power extends GameItem {

	public Power() {
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(getx() * width , gety() * height, width, height);
		g.setColor(new Color(255, 255, 255));
		g.fillArc(getx() * width , gety() * height , width, height, 0, 360);

	}
	
	@Override
	public boolean isBlocked(int nowx, int nowy, MovingGameItem item) {
		return false;
	}

	@Override
	public GameItem onCollide(GameItem item) {
		if(item instanceof PacMan){
			GameManager.getInstance().power();
//			PacMan.getInstance().makeJumpEnable();
			return new Empty(getx(),gety());
		}
		return this;
	}

}
