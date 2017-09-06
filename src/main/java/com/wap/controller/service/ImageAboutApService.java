package com.wap.controller.service;

import com.wap.controller.dao.impdao.ImageAboutApMapperImp;
import com.wap.model.ImageAboutAp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/8/7.
 */
@Service("imageAboutApService")
public class ImageAboutApService {
    @Resource(name = "imageAboutApMapperImp")
    private ImageAboutApMapperImp imageAboutApMapperImp;

    public int deleteByPrimaryKey(Integer id) {
        return imageAboutApMapperImp.deleteByPrimaryKey(id);
    }

    public int insert(ImageAboutAp record) {
        return imageAboutApMapperImp.insert(record);
    }

    public int insertSelective(ImageAboutAp record) {
        return imageAboutApMapperImp.insertSelective(record);
    }

    public ImageAboutAp selectByPrimaryKey(Integer id) {
        return imageAboutApMapperImp.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ImageAboutAp record) {
        return imageAboutApMapperImp.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ImageAboutAp record) {
        return imageAboutApMapperImp.updateByPrimaryKey(record);
    }

    public int updateByApidSelective(ImageAboutAp imageAboutAp) {
        return imageAboutApMapperImp.updateByApidSelective(imageAboutAp);
    }

    public List<ImageAboutAp> selectByApid(Integer apid) {
        return imageAboutApMapperImp.selectByApid(apid);
    }
}
