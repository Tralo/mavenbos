package cn.itcast.bos.service.impl.user;

import java.util.List;

import org.hibernate.Hibernate;
import org.jbpm.api.IdentityService;
import org.jbpm.api.identity.Group;

import cn.itcast.bos.domain.auth.Role;
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
		// 在添加用户的同时，往 JBPM 的数据库插入一个用户
		IdentityService identityService = processEngine.getIdentityService();
		identityService.createUser(user.getId(), user.getUsername(), user.getUsername());// 建立 JBPM 用户
		if(user.getRole() != null){
			// 在添加用户时，建立了和角色关系
			Role role = roleDAO.findById(user.getRole().getId());
			// 建立关系，JBPM 组 id 使用角色 name 属性
			identityService.createMembership(user.getId(), user.getRole().getName());
		}
		
	}

	@Override
	public List<User> listAll() {
		return userDAO.findAll();
	}

	@Override
	public void grantRole(User user) {
		User exitUser = userDAO.findById(user.getId());
		exitUser.setRole(user.getRole());
		// 建立 JBPM 用户和组关系，一个用户只属于一个组
		// 先删除这个用户和原来关系，建立新关系
		IdentityService identityService = processEngine.getIdentityService();
		// 获得用户原来的组
		List<Group> list = identityService.findGroupsByUser(exitUser.getId());
		for(Group groud : list){
			identityService.deleteMembership(exitUser.getId(), groud.getId(), null);
		}
		// 建立新关系
		Role role = roleDAO.findById(exitUser.getRole().getId());
		identityService.createMembership(exitUser.getId(), role.getName());
		
	}

}
