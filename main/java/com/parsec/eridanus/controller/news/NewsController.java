package com.parsec.eridanus.controller.news;

import com.parsec.eridanus.dao.NewsDao;
import com.parsec.eridanus.po.News;
import com.parsec.tool.*;
import com.parsec.universal.utils.ReturnJsonUtil;
import com.parsec.universal.utils.StringUtil;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangxb on 2016/11/12.
 */
@RestController
public class NewsController extends AbstractCommonController {
    @Resource
    private NewsDao newsDao;

    /**
     * 编辑新闻，ID为空添加新闻，否则修改新闻
     * @param news
     * @return
     */
    @RequestMapping(value = "admin/news/eidt",method = RequestMethod.POST,produces = {Constant.PRODUCE})
    public ReturnJson edit(@RequestBody News news){
        ReturnJson ret = new ReturnJson();
        if (StringUtil.isEmpty(news.getContent())){
            return ret.setFail("新闻内容不能为空");
        }if (StringUtil.isEmpty(news.getTitle())){
            return ret.setFail("新闻标题不能为空");
        }
        if (news.getId()==null){
            news.setUpdateTime(new Date());
            newsDao.add(news);
            return ret.setSuccess("新闻添加成功");
        }//添加新闻
        else {
            news.setUpdateTime(new Date());
            newsDao.edit(news);
            return ret.setSuccess("新闻修改成功");
        }//修改新闻
    }

    /**
     * 根据新闻ID查询
     * @param id 新闻ID
     * @return 对应的新闻
     */
    @RequestMapping(value = {"admin/news/{id}","public/news/{id}"},method = RequestMethod.GET,produces = {Constant.PRODUCE})
    public ReturnJson getById(@PathVariable Integer id){
        ReturnJson ret = new ReturnJson();
        News news = newsDao.getById(id);
        if (news==null){
            return ret.setFail("该新闻不存在，或已被删除");
        }
        ret.setResult(news);
        return ret.setSuccess("查询成功");
    }

    /**
     * 查询所有新闻
     * @param
     * @return
     */
    @RequestMapping(value = {"admin/news/all","public/news/all"},method = RequestMethod.GET,produces = {Constant.PRODUCE})
    public ReturnJson findByPage(Integer pageNo,Integer pageSize){
        ReturnJson ret = new ReturnJson();
        if (pageNo==null){
            return ret.setFail("pageNO is null");
        }
        if (pageSize==null){
            return ret.setFail("pageSize is null");
        }
        PageResult pageResult = getPagedList(new News(){{setPageSize(pageSize);setPageNo(pageNo);}});
        pageResult.getList().stream().sorted();
        if (pageResult.getPages()>0&&pageResult.getList().size()>0){
             ret.setResult(pageResult);
             return ret.setSuccess("查询成功");

        }
        return ret.setFail("尚未添加任何新闻");
    }

    /**
     * 删除新闻
     * @param id
     * @return
     */
    @RequestMapping(value = "admin/news/delete/{id}",method = RequestMethod.DELETE,produces = {Constant.PRODUCE})
    public ReturnJson delete(@PathVariable Integer id){
        ReturnJson ret = new ReturnJson();
        News news = newsDao.getById(id);
        if (news == null){
            return ret.setFail("删除失败，该条新闻无效");
        }
        newsDao.delete(id);
        return ret.setSuccess("删除成功");
    }


    @Override
    public Integer getRecCount(BaseModel model) {
        return newsDao.countAll();
    }

    @Override
    public List<? extends BaseModel> selectPagedList(BaseModel model) {
        News news = (News) model;
        return newsDao.selectPagedList(news);
    }
}
