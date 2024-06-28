package ch07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MultiClientServer {

	private static final int PORT = 5000; 
	// 하나의 변수에 자원을 통으로 관리하기 기법 --> 자료구조
	// 자료구조 ---> 코드 단일, 멀티 ---> 멀티 스레드 --> 자료 구조 ??
	// 객체 배열 <-- Vector<> : 멀티 스레드에 안정적이다. 
	private static Vector<PrintWriter> clientWriters = new Vector<>();
	//private static Set<PrintWriter> clientWriters = ConcurrentHashMap.newKeySet(); // 스레드 안전한 클라이언트 출력 스트림 집합
	
	public static void main(String[] args) {
		System.out.println("Server started....");
		
		try (ServerSocket serverSocket = new ServerSocket(PORT)){
			while(true) {
				// 1. serverSocket.accept() 호출하면 블록킹 상태가 된다. 멈춰있음 
				// 2. 클라이언트가 연결 요청하면 새로운 소켓 객체 생성이 된다. 
				// 3. 새로운 스레드를 만들어 처리 ... (클라이언트가 데이터를 주고 받기 위한 스레드) 
				// 4. 새로운 클라이언트가 접속 하기 까지 다시 대기 유지(반복) 
				Socket socket = serverSocket.accept();
				// 새로운 클라이언트가 연결 되면 새로운 스레가 생성된다. 
				new ClientHandler(socket).start();  
			}
		} catch (Exception e) {
			
		}

	} // end of main
	
	// 정적 내부 클래스 설계 
	private static class ClientHandler extends Thread {
		
		private Socket socket;
		private PrintWriter out; 
		private BufferedReader in;

		public ClientHandler(Socket socket) {
			this.socket = socket;
		}
		
		// 스레드 start() 호출시 동작 되는 메서드 - 약속 
		@Override
		public void run() {
			
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				
				String nameMessage = in.readLine();
				if (nameMessage != null && nameMessage.startsWith("NAME:")) {
					String clientName = nameMessage.substring(5);
					broadcastMessage("해당 성버에 입장 : "+nameMessage +"님");
				}else {
					//약속과 다르게 접근하면 종료 처리
					System.out.println("Sadfasdf");
				}
				
				
				
				// 여기서 중요 ! - 서버가 관리하는 자료구조에 자원 저장(클,연결된 소켓->outStream)   
				clientWriters.add(out);
				
				String message; 
				while( (message = in.readLine() ) != null  ) {
					System.out.println("Received : " + message);
					
					//약속 -> 
					// : 기준으로 처리, / 기준은 추가 가능
					//MSG:안녕\n --> 안녕\n
					String[] parts = message.split(":",2);
					System.out.println(parts.length);
					//명령 부분 분리
					String command = parts[0];
					//데이터 부분 분리
					String data = parts.length > 1 ? parts[1] : "";
					
					if (command.equals("MSG")) {
						System.out.println("연결된 전체 사용자에게 MSG 방송");
						broadcastMessage(data);
					}else if(command.equals("BYE")) {
						System.out.println("Client disconnected...");
						break;
					}
					
				}
			} catch (Exception e) {
				//e.printStackTrace();
			} finally {
				try {
					socket.close();
					//인덱스 번호 필요
					
					System.out.println("...... 클라이언트 연결 해제 ....... ");
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}
	}  // end of ClientHandler
	
	// 모든 클라이언트에게 메시지 보내기- 브로드캐스트 
	private static void broadcastMessage(String message) {
		for(PrintWriter writer : clientWriters) {
			writer.println(message);
		}
	}
}