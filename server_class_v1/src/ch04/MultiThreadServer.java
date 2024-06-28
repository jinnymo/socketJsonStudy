package ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

	public static void main(String[] args) {

		System.out.println("=== 서버 실행 ===");

		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(5001);
			socket = serverSocket.accept();
			System.out.println(socket.toString());

			// 클라이언트 데이터를 받을 입력 스트림
			BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 클라이언트에 데이터를 보낼 출력 스트림
			PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);

			// 서버측 - 키보드 입력을 받기 위한 입력 스트림 필요
			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

			// 멀티 스레딩 개념에 확장
			// 클라이언트로 부터 데이터를 읽는 스레드
			Thread readThread = new Thread(() -> {
				try {
					String clientMessage;
					while ((clientMessage = socketReader.readLine()) != null) {
						System.out.println("클라이언트 -> 서버 : " + clientMessage);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});

			//클라이언트에게 데이터를 보내는 스레드 생성
			Thread writeThread = new Thread(() -> {
				try {
					String serverMessage;
					while((serverMessage = keyboardReader.readLine())!= null) {
						socketWriter.println(serverMessage);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			});
			
			readThread.start();
			writeThread.start();
			
			//Thread join() 메서드는 하나의 스레드가 종료될떄 까지 기다리도록 하는 
			//기능을 제공한다.
			readThread.join();
			writeThread.join();
			
			System.out.println("---서버 프로그램 종료---");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				socket.close();
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

}
