package cn.itcast.bos.utils;

import java.util.Set;

import cn.itcast.bos.domain.auth.Function;
import cn.itcast.bos.domain.auth.Role;
import cn.itcast.bos.domain.user.User;

public class PrivilegeUtils {

	/**
	 * 判断用户是否具有权限
	 * @param user
	 * @param needPrivilege
	 * @return
	 */
	public static boolean checkHasPrivilege(User user, String needPrivilege) {
		// admin 直接放行
		if(user.getUsername().equals("admin")){
			return true;
		}
		
		Role role = user.getRole();
		if(role == null){
			//没有角色，没权限
			return false;
		} else {
			// 有角色
			Set<Function> functions = role.getFunctions();
			for(Function function : functions){
				if(function.getName().equals(needPrivilege)){
					return true;
				}
			}
		}
		
		return false;
	}

}
