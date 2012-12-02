package tests;

import java.util.Arrays;

import geneticalgorithm.Chromosome;

public class AG
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        double mutation = 0.05;
        double elitep = 0.020;
        double total, r;
        int pop = 10;
        int cycles = 50;
        int i, k, j;
        int elite = (int) Math.floor(pop*(1 - elitep));
        Chromosome o_pop[], n_pop[], p1, p2;
        
        o_pop = new Chromosome[pop];
        n_pop = new Chromosome[pop];
        
        System.out.printf("Initializing....\n");
        for(i = 0; i < pop; i++)
        {
            o_pop[i] = new Chromosome();
            System.out.printf("[%d] %s: %f\n", i, o_pop[i], o_pop[i].getValue());
        }
        
        for(i = 0; i < pop; i++)
        {
            System.out.printf("==================Generation %d==================\nEvaluating....\n", i);
            total = 0;
            
            /* Evaluate */
            for(k = 0; k < pop; k++)
            {
                o_pop[k].setRank(evaluate(o_pop[k]));
                total += o_pop[k].getRank();
                System.out.printf("[%d] = %f\n", k, o_pop[k].getRank());
            }
            System.out.printf("Total = %f\n", total);
            
            /* Calculate the percentage of fitness and store it in Rank */
            for(k = 0; k < pop; k++)
            {
//                System.out.printf("[%d] %f%%\n", k, o_pop[k].getRank()/total);
                o_pop[k].setRank(o_pop[k].getRank()/total);
            }
          
            /* Sort the vector using Rank*/
            Arrays.sort(o_pop);
            
            /* Print the percentage of fitness */
            System.out.printf("Percentage of fitness\n");
            for(k = 0; k < pop; k++)
                System.out.printf("[%d] %f%%\n", k, o_pop[k].getRank());
            
            /* Put the elite in the new poupation vector */
            for(k = elite; k < pop; k++)
                n_pop[k] = o_pop[k];
            
            /*
             * For the rest of the new population:
             *  - Select two chromosomes
             *  - Do a crossover
             *  - Store the result into n_pop;
             */
            for(k = 0; k < elite; k++)
            {
                /* Select p1 */
                r = Math.random();
                for(j = 0; j < pop; j++)
                {
//                    System.out.printf("%f <= %f: %s\n", r, o_pop[j].getRank(), r <= o_pop[j].getRank());
                    if(r <= o_pop[j].getRank())
                        break;
                }
                if(j == pop)
                    j = pop - 1;
                p1 = o_pop[j];
//                System.out.printf("[%d]P1 = %d r = %f\n", k, j, r);
                
                /* Select p2 */
                r = Math.random();
                for(j = 0; j < pop; j++)
                {
//                    System.out.printf("%f <= %f: %s\n", r, o_pop[j].getRank(), r <= o_pop[j].getRank());
                    if(r <= o_pop[j].getRank())
                        break;
                }
                if(j == pop)
                    j = pop - 1;
                p2 = o_pop[j];
//                System.out.printf("[%d]P2 = %d, r = %f\n", k,  j, r);
                
                /* Avoid Index out of bounds */
                if(p1 == p2)
                    if(j != 0)
                        p2 = o_pop[--j];
                    else
                        p2 = o_pop[++j];
                
                Chromosome cross[] = Chromosome.crossover(p1, p2);
                n_pop[k] = cross[0];
            }
            o_pop = n_pop;
        }
    }

    public static double evaluate(Chromosome c)
    {
        double v, x = c.getValue();
        
        v = 50000000000000000000.01*Math.pow(x, -5.0)/(Math.exp(100.0/x) - 1.0);
        
        return v;
    }
}
