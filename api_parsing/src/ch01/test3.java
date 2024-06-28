package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class test3 {
 
	public static void main(String[] args) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		test4 t4 = new test4();

		URL url = new URL("https://jsonplaceholder.typicode.com/users");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		// System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd = null;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			// test4[] t4Arr = gson.fromJson(msg, test4[].class);
			// System.out.println(t4Arr[0].getTitle());
		}
		StringBuilder sb = new StringBuilder();
		StringBuffer a = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		
		
		
		Type type = new TypeToken<List<UserJson>>() {}.getType();
		List<UserJson> list = gson.fromJson(sb.toString(), type);
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).toString());
			
			System.out.println();
			System.out.println("-----------");
		}
//		System.out.println("----------------------------------------------------------------");
//		
//		test4[] t4Arr = gson.fromJson(sb.toString(), test4[].class);
//		for (test4 test4 : t4Arr) {
//			System.out.println(test4.getId()+"  ||  " +test4.getTitle());
//		}
		//test4 tsa = new test4(1,2,"sdfas",true);
	}
}
