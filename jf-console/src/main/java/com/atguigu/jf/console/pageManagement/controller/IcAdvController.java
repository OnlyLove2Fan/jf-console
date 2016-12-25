package com.atguigu.jf.console.pageManagement.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.pageManagement.bean.bo.AreaDef;
import com.atguigu.jf.console.pageManagement.bean.pojo.IcAdv;
import com.atguigu.jf.console.pageManagement.service.AreaDefService;
import com.atguigu.jf.console.pageManagement.service.IcAdvService;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("IcAdv")
public class IcAdvController {

	@Autowired
	private IcAdvService icAdvService;
	
	@Autowired
	private AreaDefService areaDefService;
	
	@RequestMapping("/showOrShutDownOrDeleteIcAdv")
	@ResponseBody
	public ResultBean showOrShutDownOrDeleteIcAdv(String type, IcAdv icAdv){
		
		ResultBean rt = new ResultBean();
		// 发布
		if("showIcAdv".equals(type)) {
			icAdv.setAdvState(new Short("1"));
		}
		
		// 下架
		if("shutDownAdv".equals(type)) {
			icAdv.setAdvState(new Short("2"));
		}
		
		// 删除
		if("deleteIcAdv".equals(type)) {
			icAdv.setDataState(new Short("0"));
		}
		int i = icAdvService.updateByPrimaryKeySelective(icAdv);
		
		if(i < 1) {
			rt.setResult(ResultBean.RESULT_FAILED);
			rt.setMsg("操作失败!");
		} else {
			rt.setResult(ResultBean.RESULT_SUCCESS);
			rt.setMsg("操作成功!");
		}
		
		return rt;
	}
	
	@RequestMapping("/upMove")
	@ResponseBody
	public ResultBean upMove(IcAdv icAdv, String type) throws Exception {
		
		ResultBean rt = new ResultBean();
		Long old_advOrder = null;
		Long new_advOrder = null;
		
		if ("up".equals(type)) {
			// 上移
			old_advOrder = icAdv.getAdvOrder(); //2
			new_advOrder = old_advOrder - 1; //1
			
			// 若要上移的对象的order值小于等于1，不允许上移
			if (1 >= Integer.parseInt(old_advOrder + "")) {
				rt.setResult(ResultBean.RESULT_FAILED);
				rt.setMsg("移动失败!");
				return rt;
			}
			
		}
		if ("down".equals(type)) {
			// 下移
			old_advOrder = icAdv.getAdvOrder(); //2
			new_advOrder = old_advOrder + 1; //3
			
			// 若要下移的对象的order的值大于等于最大的order值，不允许下移操作
			if (icAdvService.selectMaxOrder() >= Integer.parseInt(old_advOrder + "")) {
				rt.setResult(ResultBean.RESULT_FAILED);
				rt.setMsg("移动失败!");
				return rt;
			}
		}
		// 先根据当前（order-1）的记录的order值为改为当前记录的（order）值
		// 在根据当前Id将当前的order改为（order-1）
		
		Map<String, Object> map = new HashedMap<>();
		map.put("old_advOrder", old_advOrder);
		map.put("new_advOrder", new_advOrder);
		map.put("advId", icAdv.getAdvId());
		
		try {
			icAdvService.updateNewOrder(map);
			icAdvService.updateOldOrder(map);
			rt.setResult(ResultBean.RESULT_SUCCESS);
			rt.setMsg("移动成功!");
		} catch (Exception e) {
			e.printStackTrace();
			rt.setResult(ResultBean.RESULT_FAILED);
			rt.setMsg("移动失败!");
			throw e;
		}
		
		return rt;
	}
	
	@RequestMapping("/showIcAdv")
	@ResponseBody
	public ResultBean showIcAdv(IcAdv icAdv, String type) throws Exception {
		
		ResultBean rt = new ResultBean();
		icAdv.setAdvState(new Short("1"));
		int i = 0;
		if("add".equals(type)) {
			// 为新添加的IcAdv设置值order为maxOrder+1
			Integer maxOrder = icAdvService.selectMaxOrder();
			String currentOrder = maxOrder + 1 + "";
			// 为当前Adv设置Order
			icAdv.setAdvOrder(new Long(currentOrder));
			
			i = icAdvService.insertSelective(icAdv);
		} else {
			i = icAdvService.updateByPrimaryKeySelective(icAdv);
		}
		if(i < 1) {
			rt.setResult(ResultBean.RESULT_FAILED);
			rt.setMsg("发布失败!");
		} else {
			rt.setResult(ResultBean.RESULT_SUCCESS);
			rt.setMsg("发布成功!");
		}
		
		return rt;
	}
	
	@RequestMapping("/modifyIcAdv")
	@ResponseBody
	public ResultBean modifyIcAdv(IcAdv icAdv) {
		
		ResultBean rt = new ResultBean();
		icAdv.setAdvState(new Short("2"));
		Integer i = icAdvService.updateByPrimaryKeySelective(icAdv);
		if(i < 1) {
			rt.setResult(ResultBean.RESULT_FAILED);
			rt.setMsg("修改失败!");
		} else {
			rt.setResult(ResultBean.RESULT_SUCCESS);
			rt.setMsg("修改成功!");
		}
		
		return rt;
	}
	
	@RequestMapping("/addIcAdv")
	@ResponseBody
	public ResultBean addIcAdv(IcAdv icAdv, @RequestParam(value="type",required=false)String type) throws Exception {
		
		ResultBean rt = new ResultBean();
		
		Integer maxOrder = icAdvService.selectMaxOrder();
		String currentOrder = maxOrder + 1 + "";
		// 为当前Adv设置Order
		icAdv.setAdvOrder(new Long(currentOrder));
		
		icAdv.setAdvState(new Short("2"));
		Integer i = icAdvService.insertSelective(icAdv);
		if(i < 1) {
			rt.setResult(ResultBean.RESULT_FAILED);
			rt.setMsg("添加失败!");
		} else {
			rt.setResult(ResultBean.RESULT_SUCCESS);
			rt.setMsg("添加成功!");
		}
		return rt;
	}
	
	@ResponseBody
	@RequestMapping("/uploadFile")
	public ResultBean uploadFile(MultipartFile uploadFile) throws Exception {
		ResultBean rt = new ResultBean();
		
		//1.定义需要上传的文件夹路径
		String path = "D:\\testFileUpload\\";
		//2.如果文件夹不存在，则创建
		File filePath = new File(path);
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
		
		Date date = new Date();
		long time = date.getTime();
		// 获取到原始文件名
		// 注意文件名应该是随机的！！！！
		String fileName = time + "" + uploadFile.getOriginalFilename() ;
		
		//3.创建文件
		File file = new File(path + fileName);
		
		try {
			uploadFile.transferTo(file);
			rt.setResult(ResultBean.RESULT_SUCCESS);
			rt.setMsg(fileName);
		} catch (Exception e) {
			rt.setResult(ResultBean.RESULT_FAILED);
			rt.setMsg("上传文件时发生异常。");
		}
		
		return rt;
	}
	
	@RequestMapping("/addIcAdvPage")
	public String addIcAdvPage(@RequestParam(value="type", required=false) String type,
			@RequestParam(value="advId", required=false) String advId, Map<String, Object> map) throws Exception {
		
		map.put("advId", advId);
		map.put("type", type);
		if("modify".equals(type)) {
			List<IcAdv> list = icAdvService.selectIcAdvListByAreaOrName(map);
			IcAdv icAdv = list.get(0);
			
			// 获得AreaCode对象
			map.put("areaCode", icAdv.getAdvAreaId());
			List<AreaDef> list2 = areaDefService.seleceAreaDef(map);
			AreaDef areaDef = list2.get(0);
			map.put("icAdv", icAdv);
			map.put("areaDef", areaDef);
		}
		return "pageManagement/addAdv";
	}
	
	@ResponseBody
	@RequestMapping("/selectIcAdvListByAreaOrName")
	public String selectIcAdvListByAreaOrName(IcAdv icAdv,@RequestParam(value="advPosName", required=false) String advPosName, 
				Integer page, Integer limit) throws Exception {
		
		Map<String, Object> map = new HashedMap<>();
		map.put("advState", icAdv.getAdvState());
		map.put("advPos", icAdv.getAdvPos());
		map.put("advAreaId", icAdv.getAdvAreaId());
		if("首页".equals(advPosName)) {
			map.put("advPos", new Short("1"));
		} else if("特价区".equals(advPosName)) {
			map.put("advPos", new Short("2"));
		}
		
		/**
		 * 开始进行分页
		 * 第一个参数：页码
		 * 第二个参数：每页显示条数
		 */
		PageHelper.startPage(page, limit);
		List<IcAdv> list = icAdvService.selectIcAdvListByAreaOrName(map);
		
		// 设置日期格式
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
		String result = JSON.toJSONString(list, SerializerFeature.WriteDateUseDateFormat);
		return result;
	}
}
