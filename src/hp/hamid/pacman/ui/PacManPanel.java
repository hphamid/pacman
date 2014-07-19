package hp.hamid.pacman.ui;

import hp.hamid.pacman.ui.items.GameItem;
import hp.hamid.pacman.ui.movingItem.GameManager;
import hp.hamid.pacman.ui.movingItem.Ghost;
import hp.hamid.pacman.ui.movingItem.PacMan;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PacManPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4272132700425764493L;

	public PacManPanel() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventDispatcher(new KeyEventDispatcher() {
					@Override
					public boolean dispatchKeyEvent(KeyEvent e) {
						char key = e.getKeyChar();
						if (key == 'w') {
							PacMan.getInstance().setNextMove(-1, 0);
						} else if (key == 's') {
							PacMan.getInstance().setNextMove(1, 0);
						} else if (key == 'a') {
							PacMan.getInstance().setNextMove(0, -1);
						} else if (key == 'd') {
							PacMan.getInstance().setNextMove(0, 1);
						}else if(key == 'j'){
							PacMan.getInstance().jumpPressed();
						}
						if(!PacMan.getInstance().isMoving()){
							PacMan.getInstance().MoveDone();
						}
						return false;
					}
				});
		TimeMachine.getInstance().start();
		TimeMachine.getInstance().addObserver(new Observer() {

			@Override
			public void update(Observable arg0, Object arg1) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						repaint();
					}
				});
			}
		});
	}

	@Override
	public void paint(Graphics arg0) {
		printMap(arg0);
		List<Ghost> ghosts = GameManager.getInstance().getGhosts();
		for(Ghost ghost: ghosts){
			ghost.draw(arg0);;
		}
		PacMan.getInstance().draw(arg0);
	}

	private void printMap(Graphics g) {
		GameItem[][] items = MainClass.getInstance().getMap();
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[i].length; j++) {
				items[i][j].draw(g);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		GameItem[][] items = MainClass.getInstance().getMap();
		return new Dimension(items[0].length * GameItem.width,
				(items.length + 1) * GameItem.height);
	}
}
