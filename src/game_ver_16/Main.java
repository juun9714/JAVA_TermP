package game_ver_16;

public class Main {

	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 3; //note speed
	public static final int SLEEP_TIME = 5; //��Ʈ�� ���� �����Ǵ� �ӵ�?
	public static final int REACH_TIME = 2; 
	//public static : ������Ʈ ������ ��ΰ� ����� �� ����
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Galaga();
		//double buffering, �� �������� ȭ�鿡 �´� �̹����� ���������ν� ȭ�鿡 �ʿ��� ��ŭ�� �������ش�. 
	}
}


//ver2. ���� ���ȭ�� �����ֱ� 
//ver3. ������� �����ֱ� 
//ver5. ȭ�� ��ȯ (by button)
//ver9. �����丵
//ver10. gameȭ�� ������ 
//ver16. judge motion 