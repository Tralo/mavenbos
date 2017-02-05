package cn.itcast.bos.service.qp;

import cn.itcast.bos.domain.qp.WorkOrderManage;
import cn.itcast.bos.page.PageQuery;

public interface WorkOrderManageService extends PageQuery{

	// 保存或更新工作单信息
	void saveOrUpdate(WorkOrderManage workOrderManage);

}
