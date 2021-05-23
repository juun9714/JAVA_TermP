package game_ver_8;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class Music extends Thread{
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		//곡의 제목, 해당 곡이 무한반복인지 여부 입력
		try {
			this.isLoop=isLoop;
			file=new File(Main.class.getResource("../music/"+name).toURI());
			fis = new FileInputStream(file);
			bis =  new BufferedInputStream(fis);
			player = new Player(bis);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {
		//현재 실행되고 있는 음악이 어느 부분에서 실행되고 있는지 확인
		if(player==null) 
			return 0;
		return player.getPosition();
		//0.001초 단위까지 알려줌
		//노트를 떨어트릴 때, 이 함수를 이용해서 시간을 분서하게 된다.
	}
	
	public void close() {
		//항상 음악을 종료할 수 있도록
		isLoop = false;
		player.close();//종료
		this.interrupt();//해당 스레드를 중지상태로 만드는 것, 우리에게 곡을 들려주는 music이라는 작은 프로그램을 종료하는 것
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis =  new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
