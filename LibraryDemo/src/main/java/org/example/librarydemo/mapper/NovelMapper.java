package org.example.librarydemo.mapper;

/**
 * Created with InterlliJ IDEA.
 * Description:
 * User:qianmo
 * Date:2026-10-25
 * Time:10:13
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.librarydemo.entity.Novel;

@Mapper
public interface NovelMapper extends BaseMapper<Novel> {
}

