package com.parsec.tool;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 *
 * @author  Created by husu on 14-8-19.
 */

public class PageResult {
    private List<? extends BaseModel> list =null;
    private Integer pages =null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer recNum =null;

    private Integer nextPage =null;
    private Integer previousPage=null;
    private Integer pageNo =null;

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public List getList() {
        return list;
    }

    public void setList(List<? extends BaseModel> lst) {
        this.list = lst;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getRecNum() {
        return recNum;
    }

    public void setRecNum(Integer recNum) {
        this.recNum = recNum;
    }
}
