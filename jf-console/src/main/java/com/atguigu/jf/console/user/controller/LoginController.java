package com.atguigu.jf.console.user.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.jf.console.common.VerifyCodeUtil;
import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.LoginService;

@Controller
@RequestMapping("/logincontroller")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;

	@RequestMapping("/getVerifyCode")
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		/**
		 * 1、禁用缓存
			2、生成随机的字符串，通过工具类中的generateTextCode方法
			3、将生成的字符串放入到Session中
			4、更具随机验证码生成图片流BufferedImage
			5、通过ImageIO写入到response.getOutputStream()
		 */
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		/**
		 * type 验证码类型,参见本类的静态属性
			length 验证码长度,要求大于0的整数
			excludeString 需排除的特殊字符（无需排除则为null）
		 */
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		
		// 放入Session域中
		session.setAttribute("verifyCode", verifyCode);
		
		/**
		 * textCode 文本验证码
			width 图片宽度(注意此宽度若过小,容易造成验证码文本显示不全,如4个字符的文本可使用85到90的宽度)
			height 图片高度
			interLine 图片中干扰线的条数
			randomLocation 每个字符的高低位置是否随机
			backColor 图片颜色,若为null则表示采用随机颜色
			foreColor 字体颜色,若为null则表示采用随机颜色
			lineColor 干扰线颜色,若为null则表示采用随机颜色
		 */
		BufferedImage image = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 4, true, Color.WHITE, Color.BLACK, null);
		
		response.setContentType("image/jpeg");
		
		/**
		 * 1、图片流对象
		 * 2、图片的格式
		 * 3、输出流
		 */
		ImageIO.write(image, "jpeg", response.getOutputStream());
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("verifyCode") String verifyCode,HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		
		logger.debug("前台表单传入的参数:username->" + username +"，password->" + password +"，verifyCode->" + verifyCode);
		// 1、验证码验证
		String s_verifyCode = (String)session.getAttribute("verifyCode");
		// 验证码验证失败
		if (!verifyCode.equals(s_verifyCode)) {
			logger.error("验证码验证失败!");
			redirectAttributes.addFlashAttribute("errMsg","验证码验证失败!");
			return "redirect:/login";
		}
		
		
		////////////////////////进行整合shiro/////////////////////////////
		// get the currently executing user:
        // 核心代码，获取当前的Subject对象，代表这当前的用户信息
        Subject currentUser = SecurityUtils.getSubject();
        
        // let's login the current user so we can check against roles and permissions:
        // 判断当前用户是否被认证，当前的用户是否登录
        if (!currentUser.isAuthenticated()) {
        	
        	// 使用UsernamePasswordToken包装用户名和密码
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
            	// 执行登陆操作，token即为包装后的
            	// 调用JFRealm的doGetAuthenticationInfo方法执行认证操作，以及密码加密
                currentUser.login(token);
            } catch (Exception ae) {
                //unexpected condition?  error?
            	logger.info("用户名或密码错误！");
            }
        }
		
		session.setAttribute("currentUser", currentUser.getPrincipal());
		
		return "redirect:/index";
	}
	
	/*@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("verifyCode") String verifyCode,HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		
		logger.debug("前台表单传入的参数:username->" + username +"，password->" + password +"，verifyCode->" + verifyCode);
		// 1、验证码验证
		String s_verifyCode = (String)session.getAttribute("verifyCode");
		// 验证码验证失败
		if (!verifyCode.equals(s_verifyCode)) {
			logger.error("验证码验证失败!");
			redirectAttributes.addFlashAttribute("errMsg","验证码验证失败!");
			return "redirect:/login";
		}
		
		// 将查询条件包装到对象中，进行登陆验证
		SysOp sysOp = new SysOp();
		sysOp.setLoginCode(username);
		sysOp.setLoginPasswd(password);
		SysOp currentUser = loginService.login(sysOp);
		//用户名和密码失败的情况下
		if(currentUser == null) {
			logger.error("用户名或密码错误!");
			redirectAttributes.addFlashAttribute("errMsg","用户名或密码错误!");
			return "redirect:/login";
		}
		
		session.setAttribute("currentUser", currentUser);
		
		return "redirect:/index";
	}*/
	
	@ResponseBody
	@RequestMapping("/getMenu")
	public List<SysFuncBean> getMenu(Long opId, HttpSession session) throws Exception {
		
		// 从session中获取登陆成功的user对象
		SysOp sysOp = (SysOp) session.getAttribute("currentUser");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("opId",sysOp.getOpId());
		// 获取到菜单列表，类似是业务实体
		List<SysFuncBean> list = loginService.selectSysFuncByOpId(map);
		
		// 最终返回结果
		List<SysFuncBean> newList = new ArrayList<>();
		// 第一次循环
		for (int i = 0; i < list.size(); i++) {
			SysFuncBean parent = list.get(i);
			//判断是否为一级菜单
			if(parent.getFuncLevel().equals(new Short("1"))) {
				// 再次循环
				// 构造子节点的树
				List<SysFuncBean> childList = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					SysFuncBean child = list.get(j);
					// 获取二级菜单
					if (parent.getFuncId().equals(child.getSupFuncId())) {
						childList.add(child);
					}
				}
				parent.setChildren(childList);
				// 加入到新菜单集合中
				newList.add(list.get(i));
			}
		}
		
		return newList;
	}
}
