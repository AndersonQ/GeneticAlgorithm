package tests;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

import geneticalgorithm.Chromosome;
import geneticalgorithm.CompMaximize;
import geneticalgorithm.CompMinimize;

public class AG
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        double mutation = 0.05;
        double elitep = 0.10;
        double threshold = 0.95;
        int pop = 50;
        int cycles = 50;
        int i, k;
        int elite = (int) Math.floor(pop*(elitep));
        Chromosome o_pop[], n_pop[], tmp[];

        o_pop = new Chromosome[pop];
        n_pop = new Chromosome[pop];
        
        System.out.println("Function to maximize: f(x) = -(x-1)^2 +5");
        for(int x = -10; x < 21; x++)
            System.out.printf("f(%d) = %f\n", x, 1.0*Math.pow(x-10.0, 2.0)+5.0);

        System.out.printf("Initializing....\n");
        for(i = 0; i < pop; i++)
        {
            o_pop[i] = new Chromosome();
            System.out.printf("[%d] %s: %f\n", i, o_pop[i], o_pop[i].getValue());
        }

        for(i = 0; i < cycles; i++)
        {
            System.out.printf("==================Generation %d==================\nEvaluating....\n", i);
            /* Evaluate */
            for(k = 0; k < pop; k++)
            {
                o_pop[k].setRank(evaluate(o_pop[k]));
                System.out.printf("[%d] = f(%f) = %f\n", k, o_pop[k].getValue(), o_pop[k].getRank());
            }

            /* Sort the vector using Rank*/
            Arrays.sort(o_pop, new CompMinimize());
            for(Chromosome c: o_pop)
                System.out.printf("f(%f) = %f\n", c.getValue(), c.getRank());

            /******************************** Stop Criteria ********************************/
            /*
             * If 10% of the population is equal
             * stop
             */
            boolean stop = true;
            for(int count = 0; count < (int)Math.floor(pop*threshold); count++)
                if(o_pop[count].getValue() != o_pop[count+1].getValue())
                    stop = false;
            if(stop)
                break;

            /* Put the elite in the new population vector */
            System.out.println("Elite:");
            for(k = 0; k < elite; k++)
            {
                n_pop[k] = o_pop[k];
                System.out.printf("Elite[%d] = %s = %f\n", k, n_pop[k], n_pop[k].getRank());
            }

            /* Do a contest */
            tmp = contest(o_pop, (pop-elite), 5);
            
            /* Put the result of the contest in n_pop */
            for(k = 0; k < pop-elite; k++)
                n_pop[k+elite] = tmp[k];

            /* Mutate */
            for(k=elite; k < pop; k++)
                n_pop[k].mutation(mutation);

            o_pop = n_pop;
            System.out.println("Final population:");
            for(Chromosome c: o_pop)
                System.out.println(c);
            System.out.flush();
        }
    }

    public static double evaluate(Chromosome c)
    {
        double v, x = c.getValue();

        v = 1.0*Math.pow(x-10.0, 2.0)+5.0; //it's f(x) = -(x-1)^2 +5

        return v;
    }
    
    /**
     * Use the contest method to
     * create a new population of 
     * chromosomes
     * 
     * @param vec Original population
     * @param n_children Number of children that will be generated
     * @param k Number of chromosomes in each contest
     * @return A new population
     */
    public static Chromosome[] contest(Chromosome[] vec, int n_children, int k)
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

            Arrays.sort(sort, new CompMinimize());

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
}
