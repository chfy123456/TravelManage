package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.Hotel;

public interface IHotelDao {

	public abstract int addHotel(Hotel hotel);

	public abstract int delHotel(String hotel_id);

	public abstract int delHotels(String[] hotel_ids);

	public abstract int updateHotel(Hotel hotel);

	public abstract Hotel getHotel(Hotel hotel);

	public abstract List<Hotel>  listHotels(Hotel hotel);

	public abstract int listHotelsCount(Hotel hotel);

}
