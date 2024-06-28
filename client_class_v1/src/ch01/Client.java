package ch01;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		// 클라이언트 측 -- 소켓 통신을 하기 위해서 준비물
		// 1.서버측 컴퓨터에 주소:포트번호
		// 2.서버측과 연결될 기본 소켓이 필요하다.
		
		//생성자 매개변수에 서버측 (IP주소, 포트번호)
		//127.0.0.1(localhost) <- 자신을뜻 하는 주소
		try (Socket socket = new Socket("localhost",5000)){
			//Socket socket = new Socket("localhost",5000) -> 객체 생성시 서버측과 연결 되어서
			//스트림을 활용할수 있다.
			//대상은 소켓이다.!!
			
			OutputStream output = socket.getOutputStream();//소켓에서 기반 스트림
			PrintWriter writer = new PrintWriter(output,true);//기능 확작 - 보조 스트림
			writer.println("Hello, Server!");
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
