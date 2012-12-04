/*
 * Copyright 2012 Anderson Queiroz <contato(at)andersonq(dot)eti(dot)br>
 * 					Fernando Zucatelli, João Coutinho, Tiago Queiroz <contato(at)tiago(dot)eti(dot)br>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package geneticalgorithm;

/**
 * Represents a polynomial
 * 
 * @author Anderson Queiroz, Fernando Zucatelli, João Coutinho, Tiago Queiroz
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
