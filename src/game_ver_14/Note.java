package game_ver_14;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();

	private int x;
	private int y = 580 - 1000 / Main.SLEEP_TIME * Main.NOTE_SPEED;
	// 시작값을 고정(-120), 1초 후에 +700 -> 580이 됨 
	private String noteType;

	public Note(int x, String noteType) {
		this.x = x;
		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		if (noteType.equals("short")) {
			g.drawImage(noteBasicImage, x, y, null);
		} else if (noteType.equals("long")) {
			// space Bar
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}

	public void drop() {
		y += Main.NOTE_SPEED;
		// 1초에 700픽셀만큼 아래로 떨어짐
		// 0.01초에 7픽셀
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				Thread.sleep(Main.SLEEP_TIME);
				// 1000 = 1초, 10 = 0.01초
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
