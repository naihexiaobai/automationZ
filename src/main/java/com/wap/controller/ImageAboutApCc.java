package com.wap.controller;

import com.alibaba.fastjson.JSONObject;
import com.wap.controller.service.ImageAboutApService;
import com.wap.model.ImageAboutAp;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 2017/8/7.
 */
@Controller()
@RequestMapping("imageAboutApCc")
public class ImageAboutApCc extends PublicFunction {
    @Resource(name = "imageAboutApService")
    private ImageAboutApService imageAboutApService;
    // 保存文件的目录
    private static String PATH_FOLDER = "/";
    // 存放临时文件的目录
    private static String TEMP_FOLDER = "/";

    /**
     * 查
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("selectByAPIdImg")
    @ResponseBody
    public JSONObject selectByAPIdImg(HttpServletRequest request, HttpServletResponse response) {
        String apid = request.getParameter("apid");
        ImageAboutAp imageAboutAp = new ImageAboutAp();
        List<ImageAboutAp> imageAboutAps = new ArrayList<ImageAboutAp>();
        JSONObject jsonObject = new JSONObject();
        if (isStringNull(apid)) {
            return null;
        }
        imageAboutAps = imageAboutApService.selectByApid(Integer.valueOf(apid));
        int q = imageAboutAps.size();
        if (q > 0) {
            imageAboutAp = imageAboutAps.get(0);
            jsonObject = getJSONObjectImage(imageAboutAp, jsonObject);
            return jsonObject;
        }
        return null;
    }

    /**
     * 新增
     *
     * @param request
     * @param response
     * @param apCar_image_up
     * @param apCar_image_down
     * @param apCar_image_left
     * @param apCar_image_right
     * @param apCar_image_front
     * @param apCar_image_behind
     * @param apCar_image_electrics
     * @param apCar_image_machine
     * @return
     * @throws FileUploadException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("addImg")
    @ResponseBody
    public String addImg(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "apCar_image_up", required = false) CommonsMultipartFile apCar_image_up
            , @RequestParam(value = "apCar_image_down", required = false) CommonsMultipartFile apCar_image_down, @RequestParam(value = "apCar_image_left", required = false) CommonsMultipartFile apCar_image_left,
                         @RequestParam(value = "apCar_image_right",required = false) CommonsMultipartFile apCar_image_right
            , @RequestParam(value = "apCar_image_front", required = false) CommonsMultipartFile apCar_image_front, @RequestParam(value = "apCar_image_behind", required = false) CommonsMultipartFile apCar_image_behind,
                         @RequestParam(value = "apcar_image_electrics", required = false) CommonsMultipartFile apCar_image_electrics
            , @RequestParam(value = "apCar_image_machine", required = false) CommonsMultipartFile apCar_image_machine) throws FileUploadException, UnsupportedEncodingException {
        int status = 0;
        //0,此订单图片已关联-修改，1，不存在-新建
        int type = 0;
        String apid = request.getParameter("apid");
        //图片名称
        String name = "";
        ImageAboutAp imageAboutAp = new ImageAboutAp();
        List<ImageAboutAp> imageAboutAps = new ArrayList<ImageAboutAp>();
        if (isStringNull(apid) || isStringNull(apCar_image_electrics.getName()) || isStringNull(apCar_image_machine.getName())) {
            return "false";
        }
        imageAboutAps = imageAboutApService.selectByApid(Integer.valueOf(apid));
        int q = imageAboutAps.size();
        if (q > 0) {
            imageAboutAp = imageAboutAps.get(0);
        } else {
            imageAboutAp.setApId(Integer.valueOf(apid));
            type = 1;
        }
//        <img src='./apimg/1502090463522.jpg'/>  页面显示路径问题
        if (!isStringNull(apCar_image_up.getName()) && apCar_image_up.getSize() > 0) {
            name = write(apCar_image_up);
            imageAboutAp.setImgUp(name);
        }
        if (!isStringNull(apCar_image_down.getName()) && apCar_image_down.getSize() > 0) {
            name = write(apCar_image_down);
            imageAboutAp.setImgDown(name);
        }
        if (!isStringNull(apCar_image_left.getName()) && apCar_image_left.getSize() > 0) {
            name = write(apCar_image_left);
            imageAboutAp.setImgLeft(name);
        }
        if (!isStringNull(apCar_image_right.getName()) && apCar_image_right.getSize() > 0) {
            name = write(apCar_image_right);
            imageAboutAp.setImgRight(name);
        }
        if (!isStringNull(apCar_image_front.getName()) && apCar_image_front.getSize() > 0) {
            name = write(apCar_image_front);
            imageAboutAp.setImgFront(name);
        }
        if (!isStringNull(apCar_image_behind.getName()) && apCar_image_behind.getSize() > 0) {
            name = write(apCar_image_behind);
            imageAboutAp.setImgBehind(name);
        }
        if (!isStringNull(apCar_image_electrics.getName()) && apCar_image_electrics.getSize() > 0) {
            name = write(apCar_image_electrics);
            imageAboutAp.setImgElectrics(name);
        }
        if (!isStringNull(apCar_image_machine.getName()) && apCar_image_machine.getSize() > 0) {
            name = write(apCar_image_machine);
            imageAboutAp.setImgMachine(name);
        }
        if (type == 0) {
            status = imageAboutApService.updateByApidSelective(imageAboutAp);
        } else if (type == 1) {
            status = imageAboutApService.insertSelective(imageAboutAp);
        }
        return "true";
    }

    private String write(CommonsMultipartFile file) {
        String path = "";
        String name = "";
        try {
            File directory = new File("..");//设定为当前文件夹
            try {
                path = directory.getCanonicalPath() + "\\webapps\\automation\\autopage\\autogo\\apimg\\";
//                path = directory.getCanonicalPath() + "\\src\\main\\webapp\\autopage\\autogo\\apimg\\";
            } catch (IOException e) {
                e.printStackTrace();
            }
            name = switchRand() + System.currentTimeMillis() + "." + file.getOriginalFilename().split("\\.")[1];
            //获取输出流
            OutputStream os = new FileOutputStream(path + name);
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is = file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while ((temp = is.read()) != (-1)) {
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    private String switchRand() {
        String result = "";
        Random random = new Random();
        switch (random.nextInt(5)) {
            case 1:
                result = "B";
                break;
            case 0:
                result = "A";
                break;
            case 2:
                result = "C";
                break;
            case 3:
                result = "D";
                break;
            case 4:
                result = "E";
                break;
            default:
                result = "F";
                break;
        }
        return result;
    }

    private FileItem getUploadFileItem(List<FileItem> list) {
        for (FileItem fileItem : list) {
            if (!fileItem.isFormField()) {
                return fileItem;
            }
        }
        return null;
    }

    private String getUploadFileName(FileItem item) {
        // 获取路径名
        String value = item.getName();
        // 索引到最后一个反斜杠
        int start = value.lastIndexOf("/");
        // 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
        String filename = value.substring(start + 1);

        return filename;
    }
}
