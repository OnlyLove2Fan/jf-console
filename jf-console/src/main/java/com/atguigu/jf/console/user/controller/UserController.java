package com.atguigu.jf.console.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.atguigu.jf.console.common.bean.bo.PageModel;
import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.UserService;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/uploadFile")
	public ResultBean uploadFile(MultipartFile uploadFile) throws Exception {
		
		ResultBean rlt = new ResultBean();
		// 1、定义需要上传的文件夹路径
		String path = "D:\\testFileUpload\\";
		// 2、如果文件夹不存在，则创建
		File filePath = new File(path);
		if(!filePath.exists()){
			filePath.mkdirs();
		}
		
		Date date = new Date();
		long time = date.getTime();
		// 获取到原始文件名
		// 注意文件名应该是随机的！！！！
		String fileName = time + "" + uploadFile.getOriginalFilename() ;
		
		// 3、创建文件
		File file = new File(path + fileName);
		
		try {
			uploadFile.transferTo(file);
			rlt.setResult(ResultBean.RESULT_SUCCESS);
			rlt.setMsg(fileName);
		} catch (Exception e) {
			rlt.setResult(ResultBean.RESULT_FAILED);
			rlt.setMsg("上传文件时发生异常。");
		}
		
		return rlt;
	}
	
	@RequestMapping(value="uploadFileByOss",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean uploadFileByOss(MultipartFile uploadFile) throws Exception {
		
		// endpoint以杭州为例，其它region请按实际情况填写
		String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
		// accessKey请登录https://ak-console.aliyun.com/#/查看
		String accessKeyId = "WuS8WuJxWJapjxbT";
		String accessKeySecret = "KCBTAFEfnIVaQodVp4tROu3L5evuXA";

		// 创建OSSClient实例
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

		// 使用访问OSS
//		String content = "Hello OSS";
//		client.putObject("0717oss", "hello", new ByteArrayInputStream(content.getBytes()));
		// 上传文件流
		InputStream inputStream = new FileInputStream("C:\\gtk\\license.txt");
		client.putObject("0717oss", "jf1.sql", inputStream);
		
		// 关闭client
		client.shutdown();
		ResultBean rlt = new ResultBean();
		
		return rlt;
	}
	
	/**
	 * 
	 * @方法名: deleteUser  
	 * @功能描述: 将对应的SysOp对象的Datastate更新为0
	 * @param sysOp
	 * @return
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年11月28日
	 */
	@ResponseBody
	@RequestMapping("/deleteUser")
	public ResultBean deleteUser(SysOp sysOp) throws Exception {
		
		// 删除用户时，只是将datastate设置为0
		sysOp.setDataState(new Short("0"));
		// 进行更新操作
		Integer i = userService.updateByPrimaryKeySelective(sysOp);
		
		ResultBean rlt = new ResultBean();
		if(i <= 0) {
			// 错误的语句
			rlt.setResult(ResultBean.RESULT_FAILED);
			rlt.setMsg("删除用户时错误！");
		} else {
			// 成功
			rlt.setResult(ResultBean.RESULT_SUCCESS);
			rlt.setMsg("删除用户成功！");
		}
		return rlt;
	}
	
	/**
	 * 
	 * @方法名: addUserPage  
	 * @功能描述: 跳转到新增页面
	 * @param type
	 * @param opId
	 * @param map
	 * @return
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年11月28日
	 */
	@RequestMapping("/addUserPage")
	public String addUserPage(String type, String opId, Map<String, Object> map) throws Exception {
		
		// 如果type=modify，表示是修改操作，需要回显
		if(type.equals("modify")) {
			// 查询出opId对应的SysOp对象，并放入到map中
			SysOp sysOp = userService.selectByPrimaryKey(Long.parseLong(opId));
			map.put("sysOp", sysOp);
		}
		// 并将type，opId的值保存到map中，以在页面中显示，保存和修改提交是需要使用到
		map.put("type", type);
		map.put("opId", opId);
		return "user/addUser";
	}
	
	/**
	 * 
	 * @方法名: addUser  
	 * @功能描述: 新增方法
	 * @return
	 * @throws Exception 
	 * @作者 syl
	 * @日期 2016年11月28日
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public ResultBean addUser(SysOp sysOp) throws Exception {
		
		// 进行新增操作，返回受影响行数
		Integer i = userService.insertSelective(sysOp);
		
		ResultBean rlt = new ResultBean();
		if(i <= 0) {
			// 错误的语句
			rlt.setResult(ResultBean.RESULT_FAILED);
			rlt.setMsg("新增用户时错误！");
		} else {
			// 成功
			rlt.setResult(ResultBean.RESULT_SUCCESS);
			rlt.setMsg("新增用户成功！");
		}
		return rlt;
	}
	
	/**
	 * 
	 * @方法名: modifyUser  
	 * @功能描述: 修改方法
	 * @param sysOp
	 * @return
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年11月28日
	 */
	@RequestMapping("/modifyUser")
	@ResponseBody
	public ResultBean modifyUser(SysOp sysOp) throws Exception {
		
		// 根据页面传过来的值进行更新操作
		Integer i = userService.updateByPrimaryKeySelective(sysOp);
		ResultBean rlt = new ResultBean();
		if(i <= 0) {
			// 错误的语句
			rlt.setResult(ResultBean.RESULT_FAILED);
			rlt.setMsg("修改用户时错误！");
		} else {
			// 成功
			rlt.setResult(ResultBean.RESULT_SUCCESS);
			rlt.setMsg("修改用户成功！");
		}
		return rlt;
	}
	
	@RequestMapping("/selectSysOplist")
	@ResponseBody
	public PageModel<SysOp> selectSysOplist(SysOp sysOp, Integer start, Integer limit) throws Exception {
		
		// 分页查询所有的SysOp
		// 构造参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("limit", limit);
		map.put("opName", sysOp.getOpName());
		map.put("opKind", sysOp.getOpKind());
		
		// 查询用户列表
		List<SysOp> list = userService.selectSysOpList(map);
		
		// 实现前台的中文显示，2016年11月28日 13:55:17
		for (SysOp sysOp2 : list) {
			// 获得opKind属性
			Short opKind = sysOp2.getOpKind();
			if(opKind != null) {
				if (opKind == 1) {
					sysOp2.setOpKindCN("超级管理员");
				} else if(opKind == 2) {
					sysOp2.setOpKindCN("管理员");
				} else {
					sysOp2.setOpKindCN("普通用户");
				}
			}
		}
		
		// 查询用户的总条数
		Integer count = userService.selectSysOpListCount(map);
		
		// 构造分页模型
		PageModel<SysOp> pageModel = new PageModel<>();
		pageModel.setPageNo(start);
		pageModel.setPageSize(limit);
		
		// 列表内容
		pageModel.setRows(list);
		
		// 设置总数
		pageModel.setTotal(count);
		
		return pageModel;
	}
	
	/**
	 * 
	 * @方法名: selectSysOpListByHelper  
	 * @功能描述: 使用插件实现分页 
	 * @param sysOp
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年11月28日
	 */
	@RequestMapping(value="/selectSysOpListByHelper")
	@ResponseBody
	public String selectSysOpListByHelper(SysOp sysOp, Integer page, Integer limit) throws Exception {
		
		// 构造参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("opName", sysOp.getOpName());
		map.put("opKind", sysOp.getOpKind());
		
		/**
		 * 开始进行分页
		 * 第一个参数：页码
		 * 第二个参数：每页显示条数
		 */
		PageHelper.startPage(page, limit);
		List<SysOp> list = userService.selectSysOpListByPageHelper(map);
		
		// 实现前台的中文显示，2016年11月28日 13:55:17
		for (SysOp sysOp2 : list) {
			Short opKind = sysOp2.getOpKind();
			if(opKind != null) {
				if (opKind == 1) {
					sysOp2.setOpKindCN("超级管理员");
				} else if(opKind == 2) {
					sysOp2.setOpKindCN("管理员");
				} else {
					sysOp2.setOpKindCN("普通用户");
				}
			}
		}
		
		// 使用FastJson将对象转成Json字符串 2016年11月29日 09:37:27
		String result = JSON.toJSONString(list);
		return result;
		
		// 使用PageInfo进行包装
//		PageInfo<SysOp> pageInfo = new PageInfo<>(list);
//		return pageInfo;
	}
}
