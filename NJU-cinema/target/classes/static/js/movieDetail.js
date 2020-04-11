$(document).ready(function(){

    var movieId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    var userId = sessionStorage.getItem('id');
    var isLike = false;

    getMovie();

    if(sessionStorage.getItem('role') == 2)
        getMovieLikeChart();

    function getMovieLikeChart() {
       getRequest(
           '/movie/' + movieId + '/like/date',
           function(res){
               var data = res.content,
                    dateArray = [],
                    numberArray = [];
               data.forEach(function (item) {
                   dateArray.push(item.likeTime);
                   numberArray.push(item.likeNum);
               });

               var myChart = echarts.init($("#like-date-chart")[0]);

               // 指定图表的配置项和数据
               var option = {
                   title: {
                       text: '想看人数变化表'
                   },
                   xAxis: {
                       type: 'category',
                       data: dateArray
                   },
                   yAxis: {
                       type: 'value'
                   },
                   series: [{
                       data: numberArray,
                       type: 'line'
                   }]
               };

               // 使用刚指定的配置项和数据显示图表。
               myChart.setOption(option);
           },
           function (error) {
               alert(error);
           }
       );
    }
    function DisplayAndHiddenBtn(btnId, type) {
        var currentBtn = document.getElementById(btnId);
        if (type == "d") {
            currentBtn.style.display = "inline";
        }
        else if (type == "h") {
            currentBtn.style.display = "none";
        }
    }
    getRequest(
        '/movie/get/'+movieId,
        function(res){
            DOH(res.content);
        },
        function (error) {
            alert(error);
        }
    );
    function DOH(movie) {
        //根据电影的状态进行隐藏
        if (movie.status) {
            DisplayAndHiddenBtn("modify", "h");
            DisplayAndHiddenBtn("delete-btn", "h");
        } else {
            DisplayAndHiddenBtn("modify", "d");
            DisplayAndHiddenBtn("delete-btn", "d");

        }

    }
    function getMovie() {
        getRequest(
            '/movie/'+movieId + '/' + userId,
            function(res){
                var data = res.content;
                isLike = data.islike;
                repaintMovieDetail(data);
            },
            function (error) {
                alert(error);
            }
        );
    }

    function repaintMovieDetail(movie) {
        $('#like-btn span').text(isLike ? ' 已想看' : ' 想 看');
        $('#movie-img').attr('src',movie.posterUrl);
        $('#movie-name').text(movie.name);
        $('#order-movie-name').text(movie.name);
        $('#movie-description').text(movie.description);
        $('#movie-startDate').text(new Date(movie.startDate).toLocaleDateString());
        $('#movie-type').text(movie.type);
        $('#movie-country').text(movie.country);
        $('#movie-language').text(movie.language);
        $('#movie-director').text(movie.director);
        $('#movie-starring').text(movie.starring);
        $('#movie-writer').text(movie.screenWriter);
        $('#movie-length').text(movie.length);
        if(isLike){
            $('#like-icon').removeClass('fa-heart-o');
            $('#like-icon').addClass('fa-heart');
        }else {
            $('#like-icon').removeClass('fa-heart');
            $('#like-icon').addClass('fa-heart-o');
        }
    }

    // user界面才有
    $('#like-btn').click(function () {
        var url = isLike ?'/movie/'+ movieId +'/unlike?userId='+ userId :'/movie/'+ movieId +'/like?userId='+ userId;
        postRequest(
             url,
            null,
            function (res) {
                 isLike = !isLike;
                getMovie();
            },
            function (error) {
                alert(error);
            });
    });

    // admin界面才有
    $("#modify-btn").click(function () {
        getRequest(
            '/movie/get/'+movieId,
            function(res){
                updateMovie(res.content);
            },
            function (error) {
                alert(error);
            }
        );


    });

    function updateMovie(movie){
        var length=movie.length;
        var status=movie.status;


        var form={
            id: movieId,
            name: $('#modify-name-input').val(),
            posterUrl: $('#modify-img-input').val(),
            director: $('#modify-director-input').val(),
            screenWriter: $('#modify-writer-input').val(),
            starring: $('#modify-star-input').val(),
            type: $('#modify-type-input').val(),
            country: $('#modify-country-input').val(),
            language: $('#modify-language-input').val(),
            startDate: $('#modify-date-input').val(),
            length:length,
            description: $('#modify-description-input').val(),
            status:status
        };
        postRequest(
            '/movie/update',
            form,
            function (res) {
                getMovie();
                $("#modifyModal").modal('hide');
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    $("#delete-btn").click(function () {

        var movieIdList=[];
        movieIdList[0]=movieId;
        var form={
            movieIdList:movieIdList
        };
        var r=confirm("确认要下架该电影吗");
        if(r) {
            postRequest(
                '/movie/off/batch',
                form,
                function (res) {

                    alert("下架成功！");
                },
                function (error) {
                    alert(JSON.stringify(error));
                }
            )
        }
    });


});