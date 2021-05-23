package game_ver_11;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Galaga extends JFrame {

	private Game game = new Game();
	Music introMusic = new Music("intro_music.mp3", true);
	private Image screenImage;
	private Graphics screenGraphic;
	// double-buffering�� ���ؼ� ��ü ȭ�鿡 ���� �̹����� ��� �� �ν��Ͻ�
	private int mouseX, mouseY;
	// �޴��ٸ� �巡������ ��, ���� ����ǵ��� ���ְ��� ���� ���α׷����� ���콺�� x,y��ǥ
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;

	private Image background = new ImageIcon(Main.class.getResource("../images/introBackGround.jpg")).getImage();
	// �̹����� ���� ��ü
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();

	// buttons
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/bar.png")));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exit_clked.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exit.png"));

	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/start_clked.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/start.png"));

	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quit_clked.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quit.png"));

	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/left_clked.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/left.png"));

	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/right_clked.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/right.png"));

	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/one_clked.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/one.png"));

	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/two_clked.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/two.png"));

	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/back_clked.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/back.png"));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	// MUSIC
	ArrayList<Track> trackList = new ArrayList<Track>();
	private Image selectedImage;
	private Image titleImage;
	private Music selectedMusic;
	private int nowSelected = 0;
	// �ʱⰪ ����

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
		introMusic.start();
		trackList.add(new Track("song_title.jpg", "snowtiger.jpg", "moon.jpg", "funky.mp3", "funky.mp3"));
		trackList.add(new Track("song_title.jpg", "tiger.jpg", "moon.jpg", "onceagain.mp3", "onceagain.mp3"));
		trackList.add(new Track("song_title.jpg", "eagle.jpg", "moon.jpg", "sunny.mp3", "sunny.mp3"));

		exitButton.setBounds(1240, 0, 32, 32);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				// ���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				// ���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// ���콺�� �ٽ� �������� ��, �⺻ Ŀ���� �ٲ��ֱ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
					// ��ư ȿ������ �� ��� ����
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
				// exit ��ư ������ ��, ������ �����
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
				// ���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				// ���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// ���콺�� �ٽ� �������� ��, �⺻ Ŀ���� �ٲ��ֱ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
					// ��ư ȿ������ �� ��� ����
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
				// exit ��ư ������ ��, ������ �����
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
				// ���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				// ���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// ���콺�� �ٽ� �������� ��, �⺻ Ŀ���� �ٲ��ֱ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();// ���� ���� �̺�Ʈ
				enterMain();
			}
		});
		add(startButton);

		leftButton.setVisible(false);
		// ó���� �Ⱥ��̵���
		leftButton.setBounds(40, 310, 64, 64);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				// ���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				// ���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// ���콺�� �ٽ� �������� ��, �⺻ Ŀ���� �ٲ��ֱ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
				// ���ʹ�ư �̺�Ʈ
				selectLeft();
			}
		});
		add(leftButton);
		// exitButton �⺻������ �����Ǵ� ���ø��� �ִµ�, �츮�� ������ ����� �ƴϱ� ������ �ٲ���� �Ѵ�.

		rightButton.setVisible(false);
		// ó���� �Ⱥ��̵���
		rightButton.setBounds(1140, 310, 64, 64);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				// ���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				// ���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// ���콺�� �ٽ� �������� ��, �⺻ Ŀ���� �ٲ��ֱ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
				// �����ʹ�ư �̺�Ʈ
				selectRight();
			}
		});
		add(rightButton);
		// exitButton �⺻������ �����Ǵ� ���ø��� �ִµ�, �츮�� ������ ����� �ƴϱ� ������ �ٲ���� �Ѵ�.

		easyButton.setVisible(false);
		// ó���� �Ⱥ��̵���
		easyButton.setBounds(375, 580, 90, 110);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				// ���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				// ���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// ���콺�� �ٽ� �������� ��, �⺻ Ŀ���� �ٲ��ֱ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
				// easyButton Event
				gameStart(nowSelected, "easy");
			}
		});
		add(easyButton);
		// exitButton �⺻������ �����Ǵ� ���ø��� �ִµ�, �츮�� ������ ����� �ƴϱ� ������ �ٲ���� �Ѵ�.

		hardButton.setVisible(false);
		// ó���� �Ⱥ��̵���
		hardButton.setBounds(665, 580, 90, 110);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				// ���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				// ���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// ���콺�� �ٽ� �������� ��, �⺻ Ŀ���� �ٲ��ֱ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
				// hardButton Event
				gameStart(nowSelected, "hard");
			}
		});
		add(hardButton);
		// exitButton �⺻������ �����Ǵ� ���ø��� �ִµ�, �츮�� ������ ����� �ƴϱ� ������ �ٲ���� �Ѵ�.

		backButton.setVisible(false);
		// ó���� �Ⱥ��̵���
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				// ���콺�� �ö����� entered �̹����� �ٲ��ش� ��
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺�� exit ��ư�� �ö��� ��, Ŀ���� �� ������� �ٲ���
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				// ���콺�� �ٽ� �������� ��, basic �̹����� �ٲ��ֱ�
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// ���콺�� �ٽ� �������� ��, �⺻ Ŀ���� �ٲ��ֱ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> �ѹ��� �������شٴ� ��
				buttonEnteredMusic.start();
				// mainȭ������ ���ư��� �̺�Ʈ
				backMain();
			}
		});
		add(backButton);
		// exitButton �⺻������ �����Ǵ� ���ø��� �ִµ�, �츮�� ������ ����� �ƴϱ� ������ �ٲ���� �Ѵ�.

		menuBar.setBounds(0, 0, 1280, 30);
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
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();

				setLocation(x - mouseX, y - mouseY);

				// �巡���Ҷ� ������������ ���콺�� x,y��ǥ�� �����ͼ� GUIâ�� �ű� �� �ְ� ����
			}
		});
		add(menuBar);

	}

	public void paint(Graphics g) {
		// JFRAME�� ��ӹ��� ���ӿ��� ���� ���� ȭ���� �׷��ִ� �Լ� : ��ӵ� ��
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// ũ�⸸ŭ �̹����� ���� �ڿ� �װ� screenGraphic�� �־��� ����
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		// ��ũ�� �׷��ȿ� ��� �׸��� �׷��ִ°�, ���� screenDraw��� �Լ��� �������� �ʾ���
		g.drawImage(screenImage, 0, 0, null);
		// ��ũ�� �̹����� (0,0) ��ġ�� �׷��� ����
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		// ��Ʈ�� ��׶��带 0,0 ��ġ�� �׷��� �Ź� �ٲ�� �̹����� ��쿡 �����

		if (isMainScreen) {
			// ���� ��ũ�� ����� = ���� �����ϸ� �̰� �ΰ� �ȳ���
			g.drawImage(selectedImage, 340, 100, null);
			// �߰��Ȱ� �ƴ϶� �׳� �ڿ� ��׶���? ������
			g.drawImage(titleImage, 500, 80, null);
		}

		if (isGameScreen) {

			g.drawImage(noteRouteImage, 228, 30, null);
			g.drawImage(noteRouteImage, 332, 30, null);
			g.drawImage(noteRouteImage, 436, 30, null);
			g.drawImage(noteRouteImage, 540, 30, null);
			g.drawImage(noteRouteImage, 640, 30, null);
			g.drawImage(noteRouteImage, 744, 30, null);
			g.drawImage(noteRouteImage, 848, 30, null);
			g.drawImage(noteRouteImage, 952, 30, null);

			g.drawImage(noteRouteLineImage, 224, 30, null);
			g.drawImage(noteRouteLineImage, 328, 30, null);
			g.drawImage(noteRouteLineImage, 432, 30, null);
			g.drawImage(noteRouteLineImage, 536, 30, null);
			g.drawImage(noteRouteLineImage, 740, 30, null);
			g.drawImage(noteRouteLineImage, 844, 30, null);
			g.drawImage(noteRouteLineImage, 948, 30, null);
			g.drawImage(noteRouteLineImage, 1052, 30, null);

			g.drawImage(gameInfoImage, 0, 660, null);
			g.drawImage(judgementLineImage, 0, 580, null);

			g.drawImage(noteBasicImage, 228, 120, null);
			g.drawImage(noteBasicImage, 332, 580, null);
			g.drawImage(noteBasicImage, 436, 500, null);
			g.drawImage(noteBasicImage, 540, 340, null);
			g.drawImage(noteBasicImage, 640, 340, null);
			g.drawImage(noteBasicImage, 744, 325, null);
			g.drawImage(noteBasicImage, 848, 305, null);
			g.drawImage(noteBasicImage, 952, 305, null);
			
			g.setColor(Color.white);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("HELLO WORLD", 20, 702);
			g.drawString("EASY",1190,702);
			g.setFont(new Font("Arial", Font.BOLD, 26));
			g.setColor(Color.DARK_GRAY);
			
			
			g.drawString("S",270,609);
			g.drawString("D",374,609);
			g.drawString("F",478,609);
			g.drawString("Space Bar",580,609);
			g.drawString("J",784,609);
			g.drawString("K",889,609);
			g.drawString("L",993,609);
			g.setColor(Color.LIGHT_GRAY);
			
			
			
			g.setFont(new Font("Elephant", Font.BOLD, 30));
			g.drawString("000000",565,702);
		}

		paintComponents(g);
		// �̹����� �ܼ��ϰ� ��ũ�� �̹��� �����ȿ� �׷��ִ� �� �̿ܿ�
		// JLabel�� JFrame�ȿ� �߰��ϸ� �׷��ִ� ���� �ǹ��� �޴��ٴ� �׻� �����ϰ� ������ �̹����̱� ������ paintComponent��
		// �Ѵ�.
		// main frame�� �߰��� (add)�� �͵��� �׸��� ��
		this.repaint();
		// �ٽ� ����Ʈ�Լ��� �ҷ��� -> ������ ����Ǵ� ���� ��� �ݺ��ϸ� ���ȭ���� �׷��ִ� ����
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();

		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);

		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;

		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;

		selectTrack(nowSelected);
	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		isMainScreen = false;
		// ���� ���� �ƴ�
		backButton.setVisible(true);
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		quitButton.setVisible(false);
		isGameScreen = true;

		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
	}

	public void backMain() {
		isMainScreen = true;
		backButton.setVisible(false);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		isGameScreen = false;
		background = new ImageIcon(Main.class.getResource("../images/MainBackGround.jpg")).getImage();
		selectTrack(nowSelected);
	}

	public void enterMain() {
		selectTrack(0);
		background = new ImageIcon(Main.class.getResource("../images/MainBackGround.jpg")).getImage();
		// background�� �� �̹��� ���� ���� -> paintComponent�� ����� ���� -> ��� ������ ����ȴ�.
		isMainScreen = true;
		startButton.setVisible(false);// start button �Ⱥ��̰�
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		quitButton.setBounds(10, 650, 64, 64);
		introMusic.close();// intro ���� ����
		// game.start();
	}

}
