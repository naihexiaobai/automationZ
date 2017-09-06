package com.wap.controller.service;

import com.wap.controller.dao.impdao.PositionMapperImp;
import com.wap.model.Position;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/26.
 */
@Service("positionService")
public class PositionService {
    @Resource(name = "positionMapperImp")
    private PositionMapperImp positionMapperImp;

    public List<Position> getAll() {
        return positionMapperImp.selectGetAll();
    }

    public int deleteByPositionCode(Integer positioncode) {
        return positionMapperImp.deleteByPositionCode(positioncode);
    }

    public int updateByPositionCodeSelective(Position position) {
        return positionMapperImp.updateByPositionCodeSelective(position);
    }

    public Position selectByPrimaryKey(Integer id) {
        return positionMapperImp.selectByPrimaryKey(id);
    }

    public int insertSelective(Position record) {
        return positionMapperImp.insertSelective(record);
    }


    public int updateByPrimaryKeySelective(Position record) {
        return positionMapperImp.updateByPrimaryKeySelective(record);
    }
}
