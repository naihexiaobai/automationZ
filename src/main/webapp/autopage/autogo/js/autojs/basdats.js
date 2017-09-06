/**
 * 初始化基础信息页--查询必要数据
 */
function initBD() {
    var fatherlevelcode = "";
    $.ajax({
        url: "http://"+url+":8888/automation/baseDatsCc/getDBByLevelCode",
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
 * 初始化查询页面
 * @param datas
 */
function showPageBD(datas) {
    var htmlMenu = "";
    var option = "<option value=' '  selected='selected'>--请选择--</option>";
    for (var i = 0; i < datas.length; i++) {
        option += "<option value='" + datas[i].levelcode + "'>" + datas[i].shortname + "</option>";
    }
    var htmlMenu = "<h1><img src='img/icons/posts.png' alt='' />念去去</h1><div class='bloc'><div class='title'>自动化设备基础信息</div><div    class='content'>" +
        " <table> " +
        // "<thead id='name_condition' style='display: block'> " +
        " <tr > " +
        // "<th><input id='wlmsinput' name='maktMaktx' list='wlmslist' value='111'/></th>"+
        "<th><label><font color='#ff0000'></font>产品名称(简)：<input type='text' name=' required' value=''   list='c_n_s_list'  onkeyup=javascript:getDataList('db_shortname','c_n_s_list',1)   id='db_shortname' class='ipt' /></label>" +
        "<datalist id='c_n_s_list'> </datalist></th>" +
        " <th><label><font color='#ff0000'></font>产品名称(全)：<input type='text' name=' required' id='db_fullname'  onkeyup=javascript:getDataList('db_fullname','c_n_f_list',2)  list='c_n_f_list'  value='' class='ipt' /></label>" +
        "<datalist id='c_n_f_list'> </datalist></th> " +
        "<th><label><font color='#ff0000'></font>产品型号：<input type='text' name=' required' id='db_sp'  list='c_n_sp_list'   onkeyup=javascript:getDataList('db_sp','c_n_sp_list',3)   value='' class='ipt' /></label>" +
        "<datalist id='c_n_sp_list'> </datalist></th> " +
        "<th><div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 4px;'    onclick='selectByCondition(1)'    type='submit' value='名称查询'></div></th>" +
        "</tr>" +
        // " </thead>" +
        // "<thead id='menu_condition'  style='display: none'> " +
        "<tr >" +
        "<td> <label><font color='#ff0000'></font>类别查询三级目录： 一:<select name='select'   onchange=getDBsByFc('db_checked_1','db_checked_2')   class='ipt' id='db_checked_1'>" + option + "</select></label></td>" +
        "<td><label><font color='#ff0000'></font>  二:<select name='select' class='ipt' id='db_checked_2' onchange=getDBsByFc('db_checked_2','db_checked_3') ><option value=''> --请选择--</option></select></label></td>" +
        "<td><label><font color='#ff0000'></font>  三:<select name='select' class='ipt' id='db_checked_3'  ><option value=''> --请选择--</option></select></label></td>" +
        "<td><div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 4px;'    onclick='selectByCondition(2)'    type='submit' value='类目查询'></div></td>" +
        "</tr>" +
        // "</thead> " +
        // "<tr>" +
        // "<td><div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 4px;'    onclick='selectByCondition()'    type='submit' value='查询'></div> </td>" +
        // // "<td><div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 4px;'    onclick='changeSelect()'    type='submit' value='切换查询方式'></div></td>" +
        // "<td></td><td></td>" +
        // "</tr>" +
        "</table><table id='dblist'  ></table></div>";
    $("#content").html(htmlMenu);
}

/**
 * 根据条件查询
 */
function selectByCondition(type) {
    //全称
    var fullname = $("#db_fullname").val();
    //型号
    var db_spq = $("#db_sp").val();
    //简称
    var db_shortname = $("#db_shortname").val();
    //类别目录
    var flevelcode = $("#db_checked_3 option:selected").val();
    var i = 0;
    if (!isStringNull(fullname)) {
        i++;
    }
    if (!isStringNull(db_shortname)) {
        i++;
    }
    if (!isStringNull(db_spq)) {
        i++;
    }
    if (i >= 2 && type == 1) {
        alert("名称查询只能输入其中一个条件");
    } else {
        $.ajax({
            data: {
                "fullname": fullname,
                "db_spq": db_spq,
                "db_shortname": db_shortname,
                "flevelcode": flevelcode,
                "type": type
            },
            url: "http://"+url+":8888/automation/baseDatsCc/getDBBySSFM",
            dataType: "json",
            type: "post",
            success: function (datas) {
                showList(datas);
            }

        });
    }
}

/**
 * 渲染页面数据----查询结果
 * @param datas
 */
function showList(datas) {
    $("#dblist").html("");
    var htmllist = "<thead> <tr> <th>ID</th> <th>简称</th> <th>全称</th> <th>型号</th></tr> </thead>";
    var bodys = "";
    for (var i = 0; i < datas.length; i++) {
        bodys += "<tr> <td>" + datas[i].id + "</td> <td>" + datas[i].shortname + "</td> <td>" + datas[i].fullname + "</td> <td>" + datas[i].specifications + "</td> </tr>";
    }
    htmllist += bodys;
    $("#dblist").append(htmllist);
}

/**
 * 根据输入模糊查询
 * @param iid
 * @param listid
 */
function getDataList(iid, listid, type) {
    var str = $("#" + iid + "").val();
    $.ajax({
        url: "http://"+url+":8888/automation/baseDatsCc/getDBBySome",
        dataType: "json",
        data: {"str": str, "type": type},
        type: "post",
        success: function (datas) {
            if (isStringNull(datas)) {
                // alertFaile();
            } else {
                showDataList(datas, listid, type);
            }
        }
    });
}

/**
 * 渲染页面
 * @param datas
 * @param listid
 * @param type
 */
function showDataList(datas, listid, type) {
    $("#" + listid + "").html("");
    for (var i = 0; i < datas.length; i++) {
        var vall = "";
        if (type == 1) {
            vall = datas[i].shortname;
        } else if (type == 2) {
            vall = datas[i].fullname;
        } else if (type == 3) {
            vall = datas[i].specifications;
        }
        $("#" + listid + "").append("<option value='" + datas[i].id + "'>" + vall + "</option>");
    }
}


/**
 * 下拉菜单联动
 * @param fathercode,父code
 * @param num，下拉级别1,2，3
 */
function getDBsByFc(fatherid, sunid) {
    var fid = $("#" + fatherid + " option:selected").val();
    $.ajax({
        url: "http://"+url+":8888/automation/baseDatsCc/getDBByLevelCode",
        type: "post",
        data: {"fathercode": fid},
        dataType: "json",
        success: function (datas) {
            if (datas == null) {
                alertFaile();
            } else {
                showOpBD(datas, sunid);
            }
        }
    });
}

/**
 * 动态添加下拉菜单
 * @param datas
 */
function showOpBD(datas, sunid) {
    $("#" + sunid + "").empty();
    $("#" + sunid + "").append("<option value=''> --请选择--</option>");
    for (var i = 0; i < datas.length; i++) {
        $("#" + sunid + "").append("<option value='" + datas[i].levelcode + "'>" + datas[i].shortname + "," + datas[i].specifications + "</option>");
    }
}
// var page = "<div class='pagination'><a onclick=initUserTable('" + (parseInt(nowpage) - 1) + "','" + type + "')  class='prev'><img src='img/icons/arrow-left.png'> </a>" +
//     "<a href='#' class='current'>" + nowpage + "</a>" +
//     "<a onclick=initUserTable('" + (parseInt(nowpage) + 1) + "','" + type + "') class='next'>  <img src='img/icons/arrow-right.png'></a></div>";


/**
 * 产品基础信息新增页面
 */
function initBDAdd() {
    $.ajax({
        type: "post",
        dataType: "json",
        url: "http://"+url+":8888/automation/baseDatsCc/getDBLevelCode",
        success: function (datas) {
            showBDAdddiv(datas);
        }
    });
}


function showBDAdddiv(datas) {
    var options = "";
    for (var i = 0; i < datas.length; i++) {
        options += "<option value='" + datas[i].levelcode + "'>" + datas[i].fullname + "</option>";
    }
    var htmlMenu = "";
    htmlMenu = "<h1><img src='img/icons/posts.png' alt='' /> 古道西风瘦马</h1><div class='bloc'><div class='title'>产品基础信息新增</div> <div class='content'>";
    htmlMenu += "<div class='input'><label for='input1'>简称<font color='#ff0000'>* </font></label><input type='text' id='bd_add_sn'  value='' /> </div>";
    htmlMenu += " <div class='input'><label for='input1'>全称<font color='#ff0000'>* </font></label><input type='text' id='bd_add_fn'   value=''  /> </div>";
    htmlMenu += " <div class='input'><label for='input1'>编码<font color='#ff0000'>* </font></label><input type='text' id='bd_add_lc' value='' /> </div> ";
    htmlMenu += " <div class='input'><label for='input1'>型号<font color='#ff0000'>* </font></label><input type='text' id='bd_add_sp' value='' /> </div> ";
    htmlMenu += " <div class='input'><label for='input1'>上级编码<font color='#ff0000'>* </font></label><select name='select' id='bd_add_fc' >" + options + "</select></div> ";
    htmlMenu += " <div class='input'><label for='input1'>属性<font color='#ff0000'>* </font> </label>" +
        "<select name='select' id='bd_add_ab'><option value='0' selected='selected'>默认</option><option value='1'>自制</option><option value='2'>外购</option></select> </div> ";
    htmlMenu += "<div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 5px;'   onclick=toAddBd()  type='submit' value='保存'></div></div>";
    $("#content").html(htmlMenu);
}
/**
 * 产品基础信息新增_post
 */
function toAddBd() {
    var flcode = $("#bd_add_fc  option:selected").val();
    var ab = $("#bd_add_ab  option:selected").val();
    var sn = $("#bd_add_sn").val();
    var fn = $("#bd_add_fn").val();
    var lc = $("#bd_add_lc").val();
    var sp = $("#bd_add_sp").val();
    if (isStringNull(flcode) || isStringNull(ab) || isStringNull(sn) || isStringNull(fn) || isStringNull(lc) || isStringNull(sp)) {
        alert("以上选项皆为必填项！");
        return;
    }
    $.ajax({
        data: {
            "shortname": sn,
            "fullname": fn,
            "levelcode": lc,
            "fatherlevelcode": flcode,
            "attribute": ab,
            "specifications": sp
        },
        url: "http://"+url+":8888/automation/baseDatsCc/insertIntoBD",
        type: "post",
        dataType: "text",
        success: function (datas) {
            if (datas == "true") {
                alertSuccess();
                toWelcome();
            } else {
                alertFaile();
            }
        }
    });
}