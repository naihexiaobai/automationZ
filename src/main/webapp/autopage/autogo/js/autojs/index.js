//全局url，唯一地址
var url = "localhost";


//菜单树
var htmlMenu = "";
$(document).ready(function () {
    //加载数据
    initData();
    //获取权限列表
    initUpPos();
    //加载欢迎页
    toWelcome();
});
//请求数据
function initData() {
    $.ajax({
        type: "post",
        url: "http://"+url+":8888/automation/menu/getAll",
        dataType: "json",
        success: function (datas) {
            //初始化菜单
            initMenu(datas);
        }
    });
}

function initMenu(jsdata) {
    initHeard();
    initBody(jsdata);
    intiEnd();
    $("#sidebar").html(htmlMenu);
    toWelcome();
}
//初始化菜单头
function initHeard() {
    var heard = " <ul><li class='nosubmenu'><a href='index.jsp'> <img src='img/icons/menu/contacts.png' alt= '' /> 首页 </a> </li> ";
    htmlMenu = heard;
}

//具体内容
//jsondata,--------id，唯一标示,name，名称,listFatherCode，父节点,listCode，节点,url,地址
function initBody(jsondata) {
    //一级目录节点id
    var menuids = "";
    for (var i = 0; i < jsondata.length; i++) {
        if (jsondata[i].listFatherCode == "0") {
            menuids += "&" + "menu" + jsondata[i].id;
        }
    }
    //循环渲染页面，根据目录结构进行三次循环（ps:可以用递归进行优化）
    for (var i = 0; i < jsondata.length; i++) {
        var body = "";
        if (jsondata[i].listFatherCode == "0") {
            body += "<li ><a onclick=controllerMenu('menu" + jsondata[i].id + "','" + menuids + "')><img src='img/icons/menu/settings.png' alt='' />" + jsondata[i].name + "</a><ul id='menu" + jsondata[i].id + "' style='display: none'>";
            for (var q = 0; q < jsondata.length; q++) {
                if (jsondata[q].listFatherCode == (jsondata[i].listCode)) {
                    body += "<li><a onclick=controllerMenu('menu" + jsondata[q].id + "','" + menuids + "') > " + jsondata[q].name + " </a><ul  id='menu" + jsondata[q].id + "' style='display: none'>";
                    for (var w = 0; w < jsondata.length; w++) {
                        if (jsondata[w].listFatherCode == (jsondata[q].listCode)) {
                            body += "<li><a onclick='" + jsondata[w].url + "' > " + jsondata[w].name + "</a></li>";
                        }
                    }
                    body += "</ul></li>";
                }
            }
            body += "</ul></li>";
        }
        htmlMenu += body;
    }
}

//隐藏或者显示,每次只显示一个一级目录，0开，1关,开关功能不完善，17.7.24
function controllerMenu(id, jsondata) {
    var status = 1;
    if ($("#" + id + "").css("display") == 'none') {
        $("#" + id + "").css('display', 'block');
        // if (status == 0) {
        //     for (var i = 1; i < jsondata.split("&").length; i++) {
        //         if (jsondata.split("&")[i] == id) {
        //             $("#" + id + "").css('display', 'block');
        //         } else {
        //             $("#" + jsondata.split("&")[i] + "").css('display', 'none');
        //         }
        //     }
        // }
    }
    else {
        $("#" + id + "").css('display', 'none');
    }

}

//初始化尾部
function intiEnd() {
    var end = "</ul>";
    htmlMenu += end;
}

/**
 * 初始化欢迎页
 */
function toWelcome() {
    //   div    id   content
    $("#content").html("<h1><img src='img/icons/dashboard.png' alt='' />滴水穿石，非一日之功！</h1>");
}

/**
 * 初始化权限列表----权限修改页
 */
function initUpPos() {
    $.ajax({
        type: "post",
        url: "http://"+url+":8888/automation/positionCc/getAllPos",
        dataType: "json",
        success: function (datas) {
            //初始化权限修改页
            initUpps(datas);
        }
    });
}

/**
 * 渲染权限修改页
 */
function initUpps(datas) {
    var htmlupps = "";
    var option = "";
    for (var i = 0; i < datas.length; i++) {
        option += "<option value='" + datas[i].position + "&" + datas[i].positioncode + "'>" + datas[i].position + "</option>";
    }
    htmlupps += " <label for='input1'>职位</label><select class='ipt' name='select' id='user_up_positions'>" + option + "</select>  ";
    $("#updateUP").html(htmlupps);
}