geeconApp.controller('HomeController',
    function HomeController($scope, ConferenceService, $routeParams) {
        $scope.showFeedback = false;
        $scope.listConferences = function(){
            ConferenceService.list().then(function(o){
                $scope.conferenceList = o;
                if (o.length == 0){
                    showAlert("warning", "No patients registered! Checked for patients by making a call to pointy-api webapp using Restangular!");
                } else {
                    showAlert("info", "Found " + o.length + " patients! Checked for patients by making a call to pointy-api webapp using Restangular!");
                }
            }, function(e){
                showAlert("error", e);
            });
        };
    }
);