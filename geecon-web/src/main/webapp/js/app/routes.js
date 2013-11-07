var geeconConfig = function ($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'HomeController',
            templateUrl: 'partials/home.html'
        })
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'partials/home.html'
        })
        .when('/logout', {
            controller: 'LogoutController',
            templateUrl: 'partials/home.html'
        })
        .when('/conferences/:conferenceId', {
            controller: 'ConferenceController',
            templateUrl: 'partials/viewConference.html'
        })
        .when('/conferences/:conferenceId/rooms/:roomId', {
            controller: 'ConferenceController',
            templateUrl: 'partials/viewConference.html'
        })
        .when('/conferences/:conferenceId/speaker/:speakerId', {
            controller: 'ConferenceController',
            templateUrl: 'partials/viewConference.html'
        })
        .when('/editConference/:conferenceId', {
            controller: 'ConferenceController',
            templateUrl: 'partials/editConference.html'
        })
        .when('/editTalk/:conferenceId/talks/', {
            controller: 'ConferenceController',
            templateUrl: 'partials/editTalk.html'
        })
        .when('/editTalk/:conferenceId/talks/:talkId', {
            controller: 'ConferenceController',
            templateUrl: 'partials/editTalk.html'
        })
}

geeconApp.config(geeconConfig);