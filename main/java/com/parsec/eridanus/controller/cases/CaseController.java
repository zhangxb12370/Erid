package com.parsec.eridanus.controller.cases;

import com.parsec.eridanus.dao.CasesDao;
import com.parsec.eridanus.po.Cases;
import com.parsec.tool.*;
import com.parsec.universal.utils.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangxb on 2016/11/13.
 */
@RestController
public class CaseController extends AbstractCommonController {
    @Resource
    private CasesDao casesDao;

    /**
     * @param cases
     * @return
     */
    @RequestMapping(value = "admin/case/edit", method = RequestMethod.POST, produces = {Constant.PRODUCE})
    public ReturnJson edit(@RequestBody Cases cases) {
        ReturnJson ret = new ReturnJson();
        if (StringUtil.isEmpty(cases.getLogoUrl())) {
            return ret.setFail("被投企业logo不能为空");
        }
        if (cases.getOrderNum() != null && cases.getOrderNum() == 0) {
            return ret.setFail("序号不能为0");
        }
        int count = casesDao.getCount();
        if (count == 0) {
            count = 1;
        }
        if (cases.getId() == null) {
            cases.setCreateTime(new Date());
            if (cases.getOrderNum() == null) {
                cases.setOrderNum(count + 1);
            } else {
                modifyOrderNum(cases, 0);
            }
            casesDao.add(cases);
            return ret.setSuccess("被投企业资料添加成功");
        } else {
            if (cases.getOrderNum() != null) {
                modifyOrderNum(cases, casesDao.getById(cases.getId()).getOrderNum());
            }
            casesDao.edit(cases);
            return ret.setSuccess("被投企业资料修改成功");
        }
    }

    /**
     * 被投企业列表
     *
     * @return
     */
    @RequestMapping(value = {"admin/case/all", "public/case/all"}, method = RequestMethod.GET, produces = {Constant.PRODUCE})
    public ReturnJson findAll(Integer pageNo, Integer pageSize) {
        ReturnJson ret = new ReturnJson();
        if (pageNo == null) {
            return ret.setFail("pageNO is null");
        }
        if (pageSize == null) {
            return ret.setFail("pageSize is null");
        }
        PageResult pageResult = this.getPagedList(new Cases() {{
            setPageNo(pageNo);
            setPageSize(pageSize);
        }});
        if (pageResult.getPages() > 0 && pageResult.getList().size() > 0) {
            ret.setResult(pageResult);
            return ret.setSuccess("查询成功");
        }
        return ret.setFail("还没有添加被投企业");

    }

    /**
     * 删除被投企业
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "admin/case/delete/{id}", method = RequestMethod.DELETE, produces = {Constant.PRODUCE})
    public ReturnJson delete(@PathVariable Integer id) {
        ReturnJson ret = new ReturnJson();
        Cases cases = casesDao.getById(id);
        if (cases == null) {
            return ret.setFail("该企业不存在");
        }
        casesDao.delete(id);
        casesDao.updateOrderNum(cases.getOrderNum());
        return ret.setSuccess("删除成功");
    }

    @RequestMapping(value = "admin/case/get/{id}", method = RequestMethod.DELETE, produces = {Constant.PRODUCE})
    public ReturnJson getById(@PathVariable Integer id) {
        ReturnJson ret = new ReturnJson();
        Cases cases = casesDao.getById(id);
        if (cases == null) {
            return ret.setFail("该企业不存在");
        }
        ret.setResult(cases);
        return ret.setSuccess("查询成功");
    }

    private void modifyOrderNum(Cases cases, int oldNum) {
        List<Cases> casesList = casesDao.selectAllCase(cases.getOrderNum());
        List<Cases> willModify;
        if (oldNum == 0) {
            willModify = casesList;
        } else {
            willModify = casesList.stream().filter(c -> c.getOrderNum() < oldNum).collect(Collectors.toList());
        }
        for (Cases existCase : willModify) {
            existCase.setOrderNum(existCase.getOrderNum() + 1);
        }
        casesDao.modifyList(casesList);
    }

    @Override
    public Integer getRecCount(BaseModel model) {
        return casesDao.countAll();
    }

    @Override
    public List<? extends BaseModel> selectPagedList(BaseModel model) {
        Cases cases = (Cases) model;
        return casesDao.selectPagedList(cases);
    }
}
