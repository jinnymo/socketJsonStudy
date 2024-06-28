package ch07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiClient {

	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("localhost", 5000);
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("server connect");
			
			//실행의 흐름 약속 먼저 사용자 닉네임 보내기
			System.out.println("enter  your name");
			String name = keyboard.readLine();
			out.println("NAME:" + name);
			
			Thread readThread = new Thread(() -> {
				String serverMSG;
				try {
					while((serverMSG = in.readLine())!= null) {
						System.out.println("server :" + serverMSG);
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			Thread writeThread = new Thread(() -> {
				String userMessage;
				try {
					while((userMessage = keyboard.readLine())!=null) {
						if (userMessage.equalsIgnoreCase("bye")) {
							out.println("BYE:");
						}else if(userMessage.equalsIgnoreCase("MSG")) {
							out.println("MSG:"+userMessage);
						}
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			readThread.start();
			writeThread.start();
			
			try {
				readThread.join();
				writeThread.join();
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			socket.close();
			System.out.println("서버로부터 연결 종료");
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
