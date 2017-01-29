package cn.itcast.bos.service.bc;

import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;

public interface StaffService {

	void saveOrUpdate(Staff staff);

	PageResponseBean pageQuery(PageRequestBean pageRequestBean);

}
