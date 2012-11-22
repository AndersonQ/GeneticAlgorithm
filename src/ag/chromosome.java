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
	
	public double getValue()
	{
		int i;
		int pint = 0, pdec = 0;
		
		for(i = 0; i < BITS; i++)
		{
			pint += integer[i]*(Math.pow(2, i + 1));
			pdec += decimal[i]*(Math.pow(2, i + 1));
		}
		
		return Double.parseDouble(String.format("%d.%d", pint, pdec));
	}
}