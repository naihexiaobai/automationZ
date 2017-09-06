function addpermission() {

}


function getpermission() {
    $.ajax({
        type: "post",
        url: "http://"+url+":8888/automation/positionCc/getAllPos",
        dataType: "json",
        success: function (datas) {
            //初始化权限页面
            initPp(datas);
        }
    });
}

function initPp(datas) {
    var htmlMenu = "<h1><img src='img/icons/posts.png' alt='' /> 一江春水向东流</h1><div class='bloc'><div class='title'>系统权限 </div><div class='content'>" +
        " <table> <thead> <tr> <th>职级名称</th> <th>职级编号</th> <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</th> </t> </thead> " +
        "<tbody>";
    var bodys = "";
    for (var i = 0; i < datas.length; i++) {
        if (datas[i].status == 1) {
            bodys += "<tr> <td>" + datas[i].position + "</td> <td>" + datas[i].positioncode + "</td> " +
                " <td class='actions'><a onclick=editPp('" + datas[i].positioncode + "') title='编辑'><img src='img/icons/actions/edit.png' alt='' /></a></td> </tr>";
        }
    }
    htmlMenu += bodys;
    htmlMenu += "</tbody> </table> </div> </div>";
    $("#content").html(htmlMenu);
}

function editPp(positioncode) {
    $.ajax({
        type: "post",
        data: {"positioncode": positioncode},
        url: "http://"+url+":8888/automation/permissionCc/getLeaveOther",
        dataType: "json",
        success: function (datas) {
            //初始化权限编辑页面
            initEditPp(datas, positioncode);
        }
    });
}

function initEditPp(datas, positioncode) {
    $("#content").html("");
    var pname = pcodeTopname(positioncode);
    var formHeard = "";
    var formEnd = "";
    var htmlMenu = "<h1><img src='img/icons/posts.png' alt=''>大江东去，浪淘尽！</h1> <div class='bloc'> " + formHeard +
        "<div class='title'>" + pname + "</div> <div class='content'>" +
        " <div class='input'> <label class='label'>修改请选择:</label>";
    var bodys = "";
    for (var i = 0; i < datas.length; i++) {
        var sp_class = "";
        var in_check = "";
        if (datas[i].remark == 1) {
            var sp_class = "class='checked'";
            var in_check = "checked='checked'";
        }
        bodys += "<div class='checker' id='uniform-check" + datas[i].id + "'><span  " + sp_class + "  id='sp" + datas[i].id + "' >" +
            "<input type='checkbox' onclick=changeSp('" + datas[i].id + "')  name='checks'  id='check" + datas[i].id + "'   " + in_check + "   style='opacity: 0;' value='" + datas[i].id + "@" + datas[i].listCode + "' /></span></div>" +
            "<label for='check" + datas[i].id + "' class='inline'>" + datas[i].name + "</label><br>";
    }
    htmlMenu += bodys;
    htmlMenu += "</div> <div class='submit'> <input type='submit'  onclick=submitDate('" + positioncode + "')  value='提交'> </div> </div>" + formEnd + " </div>";
    $("#content").html(htmlMenu);
}

function submitDate(positioncode) {
    var ids = "";
    $("input[name='checks']:checked").each(function () {
        ids += "&" + $(this).val();
    });
    $.ajax({
        type: "post",
        url: "http://"+url+":8888/automation/permissionCc/insertIntoP",
        data: {"positioncode": positioncode, "ids": ids},
        success: function (datas) {
            if (datas) {
                alert("成功！");
                initData();
            } else {
                alert("失败！");
            }
        }
    });
}

function pcodeTopname(positioncode) {
    var pname = "";
    if (positioncode == 0) {
        pname = "超级管理员";
    } else if (positioncode == 1) {
        pname = "员工";
    }
    else if (positioncode == 2) {
        pname = "组长";
    }
    else if (positioncode == 3) {
        pname = "课长";

    } else if (positioncode == 4) {
        pname = "副理";

    } else if (positioncode == 5) {
        pname = "经理";

    }
    return pname;
}

//选中状态改变
function changeSp(spid) {
    $("#sp" + spid + "").toggleClass("checked");
    if ($("#check" + spid + "").attr('checked')) {
        $("#check" + spid + "").attr("checked", true);
    } else {
        $("#check" + spid + "").attr("checked", false);
    }
}

function queryUserPer() {
    initUserTable(1, 3);
}