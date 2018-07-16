function search() {
    var $userTableForm = $(".user-table-form");
    var phone = $userTableForm.find("input[name='phone']").val().trim();
    $.post(ctx + "on_line_times/info", {"phone": phone}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $("#phone").html(phone);
            if (data.inUseTime == "1") {
                $("#times").html("当月入网");
            } else if (data.inUseTime == "2") {
                $("#times").html("上个月入网");
            } else if (data.inUseTime == "3") {
                $("#times").html("3-6个月");
            } else if (data.inUseTime == "4") {
                $("#times").html("7-12个月");
            } else if (data.inUseTime == "5") {
                $("#times").html("13-24个月");
            } else if (data.inUseTime == "6") {
                $("#times").html("25-36个月");
            } else if (data.inUseTime == "7") {
                $("#times").html("37个月及以上");
            }
        } else {
            $MB.n_danger(r.msg);
        }
    });
}