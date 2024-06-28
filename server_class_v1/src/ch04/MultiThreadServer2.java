package ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

public class MultiThreadServer2 {

	Socket[] sockets;
	Socket socket1 = null;
	ServerSocket serverSocket1 = null;
	int count;
	BufferedReader[] bufferedReaders = null;
	PrintWriter[] printWriters = null;

	public MultiThreadServer2() {
		
		count = 0;
		sockets = new Socket[3];
		bufferedReaders = new BufferedReader[3];
		printWriters = new PrintWriter[3];
		try {
			serverSocket1 = new ServerSocket(5001);
			
			System.out.println("서버 시작 유저 입장 가능 3명 까지");
			addUserListener();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nextstep();
		
	}
	public void nextstep() {
		new Thread(() -> {
			while (true) {

				try {
						String message1;
						if (bufferedReaders[0].ready()) {
							message1 = bufferedReaders[0].readLine();
							for (int j = 0; j < count; j++) {
								printWriters[j].println(message1);
							}
						}
						if (bufferedReaders[1].ready()) {
							message1 = bufferedReaders[0].readLine();
							for (int j = 0; j < count; j++) {
								printWriters[j].println(message1);
							}
						}
						if (bufferedReaders[2].ready()) {
							message1 = bufferedReaders[0].readLine();
							for (int j = 0; j < count; j++) {
								printWriters[j].println(message1);
							}
						}
//						if ((message1 = bufferedReaders[0].readLine()) != null) {
//							for (int j = 0; j < count; j++) {
//								printWriters[j].println(message1);
//							}
//						}else if ((message1 = bufferedReaders[0].readLine()) != null) {
//							for (int j = 0; j < count; j++) {
//								printWriters[j].println(message1);
//							}
//						}else if ((message1 = bufferedReaders[0].readLine()) != null) {
//							for (int j = 0; j < count; j++) {
//								printWriters[j].println(message1);
//							}
//						}else {
//							bufferedReaders[0].ready();
//						}
					
//					for (int i = 0; i < count; i++) {
//						String message = null;
//						if ((message = bufferedReaders[i].readLine()) != null) {
//							for (int j = 0; j < count; j++) {
//								printWriters[j].println(message);
//							}
//						}
//					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}).start();
	}

	public void addUserListener() throws IOException {
		
			while (count < 3) {
				try {
						
						sockets[count] = serverSocket1.accept();
						System.out.println(sockets[count].isConnected());
						
					if (sockets[count] != null) {
						bufferedReaders[count] = new BufferedReader(
								new InputStreamReader(sockets[count].getInputStream()));
						
						System.out.println("sdfsadf");
						printWriters[count] = new PrintWriter(sockets[count].getOutputStream(), true);
						count++;
						System.out.println("유저" + count + "입장");
						
						System.out.println(count);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		

	}

	public static void main(String[] args) {
		new MultiThreadServer2();
//
//		System.out.println("=== 서버 실행 ===");
//
//		ServerSocket serverSocket = null;
//		Socket socket = null;
//
//		try {
//			serverSocket = new ServerSocket(5001);
//			socket = serverSocket.accept();
//			System.out.println("포트 번호 - 5001 할당완료");
//
//			// 클라이언트 데이터를 받을 입력 스트림
//			BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//			// 클라이언트에 데이터를 보낼 출력 스트림
//			PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
//
//			// 서버측 - 키보드 입력을 받기 위한 입력 스트림 필요
//			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
//
//			// 멀티 스레딩 개념에 확장
//			// 클라이언트로 부터 데이터를 읽는 스레드
//			Thread readThread = new Thread(() -> {
//				try {
//					String clientMessage;
//					while ((clientMessage = socketReader.readLine()) != null) {
//						System.out.println("클라이언트 -> 서버 : " + clientMessage);
//					}
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			});
//
//			// 클라이언트에게 데이터를 보내는 스레드 생성
//			Thread writeThread = new Thread(() -> {
//				try {
//					String serverMessage;
//					while ((serverMessage = keyboardReader.readLine()) != null) {
//						socketWriter.println(serverMessage);
//					}
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}
//			});
//
//			readThread.start();
//			writeThread.start();
//
//			// Thread join() 메서드는 하나의 스레드가 종료될떄 까지 기다리도록 하는
//			// 기능을 제공한다.
//			readThread.join();
//			writeThread.join();
//
//			System.out.println("---서버 프로그램 종료---");
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				socket.close();
//				serverSocket.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

	}

}
