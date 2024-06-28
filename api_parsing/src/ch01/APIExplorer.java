package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class APIExplorer {

	public static void main(String[] args) throws IOException {

		// 1. 서버측 주소 - 경로
		// http://localhost:8080/test?name=홍길동&age=20
		// http://localhost:8080/test?name=%ED%99%8D%EA%B8%B8%EB%8F%99&age=20
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Msg msg1 = new Msg();
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/" + "getUlfptcaAlarmInfo"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=UnQmDbC75268f065a1%2B7cQ1B0ocs5ekjrP3hIvCznxutVhPXoQbA"
				+ "dQxxdoF1ewhax%2FnchqO3SKv6L%2BSclaCsCg%3D%3D"); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* xml 또는 json */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append(
				"&" + URLEncoder.encode("year", "UTF-8") + "=" + URLEncoder.encode("2024", "UTF-8")); /* 측정 연도 */
		urlBuilder.append("&" + URLEncoder.encode("itemCode", "UTF-8") + "="
				+ URLEncoder.encode("PM10", "UTF-8")); /* 미세먼지 항목 구분(PM10, PM25), PM10/PM25 모두 조회할 경우 파라미터 생략 */

		// URL 객체에서 문자열 경로 넣어서 객체 생성
		// url.openConnection() 데이터 요청 보내기 -설정

		URL url = new URL(urlBuilder.toString());

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		Msg[] msgArr = gson.fromJson(sb.toString(), Msg[].class);
		for (Msg msg : msgArr) {
			System.out.println(msg.getClearDate());
		}
	}

}
