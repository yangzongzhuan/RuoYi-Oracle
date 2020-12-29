package com.ruoyi.web.controller.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.web.service.CacheService;

/**
 * 缓存监控
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/cache")
public class CacheController extends BaseController
{
    private String prefix = "monitor/cache";

    @Autowired
    private CacheService cacheService;

    @GetMapping()
    public String cache(ModelMap mmap)
    {
        mmap.put("cacheNames", cacheService.getCacheNames());
        return prefix + "/cache";
    }

    @PostMapping("/getNames")
    public String getCacheNames(String fragment, ModelMap mmap)
    {
        mmap.put("cacheNames", cacheService.getCacheNames());
        return prefix + "/cache::" + fragment;
    }

    @PostMapping("/getKeys")
    public String getCacheKeys(String fragment, String cacheName, ModelMap mmap)
    {
        mmap.put("cacheName", cacheName);
        mmap.put("cacheKyes", cacheService.getCacheKeys(cacheName));
        return prefix + "/cache::" + fragment;
    }

    @PostMapping("/getValue")
    public String getCacheValue(String fragment, String cacheName, String cacheKey, ModelMap mmap)
    {
        mmap.put("cacheName", cacheName);
        mmap.put("cacheKey", cacheKey);
        mmap.put("cacheValue", cacheService.getCacheValue(cacheName, cacheKey));
        return prefix + "/cache::" + fragment;
    }

    @PostMapping("/clearCacheName")
    @ResponseBody
    public AjaxResult clearCacheName(String cacheName, ModelMap mmap)
    {
        cacheService.clearCacheName(cacheName);
        return AjaxResult.success();
    }

    @PostMapping("/clearCacheKey")
    @ResponseBody
    public AjaxResult clearCacheKey(String cacheName, String cacheKey, ModelMap mmap)
    {
        cacheService.clearCacheKey(cacheName, cacheKey);
        return AjaxResult.success();
    }

    @GetMapping("/clearAll")
    @ResponseBody
    public AjaxResult clearAll(ModelMap mmap)
    {
        cacheService.clearAll();
        return AjaxResult.success();
    }
}
