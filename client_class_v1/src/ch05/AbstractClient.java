package ch05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//상속 활용
public abstract class AbstractClient {

	public static void main(String[] args) {
		System.out.println("클라이언스 실행");
		try (Socket socket = new Socket("localhost",5000)){
			System.out.println("connected to server");
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
			
			
			startReadThread(bufferedReader);
			startWriteThread(printWriter, keyboardReader);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private static void startReadThread(BufferedReader reader) {
		Thread readThread = new Thread(() -> {
			try {
				String msg;
				while((msg = reader.readLine())!=null) {
					System.out.println(msg);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		});
		readThread.start();
	}
	private static void startWriteThread(PrintWriter writer, BufferedReader keyboardReader) {
		Thread writeThread = new Thread(() -> {
			try {
				String msg;
				while((msg = keyboardReader.readLine())!=null) {
					writer.println(msg);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		});
		writeThread.start();
	}
	
	
	
}
