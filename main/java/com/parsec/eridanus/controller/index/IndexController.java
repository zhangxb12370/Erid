package com.parsec.eridanus.controller.index;

import com.parsec.eridanus.dao.IndexDao;
import com.parsec.eridanus.po.Index;
import com.parsec.tool.Constant;
import com.parsec.tool.ReturnJson;
import com.parsec.universal.utils.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangxb on 2016/11/12.
 */
@RestController
public class IndexController {
    @Resource
    private IndexDao indexDao;
    @RequestMapping(value = "admin/index/edit",method = RequestMethod.POST,produces = {Constant.PRODUCE})
    public ReturnJson edit(@RequestBody Index index){
        ReturnJson ret = new ReturnJson();
        if (StringUtil.isEmpty(index.getImageUrl())){
            return ret.setFail("幻灯片URL为空");
        }
        if (index.getId()==null){
            index.setCreateTime(new Date());
            index.setUpdateTime(new Date());
            indexDao.addIndex(index);
            return ret.setSuccess("添加成功");
        }
        else {
            index.setUpdateTime(new Date());
            indexDao.edit(index);
            return ret.setSuccess("修改成功");
        }
    }

    /**
     * 幻灯片列表
     * @return
     */
    @RequestMapping(value = {"admin/index/all","public/index/all"},method = RequestMethod.GET,produces = {Constant.PRODUCE})
    public ReturnJson findAll(){
        ReturnJson ret = new ReturnJson();
        List<Index> indexList = indexDao.findAll();
        ret.setList(indexList);
        return ret.setSuccess("查询成功");
    }

    /**
     * 删除幻灯片
     * @param id 幻灯片ID
     * @return
     */
    @RequestMapping(value = "admin/index/delete/{id}",method = RequestMethod.DELETE,produces = {"application/json;charset=utf-8"})
    public ReturnJson deleteIndex(@PathVariable Integer id){
        ReturnJson ret = new ReturnJson();
        Index existIndex = indexDao.getById(id);
        if (existIndex==null){
            return ret.setFail("id无效");
        }
        else {
            indexDao.deleteById(id);
            return ret.setSuccess("删除成功");
        }
    }

    /**
     * 根据幻灯片ID查找
     * @param id 幻灯片ID
     * @return
     */
    @RequestMapping(value = "admin/index/get/{id}",method = RequestMethod.GET,produces = {Constant.PRODUCE})
    public ReturnJson getById(@PathVariable Integer id){
        ReturnJson ret = new ReturnJson();
        Index existIndex = indexDao.getById(id);
        if (existIndex==null){
            return ret.setFail("ID无效");
        }
        ret.setResult(existIndex);
        return ret.setSuccess("查询成功");
    }
}
