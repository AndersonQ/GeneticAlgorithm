package geneticalgorithm;

/**
 * Stores the function to be optimised 
 * @author Anderson Queiroz <contato(at)andersonq(dot)eti(dot)br
 *
 */
public class Function {

	/** The coefficient of this function */
	int[] coefficient;
	/** Order of this function */
	int order;
	
	public Function(int[] coefficient)
	{
		this.coefficient = coefficient;
		this.order = this.coefficient.length;
	}
}
