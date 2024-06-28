package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMultiServer {

	private ServerSocket serverSocket;
	private Socket socket;
	Map<Socket, PrintWriter> user;

	protected void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		try {
			System.out.println(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void run() {
		try {
			setupServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user = new HashMap<Socket, PrintWriter>();
		Thread userAdd = createNewSocket();

		userAdd.start();
		System.out.println("===서버 시작===");
	}

	protected abstract void setupServer() throws IOException;

	private Thread createNewSocket() {
		return new Thread(() -> {

			while (true) {
				try {
					socket = this.serverSocket.accept();
					user.put(socket, new PrintWriter(socket.getOutputStream(), true));
					creatReadThread(socket);
					System.out.println(socket.getInetAddress() + "유저 접속");

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
	}

	private void creatReadThread(Socket socket) throws IOException {
		new Thread(() -> {
			try {
				BufferedReader readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg;
				boolean flag = true;

				while ((msg = readerStream.readLine()) != null && flag) {
					System.out.println(socket.getInetAddress() + "의 메세지 : " + msg);
					messagePush(msg);
					if (!socket.isConnected()) {
						flag = false;
					}

				}
				
				user.remove(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					socket.close();
					System.out.println(socket.getInetAddress() + " 가 연결을 끊었습니다.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void messagePush(String msg) {
		for (PrintWriter writerStream : user.values()) {
			writerStream.println(msg);
			writerStream.flush();
		}
	}
}
