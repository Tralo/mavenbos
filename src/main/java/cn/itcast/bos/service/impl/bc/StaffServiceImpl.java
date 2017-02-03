package cn.itcast.bos.service.impl.bc;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

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

	@Override
	public void delBatch(String[] ids) {
		// 修改每个id 对应的取派员deltag 为 1
		for(String id : ids){
			Staff staff = staffDAO.findById(id);
			staff.setDeltag("1");
		}
	}

	@Override
	public void recover(String[] ids) {
		// 修改每个id 对应的取派员deltag 为 1
		for(String id : ids){
			Staff staff = staffDAO.findById(id);
			staff.setDeltag("0");
		}
	}

	@Override
	public List<Staff> findAllNotDeleteStaffs() {
		// 查询条件为 deltag = 0
		List<Staff> staffs = staffDAO.findByNamedQuery("Staff.findNoDelete");
		
		return staffs;
	}

	

}
