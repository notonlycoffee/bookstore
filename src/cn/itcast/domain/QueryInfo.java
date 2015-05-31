package cn.itcast.domain;

public class QueryInfo {

	private int currentPage = 1;
	private int pageSize = 6;
	private int startIndex;
	
	private String queryName;  //category_id
	private String queryValue; //3
	
	private String where;      // "where category_id=?"
	
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartIndex() {
		this.startIndex = (this.currentPage-1) * this.pageSize;
		return startIndex;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}
	public String getWhere() {
		
		if(this.queryName == null || this.queryName.trim().equals("")) {
			return "";
		} else {
			this.where = "where " + this.queryName + "=?";  //"where category_id=?"
		}
		
		return where;
	}
	
}
