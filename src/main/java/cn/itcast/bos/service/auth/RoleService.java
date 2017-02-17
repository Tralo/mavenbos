package cn.itcast.bos.service.auth;

import java.util.List;

import cn.itcast.bos.domain.auth.Role;

public interface RoleService {

	/**
	 * 保存角色
	 * @param role
	 * @param functionIds
	 */
	void saveRole(Role role, String functionIds);
	/**
	 * 查询所有
	 * @return
	 */
	List<Role> listAll();

}
