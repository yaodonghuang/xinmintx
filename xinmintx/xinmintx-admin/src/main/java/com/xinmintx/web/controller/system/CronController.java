package com.xinmintx.web.controller.system;

import com.xinmintx.common.annotation.Log;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.core.page.TableDataInfo;
import com.xinmintx.common.enums.BusinessType;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.system.domain.Cron;
import com.xinmintx.system.mapper.CronMapper;
import com.xinmintx.system.service.ICronService;
import com.xinmintx.web.controller.tool.UrlConfig;
import com.xinmintx.web.controller.tool.httpclient.HttpClientUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时器Controller
 *
 * @author xinmintx
 * @date 2020-03-12
 */
@Controller
@RequestMapping("/system/cron")
public class CronController extends BaseController {
    private String prefix = "system/cron";

    @Autowired
    private ICronService cronService;
    @Resource
    private CronMapper cronMapper;
    @Resource
    private UrlConfig urlConfig;

    @RequiresPermissions("system:cron:view")
    @GetMapping()
    public String cron() {
        return prefix + "/cron";
    }

    /**
     * 查询定时器列表
     */
    @RequiresPermissions("system:cron:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Cron cron) {
        startPage();
        List<Cron> list = cronService.selectCronList(cron);
        return getDataTable(list);
    }

    /**
     * 导出定时器列表
     */
    @RequiresPermissions("system:cron:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Cron cron) {
        List<Cron> list = cronService.selectCronList(cron);
        ExcelUtil<Cron> util = new ExcelUtil<Cron>(Cron.class);
        return util.exportExcel(list, "cron");
    }

    /**
     * 新增定时器
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存定时器
     */
    @RequiresPermissions("system:cron:add")
    @Log(title = "定时器", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Cron cron) {
        return toAjax(cronService.insertCron(cron));
    }

    /**
     * 修改定时器
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Cron cron = cronService.selectCronById(id);
        mmap.put("cron", cron);
        return prefix + "/edit";
    }

    /**
     * 修改保存定时器
     */
    @RequiresPermissions("system:cron:edit")
    @Log(title = "定时器", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Cron cron) {
        return toAjax(cronService.updateCron(cron));
    }

    /**
     * 删除定时器
     */
    @RequiresPermissions("system:cron:remove")
    @Log(title = "定时器", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cronService.deleteCronByIds(ids));
    }

    /**
     * 关闭定时器
     *
     * @param ids
     * @return
     */
    @RequiresPermissions("system:cron:endTask")
    @PostMapping("/endTask")
    @ResponseBody
    public AjaxResult endTask(String ids) {
        String url = urlConfig.getEndTaskUrl();
        Map<String, String> headers = new HashMap<>(1);
        headers.put("type", "tigger");
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id", ids);
        String info = HttpClientUtil.doPost(url, headers, paramMap);
        if ("endTask".equalsIgnoreCase(info)) {
            Cron cron = new Cron();
            cron.setId(Long.valueOf(ids));
            cron.setNowState("1");
            cronMapper.updateCron(cron);
            return toAjax(1);
        } else {
            Cron cron = new Cron();
            cron.setId(Long.valueOf(ids));
            cron.setNowState("2");
            cronMapper.updateCron(cron);
            return toAjax(0);
        }
    }

    /**
     * 开启定时器
     *
     * @param ids
     * @return
     */
    @RequiresPermissions("system:cron:startTask")
    @PostMapping("/startTask")
    @ResponseBody
    public AjaxResult startTask(String ids) {
        String url = urlConfig.getStartTaskUrl();
        Map<String, String> headers = new HashMap<>(1);
        headers.put("type", "tigger");
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id", ids);
        String info = HttpClientUtil.doPost(url, headers, paramMap);
        if ("schedule".equalsIgnoreCase(info)) {
            Cron cron = new Cron();
            cron.setId(Long.valueOf(ids));
            cron.setNowState("3");
            cronMapper.updateCron(cron);
            return toAjax(1);
        } else if ("schedule fail!".equalsIgnoreCase(info)) {
            Cron cron = new Cron();
            cron.setId(Long.valueOf(ids));
            cron.setNowState("4");
            cronMapper.updateCron(cron);
            return toAjax(1);
        } else if ("cron empty".equalsIgnoreCase(info)) {
            Cron cron = new Cron();
            cron.setId(Long.valueOf(ids));
            cron.setNowState("5");
            cronMapper.updateCron(cron);
            return toAjax(1);
        } else if ("className empty".equalsIgnoreCase(info)) {
            Cron cron = new Cron();
            cron.setId(Long.valueOf(ids));
            cron.setNowState("6");
            cronMapper.updateCron(cron);
            return toAjax(1);
        } else {
            Cron cron = new Cron();
            cron.setId(Long.valueOf(ids));
            cron.setNowState("7");
            cronMapper.updateCron(cron);
            return toAjax(1);
        }
    }

    /**
     * 变更定时器
     *
     * @param ids
     * @return
     */
    @RequiresPermissions("system:cron:changeTask")
    @PostMapping("/changeTask")
    @ResponseBody
    public AjaxResult changeTask(String ids) {
        String url = urlConfig.getChangeTaskUrl();
        Map<String, String> headers = new HashMap<>(1);
        headers.put("type", "tigger");
        String info = HttpClientUtil.doPost(url, headers, null);
        if ("changeTask".equalsIgnoreCase(info)) {
            Cron cron = new Cron();
            cron.setId(Long.valueOf(ids));
            cron.setNowState("4");
            cronMapper.updateCron(cron);
            return toAjax(1);
        } else {
            Cron cron = new Cron();
            cron.setId(Long.valueOf(ids));
            cron.setNowState("0");
            cronMapper.updateCron(cron);
            return toAjax(0);
        }
    }
}

