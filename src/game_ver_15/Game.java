package game_ver_15;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();

	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	private String titleName;// ???? ?̸?
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);

	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);

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

		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if(!note.isProceeded()) {
				//???????? ?Ѿ ??Ʈ?? ȭ?鿡?? ?????ֱ? 
				noteList.remove(i);
				i--;
			}else {
				note.screenDraw(g);
			}
		}

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);
		g.setFont(new Font("Arial", Font.BOLD, 26));
		g.setColor(Color.DARK_GRAY);

		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		g.setColor(Color.LIGHT_GRAY);

		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("000000", 565, 702);

	}

	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("bit.mp3", false).start();
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("bit.mp3", false).start();
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("bit.mp3", false).start();
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("bit.mp3", false).start();
	}

	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("bit.mp3", false).start();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("bit.mp3", false).start();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("bit.mp3", false).start();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes(this.titleName,this.difficulty);
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes(String titleName, String difficulty) {
		Beat[] beats = null;
		
		if (titleName.equals("song_one") && difficulty.equals("Easy")) {
			int startTime = 4460 - (Main.REACH_TIME * 1000);
			int gap = 125;//1/8 second
			beats = new Beat[] { new Beat(startTime, "Space"), new Beat(startTime + gap * 1, "Space"),
					new Beat(startTime + gap * 3, "S"), new Beat(startTime + gap * 5, "Space"),
					new Beat(startTime + gap * 7, "S"), new Beat(startTime + gap * 9, "Space"),
					new Beat(startTime + gap * 11, "Space"), new Beat(startTime + gap * 13, "Space"),
					new Beat(startTime + gap * 15, "D"), new Beat(startTime + gap * 17, "Space"),
					new Beat(startTime + gap * 19, "Space"), new Beat(startTime + gap * 21, "Space"),
					new Beat(startTime + gap * 23, "F"), new Beat(startTime + gap * 25, "Space"), };
		}else if (titleName.equals("song_one") && difficulty.equals("Hard")) {
			int startTime = 4460 - (Main.REACH_TIME * 1000);
			int gap = 125;//1/8 second
			beats = new Beat[] { new Beat(startTime, "Space"), new Beat(startTime + gap * 1, "Space"),
					new Beat(startTime + gap * 3, "S"), new Beat(startTime + gap * 5, "Space"),
					new Beat(startTime + gap * 7, "S"), new Beat(startTime + gap * 9, "Space"),
					new Beat(startTime + gap * 11, "Space"), new Beat(startTime + gap * 13, "Space"),
					new Beat(startTime + gap * 15, "D"), new Beat(startTime + gap * 17, "Space"),
					new Beat(startTime + gap * 19, "Space"), new Beat(startTime + gap * 21, "Space"),
					new Beat(startTime + gap * 23, "F"), new Beat(startTime + gap * 25, "Space"), };
		} else if (titleName.equals("song_two") && difficulty.equals("Easy")) {
			int startTime = 2000 - (Main.REACH_TIME * 1000);
			int gap = 125;
			beats = new Beat[] { new Beat(startTime, "Space"), new Beat(startTime + gap * 1, "Space"),
					new Beat(startTime + gap * 3, "Space"), new Beat(startTime + gap * 5, "Space"),
					new Beat(startTime + gap * 7, "Space"), new Beat(startTime + gap * 9, "D"),
					new Beat(startTime + gap * 11, "Space"), new Beat(startTime + gap * 13, "Space"),
					new Beat(startTime + gap * 15, "J"), new Beat(startTime + gap * 17, "S"),
					new Beat(startTime + gap * 19, "K"), new Beat(startTime + gap * 21, "Space"),
					new Beat(startTime + gap * 23, "L"), new Beat(startTime + gap * 25, "Space"), };
		} else if (titleName.equals("song_two") && difficulty.equals("Hard")) {
			int startTime = 2000 - (Main.REACH_TIME * 1000);
			int gap = 125;
			beats = new Beat[] { new Beat(startTime, "Space"), new Beat(startTime + gap * 1, "Space"),
					new Beat(startTime + gap * 3, "Space"), new Beat(startTime + gap * 5, "Space"),
					new Beat(startTime + gap * 7, "Space"), new Beat(startTime + gap * 9, "D"),
					new Beat(startTime + gap * 11, "Space"), new Beat(startTime + gap * 13, "Space"),
					new Beat(startTime + gap * 15, "J"), new Beat(startTime + gap * 17, "S"),
					new Beat(startTime + gap * 19, "K"), new Beat(startTime + gap * 21, "Space"),
					new Beat(startTime + gap * 23, "L"), new Beat(startTime + gap * 25, "Space"), };
		}else if (titleName.equals("song_three") && difficulty.equals("Easy")) {
			int startTime = 3000 - (Main.REACH_TIME * 1000);
			int gap = 125;
			beats = new Beat[] { new Beat(startTime, "Space"), new Beat(startTime + gap * 1, "Space"),
					new Beat(startTime + gap * 3, "Space"), new Beat(startTime + gap * 5, "Space"),
					new Beat(startTime + gap * 7, "Space"), new Beat(startTime + gap * 9, "Space"),
					new Beat(startTime + gap * 11, "Space"), new Beat(startTime + gap * 13, "Space"),
					new Beat(startTime + gap * 15, "Space"), new Beat(startTime + gap * 17, "Space"),
					new Beat(startTime + gap * 19, "Space"), new Beat(startTime + gap * 21, "Space"),
					new Beat(startTime + gap * 23, "Space"), new Beat(startTime + gap * 25, "Space"), };
		}else if (titleName.equals("song_three") && difficulty.equals("Hard")) {
			int startTime = 3000 - (Main.REACH_TIME * 1000);
			int gap = 125;
			beats = new Beat[] { new Beat(startTime, "Space"), new Beat(startTime + gap * 1, "Space"),
					new Beat(startTime + gap * 3, "Space"), new Beat(startTime + gap * 5, "Space"),
					new Beat(startTime + gap * 7, "Space"), new Beat(startTime + gap * 9, "Space"),
					new Beat(startTime + gap * 11, "Space"), new Beat(startTime + gap * 13, "Space"),
					new Beat(startTime + gap * 15, "Space"), new Beat(startTime + gap * 17, "Space"),
					new Beat(startTime + gap * 19, "Space"), new Beat(startTime + gap * 21, "Space"),
					new Beat(startTime + gap * 23, "Space"), new Beat(startTime + gap * 25, "Space"), };
		}

		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void judge(String input) {
		for(int i=0;i<noteList.size();i++) {
			//index?? 0???? ?????ϱ? ?????? ?ڿ??????? ???? ?Ʒ? ?ִ? ??Ʈ???? ?????ϰ? ?? 
			Note note=noteList.get(i);
			
			if(input.equals(note.getNoteType())) {
				note.judge();
				break;
			}
		}
	}
}
