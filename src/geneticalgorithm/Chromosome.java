package geneticalgorithm;

import java.util.Random;

public class Chromosome
{
	/** Integer part of chromosome */
	private int[] integer;
	/** Float part of chromosome */
	private int[] decimal;
	/** Number of bits of each chromosome part */
	private static int BITS = 16;
	
	public Chromosome()
	{
		int i;
		Random r = new Random();
		
		integer = new int[BITS];
		decimal = new int[BITS];
		
		for(i = 0; i < BITS; i++)
		{
			if(r.nextBoolean())
				integer[i] = 1;
			else
				integer[i] = 0;
			
			if(r.nextBoolean())
				decimal[i] = 1;
			else
				decimal[i] = 0;
		}
	}
	
	public Chromosome(int bits)
	{
		int i;
		Random r = new Random();
		
		this.BITS = bits;
		
		integer = new int[BITS];
		decimal = new int[BITS];
		
		for(i = 0; i < BITS; i++)
		{
			if(r.nextBoolean())
				integer[i] = 1;
			else
				integer[i] = 0;
			
			if(r.nextBoolean())
				decimal[i] = 1;
			else
				decimal[i] = 0;
		}
		
	}
	
	public double getValue()
	{
		int i;
		int pint = 0, pdec = 0;
		
		for(i = 0; i < BITS; i++)
		{
			pint += integer[i]*(Math.pow(2, i));
			pdec += decimal[i]*(Math.pow(2, i));
		}
		
		return Double.parseDouble(String.format("%d.%d", pint, pdec));
	}
}
