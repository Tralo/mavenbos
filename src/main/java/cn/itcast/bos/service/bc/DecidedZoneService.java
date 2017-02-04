package cn.itcast.bos.service.bc;

import cn.itcast.bos.domain.bc.DecidedZone;

public interface DecidedZoneService {

	//保存定区
	void saveOrUpdate(DecidedZone decidedZone, String[] subareaId);

}
