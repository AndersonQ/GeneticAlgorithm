package geneticalgorithm;

import java.util.Random;

public class Chromosome
{
	private int[] integer;
	private int[] decimal;
	private static final int BITS = 16;
	
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
	
	/**
	 * This function gets two Chromosomes
	 * and make a crossover of them in a single
	 * point.
	 * 
	 * The chromosome received will be modified
	 * to be the result of crossover. 
	 * 
	 * @param p1 
	 * @param p2
	 */
	public static void crossover(Chromosome p1, Chromosome p2)
	{
		int i, p;
		Chromosome tmp1, tmp2;
		Random r = new Random();
		
		p = r.nextInt(Chromosome.BITS);
		
		
		
		
	}
}
