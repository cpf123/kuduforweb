import javafx.scene.shape.TriangleMesh;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.TimeZone;

public class test {


    public static void main(String[] args) {
//        String str = "{'token': 'A0193-103AC-VV224-12334-45134','name': '小白','avatar': 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'}";
//        JSONObject aa=null;
//
//        {
//            try {
//                aa = new JSONObject(str);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(aa.toString());
//
//        System.out.println("原时间 " + new Date());
//
//        TimeZone time = TimeZone.getTimeZone("ETC/GMT-8");
//
//        TimeZone.setDefault(time);
//
//        System.out.println("修改后时间 " + new Date());

        String str = "aa  d  d s ";
        String[] s = str.trim().split("\\s+");
        for (String a:s) {
            System.out.println(a);
        }
    }
}
