package com.kh.app11.menu;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.app11.menu.model.vo.MenuVo;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private SqlSession sqlSession;
	
	@GetMapping("/insert")
	public String insert() {
		return "menu/insert";
	}
	
	@PostMapping("/insert")
	public String insert(MenuVo m) {
		sqlSession.update("menu.insertMenu", m);
		return "redirect:/menu/list";
	}
	
	@GetMapping("list")
	public String list(Model model) {
//		데이터를 전달해줘야 하므로(req.setAttribute() ... )
		//DB에서 메뉴 리스트를 가져와야 함, 전체를 가져온다면 아래처럼
		List<MenuVo> menuList = sqlSession.selectList("menu.selectMenuList");
		for(MenuVo x : menuList) {
			System.out.println(x);
		}
		//가져온 리스트를 화면에 전달
		//req.setAttribute("list", menuList);
		model.addAttribute("list", menuList);
		
		return "menu/list";
	}
	
}//class
