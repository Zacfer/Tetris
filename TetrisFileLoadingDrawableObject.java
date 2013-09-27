// Holds the information for one of the tetris shapes' rotation. Also checks for black columns in the left and right sides.
public class TetrisFileLoadingDrawableObject extends FileLoadingDrawableObject 
{
	private int coordX, coordY;
	
	public TetrisFileLoadingDrawableObject(String filename)
	{
		super(filename); 
		coordX = 0;
		coordY = 0;
	}
	
	public TetrisFileLoadingDrawableObject(String filename, int coordX, int coordY)
	{
		super(filename);
		this.coordX = coordX;
		this.coordY = coordY;
	}
	
	public TetrisFileLoadingDrawableObject(TetrisFileLoadingDrawableObject cpy)
	{
		super(cpy);
		cpy.coordX = this.coordX;
		cpy.coordY = this.coordY;
	}
	
	public int getCoordX()
	{
		return coordX;
	}
	
	public int getCoordY()
	{
		return coordY;
	}
	
	public void setCoordX(int newCoordX)
	{
		coordX = newCoordX;
	}
	
	public void setCoordY(int newCoordY)
	{
		coordY = newCoordY;
	}
	
	public boolean leftColumnIsBlack() // Checks whether the left most squares of a shape are all black
	{
		int numberOfBlackSquares = 0;
		
		for(int j = 0; j < getHeight(); j++)
			if(getColour(0, j) == GraphicsController.BLACK)
				numberOfBlackSquares++; // Count all of the black squares of the left column
		if(numberOfBlackSquares == getHeight()) // If they match the height, then the left column is black
			return true;
		
		return false;
	}
	
	public boolean rightColumnIsBlack() // Same as above, for right column
	{
		int numberOfBlackSquares = 0;
		
		for(int j = 0; j < getHeight(); j++)
			if(getColour((getWidth()-1), j) == GraphicsController.BLACK)
				numberOfBlackSquares++;
		if(numberOfBlackSquares == getHeight())
			return true;
		
		return false;
	}
	
	public boolean left2ColumnsAreBlack() // Checks whether the left 2 columns are black
	{
		int numberOfBlackSquares = 0;
		
		for(int i = 0; i < 2; i++)
			for(int j = 0; j < getHeight(); j++)
				if(getColour(i, j) == GraphicsController.BLACK) // Same method as for 1 column, except this time the 2 left most columns 
					numberOfBlackSquares++;						// are counted and compared to twice the height
		if(numberOfBlackSquares == (getHeight()*2))
			return true;
		
		return false;
	}
	
	public boolean right2ColumnsAreBlack() // Same as above for right 2 columns
	{
		int numberOfBlackSquares = 0;
		
		for(int i = getWidth()-2; i < getWidth(); i++)
			for(int j = 0; j < getHeight(); j++)
				if(getColour(i, j) == GraphicsController.BLACK)
					numberOfBlackSquares++;
		if(numberOfBlackSquares == (getHeight()*2))
			return true;
		
		return false;
	}
	
	public void draw(GraphicsController arg0) 
	{
		for(int i = 0; i < getWidth(); i++)
			for(int j = 0; j < getHeight(); j++)
				if(getColour(i, j) != GraphicsController.BLACK)
					arg0.setGridColour(i + coordX, j + coordY, getColour(i, j));
	}	
}
