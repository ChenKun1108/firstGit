//控制层
app.controller('userController', function ($scope, userService) {


    $scope.entity={'phone':''};


    //发送验证码
    $scope.sendCode = function () {
        if ($scope.entity.phone == null||$scope.entity.phone == "") {
            alert("请输入手机号！");
            return;
        }
        userService.sendCode($scope.entity.phone).success(
            function (response) {
                alert(response.message);
            }
        )
    }

    //用户注册
    $scope.reg = function () {
        //首先判断两次输入的密码是否一致
        if ($scope.password != $scope.entity.password) {
            alert("两次密码输入不一致");
            $scope.password = "";
            $scope.entity.password = "";
            return
        }

        //用户新增
        userService.add($scope.entity, $scope.smscode).success(
            function (response) {
                alert(response.message);
            }
        );
    }
});	
