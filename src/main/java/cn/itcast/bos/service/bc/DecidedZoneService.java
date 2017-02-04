package cn.itcast.bos.service.bc;

import java.util.List;
import java.util.Set;

import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.page.PageQuery;

public interface DecidedZoneService extends PageQuery{

	//保存定区
	void saveOrUpdate(DecidedZone decidedZone, String[] subareaId);

	//获取关联
    Set<Subarea> attachRegions(String id);

}
