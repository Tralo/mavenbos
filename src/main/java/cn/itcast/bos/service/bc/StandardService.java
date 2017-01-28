package cn.itcast.bos.service.bc;

import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;

/**
 * 收派标准业务接口
 *
 */
public interface StandardService {
	//保存标准
	void saveStandard(Standard standard);

	//分页查询
	PageResponseBean pageQuery(PageRequestBean pageRequestBean);

	//批量删除
	void delBatch(String[] ids);

}
