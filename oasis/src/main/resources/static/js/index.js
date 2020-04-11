$(document).ready(function () {
    $('.searchbox').keydown(function (event) {
        if(event.which===13) {
            var id =$('.searchbox').attr('id');
            if(id=='search-inputAuthor'){
                var auName=$('.searchbox').val();
                goToAuthor(auName);
            }else if(id=='search-inputPublisher'){
                var affName=$('.searchbox').val();
                goToAffiliation(affName);
            }else if(id=='search-inputKeywords') {
                var reName=$('.searchbox').val();
                goToReDirection(reName);
            }else if(id=='search-inputConference') {
                var confName=$('.searchbox').val();
                goToConf(confName);
            }

        }
    });
    getTopAuthors();
    getTopAffiliations();
    getTopConfs();
    getTopReDirections();

});
function getTopAuthors(){
    getRequest(
        '/index/getTopAuthors',
        function (res) {

            renderAuthorList(res.content);
        },
        function (error) {
            alert(error);
        });
}

function renderAuthorList(list) {
    var fontSize=20;
    $('.topAuthor-on-list').empty();
    var authorDomStr = '';
    if(list.length==0){
        authorDomStr+="<li><i></i><button onclick=\"goToAuthor()\" target=\"_blank\" title=\"这里调用论文标题...\"class=\"button1\">暂无数据</button></li>";
    }else {
        list.forEach(function (AuthorVO) {
            authorDomStr +="<li><i></i><a style='font-size: "+fontSize+"px' onclick=\"goToAuthorById(" + AuthorVO.id + ")\" target=\"_blank\" title=\"这里调用论文标题...\"class=\"button1\">"+AuthorVO.name+"</a></li>";
          fontSize-=0.5;
        });
    }
    $('.topAuthor-on-list').append(authorDomStr);
}
function getTopAffiliations(){
    getRequest(
        '/index/getTopAffiliations',
        function (res) {
            var affList=res.content;
            affList.shift();
            renderAffiliationList(affList);
        },
        function (error) {
            alert(error);
        });
}

function renderAffiliationList(list) {
    var fontSize=20;
    $('.topAffiliation-on-list').empty();
    var affiliationDomStr = '';
    if(list.length==0){
        affiliationDomStr+="<li><i></i><button onclick=\"goToAuthor()\" target=\"_blank\" title=\"这里调用论文标题...\"class=\"button1\">暂无数据</button></li>";
    }else {
        list.forEach(function (affiliationVO) {
            affiliationDomStr +="<li><i></i><a style='font-size: "+fontSize+"px' onclick=\"goToAffiliationById(" + affiliationVO.id + ")\" target=\"_blank\" title=\"这里调用论文标题...\"class=\"button1\">"+affiliationVO.name+"</a></li>";
            fontSize-=0.5;
        });
    }
    $('.topAffiliation-on-list').append(affiliationDomStr);
}

function getTopConfs(){
    getRequest(
        '/index/getTopConfs',
        function (res) {
            renderConfList(res.content);
        },
        function (error) {
            alert(error);
        });
}

function renderConfList(list) {
    var fontSize=20;
    $('.topConf-on-list').empty();
    var confDomStr = '';
    if(list.length==0){
        confDomStr+="<li><i></i><button onclick=\"goToAuthor()\" target=\"_blank\" title=\"这里调用论文标题...\"class=\"button1\">暂无数据</button></li>";
    }else {
        list.forEach(function (confVO) {
            confDomStr +="<li><i></i><a style='font-size: "+fontSize+"px' onclick=\"goToConfById(" + confVO.id + ")\" target=\"_blank\" title=\"这里调用论文标题...\"class=\"button1\">"+confVO.name+"</a></li>";
            fontSize-=0.5;
        });
    }
    $('.topConf-on-list').append(confDomStr);
}

function getTopReDirections(){
    getRequest(
        '/index/getTopReDirections',
        function (res) {
            renderReDirectionList(res.content);
        },
        function (error) {
            alert(error);
        });
}

function renderReDirectionList(list) {
    var fontSize=20;
    $('.topReDirection-on-list').empty();
    var ReDirectionDomStr = '';
    if(list.length==0){
        ReDirectionDomStr+="<li><i></i><button onclick=\"goToAuthor()\" target=\"_blank\" title=\"这里调用论文标题...\"class=\"button1\">暂无数据</button></li>";
    }else {
        list.forEach(function (ReDirectionVO) {
            ReDirectionDomStr +="<li><i></i><a style='font-size: "+fontSize+"px' onclick=\"goToReDirectionById(" + ReDirectionVO.id + ")\" target=\"_blank\" title=\"这里调用论文标题...\"class=\"button1\">"+ReDirectionVO.name+"</a></li>";
            fontSize-=0.5;
        });
    }
    $('.topReDirection-on-list').append(ReDirectionDomStr);
}




function goToAuthor(authorName) {
    window.location.href='../SearchResult/indexSearchResults.html?name='+authorName + '&state=' +0;
}

function goToAuthorById(authorId) {
    window.location.href='../Author/authorDetail.html?id='+authorId;
}
function goToAffiliation(affName) {
    window.location.href='../SearchResult/indexSearchResults.html?name='+affName + '&state=' +1;
}
function goToAffiliationById(affId) {
    window.location.href='../Affiliation/affiliationDetail.html?id='+affId;
}
function goToConf(confName) {
    window.location.href='../SearchResult/indexSearchResults.html?name='+confName + '&state=' +2;
}
function goToConfById(confId) {
    window.location.href='../Conf/confDetail.html?id='+confId;
}
function goToReDirection(reName) {
    window.location.href='../SearchResult/indexSearchResults.html?name='+reName + '&state=' +3;
}
function goToReDirectionById(reId) {
    window.location.href='../ReDirection/reDirectionDetail.html?id='+reId;
}