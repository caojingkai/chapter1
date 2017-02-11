package com.cjk.fighting.controller;

import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cjk.fighting.model.DEPT;
import com.cjk.fighting.service.DeptService;

import static java.lang.System.out;

@Controller
public class DeptAction {
	
	@Resource
	private DeptService deptService;
	
	@RequestMapping(value="/备用")
	public String home()
	{
		return "showDept";
	}
	
	@ModelAttribute  
    public void populateModel( ModelMap model) { 
		out.println("@ModelAttribute"+"//"+model.size());
       model.addAttribute("attributeName", "abc");  
    }
	
	@ModelAttribute("zhm")  
    public String populateModel2(ModelMap model,HttpServletRequest request) { 
		out.println("@ModelAttribute2"+"//"+model.size()+"__request:"+request.getParameter("a"));
		for(Entry<String, Object> enty :model.entrySet())
		{
			out.println("@ModelAttribute2"+enty.getKey()+"//"+enty.getValue());
		}
        return "love you";
    }
	@ModelAttribute("dept")  
    public DEPT populateModel3(ModelMap model) { 
		out.println("@ModelAttribute3"+"//"+model.size());
		for(Entry<String, Object> enty :model.entrySet())
		{
			out.println("@ModelAttribute3"+enty.getKey()+"//"+enty.getValue());
		}
		DEPT dept = new DEPT();
		dept.setDname("ds");
		dept.setLoc("haha");
        return dept;
    }
	
	//学习从定向和转发
	@RequestMapping(value="/showDept")
	public ModelAndView showUser(String a, String b,HttpServletRequest request,ModelMap model,HttpSession ds)
	{
		DEPT dept2= (DEPT)model.get("dept");
		String str3 = (String)model.get("zhm");
		DEPT dept= (DEPT)request.getAttribute("dept");
		String str = (String)request.getAttribute("zhm");
		String str2 = (String)request.getParameter("zhm");
		out.println(dept2+"??"+str+"??"+str2+"str3"+str3);
		out.println("方法参数a和b:"+a+"::"+b);
		//DEPT dept = deptService.selectByPrimaryKey((short)20);
		String aa= (String)request.getAttribute("am1");
		String bb= (String)request.getAttribute("am2");
		String aa1= (String)request.getParameter("a");
		String bb2= (String)request.getParameter("b");
		String sessionA =(String)request.getSession().getAttribute(a);
		out.println("session:"+sessionA);
		out.println("request.getAttribute(a):"+aa+"//bb"+bb);
		out.println("request.getParameter(a):"+aa1+"//bb"+bb2);
		String modela = (String)model.get("a");
		String modelb = (String)model.get("b");
		out.println("model=="+"a"+modela+"::b"+modelb);
		ModelAndView mav = new ModelAndView("showDept");
		mav.addObject("adminName", a);
		mav.addObject("adminPass", b);
        return mav;
	}
	
	@RequestMapping(value="/showDept2")
	public String showUser2(String a, String b, HttpServletRequest request,Model model2)
	{
		out.println(a+";;"+b);
		request.getSession().setAttribute(a, a+b+a);
		request.setAttribute("a", a+"request");
	    request.setAttribute("b", b+"request"); 
	    /*ModelAndView model =new ModelAndView("forward:showDept");
	    model.addObject("am1", a+"model");
	    model.addObject("am2", b+"model");*/
	    model2.addAttribute("am1", a+"model");
	    model2.addAttribute("am2", b+"model");
	    return "forward:/showDept";
	}
	
	//学习从定向和转发
		@RequestMapping(value="/showDept4")
		public ModelAndView showUser4(HttpServletRequest request,ModelMap model)
		{
			String a = "a";
			String b = "b";
			String aa= (String)request.getAttribute("a1");
			String bb= (String)request.getAttribute("b2");
			String aa1= (String)request.getParameter(a);
			String bb2= (String)request.getParameter(b);
			out.println("request.getAttribute(a):"+aa+"//bb"+bb);
			out.println("request.getParameter(a):"+aa1+"//bb"+bb2);

			String modela = (String)model.get("am1");
			String modelb = (String)model.get("am2");
			out.println("model=="+"a"+modela+"::b"+modelb);
			ModelAndView mav = new ModelAndView("showDept");
			mav.addObject("adminName", a);
			mav.addObject("adminPass", b);
	        return mav;
		}
	


}
