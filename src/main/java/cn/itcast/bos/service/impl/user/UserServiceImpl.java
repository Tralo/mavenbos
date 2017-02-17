package cn.itcast.bos.service.impl.user;

import java.util.List;

import org.hibernate.Hibernate;

import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.user.UserService;
import cn.itcast.bos.utils.MD5Utils;

public class UserServiceImpl extends BaseService implements UserService{

	@Override
	public User login(User user) {
		List<User> users = userDAO.findByNamedQuery("User.login", user.getUsername(),MD5Utils.md5(user.getPassword()));
		if(users != null && users.size() > 0){
			if(users.get(0).getRole() != null){
				Hibernate.initialize(users.get(0).getRole().getFunctions());
				
			}
			
			return users.get(0);
		}
		
		
		return null;
	}

	@Override
	public void editPassword(User user) {
		//如果修改用户所有内容，直接调用update
		//如果修改用户某个属性，先查询，再修改
		User existUser = userDAO.findById(user.getId());
		existUser.setPassword(MD5Utils.md5(user.getPassword()));
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(MD5Utils.md5(user.getPassword()));
		if(user.getRole() != null && user.getRole().getId() != null && user.getRole().getId().length() == 0){
			user.setRole(null);
		}
		userDAO.save(user);
		
	}

	@Override
	public List<User> listAll() {
		return userDAO.findAll();
	}

	@Override
	public void grantRole(User user) {
		User exitUser = userDAO.findById(user.getId());
		exitUser.setRole(user.getRole());
	}

}
