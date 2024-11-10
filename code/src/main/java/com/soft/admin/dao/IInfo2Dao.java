package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.Info2;

public interface IInfo2Dao {

	public abstract int addInfo2(Info2 info2);

	public abstract int delInfo2(String info2_id);

	public abstract int delInfo2s(String[] info2_ids);

	public abstract int updateInfo2(Info2 info2);

	public abstract Info2 getInfo2(Info2 info2);

	public abstract List<Info2>  listInfo2s(Info2 info2);

	public abstract int listInfo2sCount(Info2 info2);

}
