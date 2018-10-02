package tetrisModel;
import java.util.Random;

public class CellCompositionFactory {
	
	private int initRow;
	
	private int initCol;
	
	private Random rand;
	
	public static CellCompositionFactory Instance = new CellCompositionFactory(1, 9); 
	
	private final int typeCnt = 6;
	
	private CellCompositionFactory(int _initRow, int _initCol)
	{
		initRow = _initRow;
		initCol = _initCol;
		rand = new Random();
	}
	
	public CellComposition CreateCellComposition()
	{
		int typeNumber = (rand.nextInt() % typeCnt + typeCnt) % typeCnt ;
		switch(typeNumber)
		{
		case 0:
			return CreateLine();
		case 1:
			return CreateTian();
		case 2:
			return CreateZ();
		case 3: 
			return CreateFlipZ();
		case 4:
			return Create7();
		case 5:
			return CreateFlip7();
		default:
			throw new IllegalArgumentException("not accepted typeNumber!");
		}
	}
	
	private CellComposition CreateLine()
	{
		int[] rows = {0,1,2,3};
		int[] cols = {0,0,0,0};
		
		for(int i = 0 ; i < rows.length ; i ++)
		{
			rows[i] += initRow;
			cols[i] += initCol;
		}
		
		return new CellComposition(rows, cols);
	}
	
	private CellComposition CreateTian()
	{
		int[] rows = {0,1,0,1};
		int[] cols = {0,0,1,1};

		for(int i = 0 ; i < rows.length ; i ++)
		{
			rows[i] += initRow;
			cols[i] += initCol;
		}
		
		return new CellComposition(rows, cols);
	}

	private CellComposition CreateZ()
	{
		int[] rows = {0,0,1,1};
		int[] cols = {-1,0,0,1};

		for(int i = 0 ; i < rows.length ; i ++)
		{
			rows[i] += initRow;
			cols[i] += initCol;
		}
		
		return new CellComposition(rows, cols);
	}

	private CellComposition CreateFlipZ()
	{
		int[] rows = {0,0,1,1};
		int[] cols = {1,0,0,-1};

		for(int i = 0 ; i < rows.length ; i ++)
		{
			rows[i] += initRow;
			cols[i] += initCol;
		}
		
		return new CellComposition(rows, cols);
	}
	
	private CellComposition Create7()
	{
		int[] rows = {0,0,1,2};
		int[] cols = {-1,0,0,0};

		for(int i = 0 ; i < rows.length ; i ++)
		{
			rows[i] += initRow;
			cols[i] += initCol;
		}
		
		return new CellComposition(rows, cols);
	}

	private CellComposition CreateFlip7()
	{
		int[] rows = {0,0,1,2};
		int[] cols = {1,0,0,0};

		for(int i = 0 ; i < rows.length ; i ++)
		{
			rows[i] += initRow;
			cols[i] += initCol;
		}
		
		return new CellComposition(rows, cols);
	}
}
