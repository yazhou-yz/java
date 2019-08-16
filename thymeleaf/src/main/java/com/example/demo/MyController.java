package com.example.demo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	@GetMapping("/first")
	public ModelAndView first() {
		ModelAndView andView=new ModelAndView();
		List<Student> students=new ArrayList<Student>();
		students.add(new Student("张倩", 21));
		students.add(new Student("张倩1", 21));
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", "张倩");
		map.put("age", 12);
		andView.addObject("title", "<h1>我是后台的title</h1>");
		andView.addObject("color", "red");
		andView.addObject("height", "400px");
		andView.addObject("sex", "才徐坤");
		andView.addObject("students", students);
		andView.addObject("student", new Student("lallalla", 22));
		andView.addObject("map", map);
		andView.setViewName("first");
		return andView;
	}
}
