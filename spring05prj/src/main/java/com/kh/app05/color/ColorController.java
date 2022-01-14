package com.kh.app05.color;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.app05.color.model.vo.ColorVo;

@Controller
@RequestMapping("/color")
public class ColorController {

	@GetMapping("/select_color")
	public String selectColor() {
		return "/color/select_color";
	}
	
	@PostMapping("/select_color")
	public String selectColor(@ModelAttribute ColorVo c) {
//		public String selectColor(@RequestParam List<String> color) {
		//다중 파라미터 처리
//		1) req.getParameterValues();
//		String[] colors = req.getParameterValues("color");
//		for(String s : colors) {
//			System.out.println(s);
//		}
		
//		2) 파라미터(String[] 타입)
		
//		String[] colors = c.getColor();
		List<String> colors = c.getColor();
		
		for(String s : colors) {
			System.out.println(s);
		}
		
//		3) @RequestParam List<String>
//		for(String s : color) {
//			System.out.println(s);
//		}
		return "redirect:/color/select_color";
	}
}
