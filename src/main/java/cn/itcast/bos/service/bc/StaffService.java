package cn.itcast.bos.service.bc;

import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.page.PageQuery;

public interface StaffService extends PageQuery{

	void saveOrUpdate(Staff staff);


}
