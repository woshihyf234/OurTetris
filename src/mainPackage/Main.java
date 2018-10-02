package mainPackage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.TetrisController;
import jpanelView.TetrisView;
import tetrisModel.TetrisPlayerModel;

public class Main{
	public static void main(String[] args) {
		int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		
		JFrame frame=new JFrame("Tetris");// ����һ����Ϸ����Ŀ��
		frame.setBounds(0,0,width / 3 * 2, height);//���ÿ�ܵĴ�С    ��ܴ�СҪЭ����Ȼ��ʾ������
		//frame.setResizable(false);//���ÿ�ܴ�СΪ���ܸı�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ����رհ�ť �ر���Ϸ����
		
		String[] options= {"����ģʽ", "����ģʽ"};
		int option=JOptionPane.showOptionDialog(null,"ģʽѡ��","ģʽ", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null, options,options[0]);

		TetrisView view = new TetrisView();
		TetrisController controller = new TetrisController(view);
		LocalInputTranslator translator = new LocalInputTranslator(controller);
		frame.addKeyListener(translator);
		
		if(option == 0)
		{
			controller.registerPlayer(new TetrisPlayerModel(controller));
			controller.registerPlayer(new TetrisPlayerModel(controller));
		}
		
		frame.add(view);
		frame.setVisible(true);
		view.SetMaps(controller.getMap(), controller.GetControllerState());
		view.repaint();
	}

}

