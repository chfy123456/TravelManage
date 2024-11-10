package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.Line;

public interface ILineDao {

	public abstract int addLine(Line line);

	public abstract int delLine(String line_id);

	public abstract int delLines(String[] line_ids);

	public abstract int updateLine(Line line);

	public abstract Line getLine(Line line);

	public abstract List<Line>  listLines(Line line);

	public abstract int listLinesCount(Line line);

}
