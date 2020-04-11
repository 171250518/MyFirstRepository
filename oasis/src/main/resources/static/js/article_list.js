$(document).ready(function () {
    $('.searchbox').keydown(function (event) {
        if(event.which===13) {
            var id =$('.searchbox').attr('id');
            if(id=='search-inputAuthor'){
                getArticleListAuthor($('.searchbox').val());
            }else if(id=='search-inputTitle'){
                getArticleListTitle($('.searchbox').val());
            }else if(id=='search-inputPublisher'){
                getArticleListAffiliation($('.searchbox').val());
            }else if(id=='search-inputYear') {
                getArticleListYear($('.searchbox').val());
            }else if(id=='search-inputKeywords') {
                getArticleListKeywords($('.searchbox').val());
            }else if(id=='search-inputConference') {
                getArticleListConference($('.searchbox').val());
            }
            else if(id=='search-inputCombinatorialSearch'){
                var str=$('.searchbox').val();
                var str1=str.replace(/\&/g,"%26");
                var str2=str1.replace(/\#/g,"%23");
                getArticleListCombination(str2);
            }
        }
    });
});

//根据组合关键字搜索
function getArticleListCombination(comWords) {
    getRequest(
        '/paper/searchCombination?comWords='+comWords,
        function (res) {
            renderArticleList(res.content);
        },
        function (error) {
            alert(error);
        });
}

function getArticleListAuthor(author) {
    getRequest(
        '/paper/searchAuthor?author='+author,
        function (res) {
            renderArticleList(res.content);
        },
        function (error) {
            alert(error);
        });
}
function getArticleListKeywords(keywords) {
    getRequest(
        '/paper/searchKeywords?keywords='+keywords,
        function (res) {
            renderArticleList(res.content);
        },
        function (error) {
            alert(error);
        });
}
function getArticleListConference(conf) {
    getRequest(
        '/paper/searchConf?conf='+conf,
        function (res) {
            renderArticleList(res.content);
        },
        function (error) {
            alert(error);
        });
}
function getArticleListTitle(title) {
    getRequest(
        '/paper/searchTitle?title='+title,
        function (res) {
            renderArticleList(res.content);
        },
        function (error) {
            alert(error);
        });
}
function getArticleListAffiliation(affiliation){
    getRequest(
        '/paper/searchAffiliation?affiliation='+affiliation,
        function (res) {

            renderArticleList(res.content);
        },
        function (error) {
            alert(error);
        });
}
function getArticleListYear(year){
    getRequest(
        '/paper/searchYear?year='+year,
        function (res) {
            renderArticleList(res.content);
        },
        function (error) {
            alert(error);
        });
}
function renderArticleList(list) {
    $('.paper-on-list').empty();
    var paperDomStr = '';
    if(list.length==0){
        paperDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
    }else {
        list.forEach(function (paperVO) {
            paperDomStr +="<li class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a href=" + (paperVO.link) + " target=\"_blank\" title=\"这里是文章链接\">"+paperVO.title+  "    by   "  +paperVO.author+"  in "+paperVO.publishTime+"</a></li>";
        });
    }
    $('.paper-on-list').append(paperDomStr);
}