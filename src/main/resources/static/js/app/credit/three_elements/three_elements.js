$(function () {
    var $userTableForm = $(".user-table-form");
    var settings = {
        url: ctx + "three_elements/info",
        pageSize: 10,
        queryParams: function (params) {
            return {
                phone: $userTableForm.find("input[name='phone']").val().trim(),
                cardType: $userTableForm.find("select[name='cardType']").val(),
                cardNumber: $userTableForm.find("input[name='cardNumber']").val().trim(),
                userName: $userTableForm.find("input[name='userName']").val().trim()
            };
        },
        columns: [ {
            field: 'status',
            title: '状态',
            formatter: function (value, row, index) {
                if (value === '1') return '<span class="badge badge-success">成功</span>';
                if (value === '2') return '<span class="badge badge-warning">失败</span>';
            }
        },{
            field: 'checkResult',
            title: '三要素校验结果',
            formatter: function (value, row, index) {
                if (value === '00') return '<span class="badge badge-success">手机号、证件号、姓名均一致</span>';
                if (value === '01') return '<span class="badge badge-warning">手机号、证件号一致、姓名不一致</span>';
                if (value === '02') return '<span class="badge badge-warning">手机号、证件号一致、姓名为空</span>';
                if (value === '03') return '<span class="badge badge-warning">手机号，姓名一致、证件号不一致</span>';
                if (value === '04') return '<span class="badge badge-warning">手机号一致，证件号，姓名不一致</span>';
                if (value === '05') return '<span class="badge badge-warning">手机号一致，证件号不一致，姓名为空</span>';
                if (value === '06') return '<span class="badge badge-warning">手机号、姓名一致，证件号为空</span>';
                if (value === '07') return '<span class="badge badge-warning">手机号一致，证件号为空，姓名不一致</span>';
                if (value === '08') return '<span class="badge badge-warning">手机号一致，证件号，姓名为空</span>';
            }
        }

        ]
    };

    $MB.initTable('userTable', settings);
});
function search() {
    var $userTableForm = $(".user-table-form");
    var phone = $userTableForm.find("input[name='phone']").val().trim();
    var cardType = $userTableForm.find("select[name='cardType']").val();
    var cardNumber = $userTableForm.find("input[name='cardNumber']").val().trim();
    var userName = $userTableForm.find("input[name='userName']").val().trim();

    $.post(ctx + "three_elements/info", {"phone": phone,"cardType": cardType,"cardNumber": cardNumber, "userName": userName}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $("#userName").html(userName);
            $("#phone").html(phone);
            $("#cardNumber").html(cardNumber);
            // $(".userName").background("#7FFFAA");
            var carT = "--";
            if (cardType == '0101') {
                carT = "居民身份证";
            }else if (cardType == '0102'){
                carT = "户口簿";
            }else if (cardType == '0103'){
                carT = "军人身份证";
            }else if (cardType == '0104'){
                carT = "武装警察身份证";
            }else if (cardType == '0105'){
                carT = "港澳居民往来内地通行";
            }else if (cardType == '0106'){
                carT = "台湾居民往来大陆通行证";
            }else if (cardType == '0107'){
                carT = "护照";
            }else if (cardType == '0199'){
                carT = "其他个人证件";
            }else if (cardType == '0999'){
                carT = "未知(手机号码不存在或者本身非实名)";
            }
            $("#cardType").html(carT);
            $("#status").html("成功");
            if (data.checkResult == '00'){
                $("#cardNumberStatus").html("一致");
                $("#phoneStatus").html("一致");
                $("#userNameStatus").html("一致");
                $(".userName").css("color","#54BB3D");
                $(".phone").css("color","#54BB3D");
                $(".cardNumber").css("color","#54BB3D");
            }else if (data.checkResult == '01'){
                $("#cardNumberStatus").html("一致");
                $("#phoneStatus").html("一致");
                $("#userNameStatus").html("不一致");
                $(".userName").css("color","#E84651");
                $(".phone").css("color","#54BB3D");
                $(".cardNumber").css("color","#54BB3D");
            }else if (data.checkResult == '02'){
                $("#cardNumberStatus").html("一致");
                $("#phoneStatus").html("一致");
                $("#userNameStatus").html("为空");
                $(".userName").css("color","#E84651");
                $(".phone").css("color","#54BB3D");
                $(".cardNumber").css("color","#54BB3D");
            }else if (data.checkResult == '03'){
                $("#cardNumberStatus").html("不一致");
                $("#phoneStatus").html("一致");
                $("#userNameStatus").html("一致");
                $(".userName").css("color","#54BB3D");
                $(".phone").css("color","#54BB3D");
                $(".cardNumber").css("color","#E84651");
            }else if (data.checkResult == '04'){
                $("#cardNumberStatus").html("不一致");
                $("#phoneStatus").html("一致");
                $("#userNameStatus").html("不一致");
                $(".userName").css("color","#E84651");
                $(".phone").css("color","#54BB3D");
                $(".cardNumber").css("color","#E84651");
            }else if (data.checkResult == '05'){
                $("#cardNumberStatus").html("一致");
                $("#phoneStatus").html("一致");
                $("#userNameStatus").html("为空");
                $(".userName").css("color","#E84651");
                $(".phone").css("color","#54BB3D");
                $(".cardNumber").css("color","#54BB3D");
            }else if (data.checkResult == '06'){
                $("#cardNumberStatus").html("为空");
                $("#phoneStatus").html("一致");
                $("#userNameStatus").html("一致");
                $(".userName").css("color","#54BB3D");
                $(".phone").css("color","#54BB3D");
                $(".cardNumber").css("color","#E84651");
            }else if (data.checkResult == '07'){
                $("#cardNumberStatus").html("为空");
                $("#phoneStatus").html("一致");
                $("#userNameStatus").html("不一致");
                $(".userName").css("color","#E84651");
                $(".phone").css("color","#54BB3D");
                $(".cardNumber").css("color","#E84651");
            }else if (data.checkResult == '08'){
                $("#cardNumberStatus").html("为空");
                $("#phoneStatus").html("一致");
                $("#userNameStatus").html("为空");
                $(".userName").css("color","#E84651");
                $(".phone").css("color","#54BB3D");
                $(".cardNumber").css("color","#E84651");
            }
        } else {
            $MB.n_danger(r.msg);
        }
    });
}