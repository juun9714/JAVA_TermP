package game_ver_4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Galaga extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;
	// double-buffering�� ���ؼ� ��ü ȭ�鿡 ���� �̹����� ��� �� �ν��Ͻ�

	private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBackGround.jpg")).getImage();
	// �̹����� ���� ��ü
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/bar.png")));
	
	
	private ImageIcon exitButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/exit_clked.png"));
	private ImageIcon exitButtonBasicImage=new ImageIcon(Main.class.getResource("../images/exit.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private int mouseX, mouseY;
	//�޴��ٸ� �巡������ ��, ���� ����ǵ��� ���ְ��� ���� ���α׷����� ���콺�� x,y��ǥ

	public Galaga() {
		setUndecorated(true);
		// ������ ���� �� �⺻������ �����ϴ� �޴��ٰ� ������ �ʰ� �ȴ�.
		setTitle("Galaga");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		// �ѹ� ������� ����â�� ���̰ų� �ø� �� ����
		setLocationRelativeTo(null);
		// ����Ϳ� â�� ���߾ӿ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ����â�� �������� ��, ���α׷��� �����ϵ��� �ϴ� ��
		setVisible(true);
		// default���� false��

		setBackground(new Color(0, 0, 0, 0));
		// paintComponent�� ���� ��, ����� ȸ���� �ƴ϶� �Ͼ���� �ȴ�.
		setLayout(null);
		

		exitButton.setBounds(1240, 0, 32, 32);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				//���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3",false);
				//false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				//���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				//���콺�� �ٽ� �������� ��, �⺻ Ŀ���� �ٲ��ֱ� 
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3",false);
				//false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
					//��ư ȿ������ �� ��� ���� 
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
				//exit ��ư ������ ��, ������ ����� 
			}
		});
		add(exitButton);
		// exitButton �⺻������ �����Ǵ� ���ø��� �ִµ�, �츮�� ������ ����� �ƴϱ� ������ �ٲ���� �Ѵ�.
		
		

		menuBar.setBounds(0,0,1280,30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x=e.getXOnScreen();
				int y=e.getYOnScreen();
				
				setLocation(x - mouseX, y - mouseY);
				
				//�巡���Ҷ� ������������ ���콺�� x,y��ǥ�� �����ͼ� GUIâ�� �ű� �� �ְ� ���� 
			}
		});
		add(menuBar);
		
		Music introMusic = new Music("intro_music.mp3", true);
		introMusic.start();
	}

	public void paint(Graphics g) {
		// JFRAME�� ��ӹ��� ���ӿ��� ���� ���� ȭ���� �׷��ִ� �Լ� : ��ӵ� ��
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// ũ�⸸ŭ �̹����� ���� �ڿ� �װ� screenGraphic�� �־��� ����
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		// ��ũ�� �׷��ȿ� ��� �׸��� �׷��ִ°�, ���� screenDraw��� �Լ��� �������� �ʾ���
		g.drawImage(screenImage, 0, 0, null);
		// ��ũ�� �̹����� (0,0) ��ġ�� �׷��� ����
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		// ��Ʈ�� ��׶��带 0,0 ��ġ�� �׷��� �Ź� �ٲ�� �̹����� ��쿡 �����
		paintComponents(g);
		// �̹����� �ܼ��ϰ� ��ũ�� �̹��� �����ȿ� �׷��ִ� �� �̿ܿ�
		// JLabel�� JFrame�ȿ� �߰��ϸ� �׷��ִ� ���� �ǹ��� �޴��ٴ� �׻� �����ϰ� ������ �̹����̱� ������ paintComponent��
		// �Ѵ�.
		this.repaint();
		// �ٽ� ����Ʈ�Լ��� �ҷ��� -> ������ ����Ǵ� ���� ��� �ݺ��ϸ� ���ȭ���� �׷��ִ� ����
	}
}
