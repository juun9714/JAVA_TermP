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
		//���� ����, �ش� ���� ���ѹݺ����� ���� �Է�
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
		//���� ����ǰ� �ִ� ������ ��� �κп��� ����ǰ� �ִ��� Ȯ��
		if(player==null) 
			return 0;
		return player.getPosition();
		//0.001�� �������� �˷���
		//��Ʈ�� ����Ʈ�� ��, �� �Լ��� �̿��ؼ� �ð��� �м��ϰ� �ȴ�.
	}
	
	public void close() {
		//�׻� ������ ������ �� �ֵ���
		isLoop = false;
		player.close();//����
		this.interrupt();//�ش� �����带 �������·� ����� ��, �츮���� ���� ����ִ� music�̶�� ���� ���α׷��� �����ϴ� ��
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
