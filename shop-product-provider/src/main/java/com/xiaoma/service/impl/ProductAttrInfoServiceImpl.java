package com.xiaoma.service.impl;

import com.xiaoma.mapper.custom.BaseAttrInfoCustomMapper;
import com.xiaoma.mapper.generate.BaseAttrInfoMapper;
import com.xiaoma.mapper.generate.BaseAttrValueMapper;
import com.xiaoma.pojo.BaseAttrInfo;
import com.xiaoma.pojo.BaseAttrInfoExample;
import com.xiaoma.pojo.BaseAttrValue;
import com.xiaoma.pojo.BaseAttrValueExample;
import com.xiaoma.service.ProductAttrInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAttrInfoServiceImpl implements ProductAttrInfoService {

    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    BaseAttrInfoCustomMapper baseAttrInfoCustomMapper;

    @Override
    public List<BaseAttrInfo> getAttrByCatalog3Id(Integer catalog3Id) {
        BaseAttrInfoExample baseAttrInfoExample = new BaseAttrInfoExample();
        baseAttrInfoExample.createCriteria().andCatalog3IdEqualTo(catalog3Id.longValue());
        List<BaseAttrInfo> baseAttrInfos = baseAttrInfoMapper.selectByExample(baseAttrInfoExample);
        // 查询每个属性对应的属性列表
        baseAttrInfos.forEach(baseAttrInfo -> {
            BaseAttrValueExample baseAttrValueExample = new BaseAttrValueExample();
            baseAttrValueExample.createCriteria().andAttrIdEqualTo(baseAttrInfo.getId());
            baseAttrInfo.setAttrValueList(baseAttrValueMapper.selectByExample(baseAttrValueExample));
        });
        return baseAttrInfos;
    }

    /**
     * 添加操作 + 修改操作
     * 如果是添加操作,属性id为null -----只进行insert操作
     * 如果是修改操作,属性id是当前需要修改属性的id ------先delete属性名和属性值,再insert
     * @param baseAttrInfo 接收页面的属性和属性值信息
     */
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        if(!StringUtils.isEmpty(baseAttrInfo.getId()+"")){
            // 删除属性名
            baseAttrInfoMapper.deleteByPrimaryKey(baseAttrInfo.getId());
            // 删除属性值
            baseAttrInfo.getAttrValueList().forEach(baseAttrValue -> {
                baseAttrValueMapper.deleteByPrimaryKey(baseAttrValue.getId());
            });
        }
        // 1.将属性相关的信息保存到base_attr_info表中
        baseAttrInfoMapper.insertSelective(baseAttrInfo);
        // 2.将属性对应的所有属性值保存到base_attr_value中
        /*for (BaseAttrValue baseAttrValue : baseAttrInfo.getAttrValueList()) {
            baseAttrValue.setAttrId(baseAttrInfo.getId());
            baseAttrValueMapper.insertSelective(baseAttrValue);
        }*/
        baseAttrInfo.getAttrValueList().forEach(baseAttrValue -> {
           baseAttrValue.setAttrId(baseAttrInfo.getId());
            baseAttrValueMapper.insertSelective(baseAttrValue);
        });
    }

    @Override
    public List<BaseAttrValue> getAttrValueByAttrId(Integer attrId) {
        BaseAttrValueExample baseAttrValueExample = new BaseAttrValueExample();
        baseAttrValueExample.createCriteria().andAttrIdEqualTo(attrId.longValue());
        return baseAttrValueMapper.selectByExample(baseAttrValueExample);
    }

    @Override
    public List<BaseAttrInfo> findByValueIds(List<Integer> valueIds) {
        return baseAttrInfoCustomMapper.selectBaseAttrByValueIds(valueIds);
    }
}
