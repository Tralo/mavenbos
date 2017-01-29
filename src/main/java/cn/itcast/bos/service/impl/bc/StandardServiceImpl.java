package cn.itcast.bos.service.impl.bc;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.bc.StandardService;

public class StandardServiceImpl extends BaseService implements StandardService{

	@Override
	public void saveStandard(Standard standard) {
		standardDAO.saveOrUpdate(standard);
	}

	@Override
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		return pageQuery(pageRequestBean,standardDAO);
	}

	@Override
	public void delBatch(String[] ids) {
		//将数据 deltag 改为 1
		for(String id : ids){
			Standard standard = standardDAO.findById(id);
			standard.setDeltag("1");
		}
		
	}

	@Override
	public List<Standard> ajaxlist() {
		List<Standard> standards = standardDAO.findByNamedQuery("Standard.ajaxlist");
		return standards;
	}

}
