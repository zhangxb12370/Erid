package com.parsec.tool;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

/**
 * User: husu
 * Date: 11-4-20
 * Time: 下午12:33
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseModel implements Serializable{
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -3002409896273236250L;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
	private Integer pageNo;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Integer fromRec;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Integer toRec;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Integer recCount;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Integer pageSize;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Integer totalPages;

    public void init(int pageSize){
        this.pageNo = 1;
        this.fromRec = 0;
        this.pageSize = pageSize;
    }

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String orderBy = null;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String token = null;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }


    public Integer getFromRec() {
        return fromRec;
    }

    public void setFromRec(Integer fromRec) {
        this.fromRec = fromRec;
    }

    public Integer getToRec() {
        return toRec;
    }

    public void setToRec(Integer toRec) {
        this.toRec = toRec;
    }

    public Integer getRecCount() {
        return recCount;
    }

    public void setRecCount(Integer recCount) {
        this.recCount = recCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    public void emptyBaseAttr(){
    	setFromRec(null);
		setPageNo(null);
		setPageSize(null);
		setRecCount(null);
		setToRec(null);
		setTotalPages(null);
    	setOrderBy(null);
		setToken(null);
    }
    
    public static List emptyBaseAttrList(List list){
    	if(list != null){
    		Iterator iter = list.iterator();
    		while(iter.hasNext()){
    			BaseModel bm = (BaseModel)iter.next();
    			bm.emptyBaseAttr();
    		}
    		 
    	}
    	return list;
    }

    /**
     * 设置需要返回的分页列表
     * @param ret
     * @param count
     * @param pageSize
     * @param list
     * @return
     */
    public static JSONObject setPageList(JSONObject ret, Integer count, Integer pageSize, List list){

        int pageCount = count / pageSize ;
        if(count % pageSize != 0)
            pageCount ++ ;
        ret.put("list", list);
        ret.put("count", count);
        ret.put("pageCount", pageCount);

        return ret;
    }
    
    public JSONObject eval(String string){
  
         try {
        	 JSONObject jsonObject = new JSONObject(string);
        	 return jsonObject;
         } catch (JSONException e) {
             e.printStackTrace();
             return null;
         }
        
     }
    
    @Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sbf = new StringBuffer();
		Class<?> cls = getClass();
		Field [] fds = cls.getDeclaredFields();
		if(fds != null){
			for (Field f:fds) {
				f.setAccessible(true);
				try {
					sbf.append(f.getName() + ":"  + f.get(this) + "\n");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sbf.toString();
	}
}
