package com.soft.page.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft.admin.domain.Collect;
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
import com.soft.admin.domain.SblogReply;
import com.soft.admin.domain.User;
import com.soft.common.util.Md5;
import com.soft.common.util.PaperUtil;
import com.soft.page.manager.IndexManager;
import com.soft.common.util.JSONData;

@Controller
public class IndexAction{

	@Autowired
	IndexManager indexManager;

	String tip;
	
	/**
	 * @Title: index
	 * @Description: 首页
	 * @return String
	 */
	@RequestMapping(value={ " ", "", "/","page_index.action" },method=RequestMethod.GET)
	public String index(ModelMap model){
		//查询最新旅游线路信息
		Line line = new Line();
		line.setStart(0);
		line.setLimit(8);
		List<Line> lines = indexManager.listLines(line,null);
		model.addAttribute("lines", lines);
		
		//查询最新发布帖子信息
		Sblog sblog = new Sblog();
		sblog.setStart(0);
		sblog.setLimit(8);
		List<Sblog> sblogs = indexManager.listSblogs(sblog,null);
		model.addAttribute("sblogs", sblogs);
				
		//查询最新旅游新闻信息
		Info info = new Info();
		info.setStart(0);
		info.setLimit(8);
		List<Info> infos = indexManager.listInfos(info,null);
		model.addAttribute("infos", infos);
		
		//查询最新旅游景点信息
		Pot pot = new Pot();
		pot.setStart(0);
		pot.setLimit(8);
		List<Pot> pots = indexManager.listPots(pot,null);
		model.addAttribute("pots", pots);
		
		//查询最新当地美食信息
		Food food = new Food();
		food.setStart(0);
		food.setLimit(8);
		List<Food> foods = indexManager.listFoods(food,null);
		model.addAttribute("foods", foods);
		
		return "default"; 
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 查询新闻
	 * @return String
	 */
	@RequestMapping(value="page_listInfos.action")
	public String listInfos(Info paramsInfo,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsInfo==null) {
				paramsInfo = new Info();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsInfo);
			//总的条数
			int[] sum={0};
			//查询新闻列表
			List<Info> infos = indexManager.listInfos(paramsInfo,sum); 
			model.addAttribute("infos", infos);
			
			model.addAttribute("paramsInfo", paramsInfo);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "info";
	}
	
	/**
	 * @Title: queryInfo
	 * @Description: 查询新闻
	 * @return String
	 */
	@RequestMapping(value="page_queryInfo.action",method=RequestMethod.GET)
	public String queryInfo(Info paramsInfo,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到普通新闻
			Info info = indexManager.queryInfo(paramsInfo);
			model.addAttribute("info", info);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "infoDetail";
	}
	
	/**
	 * @Title: listInfo2s
	 * @Description: 查询自然人文景点
	 * @return String
	 */
	@RequestMapping(value="page_listInfo2s.action")
	public String listInfo2s(Info2 paramsInfo2,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsInfo2==null) {
				paramsInfo2 = new Info2();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsInfo2);
			//总的条数
			int[] sum={0};
			//查询自然人文景点列表
			List<Info2> info2s = indexManager.listInfo2s(paramsInfo2,sum); 
			model.addAttribute("info2s", info2s);
			model.addAttribute("paramsInfo2", paramsInfo2);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "info2";
	}
	
	/**
	 * @Title: queryInfo2
	 * @Description: 查询自然人文景点
	 * @return String
	 */
	@RequestMapping(value="page_queryInfo2.action",method=RequestMethod.GET)
	public String queryInfo2(Info2 paramsInfo2,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到普通自然人文景点
			Info2 info2 = indexManager.queryInfo2(paramsInfo2);
			model.addAttribute("info2", info2);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "info2Detail";
	}
	
	
	/**
	 * @Title: listLines
	 * @Description: 查询线路攻略
	 * @return String
	 */
	@RequestMapping(value="page_listLines.action")
	public String listLines(Line paramsLine,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsLine==null) {
				paramsLine = new Line();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsLine);
			//总的条数
			int[] sum={0};
			//查询线路攻略列表
			List<Line> lines = indexManager.listLines(paramsLine,sum); 
			model.addAttribute("lines", lines);
			
			model.addAttribute("paramsLine", paramsLine);
			paperUtil.setTotalCount(sum[0]);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "line";
	}
	
	/**
	 * @Title: queryLine
	 * @Description: 查询线路攻略
	 * @return String
	 */
	@RequestMapping(value="page_queryLine.action",method=RequestMethod.GET)
	public String queryLine(Line paramsLine,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到线路攻略
			Line line = indexManager.queryLine(paramsLine);
			model.addAttribute("line", line);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "lineDetail";
	}
	
	/**
	 * @Title: listPots
	 * @Description: 查询旅游景点
	 * @return String
	 */
	@RequestMapping(value="page_listPots.action")
	public String listPots(Pot paramsPot,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsPot==null) {
				paramsPot = new Pot();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsPot);
			//总的条数
			int[] sum={0};
			//查询旅游景点列表
			List<Pot> pots = indexManager.listPots(paramsPot,sum); 
			model.addAttribute("pots", pots);
			
			model.addAttribute("paramsPot", paramsPot);
			paperUtil.setTotalCount(sum[0]);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "pot";
	}
	
	/**
	 * @Title: listPotsHobby
	 * @Description: 查询旅游景点推荐-协同过滤
	 * @return String
	 */
	@RequestMapping(value="page_listPotsHobby.action")
	public String listPotsHobby(Pot paramsPot,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsPot==null) {
				paramsPot = new Pot();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			List<Pot> pots = indexManager.listPotsHobby(httpSession); 
			model.addAttribute("pots", pots);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "potHobby";
	}
	
	/**
	 * @Title: queryPot
	 * @Description: 查询旅游景点
	 * @return String
	 */
	@RequestMapping(value="page_queryPot.action",method=RequestMethod.GET)
	public String queryPot(Pot paramsPot,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到旅游景点
			Pot pot = indexManager.queryPot(paramsPot);
			model.addAttribute("pot", pot);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "potDetail";
	}
	
	/**
	 * @Title: addOrdersShow
	 * @Description: 查询景点信息
	 * @return String
	 */
	@RequestMapping(value="page_addOrdersShow.action",method=RequestMethod.GET)
	public String addOrdersShow(Pot paramsPot,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到景点信息
			Pot pot = indexManager.queryPot(paramsPot);
			model.addAttribute("pot", pot);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersAdd";
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 新增订单
	 * @return String
	 */
	@RequestMapping(value="page_addOrders.action")
	@ResponseBody
	public JSONData addOrders(Orders paramsOrders,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//新增订单
			indexManager.addOrders(paramsOrders);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("预定成功");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: listHotels
	 * @Description: 查询酒店
	 * @return String
	 */
	@RequestMapping(value="page_listHotels.action")
	public String listHotels(Hotel paramsHotel,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsHotel==null) {
				paramsHotel = new Hotel();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsHotel);
			//总的条数
			int[] sum={0};
			//查询酒店列表
			List<Hotel> hotels = indexManager.listHotels(paramsHotel,sum); 
			model.addAttribute("hotels", hotels);
			model.addAttribute("paramsHotel", paramsHotel);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "hotel";
	}
	
	/**
	 * @Title: queryHotel
	 * @Description: 查询酒店
	 * @return String
	 */
	@RequestMapping(value="page_queryHotel.action",method=RequestMethod.GET)
	public String queryHotel(Hotel paramsHotel,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到酒店
			Hotel hotel = indexManager.queryHotel(paramsHotel);
			model.addAttribute("hotel", hotel);
			 
			//查询房间信息
			House house = new House();
			house.setStart(-1);
			house.setHotel_id(hotel.getHotel_id());
			List<House> houses = indexManager.listHouses(house, null);
			if (houses==null) {
				houses = new ArrayList<House>();
			}
			model.addAttribute("houses", houses);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "hotelDetail";
	}
	
	/**
	 * @Title: addOrdershShow
	 * @Description: 酒店预订界面
	 * @return String
	 */
	@RequestMapping(value="page_addOrdershShow.action")
	public String addOrdershShow(House paramsHouse,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到酒店房间信息
			House house = indexManager.queryHouse(paramsHouse);
			model.addAttribute("house", house);
			model.addAttribute("house_count", paramsHouse.getHouse_count());
			 
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordershAdd";
	}
	
	/**
	 * @Title: addOrdersh
	 * @Description: 新增酒店预订
	 * @return String
	 */
	@RequestMapping(value="page_addOrdersh.action")
	@ResponseBody
	public JSONData addOrdersh(Ordersh paramsOrdersh,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//新增酒店预订
			indexManager.addOrdersh(paramsOrdersh);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("预定成功");
			return jsonData;
		}
		
		return jsonData;
	}
	
	
	/**
	 * @Title: listFoods
	 * @Description: 查询当地美食
	 * @return String
	 */
	@RequestMapping(value="page_listFoods.action")
	public String listFoods(Food paramsFood,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsFood==null) {
				paramsFood = new Food();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsFood);
			//总的条数
			int[] sum={0};
			//查询当地美食列表
			List<Food> foods = indexManager.listFoods(paramsFood,sum); 
			model.addAttribute("foods", foods);
			
			model.addAttribute("paramsFood", paramsFood);
			paperUtil.setTotalCount(sum[0]);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "food";
	}
	
	/**
	 * @Title: queryFood
	 * @Description: 查询当地美食
	 * @return String
	 */
	@RequestMapping(value="page_queryFood.action",method=RequestMethod.GET)
	public String queryFood(Food paramsFood,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到当地美食
			Food food = indexManager.queryFood(paramsFood);
			model.addAttribute("food", food);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "foodDetail";
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 帖子信息
	 * @return String
	 */
	@RequestMapping(value="page_listSblogs.action")
	public String listSblogs(Sblog paramsSblog,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询帖子信息集合
			if (paramsSblog==null) {
				paramsSblog = new Sblog();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsSblog);
			int[] sum={0};
			List<Sblog> sblogs = indexManager.listSblogs(paramsSblog,sum); 
			model.addAttribute("sblogs", sblogs);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblog";
	}
	
	/**
	 * @Title: querySblog
	 * @Description: 帖子详情
	 * @return String
	 */
	@RequestMapping(value="page_querySblog.action",method=RequestMethod.GET)
	public String querySblog(Sblog paramsSblog,SblogReply paramsSblogReply,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //帖子详情
			Sblog sblog = indexManager.querySblog(paramsSblog);
			model.addAttribute("sblog", sblog);
			
			//帖子回复信息
			if (paramsSblogReply==null || paramsSblogReply.getSblog_id()==0) {
				indexManager.updateSblogClick(sblog);
				paramsSblogReply = new SblogReply();
				paramsSblogReply.setSblog_id(paramsSblog.getSblog_id());
			}
			paperUtil.setLimit(8);
			paperUtil.setPagination(paramsSblogReply);//分页信息
			int[] sum={0};
			List<SblogReply> sblogReplys = indexManager.listSblogReplys(paramsSblogReply,sum); 
			model.addAttribute("sblogReplys", sblogReplys);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblogDetail";
	}
	
	/**
	 * @Title: addSblogReply
	 * @Description: 新增帖子回复信息
	 * @return String
	 */
	@RequestMapping(value="page_addSblogReply.action")
	@ResponseBody
	public JSONData addSblogReply(SblogReply paramsSblogReply,
				ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//验证码验证
			String random = (String)httpSession.getAttribute("random");
			if (!random.equals(paramsSblogReply.getRandom())) {
				jsonData.setErrorReason("验证码错误");
				return jsonData;
			}
			//新增帖子回复信息
			indexManager.addSblogReply(paramsSblogReply);
			jsonData.setResult("reply_date",paramsSblogReply.getReply_date());
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: addCollect
	 * @Description: 新增收藏
	 * @return String
	 */
	@RequestMapping(value="page_addCollect.action")
	@ResponseBody
	public JSONData addCollect(Collect paramsCollect,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//收藏验证
			Collect collect = indexManager.queryCollect(paramsCollect);
			if (collect==null) {
				//新增收藏
				indexManager.addCollect(paramsCollect);
			}else{
				jsonData.setErrorReason("您已经收藏过了！");
				return jsonData;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("收藏失败");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	@RequestMapping(value="page_saveUserFront.action",method=RequestMethod.POST)
	@ResponseBody
	public JSONData saveUserFront(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = indexManager.queryUser(admin);
			httpSession.setAttribute("userFront", admin);
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: saveUserFrontPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	@RequestMapping(value="page_saveUserFrontPass.action",method=RequestMethod.POST)
	@ResponseBody
	public JSONData saveUserFrontPass(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			User userFront = (User)httpSession.getAttribute("userFront");
			if (!userFront.getUser_pass().equals(Md5.makeMd5(paramsUser.getUser_passOld()))) {
				jsonData.setErrorReason("修改异常，原密码错误");
				return jsonData;
			}
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			if (userFront!=null) {
				userFront.setUser_pass(paramsUser.getUser_pass());
				httpSession.setAttribute("userFront", userFront);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: listMySblogs
	 * @Description: 查询帖子信息
	 * @return String
	 */
	@RequestMapping(value="page_listMySblogs.action")
	public String listMySblogs(Sblog paramsSblog,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsSblog==null) {
				paramsSblog = new Sblog();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			int[] sum={0};
			//用户身份限制
			User userFront = (User)httpSession.getAttribute("userFront");
			paramsSblog.setUser_id(userFront.getUser_id());
			List<Sblog> sblogs = indexManager.listSblogs(paramsSblog,sum); 
			model.addAttribute("sblogs", sblogs);
			model.addAttribute("paramsSblog", paramsSblog);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblogShow";
	}
	
	/**
	 * @Title: addSblogShow
	 * @Description: 新增帖子界面
	 * @return String
	 */
	@RequestMapping(value="page_addSblogShow.action")
	public String addSblogShow(){
		return "sblogEdit";
	}
	
	/**
	 * @Title: addSblog
	 * @Description: 新增帖子
	 * @return String
	 */
	@RequestMapping(value="page_addSblog.action")
	@ResponseBody
	public JSONData addSblog(Sblog paramsSblog,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存帖子
			indexManager.addSblog(paramsSblog);

		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: editSblog
	 * @Description: 编辑帖子信息
	 * @return String
	 */
	@RequestMapping(value="page_editSblog.action")
	public String editSblog(Sblog paramsSblog,
			ModelMap model){
		try {
			//查询帖子
			Sblog sblog = indexManager.querySblog(paramsSblog);
			model.addAttribute("sblog", sblog);
			
		}catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "sblogEdit";
	}
	
	/**
	 * @Title: saveSblog
	 * @Description: 保存帖子信息
	 * @return String
	 */
	@RequestMapping(value="page_saveSblog.action")
	@ResponseBody
	public JSONData saveSblog(Sblog paramsSblog){
		JSONData jsonData = new JSONData();
		try {
			//保存帖子信息
			indexManager.updateSblog(paramsSblog);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: delSblogs
	 * @Description: 删除帖子
	 * @return String
	 */
	@RequestMapping(value="page_delSblogs.action")
	@ResponseBody
	public JSONData delSblogs(Sblog paramsSblog){
		JSONData jsonData = new JSONData();
		try {
			 //删除帖子
			indexManager.delSblogs(paramsSblog);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: listMyCollects
	 * @Description: 查询我的藏品收藏
	 * @return String
	 */
	@RequestMapping(value="page_listMyCollects.action")
	public String listMyCollects(Collect paramsCollect,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsCollect==null) {
				paramsCollect = new Collect();
			}
			//获取用户,用户只能查询自己的藏品收藏
			User userFront = (User)httpSession.getAttribute("userFront");
			paramsCollect.setUser_id(userFront.getUser_id());
			//设置分页信息
			paperUtil.setLimit(7);
			paperUtil.setPagination(paramsCollect);
			//总的条数
			int[] sum={0};
			//查询藏品收藏
			List<Collect> collects = indexManager.listCollects(paramsCollect,sum); 
			model.addAttribute("collects", collects);
			paperUtil.setTotalCount(sum[0]);
			model.addAttribute("paramsCollect", paramsCollect);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "collectShow";
	}
	
	/**
	 * @Title: delCollects
	 * @Description: 删除藏品收藏
	 * @return String
	 */
	@RequestMapping(value="page_delCollects.action")
	@ResponseBody
	public JSONData delCollects(Collect paramsCollect,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //删除藏品收藏
			indexManager.delCollects(paramsCollect);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器繁忙！");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: listMyOrderss
	 * @Description: 查询订单信息
	 * @return String
	 */
	@RequestMapping(value="page_listMyOrderss.action")
	public String listMyOrderss(Orders paramsOrders,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			int[] sum={0};
			//用户身份限制
			User userFront = (User)httpSession.getAttribute("userFront");
			paramsOrders.setUser_id(userFront.getUser_id());
			List<Orders> orderss = indexManager.listOrderss(paramsOrders,sum); 
			model.addAttribute("orderss", orderss);
			model.addAttribute("paramsOrders", paramsOrders);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersShow";
	}
	  
	/**
	 * @Title: updateOrders
	 * @Description: 更新订单信息
	 * @return String
	 */
	@RequestMapping(value="page_updateOrders.action")
	@ResponseBody
	public JSONData updateOrders(Orders paramsOrders){
		JSONData jsonData = new JSONData();
		try {
			//更新订单信息
			indexManager.updateOrders(paramsOrders);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: delOrderss
	 * @Description: 删除订单
	 * @return String
	 */
	@RequestMapping(value="page_delOrderss.action")
	@ResponseBody
	public JSONData delOrderss(Orders paramsOrders){
		JSONData jsonData = new JSONData();
		try {
			 //删除订单
			indexManager.delOrderss(paramsOrders);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: listMyOrdershs
	 * @Description: 查询酒店预订信息
	 * @return String
	 */
	@RequestMapping(value="page_listMyOrdershs.action")
	public String listMyOrdershs(Ordersh paramsOrdersh,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsOrdersh==null) {
				paramsOrdersh = new Ordersh();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			int[] sum={0};
			//用户身份限制
			User userFront = (User)httpSession.getAttribute("userFront");
			paramsOrdersh.setUser_id(userFront.getUser_id());
			List<Ordersh> ordershs = indexManager.listOrdershs(paramsOrdersh,sum); 
			model.addAttribute("ordershs", ordershs);
			model.addAttribute("paramsOrdersh", paramsOrdersh);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordershShow";
	}
	  
	/**
	 * @Title: updateOrdersh
	 * @Description: 更新酒店预订信息
	 * @return String
	 */
	@RequestMapping(value="page_updateOrdersh.action")
	@ResponseBody
	public JSONData updateOrdersh(Ordersh paramsOrdersh){
		JSONData jsonData = new JSONData();
		try {
			//更新酒店预订信息
			indexManager.updateOrdersh(paramsOrdersh);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: delOrdershs
	 * @Description: 删除酒店预订
	 * @return String
	 */
	@RequestMapping(value="page_delOrdershs.action")
	@ResponseBody
	public JSONData delOrdershs(Ordersh paramsOrdersh){
		JSONData jsonData = new JSONData();
		try {
			 //删除酒店预订
			indexManager.delOrdershs(paramsOrdersh);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: reg
	 * @Description: 跳转注册页面
	 * @return String
	 */
	@RequestMapping(value="page_reg.action")
	public String reg(){
		return "reg";
	}
	
	/**
	 * @Title: myInfo
	 * @Description: 跳转个人信息页面
	 * @return String
	 */
	@RequestMapping(value="page_myInfo.action")
	public String myInfo(){
		return "myInfo";
	}
	
	/**
	 * @Title: myPwd
	 * @Description: 跳转个人密码页面
	 * @return String
	 */
	@RequestMapping(value="page_myPwd.action")
	public String myPwd(){
		return "myPwd";
	}
}
