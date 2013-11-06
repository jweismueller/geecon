geeconApp.controller('CommonController',
    function CommonController($scope) {
	    $scope.showFeedback = false;
        
        $scope.closeAlert = function(){
        	$scope.showFeedback = false;
        };
        
        function showAlert(type, message) {
    		$scope.status  = message;
    		$scope.showFeedback = "true";
    		$scope.alertType = type;
        }
    }
);