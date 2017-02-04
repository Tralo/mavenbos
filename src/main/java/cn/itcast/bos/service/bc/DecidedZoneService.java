package cn.itcast.bos.service.bc;

import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.page.PageQuery;

public interface DecidedZoneService extends PageQuery{

	//保存定区
	void saveOrUpdate(DecidedZone decidedZone, String[] subareaId);

}
