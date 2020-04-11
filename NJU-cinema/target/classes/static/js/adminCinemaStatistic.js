$(document).ready(function() {


    getScheduleRate();
    
    getBoxOffice();

    getAudiencePrice();


    function getScheduleRate() {

        getRequest(
            '/statistics/scheduleRate',
            function (res) {
                var data = res.content||[];
                var tableData = data.map(function (item) {
                   return {
                       value: item.time,
                       name: item.name
                   };
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var option = {
                    title : {
                        text: '今日排片率',
                        subtext: new Date().toLocaleDateString(),
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        x : 'center',
                        y : 'bottom',
                        data:nameList
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {
                                show: true,
                                type: ['pie', 'funnel']
                            },
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    series : [
                        {
                            name:'面积模式',
                            type:'pie',
                            radius : [30, 110],
                            center : ['50%', '50%'],
                            roseType : 'area',
                            data:tableData
                        }
                    ]
                };
                var scheduleRateChart = echarts.init($("#schedule-rate-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function getBoxOffice() {

        getRequest(
            '/statistics/boxOffice/total',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    return item.boxOffice;
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var option = {
                    title : {
                        text: '所有电影票房',
                        subtext: '截止至'+new Date().toLocaleDateString(),
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: nameList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: tableData,
                        type: 'bar'
                    }]
                };
                var scheduleRateChart = echarts.init($("#box-office-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    function getAudiencePrice() {
        getRequest(
            '/statistics/audience/price',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    return item.price;
                });
                var nameList = data.map(function (item) {
                    return formatDate(new Date(item.date));
                });
                var option = {
                    title : {
                        text: '每日客单价',
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: nameList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: tableData,
                        type: 'line'
                    }]
                };
                var scheduleRateChart = echarts.init($("#audience-price-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    $("#confirm-date-input").click(function () {
        getPlacingRate();
    });

    $("#confirm").click(function () {
        getPopularMovie();
    });
});


function getPopularMovie() {
    // todo
    var days=$("#days").val();
    var movieNum=$("#movieNum").val();
    getRequest(
        //获取最近days天内，最受欢迎的movieNum个电影(可以简单理解为最近days内票房越高的电影越受欢迎)
        // '/statistics/boxOffice/total',
        '/statistics/popular/movie?days='+days+'&movieNum='+movieNum,
        function (res) {
            var data = res.content || [];
            var tableData = data.map(function (item) {
                return item.boxOffice;
            });
            var nameList = data.map(function (item) {
                return item.name;
            });
            var option = {
                title : {
                    text: '近'+days+'天最受欢迎的'+movieNum+'部电影',
                    x:'center'
                },
                xAxis: {
                    type: 'category',
                    data: nameList
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: tableData,
                    type: 'bar'
                }]
            };
            var scheduleRateChart = echarts.init($("#popular-movie-container")[0]);
            scheduleRateChart.setOption(option);
        },
        function (error) {
            alert(JSON.stringify(error));
        });
}

function getPlacingRate() {
    var startDate = $('#start-date-input').val();
    var endDate = $('#end-date-input').val();
    getRequest(
        '/statistics/PlacingRate/?startDate='+startDate+" 00:00:00&endDate="+endDate+" 00:00:00",
        function (res) {
            var data = res.content || [];
            var tableData = data.map(function (item) {
                return item.placingRate;
            });
            var nameList = data.map(function (item) {
                return item.movieName;
            });
            var option = {
                title : {
                    text: startDate+'至'+endDate+'电影上座率统计',
                    x:'center'
                },
                xAxis: {
                    type: 'category',
                    data: nameList
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: tableData,
                    type: 'bar'
                }]
            };
            var scheduleRateChart = echarts.init($("#place-rate-container")[0]);
            scheduleRateChart.setOption(option);
        },
        function (error) {
            alert(JSON.stringify(error));
        })
}