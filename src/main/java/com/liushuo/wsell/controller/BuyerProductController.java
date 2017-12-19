package com.liushuo.wsell.controller;

import com.liushuo.wsell.Service.CategoryService;
import com.liushuo.wsell.Service.ProductService;
import com.liushuo.wsell.dataobject.ProductCategory;
import com.liushuo.wsell.dataobject.ProductInfo;
import com.liushuo.wsell.utils.ResultUtil;
import com.liushuo.wsell.vo.ProductInfoVO;
import com.liushuo.wsell.vo.ProductVO;
import com.liushuo.wsell.vo.ResultVo;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by liushuo on 2017/11/28.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public ResultVo list(){

        ResultVo vo = new ResultVo();
        vo.setCode(0);
        vo.setMsg("交易成功");

        ProductVO productVO = new ProductVO();
        productVO.setCategoryName("热卖");
        productVO.setCategoryType(2);

        ProductInfoVO productInfoVO = new ProductInfoVO();
        productInfoVO.setProductId("11111");
        productInfoVO.setProductDescription("very very nice！");
        productInfoVO.setProductName("板栗烧鸡");
        productInfoVO.setProductPrice(new BigDecimal(66));
        productInfoVO.setProductIcon("http://ffff.jpg");

        ProductInfoVO productInfoVO1= new ProductInfoVO();
        productInfoVO1.setProductId("12111");
        productInfoVO1.setProductDescription("very very nice！");
        productInfoVO1.setProductName("黄焖鸡米饭");
        productInfoVO1.setProductPrice(new BigDecimal(20));
        productInfoVO1.setProductIcon("http://ffdd.jpg");

        List<ProductInfoVO> productInfoList = new ArrayList<>();
        productInfoList.add(productInfoVO);
        productInfoList.add(productInfoVO1);
        productVO.setProductInfoVOList(productInfoList);

        vo.setData(productVO);
        return vo;
    }

    @GetMapping("/listInfo")
    public ResultVo listInfo(){
        List<ProductInfo> productInfoList = productService.findAll();
        List<Integer> categoryIdList = new ArrayList<>();
        for (ProductInfo productInfo: productInfoList) {
            categoryIdList.add(productInfo.getCategoryType());
        }

        List<ProductCategory> categoryList = categoryService.findCategorysByType(categoryIdList);

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:categoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultUtil.success(productVOList);
    }

}
