package tetrisModel;

public class UserMap implements Cloneable{
	private int mapWidth;
	private int mapHeight;
	
	private int[][] map;
	
	public UserMap(int _mapHeight, int _mapWidth)
	{
		this.mapHeight = _mapHeight;
		this.mapWidth = _mapWidth;
		this.Reset();
	}
	
	public UserMap(int _mapHeight, int _mapWidth, int[][] _map)
	{
		this.mapHeight = _mapHeight;
		this.mapWidth = _mapWidth;
		this.map = _map;
	}
	
	public boolean CheckBLockOverlap(CellComposition block)
	{
		int[][] cells = block.getaxis();
		for(int i = 0 ; i < cells.length ; i ++)
		{
			if(map[ cells[i][0] ][ cells[i][1] ] != 0)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean CheckIfFullAfterSet(CellComposition block)
	{
		if(this.CheckBLockOverlap(block) == true)
			return true;
		
		int highest = block.getHighestRow();
		int lowest = block.getLowestRow();
		
		int[][] cells = block.getaxis();
		
		for(int i = 0 ; i < cells.length ; i ++)
		{
			if(cells[i][0] == lowest)
			{
				if(map[ cells[i][0] + 1 ][ cells[i][1] ] != 0)
					return true;
			}
			
			if(cells[i][0] == highest)
			{
				if(map[ cells[i][0] - 1 ][ cells[i][1] ] != 0)
					return true;
			}
		}
		
		return false;
	}
	
	public boolean CheckIfReachBottom(CellComposition block)
	{
		int[][] cells = block.getaxis();
		
		for(int i = 0 ; i < cells.length ; i ++)
		{
			if(map[ cells[i][0] + 1 ][ cells[i][1] ] != 0)
				return true;
		}
		
		return false;
	}
	
	public boolean SetBlock(CellComposition block, int color)
	{
		boolean success = true;
		int[][] cells = block.getaxis();
		for(int i = 0 ; i < cells.length ; i ++) {
			if(map[ cells[i][0] ][ cells[i][1] ] != 0)
				success = false;
		}
		
		if (success) {
			for(int i = 0 ; i < cells.length ; i ++)
				map[ cells[i][0] ][ cells[i][1] ] = 2;
		}
		
		return success;
	}
	
	public int GetMapHeight()
	{
		return this.map.length;
	}
	
	public int GetMapWidth()
	{
		return this.map[0].length;
	}
	
	public int[][] GetMap()
	{
		return this.map;
	}
	
	public int DeleteIfFull()
	{
		int score = 0;
		int cur = this.mapHeight;
		for(int i = this.mapHeight ; i >= 1 ; i --)
		{
			boolean dismiss = true;
			for(int j = 1 ; j <= this.mapWidth ; j ++)
			{
				if(map[i][j] == 0)
				{
					dismiss = false;
				}
			}
			
			score += dismiss ? 1 : 0;
			if (!dismiss) {
				for(int j = 1 ; j <= this.mapWidth ; j ++)
				{
					map[cur][j] = map[i][j];
				}
				
				cur --;
			}
		}
		
		return score;
	}
	
	public void Reset()
	{
		this.map = new int[this.mapHeight + 2][];
		
		for(int i = 0 ; i < this.mapHeight + 2 ; i ++) {
			map[i] = new int[this.mapWidth + 2];
		}
		
		for(int i = 0 ; i < this.mapHeight + 2 ; i ++) {
			map[i][0] = map[i][this.mapWidth + 1] = 1;
		}
		
		for(int i = 0 ; i < this.mapWidth + 2 ; i ++) {
			map[0][i] = map[this.mapHeight + 1][i] = 1;
		}
	}
	
	public Object clone() {
		int[][] clonedMap = new int[this.map.length][this.map[0].length];
		for(int i = 0 ; i < this.map.length ; i ++)
			for(int j = 0 ; j < this.map[0].length ; j ++)
			{
				clonedMap[i][j] = this.map[i][j];
			}

		UserMap res = new UserMap(this.mapHeight, this.mapWidth, clonedMap);
		return res;
	}

	/*private void Output(){
		for(int i = 1 ; i <= this.mapHeight ; i ++) {
			for(int j = 1 ; j <= this.mapWidth ; j ++)
				System.out.print(this.map[i][j] + " ");

			System.out.println("");
		}
	}*/
}
