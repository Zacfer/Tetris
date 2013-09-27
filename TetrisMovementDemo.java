// Tests the TetrisDrawableObject class
public class TetrisMovementDemo extends WindowController 
{
	private TetrisDrawableObject testShape;
	private int shapeIndex = 1;
	
	public TetrisMovementDemo()
	{
		super("Tetris Movement", 1, 10, 20, 25, true);
		testShape = new TetrisDrawableObject("shape1a.txt", "shape1b.txt", "shape1c.txt", "shape1d.txt");
		this.start();
	}

	
	protected void buttonPressed(int arg0) 
	{
		if(shapeIndex != 7)
			shapeIndex++;
		else
			shapeIndex = 1; // Load a new shape each time the button is pressed.
		testShape = new TetrisDrawableObject("shape" +shapeIndex+ "a.txt", "shape" +shapeIndex+ "b.txt", "shape" +shapeIndex+ "c.txt", "shape" +shapeIndex+ "d.txt");

	}

	protected void keyPressed(Key arg0) 
	{
		if(arg0.isDown())
			testShape.moveDown();
		else if(arg0.isLeft())
			testShape.moveLeft();
		else if(arg0.isRight())
			testShape.moveRight();
		else if(arg0.isUp())
			testShape.rotate();

	}

	protected void keyReleased(Key arg0) {
		// TODO Auto-generated method stub

	}

	protected void update() 
	{
		for(int x = 0; x < 10; x++)
			for( int y = 0; y < 20; y++)
				setGridColour(x, y, GraphicsController.BLACK);
		draw(testShape);
	}
	
	public static void main(String[] args)
	{
		new TetrisMovementDemo();
	}

}
