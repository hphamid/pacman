package hp.hamid.pacman.ui.movingItem;

import java.awt.Color;
import java.awt.Graphics;

public class Inky extends Ghost {
	
	public void doMain(){
		this.setPosition(15, 14);
		this.postCreate(new Point(-1, 0));
	}
	
	@Override
	public void ghostDraw(Graphics g) {
		g.setColor(new Color(0, 255, 222));
		g.fillRect(getPositionx(), getPositiony(), width, height);	
	}
		
	@Override
	public void findTarget() {
		int x = PacMan.getInstance().getx() + 2 * PacMan.getInstance().getRight();
		int y= PacMan.getInstance().gety() + 2 * PacMan.getInstance().getDown();
		int difx = x - GameManager.getInstance().getGhosts().get(0).getx();
		int dify = y - GameManager.getInstance().getGhosts().get(0).gety();
		this.setTarget(GameManager.getInstance().getGhosts().get(0).getx() + 2 *difx, GameManager.getInstance().getGhosts().get(0).gety() + 2*dify );
	}

	@Override
	public boolean isActive() {
		return GameManager.getInstance().numberOfEatenDots >= 30;
	}
}
