package com.pinyougou.service;

        import com.pinyougou.entity.PageResult;
        import com.pinyougou.pojo.TbBrand;

        import java.util.List;

public interface BrandService {

    /**
     * 查询所有品牌
     * @return
     */
    public List<TbBrand> findAll();

    /**
     * 分页查询品牌列表
     * @param page
     * @param size
     * @return
     */
    public PageResult<TbBrand> findPage(Integer page , Integer size ,TbBrand brand);


    /**
     * 新增品牌
     * @param brand
     */
    public void saveBrand(TbBrand brand);

    /**
     * 跟据id查询数据
     * @param id
     * @return
     */
    public TbBrand queryBrand(Long id);

    /**
     * 修改品牌
     * @param brand
     */
    public void updateBrand(TbBrand brand);


    /***
     * 删除品牌
     * @param ids
     */
    public void deleteBrand(Long[] ids);

}
