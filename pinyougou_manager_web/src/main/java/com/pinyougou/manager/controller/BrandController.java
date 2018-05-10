package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Result;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;


    /**
     * 查询所有品牌
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){

        return brandService.findAll();
    }

    /**
     * 分页查询品牌
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult<TbBrand> findPage(Integer page , Integer size ,@RequestBody TbBrand brand){

        PageResult<TbBrand> pageResult = brandService.findPage(page, size , brand);

        return pageResult;
    }


    /**
     * 添加品牌
     * @param brand
     * @return
     */
    @RequestMapping("/add")
    public Result saveBrand(@RequestBody TbBrand brand){

        try {
            brandService.saveBrand(brand);
            return new Result(true , "保存品牌成功。");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false , "保存品牌失败。");
        }

    }

    /**
     * 跟据id查找数据
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public  TbBrand findById(Long id){
        return brandService.queryBrand(id);
    }


    /**
     * 修改品牌
     * @param brand
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand){
        try {
            brandService.updateBrand(brand);

            return new Result(true , "修改品牌成功。");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false , "修改品牌失败。");
        }
    }


    /**
     * 删除品牌的方法
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids){

        try {
            brandService.deleteBrand(ids);
            return new Result(true , "删除品牌成功。");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除品牌失败。");
        }
    }

}
