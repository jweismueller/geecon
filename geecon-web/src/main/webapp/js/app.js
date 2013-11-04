angular.module('asriel', [ 'asrielService' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/home', {
				templateUrl : 'partials/home.html',
				controller : AsrielCtrl
			// Add a default route
			}).otherwise({
				redirectTo : '/home'
			});
		} ]);

angular.module('asrielService', [ 'ngResource' ]).factory('AsrielList',
		function($resource) {
			return $resource('/rest/asriel/', {});
		});