package ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiThreadClient {

	public static void main(String[] args) {

		System.out.println("### 클라이언트 실행 ###");
		Socket socket =null;
		try {
			socket = new Socket("localhost", 5000);
			System.out.println("*** connected to the Server***");

			PrintWriter soketWriter = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader soketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

			// 서버로 부터 데이터를 읽는 스레드
			Thread readThread = new Thread(() -> {
				try {
					String meassage;
					
					// 스캐너 .. sc.nextLine(); <--- 데이터 들어올때까지 
					// .... 안녕\n  != null --> true 
					//  sc.nextLine(); <--- 데이터 들어올때까지
					// .... 이해오미\n
					// 무한 WHIEL --> 소켓안에 스트림 NULL 
					while((meassage = soketReader.readLine()) != null) {
						System.out.println("서버->클라이언트 : "+meassage);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			//서버에게 데이터를 보내는 스레드
			Thread writeThread = new Thread(() -> {
				 try {
					 String meassage1;
					while((meassage1 = keyboardReader.readLine())!= null) {
						 soketWriter.println(meassage1);
					 }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			readThread.start();
			writeThread.start();
			
			readThread.join();
			writeThread.join();
			
			System.out.println("클라이언트측 프로그램 종료");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
