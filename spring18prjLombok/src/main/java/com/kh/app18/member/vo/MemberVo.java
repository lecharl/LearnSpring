package com.kh.app18.member.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data				
//@Getter + @Setter + @ToString
@AllArgsConstructor	
//일반생성자
@NoArgsConstructor	
//기본생성자
@Builder
//setter의 장점 + 생성자의 장점, .build() 할 때까지 생성자를 뱉지 않는다.
public class MemberVo {
	
	private String id;
	private String pwd;
	private String name;
	
}
