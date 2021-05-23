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
	// double-buffering을 위해서 전체 화면에 대한 이미지를 담는 두 인스턴스
	private int mouseX, mouseY;
	// 메뉴바를 드래그했을 때, 뭐가 변경되도록 해주겠음 현재 프로그램에서 마우스의 x,y좌표
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;

	private Image background = new ImageIcon(Main.class.getResource("../images/introBackGround.jpg")).getImage();
	// 이미지를 담을 객체
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
	// 초기값 설정

	public Galaga() {
		setUndecorated(true);
		// 실행을 했을 때 기본적으로 존재하는 메뉴바가 보이지 않게 된다.
		setTitle("Galaga");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		// 한번 만들어진 게임창을 줄이거나 늘릴 수 없음
		setLocationRelativeTo(null);
		// 모니터에 창이 정중앙에
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 게임창을 종료했을 때, 프로그램도 종료하도록 하는 것
		setVisible(true);
		// default값이 false임

		setBackground(new Color(0, 0, 0, 0));
		// paintComponent를 했을 때, 배경이 회색이 아니라 하얀색이 된다.
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
				// 마우스가 올라갔을때 entered 이미지로 바꿔준는 뜻
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스가 exit 버튼에 올라갔을 때, 커서를 손 모양으로 바꿔줌
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				// 마우스가 다시 내려갔을 때, basic 이미지로 바꿔주기
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 마우스가 다시 내려갔을 때, 기본 커서로 바꿔주기
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
					// 버튼 효과음을 다 듣고 종료
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
				// exit 버튼 눌렀을 때, 게임이 종료됨
			}
		});
		add(exitButton);
		// exitButton 기본적으로 제공되는 템플릿이 있는데, 우리가 생각한 모양이 아니기 때문에 바꿔줘야 한다.

		quitButton.setBounds(40, 330, 64, 64);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				// 마우스가 올라갔을때 entered 이미지로 바꿔준는 뜻
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스가 exit 버튼에 올라갔을 때, 커서를 손 모양으로 바꿔줌
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				// 마우스가 다시 내려갔을 때, basic 이미지로 바꿔주기
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 마우스가 다시 내려갔을 때, 기본 커서로 바꿔주기
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
					// 버튼 효과음을 다 듣고 종료
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
				// exit 버튼 눌렀을 때, 게임이 종료됨
			}
		});
		add(quitButton);
		// exitButton 기본적으로 제공되는 템플릿이 있는데, 우리가 생각한 모양이 아니기 때문에 바꿔줘야 한다.

		startButton.setBounds(40, 200, 64, 64);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				// 마우스가 올라갔을때 entered 이미지로 바꿔준는 뜻
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스가 exit 버튼에 올라갔을 때, 커서를 손 모양으로 바꿔줌
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				// 마우스가 다시 내려갔을 때, basic 이미지로 바꿔주기
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 마우스가 다시 내려갔을 때, 기본 커서로 바꿔주기
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();// 게임 시작 이벤트
				enterMain();
			}
		});
		add(startButton);

		leftButton.setVisible(false);
		// 처음엔 안보이도록
		leftButton.setBounds(40, 310, 64, 64);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				// 마우스가 올라갔을때 entered 이미지로 바꿔준는 뜻
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스가 exit 버튼에 올라갔을 때, 커서를 손 모양으로 바꿔줌
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				// 마우스가 다시 내려갔을 때, basic 이미지로 바꿔주기
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 마우스가 다시 내려갔을 때, 기본 커서로 바꿔주기
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
				// 왼쪽버튼 이벤트
				selectLeft();
			}
		});
		add(leftButton);
		// exitButton 기본적으로 제공되는 템플릿이 있는데, 우리가 생각한 모양이 아니기 때문에 바꿔줘야 한다.

		rightButton.setVisible(false);
		// 처음엔 안보이도록
		rightButton.setBounds(1140, 310, 64, 64);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				// 마우스가 올라갔을때 entered 이미지로 바꿔준는 뜻
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스가 exit 버튼에 올라갔을 때, 커서를 손 모양으로 바꿔줌
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				// 마우스가 다시 내려갔을 때, basic 이미지로 바꿔주기
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 마우스가 다시 내려갔을 때, 기본 커서로 바꿔주기
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
				// 오른쪽버튼 이벤트
				selectRight();
			}
		});
		add(rightButton);
		// exitButton 기본적으로 제공되는 템플릿이 있는데, 우리가 생각한 모양이 아니기 때문에 바꿔줘야 한다.

		easyButton.setVisible(false);
		// 처음엔 안보이도록
		easyButton.setBounds(375, 580, 90, 110);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				// 마우스가 올라갔을때 entered 이미지로 바꿔준는 뜻
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스가 exit 버튼에 올라갔을 때, 커서를 손 모양으로 바꿔줌
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				// 마우스가 다시 내려갔을 때, basic 이미지로 바꿔주기
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 마우스가 다시 내려갔을 때, 기본 커서로 바꿔주기
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
				// easyButton Event
				gameStart(nowSelected, "easy");
			}
		});
		add(easyButton);
		// exitButton 기본적으로 제공되는 템플릿이 있는데, 우리가 생각한 모양이 아니기 때문에 바꿔줘야 한다.

		hardButton.setVisible(false);
		// 처음엔 안보이도록
		hardButton.setBounds(665, 580, 90, 110);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				// 마우스가 올라갔을때 entered 이미지로 바꿔준는 뜻
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스가 exit 버튼에 올라갔을 때, 커서를 손 모양으로 바꿔줌
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				// 마우스가 다시 내려갔을 때, basic 이미지로 바꿔주기
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 마우스가 다시 내려갔을 때, 기본 커서로 바꿔주기
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
				// hardButton Event
				gameStart(nowSelected, "hard");
			}
		});
		add(hardButton);
		// exitButton 기본적으로 제공되는 템플릿이 있는데, 우리가 생각한 모양이 아니기 때문에 바꿔줘야 한다.

		backButton.setVisible(false);
		// 처음엔 안보이도록
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				// 마우스가 올라갔을때 entered 이미지로 바꿔준는 뜻
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스가 exit 버튼에 올라갔을 때, 커서를 손 모양으로 바꿔줌
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				// 마우스가 다시 내려갔을 때, basic 이미지로 바꿔주기
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 마우스가 다시 내려갔을 때, 기본 커서로 바꿔주기
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3", false);
				// false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
				// main화면으로 돌아가는 이벤트
				backMain();
			}
		});
		add(backButton);
		// exitButton 기본적으로 제공되는 템플릿이 있는데, 우리가 생각한 모양이 아니기 때문에 바꿔줘야 한다.

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

				// 드래그할때 순간순간마다 마우스의 x,y좌표를 가져와서 GUI창을 옮길 수 있게 해줌
			}
		});
		add(menuBar);

	}

	public void paint(Graphics g) {
		// JFRAME을 상속받은 게임에서 가장 먼저 화면을 그려주는 함수 : 약속된 것
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// 크기만큼 이미지를 만든 뒤에 그걸 screenGraphic에 넣어줄 것임
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		// 스크린 그래픽에 어떠한 그림을 그려주는것, 아직 screenDraw라는 함수를 구현하진 않았음
		g.drawImage(screenImage, 0, 0, null);
		// 스크린 이미지를 (0,0) 위치에 그려줄 것임
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		// 인트로 백그라운드를 0,0 위치에 그려줌 매번 바뀌는 이미지의 경우에 사용함

		if (isMainScreen) {
			// 메인 스크린 벗어나면 = 게임 시작하면 이거 두개 안나옴
			g.drawImage(selectedImage, 340, 100, null);
			// 추가된게 아니라 그냥 뒤에 백그라운드? 같은거
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
		// 이미지를 단순하게 스크린 이미지 변수안에 그려주는 것 이외에
		// JLabel을 JFrame안에 추가하면 그려주는 것을 의미함 메뉴바는 항상 존재하고 고정된 이미지이기 때문에 paintComponent로
		// 한다.
		// main frame에 추가된 (add)된 것들을 그리는 것
		this.repaint();
		// 다시 페인트함수를 불러옴 -> 게임이 진행되는 동안 계속 반복하며 배경화면을 그려주는 것임
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
		// 이제 메인 아님
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
		// background에 들어갈 이미지 파일 변경 -> paintComponent를 사용한 이유 -> 배경 사진이 변경된다.
		isMainScreen = true;
		startButton.setVisible(false);// start button 안보이게
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		quitButton.setBounds(10, 650, 64, 64);
		introMusic.close();// intro 뮤직 종료
		// game.start();
	}

}
