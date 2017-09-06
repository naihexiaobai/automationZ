package com.wap.controller.dao.impdao;

import com.wap.controller.dao.AutoProductMapper;
import com.wap.model.AutoProduct;
import com.wap.model.AutoProductSelectPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/8/1.
 */
@Service("autoProductMapperImp")
public class AutoProductMapperImp implements AutoProductMapper {
    @Resource(name = "autoProductMapper")
    private AutoProductMapper autoProductMapper;

    public int deleteByPrimaryKey(Integer id) {
        return autoProductMapper.deleteByPrimaryKey(id);
    }

    public int selectByApCount(AutoProductSelectPage autoProduct) {
        return autoProductMapper.selectByApCount(autoProduct);
    }

    public int insert(AutoProduct record) {
        return autoProductMapper.insert(record);
    }

    public List<AutoProduct> selectByAp(AutoProductSelectPage autoProduct) {
        return autoProductMapper.selectByAp(autoProduct);
    }

    public int insertSelective(AutoProduct record) {
        return autoProductMapper.insertSelective(record);
    }

    public AutoProduct selectByPrimaryKey(Integer id) {
        return autoProductMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(AutoProduct record) {
        return autoProductMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(AutoProduct record) {
        return autoProductMapper.updateByPrimaryKey(record);
    }
}
