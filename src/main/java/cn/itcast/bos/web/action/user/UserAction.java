package cn.itcast.bos.web.action.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.annotation.Privilege;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.web.action.base.BaseAction;

/**
 * 用户管理
 *
 */
public class UserAction extends BaseAction implements ModelDriven<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	public String editpassword(){
		// model 封装用户新密码
		User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		//调用业务层
		user.setId(loginUser.getId());
		Map<String, Object> map = new HashMap();
//		System.out.println(user);
		try {
			//设置返回结果
			userService.editPassword(user);
			//修改成功
			map.put("result", "success");
			map.put("msg", "修改密码成功");
			
		} catch (Exception e) {
			//修改失败
			
			map.put("result", "failure");
			map.put("msg", "修改密码失败," + e.getMessage());
			
		}
		ActionContext.getContext().put("map", map);
		return "editpasswordSUCCESS";
	}
	
	// 业务方法 --- 添加用户
	@Privilege("添加用户")
	public String save(){
		// 调用业务层 保存用户
		userService.saveUser(user);
		return "saveSUCCESS";
	}
	// 查询所有的用户
	public String list(){
		List<User> users = userService.listAll();
		// 放入值栈
		ActionContext.getContext().put("users", users);
		return "listSUCCESS";
	}
	// 业务方法 --- 授予角色
	public String grantRole(){
		//调用业务层
		userService.grantRole(user);
		return "grantRoleSUCCESS";
	}

}
