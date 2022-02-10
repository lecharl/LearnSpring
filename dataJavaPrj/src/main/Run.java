package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Run {

	public static void main(String[] args) {
		StringBuilder urlBuilder = new StringBuilder("127.0.0.1:8888/app26/");
		
		
		
		
		HttpURLConnection conn = new h
		
		System.out.println("Response code: " + conn.getResponseCode());
		
		//커넥션에서 스트림 가져옴(버퍼 곁들임)
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		//데이터 읽고 sb에다가 전부 다 더해줌
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = rd.readLine()) != null) {
			sb.append(line);
			
			if(line.contains("real-time")) {
				System.out.println(line.substring(23, 28));
			}
		}
		
		
		rd.close();
		conn.disconnect();
	}
}
