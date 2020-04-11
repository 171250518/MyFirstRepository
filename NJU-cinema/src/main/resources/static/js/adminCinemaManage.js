var PromotionChoose="0";
var HallChoose="0";
$(document).ready(function() {

    var canSeeDate = 0;
    getCanSeeDayNum();
    getCinemaHalls();
    getPromotion();

    $('#canview-modify-btn').click(function () {
        $("#canview-modify-btn").hide();
        $("#canview-set-input").val(canSeeDate);
        $("#canview-set-input").show();
        $("#canview-confirm-btn").show();
    });


    $('#refund-add-btn').click(function () {
        var Low = parseInt($('#refund-low-input').val());
        var High = parseInt($('#refund-high-input').val());
        var refundRate = parseFloat($('#refund-rate').val());
        var canOrnot = $('#can-refund').val();
        postRequest('', {Low: Low, High: High, refundRate: refundRate, canOrnot: canOrnot},
            function (res) {
                getPromotion();

            }, function (error) {
                alert(error);
            })


    })




    $('#hall-add-btn').click(function () {
        newname = $('#add-name-input').val();
        column = parseInt($('#add-column-input').val());
        row = parseInt($('#add-row-input').val())
        postRequest("/hall/add", {id: -1, name: newname, column: column, row: row}, function (res) {
            if (res.success) {
                getCinemaHalls();
                $("#hallModal").modal('hide');
                alert("成功添加")
            }
            else {
                alert(res.content)
            }

        }, function (error) {
            alert(JSON.stringify(error))

        })
        HallChoose = '0';

    })


    $('#modify-btn').click(function () {
        column = parseInt($('#modify-column-input').val());
        row = parseInt($('#modify-row-input').val());

        postRequest("/hall/update", {id: parseInt(HallChoose), name: '', column: column, row: row}, function (res) {
            if (res.success) {
                getCinemaHalls();
                $("#hallModifyModal").modal('hide');
                alert("成功修改")
            }
            else {
                alert(res.content)
            }

        }, function (error) {
            alert(JSON.stringify(error))

        })

        HallChoose = '0';
    })


    $('#refund-add-btn').click(function () {
        var low = parseInt($('#refund-low-input').val());
        var high = parseInt($('#refund-high-input').val());
        var canOrnot = $("input[name='add-can-refund']:checked").val();
        var refundRate = parseFloat($('#refund-rate').val());
        postRequest('/ticket/refund/promotion/add', {
                number: -1,
                high: high,
                low: low,
                canOrnot: canOrnot,
                refundRate: refundRate
            },
            function (res) {
                if(res.success){
                    alert("添加成功")
                    getPromotion();
                    $("#refundModal").modal('hide');
                }
                else {
                    alert("添加失败")
                }
            }, function (error) {
                alert(JSON.stringify(error));
            })


    })

    $('#refund-edit-btn').click(function () {
        var low = parseInt($('#edit-low-input').val());
        var high = parseInt($('#edit-high-input').val());
        var canOrnot = $("input[name='can-refund']:checked").val();
        var refundRate = parseFloat($('#edit-rate').val());
        postRequest('/ticket/refund/promotion/update', {
                number: parseInt(PromotionChoose),
                high: high,
                low: low,
                canOrnot: canOrnot,
                refundRate: refundRate
            },
            function (res) {
                if (res.success) {
                    alert("修改成功")
                    $("#refundEditModal").modal('hide');
                    getPromotion();
                }
                else {
                    alert("修改失败")
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        )
        PromotionChoose = "0";

    })


    $('#canview-confirm-btn').click(function () {
        var dayNum = $("#canview-set-input").val();
        // 验证一下是否为数字
        postRequest(
            '/schedule/view/set',
            {day: dayNum},
            function (res) {
                if (res.success) {
                    getCanSeeDayNum();
                    canSeeDate = dayNum;
                    $("#canview-modify-btn").show();
                    $("#canview-set-input").hide();
                    $("#canview-confirm-btn").hide();
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    })
});


function getCinemaHalls() {
    var halls = [];
    getRequest(
        '/hall/all',
        function (res) {
            halls = res.content;
            renderHall(halls);
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}
function renderHall(halls) {
    $('#hall-card').empty();
    var hallDomStr = "";
    for (var k = 0; k < halls.length; k++) {
        hallId = halls[k].id;
        var seat = "";
        for (var i = 0; i < halls[k].row; i++) {
            var temp = "";
            for (var j = 0; j < halls[k].column; j++) {
                temp += "<div class='cinema-hall-seat'></div>";
            }
            seat += "<div>" + temp + "</div>";
        }
        var hallDom =
            "<div class='cinema-hall'>" +
            "<div>" +
            "<span class='cinema-hall-name'>" + halls[k].name + "</span>" +
            "<span class='cinema-hall-size'>" + halls[k].column + '*' + halls[k].row + "</span>" +
            "<a data-backdrop='static' data-toggle='modal' data-target='#hallModifyModal' style='margin-left: 20px;' class='primary-text'  onclick='SaveHallChoose(" + hallId + ")'  id='hall" + (k + 1) + "-modify-btn'>修改 </a>" +
            "<a style='color: red;'   onclick='HallDelete(" + hallId + ")' id='hall" + (k + 1) + "-del-btn'>删除</a>" +
            "</div>" +
            "<div class='cinema-seat'>" + seat +
            "</div>" +
            "</div>";
        hallDomStr += hallDom;
    }
    $('#hall-card').append(hallDomStr);
}
function getCanSeeDayNum() {
    getRequest(
        '/schedule/view',
        function (res) {
            canSeeDate = res.content;
            $("#can-see-num").text(canSeeDate);
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}
function getPromotion() {
    getRequest('/ticket/refund/promotion/all', function (res) {
        var content;
        var can;
        data = res.content;
        for (var i = 0; i < data.length; i++) {
            can = (data[i].canOrnot == 1) ? "是" : "否";
            Id = data[i].id;
            content += "<tr>" +
                "<td>" + Id + "</td>" +
                "<td>" + data[i].low + '元-' + data[i].high + '元' + "</td>" +
                "<td>" + can + "</td>" +
                "<td>" + data[i].refundRate + "</td>" +
                "<td><a class='primary-text'  id='refund" + (i + 1) + "-modify-btn' onclick='SavePromotionChoose(" + Id + ")' data-backdrop='static' data-toggle='modal' data-target='#refundEditModal'  >修改</a>" + "<a style='color: red;'    onclick='deleteRefundPromotion(" + Id + " )' id='refund" + (i + 1) + "-del-btn'>删除</a></td>" +
                "</tr>";


        }

        var Promotion =
            "<tbody>" +
            "<tr>" +
            "<td><b>序号</b></td>" +
            "<td><b>消费区间</b></td>" +
            "<td><b>是否可以退票</b></td>" +
            "<td><b>退款比例</b></td>" +
            "<td><b>操作</b></td>" +
            "</tr>" + content + "</tbody>"
        ;
        $('#wtf').html(Promotion);


    }, function (error) {
        alert(JSON.stringify(error));
    })
}
function SavePromotionChoose(i) {
    PromotionChoose = i;
}
function SaveHallChoose(i) {
    HallChoose = i;
}
function deleteRefundPromotion(Id) {
    getRequest('/ticket/refund/promotion/delete/' + parseInt(Id), function (res) {
        if (res.success) {
            getPromotion();
            alert("删除成功")
        }
        else {
            alert("删除失败")
        }
    }, function (error) {
        alert(JSON.stringify(error));
    })

}
function HallDelete(i) {
    getRequest('/hall/delete/' + parseInt(i), function (res) {
        if (res.success) {
            getCinemaHalls();
            alert("删除成功")
        }
        else {
            alert("删除失败")
        }
    }, function (error) {
        alert(JSON.stringify(error));

    })

}