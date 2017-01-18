package com.parsec.tool;


import java.util.List;

/**
 * Created by husu on 14-8-19.
 */
public abstract class AbstractCommonController {

    public PageResult getPagedList(BaseModel model){
        PageResult res = new PageResult();

        if(model==null)return null;
        List <? extends BaseModel> lst=null;
        if(model.getPageSize()==null || model.getPageSize()<1){
            model.setPageSize(20);
        }
        Integer counter =  this.getRecCount(model);
        Integer pages = counter/model.getPageSize() ;
        if (counter % model.getPageSize()!=0) pages++;
        if (pages < 1) pages = 1;
        
        if(model.getPageNo()==null || model.getPageNo()<1){
            model.setPageNo(1);
        } else if(model.getPageNo()>pages){
            model.setPageNo(pages);
        }

        Integer fromRec = ((model.getPageNo()-1)*model.getPageSize());
        Integer toRec= model.getPageSize();



        model.setFromRec(fromRec);
        model.setToRec(toRec);
        model.setTotalPages(pages);

        lst = this.selectPagedList(model);

        res.setList(lst);
        Integer nextPage = model.getPageNo() + 1;
        nextPage = nextPage>pages?pages:nextPage;
        Integer prePage = model.getPageNo() - 1 ;
        prePage = prePage<1?1:prePage;

        res.setNextPage(nextPage);
        res.setPageNo(model.getPageNo());
        res.setPreviousPage(prePage);
        res.setPages(pages);

        return res;
    }

    /**
     * 计算纪录总条数
     * @param model
     * @return
     */
    public abstract Integer getRecCount(BaseModel model);

    /**
     * 获得分页的数据
     * @param model
     * @return
     */
    public abstract List<? extends BaseModel> selectPagedList(BaseModel model);
}
