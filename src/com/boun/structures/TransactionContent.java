/**
 	This file is part of Simple Peer to Peer Digital Currency Project.

    It is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    It is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with it.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.boun.structures;

import java.util.ArrayList;
import java.util.Date;


/**
 * the content of a transaction that must be signed.
 * */
public class TransactionContent {
	
	public static final int maximumNumberOfInputs =  100;
	public static final int maximumNumberOfOutputs = 100;  
	
	/**the variable names are short
	 * since I want to make the json objects shorter **/

	//transaction time
	private Date tD;
	//transaction inputs
	private ArrayList<TransactionInput> in;
	//transaction outputs
	private ArrayList<TransactionOutput> ou;
	
	
	public Date getTransationDate() {
		return tD;
	}
	
	public void setTransationDate(Date transationDate) {
		this.tD = transationDate;
	}
	
	public ArrayList<TransactionInput> getInputs() {
		return in;
	}
	
	public void setInputs(ArrayList<TransactionInput> inputs) {
		this.in = inputs;
	}
	
	public ArrayList<TransactionOutput> getOutputs() {
		return ou;
	}
	
	public void setOutputs(ArrayList<TransactionOutput> outputs) {
		this.ou = outputs;
	}	
	
}
