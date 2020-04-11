//全局请求方法 包括 一些公共方法
var state = true;
function getRequest(url, onSuccess, onError) {
    $.ajax({
        type: 'GET',
        url: url,
        async: true,
        success: onSuccess,
        error: onError
    });
}
function postRequest(url, data, onSuccess, onError) {
    $.ajax({
        type: 'POST',
        url: url,
        async: true,
        data: JSON.stringify(data),
        contentType: 'application/json',
        processData: false,
        success: onSuccess,
        error: onError
    });
}
function postRequest_Noasync(url, data, onSuccess, onError) {
    $.ajax({
        type: 'POST',
        url: url,
        async:false,
        data: JSON.stringify(data),
        contentType: 'application/json',
        processData: false,
        success: onSuccess,
        error: onError
    });
}

function deleteRequest(url, data, onSuccess, onError) {
    $.ajax({
        type: 'DELETE',
        url: url,
        async: true,
        data: JSON.stringify(data),
        contentType: 'application/json',
        processData: false,
        success: onSuccess,
        error: onError
    });
}


function formatDate(date){
    var year = date.getFullYear();
    var month = date.getMonth()+1+'';
    var day = date.getDate()+'';
    month.length===1 && (month = '0'+month)
    day.length===1 && (day = '0'+day)
    return year+'-'+month+'-'+day;
}

function formatTime(date){
    var hour = date.getHours()+'';
    var minutes = date.getMinutes()+'';
    hour.length===1 && (hour = '0'+hour);
    minutes.length===1 && (minutes = '0'+minutes);
    return hour+":"+minutes;
}

/*
$(document).ready(function () {
    //全局事件
    alert("请先登录！");
    $("#login-btn").click(function () {
        var formData = getLoginForm();
        if (!validateLoginForm(formData)) {
            return;
        }

        formData.username = encode64(formData.username);
        formData.password = encode64(formData.username);

        postRequest(
            '/Account/logIn',
            formData,
            function (res) {
                sessionStorage.setItem('username', formData.username);
                sessionStorage.setItem('id', res.content.id);
                sessionStorage.setItem('identity', res.content.identity);
                var identity=sessionStorage.getItem('identity');
                window.location.href=identity==2? '/admin/movie/manage':'/index';
            },
            function (error) {
                alert(error);
            });
    });

    function getLoginForm() {
        return {
            username: $('#user-name-input').val(),
            password: $('#exampleInputPassword1').val()
        };
    }

    function validateLoginForm(data) {
        var isValidate = true;
        if (!data.username) {
            isValidate = false;
        }
        if (!data.password) {
            isValidate = false;
        }
        return isValidate;
    }

    $('#logout-head').click(function () {
        confirm('确认要退出登录吗？') && postRequest('/logout',null,function (res) {
            sessionStorage.clear();
            window.location.href='/index';
        });
    });

    $("#signUp-btn").click(function () {
        var formData = getSignUpForm();
        if (!validateSignUpForm(formData)) {
            return;
        }
        formData.username = encode64(formData.username);
        formData.password = encode64(formData.username);
        formData.secondPassword = encode64(formData.secondPassword);
        postRequest(
            '/Account/signUp',
            formData,
            function (res) {
                if (res.success) {
                    alert("注册成功");
                    window.location.href = '/index';
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(error);
            });
    });

    function getSignUpForm() {
        return {
            username: $('#user-name-input-register').val(),
            password: $('#password').val(),
            secondPassword: $('#re-password').val()
        };
    }

    function validateSignUpForm(data) {
        var isValidate = true;
        if (!data.username || data.username.length < 4 || data.username.length > 10) {
            isValidate = false;
            $('#signUp-name-error').css("visibility", "visible");
        }
        if (!data.password || data.password.length < 6 || data.password.length > 12) {
            isValidate = false;
            $('#signUp-password-error').css("visibility", "visible");
        }

        if (!data.secondPassword) {
            isValidate = false;
            $('#signUp-second-password-error').css("visibility", "visible");
            $('#signUp-second-password-error').text("请再次输入密码");
        } else if (data.secondPassword != data.password) {
            isValidate = false;
            $('#signUp-second-password-error').css("visibility", "visible");
            $('#signUp-second-password-error').text("两次输入密码不一致");
        }

        return isValidate;
    }


});*/
