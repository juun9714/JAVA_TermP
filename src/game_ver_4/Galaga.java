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
	// double-buffering을 위해서 전체 화면에 대한 이미지를 담는 두 인스턴스

	private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBackGround.jpg")).getImage();
	// 이미지를 담을 객체
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/bar.png")));
	
	
	private ImageIcon exitButtonEnteredImage=new ImageIcon(Main.class.getResource("../images/exit_clked.png"));
	private ImageIcon exitButtonBasicImage=new ImageIcon(Main.class.getResource("../images/exit.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private int mouseX, mouseY;
	//메뉴바를 드래그했을 때, 뭐가 변경되도록 해주겠음 현재 프로그램에서 마우스의 x,y좌표

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
		

		exitButton.setBounds(1240, 0, 32, 32);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				//마우스가 올라갔을때 entered 이미지로 바꿔준는 뜻
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//마우스가 exit 버튼에 올라갔을 때, 커서를 손 모양으로 바꿔줌
				Music buttonEnteredMusic = new Music("click_music.mp3",false);
				//false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				//마우스가 다시 내려갔을 때, basic 이미지로 바꿔주기
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				//마우스가 다시 내려갔을 때, 기본 커서로 바꿔주기 
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("click_music.mp3",false);
				//false -> 한번만 실행해준다는 뜻
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
					//버튼 효과음을 다 듣고 종료 
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
				//exit 버튼 눌렀을 때, 게임이 종료됨 
			}
		});
		add(exitButton);
		// exitButton 기본적으로 제공되는 템플릿이 있는데, 우리가 생각한 모양이 아니기 때문에 바꿔줘야 한다.
		
		

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
				
				//드래그할때 순간순간마다 마우스의 x,y좌표를 가져와서 GUI창을 옮길 수 있게 해줌 
			}
		});
		add(menuBar);
		
		Music introMusic = new Music("intro_music.mp3", true);
		introMusic.start();
	}

	public void paint(Graphics g) {
		// JFRAME을 상속받은 게임에서 가장 먼저 화면을 그려주는 함수 : 약속된 것
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// 크기만큼 이미지를 만든 뒤에 그걸 screenGraphic에 넣어줄 것임
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		// 스크린 그래픽에 어떠한 그림을 그려주는것, 아직 screenDraw라는 함수를 구현하진 않았음
		g.drawImage(screenImage, 0, 0, null);
		// 스크린 이미지를 (0,0) 위치에 그려줄 것임
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		// 인트로 백그라운드를 0,0 위치에 그려줌 매번 바뀌는 이미지의 경우에 사용함
		paintComponents(g);
		// 이미지를 단순하게 스크린 이미지 변수안에 그려주는 것 이외에
		// JLabel을 JFrame안에 추가하면 그려주는 것을 의미함 메뉴바는 항상 존재하고 고정된 이미지이기 때문에 paintComponent로
		// 한다.
		this.repaint();
		// 다시 페인트함수를 불러옴 -> 게임이 진행되는 동안 계속 반복하며 배경화면을 그려주는 것임
	}
}
