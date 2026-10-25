package org.example.librarydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.librarydemo.entity.Novel;
import org.example.librarydemo.mapper.NovelMapper;
import org.example.librarydemo.service.NovelService;
import org.springframework.stereotype.Service;

/**
 * 小说 Service 实现类
 */
@Service
public class NovelServiceImpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {
}
