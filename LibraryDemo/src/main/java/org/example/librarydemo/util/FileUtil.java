package org.example.librarydemo.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 本地 txt 文件读取工具（UTF-8）
 */
public class FileUtil {

    /**
     * 读取小说正文
     *
     * @param basePath 小说根目录（application.yml 中的 novel.base-path）
     * @param fileName 文件名（数据库 file_path 字段）
     * @return 小说正文
     */
    public static String readNovel(String basePath, String fileName) throws IOException {
        // 根目录（只放根目录，不含文件名）
        Path base = Paths.get(basePath).toAbsolutePath().normalize();
        // 只拼接一次文件名
        Path target = base.resolve(fileName).normalize();

        // 安全校验：防止通过 ../ 读到根目录之外的文件
        if (!target.startsWith(base)) {
            throw new SecurityException("非法文件路径：" + fileName);
        }
        if (!Files.exists(target)) {
            throw new IOException("文件不存在：" + fileName);
        }

        // 14 个 txt 已统一为 UTF-8
        return Files.readString(target, StandardCharsets.UTF_8);
    }
}
