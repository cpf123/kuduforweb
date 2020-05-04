package com.platform.kudu.process;


import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 实时读取日志文件
 */
@Service
public class LogReader {
    //文件读取指针游标
    private static long pointer = 0;
    private static boolean flag = false;

//    public static void main(String[] args) {
//        String id = "1113742857937752064";
//        String s = readLog(id);
//        System.out.println(s);
//    }

    public static String readLog(String id) {
        String str = "cd ~ && pwd";
        String[] cmd = {"/bin/sh", "-c", str};
        Process pid = null;
        BufferedReader input = null;
        try {
            pid = Runtime.getRuntime().exec(cmd);
            input = new BufferedReader(new InputStreamReader(pid.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
//                System.out.println(line);
                String basepath = line + "/tmp/";
                String path = basepath + id + ".log";
                return useBufferIStream(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return "没有日志";
    }

    // 使用BufferedInputStream
    private static String useBufferIStream(String path) {
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] buffer = new byte[Integer.parseInt(Long.toString(file.length()))];
            int cnt = 0;
            while ((cnt = bis.read(buffer)) != -1) {
                String log = new String(buffer, 0, cnt);
                //log 过滤
                String s = log.replaceAll("\\\u001B", "")
                        .replaceAll("\\[0m", "")
                        .replaceAll("\\[32m", "")
                        .replaceAll("\\[33m", "")
                        .replaceAll("\\[2K", "")
                        .replaceAll("\\[A", "")
                        .replaceAll("\\r", "");
                return s;
            }

            bis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "没有日志";
    }

//    public String monitorLog(String id) {
//        String path = basepath + id + ".log";
//        while (true) {
////            System.out.println("pointer:"+pointer);
//            randomRead(path);
//            try {
//                //停顿1秒
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * 读取文件的方法 实时监控 弃用
     *
     * @param path 文件路径
     **/
    private String randomRead(String path) {
        try {
            File file = new File(path);
            if (file == null) {
                System.out.println("文件不存在");
                return "文件不存在";
            }
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            //获取RandomAccessFile对象文件指针的位置，初始位置是0
//            System.out.println("RandomAccessFile文件指针的初始位置:"+raf.getFilePointer());
            raf.seek(pointer);//移动文件指针位置
            String line = null;
            //循环读取
            while ((line = raf.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                //打印读取的内容,并将字节转为字符串输入，做转码处理，要不然中文会乱码
//                line = new String(line.getBytes("ISO-8859-1"), "gb2312");
//                System.out.println(dateFormat.format(new Date()) + " : " + line);
//                System.out.println(line);
                return line;
            }
            //文件读取完毕后，将文件指针游标设置为当前指针位置 。
            pointer = raf.getFilePointer();

        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}