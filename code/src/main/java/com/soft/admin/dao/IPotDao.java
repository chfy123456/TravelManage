package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.Pot;

public interface IPotDao {

	public abstract int addPot(Pot pot);

	public abstract int delPot(String pot_id);

	public abstract int delPots(String[] pot_ids);

	public abstract int updatePot(Pot pot);

	public abstract Pot getPot(Pot pot);

	public abstract List<Pot>  listPots(Pot pot);

	public abstract int listPotsCount(Pot pot);

}
