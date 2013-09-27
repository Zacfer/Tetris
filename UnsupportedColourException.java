
public class UnsupportedColourException extends RuntimeException // Happens at runtime, if an unrecognisable colour is given in a file
{
	private static final long serialVersionUID = 1L;
	
	public UnsupportedColourException(char colour)
	{
		System.out.println("The character '" +colour+ "' in the file is an unrecognisable colour.");
	}

}
