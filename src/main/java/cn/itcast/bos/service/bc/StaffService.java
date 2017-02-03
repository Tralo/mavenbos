package cn.itcast.bos.service.bc;

import java.util.List;

import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.page.PageQuery;

public interface StaffService extends PageQuery{

	// 添加或者修改取派员
	void saveOrUpdate(Staff staff);
	
	// 批量删除
	void delBatch(String[] ids);

	// 还原
	void recover(String[] ids);

	// 查询未作废的取派员
	List<Staff> findAllNotDeleteStaffs();


}
