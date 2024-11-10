package com.soft.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soft.admin.domain.Food;
import com.soft.admin.domain.Hotel;
import com.soft.admin.domain.House;
import com.soft.admin.domain.Info;
import com.soft.admin.domain.Info2;
import com.soft.admin.domain.Line;
import com.soft.admin.domain.Orders;
import com.soft.admin.domain.Ordersh;
import com.soft.admin.domain.Pot;
import com.soft.admin.domain.Sblog;
import com.soft.admin.domain.User;
import com.soft.admin.manager.AdminManager;
import com.soft.common.util.Md5;
import com.soft.common.util.PaperUtil;
import com.soft.common.util.DateUtil;

@Controller
public class AdminAction{

	@Autowired
	AdminManager adminManager;

	String tip;

	/**
	 * @Title: admin
	 * @Description: 首页
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value= {"admin"},method=RequestMethod.GET)
	public void admin(ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws Exception{
		response.sendRedirect(request.getContextPath() + "admin/index.jsp");
	}
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveAdmin.action",method=RequestMethod.POST)
	public String saveAdmin(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//验证用户会话是否失效
			if (!validateAdmin(httpSession)) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.queryUser(admin);
			httpSession.setAttribute("admin", admin);

			setSuccessTip("编辑个人信息成功", "modifyInfo.jsp", model);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("编辑异常", "modifyInfo.jsp", model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveAdminPass.action",method=RequestMethod.POST)
	public String saveAdminPass(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//验证用户会话是否失效
			if (!validateAdmin(httpSession)) {
				return "loginTip";
			}
			User admin = (User)httpSession.getAttribute("admin");
			if (!admin.getUser_pass().equals(Md5.makeMd5(paramsUser.getUser_passOld()))) {
				setErrorTip("修改异常，原密码错误", "modifyPwd.jsp", model);
				return "infoTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				httpSession.setAttribute("admin", admin);
			}

			setSuccessTip("修改成功", "modifyPwd.jsp", model);
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp", model);
		}
		return "infoTip";
	}
	
	
	/**
	 * @Title: listUsers
	 * @Description: 查询注册用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listUsers.action")
	public String listUsers(User paramsUser,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//查看注册用户信息
			paramsUser.setUser_type(1);
			//设置分页信息
			paperUtil.setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询注册用户列表
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			model.addAttribute("users", users);
			model.addAttribute("paramsUser", paramsUser);
			paperUtil.setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("查询注册用户异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加注册用户页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addUserShow.action",method=RequestMethod.GET)
	public String addUserShow(ModelMap model){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加注册用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addUser.action",method=RequestMethod.POST)
	public String addUser(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//检查注册用户是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.queryUser(user);
			if (user!=null) {
				model.addAttribute("tip","失败，该用户名已经存在！");
				model.addAttribute("user", paramsUser);
				
				return "userEdit";
			}
			 //添加注册用户
			paramsUser.setUser_type(1);
			paramsUser.setReg_date(DateUtil.getCurDateTime());
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listUsers.action", model);
		} catch (Exception e) {
			setErrorTip("添加注册用户异常", "Admin_listUsers.action", model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑注册用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editUser.action",method=RequestMethod.GET)
	public String editUser(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到注册用户
			User user = adminManager.queryUser(paramsUser);
			model.addAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询注册用户异常", "Admin_listUsers.action", model);
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑注册用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveUser.action",method=RequestMethod.POST)
	public String saveUser(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑注册用户
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listUsers.action", model);
		} catch (Exception e) {
			setErrorTip("编辑注册用户异常", "Admin_listUsers.action", model);
			return "infoTip";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除注册用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delUsers.action")
	public String delUsers(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除注册用户
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除注册用户成功", "Admin_listUsers.action", model);
		} catch (Exception e) {
			setErrorTip("删除注册用户异常", "Admin_listUsers.action", model);
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listManagers
	 * @Description: 查询系统用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listManagers.action")
	public String listManagers(User paramsUser,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询系统用户列表
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			model.addAttribute("users", users);
			model.addAttribute("paramsUser", paramsUser);
			paperUtil.setTotalCount(sum[0]);
			model.addAttribute("user_type", paramsUser.getUser_type());
			model.addAttribute("user_typeDesc", paramsUser.getUser_typeDesc());

		} catch (Exception e) {
			setErrorTip("查询系统用户异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "managerShow";
	}
	
	/**
	 * @Title: addManagerShow
	 * @Description: 显示添加系统用户页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addManagerShow.action",method=RequestMethod.GET)
	public String addManagerShow(ModelMap model,User paramsUser){
		model.addAttribute("user_type", paramsUser.getUser_type());
		model.addAttribute("user_typeDesc", paramsUser.getUser_typeDesc());
		return "managerEdit";
	}
	
	/**
	 * @Title: addManager
	 * @Description: 添加系统用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addManager.action",method=RequestMethod.POST)
	public String addManager(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//检查系统用户是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.queryUser(user);
			if (user!=null) {
				model.addAttribute("tip","失败，该用户名已经存在！");
				model.addAttribute("user", user);
				model.addAttribute("user_type", paramsUser.getUser_type());
				model.addAttribute("user_typeDesc", paramsUser.getUser_typeDesc());
				
				return "managerEdit";
			}
			 //添加系统用户
			paramsUser.setReg_date(DateUtil.getCurDateTime());
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listManagers.action?user_type="+paramsUser.getUser_type(), model);
		} catch (Exception e) {
			setErrorTip("添加系统用户异常", "Admin_listManagers.action?user_type="+paramsUser.getUser_type(), model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editManager
	 * @Description: 编辑系统用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editManager.action",method=RequestMethod.GET)
	public String editManager(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到系统用户
			User user = adminManager.queryUser(paramsUser);
			model.addAttribute("user", user);
			model.addAttribute("user_type", paramsUser.getUser_type());
			model.addAttribute("user_typeDesc", paramsUser.getUser_typeDesc());
			
		} catch (Exception e) {
			setErrorTip("查询系统用户异常", "Admin_listManagers.action?user_type="+paramsUser.getUser_type(), model);
			return "infoTip";
		}
		
		return "managerEdit";
	}
	
	/**
	 * @Title: saveManager
	 * @Description: 保存编辑系统用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveManager.action",method=RequestMethod.POST)
	public String saveManager(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑系统用户
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listManagers.action?user_type="+paramsUser.getUser_type(), model);
		} catch (Exception e) {
			setErrorTip("编辑系统用户异常", "Admin_listManagers.action?user_type="+paramsUser.getUser_type(), model);
			return "infoTip";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delManagers
	 * @Description: 删除系统用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delManagers.action")
	public String delManagers(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除系统用户
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除系统用户成功", "Admin_listManagers.action?user_type="+paramsUser.getUser_type(), model);
		} catch (Exception e) {
			setErrorTip("删除系统用户异常", "Admin_listManagers.action?user_type="+paramsUser.getUser_type(), model);
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 查询发帖信息
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listSblogs.action")
	public String listSblogs(Sblog paramsSblog,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsSblog==null) {
				paramsSblog = new Sblog();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsSblog);
			int[] sum={0};
			List<Sblog> sblogs = adminManager.listSblogs(paramsSblog,sum); 
			model.addAttribute("sblogs", sblogs);
			model.addAttribute("paramsSblog", paramsSblog);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询发帖信息异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "sblogShow";
	}
	
	/**
	 * @Title: delSblogs
	 * @Description: 删除发帖信息
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delSblogs.action")
	public String delSblogs(Sblog paramsSblog,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除发帖信息
			adminManager.delSblogs(paramsSblog);

			setSuccessTip("删除发帖信息成功", "Admin_listSblogs.action", model);
		} catch (Exception e) {
			setErrorTip("删除发帖信息异常", "Admin_listSblogs.action", model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 查询旅游新闻
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listInfos.action")
	public String listInfos(Info paramsInfo,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsInfo==null) {
				paramsInfo = new Info();
			}
			
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsInfo);
			//总的条数
			int[] sum={0};
			//查询旅游新闻列表
			List<Info> infos = adminManager.listInfos(paramsInfo,sum); 
			
			model.addAttribute("infos", infos);
			model.addAttribute("paramsInfo", paramsInfo);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询旅游新闻异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "infoShow";
	}
	
	/**
	 * @Title: queryInfo
	 * @Description: 查询旅游新闻
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryInfo.action",method=RequestMethod.GET)
	public String queryInfo(Info paramsInfo,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到旅游新闻
			Info info = adminManager.queryInfo(paramsInfo);
			model.addAttribute("info", info);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("查询旅游新闻异常", "Admin_listInfos.action", model);
			return "infoTip";
		}
		
		return "infoDetail";
	}
	
	/**
	 * @Title: addInfoShow
	 * @Description: 显示添加旅游新闻页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addInfoShow.action",method=RequestMethod.GET)
	public String addInfoShow(){
		return "infoEdit";
	}
	
	/**
	 * @Title: addInfo
	 * @Description: 添加旅游新闻
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addInfo.action",method=RequestMethod.POST)
	public String addInfo(Info paramsInfo,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //添加旅游新闻
			adminManager.addInfo(paramsInfo);
			
			setSuccessTip("添加成功", "Admin_listInfos.action",model);
		} catch (Exception e) {
			setErrorTip("添加旅游新闻异常", "Admin_listInfos.action", model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editInfo
	 * @Description: 编辑旅游新闻
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editInfo.action",method=RequestMethod.GET)
	public String editInfo(Info paramsInfo,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到旅游新闻
			Info info = adminManager.queryInfo(paramsInfo);
			model.addAttribute("info", info);
			
		} catch (Exception e) {
			setErrorTip("查询旅游新闻异常", "Admin_listInfos.action", model);
			return "infoTip";
		}
		
		return "infoEdit";
	}
	
	/**
	 * @Title: saveInfo
	 * @Description: 保存编辑旅游新闻
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveInfo.action",method=RequestMethod.POST)
	public String saveInfo(Info paramsInfo,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑旅游新闻
			adminManager.updateInfo(paramsInfo);
			
			setSuccessTip("编辑成功", "Admin_listInfos.action",model);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("编辑失败", "Admin_listInfos.action",model);
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delInfos
	 * @Description: 删除旅游新闻
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delInfos.action")
	public String delInfos(Info paramsInfo,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除旅游新闻
			adminManager.delInfos(paramsInfo);
			
			setSuccessTip("删除旅游新闻成功", "Admin_listInfos.action",model);
		} catch (Exception e) {
			setErrorTip("删除旅游新闻异常", "Admin_listInfos.action", model);
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listLines
	 * @Description: 查询线路攻略
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listLines.action")
	public String listLines(Line paramsLine,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsLine==null) {
				paramsLine = new Line();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsLine);
			int[] sum={0};
			List<Line> lines = adminManager.listLines(paramsLine,sum); 
			model.addAttribute("lines", lines);
			model.addAttribute("paramsLine", paramsLine);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询线路攻略异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "lineShow";
	}
	
	/**
	 * @Title: queryLine
	 * @Description: 查询线路攻略详情
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryLine.action",method=RequestMethod.GET)
	public String queryLine(Line paramsLine,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到线路攻略
			Line line = adminManager.queryLine(paramsLine);
			model.addAttribute("line", line);
			
		} catch (Exception e) {
			setErrorTip("查询线路攻略异常", "Admin_listLines.action", model);
			return "infoTip";
		}
		
		return "lineDetail";
	}
	
	/**
	 * @Title: addLineShow
	 * @Description: 显示添加线路攻略页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addLineShow.action",method=RequestMethod.GET)
	public String addLineShow(ModelMap model){
		return "lineEdit";
	}
	
	/**
	 * @Title: addLine
	 * @Description: 添加线路攻略
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addLine.action",method=RequestMethod.POST)
	public String addLine(Line paramsLine,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //添加线路攻略
			adminManager.addLine(paramsLine);
			
			setSuccessTip("添加成功", "Admin_listLines.action", model);
		} catch (Exception e) {
			setErrorTip("添加线路攻略异常", "Admin_listLines.action", model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editLine
	 * @Description: 编辑线路攻略
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editLine.action",method=RequestMethod.GET)
	public String editLine(Line paramsLine,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到线路攻略
			Line line = adminManager.queryLine(paramsLine);
			model.addAttribute("line", line);
			
		} catch (Exception e) {
			setErrorTip("查询线路攻略异常", "Admin_listLines.action", model);
			return "infoTip";
		}
		
		return "lineEdit";
	}
	
	/**
	 * @Title: saveLine
	 * @Description: 保存编辑线路攻略
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveLine.action",method=RequestMethod.POST)
	public String saveLine(Line paramsLine,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑线路攻略
			adminManager.updateLine(paramsLine);
			
			setSuccessTip("编辑成功", "Admin_listLines.action", model);
		} catch (Exception e) {
			setErrorTip("编辑线路攻略异常", "Admin_listLines.action", model);
			return "infoTip";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delLines
	 * @Description: 删除线路攻略
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delLines.action")
	public String delLines(Line paramsLine,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除线路攻略
			adminManager.delLines(paramsLine);

			setSuccessTip("删除线路攻略成功", "Admin_listLines.action", model);
		} catch (Exception e) {
			setErrorTip("删除线路攻略异常", "Admin_listLines.action", model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listFoods
	 * @Description: 查询当地美食
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listFoods.action")
	public String listFoods(Food paramsFood,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsFood==null) {
				paramsFood = new Food();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsFood);
			int[] sum={0};
			List<Food> foods = adminManager.listFoods(paramsFood,sum); 
			model.addAttribute("foods", foods);
			model.addAttribute("paramsFood", paramsFood);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询当地美食异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "foodShow";
	}
	
	/**
	 * @Title: queryFood
	 * @Description: 查询当地美食详情
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryFood.action",method=RequestMethod.GET)
	public String queryFood(Food paramsFood,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到当地美食
			Food food = adminManager.queryFood(paramsFood);
			model.addAttribute("food", food);
			
		} catch (Exception e) {
			setErrorTip("查询当地美食异常", "Admin_listFoods.action", model);
			return "infoTip";
		}
		
		return "foodDetail";
	}
	
	/**
	 * @Title: addFoodShow
	 * @Description: 显示添加当地美食页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addFoodShow.action",method=RequestMethod.GET)
	public String addFoodShow(ModelMap model){
		return "foodEdit";
	}
	
	/**
	 * @Title: addFood
	 * @Description: 添加当地美食
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addFood.action",method=RequestMethod.POST)
	public String addFood(Food paramsFood,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //添加当地美食
			adminManager.addFood(paramsFood);
			
			setSuccessTip("添加成功", "Admin_listFoods.action", model);
		} catch (Exception e) {
			setErrorTip("添加当地美食异常", "Admin_listFoods.action", model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editFood
	 * @Description: 编辑当地美食
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editFood.action",method=RequestMethod.GET)
	public String editFood(Food paramsFood,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到当地美食
			Food food = adminManager.queryFood(paramsFood);
			model.addAttribute("food", food);
			
		} catch (Exception e) {
			setErrorTip("查询当地美食异常", "Admin_listFoods.action", model);
			return "infoTip";
		}
		
		return "foodEdit";
	}
	
	/**
	 * @Title: saveFood
	 * @Description: 保存编辑当地美食
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveFood.action",method=RequestMethod.POST)
	public String saveFood(Food paramsFood,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑当地美食
			adminManager.updateFood(paramsFood);
			
			setSuccessTip("编辑成功", "Admin_listFoods.action", model);
		} catch (Exception e) {
			setErrorTip("编辑当地美食异常", "Admin_listFoods.action", model);
			return "infoTip";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delFoods
	 * @Description: 删除当地美食
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delFoods.action")
	public String delFoods(Food paramsFood,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除当地美食
			adminManager.delFoods(paramsFood);

			setSuccessTip("删除当地美食成功", "Admin_listFoods.action", model);
		} catch (Exception e) {
			setErrorTip("删除当地美食异常", "Admin_listFoods.action", model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listPots
	 * @Description: 查询景点门票
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listPots.action")
	public String listPots(Pot paramsPot,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsPot==null) {
				paramsPot = new Pot();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsPot);
			int[] sum={0};
			List<Pot> pots = adminManager.listPots(paramsPot,sum); 
			model.addAttribute("pots", pots);
			model.addAttribute("paramsPot", paramsPot);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询景点门票异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "potShow";
	}
	
	/**
	 * @Title: queryPot
	 * @Description: 查询景点门票详情
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryPot.action",method=RequestMethod.GET)
	public String queryPot(Pot paramsPot,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到景点门票
			Pot pot = adminManager.queryPot(paramsPot);
			model.addAttribute("pot", pot);
			
		} catch (Exception e) {
			setErrorTip("查询景点门票异常", "Admin_listPots.action", model);
			return "infoTip";
		}
		
		return "potDetail";
	}
	
	/**
	 * @Title: addPotShow
	 * @Description: 显示添加景点门票页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addPotShow.action",method=RequestMethod.GET)
	public String addPotShow(ModelMap model){
		return "potEdit";
	}
	
	/**
	 * @Title: addPot
	 * @Description: 添加景点门票
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addPot.action",method=RequestMethod.POST)
	public String addPot(Pot paramsPot,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //添加景点门票
			adminManager.addPot(paramsPot);
			
			setSuccessTip("添加成功", "Admin_listPots.action", model);
		} catch (Exception e) {
			setErrorTip("添加景点门票异常", "Admin_listPots.action", model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editPot
	 * @Description: 编辑景点门票
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editPot.action",method=RequestMethod.GET)
	public String editPot(Pot paramsPot,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到景点门票
			Pot pot = adminManager.queryPot(paramsPot);
			model.addAttribute("pot", pot);
			
		} catch (Exception e) {
			setErrorTip("查询景点门票异常", "Admin_listPots.action", model);
			return "infoTip";
		}
		
		return "potEdit";
	}
	
	/**
	 * @Title: savePot
	 * @Description: 保存编辑景点门票
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_savePot.action",method=RequestMethod.POST)
	public String savePot(Pot paramsPot,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑景点门票
			adminManager.updatePot(paramsPot);
			
			setSuccessTip("编辑成功", "Admin_listPots.action", model);
		} catch (Exception e) {
			setErrorTip("编辑景点门票异常", "Admin_listPots.action", model);
			return "infoTip";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delPots
	 * @Description: 删除景点门票
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delPots.action")
	public String delPots(Pot paramsPot,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除景点门票
			adminManager.delPots(paramsPot);

			setSuccessTip("删除景点门票成功", "Admin_listPots.action", model);
		} catch (Exception e) {
			setErrorTip("删除景点门票异常", "Admin_listPots.action", model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listInfo2s
	 * @Description: 查询自然人文景点
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listInfo2s.action")
	public String listInfo2s(Info2 paramsInfo2,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsInfo2==null) {
				paramsInfo2 = new Info2();
			}
			
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsInfo2);
			//总的条数
			int[] sum={0};
			//查询自然人文景点列表
			List<Info2> info2s = adminManager.listInfo2s(paramsInfo2,sum); 
			
			model.addAttribute("info2s", info2s);
			model.addAttribute("paramsInfo2", paramsInfo2);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询自然人文景点异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "info2Show";
	}
	
	/**
	 * @Title: queryInfo2
	 * @Description: 查询自然人文景点
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryInfo2.action",method=RequestMethod.GET)
	public String queryInfo2(Info2 paramsInfo2,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到自然人文景点
			Info2 info2 = adminManager.queryInfo2(paramsInfo2);
			model.addAttribute("info2", info2);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("查询自然人文景点异常", "Admin_listInfo2s.action", model);
			return "infoTip";
		}
		
		return "info2Detail";
	}
	
	/**
	 * @Title: addInfo2Show
	 * @Description: 显示添加自然人文景点页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addInfo2Show.action",method=RequestMethod.GET)
	public String addInfo2Show(){
		return "info2Edit";
	}
	
	/**
	 * @Title: addInfo2
	 * @Description: 添加自然人文景点
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addInfo2.action",method=RequestMethod.POST)
	public String addInfo2(Info2 paramsInfo2,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //添加自然人文景点
			adminManager.addInfo2(paramsInfo2);
			
			setSuccessTip("添加成功", "Admin_listInfo2s.action",model);
		} catch (Exception e) {
			setErrorTip("添加自然人文景点异常", "Admin_listInfo2s.action", model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editInfo2
	 * @Description: 编辑自然人文景点
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editInfo2.action",method=RequestMethod.GET)
	public String editInfo2(Info2 paramsInfo2,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到自然人文景点
			Info2 info2 = adminManager.queryInfo2(paramsInfo2);
			model.addAttribute("info2", info2);
			
		} catch (Exception e) {
			setErrorTip("查询自然人文景点异常", "Admin_listInfo2s.action", model);
			return "infoTip";
		}
		
		return "info2Edit";
	}
	
	/**
	 * @Title: saveInfo2
	 * @Description: 保存编辑自然人文景点
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveInfo2.action",method=RequestMethod.POST)
	public String saveInfo2(Info2 paramsInfo2,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑自然人文景点
			adminManager.updateInfo2(paramsInfo2);
			
			setSuccessTip("编辑成功", "Admin_listInfo2s.action",model);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("编辑失败", "Admin_listInfo2s.action",model);
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delInfo2s
	 * @Description: 删除自然人文景点
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delInfo2s.action")
	public String delInfo2s(Info2 paramsInfo2,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除自然人文景点
			adminManager.delInfo2s(paramsInfo2);
			
			setSuccessTip("删除自然人文景点成功", "Admin_listInfo2s.action",model);
		} catch (Exception e) {
			setErrorTip("删除自然人文景点异常", "Admin_listInfo2s.action", model);
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 查询用户订单
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listOrderss.action")
	public String listOrderss(Orders paramsOrders,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsOrders);
			int[] sum={0};
			List<Orders> orderss = adminManager.listOrderss(paramsOrders,sum); 
			model.addAttribute("orderss", orderss);
			model.addAttribute("paramsOrders", paramsOrders);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询用户订单异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "ordersShow";
	}
	
	/**
	 * @Title: queryOrders
	 * @Description: 查询用户订单详情
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryOrders.action",method=RequestMethod.GET)
	public String queryOrders(Orders paramsOrders,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到用户订单
			Orders orders = adminManager.queryOrders(paramsOrders);
			model.addAttribute("orders", orders);
			
		} catch (Exception e) {
			setErrorTip("查询用户订单异常", "Admin_listOrderss.action", model);
			return "infoTip";
		}
		
		return "ordersDetail";
	}
	
	/**
	 * @Title: finishOrders
	 * @Description: 确认门票已使用
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_finishOrders.action")
	public String finishOrders(Orders paramsOrders,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //确认门票已使用
			paramsOrders.setOrders_flag(2);
			adminManager.updateOrders(paramsOrders);

			setSuccessTip("更新用户订单成功", "Admin_listOrderss.action", model);
		} catch (Exception e) {
			setErrorTip("更新用户订单异常", "Admin_listOrderss.action", model);
		}
		return "infoTip";
	}
		
	/**
	 * @Title: delOrderss
	 * @Description: 删除用户订单
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delOrderss.action")
	public String delOrderss(Orders paramsOrders,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除用户订单
			adminManager.delOrderss(paramsOrders);

			setSuccessTip("删除用户订单成功", "Admin_listOrderss.action", model);
		} catch (Exception e) {
			setErrorTip("删除用户订单异常", "Admin_listOrderss.action", model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listHotels
	 * @Description: 查询酒店
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listHotels.action")
	public String listHotels(Hotel paramsHotel,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsHotel==null) {
				paramsHotel = new Hotel();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsHotel);
			int[] sum={0};
			List<Hotel> hotels = adminManager.listHotels(paramsHotel,sum); 
			model.addAttribute("hotels", hotels);
			model.addAttribute("paramsHotel", paramsHotel);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询酒店异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "hotelShow";
	}
	
	/**
	 * @Title: queryHotel
	 * @Description: 查询酒店详情
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryHotel.action",method=RequestMethod.GET)
	public String queryHotel(Hotel paramsHotel,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到酒店
			Hotel hotel = adminManager.queryHotel(paramsHotel);
			model.addAttribute("hotel", hotel);
			
		} catch (Exception e) {
			setErrorTip("查询酒店异常", "Admin_listHotels.action", model);
			return "infoTip";
		}
		
		return "hotelDetail";
	}
	
	/**
	 * @Title: addHotelShow
	 * @Description: 显示添加酒店页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addHotelShow.action",method=RequestMethod.GET)
	public String addHotelShow(ModelMap model){
		return "hotelEdit";
	}
	
	/**
	 * @Title: addHotel
	 * @Description: 添加酒店
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addHotel.action",method=RequestMethod.POST)
	public String addHotel(Hotel paramsHotel,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //添加酒店
			adminManager.addHotel(paramsHotel);
			
			setSuccessTip("添加成功", "Admin_listHotels.action", model);
		} catch (Exception e) {
			setErrorTip("添加酒店异常", "Admin_listHotels.action", model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editHotel
	 * @Description: 编辑酒店
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editHotel.action",method=RequestMethod.GET)
	public String editHotel(Hotel paramsHotel,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到酒店
			Hotel hotel = adminManager.queryHotel(paramsHotel);
			model.addAttribute("hotel", hotel);
			
		} catch (Exception e) {
			setErrorTip("查询酒店异常", "Admin_listHotels.action", model);
			return "infoTip";
		}
		
		return "hotelEdit";
	}
	
	/**
	 * @Title: saveHotel
	 * @Description: 保存编辑酒店
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveHotel.action",method=RequestMethod.POST)
	public String saveHotel(Hotel paramsHotel,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑酒店
			adminManager.updateHotel(paramsHotel);
			
			setSuccessTip("编辑成功", "Admin_listHotels.action", model);
		} catch (Exception e) {
			setErrorTip("编辑酒店异常", "Admin_listHotels.action", model);
			return "infoTip";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delHotels
	 * @Description: 删除酒店
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delHotels.action")
	public String delHotels(Hotel paramsHotel,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除酒店
			adminManager.delHotels(paramsHotel);

			setSuccessTip("删除酒店成功", "Admin_listHotels.action", model);
		} catch (Exception e) {
			setErrorTip("删除酒店异常", "Admin_listHotels.action", model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listHouses
	 * @Description: 查询酒店房间
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listHouses.action")
	public String listHouses(House paramsHouse,Hotel paramsHotel,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsHouse==null) {
				paramsHouse = new House();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsHouse);
			int[] sum={0};
			List<House> houses = adminManager.listHouses(paramsHouse,sum); 
			model.addAttribute("houses", houses);
			model.addAttribute("paramsHouse", paramsHouse);
			paperUtil.setTotalCount(sum[0]);
			
			//得到酒店
			Hotel hotel = adminManager.queryHotel(paramsHotel);
			model.addAttribute("hotel", hotel);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("查询酒店房间异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "houseShow";
	}
	
	/**
	 * @Title: queryHouse
	 * @Description: 查询酒店房间详情
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryHouse.action",method=RequestMethod.GET)
	public String queryHouse(House paramsHouse,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到酒店房间
			House house = adminManager.queryHouse(paramsHouse);
			model.addAttribute("house", house);
			
		} catch (Exception e) {
			setErrorTip("查询酒店房间异常", "Admin_listHouses.action", model);
			return "infoTip";
		}
		
		return "houseDetail";
	}
	
	/**
	 * @Title: addHouseShow
	 * @Description: 显示添加酒店房间页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addHouseShow.action",method=RequestMethod.GET)
	public String addHouseShow(ModelMap model,Hotel paramsHotel){
		//得到酒店
		Hotel hotel = adminManager.queryHotel(paramsHotel);
		model.addAttribute("hotel", hotel);
		return "houseEdit";
	}
	
	/**
	 * @Title: addHouse
	 * @Description: 添加酒店房间
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addHouse.action",method=RequestMethod.POST)
	public String addHouse(House paramsHouse,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //添加酒店房间
			adminManager.addHouse(paramsHouse);
			
			setSuccessTip("添加成功", "Admin_listHouses.action?hotel_id="+paramsHouse.getHotel_id(), model);
		} catch (Exception e) {
			setErrorTip("添加酒店房间异常", "Admin_listHouses.action?hotel_id="+paramsHouse.getHotel_id(), model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editHouse
	 * @Description: 编辑酒店房间
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editHouse.action",method=RequestMethod.GET)
	public String editHouse(House paramsHouse,Hotel paramsHotel,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到酒店房间
			House house = adminManager.queryHouse(paramsHouse);
			model.addAttribute("house", house);

			//得到酒店
			Hotel hotel = adminManager.queryHotel(paramsHotel);
			model.addAttribute("hotel", hotel);
		} catch (Exception e) {
			setErrorTip("查询酒店房间异常", "Admin_listHouses.action?hotel_id="+paramsHouse.getHotel_id(), model);
			return "infoTip";
		}
		
		return "houseEdit";
	}
	
	/**
	 * @Title: saveHouse
	 * @Description: 保存编辑酒店房间
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveHouse.action",method=RequestMethod.POST)
	public String saveHouse(House paramsHouse,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑酒店房间
			adminManager.updateHouse(paramsHouse);
			
			setSuccessTip("编辑成功", "Admin_listHouses.action?hotel_id="+paramsHouse.getHotel_id(), model);
		} catch (Exception e) {
			setErrorTip("编辑酒店房间异常", "Admin_listHouses.action?hotel_id="+paramsHouse.getHotel_id(), model);
			return "infoTip";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delHouses
	 * @Description: 删除酒店房间
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delHouses.action")
	public String delHouses(House paramsHouse,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除酒店房间
			adminManager.delHouses(paramsHouse);

			setSuccessTip("删除酒店房间成功", "Admin_listHouses.action?hotel_id="+paramsHouse.getHotel_id(), model);
		} catch (Exception e) {
			setErrorTip("删除酒店房间异常", "Admin_listHouses.action?hotel_id="+paramsHouse.getHotel_id(), model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listOrdershs
	 * @Description: 查询用户酒店预订
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listOrdershs.action")
	public String listOrdershs(Ordersh paramsOrdersh,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsOrdersh==null) {
				paramsOrdersh = new Ordersh();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			paperUtil.setPagination(paramsOrdersh);
			int[] sum={0};
			List<Ordersh> ordershs = adminManager.listOrdershs(paramsOrdersh,sum); 
			model.addAttribute("ordershs", ordershs);
			model.addAttribute("paramsOrdersh", paramsOrdersh);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询用户酒店预订异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "ordershShow";
	}
	
	/**
	 * @Title: queryOrdersh
	 * @Description: 查询用户酒店预订详情
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryOrdersh.action",method=RequestMethod.GET)
	public String queryOrdersh(Ordersh paramsOrdersh,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到用户酒店预订
			Ordersh ordersh = adminManager.queryOrdersh(paramsOrdersh);
			model.addAttribute("ordersh", ordersh);
			
		} catch (Exception e) {
			setErrorTip("查询用户酒店预订异常", "Admin_listOrdershs.action", model);
			return "infoTip";
		}
		
		return "ordershDetail";
	}
	
	/**
	 * @Title: finishOrdersh
	 * @Description: 确认已入住
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_finishOrdersh.action")
	public String finishOrdersh(Ordersh paramsOrdersh,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //确认已入住
			paramsOrdersh.setOrdersh_flag(2);
			adminManager.updateOrdersh(paramsOrdersh);

			setSuccessTip("更新用户酒店预订成功", "Admin_listOrdershs.action", model);
		} catch (Exception e) {
			setErrorTip("更新用户酒店预订异常", "Admin_listOrdershs.action", model);
		}
		return "infoTip";
	}
		
	/**
	 * @Title: delOrdershs
	 * @Description: 删除用户酒店预订
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delOrdershs.action")
	public String delOrdershs(Ordersh paramsOrdersh,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除用户酒店预订
			adminManager.delOrdershs(paramsOrdersh);

			setSuccessTip("删除用户酒店预订成功", "Admin_listOrdershs.action", model);
		} catch (Exception e) {
			setErrorTip("删除用户酒店预订异常", "Admin_listOrdershs.action", model);
		}
		return "infoTip";
	}
	
	
	/**
	 * @Title: validateAdmin
	 * @Description: admin登录验证
	 * @return boolean
	 */
	private boolean validateAdmin(HttpSession httpSession){
		User admin = (User)httpSession.getAttribute("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url,ModelMap model){
		model.addAttribute("tipType", "error");
		model.addAttribute("tip", tip);
		model.addAttribute("url1", url);
		model.addAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url,ModelMap model){
		model.addAttribute("tipType", "success");
		model.addAttribute("tip", tip);
		model.addAttribute("url1", url);
		model.addAttribute("value1", "确 定");
	}

}
