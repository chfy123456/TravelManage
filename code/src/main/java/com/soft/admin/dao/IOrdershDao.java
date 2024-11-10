package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.Ordersh;

public interface IOrdershDao {

	public abstract int addOrdersh(Ordersh ordersh);

	public abstract int delOrdersh(String ordersh_id);

	public abstract int delOrdershs(String[] ordersh_ids);

	public abstract int updateOrdersh(Ordersh ordersh);

	public abstract int updateOrdershGq();

	public abstract Ordersh getOrdersh(Ordersh ordersh);

	public abstract List<Ordersh>  listOrdershs(Ordersh ordersh);

	public abstract int listOrdershsCount(Ordersh ordersh);

}
