package game_ver_15;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
		if(Galaga.game==null) {
			//게임이 현재 진행되고 있지 않은 상태라면 키보드 입력이 들어와도 무시하기 
			return;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			Galaga.game.pressS();
		}else if(e.getKeyCode() == KeyEvent.VK_D) {
			Galaga.game.pressD();
		}else if(e.getKeyCode() == KeyEvent.VK_F) {
			Galaga.game.pressF();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Galaga.game.pressSpace();
		}else if(e.getKeyCode() == KeyEvent.VK_J) {
			Galaga.game.pressJ();
		}else if(e.getKeyCode() == KeyEvent.VK_K) {
			Galaga.game.pressK();
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			Galaga.game.pressL();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(Galaga.game==null) {
			//게임이 현재 진행되고 있지 않은 상태라면 키보드 입력이 들어와도 무시하기 
			return;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			Galaga.game.releaseS();
		}else if(e.getKeyCode() == KeyEvent.VK_D) {
			Galaga.game.releaseD();
		}else if(e.getKeyCode() == KeyEvent.VK_F) {
			Galaga.game.releaseF();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Galaga.game.releaseSpace();
		}else if(e.getKeyCode() == KeyEvent.VK_J) {
			Galaga.game.releaseJ();
		}else if(e.getKeyCode() == KeyEvent.VK_K) {
			Galaga.game.releaseK();
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			Galaga.game.releaseL();
		}
	}
}
