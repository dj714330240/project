function search() {
    var $userTableForm = $(".user-table-form");
    var phone = $userTableForm.find("input[name='phone']").val().trim();
    $.post(ctx + "last_active/info", {"phone": phone}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $("#phone").html(phone);
            if (data.matches == "1") {
                $("#times").html("手机号最近一次使用时间小于等于2个月");
            } else if (data.inUseTime == "2") {
                $("#times").html("手机号最近一次使用时间大于2个月");
            } else if (data.inUseTime == "3") {
                $("#times").html("手机号最近一次使用时间大于3个月");
            } else if (data.inUseTime == "4") {
                $("#times").html("手机号最近一次使用时间大于6个月");
            } else if (data.inUseTime == "5") {
                $("#times").html("其他");
            } else if (data.inUseTime == "90") {
                $("#times").html("非测试号码");
            } else if (data.inUseTime == "91") {
                $("#times").html("联通号码但不具备数据");
            }
        } else {
            $MB.n_danger(r.msg);
        }
    });
}