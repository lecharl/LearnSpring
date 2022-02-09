package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Main {

	public static void main(String[] args) throws Exception {
		
		//인증키
		final String MY_KEY = "A3nhXM%2B8eD%2FtaKtnnZ8jh1RhBRIPv%2BIwyplaq9oBZ3KUp5z3Kkung0s77hMg2GX2DRP7K5u108sc5oBE%2F6YQ1A%3D%3D";
		
		//<자바에서 웹으로 요청하는 방법>
		//1. URL 세팅(요청주소 + 파라미터들 세팅)
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo"); /*URL*/
		String url0 = "http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo";
		
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + MY_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*xml 또는 json*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("year","UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8")); /*측정 연도*/
        urlBuilder.append("&" + URLEncoder.encode("itemCode","UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*미세먼지 항목 구분(PM10, PM25), PM10/PM25 모두 조회할 경우 파라미터 생략*/
        //URLEncoder.encode : 한글은 URLEncoding 작업을 해줘야 URL에서 정상적으로 전달이 됨
        
        url0 += "?";
        url0 += "serviceKey=vvv";	//필수
        
        url0 += "&";
        url0 += "returnType=xml";
        
        url0 += "&";
        url0 += "numOfRows=100";
        
        url0 += "&";
        url0 += "pageNo=1";
        
        url0 += "&";
        url0 += "year=2020";	//필수
        
        url0 += "&";
        url0 += "itemCode=PM10";	
        
        
        //2. URL 객체 생성(urlBuilder을 이용해서)
        URL url = new URL(urlBuilder.toString());
        
        //3. URL 을 이용해서 connection 가져오기
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        //4. request의 요청방식(method)랑 header 설정
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        //conn.getResponseCode() : connection을 이용해서 응답코드(response code) 확인 가능
        System.out.println("Response code: " + conn.getResponseCode());
        
        //5. 커넥션에서 inputStream 가져옴 -> BufferdReader로 변환
        //응답코드에 따른 처리(conn.getInputStream())
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        //6. 5에서 가져온 스트림으로 데이터 한줄씩 읽기(누적시키기)
        //그 rd를 한줄씩 읽어서 line에 누적해서 넣기
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        
        //7. 사용한 자원(스트림, 커넥션) 정리 : rd 자원 반납, 웹사이트랑 연결도 끊기
        rd.close();
        conn.disconnect();
        
        //8. 6에서 누적한 결과 확인
        System.out.println(sb.toString());
	}
}
