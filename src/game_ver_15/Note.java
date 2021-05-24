package game_ver_15;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();

	private int x;
	private int y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED)*Main.REACH_TIME;
	// ���۰��� ����(-120), 1�� �Ŀ� +700 -> 580�� �� 
	private String noteType;

	public Note(String noteType) {
		if(noteType.contentEquals("S")) {
			this.x=228;
		}else if(noteType.contentEquals("D")) {
			this.x=332;
		}else if(noteType.contentEquals("F")) {
			this.x=436;
		}else if(noteType.contentEquals("Space")) {
			this.x=540;
		}else if(noteType.contentEquals("J")) {
			this.x=744;
		}else if(noteType.contentEquals("K")) {
			this.x=848;
		}else if(noteType.contentEquals("L")) {
			this.x=952;
		}
		
		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null);
		} else{
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
