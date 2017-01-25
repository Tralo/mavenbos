package cn.itcast.bos.service.impl.bc;

import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.bc.StandardService;

public class StandardSerivceImpl extends BaseService implements StandardService{

	@Override
	public void saveStandard(Standard standard) {
		
		standardDAO.save(standard);
		
	}

}
