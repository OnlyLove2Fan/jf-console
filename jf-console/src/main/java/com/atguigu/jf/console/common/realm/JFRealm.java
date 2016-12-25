package com.atguigu.jf.console.common.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.LoginService;

/**
 * 自定义realm需要集成AuthorizingRealm类：
 * 两个实现方法：
 * 1、doGetAuthenticationInfo：认证的回调方法
 * 2、doGetAuthorizationInfo： 授权的回调方法
 */
public class JFRealm extends AuthorizingRealm {

	@Autowired
	private LoginService loginService;
	
	/**
	 * 授权：
	 *   1、强制转换principals类型为SimplePrincipalCollection
	 *   2、通过principals获取用户的实体信息
	 *   3、可以根据数据库或者其他的方式进行授权的操作
	 *   4、构造SimpleAuthorizationInfo，并返回
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		/**
		 * 验证登陆的流程
		 * 1、发送请求到Controller方法
		 * 	参考Quickstart
		 * 2、进行认证验证操作
		 * ① 将传过来的token强转成UsernamePasswordToken类型
		 * ② 根据taken获取用户名，密码
		 * ③ 从数据库查到密码
		 * ④ 进行比对，返回结果
		 * 为防止不同的用户名，有相同的密码时，我们需要让每个用户，即使有相同的密码，加密的密码却不同
		 * 	加盐
		 * 		根据用户名的不同，提供不同的盐值，从而加密为不同的代码
		 */
		// ① 将传过来的token强转成UsernamePasswordToken类型
		UsernamePasswordToken Utoken = (UsernamePasswordToken) token;
		// ② 根据taken获取包含所有信息的principal对象（用户名、密码）
		Object principal = Utoken.getPrincipal();
		
		// 根据用户名的不同获取不同的盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(principal);
		// ③ 从数据库查到密码
		SysOp sysOp = new SysOp();
		sysOp.setLoginCode(principal.toString());
		
		SysOp currentUser = null;
		try {
			currentUser = loginService.login(sysOp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 获得数据库的密码
		String credentials = currentUser.getLoginPasswd();
		// ④ 进行比对，返回结果
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(currentUser, credentials, credentialsSalt, getName());
		return info;
	}

}
