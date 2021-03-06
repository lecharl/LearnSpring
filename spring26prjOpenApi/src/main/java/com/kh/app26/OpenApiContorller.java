package com.kh.app26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@RequestMapping("data")
public class OpenApiContorller {

	@GetMapping("micro")
	public String micro() throws Exception{
		
		String result = callMicro("3");
		
		//json 데이터 파싱하기
//		JsonElement jsonElem = JsonParser.parseString(result);
		JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
		
		//3가지 방법
		//resultObj.get("response") -> 리턴타입 : JsonElement
//		JsonObject responseObj = resultObj.getAsJsonObject("response");
		JsonObject responseObj = resultObj.get("response").getAsJsonObject();
		System.out.println("responseObj ::: "+ responseObj);
//		JsonObject responseObj = (JsonObject) resultObj.get("response");
        //"key"를 넣어주면 "value"를 뱉어낸다.
		
		JsonObject headerObj = responseObj.getAsJsonObject("header");
		System.out.println("headerObj ::: "+ headerObj);
		JsonObject bodyObj = responseObj.getAsJsonObject("body");
		System.out.println("bodyObj ::: "+ bodyObj);
		
		//숫자여도 보통 String으로 받는다.
		String totalCount = bodyObj.get("totalCount").getAsString();
		System.out.println("totalCount ::: "+ totalCount);
		
		
		//"items":[{}] -> 배열이므로
//		JsonArray items = bodyObj.getAsJsonArray("items");
		JsonArray items = (JsonArray) bodyObj.get("items");
		
		System.out.println("items ::: "+ items);
		
		List<ItemVo> itemVoList = new ArrayList<ItemVo>();
		for(int i=0; i<items.size(); ++i) {
			JsonObject item = items.get(i).getAsJsonObject();
			System.out.println(item);
			
			String clearVal = item.get("clearVal").getAsString();
			String sn = item.get("sn").getAsString();
			String districtName = item.get("districtName").getAsString();
			String dataDate = item.get("dataDate").getAsString();
			String issueVal = item.get("issueVal").getAsString();
			
			ItemVo vo = new ItemVo();
			vo.setClearVal(clearVal);
			vo.setSn(sn);
			vo.setDistrictName(districtName);
			vo.setDataDate(dataDate);
			vo.setIssueVal(issueVal);
			
			itemVoList.add(vo);
			
		}
		
		for(ItemVo x : itemVoList) {
			System.out.println(x);
			System.out.println("---------------------------");
		}

		
//		System.out.println(items);
//		[{"clearVal":"65","sn":"249", ...
		
		//내가 원하는 것만
//		for(int i=0; i<items.size(); ++i) {
//			System.out.println(items.get(i));
//		}
		
		/*
//		System.out.println(result);
		//단순한 문자열
//		{"response":{"body":{"totalCount":108, "item":[...."header"...
 
//		System.out.println(resultObj);
		//KEY값을 이용해서 값(데이터)을 꺼내올 수 있다!!!!
//		{"response":{"body":{"totalCount":108, "item":[...."header"...
		
		System.out.println(responseObj);
//		{"body":{"totalCount":108,"items":[{"clearVal":...."header"...
		
		System.out.println(responseObj.getAsJsonObject("header"));
//		{"resultMsg":"NORMAL_CODE","resultCode":"00"}
		System.out.println(responseObj.getAsJsonObject("body"));
//		{"totalCount":108,"items":[{"clearVal":"....
		 * 
		 */
		
		return "data/micro";
	}
	

	@GetMapping("api-view")
	public String resp() {
		
		return "data/apiView";
	}
	
	@GetMapping(value = "api-resp", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String apiResp(String numOfRows) throws Exception{
		
		//api 호출
		String result = callMicro(numOfRows);
		
		//호출결과 리턴
		return result;
	}
	
	@GetMapping(value = "api-respxml", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String apiRespXml(String numOfRows) throws Exception{
		
		//api 호출
		String result = callMicro(numOfRows);
		
		//호출결과 리턴
		return result;
	}
	
	
	private String callMicro(String numOfRows) throws Exception{
		
		//인증키
		final String MY_KEY = "A3nhXM%2B8eD%2FtaKtnnZ8jh1RhBRIPv%2BIwyplaq9oBZ3KUp5z3Kkung0s77hMg2GX2DRP7K5u108sc5oBE%2F6YQ1A%3D%3D";
		
		//<자바에서 웹으로 요청하는 방법>
		//1. URL 세팅(요청주소 + 파라미터들 세팅)
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo"); /*URL*/
		
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + MY_KEY); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*xml 또는 json*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml 또는 json*/
//        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("year","UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8")); /*측정 연도*/
        urlBuilder.append("&" + URLEncoder.encode("itemCode","UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*미세먼지 항목 구분(PM10, PM25), PM10/PM25 모두 조회할 경우 파라미터 생략*/
        //URLEncoder.encode : 한글은 URLEncoding 작업을 해줘야 URL에서 정상적으로 전달이 됨       
        
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
//        System.out.println(sb.toString());
        
        
        return sb.toString();
	}
}
