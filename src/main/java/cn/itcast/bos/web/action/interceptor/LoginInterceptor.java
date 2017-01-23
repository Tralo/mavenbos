package cn.itcast.bos.web.action.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.itcast.bos.domain.user.User;

public class LoginInterceptor extends AbstractInterceptor{
	
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//从 Session 中获取登陆用户信息
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user == null){
			//没有登陆
			//设置错误信息
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("你还未登陆或者长时间没有使用，请重新登陆");
			return "login";
		} else {
			//已经登陆
			return invocation.invoke();
		}
	}

}
