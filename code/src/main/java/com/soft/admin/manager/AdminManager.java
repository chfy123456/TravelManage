package com.soft.admin.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.admin.dao.IFoodDao;
import com.soft.admin.dao.IHotelDao;
import com.soft.admin.dao.IHouseDao;
import com.soft.admin.dao.IInfo2Dao;
import com.soft.admin.dao.IInfoDao;
import com.soft.admin.dao.ILineDao;
import com.soft.admin.dao.IOrdersDao;
import com.soft.admin.dao.IOrdershDao;
import com.soft.admin.dao.IPotDao;
import com.soft.admin.dao.ISblogDao;
import com.soft.admin.dao.IUserDao;
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
import com.soft.common.util.Md5;
import com.soft.common.util.DateUtil;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;

@Service
public class AdminManager {
	
	//所有待注入Dao类
	@Autowired
	IUserDao userDao;
	@Autowired
	ILineDao lineDao;
	@Autowired
	IInfoDao infoDao;
	@Autowired
	IInfo2Dao info2Dao;
	@Autowired
	IPotDao potDao;
	@Autowired
	IFoodDao foodDao;
	@Autowired
	ISblogDao sblogDao;
	@Autowired
	IOrdersDao ordersDao;
	@Autowired
	IHotelDao hotelDao;
	@Autowired
	IHouseDao houseDao;
	@Autowired
	IOrdershDao ordershDao;
	
	/**
	 * @Title: listHotels
	 * @Description: 酒店查询
	 * @param hotel
	 * @return List<Hotel>
	 */
	public List<Hotel> listHotels(Hotel hotel, int[] sum) {
		if (sum != null) {
			sum[0] = hotelDao.listHotelsCount(hotel);
		}
		List<Hotel> hotels = hotelDao.listHotels(hotel);
		return hotels;
	}

	/**
	 * @Title: queryHotel
	 * @Description: 酒店查询
	 * @param hotel
	 * @return Hotel
	 */
	public Hotel queryHotel(Hotel hotel) {
		Hotel _hotel = hotelDao.getHotel(hotel);
		return _hotel;
	}

	/**
	 * @Title: addHotel
	 * @Description: 添加酒店
	 * @param hotel
	 * @return void
	 */
	public void addHotel(Hotel hotel) {
		if (!StringUtil.isEmptyString(hotel.getHotel_content())) {
			hotel.setHotel_content(Transcode.htmlEncode(hotel.getHotel_content()));
		}
		hotel.setHotel_date(DateUtil.getCurDateTime());
		hotelDao.addHotel(hotel);
		
	}

	/**
	 * @Title: updateHotel
	 * @Description: 更新酒店信息
	 * @param hotel
	 * @return void
	 */
	public void updateHotel(Hotel hotel) {
		if (!StringUtil.isEmptyString(hotel.getHotel_content())) {
			hotel.setHotel_content(Transcode.htmlEncode(hotel.getHotel_content()));
		}
		hotelDao.updateHotel(hotel);
	}
	
	/**
	 * @Title: delHotel
	 * @Description: 删除酒店信息
	 * @param hotel
	 * @return void
	 */
	public void delHotels(Hotel hotel) {
		hotelDao.delHotels(hotel.getIds().split(","));
	}
	
	/**
	 * @Title: listHouses
	 * @Description: 房间查询
	 * @param house
	 * @return List<House>
	 */
	public List<House> listHouses(House house, int[] sum) {
		if (sum != null) {
			sum[0] = houseDao.listHousesCount(house);
		}
		List<House> houses = houseDao.listHouses(house);
		return houses;
	}

	/**
	 * @Title: queryHouse
	 * @Description: 房间查询
	 * @param house
	 * @return House
	 */
	public House queryHouse(House house) {
		House _house = houseDao.getHouse(house);
		return _house;
	}

	/**
	 * @Title: addHouse
	 * @Description: 添加房间
	 * @param house
	 * @return void
	 */
	public void addHouse(House house) {
		houseDao.addHouse(house);
		
	}

	/**
	 * @Title: updateHouse
	 * @Description: 更新房间信息
	 * @param house
	 * @return void
	 */
	public void updateHouse(House house) {
		houseDao.updateHouse(house);
	}
	
	/**
	 * @Title: delHouse
	 * @Description: 删除房间信息
	 * @param house
	 * @return void
	 */
	public void delHouses(House house) {
		houseDao.delHouses(house.getIds().split(","));
	}

/**
	 * @Title: listOrdershs
	 * @Description: 用户订单查询
	 * @param ordersh
	 * @return List<Ordersh>
	 */
	public List<Ordersh> listOrdershs(Ordersh ordersh, int[] sum) {
		ordershDao.updateOrdershGq();
		if (sum != null) {
			sum[0] = ordershDao.listOrdershsCount(ordersh);
		}
		List<Ordersh> ordershs = ordershDao.listOrdershs(ordersh);

		return ordershs;
	}

	/**
	 * @Title: queryOrdersh
	 * @Description: 用户订单查询
	 * @param ordersh
	 * @return Ordersh
	 */
	public Ordersh queryOrdersh(Ordersh ordersh) {
		Ordersh _ordersh = ordershDao.getOrdersh(ordersh);
		return _ordersh;
	}

	/**
	 * @Title: addOrdersh
	 * @Description: 添加用户订单
	 * @param ordersh
	 * @return void
	 */
	public void addOrdersh(Ordersh ordersh) {
		String ordersh_no = DateUtil.dateToDateString(new Date(), "yyyyMMddHHmmssSSS");
		ordersh.setOrdersh_no(ordersh_no);
		double ordersh_money = Math.round(ordersh.getHouse_price() * ordersh.getHouse_count() * 10)/10.0;
		ordersh.setOrdersh_money(ordersh_money);
		ordersh.setOrdersh_flag(1);;
		ordersh.setOrdersh_date(DateUtil.getCurDateTime());
		ordershDao.addOrdersh(ordersh);
	}

	/**
	 * @Title: updateOrdersh
	 * @Description: 更新用户订单信息
	 * @param ordersh
	 * @return void
	 */
	public void updateOrdersh(Ordersh ordersh) {
		ordershDao.updateOrdersh(ordersh);
	}

	/**
	 * @Title: delOrdersh
	 * @Description: 删除用户订单信息
	 * @param ordersh
	 * @return void
	 */
	public void delOrdershs(Ordersh ordersh) {
		ordershDao.delOrdershs(ordersh.getIds().split(","));
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 用户订单查询
	 * @param orders
	 * @return List<Orders>
	 */
	public List<Orders> listOrderss(Orders orders, int[] sum) {
		if (sum != null) {
			sum[0] = ordersDao.listOrderssCount(orders);
		}
		List<Orders> orderss = ordersDao.listOrderss(orders);

		return orderss;
	}

	/**
	 * @Title: queryOrders
	 * @Description: 用户订单查询
	 * @param orders
	 * @return Orders
	 */
	public Orders queryOrders(Orders orders) {
		Orders _orders = ordersDao.getOrders(orders);
		return _orders;
	}

	/**
	 * @Title: addOrders
	 * @Description: 添加用户订单
	 * @param orders
	 * @return void
	 */
	public void addOrders(Orders orders) {
		String orders_no = DateUtil.dateToDateString(new Date(), "yyyyMMddHHmmssSSS");
		orders.setOrders_no(orders_no);
		double orders_money = Math.round(orders.getPot_price() * orders.getPot_count() * 10)/10.0;
		orders.setOrders_money(orders_money);
		orders.setOrders_flag(1);;
		orders.setOrders_date(DateUtil.getCurDateTime());
		ordersDao.addOrders(orders);
	}

	/**
	 * @Title: updateOrders
	 * @Description: 更新用户订单信息
	 * @param orders
	 * @return void
	 */
	public void updateOrders(Orders orders) {
		ordersDao.updateOrders(orders);
	}

	/**
	 * @Title: delOrders
	 * @Description: 删除用户订单信息
	 * @param orders
	 * @return void
	 */
	public void delOrderss(Orders orders) {
		ordersDao.delOrderss(orders.getIds().split(","));
	}

	/**
	 * @Title: listUsers
	 * @Description: 查询用户集合
	 * @param user
	 * @return List<User>
	 */
	public List<User> listUsers(User user, int[] sum) {
		if (sum != null) {
			sum[0] = userDao.listUsersCount(user);
		}
		List<User> users = userDao.listUsers(user);
		return users;
	}

	/**
	 * @Title: queryUser
	 * @Description: 用户单个查询
	 * @param user
	 * @return User
	 */
	public User queryUser(User user) {
		User _user = userDao.getUser(user);
		return _user;
	}

	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void addUser(User user) {
		//密码MD5加密
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		//默认头像
		if (StringUtil.isEmptyString(user.getUser_photo())) {
			user.setUser_photo("default.jpg");
		}
		//添加用户
		userDao.addUser(user);
	}

	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void updateUser(User user) {
		//密码MD5加密
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user);
	}

	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void delUsers(User user) {
		userDao.delUsers(user.getIds().split(","));
	}
	
	/**
	 * @Title: listLines
	 * @Description: 线路查询
	 * @param line
	 * @return List<Line>
	 */
	public List<Line> listLines(Line line, int[] sum) {
		if (sum != null) {
			sum[0] = lineDao.listLinesCount(line);
		}
		List<Line> lines = lineDao.listLines(line);
		return lines;
	}

	/**
	 * @Title: queryLine
	 * @Description: 线路查询
	 * @param line
	 * @return Line
	 */
	public Line queryLine(Line line) {
		Line _line = lineDao.getLine(line);
		return _line;
	}

	/**
	 * @Title: addLine
	 * @Description: 添加线路
	 * @param line
	 * @return void
	 */
	public void addLine(Line line) {
		if (!StringUtil.isEmptyString(line.getLine_content())) {
			line.setLine_content(Transcode.htmlEncode(line.getLine_content()));
		}
		line.setLine_date(DateUtil.getCurDateTime());
		lineDao.addLine(line);
		
	}

	/**
	 * @Title: updateLine
	 * @Description: 更新线路信息
	 * @param line
	 * @return void
	 */
	public void updateLine(Line line) {
		if (!StringUtil.isEmptyString(line.getLine_content())) {
			line.setLine_content(Transcode.htmlEncode(line.getLine_content()));
		}
		lineDao.updateLine(line);
	}
	
	/**
	 * @Title: delLine
	 * @Description: 删除线路信息
	 * @param line
	 * @return void
	 */
	public void delLines(Line line) {
		lineDao.delLines(line.getIds().split(","));
	}
	
	/**
	 * @Title: listPots
	 * @Description: 景点查询
	 * @param pot
	 * @return List<Pot>
	 */
	public List<Pot> listPots(Pot pot, int[] sum) {
		if (sum != null) {
			sum[0] = potDao.listPotsCount(pot);
		}
		List<Pot> pots = potDao.listPots(pot);
		return pots;
	}

	/**
	 * @Title: queryPot
	 * @Description: 景点查询
	 * @param pot
	 * @return Pot
	 */
	public Pot queryPot(Pot pot) {
		Pot _pot = potDao.getPot(pot);
		return _pot;
	}

	/**
	 * @Title: addPot
	 * @Description: 添加景点
	 * @param pot
	 * @return void
	 */
	public void addPot(Pot pot) {
		if (!StringUtil.isEmptyString(pot.getPot_content())) {
			pot.setPot_content(Transcode.htmlEncode(pot.getPot_content()));
		}
		pot.setPot_date(DateUtil.getCurDateTime());
		potDao.addPot(pot);
		
	}

	/**
	 * @Title: updatePot
	 * @Description: 更新景点信息
	 * @param pot
	 * @return void
	 */
	public void updatePot(Pot pot) {
		if (!StringUtil.isEmptyString(pot.getPot_content())) {
			pot.setPot_content(Transcode.htmlEncode(pot.getPot_content()));
		}
		potDao.updatePot(pot);
	}
	
	/**
	 * @Title: delPot
	 * @Description: 删除景点信息
	 * @param pot
	 * @return void
	 */
	public void delPots(Pot pot) {
		potDao.delPots(pot.getIds().split(","));
	}
	
	/**
	 * @Title: listFoods
	 * @Description: 当地美食查询
	 * @param food
	 * @return List<Food>
	 */
	public List<Food> listFoods(Food food, int[] sum) {
		if (sum != null) {
			sum[0] = foodDao.listFoodsCount(food);
		}
		List<Food> foods = foodDao.listFoods(food);
		return foods;
	}

	/**
	 * @Title: queryFood
	 * @Description: 当地美食查询
	 * @param food
	 * @return Food
	 */
	public Food queryFood(Food food) {
		Food _food = foodDao.getFood(food);
		return _food;
	}

	/**
	 * @Title: addFood
	 * @Description: 添加当地美食
	 * @param food
	 * @return void
	 */
	public void addFood(Food food) {
		if (!StringUtil.isEmptyString(food.getFood_content())) {
			food.setFood_content(Transcode.htmlEncode(food.getFood_content()));
		}
		food.setFood_date(DateUtil.getCurDateTime());
		foodDao.addFood(food);
		
	}

	/**
	 * @Title: updateFood
	 * @Description: 更新当地美食信息
	 * @param food
	 * @return void
	 */
	public void updateFood(Food food) {
		if (!StringUtil.isEmptyString(food.getFood_content())) {
			food.setFood_content(Transcode.htmlEncode(food.getFood_content()));
		}
		foodDao.updateFood(food);
	}
	
	/**
	 * @Title: delFood
	 * @Description: 删除当地美食信息
	 * @param food
	 * @return void
	 */
	public void delFoods(Food food) {
		foodDao.delFoods(food.getIds().split(","));
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 查询留言信息
	 * @param sblog
	 * @return List<Sblog>
	 */
	public List<Sblog>  listSblogs(Sblog sblog,int[] sum){
		if (sum!=null) {
			sum[0] = sblogDao.listSblogsCount(sblog);
		}
		List<Sblog> sblogs = sblogDao.listSblogs(sblog);
		return sblogs;
	}
	
	/**
	 * @Title: querySblog
	 * @Description: 查询留言
	 * @param sblog
	 * @return Sblog
	 */
	public Sblog querySblog(Sblog sblog) {
		Sblog _sblog = sblogDao.getSblog(sblog);
		return _sblog;
	}
	
	/**
	 * @Title: delSblogs
	 * @Description: 删除留言信息
	 * @param sblog
	 * @return void
	 */
	public void  delSblogs(Sblog sblog){
		sblogDao.delSblogs(sblog.getIds().split(","));
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 查询新闻信息集合
	 * @param info
	 * @return List<Info>
	 */
	public List<Info> listInfos(Info info, int[] sum) {
		if (sum != null) {
			sum[0] = infoDao.listInfosCount(info);
		}
		List<Info> infos = infoDao.listInfos(info);
		return infos;
	}

	/**
	 * @Title: queryInfo
	 * @Description: 新闻信息查询
	 * @param info
	 * @return Info
	 */
	public Info queryInfo(Info info) {
		Info _info = infoDao.getInfo(info);
		return _info;
	}

	/**
	 * @Title: addInfo
	 * @Description: 添加新闻信息
	 * @param info
	 * @return void
	 */
	public void addInfo(Info info) {
		//添加新闻信息
		if (!StringUtil.isEmptyString(info.getInfo_content())) {
			info.setInfo_content(Transcode.htmlEncode(info.getInfo_content()));
		}
		info.setInfo_date(DateUtil.getCurDateTime());
		infoDao.addInfo(info);
	}

	/**
	 * @Title: updateInfo
	 * @Description: 更新新闻信息信息
	 * @param info
	 * @return void
	 */
	public void updateInfo(Info info) {
		//添加新闻信息
		if (!StringUtil.isEmptyString(info.getInfo_content())) {
			info.setInfo_content(Transcode.htmlEncode(info.getInfo_content()));
		}
		infoDao.updateInfo(info);
	}
	
	/**
	 * @Title: delInfos
	 * @Description: 删除新闻信息信息
	 * @param info
	 * @return void
	 */
	public void delInfos(Info info) {
		infoDao.delInfos(info.getIds().split(","));
	}
	
	/**
	 * @Title: listInfo2s
	 * @Description: 查询自然人文景点集合
	 * @param info2
	 * @return List<Info2>
	 */
	public List<Info2> listInfo2s(Info2 info2, int[] sum) {
		if (sum != null) {
			sum[0] = info2Dao.listInfo2sCount(info2);
		}
		List<Info2> info2s = info2Dao.listInfo2s(info2);
		return info2s;
	}

	/**
	 * @Title: queryInfo2
	 * @Description: 自然人文景点查询
	 * @param info2
	 * @return Info2
	 */
	public Info2 queryInfo2(Info2 info2) {
		Info2 _info2 = info2Dao.getInfo2(info2);
		return _info2;
	}

	/**
	 * @Title: addInfo2
	 * @Description: 添加自然人文景点
	 * @param info2
	 * @return void
	 */
	public void addInfo2(Info2 info2) {
		//添加自然人文景点
		if (!StringUtil.isEmptyString(info2.getInfo2_content())) {
			info2.setInfo2_content(Transcode.htmlEncode(info2.getInfo2_content()));
		}
		info2.setInfo2_date(DateUtil.getCurDateTime());
		info2Dao.addInfo2(info2);
	}

	/**
	 * @Title: updateInfo2
	 * @Description: 更新自然人文景点信息
	 * @param info2
	 * @return void
	 */
	public void updateInfo2(Info2 info2) {
		//添加自然人文景点
		if (!StringUtil.isEmptyString(info2.getInfo2_content())) {
			info2.setInfo2_content(Transcode.htmlEncode(info2.getInfo2_content()));
		}
		info2Dao.updateInfo2(info2);
	}
	
	/**
	 * @Title: delInfo2s
	 * @Description: 删除自然人文景点信息
	 * @param info2
	 * @return void
	 */
	public void delInfo2s(Info2 info2) {
		info2Dao.delInfo2s(info2.getIds().split(","));
	}
	 
}
