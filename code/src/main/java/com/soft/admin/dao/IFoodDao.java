package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.Food;

public interface IFoodDao {

	public abstract int addFood(Food food);

	public abstract int delFood(String food_id);

	public abstract int delFoods(String[] food_ids);

	public abstract int updateFood(Food food);

	public abstract Food getFood(Food food);

	public abstract List<Food>  listFoods(Food food);

	public abstract int listFoodsCount(Food food);

}
