package test;

import com.wap.util.DBHelper;
import com.wap.util.ReadWriteExcelUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 2017/7/17.
 */
public class Test {

    public static void main(String[] strings) throws IOException {
        String file = "D:\\搜狗高速下载\\自动化配件类.xls";
        String fileName = "自动化配件类.xls";

//        String file = "D:\\搜狗高速下载\\自动化产品类.xls";
//        String fileName = "自动化产品类.xls";
        List<String[]> sss = ReadWriteExcelUtil.readExcel(file, fileName);
        for (int i = 0; i < sss.size(); i++) {
            int len = sss.get(i).length;
            String code = "";
            String shortName = "";
            String fullName = "";
            String fatherCode = "";
            String sp = "";
            String remark = "";
            int at = 0;

            String sql = "";
            if (3 == len) {
                code = sss.get(i)[0];
                shortName = sss.get(i)[1];
                fullName = sss.get(i)[2];
                if (code.split("\\.").length == 0) {
                    fatherCode = "";
                } else {
                    for (int we = 0; we < code.split("\\.").length - 1; we++) {
                        if (we == 0) {
                            fatherCode += code.split("\\.")[we];
                        } else {
                            fatherCode += ("." + code.split("\\.")[we]);
                        }

                    }
                }
                sql = "insert into BaseDats(LevelCode,ShortName,FullName,FatherLevelCode,Specifications,Attribute,Remark)" +
                        " values('" + code + "','" + shortName + "','" + fullName + "','" + fatherCode + "',''," + at + ",'" + remark + "')";
            } else if (len == 5) {
                code = sss.get(i)[0];
                shortName = sss.get(i)[1];
                fullName = sss.get(i)[2];
                fatherCode = "";
                sp = sss.get(i)[3];

                remark = "";
                at = 0;
                if (code.split("\\.").length == 0) {
                    fatherCode = "";
                } else {
                    for (int we = 0; we < code.split("\\.").length - 1; we++) {
                        if (we == 0) {
                            fatherCode += code.split("\\.")[we];
                        } else {
                            fatherCode += ("." + code.split("\\.")[we]);
                        }
                    }
                }
                if (sss.get(i)[4].equals("自制")) {
                    at = 1;
                } else if (sss.get(i)[4].equals("外购")) {
                    at = 2;
                }
                sql = "insert into BaseDats(LevelCode,ShortName,FullName,FatherLevelCode,Specifications,Attribute,Remark)" +
                        " values('" + code + "','" + shortName + "','" + fullName + "','" + fatherCode + "','" + sp + "'," + at + ",'" + remark + "')";
            }
            DBHelper db = new DBHelper();
//        String sql = "insert into BaseDats(LevelCode,ShortName,FullName,FatherLevelCode,Specifications,Attribute,Remark)" +
//                " values('02','自动化产品类','自动化产品类','','',0,'')";
            db.setSql(sql);
            db.executeUpdate();
            System.out.println("-----" + i + "-----");
        }

//        DBHelper db = new DBHelper();
//        String sql = "insert into BaseDats(LevelCode,ShortName,FullName,FatherLevelCode,Specifications,Attribute,Remark)" +
//                " values('02','自动化产品类','自动化产品类','','',0,'')";
//        db.setSql(sql);
//        db.executeUpdate();
    }
}
