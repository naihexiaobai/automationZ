/**
 * 订单查询页,初始化
 */
function initAplist() {
    var htmlMenu = "";
    htmlMenu = "<h1><img src='img/icons/posts.png' alt='' />夕阳西下</h1>" +
        "<div class='bloc'>" +
        "<div class='title'>订单查询</div> " +
        "<div class='content'>";
    htmlMenu += " <table> " +
        " <tr > " +
        "<th><label> 产品名称：<input type='text' name=' required' value=''   id='ap_name' class='ipt' /></label>" +
        " </th>" +
        " <th><label> 创建人：<input type='text' name=' required' id='ap_createuser' value='' class='ipt' /></label>" +
        " </th> " +
        // 状态，0默认，1未提交，2已提交，3审核通过，4审核不通过，5待发货，6已发货，7确认收货，8厂家使用中
        "<th><label> 状态：<select type='text' name=' required' id='ap_status' class='ipt' >" +
        "<option value='' selected>--请选择--</option>" +
        "<option value='0' >默认</option>" +
        "<option value='1' >未提交</option>" +
        "<option value='2' >已提交</option>" +
        "<option value='3' >审核通过</option>" +
        "<option value='4' >审核不通过</option>" +
        "<option value='5' >待发货</option>" +
        "<option value='6' >已发货</option>" +
        "<option value='7' >确认收货</option>" +
        "<option value='8' >厂家使用中</option>" +
        "</select></label>" +
        " </th> " +
        "<th><label> 采购商：<input type='text' name=' required' id='ap_agent' value='' class='ipt' /></label>" +
        " </th> " +
        "<tr><td>  <label>  创建时间：<input type='text' name=' required' id='ap_createtime'   onclick='laydate()'    class='ipt' /> </label></td>" +
        "<td><label> 订单编号：<input type='text' name=' required' id='ap_remark' value='' class='ipt' /></label></td><td></td>" +
        "<td><div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 4px;'  onclick='selectAp(1)'   type='submit' value='查询'></div></td></tr>" +
        "</tr> </table>" +
        "<table id='table_ap_list'></table><div  id='div_page'   class='pagination'></div> </div></div>";
    $("#content").html(htmlMenu);
}

/**
 * 查询多条件
 */
function selectAp(gopage) {
    var name = $("#ap_name").val();
    var createuser = $("#ap_createuser").val();
    var status = $("#ap_status option:selected").val();
    var agent = $("#ap_agent").val();
    var createtime = $("#ap_createtime").val();
    var remark = $("#ap_remark").val();

    $.ajax({
        data: {
            "name": name,
            "status": status,
            "agnet": agent,
            "createuser": createuser,
            "createtime": createtime,
            "gopage": gopage,
            "remark": remark
        },
        type: "post",
        dataType: "json",
        url: "http://" + url + ":8888/automation/autoProductCc/selectByAutoproduct",
        success: function (datas) {
            if (datas == null) {
                alert("无数据");
            } else {
                shouSelectPage(datas, gopage);
            }
        }
    });
    // if (!isStringNull(name) || !isStringNull(createuser) || !isStringNull(status) || !isStringNull(agent) || !isStringNull(createtime)) {
    //
    // } else {
    //     alert("查询条件不能全部为空值！");
    // }
}

/**
 * 查询展示页
 * @param data
 * @param nowpage
 */
function shouSelectPage(data, nowpage) {
    if (nowpage < 1) {
        nowpage = 1;
    }
    // var nowpage=datas.nowpage;
    $("#table_ap_list").empty();
    $("#table_ap_list").append("<thead> <tr> <th>序号</th> <th>订单编号</th><th>产品名称</th> <th>产品型号</th> <th>采购商</th><th>创建时间</th> <th> 创建人</th><th> 状态</th><th> 操作</th> </tr> </thead> ");
    var bodys = "";
    for (var i = 0; i < data.length; i++) {
        bodys = "<tr>" +
            "<td>" + data[i].id + "</td>" +
            "<td>" + data[i].remark + "</td>" +
            "<td>" + data[i].name + "</td>" +
            "<td>" + data[i].specifications + "</td>" +
            "<td>" + data[i].agent + "</td>" +
            "<td>" + data[i].createtime + "</td>" +
            "<td>" + data[i].createuser + "</td>" +
            "<td>" + statusToString(data[i].status) + "</td>" +
            "<td><a onclick=lookDetail('" + data[i].id + "') title='查看'><img src='img/icons/actions/edit.png'  /></a></td>" +
            "</tr>";
        $("#table_ap_list").append(bodys);
    }
    //页数
    var page = "<a onclick=selectAp(" + (parseInt(nowpage) - 1) + ")  class='prev'><img src='img/icons/arrow-left.png'> </a>" +
        "<a   class='current'>" + nowpage + "</a>" +
        "<a onclick=selectAp(" + (parseInt(nowpage) + 1) + ") class='next'>  <img src='img/icons/arrow-right.png'></a>";
    $("#div_page").html(page);
}

/**
 * 状态转换
 * @param status
 * @returns {*}
 */
function statusToString(status) {
    var str = "";
    if (status == 0) {
        return str = "默认";
    } else if (status == 1) {
        return str = "未提交";
    }
    else if (status == 2) {
        return str = "已提交";
    }
    else if (status == 3) {
        return str = "审核通过";
    }
    else if (status == 4) {
        return str = "审核不通过";
    }
    else if (status == 5) {
        return str = "待发货";
    }
    else if (status == 6) {
        return str = "已发货";
    }
    else if (status == 7) {
        return str = "确认收货";
    }
    else if (status == 8) {
        return str = "厂家使用中";
    }
}

/**
 * 查看订单详情__查询
 * @param id
 */
function lookDetail(apid) {
    $.ajax({
        url: "http://" + url + ":8888/automation/fitingListCc/selectByAPId",
        data: {"apid": apid},
        dataType: "json",
        type: "post",
        success: function (datas) {
            if (datas == null) {
                alert("该产品目录下无数据");
            }
            showDBDetail(datas, apid);
        }
    });
}

/**
 * 展示订单详情
 * @param datas
 * @param apid
 */
function showDBDetail(datas, apid) {
    var htmlMenu = "";
    htmlMenu = "<h1><img src='img/icons/posts.png' alt='' />断肠人在天涯</h1>" +
        "<div class='bloc'>" +
        "<div class='title'>订单产品配件详情</div> " +
        "<div class='content'>";
    var bodys = "";
    for (var i = 0; i < datas.length; i++) {
        bodys += "<tr> <th>" + datas[i].apid + "</th> <th>" + datas[i].bdname + "</th> " +
            "<th>" + datas[i].bdsp + "</th> " +
            "<th><select id='fl_num'  onchange=editFl('" + datas[i].id + "','" + apid + "')>" +
            "<option value='" + datas[i].number + "' selected='selected'>" + datas[i].number + "</option>" +
            "<option value='1' >1</option>" +
            "<option value='2' >2</option>" +
            "<option value='3' >3</option>" +
            "<option value='4' >4</option>" +
            "<option value='5' >5</option>" +
            "</select></th>" +
            "<th>" + datas[i].createuser + "</th><th>" + datas[i].createtime + "</th>" +
            "<th>" + datas[i].status + "</th> " +
            "<th>   &nbsp;" +
            "<a   title='删除'><img src='img/icons/actions/delete.png' alt='' onclick=deleteFl('" + datas[i].id + "','" + apid + "')  /></a></th></tr>";
    }
    htmlMenu += " <table>" +
        "<tr><td><div class='submit'> <input    onclick=initAddBD('" + apid + "')  style='margin-top:2px ;margin-left: 5px'   type='button' value='新增配件'></div></td>" +
        "<td><label> 修改订单状态：<select type='text' name=' required' id='ap_status_change'  style='margin-top:5px '  onchange=changeApSttatus('" + apid + "')    class='ipt' >" +
        "<option value='999' selected='selected'>--请选择--</option>" +
        "<option value='0' >默认</option>" +
        "<option value='1' >未提交</option>" +
        "<option value='2' >已提交</option>" +
        // "<option value='3' >审核通过</option>" +
        // "<option value='4' >审核不通过</option>" +
        "<option value='5' >待发货</option>" +
        "<option value='6' >已发货</option>" +
        "<option value='7' >确认收货</option>" +
        "<option value='8' >厂家使用中</option>" +
        "</select></label></td>" +
        "<td><label> 按配件类别查看：<select type='text' name=' required' id='ap_classes_change'  onchange=classes_change('" + apid + "')   style='margin-top:5px ' class='ipt' >" +
        "<option value=''>--请选择--</option><option value='22.01'>机械类</option><option value='22.02'>电器类</option>" +
        "<option value='22.03'>系统类</option><option value='22.90'>研发配件</option></select></td>" +
        "<td><div class='submit'> <input  style='margin-top:2px ;margin-left: 5px'  onclick=searchImg('" + apid + "')    type='button' value='查看/新增照片'></div></td>" +
        "</tr></table>" +
        "<form  id='form_imgs_f'  action='http://" + url + ":8888/automation/imageAboutApCc/addImg' method='post' enctype='multipart/form-data'>" +
        "<table id='ap_imgs'></table><input value='" + apid + "' name='apid' type='hidden' />" +
        "</form>" +
        "<table id='flAddBd'></table><table><thead><tr> <th>订单编号</th> <th>配件名称</th> <th>配件型号</th> <th>数量</th><th> 创建人</th><th>创建时间</th><th>状态</th> <th> 操作</th> </tr></thead>" + bodys + " </table>" +
        " </div></div>";
    $("#content").html(htmlMenu);
}

/**
 * 查取数据-img
 * @param apid
 */
function searchImg(apid) {
    $.ajax({
        data: {"apid": apid},
        type: "post",
        url: "http://" + url + ":8888/automation/imageAboutApCc/selectByAPIdImg",
        dataType: "json",
        success: function (datas) {
            showIUImageTable(datas, apid);
        }
    });
}

/**
 * 渲染照片处理表格
 */
function showIUImageTable(datas, apidzz) {
    var apid = "";
    var id = "";
    var up = "";
    var down = "";
    var left = "";
    var right = "";
    var front = "";
    var behind = "";
    var electrics = "";
    var machine = "";
    var uphtml = "";
    var downhtml = "";
    var lefthtml = "";
    var righthtml = "";
    var fronthtml = "";
    var behindhtml = "";
    var electricshtml = "";
    var machinehtml = "";
    if (isStringNull(datas)) {

    } else {
        apid = datas.apid;
        id = datas.id;
        up = datas.up;
        down = datas.down;
        left = datas.left;
        right = datas.right;
        front = datas.front;
        behind = datas.behind;
        electrics = datas.electrics;
        machine = datas.machine;
    }
    if (!isStringNull(up)) {
        uphtml = "<img  id='img_up'  style='width: 60px;height: 35px;'   data-original='./apimg/" + up + "' src='./apimg/" + up + "' alt='图片1' />";
    }
    if (!isStringNull(down)) {
        downhtml = "<img  id='img_down'  style='width: 60px;height: 35px;' data-original='./apimg/" + down + "' src='./apimg/" + down + "' alt='图片2' />";
    }
    if (!isStringNull(left)) {
        lefthtml = "&nbsp;<img  id='img_left' style='width: 60px;height: 35px;'  data-original='./apimg/" + left + "' src='./apimg/" + left + "' alt='图片3' />";
    }
    if (!isStringNull(right)) {
        righthtml = "<img  id='img_right' style='width: 60px;height: 35px;'  data-original='./apimg/" + right + "' src='./apimg/" + right + "' alt='图片4' />";
    }
    if (!isStringNull(front)) {
        fronthtml = "&nbsp;<img  id='img_front' style='width: 60px;height: 35px;'   data-original='./apimg/" + front + "' src='./apimg/" + front + "' alt='图片5' />";
    }
    if (!isStringNull(behind)) {
        behindhtml = "<img  id='img_behind'  style='width: 60px;height: 35px;' data-original='./apimg/" + behind + "' src='./apimg/" + behind + "' alt='图片6' />";
    }
    if (!isStringNull(electrics)) {
        // electricshtml = "&nbsp;<img  id='img_electrics' style='width: 60px;height: 35px;'  data-original='./apimg/" + electrics + "' src='./apimg/" + electrics + "' alt='图片7' />";
        electricshtml = "<a   download='" + electrics + "' href='http://" + url + ":8888/automation/autopage/autogo/apimg/" + electrics + "'>电器图pdf下载</a> ";
    }
    if (!isStringNull(machine)) {
        // machinehtml = "<img  id='img_machine' style='width: 60px;height: 35px;'  data-original='./apimg/" + machine + "' src='./apimg/" + machine + "' alt='图片8' />";
        machinehtml = "<a  download='" + machine + "' href='http://" + url + ":8888/automation/autopage/autogo/apimg/" + machine + "'>机械图pdf下载</a>";
    }
    var html = " " +
        "<tr><td><label style='margin-top:5px ;margin-left: 5px;margin-bottom: 3px;'   > 穿梭车相关照片上传</label></td><td></td><td></td><td></td></tr>" +
        "<tr>" +
        "<td><label style='margin-top:5px ;margin-left: 5px;margin-bottom: 3px;'> 车体上面：</label></td>" +
        //<img src='./apimg/2017-08-08.jpg'/>
        " <td><input id='apCar_image_up' name='apCar_image_up' type='file' /> " + uphtml + "</td>" +
        "<td><label style='margin-top:5px ;margin-left: 5px;margin-bottom: 3px;'> 车体下面：</label></td>" +
        "<td><input id='apCar_image_down' name='apCar_image_down' type='file' />" + downhtml + "</td></tr>" +
        "<tr>" +
        "<td><label style='margin-top:5px ;margin-left: 5px;margin-bottom: 3px;'>  车体左面：</label></td>" +
        "<td><input type='file' id='apCar_image_left' name='apCar_image_left'/>" + lefthtml + "</td>" +
        "<td><label style='margin-top:5px ;margin-left: 5px;margin-bottom: 3px;'> 车体右面：</label></td>" +
        "<td><input type='file' id='apCar_image_right' name='apCar_image_right'/>" + righthtml + "</td></tr>" +
        "<tr>" +
        "<td><label style='margin-top:5px ;margin-left: 5px;margin-bottom: 3px;'> 车体前面：</label></td>" +
        "<td><input type='file' id='apCar_image_front' name='apCar_image_front'/>" + fronthtml + "</td>" +
        "<td><label style='margin-top:5px ;margin-left: 5px;margin-bottom: 3px;'> 车体后面：</label></td>" +
        "<td><input type='file' id='apCar_image_behind' name='apCar_image_behind'/>" + behindhtml + "</td></tr>" +
        "<tr><td><label style='margin-top:5px ;margin-left: 5px;margin-bottom: 3px;' > <span style='color: #f21721;margin-top:8px ;margin-left: 5px;margin-bottom: 3px;'>*</span>电器图：</label></td>" +
        "<td><input type='file' id='apCar_image_electrics' name='apcar_image_electrics'/>" + electricshtml + "</td>" +
        "<td><label style='margin-top:5px ;margin-left: 5px;margin-bottom: 3px;' > <span style='color: #f21721;margin-top:8px ;margin-left: 5px;margin-bottom: 3px;'>*</span>机械图：</label></td>" +
        "<td><input type='file' id='apCar_image_machine' name='apCar_image_machine'/>" + machinehtml + "</td>" +
        "</tr>" +
        "<tr><td><div class='submit'> <input   style='margin-top:2px ;margin-left: 5px;margin-bottom: 3px;'    onclick=submitImgAp('" + apidzz + "')    type='button' value='保存图片' ></div></td><td></td><td></td><td></td></tr> " +
        " ";
    $("#ap_imgs").append(html);
    if (!isStringNull(up)) {
        var viewer = new Viewer(document.getElementById('img_up'), {
            url: 'data-original'
        });
    }
    if (!isStringNull(down)) {
        var viewer1 = new Viewer(document.getElementById('img_down'), {
            url: 'data-original'
        });
    }
    if (!isStringNull(left)) {
        var viewer2 = new Viewer(document.getElementById('img_left'), {
            url: 'data-original'
        });
    }
    if (!isStringNull(right)) {
        var viewer3 = new Viewer(document.getElementById('img_right'), {
            url: 'data-original'
        });
    }
    if (!isStringNull(front)) {
        var viewer4 = new Viewer(document.getElementById('img_front'), {
            url: 'data-original'
        });
    }
    if (!isStringNull(behind)) {
        var viewer5 = new Viewer(document.getElementById('img_behind'), {
            url: 'data-original'
        });
    }
    // if (!isStringNull(electrics)) {
    //     var viewer6 = new Viewer(document.getElementById('img_electrics'), {
    //         url: 'data-original'
    //     });
    // }
    // if (!isStringNull(machine)) {
    //     var viewer7 = new Viewer(document.getElementById('img_machine'), {
    //         url: 'data-original'
    //     });
    // }
}


/**
 *
 */
function submitImgAp(apidzz) {
    var form = new FormData(document.getElementById("form_imgs_f"));
    var str = "";
    str = $("#apCar_image_electrics").val();
    if (!isStringNull(str)) {
        var arr = str.split('\\');//注split可以用字符或字符串分割
        var my = arr[arr.length - 1];//这就是要取得的图片名称
        var mmm = my.split("\\.");
        if (mmm[mmm.length - 1] != "dwg" || mmm[mmm.length - 1] != "pdf") {
            alert("只能选择pdf或者dwg格式文件");
            return;
        }
    }

    str = $("#apCar_image_machine").val();
    if (!isStringNull(str)) {
        var arr = str.split('\\');//注split可以用字符或字符串分割
        var my = arr[arr.length - 1];//这就是要取得的图片名称
        var mmm = my.split("\\.");
        if (mmm[mmm.length - 1] != "dwg" || mmm[mmm.length - 1] != "pdf") {
            alert("只能选择pdf或者dwg格式文件");
            return;
        }
    }

    $.ajax({
        url: "http://" + url + ":8888/automation/imageAboutApCc/addImg",
        type: "post",
        data: form,
        cache: false,
        processData: false,
        contentType: false,
        success: function (data) {
            alert("操作成功！");
            lookDetail(apidzz);
        },
        error: function (e) {
            alert("网络错误，请重试！！");
        }
    });
}

/**
 * 按类别进行查看
 * @param apid
 */
function classes_change(apid) {
    var fatherLevelCode = $("#ap_classes_change option:selected").val();
    $.ajax({
        url: "http://" + url + ":8888/automation/fitingListCc/selectByAPIdClasses",
        data: {"apid": apid, "fatherLevelCode": fatherLevelCode},
        dataType: "json",
        type: "post",
        success: function (datas) {
            showDBDetail(datas, apid);
        }
    });
}


/**
 * 修改订单状态
 * @param apid
 */
function changeApSttatus(apid) {
    var status = $("#ap_status_change option:selected").val();
    $.ajax({
        url: "http://" + url + ":8888/automation/autoProductCc/changeApSttatus",
        data: {"id": apid, "status": status},
        dataType: "text",
        type: "post",
        success: function (datas) {
            if (datas == "true") {
                alert("修改成功！");
                lookDetail(apid, status);
            } else {
                alertFaile();
            }
        }
    });
}

/**
 * fl
 * 修改此条数据----修改数量
 */
function editFl(fid, apid) {
    var number = $("#fl_num option:selected").val();
    if (confirm("确认修改其数量？")) {
        $.ajax({
            data: {"id": fid, "number": number},
            type: "post",
            dataType: "text",
            url: "http://" + url + ":8888/automation/fitingListCc/updateFlById",
            success: function (datas) {
                if (datas == "true") {
                    lookDetail(apid);
                } else {
                    alertFaile();
                }
            }
        });
    }
}

/**
 * fl
 * 删除此条数据
 */
function deleteFl(fid, apid) {
    if (confirm("确认删除此条信息?")) {
        $.ajax({
            url: "http://" + url + ":8888/automation/fitingListCc/deleteFlById",
            data: {"id": fid},
            type: "post",
            dataType: "text",
            success: function (datas) {
                if (datas == "true") {
                    lookDetail(apid);
                } else {
                    alertFaile();
                }
            }
        });
    }

}

/**
 * 新增配件————数据查询
 * @param apid
 */
function initAddBD(apid) {
    var fatherlevelcode = "";
    $.ajax({
        url: "http://" + url + ":8888/automation/baseDatsCc/getDBByLevelCode",
        type: "post",
        data: {"fathercode": fatherlevelcode},
        dataType: "json",
        success: function (datas) {
            if (datas == null) {
                alertFaile();
            } else {
                showAddApbd(datas, apid);
            }
        }
    });
}

/**
 * 渲染新增bdToFl页面
 * @param datas
 * @param apid
 */
function showAddApbd(datas, apid) {
    var option = "<option value=' '  selected='selected'>--请选择--</option>";
    for (var i = 0; i < datas.length; i++) {
        option += "<option value='" + datas[i].levelcode + "'>" + datas[i].shortname + "</option>";
    }
    var bodys = "<tr><td  ><label> 一级·目:<select name='select'   onchange=getDBsByFc('db_checked_1','db_checked_2')   class='ipt' id='db_checked_1'>" + option + "</select></label></td>" +
        " <td  ><label  >二级·目 :<select name='select' class='ipt' id='db_checked_2' onchange=getDBsByFc('db_checked_2','db_checked_3') ><option value=''> --请选择--</option></select></label></td>" +
        " <td  ><label  >三级·目 :<select name='select' class='ipt' id='db_checked_3' onchange=getDBsByFc('db_checked_3','db_checked_4') ><option value=''> --请选择--</option></select></label></td>" +
        " " +
        " </tr><tr><td  ><label  >四级·目 :<select name='select' class='ipt' id='db_checked_4'  ><option value=''> --请选择--</option></select></label></td>" +
        "<td><label >数量:<input type='text'  class='ipt'  id='fl_bd_num' /></label></td>" +
        "<td><div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 5px;'  onclick=AddBDtoFL('" + apid + "')    type='submit' value='添加'></div></td> </tr>";
    // addFLbd
    $("#flAddBd").append(bodys);
}

/**
 * 持久化保存————增加配件到订单
 * @param apid
 * @constructor
 */
function AddBDtoFL(apid) {
    var levelcode = $("#db_checked_4 option:selected").val();
    var number = $("#fl_bd_num").val();
    $.ajax({
        url: "http://" + url + ":8888/automation/fitingListCc/addBdToFl",
        type: "post",
        data: {"apid": apid, "levelcode": levelcode, "number": number},
        dataType: "text",
        success: function (datas) {
            if (datas == "true") {
                lookDetail(apid);
            } else {
                alertFaile();
            }
        }
    });
}
/**
 *订单新增页___渲染
 */
function initAddjsp() {
    $.ajax({
        data: {"levelcode": "02.01.%.%"},
        dataType: "json",
        url: "http://" + url + ":8888/automation/baseDatsCc/getDBLikeLevelCode",
        type: "post",
        success: function (datas) {
            showAddJSP(datas);
        }
    });

}

function showAddJSP(datas) {
    var htmlMenu = "";
    // var options = "";
    var options1 = "<option selected='selected'>--请选择--</option>";
    for (var i = 0; i < datas.length; i++) {
        // options += ("<option value='" + datas[i].fullname + "'>" + datas[i].shortname + "," + datas[i].specifications + "</option>");
        options1 += "<option value='" + datas[i].shortname + "'>" + datas[i].shortname + "&" + datas[i].specifications + "</option>";
    }
    htmlMenu = "<h1><img src='img/icons/posts.png' alt='' />断肠人在天涯</h1>" +
        "<div class='bloc'>" +
        "<div class='title'>订单新增</div> " +
        "<div class='content'>";
    htmlMenu += " <div class='input'><label for='input1'>订单编号</label><input type='text' id='ap_compentid'   value=''  /> </div> ";
    htmlMenu += " <div class='input'><label for='input1'>客户名称</label><input type='text' id='ap_agent'   value=''  /> </div> ";
    htmlMenu += " <div class='input'><label for='input1'>产品名称<select name='select' class='ipt'  onchange=inputToSp()  id='ap_name'>" + options1 + "</select> </div>";
    htmlMenu += " <div class='input'><label for='input1'>产品型号</label>" +
        // "<select name='select' class='ipt'id='ap_sp'>" + options + "</select> " +
        "<input type='text' id='ap_sp'  readonly='readonly'  value=''  />" +
        "</div> ";
    htmlMenu += "<div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 5px;'   onclick=toAddUs()  type='submit' value='保存'></div>" +
        "</div></div>";
    $("#content").html(htmlMenu);
}

/**
 * 自动填充型号
 */
function inputToSp() {
    var fs = $("#ap_name option:selected").text();
    var strs = new Array();
    strs = fs.split("&");
    $("#ap_sp").val(strs[1]);
}

/**
 * 订单新增__toC
 */
function toAddUs() {
    var compentid = $("#ap_compentid").val();
    var name = $("#ap_name option:selected").val();
    var agent = $("#ap_agent").val();
    var specifications = $("#ap_sp").val();
    if (isStringNull(name) || isStringNull(agent) || isStringNull(specifications) || isStringNull(compentid)) {
        alert("参数中不能为空！！");
        return;
    }
    $.ajax({
        url: "http://" + url + ":8888/automation/autoProductCc/apAdd",
        type: "post",
        data: {"name": name, "agent": agent, "specifications": specifications, "compentid": compentid},
        dataType: "text",
        success: function (datas) {
            if (datas == "true") {
                initAplist();
            } else {
                alert("新增出错！");
            }
        }
    });
}

/**
 * 产品配件添加页
 */
function initFight(datas) {
    var fatherlevelcode = "";
    $.ajax({
        url: "http://" + url + ":8888/automation/baseDatsCc/getDBByLevelCode",
        type: "post",
        data: {"fathercode": fatherlevelcode},
        dataType: "json",
        success: function (datas) {
            if (datas == null) {
                alertFaile();
            } else {
                showPageBD(datas);
            }
        }
    });
}

/**
 * 订单审核
 */
function initqueryAplist() {
    var htmlMenu = "";
    htmlMenu = "<h1><img src='img/icons/posts.png' alt='' />一杯清酒斗十千</h1>" +
        "<div class='bloc'>" +
        "<div class='title'>订单审核</div> " +
        "<div class='content'>";
    htmlMenu += " <table> " +
        " <tr > " +
        "<th><label> 产品名称：<input type='text' name=' required' value=''   id='ap_name' class='ipt' /></label>" +
        " </th>" +
        " <th><label> 创建人：<input type='text' name=' required' id='ap_createuser' value='' class='ipt' /></label>" +
        " </th> " +
        // 状态，0默认，1未提交，2已提交，3审核通过，4审核不通过，5待发货，6已发货，7确认收货，8厂家使用中
        "<th><label> 状态：<select type='text' name=' required' id='ap_status' class='ipt' >" +
        "<option value='0' >默认</option>" +
        "<option value='1' >未提交</option>" +
        "<option value='2' selected >已提交</option>" +
        "<option value='3' >审核通过</option>" +
        "<option value='4' >审核不通过</option>" +
        "<option value='5' >待发货</option>" +
        "<option value='6' >已发货</option>" +
        "<option value='7' >确认收货</option>" +
        "<option value='8' >厂家使用中</option>" +
        "</select></label>" +
        " </th> " +
        "<th><label> 采购商：<input type='text' name=' required' id='ap_agent' value='' class='ipt' /></label>" +
        " </th> " +
        "<tr><td>  <label>  创建时间：<input type='text' name=' required' id='ap_createtime'   onclick='laydate()'    class='ipt' /> </label></td><td>   </td><td></td>" +
        "<td><div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 4px;'  onclick='selectApQuery(1)'   type='submit' value='查询'></div></td></tr>" +
        "</tr> </table>" +
        "<table id='table_ap_list'></table><div  id='div_page'   class='pagination'></div> </div></div>";
    $("#content").html(htmlMenu);

    selectApQuery(1);
}

/**
 * 查询多条件
 */
function selectApQuery(gopage) {
    var name = $("#ap_name").val();
    var createuser = $("#ap_createuser").val();
    var status = $("#ap_status option:selected").val();
    var agent = $("#ap_agent").val();
    var createtime = $("#ap_createtime").val();
    if (!isStringNull(name) || !isStringNull(createuser) || !isStringNull(status) || !isStringNull(agent) || !isStringNull(createtime)) {
        $.ajax({
            data: {
                "name": name,
                "status": status,
                "agnet": agent,
                "createuser": createuser,
                "createtime": createtime,
                "gopage": gopage
            },
            type: "post",
            dataType: "json",
            url: "http://" + url + ":8888/automation/autoProductCc/selectByAutoproduct",
            success: function (datas) {
                if (datas == null) {
                    alert("无数据");
                } else {
                    shouSelectPageQuery(datas, gopage);
                }
            }
        });
    } else {
        alert("查询条件不能全部为空值！");
    }
}


/**
 * 查询展示页
 * @param data
 * @param nowpage
 */
function shouSelectPageQuery(data, nowpage) {
    if (nowpage < 1) {
        nowpage = 1;
    }
    // var nowpage=datas.nowpage;
    $("#table_ap_list").empty();
    $("#table_ap_list").append("<thead> <tr> <th>编号</th> <th>产品名称</th> <th>产品型号</th> <th>采购商</th><th>创建时间</th> <th> 创建人</th><th> 状态</th><th> 操作</th> </tr> </thead> ");
    var bodys = "";
    for (var i = 0; i < data.length; i++) {
        bodys = "<tr>" +
            "<td>" + data[i].id + "</td>" +
            "<td>" + data[i].name + "</td>" +
            "<td>" + data[i].specifications + "</td>" +
            "<td>" + data[i].agent + "</td>" +
            "<td>" + data[i].createtime + "</td>" +
            "<td>" + data[i].createuser + "</td>" +
            "<td>" + statusToString(data[i].status) + "</td>" +
            "<td><a onclick=updateApStatus('" + data[i].id + "',1) title='通过'><img src='img/icons/button-check.png' style='padding:5px;' /></a>&nbsp;" +
            "<a onclick=updateApStatus('" + data[i].id + "',2) title='不通过'><img src='img/icons/stop.png' style='padding:5px;' /></a></td>" +
            "</tr>";
        $("#table_ap_list").append(bodys);
    }
    //页数
    var page = "<a onclick=selectApQuery(" + (parseInt(nowpage) - 1) + ")  class='prev'><img src='img/icons/arrow-left.png'> </a>" +
        "<a   class='current'>" + nowpage + "</a>" +
        "<a onclick=selectApQuery(" + (parseInt(nowpage) + 1) + ") class='next'>  <img src='img/icons/arrow-right.png'></a>";
    $("#div_page").html(page);
}

/**
 * 修改状态
 * @param id
 * @param type
 */
function updateApStatus(id, type) {
    $.ajax({
        url: "http://" + url + ":8888/automation/autoProductCc/updateApStatus",
        data: {"id": id, "type": type},
        dataType: "text",
        type: "post",
        success: function (datas) {
            if (datas == "true") {
                initAplist();
            } else {
                alertFaile();
            }
        }
    });
}
