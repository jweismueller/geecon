var geeconApp = angular.module('geeconApp', ['restangular', 'ui.bootstrap'])
    .config(function ($routeProvider, RestangularProvider, $locationProvider) {

        RestangularProvider.setBaseUrl('http://localhost:8080/geecon-rest');
        //RestangularProvider.setBaseUrl("${RestEndpoint}");

        RestangularProvider.setResponseExtractor(function (response, operation, what, url) {
            var newResponse = response.payload;
            if (angular.isArray(newResponse)) {
                angular.forEach(newResponse, function (value, key) {
                    if (newResponse[key] != undefined) {
                        newResponse[key].originalElement = angular.copy(value);
                    }
                });
            } else {
                if (newResponse != undefined) {
                    newResponse.originalElement = angular.copy(newResponse);
                }
            }
            return newResponse;
        });
        $routeProvider.when('/home',
            {
                templateUrl: 'views/home.html',
                controller: 'HomeController'
            });
        $routeProvider.when('/conferences/:conferenceId',
            {
                templateUrl: 'views/conference.html',
                controller: 'ConferenceController'
            });
        $routeProvider.otherwise({redirectTo: '/home'});
    });

