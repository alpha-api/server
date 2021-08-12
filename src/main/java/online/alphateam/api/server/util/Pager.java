package online.alphateam.api.server.util;
import java.util.List;
public class Pager<T> {
	public static int PAGESIZE=10;
	private int currentPage;
	private int totalPage;
	private int pageSize;
	private int total;
	private List<T> pageData;
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}	
	
	public Pager() {
		
		
	}
	

	public Pager(Integer currenPage,Integer pageSize,Integer total,List<T> list){		
		if(pageSize == null || pageSize < 1)pageSize=PAGESIZE;		
		if(currenPage == null || currenPage < 1)currenPage=1;
		this.currentPage=currenPage;
		this.pageSize=pageSize;
		this.total=0;
		this.totalPage=0;
		if(list != null && !list.isEmpty()){
			this.total=total;		
			this.totalPage=(total-1) / pageSize + 1;			
			this.pageData=list;	
		}			
	}
	
	

	/**
	 * 
	 * @param currenPage
	 * @param pageSize
	 * @param list
	 * @return
	 * @date 2021-08-07 
	 * @author Michael liangzongc@gmail.com
	 */
	public Pager<T> subPager(Integer currenPage,Integer pageSize,List<T> list){
		if(pageSize == null || pageSize < 1)pageSize=PAGESIZE;		
		if(currenPage == null || currenPage < 1)currenPage=1;
		this.currentPage=currenPage;
		this.pageSize=pageSize;
		this.total=0;
		this.totalPage=0;
		if(list != null && !list.isEmpty()){
			this.total=list.size();			
			this.totalPage=(list.size()-1) / pageSize + 1;
			if(currenPage > totalPage){
				currenPage=totalPage;
				this.currentPage=currenPage;
			}
			Integer startIndex=(currenPage-1)*pageSize;
			Integer endIndex=startIndex+pageSize;
			if(endIndex > list.size())endIndex=list.size();				
			List<T> pageData=(List<T>) list.subList(startIndex, endIndex);		
			this.pageData=pageData;	
		}		
		return this;		
	}
	
	/**
	 * 
	 * @param currenPage
	 * @param pageSize
	 * @param total
	 * @param list
	 * @return
	 * @date 2021-08-07 
	 * @author Michael liangzongc@gmail.com
	 */
	public Pager<T> subPager(Integer currenPage,Integer pageSize,Integer total,List<T> list){
		if(pageSize == null || pageSize < 1)pageSize=PAGESIZE;		
		if(currenPage == null || currenPage < 1)currenPage=1;
		this.currentPage=currenPage;
		this.pageSize=pageSize;
		this.total=0;
		this.totalPage=0;
		if(list != null && !list.isEmpty()){
			this.total=total;			
			this.totalPage=(list.size()-1) / pageSize + 1;
			if(currenPage > totalPage){
				currenPage=totalPage;
				this.currentPage=currenPage;
			}
			Integer startIndex=(currenPage-1)*pageSize;
			Integer endIndex=startIndex+pageSize;
			if(endIndex > list.size())endIndex=list.size();				
			List<T> pageData=(List<T>) list.subList(startIndex, endIndex);		
			this.pageData=pageData;	
		}		
		return this;		
	}
	
	
}
