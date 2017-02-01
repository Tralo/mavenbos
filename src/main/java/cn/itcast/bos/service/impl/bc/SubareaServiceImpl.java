package cn.itcast.bos.service.impl.bc;

import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.bc.SubareaService;

public class SubareaServiceImpl extends BaseService implements SubareaService{

	@Override
	public void saveOrUpdate(Subarea subarea) {
		subareaDAO.saveOrUpdate(subarea);
	}

	@Override
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		
		return pageQuery(pageRequestBean, subareaDAO);
	}

}
