package org.example.librarydemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 小说元信息实体（对应 novel 表；正文不入库，仍存放在磁盘 txt）
 */
@Data
@TableName("novel")
public class Novel {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 小说名称，对应列 novel_name */
    private String novelName;

    /** 分类：原著 / 人祖传 / 同人续写 / 语录摘抄 */
    private String category;

    /** txt 文件名（相对 base-path），对应列 file_path */
    private String filePath;

    /** 字数（字符数），对应列 word_count */
    private Integer wordCount;

    /** 阅读位置（字符偏移量），对应列 read_pos */
    private Long readPos;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
