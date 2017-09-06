function getposition() {
    $.ajax({
        type: "post",
        url: "http://"+url+":8888/automation/positionCc/getAllPos",
        dataType: "json",
        success: function (datas) {
            //初始化权限页面
            initPps(datas);
        }
    });
}

function initPps(datas) {
    var htmlMenu = "<h1><img src='img/icons/posts.png' alt='' /> 商女不知亡国恨</h1><div class='bloc'><div class='title'>权限级别页</div> <div class='content'>" +
        " <table> <thead> <tr> <th>职级名称</th> <th>职级编号</th><th>状态</th> <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</th> </t> </thead> " +
        "<tbody>";
    var bodys = "";
    for (var i = 0; i < datas.length; i++) {
        var status = "";
        var qq = "";
        if (datas[i].status == "1") {
            status = "<span style='color: #43fb11; font-weight:bold;'>ON<span>";
        } else {
            status = "<span style='color: #f21721; font-weight:bold;'>OFF<span>";
        }
        bodys += "<tr> <td>" + datas[i].position + "</td> <td>" + datas[i].positioncode + "</td> <td>" + status + "</td> " +
            " <td class='actions'><a onclick=updatepps('" + datas[i].id + "') title='修'><img src='img/icons/actions/delete.png' alt='' /></a></td> </tr>";
    }
    htmlMenu += bodys;
    htmlMenu += "</tbody> </table> </div> " +
        "<div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 4px;'  onclick=initAddPps()   type='submit' value='新增'></div></div>";
    $("#content").html(htmlMenu);
}

function updatepps(id) {
    if (confirm("确定修改其状态？")) {
        toUpdatePps(id);
    }
}

function toUpdatePps(id) {
    $.ajax({
        data: {"id": id},
        url: "http://"+url+":8888/automation/positionCc/updatePos",
        type: "post",
        success: function (datas) {
            if (datas) {
                alert("you are very good!");
                getposition();
            } else {
                alert("you can go die!");
            }
        }
    })
}

// <div class='content'><div class='input'><label for='input1'>Text input</label><input type='text' id='input1' /> </div><</div>
function initAddPps() {
    var htmlMenu = "<h1><img src='img/icons/posts.png' alt='' /> 隔江犹唱后庭花</h1><div class='bloc'><div class='title'>系统权限新增</div> <div class='content'>";
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>权限名称</label><input type='text' id='position'  name='position' /> </div></div>";
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>权限名称编码</label><input type='text' id='positioncode'  name='positioncode' /> </div></div>";
    htmlMenu += " </div> " +
        "<div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 4px;'  onclick='addPpos()'    type='submit' value='保存'></div></div>";
    $("#content").html(htmlMenu);
}

function addPpos() {
    var positioncode = $("#positioncode").val();
    var position = $("#position").val();
    $.ajax({
        data:{"positioncode":positioncode,"position":position},
        url:"http://"+url+":8888/automation/positionCc/addPos",
        type:"post",
        success:function (data) {
            if (data){
                alert("go go go succccccccccc");
                getposition();
            }else {
                alert("fail~~~~~~~~~~~~~~~~~~~~ ,请拨打110致电咨询");
            }
        }
    });
}