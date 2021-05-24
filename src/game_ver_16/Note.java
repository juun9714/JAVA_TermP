package game_ver_16;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();

	private int x;
	private int y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	// 시작값을 고정(-120), 1초 후에 +700 -> 580이 됨
	private String noteType;
	private boolean proceeded = true;
	//현재 게임이 진행되고 있는지 확인 
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {
		//노트를 성공적으로 입력해서 해당 노트가 더이상 화면에 뜨지 않도록 하는 함수 
		proceeded = false;
	}
	
	
	public Note(String noteType) {
		if (noteType.contentEquals("S")) {
			this.x = 228;
		} else if (noteType.contentEquals("D")) {
			this.x = 332;
		} else if (noteType.contentEquals("F")) {
			this.x = 436;
		} else if (noteType.contentEquals("Space")) {
			this.x = 540;
		} else if (noteType.contentEquals("J")) {
			this.x = 744;
		} else if (noteType.contentEquals("K")) {
			this.x = 848;
		} else if (noteType.contentEquals("L")) {
			this.x = 952;
		}

		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null);
		} else {
			// space Bar
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}

	public void drop() {
		y += Main.NOTE_SPEED;
		if(y>620) {
			System.out.println("MISS");
			close();
		}
		// 1초에 700픽셀만큼 아래로 떨어짐
		// 0.01초에 7픽셀
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
					//현재 노트가 아직 안죽었으면? 계속 노트가 쉬면서 아래로 내려올 수 있도록 하는 것 
				}else {
					interrupt();
					break;
				}
				
				// 1000 = 1초, 10 = 0.01초
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String judge() {
		if(y>=613) {
			System.out.println("LATE");
			close();
			return "Late";
		}else if(y>=600) {
			System.out.println("GOOD");
			close();
			return "Good";
		}else if(y>=587) {
			System.out.println("GREAT");
			close();
			return "Great";
		}else if(y>=573) {
			System.out.println("PERFECT");
			close();
			return "Perfect";
		}else if(y>=565) {
			System.out.println("GREAT");
			close();
			return "Great";
		}else if(y>=550) {
			System.out.println("EARLY");
			close();
			return "Early";
		}
		return "None";
	}
	
	public int getY() {
		return y;
	}
}
