$(document).ready(function () {
    $('.inputBox').keydown(function (event) {
        if (event.which === 13) {
            getData($('.inputBox').val());
        }
    })
});
function getData(urls) {
    getRequest(
        '/data/collect?urls='+urls,
        function (res) {
            alert("成功加入");
        },
        function (error) {
            alert(error);
        });
}