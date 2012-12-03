package tests;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

import geneticalgorithm.Chromosome;
import geneticalgorithm.CompMaximize;

public class AG
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        double mutation = 0.05;
        double elitep = 0.10;
        double total, r;
        int pop = 10;
        int cycles = 10;
        int i, k, j;
        int elite = (int) Math.floor(pop*(elitep));
        Chromosome o_pop[], n_pop[], p1, p2, tmp[];

        o_pop = new Chromosome[pop];
        n_pop = new Chromosome[pop];
        
        System.out.println("Function to maximize: f(x) = -(x-1)^2 +5");
        for(int x = -10; x < 21; x++)
            System.out.printf("f(%d) = %f\n", x, -1.0*Math.pow(x-1.0, 2.0)+5.0);

        System.out.printf("Initializing....\n");
        for(i = 0; i < pop; i++)
        {
            o_pop[i] = new Chromosome();
            System.out.printf("[%d] %s: %f\n", i, o_pop[i], o_pop[i].getValue());
        }

        for(i = 0; i < cycles; i++)
        {
            System.out.printf("==================Generation %d==================\nEvaluating....\n", i);
            total = 0;

            /* Evaluate */
            for(k = 0; k < pop; k++)
            {
                o_pop[k].setRank(evaluate(o_pop[k]));
                total += o_pop[k].getRank();
                /* Adding the value of Chromosome na exibi��o*/
                System.out.printf("[%d] = f(%f) = %f\n", k, o_pop[k].getValue(), o_pop[k].getRank());
            }
            System.out.printf("Total = %f\n", total);

//            /* Calculate the percentage of fitness and store it in Rank */
//            for(k = 0; k < pop; k++)
//                o_pop[k].setRank(o_pop[k].getRank()/total);

            /* Sort the vector using Rank*/
            Arrays.sort(o_pop, new CompMaximize());
            System.out.println("Sorted!");
            for(Chromosome c: o_pop)
                System.out.printf("f(%f) = %f\n", c.getValue(), c.getRank());

            /* Print the percentage of fitness */
//            System.out.printf("Percentage of fitness\n");
//            for(k = 0; k < pop; k++)
//                System.out.printf("[%d] %f%%\n", k, o_pop[k].getRank());

            /* Put the elite in the new population vector */
            System.out.println("Elite:");
            for(k = 0; k < elite; k++)
            {
                n_pop[k] = o_pop[k];
                System.out.printf("Elite[%d] = %s = %f\n", k, n_pop[k], n_pop[k].getRank());
            }

            /*
             * For the rest of the new population:
             *  - Select two chromosomes
             *  - Do a crossover
             *  - Store the result into n_pop;
             *  - Mutate it
             */
//            for(k = 0; k < elite; k++)
//            {
//                /* Select p1 */
//                r = Math.random();
//                for(j = 0; j < pop; j++)
//                    if(r <= o_pop[j].getRank())
//                        break;
//
//                if(j == pop)
//                    j = pop - 1;
//                p1 = o_pop[j];
//
//                /* Select p2 */
//                r = Math.random();
//                for(j = 0; j < pop; j++)
//                    if(r <= o_pop[j].getRank())
//                        break;
//
//                if(j == pop)
//                    j = pop - 1;
//                p2 = o_pop[j];
//
//                /* Avoid Index out of bounds */
//                if(p1 == p2)
//                    if(j != 0)
//                        p2 = o_pop[--j];
//                    else
//                        p2 = o_pop[++j];
//
//                Chromosome cross[] = Chromosome.crossover(p1, p2);
//                n_pop[k] = cross[0];
//                n_pop[k].mutation(mutation);
//            }
            
            /* Do a contest */
            tmp = contest(o_pop, (pop-elite), 5, elite);
            
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

        //v = 50000000000000000000.01*Math.pow(x, -5.0)/(Math.exp(100.0/x) - 1.0);
        v = -1.0*Math.pow(x-1.0, 2.0)+5.0; //it's not f(x) = -(x-1)^2 +5

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
     * @param elite Position of vec to finish the selection 
     * @return A new population
     */
    public static Chromosome[] contest(Chromosome[] vec, int n_children, int k, int elite)
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
             * Select the challengers between
             * 0 and elite -1
             * */
            for(j = 0; j < k; j++)
            {
                chro[j] = r.nextInt(n_children + elite);
//                System.out.printf("Contest selecting %d. Elite = %d\n", chro[j], elite);
            }

            /*
             * Start the challenge, put the chromosomes
             * in a array, order it and get the first
             * and the second (winners).
             */
            for(j = 0; j < k; j++)
                sort[j] = vec[chro[j]];

            Arrays.sort(sort, new CompMaximize());

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
//        System.out.println("Contest:");
//        for(Chromosome c: ret)
//            System.out.println(c);
        return ret;
    }
}
