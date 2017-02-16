package cn.itcast.bos.web.action.auth;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.auth.Role;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 角色管理
 */
public class RoleAction extends BaseAction implements ModelDriven<Role>{

	private static final long serialVersionUID = 1L;
	
	private Role role = new Role();
	
	private String functionIds;
	
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	@Override
	public Role getModel() {
		return role;
	}
	
	public String save(){
		// 调用业务层,添加角色,完成授权
		roleService.saveRole(role,functionIds);
		
		return "saveSUCCESS";
	}

}
