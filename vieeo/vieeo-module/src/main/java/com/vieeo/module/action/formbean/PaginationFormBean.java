package com.vieeo.module.action.formbean;

import com.vieeo.core.domain.Pagination;

/**
 * 页面分页参数bean
 * @author hehy
 *
 */
public class PaginationFormBean {
	
	private String sortname;
	
	private String sortorder;
	
	private int page;
	
	private int rp = Pagination.DEFAULT_PAGE_SIZE;

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

}
