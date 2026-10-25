package org.example.librarydemo.controller;

/**
 * Created with InterlliJ IDEA.
 * Description:
 * User:qianmo
 * Date:2026-10-25
 * Time:10:12
 */
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.librarydemo.entity.Novel;
import org.example.librarydemo.service.NovelService;
import org.example.librarydemo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/novel")
public class NovelController {

    @Value("${novel.base-path}")
    private String basePath;

    @Autowired
    private NovelService novelService;

    /** 1. 小说列表（可选按分类筛选 ?category=原著） */
    @GetMapping("/list")
    public List<Novel> list(@RequestParam(required = false) String category) {
        QueryWrapper<Novel> qw = new QueryWrapper<>();
        if (category != null && !category.isEmpty()) {
            qw.eq("category", category);
        }
        qw.orderByAsc("category", "id");
        return novelService.list(qw);
    }

    /** 2. 按 id 读取正文（正文从磁盘 txt 读，不在数据库） */
    @GetMapping("/read/{id}")
    public String read(@PathVariable Long id) {
        Novel novel = novelService.getById(id);
        if (novel == null) {
            return "小说不存在";
        }
        try {
            return FileUtil.readNovel(basePath, novel.getFilePath());
        } catch (IOException e) {
            return "读取失败：" + e.getMessage();
        }
    }

    /** 3. 保存阅读进度（前端传 {id, readPos}） */
    @PostMapping("/progress")
    public String saveProgress(@RequestBody Novel param) {
        Novel update = new Novel();
        update.setId(param.getId());
        update.setReadPos(param.getReadPos());
        boolean ok = novelService.updateById(update);
        return ok ? "进度已保存" : "保存失败";
    }

    /** 4. 手动新增一本（可选） */
    @PostMapping("/add")
    public String add(@RequestParam String novelName,
                      @RequestParam String category,
                      @RequestParam String fileName,
                      @RequestParam(required = false) Integer wordCount) {
        Novel novel = new Novel();
        novel.setNovelName(novelName);
        novel.setCategory(category);
        novel.setFilePath(fileName);
        novel.setWordCount(wordCount == null ? 0 : wordCount);
        novel.setReadPos(0L);
        novelService.save(novel);
        return "添加成功，id=" + novel.getId();
    }
}

