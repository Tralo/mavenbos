package cn.itcast.bos.service.impl.bc;

import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.bc.RegionService;

public class RegionServiceImpl extends BaseService implements RegionService{

	@Override
	public void saveRegion(Region region) {
		regionDAO.save(region);
	}

}
