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

	@Override
	public void saveFunction(Function function) {
		// 防止 "" 的 id 外键关联
		if(function.getParentFunction() != null 
				&& function.getParentFunction().getId() != null 
				&& function.getParentFunction().getId().trim().length() == 0){
			function.setParentFunction(null);
		}
		
		
		functionDAO.save(function);
		
	}

}
