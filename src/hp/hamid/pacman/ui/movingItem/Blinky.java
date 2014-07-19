package hp.hamid.pacman.ui.movingItem;

import java.awt.Color;
import java.awt.Graphics;

public class Blinky extends Ghost {

	public void doMain(){
		this.setPosition(14, 10);
		this.postCreate(new Point(-1, 0));
	}
	
	@Override
	public void ghostDraw(Graphics g) {
		g.setColor(new Color(255, 0, 0));
		g.fillRect(getPositionx(), getPositiony(), width, height);	
	}

	@Override
	public void findTarget() {
		this.setTarget(PacMan.getInstance().getx(), PacMan.getInstance().gety());
	}

	@Override
	public boolean isActive() {
		return true;
	}
}
