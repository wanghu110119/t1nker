package filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pojo.Company;
import pojo.User;

public class CompanyFilter extends HandlerInterceptorAdapter {
	private static String[] url = {	
		"companyaddjob.action"
		
		};

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		String title = req.getRequestURI().toString();
		User company  = (User)req.getSession().getAttribute("company");
//		System.out.println(company+"===========");
		for (String str : url) {
			if(title.contains(str)){
				if(company==null){
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








