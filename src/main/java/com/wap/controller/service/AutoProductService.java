package com.wap.controller.service;

import com.wap.controller.dao.impdao.AutoProductMapperImp;
import com.wap.model.AutoProduct;
import com.wap.model.AutoProductSelectPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/8/1.
 */
@Service("autoProductService")
public class AutoProductService {
    @Resource(name = "autoProductMapperImp")
    private AutoProductMapperImp autoProductMapperImp;

    public int deleteByPrimaryKey(Integer id) {
        return autoProductMapperImp.deleteByPrimaryKey(id);
    }

    public int insert(AutoProduct record) {
        return autoProductMapperImp.insert(record);
    }

    public int insertSelective(AutoProduct record) {
        return autoProductMapperImp.insertSelective(record);
    }

    public AutoProduct selectByPrimaryKey(Integer id) {
        return autoProductMapperImp.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(AutoProduct record) {
        return autoProductMapperImp.updateByPrimaryKeySelective(record);
    }

    public int selectByApCount(AutoProductSelectPage autoProduct) {
        return autoProductMapperImp.selectByApCount(autoProduct);
    }

    public List<AutoProduct> selectByAp(AutoProductSelectPage autoProduct) {
        return autoProductMapperImp.selectByAp(autoProduct);
    }

    public int updateByPrimaryKey(AutoProduct record) {
        return autoProductMapperImp.updateByPrimaryKey(record);
    }

}
