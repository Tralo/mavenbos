package cn.itcast.bos.service.impl.auth;

import java.util.List;

import cn.itcast.bos.domain.auth.Function;
import cn.itcast.bos.service.auth.FunctionService;
import cn.itcast.bos.service.base.BaseService;

public class FunctionServiceImpl extends BaseService implements FunctionService {

	@Override
	public List<Function> listAll() {
		return functionDAO.findAll();
	}

}
