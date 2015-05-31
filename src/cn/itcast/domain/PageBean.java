package cn.itcast.domain;

import java.util.List;

public class PageBean {
	
	private List list;
	private int totalRecord;
	private int pageSize;
	private int totalPage;
	private int currentPage;
	
	private int previousPage;
	private int nextPage;
	
	private int [] pageBar;
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		if(this.totalRecord % this.pageSize == 0) {
			this.totalPage = this.totalRecord / this.pageSize;
		} else {
			this.totalPage = this.totalRecord / this.pageSize + 1;
		}
		return totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPreviousPage() {
		if(this.currentPage-1<1) {
			this.previousPage = 1;
		} else {
			this.previousPage = this.currentPage-1;
		}
		return previousPage;
	}
	public int getNextPage() {
		if(this.currentPage + 1 > this.totalPage) {
			if(this.totalPage == 0) {
				this.nextPage = 1;
			} else {
				this.nextPage = this.totalPage;
			}
		} else {
			this.nextPage = this.currentPage + 1;
		}
		return nextPage;
	}
	public int[] getPageBar() {
		
		int startPage;
		int endPage;
		
		if( this.totalPage <= 10) {
			startPage = 1;
			endPage = this.totalPage;
		} else {
			
			startPage = this.currentPage - 4;
			endPage = this.currentPage + 5;
			
			if(startPage < 1) {
				startPage = 1;
				endPage = 10;
			}
			if(endPage > this.totalPage) {
				endPage = this.totalPage;
				startPage = this.totalPage - 9;
			}
		}
		this.pageBar = new int [endPage - startPage + 1]; // 1 4
		
		int index = 0;
		for( int i = startPage ; i <= endPage ; i++) {
			this.pageBar[index++] = i;
		}
		return pageBar;
	}
	
	
	
	
}
