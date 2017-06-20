package service;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import pojo.Resume;
import pojo.User;

public interface UserService {
	ModelAndView mav = new ModelAndView();
	User findByUsername(String name);

	void AddUser(User user);

	ModelAndView login(User user, HttpSession session);

	User emailattestation(User user, String id);

	Resume addResume(Resume resume, User user);

	void updateResume(Resume resume, User user);

	Resume findById(Resume resume);

}
