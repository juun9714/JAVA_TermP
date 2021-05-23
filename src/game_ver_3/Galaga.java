package game_ver_3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Galaga extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphic;
	//double-buffering을 위해서 전체 화면에 대한 이미지를 담는 두 인스턴스 
	
	private Image introBackground;
	//이미지를 담을 객체
	
	public Galaga() {
		setTitle("Galaga");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setResizable(false);
		//한번 만들어진 게임창을 줄이거나 늘릴 수 없음
		setLocationRelativeTo(null);
		//모니터에 창이 정중앙에
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//게임창을 종료했을 때, 프로그램도 종료하도록 하는 것
		setVisible(true);
		//default값이 false임
		
		introBackground = new ImageIcon(Main.class.getResource("../images/introBackGround.jpg")).getImage();
		
		Music introMusic = new Music("intro_music.mp3",true);
		introMusic.start();
	}
	
	public void paint(Graphics g) {
		//JFRAME을 상속받은 게임에서 가장 먼저 화면을 그려주는 함수 : 약속된 것
		screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		//크기만큼 이미지를 만든 뒤에 그걸 screenGraphic에 넣어줄 것임
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		//스크린 그래픽에 어떠한 그림을 그려주는것, 아직 screenDraw라는 함수를 구현하진 않았음
		g.drawImage(screenImage, 0, 0, null);
		//스크린 이미지를 (0,0) 위치에 그려줄 것임
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0,0,null);
		//인트로 백그라운드를 0,0 위치에 그려줌
		this.repaint();
		//다시 페인트함수를 불러옴 -> 게임이 진행되는 동안 계속 반복하며 배경화면을 그려주는 것임
	}
}
