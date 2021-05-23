package game_ver_3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Galaga extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphic;
	//double-buffering�� ���ؼ� ��ü ȭ�鿡 ���� �̹����� ��� �� �ν��Ͻ� 
	
	private Image introBackground;
	//�̹����� ���� ��ü
	
	public Galaga() {
		setTitle("Galaga");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setResizable(false);
		//�ѹ� ������� ����â�� ���̰ų� �ø� �� ����
		setLocationRelativeTo(null);
		//����Ϳ� â�� ���߾ӿ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//����â�� �������� ��, ���α׷��� �����ϵ��� �ϴ� ��
		setVisible(true);
		//default���� false��
		
		introBackground = new ImageIcon(Main.class.getResource("../images/introBackGround.jpg")).getImage();
		
		Music introMusic = new Music("intro_music.mp3",true);
		introMusic.start();
	}
	
	public void paint(Graphics g) {
		//JFRAME�� ��ӹ��� ���ӿ��� ���� ���� ȭ���� �׷��ִ� �Լ� : ��ӵ� ��
		screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		//ũ�⸸ŭ �̹����� ���� �ڿ� �װ� screenGraphic�� �־��� ����
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		//��ũ�� �׷��ȿ� ��� �׸��� �׷��ִ°�, ���� screenDraw��� �Լ��� �������� �ʾ���
		g.drawImage(screenImage, 0, 0, null);
		//��ũ�� �̹����� (0,0) ��ġ�� �׷��� ����
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0,0,null);
		//��Ʈ�� ��׶��带 0,0 ��ġ�� �׷���
		this.repaint();
		//�ٽ� ����Ʈ�Լ��� �ҷ��� -> ������ ����Ǵ� ���� ��� �ݺ��ϸ� ���ȭ���� �׷��ִ� ����
	}
}
