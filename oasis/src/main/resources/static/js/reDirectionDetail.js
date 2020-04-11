$(document).ready(function(){
    var reId = parseInt((window.location.href.split('?')[1].split('&')[0].split('=')[1]));
    var year;
    var auId;
    getReDirection();
    getArticleListReDirection(reId);
    getReDirectionPaperNumByYearChart();
    getReDirectionPaperCitationNumByYearChart();

    getTopAuthorsOfReDirectionChart();
    getTopAffiliationsOfReDirectionChart()

    $("#PaperYear").change(function(){
        year=($("#PaperYear").val()).toString();
        if(auId==20){
            getArticleListReDirectionYear(reId);
        }else{
            getPapersOfReDirectionByYear();
        }

    });

    getAuthor();
    $("#selectAuthor").change(function(){
        auId=($("#selectAuthor").val());
        if(auId==21){
            getArticleListReDirectionAuthor(reId);
        }else{
            getPapersOfReDirectionByAuthor();
        }

    });



    function getReDirection(){
        getRequest(
            '/researchDirection/detail?reId='+reId,
            function(res){
                var reDirection = res.content;//返回的ResearchDirectionVO对象
                repainReDirectionDetail(reDirection);
            },
            function(error){
                alert(error);
            }
        );
    }

    function getArticleListReDirection(reId) {
        getRequest(
            '/paper/searchReDirection?reId='+reId,
            function (res) {
                renderReArticleList(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function getArticleListReDirectionYear(reId) {
        getRequest(
            '/paper/searchReDirection?reId='+reId,
            function (res) {
                renderArticleListByReDirectionAndYear(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function getArticleListReDirectionAuthor(reId) {
        getRequest(
            '/paper/searchReDirection?reId='+reId,
            function (res) {
                renderArticleListByIdAndAuthor(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function repainReDirectionDetail(reDirection) {
        $('#reDirection').text(reDirection.name);
        $('#authoritativeScholar').text(reDirection.authors);
        $('#papersNumber').text(reDirection.paper_number);
        $('#citationNumber').text(reDirection.citation_count);
        $('#reRank').text(reDirection.rank.toFixed(2));
    }


    function renderReArticleList(list) {
        $('.paper-on-list').empty();
        var paperDomStr = '';
        if(list.length==0){
            paperDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (paperVO) {
                paperDomStr +="<li style=\"background:none; clear:both\" class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+  "    by   "  +paperVO.author+"  in "+paperVO.publishTime+"</a></li>";
            });
        }
        $('.paper-on-list').append(paperDomStr);
    }

    function getPapersOfReDirectionByYear() {
        getRequest(
            '/getPapersOfReDirectionByYear/'+reId + '/' + year,
            function (res) {
                renderArticleListByReDirectionAndYear(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function renderArticleListByReDirectionAndYear(list) {
        $('.reYearPaper-on-list').empty();
        var rePaperDomStr = '';
        if(list.length==0){
            rePaperDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (paperVO) {
                rePaperDomStr +="<li style=\"background:none; clear:both\" class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+  "    by   "  +paperVO.author+"  in "+paperVO.publishTime+"</a></li>";
            });
        }
        $('.reYearPaper-on-list').append(rePaperDomStr);
    }

    function  getAuthor() {
        getRequest(
            '/reDirection/searchAuthors?reId='+reId,
            function (res) {
                setAuthorSelection(res.content);//返回的AuthorVO对象列表
            },
            function (error) {
                alert(error);
            });

    }

    function setAuthorSelection(list){
        $('.getAuthor').empty();
        var selectionStr = "<option value='21'>全部作者</option>";
        list.forEach(function (Author) {
            selectionStr+="<option value="+(Author.id)+">" + Author.name + "</option>";
        });
        $('.getAuthor').append(selectionStr);
    }

    function getPapersOfReDirectionByAuthor(){
        getRequest(
            '/getPapersOfReDirectionByAuthor/'+reId + '/' + auId,
            function (res) {
                renderArticleListByIdAndAuthor(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function renderArticleListByIdAndAuthor(list) {
        $('.paper-byAuthor').empty();
        var auPaperDomStr = '';
        if(list.length==0){
            auPaperDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (paperVO) {
                auPaperDomStr +="<li  style=\"background:none; clear:both\" class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+  "    by   "  +paperVO.author+"  in "+paperVO.publishTime+"</a></li>";
            });
        }
        $('.paper-byAuthor').append(auPaperDomStr);
    }
    function getReDirectionPaperNumByYearChart() {
        var dateArray = [2011,2012,2013,2014,2015,2016,2017,2018,2019,2020];
        getRequest(
            '/reDirection/paperNumChart?reId='+reId,
            function(res){
                var numberArray = res.content;
                var myChart = echarts.init($("#paper-year-chart")[0]);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '研究方向发表论文数量变化表'
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
    function getReDirectionPaperCitationNumByYearChart() {
        var dateArray1 = [2011,2012,2013,2014,2015,2016,2017,2018,2019,2020];
        getRequest(
            '/reDirection/citationNumChart?reId='+reId,
            function(res){
                var numberArray = res.content;
                var myChart = echarts.init($("#citation-year-chart")[0]);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '研究方向发表论文被引用数量变化表'
                    },
                    xAxis: {
                        type: 'category',
                        data: dateArray1
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

    function getTopAuthorsOfReDirectionChart() {
        getRequest(
            '/reDirection/searchTopAuthors?reId='+reId,
            function(res){
                var data = res.content;
                var num1=7;
                var authors=[];
                var rankNums=[];
                if(num1>data.length) {
                    num1 = data.length;
                }
                for(var i=0;i<num1;i++){
                    authors.push(data[i].name);
                   rankNums.push(data[i].rank);
                }
               /* for(var i=0;i<10;i++){
                    authors.push(data[i].name);
                }
                for(var j=0;j<10;j++){
                    rankNums.push(data[i].rank);
                }*/
                var myChart = echarts.init($("#author-rank-chart")[0]);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '研究方向活跃学者图'
                    },
                    xAxis: {
                        type: 'category',
                        data: authors,
                        axisLabel:{
                            interval:0,
                            rotate:30,//倾斜度 -90 至 90 默认为0
                            margin:2,
                            textStyle:{
                                color:"#000000"
                            }
                        }
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: rankNums,
                        type: 'bar'
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
                myChart.on('click', function (param){
                    var name=param.name;
                    getAuthorByName(name);
                });
            },
            function (error) {
                alert(error);
            }
        );
    }
    function getTopAffiliationsOfReDirectionChart() {
        getRequest(
            '/reDirection/searchTopAffiliations?reId='+reId,
            function(res){

                var data = res.content;
                var num1=7;
                var affiliations=[];
                var paperNums=[];
                if(num1>data.length) {
                    num1 = data.length;
                }
                for(var i=0;i<num1;i++){
                    affiliations.push(data[i].name);
                    paperNums.push(data[i].paper_number);
                }
               /* for(var i=0; i<10;i++){
                    affiliations.push(data[i].name);
                }
                for(var j=0;j<10;j++){
                    paperNums.push(data[i].paper_number);
                }*/
                var myChart = echarts.init($("#affiliation-rank-chart")[0]);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '研究方向活跃机构图'
                    },
                    xAxis: {
                        type: 'category',
                        data: affiliations,
                        axisLabel:{
                            interval:0,
                            rotate:25,//倾斜度 -90 至 90 默认为0
                            margin:2,
                            textStyle:{
                                color:"#000000"
                            }
                        }
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: paperNums,
                        type: 'bar'
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
                myChart.on('click', function (param){
                    var name=param.name;
                    getAffiliationByName(name);
                });
            },
            function (error) {
                alert(error);
            }
        );
    }
    function getAuthorByName(auName){
        getRequest(
            '/index/getAuthorIdByName?auName='+auName,
            function (res) {
                var authorId=(res.content)[0].id;
                window.location.href='../Author/authorDetail.html?id='+authorId;
            },
            function (error) {
                alert(error);
            });
    }

    function getAffiliationByName(affName){
        getRequest(
            '/index/getAffiliationIdByName?affName='+affName,
            function (res) {
                var affId=(res.content[0]).id;
                window.location.href='../Affiliation/affiliationDetail.html?id='+affId;
            },
            function (error) {
                alert(error);
            });
    }

});