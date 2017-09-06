/**
 * 初始化查询菜单页面
 */
function initMenuTree(page) {
    if (page == null || page == "" || page < 1) {
        page = 1;
    }
    $.ajax({
        url: "http://"+url+":8888/automation/menu/getAllStatusByPage",
        data: {"gopage": page},
        type: "post",
        dataType: "json",
        success: function (datas) {
            if (datas != null) {
                showMenuTree(datas);
            } else {
                alert("you are lowser~");
            }
        }
    });
}
/**
 * 菜单查询展示
 * @param data
 */
function showMenuTree(data) {
    $("#content").empty();
    var datas = data.jsonarray;
    var nowpage = data.pagenow;
    var title = "海上明月共潮生";
    var title_fu = "菜单查看";
    var page = "<div class='pagination'><a onclick=initMenuTree('" + (parseInt(nowpage) - 1) + "')  class='prev'><img src='img/icons/arrow-left.png'> </a>" +
        "<a href='#' class='current'>" + nowpage + "</a>" +
        "<a onclick=initMenuTree('" + (parseInt(nowpage) + 1) + "') class='next'>  <img src='img/icons/arrow-right.png'></a></div>";
    var htmlMenu = "<h1><img src='img/icons/posts.png' alt='' />" + title + "</h1><div class='bloc'><div class='title'>" + title_fu + "</div><div class='content'>" +
        " <table> <thead> <tr> <th>序号</th> <th>名称</th> <th>菜单编号</th> <th>地址</th> <th>状态</th> <th> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 操作</th> </tr> </thead> " +
        "<tbody>";
    var bodys = "";
    for (var i = 0; i < datas.length; i++) {
        var options = "";
        if (datas[i].status == 1) {
            status = "<span style='color: #43fb11; font-weight:bold;'>ON<span>";
        } else {
            status = "<span style='color: #f21721; font-weight:bold;'>OFF<span>";
        }
        bodys += "<tr> <td>" + datas[i].id + "</td>" +
            " <td>" + datas[i].name + "</td> " +
            " <td>" + datas[i].listCode + "</td> " +
            " <td>" + datas[i].url + "</td> " +
            " <td>" + status + "</td> " +
            " <td class='actions'>" +
            "<a title='编辑' onclick='updateMenutree('" + datas[i].id + "','" + datas[i].name + "','" + datas[i].url + "','" + datas[i].listCode + "','" + datas[i].listFatherCode + "')'><img src='img/icons/menu/dark/brush.png'  ></a><a title='启用'  onclick=changeMenuStatus(" + datas[i].id + ",1)><img src='img/icons/menu/dark/pig.png'  ></a><a   title='禁用' onclick=changeMenuStatus(" + datas[i].id + ",0)><img src='img/icons/actions/delete.png' alt=''   /></a>"
            +
            "</td> </tr>";
    }
    htmlMenu += bodys;
    htmlMenu += "</tbody> </table> " + page +
        "</div> </div>";
    $("#content").html(htmlMenu);
}

/**
 * 修改菜单信息
 * @param id
 * @param name
 * @param url
 */
function updateMenutree(id, name, url, listcode, listfathercode) {
    var htmlMenu = "";
    var title = "江春入旧年";
    var title2 = "菜单信息修改";
    htmlMenu = "<h1><img src='img/icons/posts.png' alt='' /> " + title + "</h1><div class='bloc'><div class='title'>" + title2 + "</div> <div class='content'>";
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>菜单名称</label>" +
        "<input type='text' id='m_name'   value='" + name + "'  /> </div></div>";
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>菜单地址</label>" +
        "<input type='text' id='m_url'    value='" + url + "' /> </div></div>";
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>菜单编号</label>" +
        "<input type='text'  readonly  id='m_listcode'   value='" + listcode + "'  /> </div></div>";
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>父菜单编号</label>" +
        "<input type='text'  readonly  id='m_listfathercode'   value='" + listfathercode + "'  /> </div></div>";
    htmlMenu += " </div> " +
        "<div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 4px;'   onclick=toUpdateM('" + id + "')  type='submit' value='保存'></div></div>";
    $("#content").html(htmlMenu);
}

/**
 * 保存————menutree
 * @param id
 */
function toUpdateM(id) {
    var name = $("#m_name").val();
    var url = $("#m_url").val();
    var listcode = $("#m_listcode").val();
    var listfathercode = $("#m_listfathercode").val();
    $.ajax({
        data: {"id": id, "name": name, "url": url, "listcode": listcode, "listfathercode": listfathercode},
        // data: {"id": id, "name": name, "url": url },
        url: "http://"+url+":8888/automation/menu/updateMenuTreeSome",
        dataType: "text",
        type: "post",
        success: function (datas) {
            if (datas == "true") {
                alert("修改成功！");
                initMenuTree(1);
            } else {
                alertFaile();
            }
        }
    });
}


/**
 * 更改状态
 * @param id
 * @param type
 */
function changeMenuStatus(id, type) {
    if (confirm("确认改变其状态？")) {
        $.ajax({
            data: {"id": id, "type": type},
            type: "post",
            dataType: "text",
            url: "http://"+url+":8888/automation/menu/changeMenuStatus",
            success: function (datas) {
                if (datas == "true") {
                    alert("修改成功！");
                    initMenuTree(1);
                } else {
                    alertFaile();
                }
            }
        });
    }
}

/**
 * 新增menutree
 */
function addMenuTree() {
    $.ajax({
        url: "http://"+url+":8888/automation/menu/selectByLC",
        dataType: "json",
        type: "post",
        success: function (datas) {
            showAddM(datas);
        }
    });
}

/**
 * 渲染页面
 * @param datas
 */
function showAddM(datas) {
    var htmlMenu = "";
    var title = "忽如一夜春风来";
    var title2 = "菜单信息新增";
    var options = "<option value='0' selected='selected'>--请选择--</option>";
    for (var i = 0; i < datas.length; i++) {
        options += "<option value='" + datas[i].listCode + "'>" + datas[i].name + "</option>";
    }
    htmlMenu = "<h1><img src='img/icons/posts.png' alt='' /> " + title + "</h1><div class='bloc'><div class='title'>" + title2 + "</div> <div class='content'>";
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>菜单名称</label>" +
        "<input type='text' id='m_name'   value=' '  /> </div></div>";
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>菜单地址</label>" +
        "<input type='text' id='m_url'    value=' ' /> </div></div>";
    htmlMenu += "<div class='content'><div class='input'><label for='input1'>选择父菜单</label>" +
        "<select id='m_lfc'>" + options + "</select> </div></div>";
    htmlMenu += " </div> " +
        "<div class='submit'>&nbsp;&nbsp; <input style='margin-bottom: 4px;'   onclick=toAddM()  type='submit' value='保存'></div></div>";
    $("#content").html(htmlMenu);
}

/**
 * 新增menutree
 */
function toAddM() {
    var name = $("#m_name").val();
    var url = $("#m_url").val();
    var listfathercode = $("#m_lfc option:selected").val();
    $.ajax({
        data: {"name": name, "url": url, "listfathercode": listfathercode},
        // data: {"id": id, "name": name, "url": url },
        url: "http://"+url+":8888/automation/menu/addMenuTreeSome",
        dataType: "text",
        type: "post",
        success: function (datas) {
            if (datas == "true") {
                alert("新增成功！");
                initMenuTree(1);
            } else {
                alertFaile();
            }
        }
    });
}