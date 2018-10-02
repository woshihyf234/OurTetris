package jpanelView;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import java.util.EventListener;

import tetrisModel.UserMap;

public class TetrisView extends JPanel implements EventListener{
	private final int cellwidth;
	private static final long serialVersionUID = 100;
	
	private UserMap[] maps;
	private int totalHeight;
	private int totalWidth;
	private int playerCnt;
	private int controllerState; // 0: running 1: pause 2: finish
	
	public TetrisView() {
		int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		this.cellwidth = height / 40;
	}
	
	public void SetMaps(UserMap[] _maps, int _controllerState) {
		this.maps = _maps;
		this.playerCnt = this.maps.length;
		this.totalHeight = this.maps[0].GetMapHeight();
		this.totalWidth = this.maps[0].GetMapWidth();
		this.controllerState = _controllerState;
	}
	
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,cellwidth*totalWidth*playerCnt,cellwidth*totalHeight);

        for(int k = 0 ; k < this.playerCnt ; k ++) {
        	int[][] curMap = this.maps[k].GetMap();
        	for(int i=0;i<this.totalHeight;i++) {

                for(int j=0;j<this.totalWidth;j++) {
                	if(curMap[i][j] == 0) {
                		continue;
                	}
                	else if(curMap[i][j] == 1) {
                		g.setColor(Color.GREEN);
                	}
                	else if(curMap[i][j] == 2) {
                		g.setColor(Color.BLUE);
                	}
                	else
                		continue;
                    int x = cellwidth * i;
                    int y = cellwidth * j + k * cellwidth * this.totalWidth;
                    drawCell(g, x, y);
    	        }
            }
        }

        //TODO:
    	if (this.controllerState == 1){ 
            g.setColor(Color.BLACK);
            g.setFont(new Font("ËÎÌו",Font.BOLD, 25));
            g.drawString("Press space to start!", cellwidth * this.totalWidth * playerCnt / 4,cellwidth * this.totalHeight / 2);
        }

		if (this.controllerState == 2){
            g.setColor(Color.RED); 
            g.setFont(new Font("ËÎÌו",Font.BOLD, 30));
            g.drawString("Game Over ! ", cellwidth * this.totalWidth * playerCnt / 4,cellwidth * this.totalHeight / 2);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
    	this.repaint();
    }
    
    private void drawCell(Graphics g, int x, int y) {
        g.drawRect(y,x,this.cellwidth, this.cellwidth);
        g.fillRect(y,x,this.cellwidth, this.cellwidth);
        g.setColor(Color.BLACK);
        g.drawLine(y, x, y + this.cellwidth, x);
        g.drawLine(y, x, y, x + this.cellwidth);
        g.drawLine(y + this.cellwidth, x, y + this.cellwidth, x + this.cellwidth);
        g.drawLine(y, x + this.cellwidth, y + this.cellwidth, x + this.cellwidth);
    }
}
