package cn.itcast.bos.service.qp;

import cn.itcast.bos.domain.qp.WorkOrderManage;
import cn.itcast.bos.page.PageQuery;
import cn.itcast.bos.page.PageResponseBean;

public interface WorkOrderManageService extends PageQuery{

	// 保存或更新工作单信息
	void saveOrUpdate(WorkOrderManage workOrderManage);

	// 结合 lucenne索引库进行分页查询
	PageResponseBean queryByLuence(String conditionName, String conditionValue, int page, int rows);

}
