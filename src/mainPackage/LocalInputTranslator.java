package mainPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.TetrisController;

public class LocalInputTranslator implements KeyListener{
	TetrisController controller;
	public LocalInputTranslator(TetrisController _controller) {
		this.controller = _controller;
	}
	
    @Override
    // Hard code first, use map to translate this:
    public void keyPressed(KeyEvent e) {
    	int code = e.getKeyCode();
    	switch(code)
    	{
    	case KeyEvent.VK_SPACE:
    		controller.PauseOrStart();
    		break;
    	case KeyEvent.VK_W:
    		controller.UpdateUserAction(0, UserAction.Up);
    		break;
    	case KeyEvent.VK_S:
    		controller.UpdateUserAction(0, UserAction.Down);
    		break;
    	case KeyEvent.VK_A:
    		controller.UpdateUserAction(0, UserAction.Left);
    		break;
    	case KeyEvent.VK_D:
    		controller.UpdateUserAction(0, UserAction.Right);
    		break;
    	case KeyEvent.VK_UP:
    		controller.UpdateUserAction(1, UserAction.Up);
    		break;
    	case KeyEvent.VK_DOWN:
    		controller.UpdateUserAction(1, UserAction.Down);
    		break;
    	case KeyEvent.VK_LEFT:
    		controller.UpdateUserAction(1, UserAction.Left);
    		break;
    	case KeyEvent.VK_RIGHT:
    		controller.UpdateUserAction(1, UserAction.Right);
    		break;
    	}
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    	
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    
}
