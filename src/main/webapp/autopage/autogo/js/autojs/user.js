/**
 * 初始化用户页面--请求数据
 * @param page
 * @param type,类型1，编辑，2禁用
 */
//初始化页数为1
function initUserTable(page, type) {
    $.ajax({
        url: "http://" + url + ":8888/automation/userCc/selectUserByPage",
        data: {"gopage": page},
        type: "post",
        dataType: "json",
        success: function (datas) {
            if (datas != null) {
                showUserTable(datas, type);
            } else {
                alert("you are lowser~");
            }
        }
    });
}

/**
 * 初始化用户页面--渲染页面
 * @param datas
 */
function showUserTable(data, type) {
    var datas = data.jsonarray;
    var nowpage = data.pagenow;
    var title = "醉里挑灯看剑";
    var title_fu = "用户查看";
    if (type == 2) {
        title = "八百里分麾下炙";
        title_fu = "用户禁用";
    }
    if (type == 3) {
        title = "梦回吹角连营";
        title_fu = "用户权限修改";
    }
    var page = "<div class='pagination'><a onclick=initUserTable('" + (parseInt(nowpage) - 1) + "','" + type + "')  class='prev'><img src='img/icons/arrow-left.png'> </a>" +
        "<a href='#' class='current'>" + nowpage + "</a>" +
        "<a onclick=initUserTable('" + (parseInt(nowpage) + 1) + "','" + type + "') class='next'>  <img src='img/icons/arrow-right.png'></a></div>";
    var htmlMenu = "<h1><img src='img/icons/posts.png' alt='' />" + title + "</h1><div class='bloc'><div class='title'>" + title_fu + "</div><div class='content'>" +
        " <table> <thead> <tr> <th>姓名</th> <th>部门</th> <th>职位</th> <th>手机号</th><th>状态</th> <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</th> </tr> </thead> " +
        "<tbody>";
    var bodys = "";
    for (var i = 0; i < datas.length; i++) {
        var options = "";
        if (datas[i].status == 0) {
            status = "<span style='color: #43fb11; font-weight:bold;'>in<span>";
        } else {
            status = "<span style='color: #f21721; font-weight:bold;'>leave<span>";
        }
        if (type == 1) {
            options = "<a onclick=editUs('" + datas[i].id + "','" + type + "') title='编辑'><img src='img/icons/actions/edit.png' alt='' /></a>";
        }
        if (type == 2) {
            options = "<a   title='禁用'><img src='img/icons/actions/delete.png' alt='' onclick=deleteUs('" + datas[i].id + "','" + type + "')  /></a>";
        }
        if (type == 3) {
            //onclick=updateUserPersi('" + datas[i].id + "','" + type + "')
            // <div class='demo'><a href='javascript:;' class='bounceIn'>DEMO1</a></div>
            //     options = "<a  href='javascript:;' class='bounceIn'    >修改权限 </a>";
            options = "<a onclick=changeDivUpdate('" + datas[i].id + "','" + type + "')>修改权限</a>";
        }
        bodys += "<tr> <td>" + datas[i].name + "</td>" +
            " <td>" + datas[i].department + "</td> " +
            " <td>" + datas[i].position + "</td> " +
            " <td>" + datas[i].phone + "</td> " +
            " <td>" + status + "</td> " +
            " <td class='actions'>" +
            options
            +
            "</td> </tr>";
    }
    htmlMenu += bodys;
    htmlMenu += "</tbody> </table> " + page +
        "</div> </div>";
    $("#content").html(htmlMenu);
}
/**
 * 修改用户权限
 * @param id
 * @param type
 */
function changeDivUpdate(id, type) {
    $("#dialogBg").css("display", "block");
    $("#dialog").css("display", "block");
    $("#user_update_position_uid").val(id);
}

function updateUsPs() {
    var uid = $("#user_update_position_uid").val();
    var position = $("#user_up_positions option:selected").val();
    $.ajax({
        url: "http://" + url + ":8888/automation/userCc/updateUp",
        data: {"id": uid, "position": position},
        type: "post",
        success: function (data) {
            if (data == "true") {
                $("#dialogBg").css("display", "none");
                $("#dialog").css("display", "none");
                initUserTable(1, 3);
            } else {
                alert("you are lowser~");
            }
        }
    });
}


/**
 * 更改状态
 * @param id
 */
function deleteUs(id, type) {
    $.ajax({
        url: "http://" + url + ":8888/automation/userCc/deleteOff",
        data: {"id": id},
        type: "post",
        success: function (data) {
            if (data != null) {
                initUserTable(1, type);
            } else {
                alert("you are lowser~");
            }
        }
    });
}

/**
 * 用户页面----修改查询数据
 * @param data
 */
function editUs(id, type) {
    $.ajax({
        url: "http://" + url + ":8888/automation/userCc/selectUserById",
        data: {"id": id},
        type: "post",
        dataType: "json",
        success: function (data) {
            if (data != null) {
                initEditUs(data, type);
            } else {
                alert("you are lowser~");
            }
        }
    });
}
/**
 * 用户页面----修改,渲染页面
 * @param data
 */
function initEditUs(data, type) {
    var htmlMenu = "";
    var title = "";
    var title2 = "";
    if (type == 1) {
        title = "枯藤老树昏鸦";
        title2 = "用户编辑";
    }
    if (type == 2) {
        title = "古道西风瘦马";
        title2 = "用户禁用";
    }
    htmlMenu = "<h1><img src='img/icons/posts.png' alt='' /> " + title + "</h1><div class='bloc'><div class='title'>" + title2 + "</div> <div class='content'>";
    htmlMenu += ( "<input type='hidden' name='user.id' id='userid'  value='" + data.id + "' />");
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>姓名</label><input type='text' id='username'  name='user.name' value='" + data.name + "' /> </div></div>";
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>部门</label><input type='text' id='userdepartment'  name='user.department' value='" + data.department + "'  /> </div></div>";
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>手机号</label><input type='text' id='userphone'  name='user.phone' value='" + data.phone + "' /> </div></div>";
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>职位</label><input type='text'  readonly id='userposition'  name='user.position' value='" + data.position + "'  /> </div></div>";
    htmlMenu += " </div> " +
        "<div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 4px;'   onclick=toUpdate('" + type + "')  type='submit' value='保存'></div></div>";
    $("#content").html(htmlMenu);
}

/**
 * 修改用户----data
 */
function toUpdate(type) {
    var id = $("#userid").val();
    var name = $("#username").val();
    var department = $("#userdepartment").val();
    var position = $("#userposition").val();
    var phone = $("#userphone").val();
    if (isStringNull(id) || isStringNull(name) || isStringNull(department) || isStringNull(position) || isStringNull(phone)) {
        alert("不能存在空值！");
    } else {
        $.ajax({
            url: "http://" + url + ":8888/automation/userCc/updateUser",
            data: {"id": id, "name": name, "department": department, "position": position, "phone": phone},
            dataType: "json",
            type: "post",
            success: function (data) {
                if (data) {
                    initUserTable(1, type);
                } else {
                    alertFaile();
                }
            }
        });
    }
}

/**
 * 禁用用户
 */
function iniuserban(page, type) {
    initUserTable(page, type);
}

/**
 * 失败提示弹框
 */
function alertFaile() {
    alert("修改失败，拨打110！");
}

/**
 * 成功弹框
 */
function alertSuccess() {
    alert("修改成功，快去撸串庆祝！");
}

/**
 * 判断空值
 * @param str
 * @returns {boolean}
 */
function isStringNull(str) {
    if (str == null || str == "") {
        return true;
    } else {
        return false;
    }
}

/**
 * 初始化用户新增页面
 */
function initAddUsTable() {
    $.ajax({
        type: "post",
        url: "http://" + url + ":8888/automation/positionCc/getAllPos",
        dataType: "json",
        success: function (datas) {
            //初始化用户新增页面
            initUserTables(datas);
        }
    });

}

/**
 * 初始化新增用户表单
 * @param datas
 */
function initUserTables(datas) {
    var htmlMenu = "";
    htmlMenu = "<h1><img src='img/icons/posts.png' alt='' /> 小桥流水人家</h1><div class='bloc'><div class='title'>用户新增</div> <div class='content'>";
    htmlMenu += "<div class='input'><label for='input1'>姓名</label><input type='text' id='username'  value='' /> </div>";
    htmlMenu += " <div class='input'><label for='input1'>部门</label><input type='text' id='userdepartment'   value=''  /> </div>";
    htmlMenu += " <div class='input'><label for='input1'>手机号</label><input type='text' id='userphone' value='' /> </div> ";
    var option = "";
    for (var i = 0; i < datas.length; i++) {
        option += "<option value='" + datas[i].position + "&" + datas[i].positioncode + "'>" + datas[i].position + "</option>";
    }
    htmlMenu += " <div class='input'><label for='input1'>职位</label><select name='select' id='userposition'>" + option + "</select></div> ";
    htmlMenu += " <div class='input'><label for='input1'>工号</label><input type='text' id='userjobnumber'   value=''  /> </div> ";
    htmlMenu += " <div  class=input' ><label for='input1'>用户名</label><input type='text'  onchange=checkUserName()  id='userusername'  value='用户名'  /> <div id='us_check_sp'></div>" +
        "</div> ";
    htmlMenu += " <div class='input'><label for='input1'>密码</label><input type='password' id='useruserpassword'  value='密码'  /> </div> ";
    htmlMenu += " </div> " +
        "<div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 5px;'   onclick=toAddUs()  type='submit' value='保存'></div></div>";
    $("#content").html(htmlMenu);
}

/**
 * 新增用户
 */
function toAddUs() {
    var name = $("#username").val();
    var department = $("#userdepartment").val();
    var position = $("#userposition option:selected").val();
    var phone = $("#userphone").val();
    var jobnumber = $("#userjobnumber").val();
    var username = $("#userusername").val();
    var password = $("#useruserpassword").val();
    if (isStringNull(jobnumber) || isStringNull(name) || isStringNull(department) || isStringNull(position) || isStringNull(phone) || isStringNull(username) || isStringNull(password)) {
        alert("不能存在空值！");
    } else {
        $.ajax({
            url: "http://" + url + ":8888/automation/userCc/addUser",
            data: {
                "name": name,
                "department": department,
                "position": position,
                "phone": phone,
                "jobnumber": jobnumber,
                "username": username,
                "password": password
            },
            dataType: "json",
            type: "post",
            success: function (data) {
                if (data) {
                    //查询
                    initUserTable(1, 1);
                } else {
                    alertFaile();
                }
            }
        });
    }
}

/**
 * 校验用户名
 */
function checkUserName() {
    var username = $("#userusername").val();
    $("#us_check_sp").html("");
    var status = "";
    if (isStringNull(username)) {
        status = "<span style='color: #f21721; font-weight:bold;'>用户名不能为空！<span>";
        $("#us_check_sp").html(status);
    } else {
        $.ajax({
            type: "post",
            url: "http://" + url + ":8888/automation/userCc/selectByUserName",
            data: {"username": username},
            success: function (datas) {
                //初始化用户新增页面
                if (datas == "false") {
                    status = "<span style='color: #f21721; font-weight:bold;'>用户名已存在<span>";
                } else {
                    status = "<span style='color: #43fb11; font-weight:bold;'>用户名校验通过<span>";
                }
                $("#us_check_sp").html(status);
            }
        });
    }
}

/**
 * 修改密码-----渲染页面
 */
function eduitPassword() {
    var htmlMenu = "";
    htmlMenu = "<h1><img src='img/icons/posts.png' alt='' /> 一树梨花压海棠</h1><div class='bloc'><div class='title'>重置密码</div> <div class='content'>";
    htmlMenu += ( "<input type='hidden' name='user.id' id='userid'  value=' ' />");
    htmlMenu += " <div class='input'><label for='input1'>旧密码</label><input type='password' id='userpassword'  name='user.password' value='' /> </div>";
    htmlMenu += " <div class='input'><label for='input1'>新密码</label><input type='password' id='userpassword1'  name='user.password1' value='' /> </div>";
    htmlMenu += " <div class='input'><label for='input1'>新密码</label><input type='password' id='userpassword2'  name='user.password2' value='' /> </div>";
    htmlMenu += " </div> " +
        "<div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 4px;'   onclick=toUpdatePassword()  type='submit' value='保存'></div></div>";
    $("#content").html(htmlMenu);
}
/**
 * 修改密码---提交事务
 */
function toUpdatePassword() {
    var password = "";
    var password1 = "";
    var password2 = "";
    password = $("#userpassword").val();
    password1 = $("#userpassword1").val();
    password2 = $("#userpassword2").val();
    if (password1 == password2) {
        $.ajax({
            data: {"password": password, "password1": password1},
            type: "post",
            url: "http://" + url + ":8888/automation/userCc/updatePassword",
            success: function (data) {
                if (data) {
                    alertSuccess();
                    toWelcome();
                } else {
                    alertFaile();
                }
            }
        });
    } else {
        alert("两次密码不一致！请重新输入。。。")
    }
}