package game_ver_6;

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
	
	private Game game=new Game();
	Music introMusic = new Music("intro_music.mp3", true);
	Music gameMusic= new Music("funky.mp3",true);
	Music endMusic= new Music("onceagain.mp3",true);
	private Image screenImage;
	private Graphics screenGraphic;
	// double-buffering�� ���ؼ� ��ü ȭ�鿡 ���� �̹����� ��� �� �ν��Ͻ�

	private Image background = new ImageIcon(Main.class.getResource("../images/introBackGround.jpg")).getImage();
	// �̹����� ���� ��ü
	
	private Image selectedImage = new ImageIcon(Main.class.getResource("../images/tiger.jpg")).getImage();
	private Image titleImage = new ImageIcon(Main.class.getResource("../images/song_title.jpg")).getImage();
	
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/bar.png")));
	
	
	private ImageIcon exitButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/exit_clked.png"));
	private ImageIcon exitButtonBasicImage=new ImageIcon(Main.class.getResource("../images/exit.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private int mouseX, mouseY;
	//�޴��ٸ� �巡������ ��, ���� ����ǵ��� ���ְ��� ���� ���α׷����� ���콺�� x,y��ǥ
	
	
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/start_clked.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/start.png"));
	
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quit_clked.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quit.png"));
	
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);

	
	private boolean isMainScreen = false;
	
	private ImageIcon leftButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/left_clked.png"));
	private ImageIcon leftButtonBasicImage=new ImageIcon(Main.class.getResource("../images/left.png"));
	
	private ImageIcon rightButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/right_clked.png"));
	private ImageIcon rightButtonBasicImage=new ImageIcon(Main.class.getResource("../images/right.png"));
	
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
	
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
		
		quitButton.setBounds(40, 330, 64, 64);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				//���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3",false);
				//false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				//���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
		add(quitButton);
		// exitButton �⺻������ �����Ǵ� ���ø��� �ִµ�, �츮�� ������ ����� �ƴϱ� ������ �ٲ���� �Ѵ�.
		
		
		startButton.setBounds(40, 200, 64, 64);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				//���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3",false);
				//false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				//���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				//���콺�� �ٽ� �������� ��, �⺻ Ŀ���� �ٲ��ֱ� 
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3",false);
				//false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
				//���� ���� �̺�Ʈ
				startButton.setVisible(false);
				//start button �Ⱥ��̰� 
				background=new ImageIcon(Main.class.getResource("../images/MainBackGround.jpg")).getImage();
				//background�� �� �̹��� ���� ���� -> paintComponent�� ����� ���� -> ��� ������ ����ȴ�. 
				introMusic.close();
				isMainScreen=true;
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				quitButton.setBounds(10, 650, 64, 64);
				gameMusic.start();
				game.start();
			}
		});
		add(startButton);
		
		leftButton.setVisible(false);
		//ó���� �Ⱥ��̵���
		leftButton.setBounds(140, 310, 64, 64);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				//���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3",false);
				//false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				//���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				//���콺�� �ٽ� �������� ��, �⺻ Ŀ���� �ٲ��ֱ� 
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3",false);
				//false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
				//���ʹ�ư �̺�Ʈ 
			}
		});
		add(leftButton);
		// exitButton �⺻������ �����Ǵ� ���ø��� �ִµ�, �츮�� ������ ����� �ƴϱ� ������ �ٲ���� �Ѵ�.
		
		
		rightButton.setVisible(false);
		//ó���� �Ⱥ��̵���
		rightButton.setBounds(1140, 310, 64, 64);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				//���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3",false);
				//false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				//���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				//���콺�� �ٽ� �������� ��, �⺻ Ŀ���� �ٲ��ֱ� 
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3",false);
				//false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
				//�����ʹ�ư �̺�Ʈ 
			}
		});
		add(rightButton);
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
		g.drawImage(background, 0, 0, null);
		// ��Ʈ�� ��׶��带 0,0 ��ġ�� �׷��� �Ź� �ٲ�� �̹����� ��쿡 �����
		
		if(isMainScreen==true) {
			g.drawImage(selectedImage,340,100,null);
			//�߰��Ȱ� �ƴ϶� �׳� �ڿ� ��׶���? ������ 
			g.drawImage(titleImage, 500, 80, null);
		}
		
		paintComponents(g);
		// �̹����� �ܼ��ϰ� ��ũ�� �̹��� �����ȿ� �׷��ִ� �� �̿ܿ�
		// JLabel�� JFrame�ȿ� �߰��ϸ� �׷��ִ� ���� �ǹ��� �޴��ٴ� �׻� �����ϰ� ������ �̹����̱� ������ paintComponent��
		// �Ѵ�.
		//main frame�� �߰��� (add)�� �͵��� �׸��� �� 
		this.repaint();
		// �ٽ� ����Ʈ�Լ��� �ҷ��� -> ������ ����Ǵ� ���� ��� �ݺ��ϸ� ���ȭ���� �׷��ִ� ����
	}
}
