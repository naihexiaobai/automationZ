package com.wap.controller.service;

import com.wap.controller.dao.impdao.MenuTreeMapperImp;
import com.wap.model.MenuTree;
import com.wap.util.PagerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/20.
 */
@Service("menuTreeService")
public class MenuTreeService {
    @Resource(name = "menuTreeMapperImp")
    private MenuTreeMapperImp menuTreeMapperImp;

    public void selectByIdUpdateStatus(MenuTree menuTree) {
        menuTreeMapperImp.selectByIdUpdateStatus(menuTree);
    }

    public List<MenuTree> selectGruopByLC(String listC) {
        return menuTreeMapperImp.selectGruopByLC(listC);
    }

    public List<MenuTree> selectByLFC(String listFC) {
        return menuTreeMapperImp.selectByLFC(listFC);
    }

    public int insertSelective(MenuTree record) {
        return menuTreeMapperImp.insertSelective(record);
    }

    public List<MenuTree> getAll() {
        return menuTreeMapperImp.selectAll();
    }

    public List<MenuTree> selectMenuByListCode(String listcode) {
        return menuTreeMapperImp.selectMenuByListCode(listcode);
    }

    public int updateByPrimaryKeySelective(MenuTree record) {
        return menuTreeMapperImp.updateByPrimaryKeySelective(record);
    }

    public int selectAllStatusCount() {
        return menuTreeMapperImp.selectAllStatusCount();
    }

    public List<MenuTree> selectAllStatusByPage(PagerUtil pagerUtil) {
        return menuTreeMapperImp.selectAllStatusByPage(pagerUtil);
    }

    public MenuTree selectByPrimaryKey(Integer id) {
        return menuTreeMapperImp.selectByPrimaryKey(id);
    }

    public List<MenuTree> selectMenuInId(String ids) {
        return menuTreeMapperImp.selectMenuInId(ids);
    }
}
