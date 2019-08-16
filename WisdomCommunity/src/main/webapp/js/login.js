let owner_id = "";
$(function () {
    $("#user_info").hide();
    $.ajax({
        type: "GET"
        , url: "owner/getInfo"
        , dataType: "json"
        , cache: true
        , success: function (data) {
            console.log(data);
            owner_id = data.ownerId;
            if (data != null) {
                $(".btn1").css({
                    "display": "none"
                })
                $("#user_name").text(data.ownerName);
                $("#user_info").show();
            }
        }
    })
    $(".btn1").click(function () {
        window.location.href = "loginPage";
    })
    $("#edit_password").click(function () {
        window.location.href = "ownerEdit";
    })
    $("#return").click(function () {
        window.location.href = "indexPage";
    });

})
layui.use(['layer'], function () {
    
    let layer = layui.layer;
    $("#sub").click(function () {
        let old_pwd = $("#old_pwd").val();
        let new_pwd = $("#new_pwd").val();
        let re_pwd = $("#re_pwd").val();

        if (new_pwd == null || new_pwd == undefined || !(/^[A-Za-z0-9]+$/.test(new_pwd))) {
            layer.msg("密码不能包含特殊字符");
            return;
        }
        if (re_pwd == null || re_pwd == undefined || !(/^[A-Za-z0-9]+$/.test(re_pwd))) {
            layer.msg("密码不能包含特殊字符");
            return;
        }
        if (old_pwd == new_pwd) {
            layer.msg("新旧密码重复");
            return;
        }
        if (new_pwd != re_pwd) {
            layer.msg("两次输入的密码不一致");
            return;
        }
        $.ajax({
            type: "POST"
            , url: "owner/update"
            , data: {
                oldPwd: old_pwd,
                newPwd: new_pwd,
                ownerId: owner_id
            }
            ,dataType:"json"
            ,success:function(data){
                layer.msg(data.msg, { time: 500 }, function () {
                    if (data.code == 0) {
                        window.location.href = "loginPage";
                    }
                })
            }
        })
    })

    $("#owner_logout").click(function(){
        $.ajax({
            type:"GET"
            ,url:"owner/logout"
            ,dataType:"json"
            ,success:function(data){
                layer.msg(data.msg, { time: 500 }, function () {
                    if (data.code == 0) {
                        window.location.href = "indexPage";
                    }
                })
            }
        })
    })


})