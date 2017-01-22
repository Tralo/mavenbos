package cn.itcast.bos.service.user;

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
	
}
