$(document).ready(function () {
    renderOrders();
});


function renderOrders() {
    $(".order-on-list").empty();
    getRequest(
        '/ticket/order/get/' + sessionStorage.getItem('id'),
        function (res) {
            if(res.success){
                var orders = res.content;
                var orderLine = "";
                var i = 0;
                while(true){
                    if(i >= orders.length){
                        break;
                    } else if(i == orders.length-1){
                        var order = orders[i];
                        var tickets = order.ticketList;
                        var seats = "";
                        for(var j = 0; j < tickets.length; j++){
                            var ticket = tickets[j];
                            seats += (ticket.row_index+1) + "排" + (ticket.column_index+1) + "座";
                            if(j!=ticket.length-1){
                                seats+=" ";
                            }
                        }


                        orderLine += "<li >\n" +
                            "                        <div class='order-item card col-md-6'>\n" +
                            "                            <img class='movie-img'\n" +
                            "                                 src='"+  tickets[0].poster_url +"'>\n" +
                            "                            <div class='order-info'>\n" +
                            "                                <div class='movie-title' ><span class='primary-text' style='font-size: 25px'>"+ tickets[0].movie_name +" "+  tickets.length +"张</span></div>\n" +
                            "                                <div class='order-time'><span>开场时间："+ format(tickets[0].start_time) +"</span>\n" +
                            "                                </div>\n" +
                            "                                <div>"+ tickets[0].hall_name +" "+ seats + "</div>\n" +
                            "                                <div>总价："+ order.fare +"元</div>\n" +
                            "                                <div style='display: flex'>\n" +
                            "                                    <div class='order-operation'><a href='/user/orderDetail?orderTime="+ order.order_time +"' class='order-detail' style='color: #007DD3'>订单详情</a></div>\n" +
                            "                                </div>\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                    </li>";
                        i++;
                    } else{
                        var order1 = orders[i];
                        var tickets1 = order1.ticketList;
                        var seats1 = "";
                        for(var j = 0; j < tickets1.length; j++){
                            var ticket = tickets1[j];
                            seats1 += (ticket.row_index+1) + "排" + (ticket.column_index+1) + "座";
                            if(j!=ticket.length-1){
                                seats1+=" ";
                            }
                        }
                        var order2 = orders[i+1];
                        var tickets2 = order2.ticketList;
                        var seats2 = "";
                        for(var j = 0; j < tickets2.length; j++){
                            var ticket = tickets2[j];
                            seats2 += (ticket.row_index+1) + "排" + (ticket.column_index+1) + "座";
                            if(j!=ticket.length-1){
                                seats2+=" ";
                            }
                        }
                        orderLine += "<li >\n" +
                            "                        <div class='order-item card col-md-6'>\n" +
                            "                            <img class='movie-img'\n" +
                            "                                 src='"+  tickets1[0].poster_url +"'>\n" +
                            "                            <div class='order-info'>\n" +
                            "                                <div class='movie-title' ><span class='primary-text' style='font-size: 25px'>"+ tickets1[0].movie_name +" "+  tickets1.length +"张</span></div>\n" +
                            "                                <div class='order-time'><span>开场时间："+ format(tickets1[0].start_time) +"</span>\n" +
                            "                                </div>\n" +
                            "                                <div>"+ tickets1[0].hall_name +" "+ seats1 + "</div>\n" +
                            "                                <div>总价："+ order1.fare +"元</div>\n" +
                            "                                <div style='display: flex'>\n" +
                            "                                    <div class='order-operation'><a href='/user/orderDetail?orderTime="+ order1.order_time +"' class='order-detail' style='color: #007DD3'>订单详情</a></div>\n" +
                            "                                </div>\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                        <div class='order-item card col-md-6'>\n" +
                            "                            <img class='movie-img'\n" +
                            "                                 src='"+  tickets2[0].poster_url +"'>\n" +
                            "                            <div class='order-info'>\n" +
                            "                                <div class='movie-title' ><span class='primary-text' style='font-size: 25px'>"+ tickets2[0].movie_name +" "+  tickets2.length +"张</span></div>\n" +
                            "                                <div class='order-time'><span>开场时间："+ format(tickets2[0].start_time) +"</span>\n" +
                            "                                </div>\n" +
                            "                                <div>"+ tickets2[0].hall_name +" "+ seats2 + "</div>\n" +
                            "                                <div>总价："+ order2.fare +"元</div>\n" +
                            "                                <div style='display: flex'>\n" +
                            "                                    <div class='order-operation'><a href='/user/orderDetail?orderTime="+ order2.order_time +"' class='order-detail' style='color: #007DD3'>订单详情</a></div>\n" +
                            "                                </div>\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                    </li>";
                        i += 2;
                    }
                }
                $(".order-on-list").append(orderLine);
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