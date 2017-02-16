package cn.itcast.bos.service.auth;

import java.util.List;

import cn.itcast.bos.domain.auth.Function;

public interface FunctionService {

	/**
	 * 查询所有功能列表
	 * @return
	 */
	List<Function> listAll();

}
