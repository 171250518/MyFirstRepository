var EmployeeChoose;
$(document).ready(function () {
     getEmployeeList();
    $('#employ-add-btn').click(function () {
            var id=-1;
            var identity;
            var password=123456;
            if ($("#add-identity").children('option:selected').val()=="员工"){
                identity=1;
            }else{
                identity=2;
            }
            var form = {
                id:id,
                username: $("#add-name").val(),
                password: password,
                identity:identity,
            };
            postRequest(
                '/employee/add',
                form,
                function (res) {
                    if(res.success){
                        getEmployeeList();
                        $("#employeeModal").modal('hide');
                        alert("成功添加");
                    } else {
                        alert(res.message);
                    }
                },
                function (error) {
                    alert(JSON.stringify(error));
                }
            );

        });
    $('#edit-btn').click(function () {
        var id=parseInt(EmployeeChoose);
        var identity;
        var password=123456;
        if ($("#edit-identity").children('option:selected').val()=="员工"){
            identity=1;
        }else{
            identity=2;
        }

        var form = {
            id: id,
            identity: identity,
            password: password,
            username: $("#edit-name").val(),

        };
        postRequest(
            '/employee/update',
            form,
            function (res) {
                if(res.success){
                    getEmployeeList();
                    $("#employEditModal").modal('hide');
                } else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );

    });
});
function getEmployeeList() {
    getRequest(
        '/employee/get/all',
        function (res) {
            renderEmployeeList(res.content);
        },
        function (error) {
            alert(error);
        }
    );
}
function renderEmployeeList(employee) {
    $("#employees").html('');

    var div = "                            <tr>\n" +
        "                                <td><b>序号</b></td>\n" +
        "                                <td><b>身份</b></td>\n" +
        "                                <td><b>账号</b></td>\n" +
        "                                <td><b>操作</b></td>\n" +
        "                            </tr>" ;
    for (var i=0;i<employee.length;i++)
    {

        if(employee[i].identity==1) {
            div = div + "<tr>\n" +
                "                <td>" + employee[i].id + "</td>" +
                "                <td>员工</td>" +
                "                <td>" + employee[i].username + "</td>" +
                "                <td><a class='primary-text' data-toggle='modal' data-target='#employEditModal' onclick='saveEmployeeChoose(this.id)'  id='" + employee[i].id+"' style='color: #007DD3;text-decoration: underline'>修改</a><a style='color: red'  onclick='deleteEmployee(this.id)'  id='" + employee[i].id+"'>删除</a></td>\n" +
                "                </tr>";
        }else if(employee[i].identity==2) {
            div = div + "<tr>\n" +
                "                <td>" + employee[i].id + "</td>" +
                "                <td>经理</td>" +
                "                <td>" + employee[i].username + "</td>" +
                "                <td><a class='primary-text' data-toggle='modal' data-target='#employEditModal'  onclick='saveEmployeeChoose(this.id)'  id='" + employee[i].id+"' style='color: #007DD3;text-decoration: underline'>修改</a><a style='color: red' onclick='deleteEmployee(this.id)'  id='" + employee[i].id+"'>删除</a></td>\n" +
                "                </tr>";

        }
    }

    $("#employees").append(div);


}

function saveEmployeeChoose(i) {
    EmployeeChoose = i;
}
function deleteEmployee(i) {
    var r=confirm("确认要删除该员工的信息吗？");
    if (r) {
        deleteRequest(
            '/employee/delete/batch',
            {employeeIdList:[Number(i)]},
            function (res) {
                if(res.success){
                    getEmployeeList();
                } else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

}



