package com.wap.controller.dao.impdao;

import com.wap.controller.dao.MenuTreeMapper;
import com.wap.model.MenuTree;
import com.wap.util.PagerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/20.
 */
@Service("menuTreeMapperImp")
public class MenuTreeMapperImp implements MenuTreeMapper {
    @Resource(name = "menuTreeMapper")
    private MenuTreeMapper menuTreeMapper;

    public int deleteByPrimaryKey(Integer id) {
        return menuTreeMapper.deleteByPrimaryKey(id);
    }

    public int insert(MenuTree record) {
        return menuTreeMapper.insert(record);
    }

    public List<MenuTree> selectAll() {
        return menuTreeMapper.selectAll();
    }

    public List<MenuTree> selectAllStatusByPage(PagerUtil pagerUtil) {
        return menuTreeMapper.selectAllStatusByPage(pagerUtil);
    }

    public int selectAllStatusCount() {
        return menuTreeMapper.selectAllStatusCount();
    }

    public List<MenuTree> selectGruopByLC(String listC) {
        return menuTreeMapper.selectGruopByLC(listC);
    }

    public List<MenuTree> selectByLFC(String listFC) {
        return menuTreeMapper.selectByLFC(listFC);
    }

    public void selectByIdUpdateStatus(MenuTree menuTree) {
        menuTreeMapper.selectByIdUpdateStatus(menuTree);
    }

    /**
     * @param listcode,sql中判断条件为like格式 '%.%.%'
     * @return
     */
    public List<MenuTree> selectMenuByListCode(String listcode) {
        return menuTreeMapper.selectMenuByListCode(listcode);
    }

    public List<MenuTree> selectMenuInId(String ids) {
        return menuTreeMapper.selectMenuInId(ids);
    }

    public int insertSelective(MenuTree record) {
        return menuTreeMapper.insertSelective(record);
    }

    public MenuTree selectByPrimaryKey(Integer id) {
        return menuTreeMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(MenuTree record) {
        return menuTreeMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MenuTree record) {
        return menuTreeMapper.updateByPrimaryKey(record);
    }
}
