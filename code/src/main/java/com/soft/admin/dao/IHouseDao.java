package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.House;

public interface IHouseDao {

	public abstract int addHouse(House house);

	public abstract int delHouse(String house_id);

	public abstract int delHouses(String[] house_ids);

	public abstract int updateHouse(House house);

	public abstract House getHouse(House house);

	public abstract List<House>  listHouses(House house);

	public abstract int listHousesCount(House house);

}
