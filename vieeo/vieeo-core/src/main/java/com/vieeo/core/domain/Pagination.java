package com.vieeo.core.domain;

import java.util.List;
/**
 * 分页对象
 * @author roy.he
 *
 * @param <R>
 */
public class Pagination<R extends Entity> {
	
	private static Pagination<Entity> defaultInstance;
	
	private static Pagination<BizEntity> defaultBizInstance;
	
	public static final int DEFAULT_PAGE_SIZE = 15;
	
	static {
		defaultInstance = new Pagination();
		defaultInstance.setPageNum(0);
		defaultInstance.setPageSize(DEFAULT_PAGE_SIZE);
		defaultInstance.setTotalCount(0);
		
		defaultBizInstance = new Pagination();
		defaultBizInstance.setPageNum(0);
		defaultBizInstance.setPageSize(DEFAULT_PAGE_SIZE);
		defaultBizInstance.setTotalCount(0);
	}

	private int pageNum = 1;

	private int pageSize;

	private int pageCount = 1;

	private List<R> data;

	private Integer totalCount;
	
	public Pagination(){};
	
	public Pagination(int pageNum,int pageSize){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public void calc() {
		pageCount = (totalCount + pageSize - 1) / pageSize;

		if (pageNum > pageCount - 1) {
			pageNum = pageCount - 1;
		}

		if (pageNum < 0) {
			pageNum = 0;
		}
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<R> getData() {
		return data;
	}

	public void setData(List<R> data) {
		this.data = data;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	public static Pagination getDefaultInstance(){
		return defaultInstance;
	}
	
	public static Pagination getDefaultBizInstance(){
		return defaultBizInstance;
	}

}
