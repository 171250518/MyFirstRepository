$(document).ready(function () {
   getPromotions();
});

function getPromotions() {
    getRequest(
        '/activity/get/userActivity',
        function (res) {
            if(res.success){
                renderActivities(res.content);
            }
        },
        function (error) {
            alert(error);
        }
    )
}

function renderActivities(activities) {
    $(".content-activity").empty();
    var activitiesDomStr = "";
    activities.forEach(function (activity) {
        var movieDomStr = "";
        if(activity.movieList.length){
            activity.movieList.forEach(function (movie) {
                movieDomStr += "<li class='activity-movie primary-text'>"+movie.name+"</li>";
            });
        }else{
            movieDomStr = "<li class='activity-movie primary-text'>所有热映电影</li>";
        }

        activitiesDomStr+=
            "<div class='activity-container'>\n" +
            "                    <div class='activity-card card'>\n" +
            "                        <div class='activity-line'><span class='title'>" + activity.name + "</span> <span style='color:#007DD3;'>【消费满"+ activity.coupon.consumeReq +"元领取】</span>\n" +
            "                        </div>\n" +
            "                        <div class='activity-line'><span>活动时间："+ activity.startTime.split('T')[0] +"至"+ activity.endTime.split('T')[0] +"</span></div>\n" +
            "                        <div class='activity-line'><span>参与电影：</span>\n" +
            "                            <ul>\n"+movieDomStr+"</ul>" +
            "                        </div>\n" +
            "                        <div class='activity-line'>\n" +
            "                            <a href='#' style='color: red;font-size: 20px;text-decoration: underline' onclick='getCoupon(this.id)' id='"+ activity.coupon.id +"and"+ activity.coupon.consumeReq +"' >点击领取</a>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class='activity-coupon primary-bg'><span class='title'>优惠券："+ activity.coupon.name +"</span> <span\n" +
            "                            class='title'>满"+ activity.coupon.targetAmount +"减<span class='error-text title'>"+ activity.coupon.discountAmount +"</span></span> <span>"+ activity.coupon.description +"</span>\n" +
            "                    </div></div>";
    });
    $(".content-activity").append(activitiesDomStr);

}

function getCoupon(couponId) {
    getRequest(
        '/vip/consumes/get?userId='+ sessionStorage.id,
        function (res) {
            if(res.success){
                var list = res.content;
                var total = 0;
                for(var i = 0; i < list.length; i++){
                    total += list[i].consume;
                }
                if(total >= couponId.split("and")[1] ){
                    getRequest(
                        '/coupon/'+ sessionStorage.getItem('id') +'/'+ couponId.split('and')[0] +'/issue',
                        function () {alert("领取成功")},
                        function () {}
                    )
                }
                else{
                    alert(res.content);
                }
            }
            else {
                alert(res.content);
            }
        },
        function (error) {
            alert(error);
        }
    )
}
