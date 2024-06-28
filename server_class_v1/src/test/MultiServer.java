package test;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiServer extends AbstractMultiServer {
	@Override
	protected void setupServer() throws IOException {
		super.setServerSocket(new ServerSocket(5000));
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		MultiServer multiServer = new MultiServer();
		multiServer.run();
	}
}
