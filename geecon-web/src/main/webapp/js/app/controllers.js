geeconApp.controller('HomeController', function ($scope, $location, ConferenceService, LogonService) {
    $scope.loggedin = LogonService.isLoggedIn();
    $scope.conferenceList = ConferenceService.list();
    $scope.editConference = function (id) {
        $location.path("/editConference/" + id);
    };
});

geeconApp.controller('ConferenceController', function ($scope, $location, $routeParams, LogonService, ConferenceService, TalkService) {
    $scope.loggedin = LogonService.isLoggedIn();
    $scope.conference = ConferenceService.getById($routeParams.conferenceId);
    $scope.talkList = TalkService.list($routeParams.conferenceId);
    if ($routeParams.talkId) {
        $scope.talk = TalkService.getById($routeParams.conferenceId, $routeParams.talkId);
    } else {
        $scope.talk = {};
    }
    $scope.saveConference = function (conference, form) {
        ConferenceService.save(conference);
        $location.path("/");
    };
    $scope.editTalk = function(cId, tId) {
        $location.path("/editTalk/" + cId + "/talks/" + tId);
    };
    $scope.newTalk = function(cId, tId) {
        $location.path("/editTalk/" + cId + "/talks/");
    };
    $scope.saveTalk = function (conference, talk, form) {
        TalkService.mySave(conference.id, talk);
        $location.path("/conferences/" + conference.id);
    };
});

geeconApp.controller('LoginController', function ($scope, $location, LogonService) {
    LogonService.login();
    $location.path("/");
});

geeconApp.controller('LogoutController', function ($scope, $location, LogonService) {
    LogonService.logout();
    $location.path("/");
});




