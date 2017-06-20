package service.UserServiceImpl;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import mapper.ResumeMapper;
import mapper.UserMapper;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import org.springframework.web.servlet.ModelAndView;

import pojo.Resume;
import pojo.User;
import service.UserService;
@Service
public class UserServiceImpl implements UserService{
	ModelAndView mav = new ModelAndView();
	private User user;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ResumeMapper resumeMapper;
	@Override
	public User findByUsername(String name) {
		user = userMapper.selectByUserName(name);
		return user;
	}
	@Override
	public void AddUser(User user) {
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		user.setCartid(user.getId());
		user.setUsername("新用户");
		user.setState(false);
		user.setPermission(0);
		userMapper.insert(user);
		
	}
	@Override
	public ModelAndView login(User user, HttpSession session) {
		String password = DigestUtils.md5Hex(user.getPassword());
		user = userMapper.selectByUserName(user.getAccount());
		if(user.getPassword().equals(password)){
			if(user.getPermission()==0){
				session.setAttribute("user", user);
			}else if(user.getPermission()==1){
				session.setAttribute("company", user);
			}else if(user.getPermission()==2){
				session.setAttribute("admin", user);
			}
			mav.setViewName("/index");
		}else{
			mav.addObject("err", "您输入的账户或者密码错误");
			mav.setViewName("/users/login");
		}
		return mav;
	}
	@Override
	public User emailattestation(User user, String id) {
		user.setState(true);
		userMapper.updateByPrimaryKey(user);
		user = userMapper.selectByPrimaryKey(user.getId());
		System.out.println(user);
		return user;
	}
	@Override
	public Resume addResume(Resume resume,User user) {
		resume.setId(UUID.randomUUID().toString().replace("-", ""));
		resume.setUid(user.getId());
		resumeMapper.insert(resume);
		return resumeMapper.selectByPrimaryKey(resume.getId());
	}
	@Override
	public void updateResume(Resume resume, User user) {
		resumeMapper.updateByPrimaryKey(resume);
		
	}
	@Override
	public Resume findById(Resume resume) {
		return resumeMapper.selectByPrimaryKey(resume.getId());
		
	}

}














