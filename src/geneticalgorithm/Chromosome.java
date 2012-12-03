package geneticalgorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Chromosome implements Comparable
{
	/** Integer part of chromosome */
	private int[] integer;
	/** Float part of chromosome */
	private int[] decimal;
	/** Number of bits of each chromosome part */
	public static int BITS = 5;
	/** A rank to this chromosome, it means, how good it is to evaluate function*/
	private double rank;

	public Chromosome()
	{
		int i;
		Random r = new Random();

		this.rank = 0.0;
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

		this.rank = 0.0;
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

	public int[] getInteger()
	{
		return integer;
	}

	public void setInteger(int[] integer)
	{
		this.integer = integer;
	}

	public int[] getDecimal()
	{
		return decimal;
	}

	public void setDecimal(int[] decimal)
	{
		this.decimal = decimal;
	}

	public double getRank() {
		return rank;
	}

	public void setRank(double rank) {
		this.rank = rank;
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
	public static Chromosome[] crossover(Chromosome p1, Chromosome p2)
	{
	    int i, p;
	    int[] tmpint, tmpdec, pint1, pint2, pdec1, pdec2;
	    Chromosome ret[];
	    Random r = new Random();

	    ret = new Chromosome[2];
	    ret[0] = new Chromosome();
	    ret[1] = new Chromosome();

	    tmpint = new int[Chromosome.BITS];
	    tmpdec = new int[Chromosome.BITS];

	    /* Copy the vectors of bit to work */
	    pint1 = new int[Chromosome.BITS];
	    pint2 = new int[Chromosome.BITS];
	    pdec1 = new int[Chromosome.BITS];
	    pdec2 = new int[Chromosome.BITS];

	    for(i = 0; i <Chromosome.BITS; i++)
	    {
	        pint1[i] = p1.getInteger()[i];
	        pint2[i] = p2.getInteger()[i];

	        pdec1[i] = p1.getDecimal()[i];
	        pdec2[i] = p2.getDecimal()[i];
	    }

	    /* Get the position to do the crossover */
	    p = r.nextInt(Chromosome.BITS);

	    /*
	     * for 0  to p:
	     * swap the i-th position of p1 and p2
	     * 
	     */
	    for(i = 0; i < p; i++)
	    {
	        /* Store in a temp var */
	        tmpint[i] = pint1[i];
	        tmpdec[i] = pdec1[i];

	        /* get pint2/pdec2 and put it in pint1/pdec1 */
	        pint1[i] = pint2[i];
	        pdec1[i] = pdec2[i];

	        /* get tmpint/tmpdec and put it in pint1/pdec1 */
	        pint2[i] = tmpint[i];
	        pdec2[i] = tmpdec[i];
	    }

	    /* store the new vector in the received objects */
	    ret[0].setInteger(pint1);
	    ret[0].setDecimal(pdec1);

	    ret[1].setInteger(pint2);
	    ret[1].setDecimal(pdec2);

	    return ret;
	}
	
	/**
	 * Mutate a chromosome with a probability
	 * indicated by tax
	 * @param tax the probability of mutation
	 */
	public void mutation (double tax)
	{
	    int[] pint1, pdec1;
	    int i;

	    /* Get the vectors of bit to work */
	    pint1 = this.getInteger();
	    pdec1 = this.getDecimal();

	    /* Select the bit to change */
	    i = (int) Math.floor(Math.random()*Chromosome.BITS);
	    /*
	     * If the generated number is less than or equal
	     * to tax, mutate the i-th position
	     */
	    if(Math.random() <= tax)
	        pint1[i] = (pint1[i]+1)%2;

	    /* Select the bit to change */
        i = (int) Math.floor(Math.random()*Chromosome.BITS);
        /*
         * If the generated number is less than or equal
         * to tax, mutate the i-th position
         */
	    if(Math.random() <= tax)
	        pdec1[i] = (pdec1[i]+1)%2;
	}
	
    /**
     * Use the contest method to
     * create a new population of 
     * chromosomes
     * 
     * @param vec Original population
     * @param n_children Number of children that will be generated
     * @param k Number of chromosomes in each contest
     * @param comp a Comparator to sort chromosomes by Crhomossome.rank
     * @return A new population
     */
    public static Chromosome[] contest(Chromosome[] vec, int n_children, int k, Comparator comp)
    {
        Chromosome ret[], sort[], children[];
        Random r = new Random();
        int i, chro[], j;
        
        chro = new int[k]; 
        sort = new Chromosome[k];
        ret = new Chromosome[n_children];
        
        /* create each child */
        for(i = 0; i < n_children; i++)
        {
            /*
             * Select the challengers
             */
            for(j = 0; j < k; j++)
            {
                chro[j] = r.nextInt(vec.length);
            }

            /*
             * Start the challenge, put the chromosomes
             * in a array, order it and get the first
             * and the second (winners).
             */
            for(j = 0; j < k; j++)
                sort[j] = vec[chro[j]];

            Arrays.sort(sort, comp);

            /* Do a crossover with the winners */
            children = Chromosome.crossover(sort[0], sort[1]);

            /*
             * Randomically select a child
             * and copy it's 'numbers' to
             * ret vector
             */
            if(r.nextBoolean())
            {
                ret[i] = new Chromosome();
                for(j = 0; j < Chromosome.BITS; j++)
                {
                    ret[i].getInteger()[j] = children[0].getInteger()[j];
                    ret[i].getDecimal()[j] = children[0].getDecimal()[j];
                }
            }
            else
            {
                ret[i] = new Chromosome();
                for(j = 0; j < Chromosome.BITS; j++)
                {
                    ret[i].getInteger()[j] = children[1].getInteger()[j];
                    ret[i].getDecimal()[j] = children[1].getDecimal()[j];
                }
            }
        }
        return ret;
    }

	
	@Override
	public String toString()
	{
		String ret = new String("");
		int i;

		for(i = Chromosome.BITS - 1; i >= 0; i--)
		{
			ret = String.format("%s%d", ret, integer[i]);
		}

		ret = String.format("%s.", ret);

		for(i = Chromosome.BITS - 1; i >= 0; i--)
		{
			ret = String.format("%s%d", ret, decimal[i]);
		}

		return ret;
	}

    public static int getBITS()
    {
        return BITS;
    }
}