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
	}

}

/*
 * Sorry guys, I made it to select somes rates at the main flow of the program
 * but I was afraid to make any mistake with the main class
 * so I decided to insert it as a comment block, which I believe is less dangerous than
 * any other change I could make. I teste id and it worked, as simple as it is.
 * 
import java.util.Scanner;

public class testing
{
	public static void main(String[] trash)
	{
		System.out.println("Algoritmo Gen�tico para pontos �timos de fun��es");

		int op = 100, cycle=1, i;
		double txmutation=0, txelite=0, txcross=0;
		Scanner sc = new Scanner(System.in);

		while(op > 0)
		{

			while (txcross < 1 || txcross > 80) {
				System.out.printf("\nEscolha a taxa de crossover , real de 1 a 80 :\n");
				System.out.printf("> ");
				txcross = sc.nextInt();
			}

			while (txmutation <= 0 || txmutation > 80) {
				System.out.printf("\nEscolha a taxa de muta��o, real de 0 a 80 :\n");
				System.out.printf("> ");
				txmutation = sc.nextDouble();
			}

			while (txelite <= 0 || txmutation > 80) {
				System.out.printf("\nEscolha a taxa de elitismo, real de 0 a 80 :\n");
				System.out.printf("> ");
				txelite = sc.nextDouble();
			}

			System.out.printf("0 - Sair\n");
			System.out.printf("1 - Maximizar fun��o\n");
			System.out.printf("2 - Minimizar fun��o\n");

			System.out.printf("> ");
			op = sc.nextInt();            

			switch(op)
			{
			case 1:

				while (cycle > 0) {
					System.out.printf("\n\nMaximizar fun��o escolhido\n");
					System.out.printf("\nQuantos ciclos rodar? 0 => Sair:\n");
					System.out.printf("> ");
					cycle = sc.nextInt();

					for (i=1; i <= cycle; i++){
						System.out.printf("\nGera��o %d", i);
					}
						
				}
				//break;
				
			case 2:

				while (cycle > 0) {
					System.out.printf("\n\nMinimizar fun��o escolhido\n ");
					System.out.printf("\nQuantos ciclos rodar? 0 => Sair:\n");
					System.out.printf("> ");
					cycle = sc.nextInt();
					
					for (i=1; i <= cycle; i++){
						System.out.printf("\nGera��o %d", i);
					}
										
				}
				//break;
								
			//default:
				//break;
			}
			// Seting variables to reenter while loopings
			txmutation=0; txelite=0; txcross=0; cycle=1;
		}
	}
}

*/
