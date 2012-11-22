package ag;

public class chromosome
{
	private int[] integer;
	private int[] decimal;
	private static final int BITS = 16;
	
	public chromosome()
	{
		int i;
		
		integer = new int[BITS];
		decimal = new int[BITS];
		
		for(i = 0; i < BITS; i++)
		{
			integer[i] = 0;
			decimal[i] = 0;
		}
	}
}