package game_ver_2;

import javax.swing.JFrame;

public class Galaga extends JFrame{
	
	
	public Galaga() {
		setTitle("Galaga");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_WIDTH);
		setResizable(false);
		//�ѹ� ������� ����â�� ���̰ų� �ø� �� ����
		setLocationRelativeTo(null);
		//����Ϳ� â�� ���߾ӿ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//����â�� �������� ��, ���α׷��� �����ϵ��� �ϴ� ��
		setVisible(true);
		//default���� false��
	}
}
