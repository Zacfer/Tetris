// Used to test the grid.
public class BasicTetrisGridDemo extends WindowController 
{
	private TetrisDrawableObject testShape;
	private BasicTetrisGrid testGrid;
	private int shapeIndex = 1;
	
	public BasicTetrisGridDemo()
	{
		super("Basic Tetris Grid", 1, 10, 20, 25, true);
		testShape = new TetrisDrawableObject("shape4a.txt", "shape4b.txt", "shape4c.txt", "shape4d.txt");
		testGrid = new BasicTetrisGrid();
		this.start();
	}
	
	protected void buttonPressed(int arg0) 
	{
		testGrid.add(testShape);
		testShape = new TetrisDrawableObject("shape4a.txt", "shape4b.txt", "shape4c.txt", "shape4d.txt");
	}

	protected void keyPressed(Key arg0) 
	{
		if(arg0.isDown())
		{
			if(testGrid.canMoveDown(testShape))
				testShape.moveDown();
		}
		else if(arg0.isLeft())
		{
			if(testGrid.canMoveLeft(testShape))
				testShape.moveLeft();
		}
		else if(arg0.isRight())
		{
			if(testGrid.canMoveRight(testShape))
				testShape.moveRight();
		}
		else if(arg0.isUp())
		{
			if(testGrid.canRotate(testShape))
				testShape.rotate();
		}

	}

	protected void keyReleased(Key arg0) 
	{

	}

	protected void update() 
	{
		for(int x = 0; x < 10; x++)
			for( int y = 0; y < 20; y++)
				setGridColour(x, y, GraphicsController.BLACK);
		draw(testGrid);
		draw(testShape);
	}
	
	public static void main(String[] args)
	{
		new BasicTetrisGridDemo();
	}

}
