package cn.itcast.bos.service.bc;

import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.page.PageQuery;

public interface SubareaService extends PageQuery{
	//添加或修改子分区
	void saveOrUpdate(Subarea subarea);

}
