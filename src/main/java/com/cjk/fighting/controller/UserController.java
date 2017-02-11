package com.cjk.fighting.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.format.HashFormat;
import org.apache.shiro.crypto.hash.format.Shiro1CryptFormat;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cjk.fighting.common.ParamDefine;
import com.cjk.fighting.model.Users;
import com.cjk.fighting.service.UsersService;

import static java.lang.System.out;

@SessionAttributes("user")
@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UsersService usersService;
	
	@Resource
	private PasswordService passwordService;
	
	@RequestMapping(value="/")
	public String home()
	{
		return "login/userlogin";	
		
		/*
		 * Subject subject = SecurityUtils.getSubject();
		 * //这样导致：点击浏览器回退时，退不倒登录界面。
		if(!subject.isAuthenticated())
		{
			return "login/userlogin";	
		}
		else
		{
			return "main/usermain";
		}*/
		
	}
	
	
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String userlogin(String loginName, String password, Model model)
	{
		
		Subject subject = SecurityUtils.getSubject();
		//password=passwordService.encryptPassword(password);
		subject.login(new UsernamePasswordToken(loginName,password));
		return "redirect:/main/usermain";
	}
	
	@RequestMapping(value="/main/usermain")
	public String userMain(Model model)
	{
		//获得验证通过的用户
		Subject subject = SecurityUtils.getSubject();
		String userID = (String)subject.getPrincipal();
		Users user = usersService.selectByUserLoginName(userID);
		Session session = subject.getSession();
		session.setAttribute("user", user);
		session.setAttribute("postList", user.getPostList());

		//model.addAttribute("user", user);
		//model.addAttribute("postList", user.getPostList());
		return "main/usermain";
	}

	
	@RequestMapping("/register")
	public String register(@RequestParam(required=true)String type, @RequestParam(required=true)String state, 
			@RequestParam(value="headimgx",required=false) MultipartFile image, Users user ,Model model)
	{
		if (ParamDefine.USER.equals(type) && null != user)
		{
			if("0".equals(state))
			{
				return "register/userregister";
			}
			else
			{
				SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date =new Date();
				try {
					date = sdt.parse(sdt.format(new Date()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				user.setRegistertime(date);
				
				if(!image.isEmpty())
				{
					//将图片存到本地
					File file = new File("/ww/resourceewew/"+image.getOriginalFilename());
					if (!file.exists())
					{
						file.mkdirs();
					}
					try {
						
						image.transferTo(file);
						//将图片二进制 存入数据库中
						byte [] bby = new byte[image.getInputStream().available()];
						image.getInputStream().read(bby);
						user.setImage(bby);
						/*String strr2 = new String(bby);
						System.out.println(strr2);*/
						
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.addAttribute("user", user);
				}
				
				encryptPassword(user);
				usersService.addUser(user);
				
			}
			
		}
			
		return "redirect:/";
	}
	
	
	//加密密码
	public void encryptPassword(Users user)
	{
		String password = user.getPassword();
		String userSalt =  new SecureRandomNumberGenerator().nextBytes().toBase64();
		user.setUserSalt(userSalt);
		String salt = userSalt+user.getLoginName();
		Hash computed = new SimpleHash("SHA-512",ByteSource.Util.bytes(password), ByteSource.Util.bytes(salt), 500000);
		HashFormat hashFormat = new Shiro1CryptFormat();
		out.println("encryptPassword:"+hashFormat.format(computed).toString()+"salt::::"+salt);
		user.setPassword(hashFormat.format(computed).toString());
	}
	
	@RequestMapping("showImage")
	public ModelAndView  showImage(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("Begin::showImage");
		Users user = (Users)request.getSession().getAttribute("user");
		byte [] resByte = null;
		if(null != user && null != user.getImage() && user.getImage().length > 0 )
		{
			resByte = user.getImage();
		}
		else
		{
		    String path12 = request.getSession().getServletContext().getRealPath("/");
			//默认头像
			String fileName =path12+"image/d.jpg";
			File file = new File(fileName);
			try {
				FileInputStream input = new FileInputStream(file);
				byte [] by = new byte[input.available()];
				input.read(by);
				input.close();
				resByte =by;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			ServletOutputStream sos = response.getOutputStream();
			sos.write(resByte);
			sos.flush();
			sos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	  return null;
	}
	
	@RequestMapping("out")
	public ModelAndView outLogin(HttpSession session, SessionStatus sessionStatus)
	{
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		ModelAndView mv =new ModelAndView("login/userlogin");
		/*Object obj =session.getAttribute("user");
		if (null != obj)
		{
			//session.removeAttribute("user");
			//清楚@SessionAttributes 使用以下
			sessionStatus.setComplete();
		}*/
		
		return mv;
	}
	
	@RequestMapping("ttest")
	public void ttest(HttpServletRequest request ,HttpServletResponse response)
	{
		String path12 = request.getSession().getServletContext().getRealPath("/");
		//默认头像
		String fileName =path12+"image/d.jpg";
		File file = new File(fileName);
		try {
			FileInputStream input = new FileInputStream(file);
			byte [] by = new byte[input.available()];
			input.read(by);
			input.close();
		ServletOutputStream sos = response.getOutputStream();
		sos.write(by);
		sos.flush();
		sos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	   public static void getClassSet()
	   {
		   ClassLoader classLoader= Thread.currentThread().getContextClassLoader();
		   
		   try {
			Enumeration<URL> urls = classLoader.getResources("com/cjk/fighting");
	        
			while(urls.hasMoreElements())
			{
				URL  url = urls.nextElement();
				if(url !=null)
				{
					String protocal = url.getProtocol();
					if("file".equals(protocal))
					{
						
					}
					else if ("jar".equals(protocal))
					{
						
					}
					out.println(""+url.getProtocol()+":"+url.getPath());
				}
			}
			
		   } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	   
	   @RequestMapping(value="/login_failure",method=RequestMethod.POST)
		public String userlogin_failure(String loginName, String password, Model model)
		{
			
			out.println("userlogin");
			if(StringUtils.isBlank(loginName) && StringUtils.isBlank(password))
			{
				return "login/userlogin";
			}
			LOGGER.debug("userlogin begin"+loginName);
			Users user = usersService.selectByUserLoginName(loginName);
			if(user ==null)
			{
				LOGGER.debug("userlogin return noUser");
				return "noUser";
			}
			else if (StringUtils.isNotBlank(user.getPassword()))
			{
				if(password.equals(user.getPassword()))
				{	
					
					model.addAttribute("user", user);
					model.addAttribute("postList", user.getPostList());
					
					LOGGER.debug("userlogin return userMain");
					return "main/usermain";
				}
			}
			LOGGER.debug("userlogin return userloginFail");
			return "userloginFail";
		}
	
	 
}
