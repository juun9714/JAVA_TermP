package game_ver_2;

import javax.swing.JFrame;

public class Galaga extends JFrame{
	
	
	public Galaga() {
		setTitle("Galaga");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_WIDTH);
		setResizable(false);
		//한번 만들어진 게임창을 줄이거나 늘릴 수 없음
		setLocationRelativeTo(null);
		//모니터에 창이 정중앙에
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//게임창을 종료했을 때, 프로그램도 종료하도록 하는 것
		setVisible(true);
		//default값이 false임
	}
}
