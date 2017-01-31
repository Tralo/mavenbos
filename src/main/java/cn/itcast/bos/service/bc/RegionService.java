package cn.itcast.bos.service.bc;

import java.util.List;

import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.page.PageQuery;

/**
 * 区域管理业务接口
 */
public interface RegionService extends PageQuery{
	//添加区域
	void saveRegion(Region region);

	// 查询所有区域
	List<Region> findAllRegions();

}
