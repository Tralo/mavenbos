package cn.itcast.bos.service.bc;

import java.util.List;

import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.page.PageQuery;

public interface SubareaService extends PageQuery{
	//添加或修改子分区
	void saveOrUpdate(Subarea subarea);

	// 查询所有未关联定区的分区
	List<Subarea> findnoassiociations();

}
