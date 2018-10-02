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
		
		JFrame frame=new JFrame("Tetris");// 创建一个游戏界面的框架
		frame.setBounds(0,0,width / 3 * 2, height);//设置框架的大小    框架大小要协调不然显示不出来
		//frame.setResizable(false);//设置框架大小为不能改变
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点击关闭按钮 关闭游戏界面
		
		String[] options= {"本地模式", "联网模式"};
		int option=JOptionPane.showOptionDialog(null,"模式选择","模式", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null, options,options[0]);

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

