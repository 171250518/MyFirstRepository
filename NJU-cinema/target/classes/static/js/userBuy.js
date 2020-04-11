$(document).ready(function () {
    getMovieList();

    function getMovieList() {
        getRequest(
            '/ticket/get/' + sessionStorage.getItem('id'),
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空
    function renderTicketList(list) {
        var div = "" ;
        for (var i=list.length-1; i >= 0; i--)
        {
            var schedule = list[i].schedule;
            if(schedule==null){
                continue;
            }
            if(list[i].state=="已失效") {
                div = div + "<tr>\n" +
                    "                <td>" + schedule.movieName + "</td>" +
                    "                <td>" + schedule.hallName + "</td>" +
                    "                <td>" + (list[i].rowIndex + 1) + "排" + (list[i].columnIndex + 1) + "座</td>" +
                    "                <td>" + getLocalTime(schedule.startTime) + "</td>" +
                    "                <td>" + getLocalTime(schedule.endTime) + "</td>" +
                    "                <td style='color: red'>" + list[i].state + "</td>" +
                    "                </tr>";
            }else {
                div = div + "<tr>\n" +
                    "                <td>" + schedule.movieName + "</td>" +
                    "                <td>" + schedule.hallName + "</td>" +
                    "                <td>" + (list[i].rowIndex + 1) + "排" + (list[i].columnIndex + 1) + "座</td>" +
                    "                <td>" + getLocalTime(schedule.startTime) + "</td>" +
                    "                <td>" + getLocalTime(schedule.endTime) + "</td>" +
                    "                <td>" + list[i].state + "</td>" +
                    "                </tr>";
            }
        }

        $("#tickets").append(div);

    }

});

function getLocalTime(nS) {
    var date = new Date(nS);
    var year = date.getFullYear();
    var month = formatzero(date.getMonth()+1);
    var day = formatzero(date.getDate());
    var hour = formatzero(date.getHours());
    var min = formatzero(date.getMinutes());
    var seconds = formatzero(date.getSeconds());
    return year+"-"+month+"-"+day+" "+hour+":"+min+":"+seconds;
}

function formatzero(num) {
    return num < 10 ? '0' + num : num;
}
