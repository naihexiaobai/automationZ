package com.wap.controller.dao.impdao;

import com.wap.controller.dao.PositionMapper;
import com.wap.model.Position;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/26.
 */
@Service("positionMapperImp")
public class PositionMapperImp implements PositionMapper {
    @Resource(name = "positionMapper")
    private PositionMapper positionMapper;

    public int deleteByPrimaryKey(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    public int insert(Position record) {
        return positionMapper.insert(record);
    }

    public int insertSelective(Position record) {
        return positionMapper.insertSelective(record);
    }

    public List<Position> selectGetAll() {
        return positionMapper.selectGetAll();
    }

    public int deleteByPositionCode(Integer positioncode) {
        return positionMapper.deleteByPositionCode(positioncode);
    }

    public int updateByPositionCodeSelective(Position position) {
        return positionMapper.updateByPositionCodeSelective(position);
    }

    public Position selectByPrimaryKey(Integer id) {
        return positionMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Position record) {
        return positionMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Position record) {
        return positionMapper.updateByPrimaryKeySelective(record);
    }
}
