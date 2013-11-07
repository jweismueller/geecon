var geeconApp = angular.module('geeconApp', ['ngResource', 'ngRoute', 'ui.bootstrap', 'ngCookies']);

geeconApp.directive(
    'pdDateInput',
    function(dateFilter) {
        return {
            require: 'ngModel',
            template: '<input type="date">',
            replace: true,
            link: function(scope, elm, attrs, ngModelCtrl) {
                ngModelCtrl.$formatters.unshift(function (modelValue) {
                    return dateFilter(modelValue, 'yyyy-MM-dd');
                });
                ngModelCtrl.$parsers.unshift(function(viewValue) {
                    return new Date(viewValue);
                });
            }
        };
    });

var constants = {
    "serverAddress": "http://localhost:8080/geecon-rest/"
}
