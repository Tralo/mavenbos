package cn.itcast.bos.service.impl.bc;

import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.bc.StaffService;

public class StaffServiceImpl extends BaseService implements StaffService{

	@Override
	public void saveOrUpdate(Staff staff) {
		staffDAO.saveOrUpdate(staff);
	}

	@Override
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		return pageQuery(pageRequestBean,staffDAO);
	}

	

}
