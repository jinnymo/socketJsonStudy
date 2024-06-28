package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpGetClient2 {

	public static void main(String[] args) {
		
		String urlString = "https://www.naver.com/";
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			int reponseCode = conn.getResponseCode();
			BufferedReader brIn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String inputLine;
			StringBuffer responseBuffer = new StringBuffer();
			
			while((inputLine = brIn.readLine())!= null) {
				responseBuffer.append(inputLine);
			}
			String[] strHtmls = responseBuffer.toString().split("\\s");
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
