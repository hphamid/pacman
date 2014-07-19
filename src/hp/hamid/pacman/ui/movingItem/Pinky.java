package hp.hamid.pacman.ui.movingItem;

import java.awt.Color;
import java.awt.Graphics;

public class Pinky extends Ghost {

	 public void doMain() {
		this.setPosition(16, 14);
		this.postCreate(new Point(0, -1));
	}

	@Override
	public void ghostDraw(Graphics g) {
		g.setColor(new Color(255, 184, 222));
		g.fillRect(getPositionx(), getPositiony(), width, height);
	}

	@Override
	public void findTarget() {
		this.setTarget(PacMan.getInstance().getx()
				+ 4 * PacMan.getInstance().getRight(), PacMan.getInstance().gety()
				+ 4 * PacMan.getInstance().getDown());
	}

	@Override
	public boolean isActive() {
		return true;
	}
}
