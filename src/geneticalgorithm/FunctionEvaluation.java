package geneticalgorithm;

/**
 * Evaluate the chromosomes 
 * @author Anderson Queiroz <contato(at)andersonq(dot)eti(dot)br
 *
 */
public class FunctionEvaluation {

	private enum optimisation{
		/** Maximise the function */
		MAXIMASE,
		/** Minimise the function */
		MINIMISE;
	}
	
	/** Indicates if this function should be maximised or minimised */
	optimisation opt;
	
	public FunctionEvaluation(optimisation opt)
	{
		this.opt = opt;
	}
	
	
}
