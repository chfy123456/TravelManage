package com.soft.page.manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.admin.dao.ICollectDao;
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
import com.soft.admin.dao.ISblogReplyDao;
import com.soft.admin.dao.IUserDao;
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
import com.soft.common.util.DateUtil;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;

@Service
public class IndexManager {
	
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
	ISblogReplyDao sblogReplyDao;
	@Autowired
	ICollectDao collectDao;
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
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void updateUser(User user) {
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user);
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
	 * @Title: addSblog
	 * @Description: 新增留言信息
	 * @param sblog
	 * @return void
	 */
	public void  addSblog(Sblog sblog){
		if (!StringUtil.isEmptyString(sblog.getSblog_content())) {
			sblog.setSblog_content(Transcode.htmlEncode(sblog.getSblog_content()));
		}
		sblog.setSblog_date(DateUtil.getCurDateTime());
		sblogDao.addSblog(sblog);
	}
	
	/**
	 * @Title: updateSblog
	 * @Description: 更新帖子信息
	 * @param sblog
	 * @return void
	 */
	public void updateSblog(Sblog _sblog) {
		sblogDao.updateSblog(_sblog);
	}
	public void updateSblogClick(Sblog _sblog) {
		_sblog.setSblog_click(_sblog.getSblog_click()+1);
		sblogDao.updateSblog(_sblog);
	}
	public void updateSblogReply(int sblog_id) {
		Sblog sblog = new Sblog();
		sblog.setSblog_id(sblog_id);
		sblog = sblogDao.getSblog(sblog);
		sblog.setSblog_reply(sblog.getSblog_reply()+1);
		sblogDao.updateSblog(sblog);
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
	 * @Title: listSblogReplys
	 * @Description: 查询回复集合
	 * @param user
	 * @return List<User>
	 */
	public List<SblogReply> listSblogReplys(SblogReply user, int[] sum) {
		if (sum != null) {
			sum[0] = sblogReplyDao.listSblogReplysCount(user);
		}
		List<SblogReply> sblogReplys = sblogReplyDao.listSblogReplys(user);
		return sblogReplys;
	}
	
	/**
	 * @Title: addSblogReply
	 * @Description: 添加回复
	 * @param sblogReply
	 * @return void
	 */
	public void addSblogReply(SblogReply sblogReply) {
		//添加回复
		if (!StringUtil.isEmptyString(sblogReply.getReply_content())) {
			sblogReply.setReply_content(Transcode.htmlEncode(sblogReply.getReply_content()));
		}
		sblogReply.setReply_date(DateUtil.getCurDateTime());
		
		//增加帖子回复数
		updateSblogReply(sblogReply.getSblog_id());
		sblogReplyDao.addSblogReply(sblogReply);
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
	 * @Title: listCollects
	 * @Description: 用户收藏查询
	 * @param collect
	 * @return List<Collect>
	 */
	public List<Collect> listCollects(Collect collect, int[] sum) {
		if (sum != null) {
			sum[0] = collectDao.listCollectsCount(collect);
		}
		List<Collect> collects = collectDao.listCollects(collect);

		return collects;
	}

	/**
	 * @Title: queryCollect
	 * @Description: 用户收藏查询
	 * @param collect
	 * @return Collect
	 */
	public Collect queryCollect(Collect collect) {
		Collect _collect = collectDao.getCollect(collect);
		return _collect;
	}

	/**
	 * @Title: addCollect
	 * @Description: 添加用户收藏
	 * @param collect
	 * @return void
	 */
	public void addCollect(Collect collect) {
		collect.setCollect_date(DateUtil.getCurDateTime());
		collectDao.addCollect(collect);
	}

	/**
	 * @Title: updateCollect
	 * @Description: 更新用户收藏信息
	 * @param collect
	 * @return void
	 */
	public void updateCollect(Collect collect) {
		collectDao.updateCollect(collect);
	}

	/**
	 * @Title: delCollect
	 * @Description: 删除用户收藏信息
	 * @param collect
	 * @return void
	 */
	public void delCollects(Collect collect) {
		collectDao.delCollects(collect.getIds().split(","));
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
	 * @Title: findLikesByUser   
	 * @Description: 查询所有用户的购买记录 
	 * @param: @return      
	 * @return: Map<Integer,List<Uhis>>      
	 * @throws
	 */
	public Map<Integer, List<Orders>> findLikesByUser(){
		Map<Integer, List<Orders>> ulikeMap = new HashMap<Integer, List<Orders>>();
		Orders orders = new Orders();
		orders.setStart(-1);
		List<Orders> ordersList = ordersDao.listOrderss(orders);
		List<String> ordersList2 = new ArrayList<>();
		if (ordersList!=null) {
			for (Orders orders2 : ordersList) {
				String key = orders2.getUser_id()+"_"+orders2.getPot_id();
				List<Orders> ulikeList = ulikeMap.get(orders2.getUser_id());
				if (ulikeList==null) {
					ulikeList = new ArrayList<>();
					ulikeMap.put(orders2.getUser_id(), ulikeList);
				}
				if (!ordersList2.contains(key)) {
					ulikeList.add(orders2);
					ordersList2.add(key);
				}
				
			}
		}
		return ulikeMap;
	}
	
	/**
	 * @Title: listPotsHobby
	 * @Description: 景点推荐查询
	 * @param tpot
	 * @return List<Pot>
	 */
	public List<Pot> listPotsHobby(HttpSession httpSession) {
		List<Pot> recomLists = new ArrayList<Pot>();
		User userFront = (User)httpSession.getAttribute("userFront");//当前用户
		int uid = userFront.getUser_id();//当前用户ID
		
		Map<Integer, List<Orders>> ulikeMap = findLikesByUser();//所有用户购买的景点集合
		List<Orders> likeLists;  //其他用户购买的景点列表
		Pot pot = new Pot();
		pot.setStart(-1);
		pot.setHot_flag(1);
		List<Pot> pots = potDao.listPots(pot);   //所有景点列表
		Map<Integer, Integer> potIdIndexMap = new HashMap<>(); //所有景点ID的索引值{景点ID: 索引值}
		if (pots==null || pots.size()==0) {
			return recomLists;
		}
		int index=0;
		for (Pot pot2 : pots) {
			potIdIndexMap.put(pot2.getPot_id(), index++);
		}
        int[][] comMatrix = new int[pots.size()][pots.size()];   //共现矩阵
        int[] N = new int[pots.size()];                              //购买每个景点的人数
		
        for (Integer curID : ulikeMap.keySet()) {
        	if(curID==uid) continue;//当前用户则跳过
        	likeLists = ulikeMap.get(curID); //当前用户的购买列表
            for(int i = 0; i < likeLists.size(); i++){
				if(!potIdIndexMap.containsKey(likeLists.get(i).getPot_id())){
					continue;
				}
                int pid1 = potIdIndexMap.get(likeLists.get(i).getPot_id());
                ++N[pid1];
                for(int j = i+1; j < likeLists.size(); j++){
					if(!potIdIndexMap.containsKey(likeLists.get(j).getPot_id())){
						continue;
					}
                    int pid2 = potIdIndexMap.get(likeLists.get(j).getPot_id());
                    ++comMatrix[pid1][pid2];
                    ++comMatrix[pid2][pid1]; //两两加一
                }
            }
        	
		}
        
        TreeSet<Pot> preList = new TreeSet<Pot>(new Comparator<Pot>() {
            public int compare(Pot o1, Pot o2) {
                if(o1.getW()!=o2.getW()) {
                	 return (int) (o1.getW()-o2.getW())*100;
                }else if(o1.getBuy_count()!=o2.getBuy_count()) {
                	return o1.getBuy_count() > o2.getBuy_count() ? 1 : -1;
                }else {
					return 0;
				}
            }
        }); //预处理的列表
        
        likeLists = ulikeMap.get(uid);       //当前用户喜欢的景点列表
        Map<Integer, Pot> used = new HashMap<>();//判重数组
        if (likeLists!=null && likeLists.size()>0) {
        	for(Orders like: likeLists){
                int Nij = 0;// 既喜欢i又喜欢j的人数
                double Wij;  //相似度
                Pot tmp;  //当前的景点

			    if(!potIdIndexMap.containsKey(like.getPot_id())){
					continue;
				}
                int i = potIdIndexMap.get(like.getPot_id());
                for(Pot potCur: pots){
                    if(like.getPot_id() == potCur.getPot_id()) continue;
					if(!potIdIndexMap.containsKey(potCur.getPot_id())){
						continue;
					}
                    int j = potIdIndexMap.get(potCur.getPot_id());

                    if(comMatrix[i][j]!=0 && (N[i]*N[j]!=0)) {
                    	Nij = comMatrix[i][j];
                        Wij = (double)Nij/Math.sqrt(N[i]*N[j]); //计算余弦相似度

                        tmp = potDao.getPot(potCur);
                        tmp.setW(Wij);

                        if(used.containsKey(j)) {
                        	tmp.setW(Math.max(Wij, used.get(j).getW()));
                        }
                        used.put(j, tmp);
                    }
                }
            }
		}
        for (Integer key : used.keySet()) {
        	preList.add(used.get(key));
		}

        for(int i = 0; preList.size()>0 && i<5; i++){
            recomLists.add(preList.pollLast());
            preList.pollLast();
        }
        if(recomLists.size()<5){
            //推荐数量不满5个, 补足购买最高的景点
        	for (int i = 0; i < pots.size(); i++) {
        		 int j = potIdIndexMap.get(pots.get(i).getPot_id());
        		 if(!used.containsKey(j)){
        			 recomLists.add(pots.get(i));
        			 if (recomLists.size()>=5) {
						break;
					}
        		 }
			}
        }

        return recomLists;
	}
}
