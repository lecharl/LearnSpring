package baekjoon;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int a = Integer.valueOf(sc.nextLine());
		int[] scoreArr = new int[a];
		
		for(int i=0; i<a; i++) {
			//입력하기
			String str = sc.nextLine();
			
			//잘라내기&담기
			String[] strArr = str.split("");
			
			//현재score
			int score = 0;
			//이전score
			int pastScore = 0;
			//각합계score
			int result = 0;
			//총합계score
			int allResult = 0;
			//o면 +1 하기
			for(int j=0; j<strArr.length; j++) {
				if("O".equals(strArr[j])) {
					score = 1;
					pastScore = result;
				}else {
					score = 0;
					pastScore = 0;
				}
				result = score+pastScore;
				allResult += result;
			}
			scoreArr[i] = allResult;
		}	
		
		//출력
		for(int sa : scoreArr)
			System.out.println(sa);
	}

}
