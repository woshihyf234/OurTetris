package tetrisModel;
import java.util.function.Consumer;

import controller.TetrisController;

public class TetrisPlayerModel {

    UserMap map;
	private CellComposition block;
	
    private int score=0;
    boolean isFailed=false;
    private TetrisController controller;
    
    public TetrisPlayerModel(TetrisController _controller) {
    	this.map = new UserMap(32, 18);
    	block = CellCompositionFactory.Instance.CreateCellComposition();

    	score=0;
    	isFailed=false;
    	this.controller = _controller;
    }

    public int GetScore() {
    	return this.score;
    }
    
    public boolean CheckFailed(CellComposition block) {
    	if(this.isFailed == true) {
    		return true;
    	}
    	
    	if(map.CheckIfFullAfterSet(block) == true) {
    		this.isFailed = true;
    	}
    	
    	return this.isFailed;
    }
    
    public void Up() {
    	takeAction(x -> x.Up());
    }

    public void Down() {
    	takeAction(x -> x.Down());
    }
    
    public void Left() {
    	takeAction(x -> x.Left());
    	
    }
    
    public void Right() {
    	takeAction(x -> x.Right());
    }
    
    public void AutoDown() {
    	takeAction(x -> x.Down());
    }
    
    public UserMap getUserMap() {
    	UserMap mapWithBlock = (UserMap)this.map.clone();
    	mapWithBlock.SetBlock(this.block, 2);
    	
    	return mapWithBlock;
    }
    
    private void takeAction(Consumer<CellComposition> consumer) {
    	if(!this.isFailed) {
    		CellComposition clonedBlock = this.block.clone();
    		consumer.accept(clonedBlock);
    		
    		if(!this.map.CheckBLockOverlap(clonedBlock))
    		{
    			this.block = clonedBlock;
            	controller.FireRepaint();
            	if(this.map.CheckIfReachBottom(block)) {
            		this.map.SetBlock(block, 2);
            		this.score += this.map.DeleteIfFull();
            		this.block = CellCompositionFactory.Instance.CreateCellComposition();
                	this.CheckFailed(this.block);
            	}
    		}
    	}
    }
    
    public void Reset() {
    	map.Reset();
    	this.block = CellCompositionFactory.Instance.CreateCellComposition();
    }
}
