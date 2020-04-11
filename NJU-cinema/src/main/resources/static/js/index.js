$(document).ready(function () {
   getHotMovies();
   getMyOrders();
});

function getHotMovies() {
    getRequest(
        '/statistics/popular/movie?days=7&movieNum=5',
        function (res) {
            console.log(res.content);
            renderHotMovies(res.content)
        },
        function (error) {
            alert(error);
        }
    )
}

function renderHotMovies(movies) {
    $('#hot-movies').html('');
    var domStr='';
    for(var i=0;i<movies.length;i++){
        domStr+='<li>\n' +
            '    <img style="width: 100%;height: 600px" src='+movies[i].posterUrl+' class="img-responsive" alt="" id="hotMovie'+movies[i].movieId+'" onclick="goToMovie(this.id)"/>\n' +
            '</li>\n';
    }
    $('#hot-movies').append(domStr);

}


function goToMovie(movieId) {
    window.location.href='/user/movieDetail?id='+movieId.substring(8,movieId.length);
}

function getMyOrders() {
    getRequest(
        '/ticket/order/get/' + sessionStorage.getItem('id'),
        function (res) {
            if(res.success){
                console.log(res.content);
                renderMyOrders(res.content);
            }
        },
        function (error) {
            alert(error);
        }
    )
}

function renderMyOrders(orders) {
    $('#my-orders-list').html('');
    var domStr='';
    for(var i=0;i<orders.length;i++){
        var seats='';
        for(var j = 0; j < orders[i].ticketList.length; j++){
            var ticket = orders[i].ticketList[j];
            seats += (ticket.row_index+1) + "排" + (ticket.column_index+1) + "座";
            if(j!=ticket.length-1){
                seats+=" ";
            }
        }
        domStr+='<li class="nbs-flexisel-item">\n' +
            '        <a href="#"><img src="'+orders[i].ticketList[0].poster_url+'" alt=""></a>\n' +
            '    <div class="slide-title"><h4>'+orders[i].ticketList[0].movie_name+' '+orders[i].ticketList.length+'张</h4></div>\n' +
            '    <div class="date-city">\n' +
            '            <h5>'+format(orders[i].ticketList[0].start_time)+'</h5>\n' +
            '            <h6>'+orders[i].ticketList[0].hall_name+' '+seats+'</h6>\n' +
            '        <div class="buy-tickets">\n' +
            '            <a href="/user/orderDetail?orderTime='+ orders[i].order_time +'">订单详情</a>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</li>';
    }
    $('#my-orders-list').append(domStr);
}

function add0(m) {
    return m < 10 ? '0' + m : m
}

function format(timeStamp) {
    var date=new Date(timeStamp);
    var month=date.getMonth()+1;
    var day=date.getDate();
    var hour=date.getHours();
    var minute=date.getMinutes();
    return add0(month)+'/'+add0(day)+' '+add0(hour)+':'+add0(minute);
}