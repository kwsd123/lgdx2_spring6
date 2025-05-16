package com.lgdx.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lgdx.entity.Member;
import com.lgdx.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	@GetMapping("/login.do")
	public String login() {
		return "login";
	}
	
	@GetMapping("/join.do")
	public String join() {
		return "join";
	}
	
	
	@PostMapping("/join.do")
	public String join(Member vo) {
		System.out.println("회원가입 기능");
		System.out.println(vo.toString());
		service.join(vo);
		return "redirect:/boardList.do";
	}
	
	@PostMapping("/login.do")
	public String login(Member vo, HttpSession session) {
		System.out.println("로그인 기능");
		System.out.println(vo.toString());
		Member info = service.login(vo);
		
		if(info != null) {
			System.out.println("로그인 성공!");
			 
			session.setMaxInactiveInterval(60 * 10);
			session.setAttribute("info", info);
		}else {
			System.out.println("로그인 실패");
		}
		
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("로그아웃 기능");
		// 1.세션의 특정 이름의 값을 삭제하는 방법
		// session.removeAttribute("info");
		// 2.해당 client의 세션 전체를 만료시키는 방법
		session.invalidate();
		return "redirect:/boardList.do";
	}
	
	
}
