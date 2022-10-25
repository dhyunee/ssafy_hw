package com.mtcom.myboard.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mtcom.myboard.dto.UserDto;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	//DispatcherServlet 이후 다른 controller의 method를 실행하기 직전에 호출
	//뭔가 테스트를 진행하고 그 결과에 따라 return true 하면 controller로 진행
	//return false 하면 controller 로 진행하지 못 함=>empty 한 response를 client에게 보냄
	
	
	//테스트 하려는 항목 뭔지 =>이곳 코드 안에서 처리/판단
	//이걸 누구에게 적용할 것인가 => 설정을 통해서 servlet-context.xml에서 지정
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws IOException {
		
		System.out.println("LoginInterceptor : preHandle !!!");
		//#1 무조건 통과
		//return true;
		
		//#2 login
		HttpSession session=request.getSession();
		UserDto userDto=(UserDto)session.getAttribute("userDto");
		
		//login상태
		//async에 대한 처리 미구현!!
		if(userDto==null) {
			response.sendRedirect("/myboard/login");
			return false;//미통과 //단지 return false만 하면 아무런 반응 X
			
		}
		return true;//통과
		
		
	}
}
