var orderTime;

$(document).ready(function () {
    orderTime = window.location.href.split('?')[1].split('=')[1];
   renderOrder();
});

function renderOrder() {
    getRequest(
        '/ticket/order/detail/' + orderTime + '/' + sessionStorage.getItem('id'),
        function (res) {
            if(res.success){
                var order = res.content;
                var tickets = order.ticketList;
                var seats = "";
                for(var j = 0; j < tickets.length; j++){
                    var ticket = tickets[j];
                    seats += (ticket.row_index+1) + "排" + (ticket.column_index+1) + "座";
                    if(j!=ticket.length-1){
                        seats+=" ";
                    }
                }
                $('#order-detail').html("<div class='content-header'><img class='movie-img' style='height: 190px;width: 150px;' src='"+ tickets[0].poster_url +"'>\n" +
                    "                <div class='order-info'>\n" +
                    "                    <div class='movie-title'><span class='primary-text' style='font-size: 25px'>"+ tickets[0].movie_name +" "+ tickets.length +"张</span></div>\n" +
                    "                    <div class='order-time'><span>开场时间："+ format(tickets[0].start_time) +"</span>\n" +
                    "                    </div>\n" +
                    "                    <div>"+ tickets[0].hall_name +" "+ seats + "</div>\n" +
                    "                    <div>总价："+ order.fare +"元</div>\n" +
                    "                    <div class='order-operations'>\n" +
                    "                        <button id='"+ order.fare +"' type='button' onclick='refund(this.id)'  class='btn btn-primary' style='background: red'><span>退款</span></button>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>");
            }
            else {
                alert('加载失败');
            }
        },
        function (error) {
            alert(error);
        }
    )

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

function refund(amount) {
    getRequest(
        '/ticket/refund/promotion/search/' + amount,
        function (res) {
            if(res.success){
                if(res.content.length==0||res.content[0].canOrnot == 0){
                    alert("不可退款");
                }
                else{
                    var refund = amount*res.content[0].refundRate;
                    getRequest(
                        '/ticket/refund/' + sessionStorage.getItem('id') +'/'+refund,
                        function (res) {
                        },
                        function (error) {
                            alert(error);
                        }
                    );

                    getRequest(
                        '/ticket/delete/ticket/' + sessionStorage.getItem('id') + '/' + orderTime,
                        function (res) {
                            alert("退款成功！退款金额："+refund+"元。");
                            window.location.href='/user/order';
                        },
                        function (error) {
                            alert(error);
                        }
                    )

                }

            }
        }
    )
}