package com.kh.app20;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		String str1 = null;
		str1.substring(0, 2);	//npe error
		
		int i = 1 / 0;	//ame error

		String str2 = "o1o";
		System.out.println(Integer.parseInt(str2));	//nfe error
		
		
		return "home";
	}
	
	@RequestMapping("zero")
	public String zero() {
		int i = 1 / 0;
		System.out.println(i);	//ame error
		return "home";
	}
	
	@RequestMapping("format")
	public String format() {
		Integer.parseInt("zzz");
		return "home";
	}
}
