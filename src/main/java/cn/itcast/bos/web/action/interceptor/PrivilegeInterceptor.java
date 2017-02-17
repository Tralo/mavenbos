package cn.itcast.bos.web.action.interceptor;

import java.lang.reflect.Method;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sun.mail.handlers.message_rfc822;

import cn.itcast.bos.annotation.Privilege;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.utils.PrivilegeUtils;

/**
 * 权限控制拦截器
 */
public class PrivilegeInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 假设用户已经登陆
		// 1. 判断目标Action业务方法上，是否已经有 Privilege注解
		Class c = invocation.getAction().getClass();// 目标Action 的Class对象
		String methodName = invocation.getProxy().getMethod();// 目标业务方法名称
		Method method = c.getDeclaredMethod(methodName);
		method.setAccessible(true);
		// 判断是否具有注解
		if(method.isAnnotationPresent(Privilege.class)){
			// 需要权限
			// 2. 获得注解中需要的权限 
			Privilege privilege = method.getAnnotation(Privilege.class);
			String needPrivilege = privilege.value();
			// 3，判断当权用户是否具有该权限 
			User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(PrivilegeUtils.checkHasPrivilege(user,needPrivilege)){
				//有权限
				return invocation.invoke();
			} else {
				// 没有权限
				return "noprivilege";
			}
			
		} else {
			// 无注解,不需要权限
			return invocation.invoke();
		}
		
	}

}
