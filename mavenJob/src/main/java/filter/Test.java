package filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pojo.User;

public class Test extends HandlerInterceptorAdapter {
	private static String[] url = {"/usercenter.action","/personaladdresume.action"};

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		String title = req.getRequestURI().toString();
		User user  = (User)req.getSession().getAttribute("user");
//		System.out.println(user+"+++++++++++");
		for (String str : url) {
			if(title.contains(str)){
				if(user==null){
					resp.sendRedirect(req.getContextPath()+"/users/login.jsp");
					return false;
				}else{
					return true;
				}
			}
		}
		return true;
		
		
	}
	

}
