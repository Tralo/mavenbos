package cn.itcast.bos.service.impl.auth;

import cn.itcast.bos.domain.auth.Function;
import cn.itcast.bos.domain.auth.Role;
import cn.itcast.bos.service.auth.RoleService;
import cn.itcast.bos.service.base.BaseService;

public class RoleServiceImpl extends BaseService implements RoleService {

	@Override
	public void saveRole(Role role, String functionIds) {
		// 将role 信息保存角色表
		roleDAO.save(role);// 持久化
		// 建立 role 和 function 联系, 向role_function中间插入数据
		if(functionIds != null){
			String[] ids = functionIds.split(",");
			for(String id: ids){
				Function function = functionDAO.findById(id);// 功能权限
				role.getFunctions().add(function);// 多对多关联，向中间表插入数据
//				function.getRoles().add(role); 同时写，要加入 inverse 属性，不然会重复插入
			}
		}
		
		
	}

}
