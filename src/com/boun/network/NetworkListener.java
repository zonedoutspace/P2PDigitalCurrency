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

import java.io.IOException;
import java.net.ServerSocket;

public class NetworkListener extends Thread{


	public void run(){

		ServerSocket listener = null;
		try {
			listener = new ServerSocket(NetworkData.APP_PORT);
			while (true) {
				new RequestHandler(listener.accept()).start();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			
			try {
				listener.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
