package com.ruoyi.qwk.service.impl;

import java.util.*;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.qwk.domain.HomepageRotatingPictures;
import com.ruoyi.qwk.mapper.HomepageRotatingPicturesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.HomepageGameMapper;
import com.ruoyi.qwk.domain.HomepageGame;
import com.ruoyi.qwk.service.IHomepageGameService;

/**
 * 首页游戏列 Service业务层处理
 *
 * @author cjf
 * @date 2023-11-27
 */
@Service
public class HomepageGameServiceImpl implements IHomepageGameService
{
    @Autowired
    private HomepageGameMapper homepageGameMapper;

    @Autowired
    private HomepageRotatingPicturesMapper homepageRotatingPicturesMapper;

    /**
     * 查询首页游戏列
     *
     * @param id 首页游戏列 主键
     * @return 首页游戏列
     */
    @Override
    public HomepageGame selectHomepageGameById(Long id)
    {
        return homepageGameMapper.selectHomepageGameById(id);
    }

    /**
     * 查询首页游戏列 列表
     *
     * @param homepageGame 首页游戏列
     * @return 首页游戏列
     */
    @Override
    public List<HomepageGame> selectHomepageGameList(HomepageGame homepageGame)
    {
        return homepageGameMapper.selectHomepageGameList(homepageGame);
    }

    /**
     * 新增首页游戏列
     *
     * @param homepageGame 首页游戏列
     * @return 结果
     */
    @Override
    public int insertHomepageGame(HomepageGame homepageGame)
    {
        homepageGame.setCreateTime(DateUtils.getNowDate());
        return homepageGameMapper.insertHomepageGame(homepageGame);
    }

    /**
     * 修改首页游戏列
     *
     * @param homepageGame 首页游戏列
     * @return 结果
     */
    @Override
    public int updateHomepageGame(HomepageGame homepageGame)
    {
        homepageGame.setUpdateTime(DateUtils.getNowDate());
        return homepageGameMapper.updateHomepageGame(homepageGame);
    }

    /**
     * 批量删除首页游戏列
     *
     * @param ids 需要删除的首页游戏列 主键
     * @return 结果
     */
    @Override
    public int deleteHomepageGameByIds(Long[] ids)
    {
        return homepageGameMapper.deleteHomepageGameByIds(ids);
    }

    /**
     * 删除首页游戏列 信息
     *
     * @param id 首页游戏列 主键
     * @return 结果
     */
    @Override
    public int deleteHomepageGameById(Long id)
    {
        return homepageGameMapper.deleteHomepageGameById(id);
    }

    /**
     * 查询APP首页游戏列表
     * @return
     */
    @Override
    public Map<String,Object> getAppHomepageGame(){
        Map<String,Object> map = new HashMap<>();
        //获取首页游戏列表
        HomepageGame homepageGame = new HomepageGame();
        List<HomepageGame> gameList = homepageGameMapper.selectHomepageGameList(homepageGame);
        if(CollUtil.isNotEmpty(gameList)){
            HomepageGame maxGame = gameList.stream().max(Comparator.comparing(HomepageGame::getSort)).get();
            map.put("blindBox",maxGame);
            gameList.removeIf(s -> "999".equals(s.getSort()));
        }
        //获取首页图片组
        HomepageRotatingPictures homepageRotatingPictures = new HomepageRotatingPictures();
        List<HomepageRotatingPictures> pictureList = homepageRotatingPicturesMapper.selectHomepageRotatingPicturesList(homepageRotatingPictures);
        //角色图片
        List<HomepageRotatingPictures> rolePictureList = new ArrayList<>();
        //背景图片
        List<HomepageRotatingPictures> backgroundPictureList = new ArrayList<>();
        map.put("gameList",gameList);
        map.put("pictureList",pictureList);
        return map;
    }
}
