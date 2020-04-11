$(document).ready(function () {
    var auName;
    var affName;
    var confName;
    var reName;
    var state=window.location.href.split('?')[1].split('&')[1].split('=')[1];
    if(state==0){
        auName = (window.location.href.split('?')[1].split('&')[0].split('=')[1]);
        getAuthorByName();
    }else if(state==1){
        affName = (window.location.href.split('?')[1].split('&')[0].split('=')[1]);
        getAffiliationByName();
    }else if(state==2){
        confName = (window.location.href.split('?')[1].split('&')[0].split('=')[1]);
        getConfByName();
    }else if(state==3){
        reName = (window.location.href.split('?')[1].split('&')[0].split('=')[1]);
        getReDirectionByName();
    }



    function getAuthorByName(){
        getRequest(
            '/index/getAuthorIdByName?auName='+auName,
            function (res) {

                renderAuthorListByName(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    function renderAuthorListByName(list) {
        $('.result-on-list').empty();
        var authorDomStr = '';
        if(list.length==0){
            authorDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (AuthorVO) {
                authorDomStr +="<li class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a onclick=\"goToAuthorById(" + AuthorVO.id + ")\"  target=\"_blank\" title=\"这里是学者详情链接\">"+AuthorVO.name+"</a></li>";
            });
        }
        $('.result-on-list').append(authorDomStr);
    }

    function getAffiliationByName(){
        getRequest(
            '/index/getAffiliationIdByName?affName='+affName,
            function (res) {
                renderAffiliationListByName(res.content);
            },
            function (error) {

                alert(error);
            });
    }

    function renderAffiliationListByName(list) {
        $('.result-on-list').empty();
        var affDomStr = '';
        if(list.length==0){
            affDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (AffiliationVO) {
                affDomStr +="<li class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a onclick=\"goToAffiliationById(" + AffiliationVO.id + ")\"  target=\"_blank\" title=\"这里是机构详情链接\">"+AffiliationVO.name+"</a></li>";
            });
        }
        $('.result-on-list').append(affDomStr);
    }
    function getConfByName(){
        getRequest(
            '/index/getConfIdByName?confName='+confName,
            function (res) {
                renderConfListByName(res.content);
            },
            function (error) {
                alert(error);
            });
    }
    function renderConfListByName(list) {
        $('.result-on-list').empty();
        var confDomStr = '';
        if(list.length==0){
            confDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (ConfVO) {
               confDomStr +="<li class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a onclick=\"goToConfById(" + ConfVO.id + ")\"  target=\"_blank\" title=\"这里是会议详情链接\">"+ConfVO.name+"</a></li>";
            });
        }
        $('.result-on-list').append(confDomStr);
    }
    function getReDirectionByName(){
        getRequest(
            '/index/getReDirectionIdByName?reName='+reName,
            function (res) {
                renderReDirectionListByName(res.content);
            },
            function (error) {
                alert(error);
            });
    }
    function renderReDirectionListByName(list) {
        $('.result-on-list').empty();
        var reDirectionDomStr = '';
        if(list.length==0){
            reDirectionDomStr+="<li class=\"p1 FontW Font 14\"><i class=\"Blockinline\"></i>暂无数据</li>";
        }else {
            list.forEach(function (ResearchDirectionVO) {
                reDirectionDomStr +="<li class=\"p1 FontW Font14\"><i class=\"Blockinline\"></i><a onclick=\"goToReDirectionById(" + ResearchDirectionVO.id + ")\"  target=\"_blank\" title=\"这里是研究方向详情链接\">"+ResearchDirectionVO.name+"</a></li>";
            });
        }
        $('.result-on-list').append(reDirectionDomStr);
    }

});
function goToAuthorById(authorId) {
    window.location.href='../Author/authorDetail.html?id='+authorId;
}
function goToAffiliationById(affId) {
    window.location.href='../Affiliation/affiliationDetail.html?id='+affId;
}
function goToConfById(confId) {
    window.location.href='../Conf/confDetail.html?id='+confId;
}
function goToReDirectionById(reId) {
    window.location.href='../ReDirection/reDirectionDetail.html?id='+reId;
}