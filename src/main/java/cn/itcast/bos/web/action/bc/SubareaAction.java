package cn.itcast.bos.web.action.bc;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.bos.web.action.base.BaseAction;

public class SubareaAction extends BaseAction implements ModelDriven<Subarea>{
	
	private static final long serialVersionUID = -4445628862338996125L;
	
	
	private Subarea subarea = new Subarea();

	@Override
	public Subarea getModel() {
		return subarea;
	}
	
	// 业务方法 --- 添加或修改子分区
	public String saveOrUpdate(){
		subareaService.saveOrUpdate(subarea);
		
		return "saveOrUpdateSUCCESS";
	}
	
	
	// 业务方法 --- 
	public String pageQuery(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		PageRequestBean pageRequestBean = initPageRequestBean(detachedCriteria);
		
		// 针对 QBC 添加查询条件
		if (subarea.getAddresskey() != null && subarea.getAddresskey().trim().length() > 0) {
			// 添加关键字条件
			detachedCriteria.add(Restrictions.like("addresskey", "%" + subarea.getAddresskey() + "%"));
		}
		
		if(subarea.getDecidedZone() != null && subarea.getDecidedZone().getId() != null && subarea.getDecidedZone().getId().trim().length() > 0){
			// 输入定区编号
			detachedCriteria.add(Restrictions.eq("decidedZone", subarea.getDecidedZone()));// 比较对象，实际上比较定区 id 属性
		}
		if(subarea.getRegion() != null){
			// 表关联, QBC解决方案 --- 别名
			detachedCriteria.createAlias("region", "r");
			if(subarea.getRegion().getProvince() != null && subarea.getRegion().getProvince().trim().length() > 0){
				detachedCriteria.add(Restrictions.like("r.province", "%" + subarea.getRegion().getProvince() + "%"));
			}
			if(subarea.getRegion().getCity() != null && subarea.getRegion().getCity().trim().length() > 0){
				detachedCriteria.add(Restrictions.like("r.city", "%" + subarea.getRegion().getCity() + "%"));
			}
			if(subarea.getRegion().getDistrict() != null && subarea.getRegion().getDistrict().trim().length() > 0){
				detachedCriteria.add(Restrictions.like("r.district", "%" + subarea.getRegion().getDistrict() + "%"));
			}
		}
		// 调用业务层
		PageResponseBean pageResponseBean = subareaService.pageQuery(pageRequestBean);
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		
		return "pageQuerySUCCESS";
	}
	

}
