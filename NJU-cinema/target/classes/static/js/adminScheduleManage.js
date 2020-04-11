var colors = [
    '#FF6666',
    '#3399FF',
    '#FF9933',
    '#66cccc',
    '#FFCCCC',
    '#9966FF',
    'steelblue'
];

$(document).ready(function() {
    var hallId,
        scheduleDate = formatDate(new Date()),
        schedules = [];

    initSelectAndDate();

    function getSchedules() {

        getRequest(
            '/schedule/search?hallId='+hallId+'&startDate='+scheduleDate.replace(/-/g,'/'),
            function (res) {
                schedules = res.content;
                renderScheduleTable(schedules);
             },
            function (error) {
                alert(JSON.stringify(error));
             }
        );
    }

    function renderScheduleTable(schedules){
        $('.schedule-date-container').empty();
        $(".schedule-time-line").siblings().remove();
        schedules.forEach(function (scheduleOfDate) {
            $('.schedule-date-container').append("<div class='schedule-date'>"+formatDate(new Date(scheduleOfDate.date))+"</div>");
            var scheduleDateDom = $(" <ul class='schedule-item-line'></ul>");
            $(".schedule-container").append(scheduleDateDom);
            scheduleOfDate.scheduleItemList.forEach(function (schedule,index) {
                var scheduleStyle = mapScheduleStyle(schedule);
                var scheduleItemDom =$(
                    "<li id='schedule-"+ schedule.id +"' class='schedule-item' data-schedule='"+JSON.stringify(schedule)+"' style='background:"+scheduleStyle.color+";top:"+scheduleStyle.top+";height:"+scheduleStyle.height+"'>"+
                    "<span>"+schedule.movieName+"</span>"+
                    "<span class='error-text'>¥"+schedule.fare+"</span>"+
                    "<span>"+formatTime(new Date(schedule.startTime))+"-"+formatTime(new Date(schedule.endTime))+"</span>"+
                    "</li>");
                scheduleDateDom.append(scheduleItemDom);
            });
        })
    }

    function mapScheduleStyle(schedule) {
        var start = new Date(schedule.startTime).getHours()+new Date(schedule.startTime).getMinutes()/60,
            end = new Date(schedule.endTime).getHours()+new Date(schedule.endTime).getMinutes()/60 ;
        return {
            color: colors[schedule.movieId%colors.length],
            top: 40*start+'px',
            height: 40*(end-start)+'px'
        }
    }

    function initSelectAndDate() {
        $('#schedule-date-input').val(scheduleDate);
        getCinemaHalls();
        getAllMovies();
        
        // 过滤条件变化后重新查询
        $('#hall-select').change (function () {
            hallId=$(this).children('option:selected').val();
            getSchedules();
        });
        $('#schedule-date-input').change(function () {
            scheduleDate = $('#schedule-date-input').val();
            getSchedules();
        });
    }

    function getCinemaHalls() {
        var halls = [];
        getRequest(
            '/hall/all',
            function (res) {
                console.log(res.content);
                halls = res.content;
                hallId = halls[0].id;
                halls.forEach(function (hall) {
                    $('#hall-select').append("<option value="+ hall.id +">"+hall.name+"</option>");
                    $('#schedule-hall-input').append("<option value="+ hall.id +">"+hall.name+"</option>");
                    $('#schedule-edit-hall-input').append("<option value="+ hall.id +">"+hall.name+"</option>");
                });
                getSchedules();
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function getAllMovies() {
        getRequest(
            '/movie/all/exclude/off',
            function (res) {
                var movieList = res.content;
                movieList.forEach(function (movie) {
                    $('#schedule-movie-input').append("<option value="+ movie.id +">"+movie.name+"</option>");
                    $("#schedule-edit-movie-input").append("<option value="+ movie.id +">"+movie.name+"</option>");
                });
            },
            function (error) {
                alert(error);
            }
        );
    }

    $(document).on('click','.schedule-item',function (e) {
        var schedule = JSON.parse(e.target.dataset.schedule);
        $("#schedule-edit-hall-input").children('option[value='+schedule.hallId+']').attr('selected',true);
        $("#schedule-edit-movie-input").children('option[value='+schedule.movieId+']').attr('selected',true);
        $("#schedule-edit-start-date-input").val(schedule.startTime.slice(0,16));
        $("#schedule-edit-end-date-input").val(schedule.endTime.slice(0,16));
        $("#schedule-edit-price-input").val(schedule.fare);
        $('#scheduleEditModal').modal('show');
        $('#scheduleEditModal')[0].dataset.scheduleId = schedule.id;
        console.log(schedule);
    });
    
    $('#schedule-form-btn').click(function () {
        var form = {
            hallId: $("#schedule-hall-input").children('option:selected').val(),
            movieId : $("#schedule-movie-input").children('option:selected').val(),
            startTime: $("#schedule-start-date-input").val(),
            endTime: $("#schedule-end-date-input").val(),
            fare: $("#schedule-price-input").val()
        };
        //todo 需要做一下表单验证？


        if(form.startTime==""){
            $("#schedule-start-date-input").parent().append($("<strong class='high' style='color: red'>*请选择开始时间</strong>"));
        }
        if(earlier(form.startTime)) {
            $("#schedule-start-date-input").parent().append($("<strong class='high' style='color: red'>*开始时间必须晚于当前时间</strong>"));
        }
        if(form.endTime==""){
            $("#schedule-end-date-input").parent().append($("<strong class='high' style='color: red'>*请选择结束时间</strong>"));
        }
        getRequest(
            '/movie/get/' + form.movieId,
            function (res) {
                if(earlierthan(form.startTime,form.endTime,res.content)) {
                    $("#schedule-end-date-input").parent().append($("<strong class='high' style='color: red'>*结束时间必须晚于开始时间，且起止时间段必须大于电影时长</strong>"));
                }
            },
            function (error) {
                alert(error);
            }
        );
        if(form.fare==""){
            $("#schedule-price-input").parent().append($("<strong class='high' style='color: red'>*请输入票价</strong>"));
        }
        else if(form.fare<=0){
            $("#schedule-price-input").parent().append($("<strong class='high' style='color: red'>*票价必须高于0元</strong>"));
        }



        function earlier(time){
            var date = new Date();
            var t1 = time.split("T")[0];
            var t2 = time.split("T")[1];
            var t = t1.split("-").concat(t2.split(":"));
            if(date.getFullYear()>t[0]){

                return true;
            }
            else if(date.getFullYear()==t[0]){
                if(date.getMonth()>t[1]){
                    return true;
                }
                else if(date.getMonth()+1==t[1]){
                    if(date.getDate()>t[2]){
                        return true;
                    }
                    else if(date.getDate()==t[2]){
                        if(date.getHours()>t[3]){
                            return true;
                        }
                        else if(date.getHours()==t[3]){
                            if(date.getMinutes()>t[4]){
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        function earlierthan(time1,time2,movie){

            var movieLength = movie.length;
            var movieHours = movieLength/60;
            var moveMinutes = movieLength%60;
            var t1 = time2.split("T")[0];
            var t2 = time2.split("T")[1];
            var t3 = t1.split("-").concat(t2.split(":"));
            var t4 = time1.split("T")[0];
            var t5 = time1.split("T")[1];
            var t6 = t4.split("-").concat(t5.split(":"));
            if(t6[0]>t3[0]){
                return true;
            }
            else if(t6[0]==t3[0]){
                if(t6[1]>t3[1]){
                    return true;
                }
                else if(t6[1]==t3[1]){
                    if(t6[2]>t3[2]){
                        return true;
                    }
                    else if(t6[2]==t3[2]){
                        if(parseInt(t6[3]+movieHours)>parseInt(t3[3])){
                            return true;
                        }
                        else if(t6[3]==t3[3]){
                            if(parseInt(t6[4]+moveMinutes)>parseInt(t3[4])){
                                return true;
                            }
                        }
                    }
                }
            }
            return false;

        }



        postRequest(
            '/schedule/add',
            form,
            function (res) {
                if(res.success){
                    getSchedules();
                    $("#scheduleModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    $('#schedule-edit-form-btn').click(function () {
        var form = {
            id: Number($('#scheduleEditModal')[0].dataset.scheduleId),
            hallId: $("#schedule-edit-hall-input").children('option:selected').val(),
            movieId : $("#schedule-edit-movie-input").children('option:selected').val(),
            startTime: $("#schedule-edit-start-date-input").val(),
            endTime: $("#schedule-edit-end-date-input").val(),
            fare: $("#schedule-edit-price-input").val()
        };
        //todo 需要做一下表单验证？
        if(form.startTime==""){
            $("#schedule-edit-start-date-input").parent().append($("<strong class='high' style='color: red'>*请选择开始时间</strong>"));
        }
        if(earlier(form.startTime)) {
            $("#schedule-edit-start-date-input").parent().append($("<strong class='high' style='color: red'>*开始时间必须晚于当前时间</strong>"));
        }
        if(form.endTime==""){
            $("#schedule-edit-end-date-input").parent().append($("<strong class='high' style='color: red'>*请选择结束时间</strong>"));
        }
        getRequest(
            '/movie/get/' + form.movieId,
            function (res) {
                if(earlierthan(form.startTime,form.endTime,res.content)) {
                    $("#schedule-edit-end-date-input").parent().append($("<strong class='high' style='color: red'>*结束时间必须晚于开始时间，且起止时间段必须大于电影时长</strong>"));
                }
            },
            function (error) {
                alert(error);
            }
        );
        if(form.fare==""){
            $("#schedule-edit-price-input").parent().append($("<strong class='high' style='color: red'>*请输入票价</strong>"));
        }
        else if(form.fare<=0){
            $("#schedule-edit-price-input").parent().append($("<strong class='high' style='color: red'>*票价必须高于0元</strong>"));
        }



        function earlier(time){
            var date = new Date();
            var t1 = time.split("T")[0];
            var t2 = time.split("T")[1];
            var t = t1.split("-").concat(t2.split(":"));
            if(date.getFullYear()>t[0]){

                return true;
            }
            else if(date.getFullYear()==t[0]){
                if(date.getMonth()>t[1]){
                    return true;
                }
                else if(date.getMonth()+1==t[1]){
                    if(date.getDate()>t[2]){
                        return true;
                    }
                    else if(date.getDate()==t[2]){
                        if(date.getHours()>t[3]){
                            return true;
                        }
                        else if(date.getHours()==t[3]){
                            if(date.getMinutes()>t[4]){
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        function earlierthan(time1,time2,movie){

            var movieLength = movie.length;
            var movieHours = movieLength/60;
            var moveMinutes = movieLength%60;
            var t1 = time2.split("T")[0];
            var t2 = time2.split("T")[1];
            var t3 = t1.split("-").concat(t2.split(":"));
            var t4 = time1.split("T")[0];
            var t5 = time1.split("T")[1];
            var t6 = t4.split("-").concat(t5.split(":"));
            if(t6[0]>t3[0]){
                return true;
            }
            else if(t6[0]==t3[0]){
                if(t6[1]>t3[1]){
                    return true;
                }
                else if(t6[1]==t3[1]){
                    if(t6[2]>t3[2]){
                        return true;
                    }
                    else if(t6[2]==t3[2]){
                        if(parseInt(t6[3]+movieHours)>parseInt(t3[3])){
                            return true;
                        }
                        else if(t6[3]==t3[3]){
                            if(parseInt(t6[4]+moveMinutes)>parseInt(t3[4])){
                                return true;
                            }
                        }
                    }
                }
            }
            return false;

        }


        postRequest(
            '/schedule/update',
            form,
            function (res) {
                if(res.success){
                    getSchedules();
                    $("#scheduleEditModal").modal('hide');
                } else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    $("#schedule-edit-remove-btn").click(function () {
        var r=confirm("确认要删除该排片信息吗")
        if (r) {
            deleteRequest(
                '/schedule/delete/batch',
                {scheduleIdList:[Number($('#scheduleEditModal')[0].dataset.scheduleId)]},
                function (res) {
                    if(res.success){
                        getSchedules();
                        $("#scheduleEditModal").modal('hide');
                    } else{
                        alert(res.message);
                    }
                },
                function (error) {
                    alert(JSON.stringify(error));
                }
            );
        }
    })

});

