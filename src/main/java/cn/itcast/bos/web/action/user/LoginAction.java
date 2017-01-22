package cn.itcast.bos.web.action.user;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.web.action.base.BaseAction;

/**
 * 登陆功能
 *
 */
public class LoginAction extends BaseAction implements ModelDriven<User>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}
	
	@Override
	public String execute() throws Exception {
		//判断验证码是否正确（比较用户输入的验证和Session中的验证码是否一致）
		String checkCodeSession = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		System.out.println("User:  " + user.toString());
		if(checkCodeSession == null || !checkCodeSession.equals(checkcode)){
			//验证码失败
			this.addActionError("验证码错误");
			return INPUT;
		}
		//验证码成功，比较用户名和密码，调用业务层
		User loginUser = userService.login(user);
		if(loginUser == null){
			this.addActionError("用户名或密码错误");
			return INPUT;
		} else {
			//登陆成功
			//将用户信息存入Session
			ServletActionContext.getRequest().getSession().setAttribute("user", loginUser);
			return SUCCESS;
		}
		
	}
	
	//接收验证码
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	

}
