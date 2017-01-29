package cn.itcast.bos.service.bc;

import java.util.List;

import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.page.PageQuery;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;

/**
 * 收派标准业务接口
 *
 */
public interface StandardService extends PageQuery{
	//保存标准
	void saveStandard(Standard standard);

	//批量删除
	void delBatch(String[] ids);

	//获取标准聊表
	List<Standard> ajaxlist();

}
