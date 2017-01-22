package cn.itcast.bos.web.action.base;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.bos.service.user.UserService;

/**
 * 公共抽象Action(实现代码复用)
 */
public abstract class BaseAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "userService")
	protected UserService userService;
	

}
