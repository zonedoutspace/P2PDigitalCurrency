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

/**
 * inputs of a transaction
 **/

public class TransactionInput {

	/**the variable names are short
	 * since I want to make the json objects shorter**/
	
	//the blockNumber of the unspent money
	private int b;
	//the order of transaction in the block
	private int t;
	//the order of output in the transaction
	private int o;
	//the amount of the money
	private int a;
	

	public int getBlockNumber() {
		return b;
	}

	public void setBlockNumber(int blockNumber) {
		this.b = blockNumber;
	}

	public int getTransactionOrder() {
		return t;
	}
	
	public void setTransactionOrder(int tranactionOrder) {
		this.t = tranactionOrder;
	}
	
	public int getOutputOrder() {
		return o;
	}
	
	public void setOutputOrder(int outputOrder) {
		this.o = outputOrder;
	}
	
	public int getAmount() {
		return a;
	}
	
	public void setAmount(int amount) {
		this.a = amount;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		TransactionInput other = (TransactionInput) obj;
		
		if(this.getBlockNumber()!=other.getBlockNumber()){
			return false;
		}
		
		if(this.getTransactionOrder()!=other.getTransactionOrder()){
			return false;
		}
		
		if(this.getOutputOrder()!=other.getOutputOrder()){
			return false;
		}

		
		return true;
	}
	
}
