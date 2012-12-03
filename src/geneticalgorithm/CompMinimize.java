package geneticalgorithm;

import java.util.Comparator;

public class CompMinimize implements Comparator<Chromosome>
{
    public int compare(Chromosome c1, Chromosome c2)
    {
        if(c1.getRank() > c2.getRank())
            return 1;
        else if(c1.getRank() < c2.getRank())
            return -1;
        else
            return 0;
    }
}
