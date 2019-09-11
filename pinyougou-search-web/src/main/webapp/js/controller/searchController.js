app.controller('searchController',function($scope,searchService) {
    //定义搜索变量
    $scope.searchMap = {'keywords': '', 'category': '', 'brand': '', 'spec': {}};

    //搜索
    $scope.search = function () {
        searchService.search($scope.searchMap).success(
            function (response) {
                $scope.resultMap = response;
            }
        );
    }

    //添加搜索项
    $scope.addSearchItem = function (key, value) {
        //如果是 分类或者是品牌  他们是 searchMap.xx  格式
        if (key == 'category' || key == 'brand') {
            $scope.searchMap[key] = value;
        } else {// 点击的是扩展列表 格式是 searchMap.spec.xx
            $scope.searchMap.spec[key] = value;
        }

        //执行搜索
        $scope.search();
    }

    //撤销搜索选项
    $scope.removeSearchItem=function (key) {
        //如果是 分类或者是品牌  他们是 searchMap.xx  格式
        if (key == 'category' || key == 'brand') {
            $scope.searchMap[key] ='';
        } else {// 点击的是扩展列表 格式是 searchMap.spec.xx
            delete $scope.searchMap.spec[key];//直接删除
        }

        //执行搜索
        $scope.search();
    }

})