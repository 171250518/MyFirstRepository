$(document).ready(function () {
    getVIP();
    getCoupon();
    getPromotions();
});

var isBuyState = true;
var vipCardId;
var chargeHistCardShow = false;
var consumeHistCardShow = false;
function getVIP() {
    getRequest(
        '/vip/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            if (res.success) {
                // 是会员
                $("#member-card").css("visibility", "visible");
                $("#member-card").css("display", "");
                $("#nonmember-card").css("display", "none");

                vipCardId = res.content.id;
                $("#member-id").text(res.content.id);
                $("#member-balance").text("¥" + res.content.balance.toFixed(2));
                $("#member-joinDate").text(res.content.joinDate.substring(0, 10));
            } else {
                // 非会员
                $("#member-card").css("display", "none");
                $("#nonmember-card").css("display", "");
            }
        },
        function (error) {
            alert(error);
        });


    getRequest(
        '/vip/getVIPInfo',
        function (res) {
            if (res.success) {
                $("#member-buy-price").text(res.content.price);
                $("#member-buy-description").text("充值优惠：充" + res.content.target + "元送" + res.content.discount + "元。永久有效");
                $("#member-description").text("充" + res.content.target + "元送" + res.content.discount + "元。");
            } else {
                alert(res.content);
            }

        },
        function (error) {
            alert(error);
        });
}

function buyClick() {
    clearForm();
    $('#buyModal').modal('show')
    $("#userMember-amount-group").css("display", "none");
    isBuyState = true;
}

function chargeClick() {
        clearForm();
        $('#chargeModal').modal('show')
        $("#userMember-amount-group").css("display", "");
        isBuyState = false;
}

function clearForm() {
    $('#userMember-form input').val("");
    $('#userMember-form .form-group').removeClass("has-error");
    $('#userMember-form p').css("visibility", "hidden");
}

function confirmCommit() {
    if (validateForm()) {
        if (isBuyState) {
            if ($('#buy-userMember-cardNum').val() === "123123123" && $('#buy-userMember-cardPwd').val() === "123123") {
                postRequest(
                    '/vip/add?userId=' + sessionStorage.getItem('id'),
                    null,
                    function (res) {
                        $('#buyModal').modal('hide');
                        alert("购买会员卡成功");
                        getVIP();
                    },
                    function (error) {
                        alert(error);
                    });
            } else {
                alert("银行卡密码错误！");
            }
        } else {
            if ($('#userMember-cardNum').val() === "123123123" && $('#userMember-cardPwd').val() === "123123") {
                postRequest(
                    '/vip/charge',
                    {vipId: vipCardId, amount: parseInt($('#userMember-amount').val())},
                    function (res) {
                        $('#chargeModal').modal('hide');
                        alert("充值成功");
                        getVIP();
                    },
                    function (error) {
                        alert(error);
                    });
            } else {
                alert("银行卡密码错误！");
            }
        }
    }
}

function validateForm() {
    var isValidate = true;
    if (!isBuyState) {
        if (!$('#userMember-cardNum').val()) {
            isValidate = false;
            $('#userMember-cardNum').parent('.form-group').addClass('has-error');
            $('#userMember-cardNum-error').css("visibility", "visible");
        }
        if (!$('#userMember-cardPwd').val()) {
            isValidate = false;
            $('#userMember-cardPwd').parent('.form-group').addClass('has-error');
            $('#userMember-cardPwd-error').css("visibility", "visible");
        }
        if (!$('#userMember-amount').val() || parseInt($('#userMember-amount').val()) <= 0) {
            isValidate = false;
            $('#userMember-amount').parent('.form-group').addClass('has-error');
            $('#userMember-amount-error').css("visibility", "visible");
        }
    } else {
        if (!$('#buy-userMember-cardNum').val()) {
            isValidate = false;
            $('#buy-userMember-cardNum').parent('.form-group').addClass('has-error');
            $('#buy-userMember-cardNum-error').css("visibility", "visible");
        }
        if (!$('#buy-userMember-cardPwd').val()) {
            isValidate = false;
            $('#buy-userMember-cardPwd').parent('.form-group').addClass('has-error');
            $('#buy-userMember-cardPwd-error').css("visibility", "visible");
        }
    }
    return isValidate;
}

function getCoupon() {
    getRequest(
        '/coupon/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            if (res.success) {
                var couponList = res.content;
                var couponListContent = '';
                for (let coupon of couponList) {
                    couponListContent += '<div class="col-md-6 coupon-wrapper"><div class="coupon"><div class="content">' +
                        '<div class="col-md-8 left">' +
                        '<div class="name">' +
                        coupon.name +
                        '</div>' +
                        '<div class="description">' +
                        coupon.description +
                        '</div>' +
                        '<div class="price">' +
                        '满' + coupon.targetAmount + '减' + coupon.discountAmount +
                        '</div>' +
                        '</div>' +
                        '<div class="col-md-4 right">' +
                        '<div>有效日期：</div>' +
                        '<div>' + formatDate(coupon.startTime) + ' ~ ' + formatDate(coupon.endTime) + '</div>' +
                        '</div></div></div></div>'
                }
                $('#coupon-list').html(couponListContent);

            }
        },
        function (error) {
            alert(error);
        });
}

function formatDate(date) {
    return date.substring(5, 10).replace("-", ".");
}

function chargeHistory() {
    if(!chargeHistCardShow) {
        $("#charge-hist-card").css("display", "");
        chargeHistCardShow = true;
        getRequest(
            '/vip/charges/get?userId=' + sessionStorage.getItem('id'),
            function (res) {
                renderChargesList(res.content);
            },
            function (error) {
                alert(error);
            }
        );
    }else {
        $("#charge-hist-card").css("display", "none");
        chargeHistCardShow = false;
    }
}

function consumeHistory() {
    if(!consumeHistCardShow) {
        $("#consume-hist-card").css("display", "");
        consumeHistCardShow = true;
        getRequest(
            '/vip/consumes/get?userId=' + sessionStorage.getItem('id'),
            function (res) {
                renderConsumesList(res.content);
            },
            function (error) {
                alert(error);

            }
        )
    }else {
        $("#consume-hist-card").css("display", "none");
        consumeHistCardShow = false;
    }
}

function renderChargesList(charges) {
    $("#charges").html('');
    var div = " <tr>\n" +
        "<td><b>充值金额</b></td>\n" +
        "<td><b>余额</b></td>\n" +
        "<td><b>时间</b></td>\n" +
        "<td></td>\n" +
        "</tr>";
    for (var i = 0; i < charges.length; i++) {
        div = div + "<tr>\n" +
            "<td>" + charges[i].charge + "</td>\n" +
            "<td>" + charges[i].balance_after + "</td>\n" +
            "<td>" + format(charges[i].date) + "</td>\n" +
            "<td><a class='charge-detail' data-toggle='modal' data-target='#chargeDetailModal' onclick='showChargeDetail(this.id)' id='charge" + charges[i].id + "' style='color: #007DD3;text-decoration: underline'>详情</a></td>\n" +
            "</tr>"
    }
    $("#charges").append(div);
}
function renderConsumesList(consumes) {
    $("#consumes").html('');
    var div = " <tr>\n" +
        "<td><b>消费金额</b></td>\n" +
        "<td><b>余额</b></td>\n" +
        "<td><b>时间</b></td>\n" +
        "<td></td>\n" +
        "</tr>";
    for (var i = 0; i < consumes.length; i++) {
        div = div + "<tr>\n" +
            "<td>" + consumes[i].consume + "</td>\n" +
            "<td>" + consumes[i].balance_after + "</td>\n" +
            "<td>" + format(consumes[i].date) + "</td>\n" +
            "<td><a class='consume-detail' data-toggle='modal' data-target='#consumeDetailModal' onclick='showConsumeDetail(this.id)' id='consume" + consumes[i].id + "' style='color: #007DD3;text-decoration: underline'>详情</a></td>\n" +
            "</tr>"
    }
    $("#consumes").append(div);
}

function showChargeDetail(chargeId) {
    getRequest(
      '/vip/charge/detail?chargeId='+chargeId.substring(6,chargeId.length),
        function (res) {
            chargeDetailModal(res.content);
        },
        function (error) {
            alert(error);
        }
    );
}
function showConsumeDetail(consumeId) {
    getRequest(
        '/vip/consume/detail?consumeId='+consumeId.substring(7,consumeId.length),
        function (res) {
            consumeDetailModal(res.content);
        },
        function (error) {
            alert(error);
        }
    );
}

function chargeDetailModal(charge) {
    $("#chargeDetail").html('');
    var div="<tr>\n" +
        "<td><b>订单号</b></td>\n" +
        "<td><b>充值金额</b></td>\n" +
        "<td><b>余额</b></td>\n" +
        "<td><b>时间</b></td>\n" +
        "</tr>\n"+
        "<tr>\n" +
        "<td>" + charge.id + "</td>\n" +
        "<td>" + charge.charge + "</td>\n" +
        "<td>" + charge.balance_after + "</td>\n" +
        "<td>" + format(charge.date) + "</td>\n" +
        "</tr>";
    $("#chargeDetail").append(div);
}

function consumeDetailModal(consume) {
    $("#consumeDetail").html('');
    var div="<tr>\n" +
        "<td><b>订单号</b></td>\n" +
        "<td><b>消费金额</b></td>\n" +
        "<td><b>余额</b></td>\n" +
        "<td><b>时间</b></td>\n" +
        "</tr>\n"+
        "<tr>\n" +
        "<td>" + consume.id + "</td>\n" +
        "<td>" + consume.consume + "</td>\n" +
        "<td>" + consume.balance_after + "</td>\n" +
        "<td>" + format(consume.date) + "</td>\n" +
        "</tr>";
    $("#consumeDetail").append(div);
}


function add0(m) {
    return m < 10 ? '0' + m : m
}

function format(timestamp) {
    var time = new Date(timestamp);
    var y = time.getFullYear();
    var m = time.getMonth() + 1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm) + ':' + add0(s);
}

function getPromotions() {
    getRequest(
        '/activity/get/user/' + sessionStorage.getItem('id'),
        function (res) {
            if(res.success){
                renderActivities(res.content);
            }
        },
        function (error) {
            alert(error);
        }
    )
}

function renderActivities(activities) {
    $(".content-activity").empty();
    var activitiesDomStr = "";
    activities.forEach(function (activity) {
        var movieDomStr = "";
        if(activity.movieList.length){
            activity.movieList.forEach(function (movie) {
                movieDomStr += "<li class='activity-movie primary-text'>"+movie.name+"</li>";
            });
        }else{
            movieDomStr = "<li class='activity-movie primary-text'>所有热映电影</li>";
        }

        activitiesDomStr+=
            "<div class='activity-container'>\n" +
            "                    <div class='activity-card card'>\n" +
            "                        <div class='activity-line'><span class='title'>" + activity.name + "</span> <span style='color:#007DD3;'>【消费满"+ activity.coupon.consumeReq +"元领取】</span>\n" +
            "                        </div>\n" +
            "                        <div class='activity-line'><span>活动时间："+ activity.startTime.split('T')[0] +"至"+ activity.endTime.split('T')[0] +"</span></div>\n" +
            "                        <div class='activity-line'><span>参与电影：</span>\n" +
            "                            <ul>\n"+movieDomStr+"</ul>" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class='activity-coupon primary-bg'><span class='title'>优惠券："+ activity.coupon.name +"</span> <span\n" +
            "                            class='title'>满"+ activity.coupon.targetAmount +"减<span class='error-text title'>"+ activity.coupon.discountAmount +"</span></span> <span>"+ activity.coupon.description +"</span>\n" +
            "                    </div></div>";
    });
    $(".content-activity").append(activitiesDomStr);


}