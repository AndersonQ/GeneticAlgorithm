package geneticalgorithm;

/**
 * Represents a polynomial
 * 
 * @author Anderson Queiroz <contato(at)andersonq(dot)eti(dot)br
 *
 */
public class Function {

	/** Coefficients of this polynomial */
	private int[] coefficients;
	
	public Function(int[] coefficients)
	{
		this.coefficients = coefficients; 
	}

	/** Get the coefficients of this function */
	public int[] getCoefficients() {
		return coefficients;
	}

	/**
	 * Set the coefficients of this function
	 * @param coefficients the new coefficients
	 */
	public void setCoefficients(int[] coefficients) {
		this.coefficients = coefficients;
	}
}
