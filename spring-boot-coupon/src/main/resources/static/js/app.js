var app = angular.module('coupon', []); 

app.controller('CouponController', ['$scope', '$http', '$window', function($scope, $http, $window) {
	$scope.title="쿠폰발급 서비스";
	$scope.currentPage = 0;
	$scope.size = 10;
	$scope.page = [];
	$scope.form = {
		email : ""
	};
	
	_refreshPageData(0);
	
	$scope.couponSubmit = function() {
		$http({
			method: 'POST',
			url: 'http://localhost:8080/coupons', 
			data: angular.toJson($scope.form),
			headers: {'Content-Type':'application/json; charset=UTF-8' }
		}).then(function successCallback(response) {
			_refreshPageData(0);
		}, function errorCallback(response) {
			$window.alert(response.data.errorMessage);
        });
	};
	
	function _refreshPageData(pageNo) {
		$http({
			method: 'GET',
			url: 'http://localhost:8080/coupons?page=' + pageNo
		}).then(function successCallback(response) {
			$scope.coupons = response.data.content;
			if($scope.page.length == 0) {
				_paging(response.data.totalElements);
			}
		}, function errorCallback(response) {
			console.error(response.data.errorMessage);
        });
	}
	
	function _paging(contentCnt) {
		$scope.numberOfPages = function(){
	        return Math.ceil(contentCnt/$scope.size);                
	    };
	    
	    var numberOfPages = $scope.numberOfPages();
	    for (var i=0; i<numberOfPages; i++) {
			$scope.page.push(i);
		}
	}
	
	$scope.viewPage = function(pageNo) {
		_refreshPageData(pageNo)
	}
}]);

app.filter('startFrom', function() {
    return function(input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
});