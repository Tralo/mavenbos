package cn.itcast.bos.service.impl.bc;

import java.io.ByteArrayOutputStream;

import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.bc.DecidedZoneService;

public class DecidedZoneServiceImpl extends BaseService implements DecidedZoneService {

	@Override
	public void saveOrUpdate(DecidedZone decidedZone, String[] subareaIds) {
		// 保存定区数据
		decidedZoneDAO.save(decidedZone);
		
		// 实现定区和分区关联（用分区对象关联定区 多方关联一方）
		for(String id : subareaIds){
			Subarea subarea = subareaDAO.findById(id);
			subarea.setDecidedZone(decidedZone);
			
		}
		
		
	}

}
