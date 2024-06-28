package ch05;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyTreadServer extends AbstractServer {

	@Override
	protected void setupServer() throws IOException {
		super.setServerSocket(new ServerSocket(5000));
		System.out.println(">>> Server started on port 5000 <<<");
	}

	@Override
	protected void connection() throws IOException {
		super.setSocket(super.getServerSocket().accept());

	}

	public static void main(String[] args) {
		MyTreadServer myTreadServer = new MyTreadServer();
		myTreadServer.run();
	}

}
