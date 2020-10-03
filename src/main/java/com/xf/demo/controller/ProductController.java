package com.xf.demo.controller;

import com.xf.demo.model.AyProduct;
import com.xf.demo.service.AyProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author xfgg
 */
@Controller
@RequestMapping("/products")
public class ProductController {
    @Resource
    private AyProductService ayProductService;

    /**
     * 查询所有商品
     * @param model model对象
     * @return 返回product_list页面
     */
    @RequestMapping("/all")
    public String findAll(Model model){
        List<AyProduct> products = ayProductService.findAll();
        model.addAttribute("products",products);
        return "product_list";
    }

    /**
     * 秒杀商品
     * @param model model对象
     * @param productId 商品id
     * @param userId 用户id
     * @return 返回成功或者失败页面
     */
    @RequestMapping("/{id}/kill")
    public String killProduct(Model model, @PathVariable("id") Integer productId,
                              @RequestParam("userId") Integer userId){
        AyProduct ayProduct = ayProductService.killProduct(productId,userId);
        if (null!=ayProduct){
            return  "success";
        }
        return  "fail";
    }

    /**
     * 查询所有商品带缓存
     * @param model
     * @return
     */
    @RequestMapping("/all/cache")
    public String findAllCache(Model model){
        Collection<AyProduct> products = ayProductService.findAllCache();
        model.addAttribute("products",products);
        return "product_list";
    }
}
