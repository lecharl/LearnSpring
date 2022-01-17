package com.kh.app10.menu;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.app10.menu.model.vo.MenuVo;

@Controller
@RequestMapping("/menu")
public class MenuController {
//	1.
//	@Autowired
//	private JdbcTemplate jt;
	
//	2.
	@Autowired
	private SqlSession sqlSession;

	@GetMapping("/insert")
	public String insert() {
		return "menu/insert";
	}
	
	@PostMapping("/insert")
	public String insert(MenuVo m) {
//		1. JdbcTemplate
//		String sql = "INSERT INTO MENU(MENU, PRICE) VALUES(?,?)";
//		Object[] params = /* new Object[] */{menu, price};
//		jt.update(sql, params);
		
//		2. SqlSession
//		menu-mapper 에서 "namespace.insert id"
		sqlSession.insert("menu.insertMenu", m);
		return "redirect:/menu/insert";
	}
}
