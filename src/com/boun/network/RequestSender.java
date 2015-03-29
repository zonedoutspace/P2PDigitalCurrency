package com.boun.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RequestSender {

	public static String send(Request request,String ipAddress) throws Exception {

		Socket  client = new Socket(ipAddress, NetworkData.APP_PORT);

		OutputStream outToServer = client.getOutputStream();
		DataOutputStream out = new DataOutputStream(outToServer);

		out.writeUTF(request.createRequest());

		InputStream inFromServer = client.getInputStream();
		DataInputStream in = new DataInputStream(inFromServer);

		String result = in.readUTF();

		client.close();
		out.close();
		in.close();

		return result;

	}

}
