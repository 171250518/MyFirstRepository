$(document).ready(function(){
    var confId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    var auId;
    getConf();
    getArticleListConference(confId);
    getTopAuthorsOfConferenceChart();
    getAuthor();
    $("#selectAuthor").change(function(){
        auId=($("#selectAuthor").val());
        if(auId==20){
            getArticleListConferenceAuthor(confId);
        }else {
            getPapersOfConferenceByAuthor();
        }

    });

    function getConf(){
        getRequest(
            '/conference/detail?confId='+confId,
            function(res){
                var conf= res.content;//返回的ConfVO对象
                repainConfDetail(conf);
            },
            function(error){
                alert(error);
            }
        );
    }


    function repainConfDetail(conf) {
        $('#confName').text(conf.name);
        $('#confYear').text(conf.time);
        $('#papersNumber').text(conf.paper_num);
        $('#citationNumber').text(conf.citation);
        $('#confRank').text(conf.rank.toFixed(2));
    }


    function getArticleListConference(confId) {
        getRequest(
            '/paper/searchConference?confId='+confId,
            function (res) {
                renderConfArticleList(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }
    function getArticleListConferenceAuthor(confId) {
        getRequest(
            '/paper/searchConference?confId='+confId,
            function (res) {
                renderConfArticleListByIdAndAuthor(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function renderConfArticleList(list) {
        $('.paper-on-list').empty();
        var paperDomStr = '';
        if(list.length==0){
            paperDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (paperVO) {
                paperDomStr +="<li style=\"background:none; clear:both\" class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+  "  in "+paperVO.publishTime+"</a></li>";
            });
        }
        $('.paper-on-list').append(paperDomStr);
    }

    function  getAuthor() {
        getRequest(
            '/conference/searchAuthors?confId='+confId,
            function (res) {
                setAuthorSelection(res.content);//返回的AuthorVO对象列表
            },
            function (error) {
                alert(error);
            });

    }

    function setAuthorSelection(list){
        $('.getAuthor').empty();
        var selectionStr = "<option value='20'>全部作者</option>";
        list.forEach(function (Author) {
            selectionStr+="<option value="+(Author.id)+">" + Author.name + "</option>";
        });
        $('.getAuthor').append(selectionStr);
    }

    //根据会议id和学者id获得论文
    function getPapersOfConferenceByAuthor(){
        getRequest(
            '/getPapersOfConfByAuthor/'+confId + '/' + auId,
            function (res) {
                renderConfArticleListByIdAndAuthor(res.content);//返回的PaperVO对象列表
            },
            function (error) {
                alert(error);
            });
    }

    function renderConfArticleListByIdAndAuthor(list) {
        $('.paper-byAuthor').empty();
        var auPaperDomStr = '';
        if(list.length==0){
            auPaperDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (paperVO) {
                auPaperDomStr +="<li style=\"background:none; clear:both\" class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+  "    by   "  +paperVO.author+"  in "+paperVO.publishTime+"</a></li>";
            });
        }
        $('.paper-byAuthor').append(auPaperDomStr);
    }

    function getTopAuthorsOfConferenceChart() {
        getRequest(
            '/conference/searchTopAuthors?confId='+confId,
            function(res){
                var data = res.content;
                /*var authors=[data[0].name];
                var papernums=[data[0].paper_num];*/
                //数据多时用下面的authors和papernums
                var authors=[data[0].name,data[1].name,data[2].name,data[3].name,data[4].name,data[5].name,data[6].name];
                var papernums=[data[0].rank,data[1].rank,data[2].rank,data[3].rank,data[4].rank,data[5].rank,data[6].rank,];
                var myChart = echarts.init($("#paper-year-chart")[0]);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '会议活跃学者图'
                    },
                    xAxis: {
                        type: 'category',
                        data: authors,
                        axisLabel:{
                            interval:0,
                            rotate:45,//倾斜度 -90 至 90 默认为0
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
                        data: papernums,
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
    function getAuthorByName(auName){
        getRequest(
            '/index/getAuthorIdByName?auName='+auName,
            function (res) {
                var authorId=(res.content[0]).id;
                window.location.href='../Author/authorDetail.html?id='+authorId;
            },
            function (error) {
                alert(error);
            });
    }


});
/**
 * 分页函数
 * pno--页数
 * psize--每页显示记录数
 * 分页部分是从真实数据行开始，因而存在加减某个常数，以确定真正的记录数
 * 纯js分页实质是数据行全部加载，通过是否显示属性完成分页功能
 **/
/*function goPage(pno,psize){
    alert(1);
    var iRows = document.getElementById("idData");
    lis = iRows.getElementsByTagName('li');
    var num = lis.length;//表格所有行数(所有记录数)
    alert(num);
    var totalPage = 0;//总页数
    var pageSize = psize;//每页显示行数
    //总共分几页
    if(num/pageSize > parseInt(num/pageSize)){
        totalPage=parseInt(num/pageSize)+1;
    }else{
        totalPage=parseInt(num/pageSize);
    }
    var currentPage = pno;//当前页数
    var startRow = (currentPage - 1) * pageSize+1;//开始显示的行 31
    var endRow = currentPage * pageSize;//结束显示的行  40
    endRow = (endRow > num)? num : endRow;  //40
    alert(endRow);
    //遍历显示数据实现分页
    for(var i=1;i<(num+1);i++){
        var irow = iRows.rows[i-1];
        if(i>=startRow && i<=endRow){
            irow.style.display = "block";
        }else{
            irow.style.display = "none";
        }
    }
    var tempStr = "共"+num+"条记录 分"+totalPage+"页 当前第"+currentPage+"页";
    if(currentPage>1){
        tempStr += "<a href=\"#\" onClick=\"goPage("+(1)+","+psize+")\">首页</a>";
        tempStr += "<a href=\"#\" onClick=\"goPage("+(currentPage-1)+","+psize+")\"><上一页</a>"
    }else{
        tempStr += "首页";
        tempStr += "<上一页";
    }
    if(currentPage<totalPage){
        tempStr += "<a href=\"#\" onClick=\"goPage("+(currentPage+1)+","+psize+")\">下一页></a>";
        tempStr += "<a href=\"#\" onClick=\"goPage("+(totalPage)+","+psize+")\">尾页</a>";
    }else{
        tempStr += "下一页>";
        tempStr += "尾页";
    }
    document.getElementById("barcon").innerHTML = tempStr;
}*/
