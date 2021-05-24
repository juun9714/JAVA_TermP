package game_ver_14;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();

	private int x;
	private int y = 580 - 1000 / Main.SLEEP_TIME * Main.NOTE_SPEED;
	// ���۰��� ����(-120), 1�� �Ŀ� +700 -> 580�� �� 
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
		// 1�ʿ� 700�ȼ���ŭ �Ʒ��� ������
		// 0.01�ʿ� 7�ȼ�
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				Thread.sleep(Main.SLEEP_TIME);
				// 1000 = 1��, 10 = 0.01��
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
