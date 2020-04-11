$(document).ready(function () {
    getVIP();
});

function getVIP() {

    getRequest(
        '/vip/getVIPInfo',
        function (res) {
            console.log(res);
            if (res.success) {
                $("#member-buy-price").text(res.content.price+"元");
                $("#member-description").text("充" + res.content.target+"元送"+res.content.discount + "元");
            } else {
                alert(res.content);
            }

        },
        function (error) {
            alert(error);
        });
}

function changeClick() {
    $("#vipCardModal").show();
    $("#vipCard-edit-form-btn").click(function () {
        var form={
            price: $("#vipCard-price").val(),
            target:$("#vipCard-target").val(),
            discount:$("#vipCard-discount").val()
        };
        console.log(form);
        postRequest(
            "/vip/setVIPInfo",
            form,
            function (res) {
                getVIP();
                $("#vipCardModal").modal('hide');
            },
            function (error) {
                alert(error);
            });
    });
}