package tests;

import java.util.Scanner;

import geneticalgorithm.Chromosome;

public class testing
{
    public static void main(String[] trash)
    {
        Chromosome c1, c2;

        c1 = new Chromosome();
        c2 = new Chromosome();

        System.out.println(c1.getValue());
        System.out.println(c2.getValue());

        System.out.println(c1);
        System.out.println(c2);
        /*
         * Sorry guys, I made it to select somes rates at the main flow of the program
         * but I was afraid to make any mistake with the main class
         * so I decided to insert it as a comment block, which I believe is less dangerous than
         * any other change I could make. I teste id and it worked, as simple as it is.
         */

        System.out.println("Algoritmo Genetico para pontos de funcoes");

        int op = 100, cycle=1, i;
        double txmutation=0, txelite=0, txcross=0;
        Scanner sc = new Scanner(System.in);

        while(op > 0)
        {
            // Seting variables to reenter while loopings
            txmutation=0; txelite=0; txcross=0; cycle=1;

            while (txcross < 1 || txcross > 80)
            {
                System.out.printf("\nEscolha a taxa de crossover , real de 1 a 80 :\n");
                System.out.printf("> ");
                txcross = sc.nextInt();
            }

            while (txmutation <= 0 || txmutation > 80)
            {
                System.out.printf("\nEscolha a taxa de mutacao, real de 0 a 80 :\n");
                System.out.printf("> ");
                txmutation = sc.nextDouble();
            }

            while (txelite <= 0 || txmutation > 80)
            {
                System.out.printf("\nEscolha a taxa de elitismo, real de 0 a 80 :\n");
                System.out.printf("> ");
                txelite = sc.nextDouble();
            }

            System.out.printf("0 - Sair\n");
            System.out.printf("1 - Maximizar funcao\n");
            System.out.printf("2 - Minimizar funcao\n");

            System.out.printf("> ");
            op = sc.nextInt();

            switch(op)
            {
            case 1:

                while (cycle > 0)
                {
                    System.out.printf("\n\nMaximizar funcao escolhido\n");
                    System.out.printf("\nQuantos ciclos rodar? 0 => Sair:\n");
                    System.out.printf("> ");
                    cycle = sc.nextInt();

                    for (i=1; i <= cycle; i++)
                        System.out.printf("\nGeracao %d", i);
                }
                break;

            case 2:

                while (cycle > 0)
                {
                    System.out.printf("\n\nMinimizar funcao escolhido\n ");
                    System.out.printf("\nQuantos ciclos rodar? 0 => Sair:\n");
                    System.out.printf("> ");
                    cycle = sc.nextInt();

                    for (i=1; i <= cycle; i++)
                        System.out.printf("\nGeracao %d", i);
                }
                break;
            }
        }
    }
}
