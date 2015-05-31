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

package com.boun.hashes;

import java.security.MessageDigest;

import com.boun.signature.Base58;

public class HashOperations {

	public static String getHash(String input) throws Exception{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(input.getBytes("UTF-8"));
		return Base58.encode(md.digest());
	}
	
	public static boolean checkHash(String content,String hash) throws Exception{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(content.getBytes("UTF-8"));
		String correctHash = Base58.encode(md.digest());
		return hash.equals(correctHash);		
	}
	
}
