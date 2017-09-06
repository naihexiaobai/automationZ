package test;

import java.io.File;
import java.io.IOException;

/**
 * Created by admin on 2017/7/20.
 */
public class AppTest {

    private static int q = 0x101010;
    private static int w = 0x010101;

    public static void main(String[] qq) {
//       String ss="1&";
//        String ss2="12&133&";
//        System.out.println(ss.split("&").length);
//        System.out.println(ss2.split("&").length);

//        AppTest appTest=new AppTest();
//        AppTest appTest1=appTest;
//        System.out.println(appTest==appTest1);
//        System.out.println(appTest.equals(appTest1));

        //用指数计数法表示float最大数
//        System.out.println(Float.MAX_VALUE);
//        String ss = "asdasmcz";
//        try {
//            String newS = MD5Util.getEncryptedPwd(ss);
//            System.out.println(newS);
//            boolean status = MD5Util.validPassword(ss, newS);
//            System.out.println(status);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(q);
//        System.out.println(w);
//        System.out.println(q & w);
//        System.out.println(q &= w);
//        System.out.println(q | w);
//        System.out.println(q |= w);
//        System.out.println(q ^ w);
//        System.out.println(q ^= w);
//        System.out.println(~q);
//        System.out.println(~w);

//        System.out.println("1".split("\\


//      System.out.println("22.01.22.23".contains("22.01"));
        File directory = new File("");//设定为当前文件夹
        try {
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            System.out.println(directory.getAbsolutePath());//获取绝对路径
        } catch (IOException e) {
            e.printStackTrace();
        }

        File directoryq = new File(".");//设定为当前文件夹
        try {
            System.out.println(directoryq.getCanonicalPath());//获取标准的路径
            System.out.println(directoryq.getAbsolutePath());//获取绝对路径
        } catch (IOException e) {
            e.printStackTrace();
        }

        File directoryq1 = new File("..");//设定为当前文件夹
        try {
            System.out.println(directoryq1.getCanonicalPath());//获取标准的路径
            System.out.println(directoryq1.getAbsolutePath());//获取绝对路径
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
