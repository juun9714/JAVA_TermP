package game_ver_16;

public class Main {

	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 3; //note speed
	public static final int SLEEP_TIME = 5; //노트가 새로 생성되는 속도?
	public static final int REACH_TIME = 2; 
	//public static : 프로젝트 내에서 모두가 사용할 수 있음
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Galaga();
		//double buffering, 매 순간마다 화면에 맞는 이미지를 갱신함으로써 화면에 필요한 만큼만 생성해준다. 
	}
}


//ver2. 시작 배경화면 정해주기 
//ver3. 배경음악 정해주기 
//ver5. 화면 전환 (by button)
//ver9. 리팩토링
//ver10. game화면 디자인 
//ver16. judge motion 