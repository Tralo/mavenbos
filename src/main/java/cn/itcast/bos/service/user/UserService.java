package cn.itcast.bos.service.user;

import java.util.List;

import cn.itcast.bos.domain.user.User;

/**
 * 用户业务接口
 *
 */
public interface UserService {

	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	User login(User user);

	/**
	 * 修改密码
	 * @param user
	 */
	void editPassword(User user);

	/**
	 * 添加用户
	 * @param user
	 */
	void saveUser(User user);

	/**
	 * 查询所有的用户列表
	 * @return
	 */
	List<User> listAll();

	/**
	 * 授予用户角色
	 * @param user
	 */
	void grantRole(User user);
	
}
