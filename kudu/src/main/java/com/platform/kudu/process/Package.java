package com.platform.kudu.process;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class Package {
    // 基本路径
    private static final String gitCanonicalPath = "platform_kudu/src/main/java/com/platform/kudu/sources/";
    private static final String gitname = "/package.sh";

    public int executeShell(String id, String file, String git) throws IOException {
        File gitfile = new File(gitCanonicalPath);
        String shellbasePath = gitfile.getAbsolutePath();
        String shellname = shellbasePath + gitname;
        String chmodshellname = "chmod u+x " + shellname + " && sh " + shellname + " " + id + " " + git + " " + file;
        System.out.println("shellCommand:" + chmodshellname);
        int success = 0;

        try {
            Process pid = null;
            String[] cmd = {"/bin/sh", "-c", chmodshellname};
            //给shell传递参数
            //String[] cmd = { "/bin/sh", "-c", shellCommand+" paramater" };
            // 执行Shell命令
            pid = Runtime.getRuntime().exec(cmd);
            pid.waitFor();//等待进程结束
            if (!pid.isAlive()) {
                success = 1;
            }
            return success;

        } catch (Exception io) {
            io.printStackTrace();
        }

        return success;
    }

//    public static void main(String[] args) {
//        try {
//            int i = new RunShell().executeShell("100");
//            System.out.println(i);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}