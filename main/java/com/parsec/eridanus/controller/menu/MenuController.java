package com.parsec.eridanus.controller.menu;

import com.parsec.eridanus.dao.SsMenuDao;
import com.parsec.eridanus.po.Article;
import com.parsec.eridanus.po.SsMenu;
import com.parsec.tool.ReturnJson;
import com.parsec.universal.utils.Constants;
import com.parsec.universal.utils.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by kevin on 2016/11/28.
 */
@RestController
public class MenuController {
    @Resource
    private SsMenuDao ssMenuDao;

    /**
     * 后台频道列表
     * @return
     */
    @RequestMapping(value="admin/ssMenu",method={RequestMethod.GET}, produces = {Constants.JSON_ENCODING})
    public ReturnJson<SsMenu> findAllByAdmin() {
        ReturnJson<SsMenu> returnJson=new ReturnJson<>();
        List<SsMenu> all = ssMenuDao.findAll();
        all.sort(new SsMenu());
        returnJson.setList(all);
        return returnJson.setSuccess("查询成功");
//        Map<Integer,List<SsMenu>> ssMap = all.stream().collect(Collectors.groupingBy(ssmenu->ssmenu.getParentId()));
//        Map<Integer,List<SsMenu>> allMap = all.stream().collect(Collectors.groupingBy(ssmenu->ssmenu.getId()));
//        List<Integer> parentList = ssMap.keySet().stream().collect(Collectors.toList());
//        Set<SsMenu> result = new HashSet<>();
//        for(Integer parentId:parentList){
//            if(!parentId.equals(0)){
//                List<SsMenu> ssMenus = ssMap.get(parentId);
//                SsMenu ssMenu =  allMap.get(ssMenus.get(0).getParentId()).get(0);
//                ssMenu.setSsMenus(ssMenus);
//                result.add(ssMenu);
//            }else {
//                List<SsMenu> firstMenu = ssMap.get(parentId);
//                for(SsMenu ssMenu:firstMenu){
//                    result.add(ssMenu);
//                }
//            }
//
//        }

    }
    /**
     * 前端频道列表
     * @return
     */
    @RequestMapping(value="public/user/ssMenu",method={RequestMethod.GET}, produces = {Constants.JSON_ENCODING})
    public ReturnJson<SsMenu> findAll() {
        ReturnJson<SsMenu> returnJson=new ReturnJson<>();
        List<SsMenu> all = ssMenuDao.findAll();
        all.sort(new SsMenu());
        List<SsMenu> ssMenuList = all.stream().filter(ssMenu -> ssMenu.getUrl().startsWith("article")).collect(Collectors.toList());
        if (ssMenuList.size()>0){
            returnJson.setList(ssMenuList);
            return  returnJson.setSuccess("查询成功");
        }
        return  returnJson.setSuccess("尚未添加频道");
    }

    @RequestMapping(value="admin/channel/edit",method={RequestMethod.POST}, produces = {Constants.JSON_ENCODING})
    public ReturnJson edit(@RequestBody SsMenu ssMenu){
        ReturnJson<SsMenu> returnJson=new ReturnJson<>();
        if (StringUtil.isEmpty(ssMenu.getUrlName())){
            return returnJson.setFail("频道名称不能为空");
        }
        if (ssMenu.getId()==null){
            SsMenu existSsMenu = ssMenuDao.getByUrlName(ssMenu.getUrlName());
            if (existSsMenu!=null){
                return returnJson.setFail("该频道已存在");
            }
            ssMenuDao.add(ssMenu);
            String url = "article.html?id="+ssMenu.getId();
            ssMenu.setUrl(url);
            ssMenuDao.edit(ssMenu);
            return returnJson.setSuccess("频道添加成功");
        }
        else {
            ssMenuDao.edit(ssMenu);
            return returnJson.setSuccess("频道修改成功");
        }
    }

    @RequestMapping(value= {"admin/channel/getArticle/{id}","public/channel/getArticle/{id}"},method={RequestMethod.GET}, produces = {Constants.JSON_ENCODING})
    public ReturnJson getArticleById(@PathVariable Integer id){
        ReturnJson returnJson = new ReturnJson();
        List<Article> articles = ssMenuDao.getArticleById(id);
        if (articles.size()>0){
            returnJson.setList(articles);
            return returnJson.setSuccess("查询成功");
        }
        return returnJson.setSuccess("该频道下尚未添加文章");
    }

    @RequestMapping(value="admin/channel/get/{id}",method={RequestMethod.GET}, produces = {Constants.JSON_ENCODING})
    public ReturnJson getById(@PathVariable Integer id){
        ReturnJson returnJson = new ReturnJson();
        SsMenu ssMenu = ssMenuDao.getById(id);
        if (ssMenu!=null){
            returnJson.setResult(ssMenu);
            return returnJson.setSuccess("查询成功");
        }
        return returnJson.setFail("频道ID不存在");
    }

    @RequestMapping(value="admin/channel/delete/{id}",method={RequestMethod.DELETE}, produces = {Constants.JSON_ENCODING})
    public ReturnJson delete(@PathVariable Integer id){
        ReturnJson<SsMenu> returnJson=new ReturnJson<>();
        SsMenu ssMenu = ssMenuDao.getById(id);
        if (ssMenu!=null){
            ssMenuDao.delete(id);
            return returnJson.setFail("删除成功");
        }
        return returnJson.setFail("删除失败");
    }
}
