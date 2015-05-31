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
 * outputs of a transaction
 **/
public class TransactionOutput {

	/**the variable names are short
	 * since I want to make the json objects shorter**/
	
	//the receiver's public Key
	String rPK;
	//the amount of the output
	int a;
	
	
	public String getReceiverPublicKey() {
		return rPK;
	}
	
	public void setReceiverPublicKey(String receiverPublicKey) {
		this.rPK = receiverPublicKey;
	}
	
	public int getAmount() {
		return a;
	}
	
	public void setAmount(int amount) {
		this.a = amount;
	}
	
}
