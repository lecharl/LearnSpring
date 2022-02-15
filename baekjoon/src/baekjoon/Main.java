package baekjoon;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int result = a*b*c;
		System.out.println(result);
		int[] reArr = new int[10];
		
		//숫자 하나씩 가져오기(숫자 그대로 하나씩 가져오기)
		while(result != 0) {
			//맨뒤부터, 맨뒤숫자 가져오고
			int num = result % 10;
			//없애기를 반복
			result /= 10;
			reArr[num]++;
		}
		
		
		
//		//숫자 하나씩 가져오기(문자열 charAt)
//		String reStr = String.valueOf(a*b*c);
//		int[] reArr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//		
//		for(int i=0; i<reStr.length(); i++) {
//			char num = reStr.charAt(i);	//내가 원하는 숫자
//			//기록하기(int[10] reArr)
//			//reArr[내가 가져온 숫자]++;
//			reArr[Integer.valueOf(String.valueOf(num))]++;
//		}
		
		//기록한 내용 출력
		//System.out.println(reStr);
		for(int x : reArr) {
			System.out.println(x);
		}

	}

}
