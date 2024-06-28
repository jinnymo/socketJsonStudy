package ch02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientFile {

	public static void main(String[] args) {

		// 클라이언트 측 준비물
		// 1. 서버측 ip주소와 포트 번호 필요
		// 2. 서버측 소켓과 연결된 소켓이 필요하다.

		Socket socket = null;
		try {
			socket = new Socket("localhost", 5001);

			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.print("안녕 ㅎㅎㅎ");
			writer.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
