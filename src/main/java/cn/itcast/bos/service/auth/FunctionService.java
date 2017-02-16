package cn.itcast.bos.service.auth;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bos.domain.auth.Function;

public interface FunctionService {

	/**
	 * 查询所有功能列表
	 * @return
	 */
	List<Function> listAll();

	
	/**
	 * 保存权限信息
	 * @param function
	 */
	void saveFunction(Function function);

	/**
	 * 查询树节点数据
	 * @return
	 */
	List<Function> findTreeData(DetachedCriteria detachedCriteria);

}
