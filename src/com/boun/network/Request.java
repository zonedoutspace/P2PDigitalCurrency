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

package com.boun.network;

import com.google.gson.Gson;


/**
 * @author ERKAN EROL 2010400129
 *
 * the structure of requests
 */
public abstract class Request {
			
	public abstract int getRequestCode();
	public abstract void initializeRequest() throws Exception;

	//handle the request and return outputs
	public abstract  String handleRequest();
	
	//for clients
	public String createRequest() throws Exception{		
		Gson gson = new Gson();
		EncapsulatedRequest en= new EncapsulatedRequest(this.getRequestCode(),gson.toJson(this));
		return gson.toJson(en);
	}
	
}
