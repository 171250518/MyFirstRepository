$(document).ready(function(){
    var affId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    var year;
    var auId;

    $("#PaperYear").change(function(){
        year=($("#PaperYear").val()).toString();
        if(year==20){
            getArticleListAffiliationYear(affId);
        }else{
            getPapersOfAffiliationByYear();
        }

    });
    getAuthor();
    $("#PaperAuthor").change(function(){
        auId=($("#PaperAuthor").val());
        if(auId==21){
            getArticleListAffiliationAuthor(affId);
        }else{
            getPapersOfAffiliationByAuthor();
        }

    });

    getAffiliation();
    getArticleListAffiliation(affId);
    getTopReDirectionOfAffChart()
    getAffiliationPaperNumByYearChart();
    getAffiliationPaperCitationNumByYearChart();

    function getAffiliation(){
        getRequest(
            '/affiliation/detail?affId='+affId,
            function(res){
                var affiliation = res.content;//返回的AffiliationVO对象
                repainAffiliationDetail(affiliation);
            },
            function(error){
                alert(error);
            }
        );
    }


    function repainAffiliationDetail(affiliation) {
        $('#affiliationName').text(affiliation.name);
        $('#authors').text(affiliation.authors);
        $('#researchDirection').text(affiliation.reDirection);
        $('#papersNumber').text(affiliation.paper_number);
        $('#citationNumber').text(affiliation.citation_count);
        $('#rankNumber').text(affiliation.rank.toFixed(2));
    }

    function getArticleListAffiliation(affId) {
        getRequest(
            '/paper/searchAffiliationArticle?affId='+affId,
            function (res) {
                renderAffArticleList(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }
    function getArticleListAffiliationYear(affId) {
        getRequest(
            '/paper/searchAffilation?affId='+affId,
            function (res) {
                renderArticleListByIdAndYear(res.content);//回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }
    function getArticleListAffiliationAuthor(affId) {
        getRequest(
            '/paper/searchAffilation?affId='+affId,
            function (res) {
                renderArticleListByIdAndAuthor(res.content);//回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function renderAffArticleList(list) {
        $('.affPaper-on-list').empty();
        var paperDomStr = '';
        if(list.length==0){
            paperDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (paperVO) {
                paperDomStr +="<li style=\"background:none; clear:both\" class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+  "    by   "  +paperVO.author+"  in "+paperVO.publishTime+"</a></li>";
            });
        }
        $('.affPaper-on-list').append(paperDomStr);
    }



    function getAffiliationPaperNumByYearChart() {
        var dateArray = [2011,2012,2013,2014,2015,2016,2017,2018,2019,2020];
        getRequest(
            '/affiliation/paperNumChart?affId='+affId,
            function(res){
                var numberArray = res.content;
                var myChart = echarts.init($("#paper-year-chart")[0]);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '机构发表论文数量变化表'
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
    function getAffiliationPaperCitationNumByYearChart() {
        var dateArray1 = [2011,2012,2013,2014,2015,2016,2017,2018,2019,2020];
        getRequest(
            '/affiliation/citationNumChart?affId='+affId,
            function(res){
                var numberArray1 = res.content;
                var myChart = echarts.init($("#citation-year-chart")[0]);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '机构发表论文被引用数量变化表'
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

    function getTopReDirectionOfAffChart() {
        getRequest(
            '/affiliation/searchTopRes?affId='+affId,
            function(res){
                var data = res.content;
                var num1=7;
                var result=[];
                var paperNums=[];
                if(num1>data.length) {
                    num1 = data.length;
                }
                for(var i=0;i<num1;i++){
                    result.push(data[i].name);
                    paperNums.push(data[i].paper_number);
                }
              /*  var res=[data[0].name,data[1].name,data[2].name,data[3].name,data[4].name,data[5].name,data[6].name];
                var paperNums=[data[0].paper_number,data[1].paper_number,data[2].paper_number,data[3].paper_number,data[4].paper_number,data[5].paper_number,data[6].paper_number];*/
                var myChart = echarts.init($("#re-rank-chart")[0]);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '机构活跃研究方向图'
                    },
                    xAxis: {
                        type: 'category',
                        data: result,
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
                        data: paperNums,
                        type: 'bar'
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
                myChart.on('click', function (param){
                    var name=param.name;
                    getReDirectionByName(name);
                });
            },
            function (error) {
                alert(error);
            }
        );
    }
    function getReDirectionByName(reName){
        getRequest(
            '/index/getReDirectionIdByName?reName='+reName,
            function (res) {
                var reId=(res.content[0]).id;
                window.location.href='../ReDirection/reDirectionDetail.html?reId='+reId;
            },
            function (error) {
                alert(error);
            });
    }

    //根据机构id和年份获得论文
    function getPapersOfAffiliationByYear() {
        getRequest(
            '/getPapersOfAffiliationByYear/'+affId + '/' + year,
            function (res) {
                renderArticleListByIdAndYear(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function renderArticleListByIdAndYear(list) {
        $('.affYearPaper-on-list').empty();
        var affPaperDomStr = '';
        if(list.length==0){
            affPaperDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (paperVO) {
                affPaperDomStr +="<li style=\"background:none; clear:both\" class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+  "    by   "  +paperVO.author+"  in "+paperVO.publishTime+"</a></li>";
            });
        }
        $('.affYearPaper-on-list').append(affPaperDomStr);
    }
//根据机构id和学者id获得论文
    function getPapersOfAffiliationByAuthor() {
        getRequest(
            '/getPapersOfAffiliationByAuthor/'+affId + '/' + auId,
            function (res) {
                renderArticleListByIdAndAuthor(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function renderArticleListByIdAndAuthor(list) {
        $('.affAuthorPaper-on-list').empty();
        var affPaperDomStr = '';
        if(list.length==0){
            affPaperDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (paperVO) {
                affPaperDomStr +="<li style=\"background:none; clear:both\" class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+  "    by   "  +paperVO.author+"  in "+paperVO.publishTime+"</a></li>";
            });
        }
        $('.affAuthorPaper-on-list').append(affPaperDomStr);
    }

    //获得作者并设置列表
    function getAuthor() {
        getRequest(
            '/affiliation/getAuthorById?affId='+affId,
            function (res) {
                setAuthorSelection(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function setAuthorSelection(list){
        $('.AuthorSelection').empty();
        var selectionStr = "<option value='21'>全部作者</option>";
        list.forEach(function (Author) {
            selectionStr+="<option value="+(Author.id)+">" + Author.name + "</option>";
        });
        $('.AuthorSelection').append(selectionStr);
    }

});

