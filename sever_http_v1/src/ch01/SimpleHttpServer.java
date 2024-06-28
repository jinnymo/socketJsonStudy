package ch01;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {

	public static void main(String[] args) {
		//8080 <-https, 80<--(포트번호 생략 가능하다.)
		try {
			//포트 번호 8080 으로 http 서버 생성
			HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080),0);
			
			//서버에 대한 설정
			
			//프로토콜 정의 (경로, 핸들러 처리)
			//핸들러 처리를 내부 정적 클래스로 사용
			httpServer.createContext("/test",new MyTestHandler());
			httpServer.createContext("/hello",new HelloHandler());
			//서버 시작
			httpServer.start();
			System.out.println(">>>server start<<<");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}// end of main
		// http://localhost:8080/test <- 주소 설계

	static class MyTestHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {

			// 사용자의 요청 방식 (METHOD) GET, POST 중 어떤건지 알아야 동작 할수 있다.

			String method = exchange.getRequestMethod();
			//exchange.
			System.out.println("method : " + method);
			if ("GET".equalsIgnoreCase(method)) {
				//겟용청시 여기
				//System.out.println("여기는 겟입니당.");
				
				//path: /test 라고 들어오면 어떤 응답 처리를 내려주면 된다.
				handelGetRequest(exchange);
			}else if ("POST".equalsIgnoreCase(method)) {
				//System.out.println("여기는 포스트 방식입니당.");
				handlePostRequest(exchange);
			}else {
				// 지원하지 않는 메서드에 대한 응답 
				String respnose = "Unsupported Methdo : " + method;
				exchange.sendResponseHeaders(405, respnose.length()); // Method Not Allowed
				OutputStream os = exchange.getResponseBody();
				os.write(respnose.getBytes());
				os.flush();
				os.close();
			}

		}
		//get 요청시 동작 만들기ㅕㅜ87 ㅔ0-
		private void handelGetRequest(HttpExchange exchange) throws IOException {
			String response = """
					<!DOCTYPE html>
					<html lang=ko>
						<head></head> 
						<body>
							<h1 style="background-color:yellow"> Hello path by /test </h1>
						</body>
						</html>
					""";
			//String response = "hello get~~~";
			exchange.sendResponseHeaders(200, response.length());
			//	OutputStream os = exchange.getResponseBody();
			PrintWriter pw = new PrintWriter(exchange.getResponseBody());
			pw.print(response);
			pw.flush();
			pw.close();
			//	os.write(response.getBytes());//응답 본문 전송
			//	os.close();
		}
		private void handlePostRequest(HttpExchange exchange) throws IOException{
			//post 요청은 http 메세지에 바디 영역이 존재 한다.
			String response = """
					<!DOCTYPE html>
					<html lang=ko>
						<head></head>
						<body>
							<h1 style="background-color:red"> Hello path by /test </h1>
						</body>
						</html>
					""";
			exchange.setAttribute("Content-Type", "text/html; charset=UTF-8");
			exchange.sendResponseHeaders(200, response.length());
			
			
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
			
		}
	}// end of mytesthandler

	static class HelloHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			//사용자의 요청 방식 (METHOD) GET, POST 중 어떤건지 알아야 동작 할수 있다.

			String method = exchange.getRequestMethod();
			System.out.println("hello method : " + method);

		}
		
	}
}
