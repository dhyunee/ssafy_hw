package com.mycom.myboard.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycom.myboard.dto.UserDto;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	// DispatcherServlet 이후 다른 Controller의 메소드를 실행하기 직전에 호출
	// 뭔가 테스트를 진행하고 그 결과에 따라 return true 하면 Controller로 진행
	// return false를 하면 Controller로 진행하지 못함 => empty response를 Client에 보낸다.
	
	// 테스트 하려는 항목이 뭔가!! => 이곳 코드안에서 처리/판단
	// 이걸 누구에게 적용할 것인가!! => 설정을 통해서 servlet-context.xml 지정
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response,
			Object handler) throws Exception {
		
		// Interceptor가 적용이 되는 걸 인식하게 하기 위해 print
		System.out.println("LoginInterceptor : preHandle!!!");
		
		// #1 무조건 통과 test
//		return true;
		
		// #2 login test
		// async에 대한 처리 미구현!!!!
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
//		if (userDto == null) { // 로그인 실패
//			response.sendRedirect("/login");
//			return false; // 단지 return false만 하면 아무런 반응 X <= response 필요
//		}
//		
//		return true; // 로그인 통과 - Client가 가고자 하는 요청으로 넘어간다.
		
		// #3 페이지 요청과 async 요청에 대한 처리를 따로 구성
		
		// async 요청인지 확인
		String async = request.getHeader("async"); // client가 담아서 보내야함
		
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		if (userDto == null) {
			// 나눠서 처리
			if ("true".equals(async)) { // async 요청
				// json으로 session timeout => login 이동하라는 내용을 만들어서 보낸다.
				Gson gson = new Gson();
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("result", "login");
				String jsonStr = gson.toJson(jsonObject);
				response.getWriter().write(jsonStr);
			} else { // 페이지 요청
				response.sendRedirect("/login");
			}
			return false;
		}
		return true;
	}
}
