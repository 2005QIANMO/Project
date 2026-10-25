-- ============================================================
-- 小说阅读器 数据库搭建脚本
-- 目录: D:/JAVA/Code/蛊真人/   共 14 本 txt（已全部统一为 UTF-8）
-- ============================================================

-- 1. 建库（库名统一小写，避免大小写问题；utf8mb4 支持中文/生僻字/符号）
CREATE DATABASE IF NOT EXISTS novel
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE novel;

-- 2. 建表（修正 ENGINE 位置；file_path 存相对文件名，配合 base-path 拼接）
DROP TABLE IF EXISTS novel;
CREATE TABLE novel (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    novel_name  VARCHAR(255) NOT NULL COMMENT '小说名称',
    category    VARCHAR(32)  DEFAULT NULL COMMENT '分类: 原著/人祖传/同人续写/语录摘抄',
    file_path   VARCHAR(512) NOT NULL COMMENT 'txt文件名(相对 novel.base-path, 不含目录)',
    word_count  INT          DEFAULT 0 COMMENT '字数(字符数, 含标点)',
    read_pos    BIGINT       DEFAULT 0 COMMENT '阅读位置(字符偏移量)',
    create_time DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    KEY idx_category (category)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '小说元信息表';

-- 3. 导入 14 本书元数据（正文不入库，仍在磁盘 txt）
-- 重复执行前先清空: TRUNCATE TABLE novel;

-- —— 原著（作者: 蛊真人）——
INSERT INTO novel (novel_name, category, file_path, word_count) VALUES
('控运',       '原著', '《控运》.txt',                       1236789),
('御妖至尊',    '原著', '《御妖至尊》.txt',                    2304505),
('无限群芳谱',  '原著', '无限群芳谱_www.84sk.com.txt',        1166516);

-- —— 人祖传（《蛊真人》书中书，两个版本）——
INSERT INTO novel (novel_name, category, file_path, word_count) VALUES
('人祖传（网络版）',           '人祖传', '《人祖传》.txt',                        82247),
('人祖传（横版简体打印版）',    '人祖传', '人祖传横版简体字A4自打印版本.txt',       88794);

-- —— 同人续写 ——
INSERT INTO novel (novel_name, category, file_path, word_count) VALUES
('蛊真人续写（三）',          '同人续写', '蛊续写(三).txt',                    93547),
('剑下败将·厉煌传',           '同人续写', '剑下败将——厉煌传.txt',                2263),
('狂蛮传',                    '同人续写', '狂蛮传.txt',                         15324),
('魔卡异世界·蛊真人',         '同人续写', '魔卡异世界 蛊真人.txt',               477660),
('海贼王之母巢果实（精较版）', '同人续写', '海贼王之母巢果实（精较版）.txt',       631361);

-- —— 语录摘抄 / 诗词合集 ——
INSERT INTO novel (novel_name, category, file_path, word_count) VALUES
('蛊真人·诗句摘抄注解大全集', '语录摘抄', '《gu真人》诗句摘抄注解大全集.txt',        7390),
('大爱仙尊诗词金句合集',      '语录摘抄', '大爱仙尊诗词金句合集A4打印版-1.txt',      8086),
('仙尊语录',                  '语录摘抄', '仙尊语录.txt',                           2308),
('蛊真人语录金句摘抄',        '语录摘抄', '语录金句摘抄（超多）.txt',                8148);

-- 4. 验证
SELECT id, novel_name, category, file_path, word_count FROM novel ORDER BY category, id;
