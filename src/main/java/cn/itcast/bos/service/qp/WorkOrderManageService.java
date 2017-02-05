package cn.itcast.bos.service.qp;

import cn.itcast.bos.domain.qp.WorkOrderManage;

public interface WorkOrderManageService {

	// 保存或更新工作单信息
	void saveOrUpdate(WorkOrderManage workOrderManage);

}
