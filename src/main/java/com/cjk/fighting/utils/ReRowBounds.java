package com.cjk.fighting.utils;

import org.apache.ibatis.session.RowBounds;

public class ReRowBounds extends RowBounds
{
	
	private Page page = new Page();

	public ReRowBounds() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReRowBounds(int offset, int limit) {
		super(offset, limit);
		// TODO Auto-generated constructor stub
	}

	public Page getPage() {
		return page;
	}

}
