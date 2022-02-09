package main;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
    	
    	final String MY_KEY = "A3nhXM%2B8eD%2FtaKtnnZ8jh1RhBRIPv%2BIwyplaq9oBZ3KUp5z3Kkung0s77hMg2GX2DRP7K5u108sc5oBE%2F6YQ1A%3D%3D";
    	
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/CovidDagnsRgntDmstPrmsnPrdtService/getCovidDagnsRgntDmstPrmsnPrdtInq"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + MY_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*응답데이터 형식(xml/json) default : xml*/
        urlBuilder.append("&" + URLEncoder.encode("MEDDEV_ITEM_NO","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*품목허가번호*/
        urlBuilder.append("&" + URLEncoder.encode("BSSH_NM","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*업체명*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
    }
}