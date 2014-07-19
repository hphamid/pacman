package hp.hamid.pacman.ui.movingItem;

import java.awt.Color;
import java.awt.Graphics;

public class Clyde extends Ghost {

	public void doMain(){
		this.setPosition(14, 12);
		this.postCreate(new Point(0, 1));
	}
	
	@Override
	public void ghostDraw(Graphics g) {
		g.setColor(new Color(255, 184, 71));
		g.fillRect(getPositionx(), getPositiony(), width, height);	
	}

	@Override
	public void findTarget() {
		int dist = Math.abs(this.getx() - PacMan.getInstance().getx()) + Math.abs(this.gety() - PacMan.getInstance().gety());
		if(dist <= 8){
			this.setTarget(PacMan.getInstance().getx(), PacMan.getInstance().gety());
		}else{
			this.setTarget(0, height + 1);
		}
	}

	@Override
	public boolean isActive() {
//		return true;
		return GameManager.getInstance().numberOfEatenDots >= 60;
	}
}
