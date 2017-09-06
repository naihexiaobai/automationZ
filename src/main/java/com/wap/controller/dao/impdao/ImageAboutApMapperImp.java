package com.wap.controller.dao.impdao;

import com.wap.controller.dao.ImageAboutApMapper;
import com.wap.model.ImageAboutAp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/8/7.
 */
@Service("imageAboutApMapperImp")
public class ImageAboutApMapperImp implements ImageAboutApMapper {
    @Resource(name = "imageAboutApMapper")
    private ImageAboutApMapper imageAboutApMapper;

    public int deleteByPrimaryKey(Integer id) {
        return imageAboutApMapper.deleteByPrimaryKey(id);
    }

    public int insert(ImageAboutAp record) {
        return imageAboutApMapper.insert(record);
    }

    public int insertSelective(ImageAboutAp record) {
        return imageAboutApMapper.insertSelective(record);
    }

    public ImageAboutAp selectByPrimaryKey(Integer id) {
        return imageAboutApMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ImageAboutAp record) {
        return imageAboutApMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ImageAboutAp record) {
        return imageAboutApMapper.updateByPrimaryKey(record);
    }

    public int updateByApidSelective(ImageAboutAp imageAboutAp) {
        return imageAboutApMapper.updateByApidSelective(imageAboutAp);
    }

    public List<ImageAboutAp> selectByApid(Integer apid) {
        return imageAboutApMapper.selectByApid(apid);
    }
}
