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
	

	@Override
	public Role getModel() {
		return role;
	}
	
	

}
