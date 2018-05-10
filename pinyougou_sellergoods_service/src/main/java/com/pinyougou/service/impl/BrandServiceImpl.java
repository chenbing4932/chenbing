package com.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.entity.PageResult;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.service.BrandService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        return brandMapper.select(null);
    }



    @Override
    public PageResult<TbBrand> findPage(Integer page, Integer size , TbBrand brand) {
        PageResult<TbBrand> pageResult = new PageResult<>();
        //设置分页条件
        PageHelper.startPage(page,size);

        //构建查询条件
        Example example = new Example(TbBrand.class);
        Example.Criteria criteria = example.createCriteria();
        //如果名称不为空
        if(StringUtils.isNotBlank(brand.getName())){
            criteria.andLike("name" , "%"+brand.getName()+"%");
        }
        //如果首字母不为空
        if(StringUtils.isNotBlank(brand.getFirstChar())){
            criteria.andEqualTo("firstChar" , brand.getFirstChar());
        }


        //查询数据
        List<TbBrand> brandList = brandMapper.selectByExample(example);


        //保存数据列表
        pageResult.setRows(brandList);
        //获取总记录数
        PageInfo<TbBrand> pageInfo = new PageInfo<>(brandList);
        pageResult.setTotal(pageInfo.getTotal());

        return pageResult;
    }

    @Override
    public void saveBrand(TbBrand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public TbBrand queryBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateBrand(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void deleteBrand(Long[] ids) {
        List idList = Arrays.asList(ids);

        Example example = new Example(TbBrand.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andIn("id",idList);

        //跟据查询条件删除数据
        brandMapper.deleteByExample(example);

    }
}
