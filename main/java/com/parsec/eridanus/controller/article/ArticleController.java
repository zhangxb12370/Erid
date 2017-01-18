package com.parsec.eridanus.controller.article;

import com.parsec.eridanus.dao.ArticleDao;
import com.parsec.eridanus.dao.NewsDao;
import com.parsec.eridanus.po.Article;
import com.parsec.eridanus.po.News;
import com.parsec.tool.*;
import com.parsec.universal.utils.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangxb on 2016/11/12.
 */
@RestController
public class ArticleController {
    @Resource
    private ArticleDao articleDao;

    /**
     * 编辑文章，ID为空添加文章，否则修改文章
     * @param article
     * @return
     */
    @RequestMapping(value = "admin/article/eidt",method = RequestMethod.POST,produces = {Constant.PRODUCE})
    public ReturnJson edit(@RequestBody Article article){
        ReturnJson ret = new ReturnJson();
        if (StringUtil.isEmpty(article.getContent())){
            return ret.setFail("文章内容不能为空");
        }if (StringUtil.isEmpty(article.getTitle())){
            return ret.setFail("文章标题不能为空");
        }
        if (article.getId()==null){
            article.setPublishTime(new Date());
            article.setUpdateTime(new Date());
            articleDao.add(article);
            return ret.setSuccess("文章添加成功");
        }//添加文章
        else {
            article.setUpdateTime(new Date());
            articleDao.edit(article);
            return ret.setSuccess("文章修改成功");
        }//修改文章
    }

    /**
     * 根据文章ID查询
     * @param id 文章ID
     * @return 对应的文章
     */
    @RequestMapping(value = {"admin/article/{id}","public/article/{id}"},method = RequestMethod.GET,produces = {Constant.PRODUCE})
    public ReturnJson getById(@PathVariable Integer id){
        ReturnJson ret = new ReturnJson();
        Article article = articleDao.getById(id);
        if (article==null){
            return ret.setFail("文章闻不存在，或已被删除");
        }
        ret.setResult(article);
        return ret.setSuccess("查询成功");
    }


    @RequestMapping(value = "admin/article/delete/{id}",method = RequestMethod.DELETE,produces = {Constant.PRODUCE})
    public ReturnJson delete(@PathVariable Integer id){
        ReturnJson ret = new ReturnJson();
        Article article = articleDao.getById(id);
        if (article==null){
            return ret.setFail("文章闻不存在，或已被删除");
        }
        articleDao.delete(id);
        return ret.setSuccess("删除成功");
    }


}
