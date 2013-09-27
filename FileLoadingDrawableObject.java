import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Class which represents a drawable object obtained from a file.
//Reads details from file such as height and width, and then reads characters which maps to corresponding colours.
public class FileLoadingDrawableObject extends DrawableObject 
{
	private int height, width, grid[][];
	private BufferedReader input;
	
	// If any type of exception is thrown here, the program terminates as it cannot go on without proper input.
	public FileLoadingDrawableObject(String filename) 
	{
		String textLine = null;
		
		 try
         {
                 input = new BufferedReader(new FileReader(filename));
         } catch (FileNotFoundException e)
         {
                 System.out.println("The file " +filename+ " could not be found");
                 e.printStackTrace();
             	 System.exit(1);
         }
         try { // Read the file line by line
			textLine = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
         width = Integer.parseInt(textLine); // First line is width. Convert the character to int
         try {
			textLine = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
         height = Integer.parseInt(textLine); // Second line is height. Convert the character to int
         grid = new int[width][height];
         for(int i = 0; i < height; i++)
         {
        	 try {
				textLine = input.readLine(); // Read each row
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
        	 for( int j = 0; j < width; j++)
        	 {
        		 grid[j][i] = colourMapper(textLine.charAt(j)); // For each column of the row map the corresponding character to its colour
        	 }
         }
         
	}
	
	public FileLoadingDrawableObject(FileLoadingDrawableObject cpy)
	{
		height = cpy.getHeight();
		width = cpy.getWidth();
		grid = new int[width][height];
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				grid[i][j] = cpy.getColour(i, j);
	}

	public void draw(GraphicsController arg0) 
	{
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				arg0.setGridColour(i, j, grid[i][j]);
}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getColour(int coordX, int coordY)
	{
		return grid[coordX][coordY];
	}
	
	public void setColour(int colour, int coordX, int coordY)
	{
		grid[coordX][coordY] = colour;
	}
	
	private int colourMapper(char inputColour) 
	{
		switch(inputColour)
		{
		case 'B' : return GraphicsController.BLACK;
		case 'w' : return GraphicsController.WHITE;
		case 'y' : return GraphicsController.YELLOW;
		case 'b' : return GraphicsController.BLUE;
		case 'r' : return GraphicsController.RED;
		case 'g' : return GraphicsController.GREEN;
		case 'o' : return GraphicsController.ORANGE;
		case 'p' : return GraphicsController.PINK;
		case 'P' : return GraphicsController.PURPLE;
		default : try {  // If none of the above characters/colours is found, throw an UnsupportedColour Exception
					throw new UnsupportedColourException(inputColour);
				} catch (UnsupportedColourException e) {
					e.printStackTrace();
					System.exit(1);
				}
				return -1;
		}
	}
}
