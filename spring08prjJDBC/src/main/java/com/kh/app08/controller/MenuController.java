package com.kh.app08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private JdbcTemplate jt;
//	private JdbcTemplate jt = new JdbcTemplate(); 의 효과
	
	@GetMapping("/insert")
	public String insert() {
		return "menu/insert";
	}
	
	@PostMapping("/insert")
	public String insert(String menu, String price) {
		//값을 db에 insert~
		String sql = "INSERT INTO MENU(MENU, PRICE) VALUES(?, ?)";
//		Object[] params = new Object[] {menu, price};
		Object[] params = {menu, price};
		
		//jt.update(쿼리, 파라미터), 파라미터는 Object[]
		jt.update(sql, params);
		
		
		System.out.println("post called...");
		return "redirect:menu/insert";
	}
}
