package com.wap.controller.dao;

import com.wap.model.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("positionMapper")
public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    List<Position> selectGetAll();

    int deleteByPositionCode(Integer positioncode);

    int updateByPositionCodeSelective(Position position);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
}