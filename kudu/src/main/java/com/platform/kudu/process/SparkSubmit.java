//package com.platform.kudu.process;
//
//import com.platform.kudu.pojo.Kuduinit;
//import org.hibernate.validator.constraints.URL;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import utils.JsonUtil;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Stream;
//
//import static java.util.stream.Collectors.toList;
//
//@Service
//public class SparkSubmit {
//    @Autowired
//    JsonUtil jsonUtil;
//    // 基本路径
//    private static final String gitCanonicalPath = "platform_kudu/src/main/java/com/platform/kudu/sources/";
//    private static final String gitname = "/spark_submit.sh";
////    private static final List<String> list = Stream.of("master","deploy-mode","class","name","jars","packages","exclude-packages",
////            "repositories","py-files","files","conf","properties-file","driver-memory","driver-java-options","driver-library-path",
////            "driver-class-path","executor-memory","proxy-user","driver-cores","supervise","verbose","total-executor-cores",
////            "executor-core","driver-cores","queue","num-executors").collect(toList());
//
//    public int runSparkSubmit(String id, String jsonparam) throws IOException {
//        Map<String, String> map = jsonUtil.stringToMap(jsonparam);
//        Set<String> strings = map.keySet();
//        StringBuilder stringBuilderdolar = new StringBuilder();
//        StringBuilder stringBuilderParam = new StringBuilder();
//        for (String param : strings) {
//            String paramtrim = param.trim();
//            if (paramtrim.indexOf("jar") != -1) {
//                String sparam = " " + map.get(paramtrim);
//                stringBuilderdolar.append(sparam);
//            }
//
//            if (paramtrim.indexOf("$") != -1) {
//                String sparam = " " + map.get(paramtrim);
//                stringBuilderdolar.append(sparam);
//            }
//            if (paramtrim.indexOf("#") != -1) {
//                String strparam = paramtrim.split("#")[0];
//                String sparam = " --" + strparam + " " + map.get(paramtrim);
//                stringBuilderParam.append(sparam);
//            }
//            String sparam = " --" + paramtrim + " " + map.get(paramtrim);
//            stringBuilderParam.append(sparam);
//        }
//        String sparkSubmitParam = stringBuilderParam.append(stringBuilderdolar).toString();
//        File gitfile = new File(gitCanonicalPath);
//        String shellbasePath = gitfile.getAbsolutePath();
//        String shellname = shellbasePath + gitname;
//        String chmodshellname = "chmod u+x " + shellname + " && sh " + shellname + " " + id + " " + sparkSubmitParam;
//        System.out.println("shellCommand:" + chmodshellname);
//        int success = 0;
//
//        try {
//            Process pid = null;
//            String[] cmd = {"/bin/sh", "-c", chmodshellname};
//            //给shell传递参数
//            //String[] cmd = { "/bin/sh", "-c", shellCommand+" paramater" };
//            // 执行Shell命令
//            pid = Runtime.getRuntime().exec(cmd);
//            pid.waitFor();//等待进程结束
//            if (!pid.isAlive()) {
//                success = 1;
//            }
//            return success;
//
//        } catch (Exception io) {
//            io.printStackTrace();
//        }
//
//        return success;
//    }
//}
