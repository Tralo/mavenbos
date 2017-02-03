package cn.itcast.bos.web.action.bc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
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
		ServletActionContext.getRequest().getSession().setAttribute("pageResponseBean", pageResponseBean);
		
		return "pageQuerySUCCESS";
	}
	
	// 业务方法 --- 以 Excel 形式导出数据
	public String exportXls(){
		
		return "exportXlsSUCCESS";
	}
	// 提供文件下载流
	public InputStream getInputStream() throws IOException{
		// 将 Session 中缓存 PageResponseBean 中数据, 生成 Excel
		PageResponseBean pageResponseBean = (PageResponseBean) ServletActionContext.getRequest().getSession().getAttribute("pageResponseBean");
		List<Subarea> subareas = pageResponseBean.getRows();
		// 根据内存的数据生成 Excel
		// 工作薄
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		// sheet 
		HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
		// 先写标题行
		HSSFRow headRow = sheet.createRow(0);// 第一行(标题行)
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("关键字");
		headRow.createCell(2).setCellValue("起始号");
		headRow.createCell(3).setCellValue("结束号");
		headRow.createCell(4).setCellValue("是否区分单双号");
		headRow.createCell(5).setCellValue("位置信息");
		int rowLine = 1;
		for(Subarea subarea : subareas){
			//每个分区一行
			HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
			row.createCell(0).setCellValue(subarea.getId());
			row.createCell(1).setCellValue(subarea.getAddresskey());
			row.createCell(2).setCellValue(subarea.getStartnum());
			row.createCell(3).setCellValue(subarea.getEndnum());
			row.createCell(4).setCellValue(subarea.getSingle());
			row.createCell(5).setCellValue(subarea.getPosition());
		}
		// 将数据缓存到字节数组
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		hssfWorkbook.write(bos);
		bos.close();
		byte[] data = bos.toByteArray();
		// 再通过字节数组输入流读取数据
		return new ByteArrayInputStream(data);
	}
	
	

}
