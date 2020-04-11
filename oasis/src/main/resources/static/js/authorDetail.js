$(document).ready(function() {
    var auId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    var year;
    var conf;
    var coId;
    var type;
    getAuthor();
    getAuthorPaperNumByYearChart();
    getAuthorPaperCitationNumByYearChart();
    getArticleListByCitation();

    $("#PaperYear").change(function(){
        year=($("#PaperYear").val()).toString();
        if(year==20){
            getArticleListAuthorYear();
        }else {
            getPapersOfAuthorByYear();
        }

    });
    $("#PaperConf").change(function(){
        conf=($("#PaperConf").val());
        if(conf==21){
            getArticleListAuthorConf();
        }else{
            getPapersOfAuthorByConf();
        }

    });
    getCoAuthor();
    $("#PaperCoWorker").change(function(){
        coId=($("#PaperCoWorker").val());
        if(coId==22){
            getArticleListAuthorCoWorker();
        }else{
            getPapersOfAuthorByCoWorker();
        }

    });


//获得学者基本信息
    function getAuthor(){
        getRequest(
            '/author/detail?auId='+auId,
            function(res){
                var author = res.content;//返回的AuthorVO对象
                repainAuthorDetail(author);
                renderAuthorMap(author)
            },
            function(error){
                alert(error);
            }
        );
    }

    function renderAuthorMap(author) {

        $('.authorMap').empty();
         var auMapDomStr = "<img src=\"../../upload/map.png\"   onclick=\"goToChart('"+author.name.replace('/ /g','_')+"','"+author.id+"')\" target=\"_blank\" title=\"学者关系图谱，详情请点击\">";

        $('.authorMap').append(auMapDomStr);
    }
    function repainAuthorDetail(author) {
        $('#authorName').text(author.name);
        $('#authorAffiliation').text(author.institution);
        $('#researchDirection').text(author.researchDirection);
        $('#papersNumber').text(author.paper_num);
        $('#citationNumber').text(author.c_count);
        $('#authorRank').text((author.rank).toFixed(2));
    }
    //根据id获得学者论文
    function getArticleListAuthorYear() {
        getRequest(
            '/Author/searchPapersById?auId='+auId,
            function (res) {
                renderArticleListByIdAndYear(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }
    function getArticleListAuthorConf() {
        getRequest(
            '/Author/searchPapersById?auId='+auId,
            function (res) {
                renderArticleListByIdAndConf(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }
    function getArticleListAuthorCoWorker() {
        getRequest(
            '/Author/searchPapersById?auId='+auId,
            function (res) {
                renderArticleListByIdAndCoWorker(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }
    function getArticleListByCitation() {
        getRequest(
            '/Author/searchPapersByCitation?auId='+auId,
            function (res) {
                renderArticleListById(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function renderArticleListById(list) {
        $('.auPaper-on-list').empty();
        var auPaperDomStr = '';
        if(list.length==0){
            auPaperDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (paperVO) {
                auPaperDomStr +="<li style=\"background:none; clear:both\" class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+"  in "+paperVO.publishTime+"</a></li>";
            });
        }
        $('.auPaper-on-list').append(auPaperDomStr);
    }



//根据学者id和年份获得论文
    function getPapersOfAuthorByYear() {
        getRequest(
            '/getPapersOfAuthorByYear/'+auId + '/' + year,
            function (res) {
                renderArticleListByIdAndYear(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function renderArticleListByIdAndYear(list) {
        $('.auYearPaper-on-list').empty();
        var auPaperDomStr = '';
        if(list.length==0){
            auPaperDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (paperVO) {
                auPaperDomStr +="<li style=\"background:none; clear:both\" class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+ "  in "+paperVO.publishTime+"</a></li>";
            });
        }
        $('.auYearPaper-on-list').append(auPaperDomStr);
    }

    //根据学者id和会议获得论文
    function getPapersOfAuthorByConf() {
        getRequest(
            '/getPapersOfAuthorByConf/'+auId + '/' + conf,
            function (res) {
                renderArticleListByIdAndConf(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function renderArticleListByIdAndConf(list) {
        $('.auConfPaper-on-list').empty();
        var auPaperDomStr1 = '';
        if(list.length==0){
            auPaperDomStr1+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (paperVO) {
                auPaperDomStr1 +="<li style=\"background:none; clear:both\" class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+"  in "+paperVO.publishTime+"</a></li>";
            });
        }
        $('.auConfPaper-on-list').append(auPaperDomStr1);
    }
//根据学者id和共同作者id获得论文
    function getPapersOfAuthorByCoWorker() {
        getRequest(
            '/getPapersOfAuthorByCoWorker/'+auId + '/' + coId,
            function (res) {
                renderArticleListByIdAndCoWorker(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function renderArticleListByIdAndCoWorker(list) {
        $('.auCoWorkerPaper-on-list').empty();
        var auPaperDomStr = '';
        if(list.length==0){
            auPaperDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (paperVO) {
                auPaperDomStr +="<li style=\"background:none; clear:both\" class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+ " in "+paperVO.publishTime+"</a></li>";
            });
        }
        $('.auCoWorkerPaper-on-list').append(auPaperDomStr);
    }

    //获得共同作者并设置作者关系图；
    function getCoAuthor() {
        getRequest(
            '/author/getCoAuthorById?auId='+auId,
            function (res) {
                setCoAuthorSelection(res.content);//返回的AuthorVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function setCoAuthorSelection(list){
        $('.coAuthorSelection').empty();
        var selectionStr = "<option value='22'>全部合作作者</option>";
        list.forEach(function (Author) {
            selectionStr+="<option value="+(Author.id)+">" + Author.name + "</option>";
        });
        $('.coAuthorSelection').append(selectionStr);
    }
    function getAuthorPaperNumByYearChart() {
        var dateArray = [2011,2012,2013,2014,2015,2016,2017,2018,2019,2020];
        getRequest(
            '/author/paperNumChart?auId='+auId,
            function(res){
                var numberArray = res.content;

                var myChart = echarts.init($("#paper-year-chart")[0]);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '学者发表论文数量变化表'
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
    function getAuthorPaperCitationNumByYearChart() {
        var dateArray1 = [2011,2012,2013,2014,2015,2016,2017,2018,2019,2020];
        getRequest(
            '/author/citationNumChart?auId='+auId,
            function(res){
                var numberArray1 = res.content;

                var myChart = echarts.init($("#citation-year-chart")[0]);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '学者发表论文被引用数量变化表'
                    },
                    xAxis: {
                        type: 'category',
                        data: dateArray1
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: numberArray1,
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

});

function goToChart(auName,auId) {
    window.location.href='../Author/authorMap.html?name='+auName + '&id=' +auId;

}