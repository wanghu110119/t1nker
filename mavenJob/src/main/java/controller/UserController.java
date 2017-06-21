package controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pojo.Resume;
import pojo.User;
import service.UserService;
import service.UserServiceImpl.UserServiceImpl;
import util.MailUtil;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("findByUsername.do")
	public void checkname(HttpServletRequest req, HttpServletResponse resp,
			User user, String name) throws ServletException, IOException {
		user = userService.findByUsername(name);

		if (user == null) {
			resp.getWriter().print("用户名可以使用");
		} else {
			resp.getWriter().print("用户名已存在请重新输入");
		}
	}

	ModelAndView mav = new ModelAndView();

	@RequestMapping("personalregister.do")
	public ModelAndView register1(User user, HttpSession session) {

		mav.setViewName("/personal/index");
		userService.AddUser(user);
		session.setAttribute("user", user);
		return mav;
	}

	@RequestMapping("logout.do")
	public ModelAndView userlogout(HttpSession session) {
		session.invalidate();
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("userlogin.do")
	public ModelAndView userlogin(User user, HttpSession session) {
		session.setAttribute("user", null);
		session.setAttribute("admin", null);
		session.setAttribute("company", null);
		mav = userService.login(user, session);
		return mav;
	}

	@RequestMapping("usercenter.action")
	public ModelAndView usercenter() {
		mav.setViewName("/personal/index");
		return mav;
	}

	@RequestMapping("personaladdresume.action")
	public ModelAndView personaladdresume() {
		mav.setViewName("/personal/resume_1");
		return mav;
	}

	@RequestMapping("sendmail.do")
	public void sendmail(HttpSession session, HttpServletResponse resp) {
		User user = (User) session.getAttribute("user");
		MailUtil.sendMail(user.getEmail(), user.getId());
		try {
			resp.getWriter().print("请去邮箱激活");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("emailattestation.do")
	
	public ModelAndView emailattestation(User user, String id,
			HttpSession session) {
		user = userService.emailattestation(
				(User) session.getAttribute("user"), id);
		session.setAttribute("user", user);
		mav.setViewName("/personal/resume_1");
		return mav;
	}
	
	
	@RequestMapping("resume1.do")
	public ModelAndView resume1( Resume resume, HttpSession session,Model model) {
		System.out.println(resume);
		User user = (User) session.getAttribute("user");
		resume = userService.addResume(resume, user);
		model.addAttribute("resume", resume);
		session.setAttribute("user", user);
		session.setAttribute("resume", resume);
		mav.setViewName("/personal/resume_2");
		return mav;
	}


	@RequestMapping(value = "resume2.do", method = RequestMethod.POST)
	public ModelAndView resume2( Resume resume1, HttpSession session) {
		System.out.println(resume1);
		User user = (User) session.getAttribute("user");
		Resume resume = (Resume) session.getAttribute("resume");
		resume.setJobtype(resume1.getJobtype());
		resume.setNearjob(resume1.getNearjob());
		resume.setWorkplace(resume1.getWorkplace());
		resume.setMoney(resume1.getMoney());
		resume.setJobwanted(resume1.getJobwanted());
		userService.updateResume(resume, user);
		System.out.println(resume+"22222222");
		session.setAttribute("resume", resume);
		mav.setViewName("/personal/resume_3");
		return mav;
	}
	@RequestMapping(value = "resume3.do", method = RequestMethod.POST)
	public ModelAndView resume3( String jobAdvantage, HttpSession session) {
		System.out.println("========"+jobAdvantage);
		User user = (User) session.getAttribute("user");
		Resume resume = (Resume) session.getAttribute("resume");
		resume.setJobadvantage(jobAdvantage);
		userService.updateResume(resume, user);
		System.out.println(resume+"22222222");
		session.setAttribute("resume", resume);
		mav.setViewName("/personal/resume_4");
		return mav;
	}
}
