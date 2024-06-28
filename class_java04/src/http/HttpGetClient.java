package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpGetClient {

	public static void main(String[] args) {
		// 자바 기본 코드로 http 요청을 만들어 보자

		// http 통신 하기 위한 준비물
		// 서버 주소 (경로준비)
		String urlString = "https://jsonplaceholder.typicode.com/todos/5";

		// 1. URL 클래스 만들기
		// 2.Connection 객체를 만들어준다. (URL --> 멤버로 Connection 객체를 뽑을 수 있다.)
		try {
			URL url = new URL(urlString);
			// url.openConnectrion() 연결 요청 진행
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// 추가 설정을 할 수 있음
			// METHOD 방식 설정 (약속) --> GET 요청은 해당 서버의 자원 요청입니다.
			conn.setRequestMethod("GET");

			// HTTP 응답 메시지에서 데이터를 추출 가능하다.
			int reponseCode = conn.getResponseCode();
			System.out.println("HTTP Code : " + reponseCode);// 200 -> 연결 성공

			BufferedReader brIn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String inputLine;
			StringBuffer responseBuffer = new StringBuffer();
			while((inputLine = brIn.readLine())!= null) {
				responseBuffer.append(inputLine);// append 체크 할것  String += String 이랑 차이 확인;
			}
//			System.out.println(responseBuffer.toString());
			String[] strHtmls = responseBuffer.toString().split("\\s");
			System.out.println("index count : " + strHtmls.length);// .lenght 와 .size 확인 하기 !!!!
			
			for (String word : strHtmls) {
				System.out.println(word);
			
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
