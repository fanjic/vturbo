package com.fan.vturbo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

public class WalkFileTreeTest {

    @Test
    public void copy2() {
        try {
            /*Path path= Paths.get("C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\test\\testson/a/b");
            Files.createDirectories(path);
            System.out.println("多级文件夹创建完成");*/

            Path source = Paths.get("C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\test\\testson/hello.txt");
            Path target = Paths.get("C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\test\\testson/hello2.txt");
            // 移动，复制且覆盖,StandardCopyOption.REPLACE_EXISTING
            //Files.move(source,target);
            // 复制
            Files.copy(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void walkFileTreeTest() throws IOException {
        // 遍历文件夹
        AtomicInteger dirNum = new AtomicInteger();
        AtomicInteger fileNum = new AtomicInteger();
        Path path = Paths.get("C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\test");
        // 对文件夹和文件进行遍历
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("=====>进入" + dir);
                dirNum.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                fileNum.incrementAndGet();
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("=====>退出" + dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
        // 包括了当前父级文件夹
        System.out.println(dirNum);
        System.out.println(fileNum);
    }

    @Test
    public void getJar() throws IOException {
        // 得到jar文件和文件数量
        AtomicInteger jarNum = new AtomicInteger();

        Path path = Paths.get("C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\test");
        // 对文件夹和文件进行遍历
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".jpg")) {
                    System.out.println(file);
                    jarNum.incrementAndGet();
                }
                return super.visitFile(file, attrs);
            }
        });
        // 包括了当前父级文件夹
        System.out.println(jarNum);
    }

    @Test
    public void copyFileTree() throws IOException {
        String source = "C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\test\\testson\\a";
        String target = "C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\test\\testson\\b";

        Files.walk(Paths.get(source)).forEach(path -> {

            // 这里的targetName指的是名字中的字符串
            String targetName = path.toString().replace(source, target);
            try {
                if (Files.isDirectory(path)) {
                    Files.createDirectory(Paths.get(targetName));
                } else if (Files.isRegularFile(path)) {
                    Files.copy(path, Paths.get(targetName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void delFileTree() throws IOException {
        Path path = Paths.get("C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\test\\testson\\a");
        // 对文件夹和文件进行遍历
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }


}
