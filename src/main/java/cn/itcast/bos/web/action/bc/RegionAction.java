package cn.itcast.bos.web.action.bc;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.bos.utils.PinYin4jUtils;
import cn.itcast.bos.web.action.base.BaseAction;

@SuppressWarnings("serial")
public class RegionAction extends BaseAction implements ModelDriven<Region>{
	
	private static final Logger LOGGER = Logger.getLogger(RegionAction.class);
	
	private Region region = new Region();
	//接收上传文件
	private File upload;// upload就是页面上传项的 name 属性
	
	public void setUpload(File upload) {
		this.upload = upload;
	}


	@Override
	public Region getModel()  {
		
		return region;
	}
	// 业务方法 --- 分页列表显示
	public String pageQuery(){
		// 封装 PageRequestBean
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Region.class);
		PageRequestBean pageRequestBean = initPageRequestBean(detachedCriteria);
		// 调用业务层， 查询 PageReponse 对象
		PageResponseBean pageResponseBean = regionService.pageQuery(pageRequestBean);
		
		// 将结果转换成 json
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		return "pageQuerySUCCESS";
	}
	
	// 业务方法 --- 导入 excel 区域数据
	public String importXls() throws Exception {
		//进行 Excel 解析
		// 1. 工作薄对象
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(upload));
		// 解析工作薄
		hssfWorkbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);// 避免空指针异常
		
		// 2. 获得 Sheet
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0);//获取第一个 sheet
		
		// 3. 遍历每一行
		for(Row row : sheet){
			//进行解析，每一行数据， 对应 Region 区域信息
			if(row.getRowNum() == 0){// 第一行（表头，无需解析）
				continue;
			}
			// 从第二行开始解析
			Region region = new Region();
			String id = row.getCell(0).getStringCellValue();// 获得第一个单元格信息
			if(id.trim().equals("")){
				// id 无值，跳过
				continue;
			}
			region.setId(id);
			region.setProvince(row.getCell(1).getStringCellValue());
			region.setCity(row.getCell(2).getStringCellValue());
			region.setDistrict(row.getCell(3).getStringCellValue());
			region.setPostcode(row.getCell(4).getStringCellValue());
			
			// 使用 pinyin4j 生成简码和城市编码
			// 连接省市区
			String str = region.getProvince() + region.getCity() + region.getDistrict();
			str.replaceAll("省", "").replaceAll("市", "").replaceAll("区", "");
			// 使用 pinyin4j
			String[] arr = PinYin4jUtils.getHeadByString(str);
			StringBuffer sb = new StringBuffer();
			for(String var : arr){
				sb.append(var);
			}
			
			region.setShortcode(sb.toString());
			
			// 生成城市编码
			
			region.setCitycode(PinYin4jUtils.hanziToPinyin(region.getCity(),""));
			// 保存 region 信息(批量导入如果出错怎么办? )
			try{
				regionService.saveRegion(region);
			}catch (Exception e) {
				// 导入region 失败 ，记入日志
				LOG.error("区域导入失败, 编号:  " + region.getId(), e);
			}
		}
		
		// 返回 json
		Map<String,Object> uploadResultMap = new HashMap<String, Object>();
		uploadResultMap.put("result", "success");
		uploadResultMap.put("msg", "区域数据导入成功");
		ActionContext.getContext().put("uploadResultMap", uploadResultMap);
		
		return "importXlsSUCCESS";
	}
	// 业务方法 -----   获取区域列表数据,转换 json 列表
	public String ajaxlist(){
		// 调用业务层，查询所有区域信息
		List<Region> regions = regionService.findAllRegions();
		// 将查询结果转换 json 格式
		ActionContext.getContext().put("regions", regions);
		return "ajaxlistSUCCESS";
	}

}
