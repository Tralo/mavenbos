package cn.itcast.bos.service.qp;

import java.util.List;

import cn.itcast.bos.domain.qp.WorkOrderManage;
import cn.itcast.bos.page.PageQuery;
import cn.itcast.bos.page.PageResponseBean;

public interface WorkOrderManageService extends PageQuery{

	// 保存或更新工作单信息
	void saveOrUpdate(WorkOrderManage workOrderManage);

	// 结合 lucenne索引库进行分页查询
	PageResponseBean queryByLuence(String conditionName, String conditionValue, int page, int rows);
	// 查询未审核的工作单
	List<WorkOrderManage> listUnCheckWorkOrderManages();

	// 对工作单进行审核
	void check(WorkOrderManage workOrderManage);

}
