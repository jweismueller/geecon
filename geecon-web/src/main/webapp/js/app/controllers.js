geeconApp.controller('HomeController', function ($scope, $location, ConferenceService, LogonService) {
    $scope.loggedin = LogonService.isLoggedIn();
    $scope.conferenceList = ConferenceService.list();
    $scope.editConference = function (id) {
        $location.path("/editConference/" + id);
    };
});

geeconApp.controller('ConferenceController', function ($scope, $location, $routeParams, LogonService, ConferenceService, RoomAllocationService, RoomService, SpeakerAllocationService, SpeakerService, TalkService) {
        $scope.loggedin = LogonService.isLoggedIn();
        $scope.conference = ConferenceService.getById($routeParams.conferenceId);
        // editing talk
        if ($routeParams.talkId) {
            $scope.talk = TalkService.getById($routeParams.conferenceId, $routeParams.talkId);
            $scope.roomList = RoomService.list();
            $scope.speakerList = SpeakerService.list();
        } else {
            $scope.talk = new TalkService();
            $scope.roomList = RoomService.list();
            $scope.speakerList = SpeakerService.list();
        }
        if ($routeParams.roomId) {
            $scope.talkList = RoomAllocationService.list($routeParams.conferenceId, $routeParams.roomId);
        } else if ($routeParams.speakerId) {
            $scope.talkList = SpeakerAllocationService.list($routeParams.conferenceId, $routeParams.speakerId);
        } else if (!$routeParams.talkId) {
            $scope.talkList = TalkService.list($routeParams.conferenceId);
        }
        $scope.saveConference = function (conference, form) {
            ConferenceService.save(conference);
            $location.path("/");
        };
        $scope.editTalk = function (cId, tId) {
            $location.path("/editTalk/" + cId + "/talks/" + tId);
        };
        $scope.newTalk = function (cId, tId) {
            $location.path("/editTalk/" + cId + "/talks/");
        };
        $scope.saveTalk = function (conference, talk, form) {
            $scope.errorMessages = '';
            $scope.errors = {};
            TalkService.save({conferenceId: conference.id}, talk,
                function (data) {
                    $location.path("/conferences/" + conference.id);
                }, function (result) {
                    alert("There was an error - please validate your data.");
                });
        };
        $scope.roomTitle = function (room) {
            return room.title;
        };
        $scope.isSpeakerAssigned = function (talk, speaker) {
            if (!talk || !talk.assignments) {
                return false;
            }
            for (var i = 0; i < talk.assignments.length; i++) {
                if (talk.assignments[i].speaker.id == speaker.id) {
                    return true;
                }
            }
            return false;
        };
    }
);

geeconApp.controller('LoginController', function ($scope, $location, LogonService) {
    $scope.login = function(form) {
        // TODO
    }
    LogonService.login();
    $location.path("/");
});

geeconApp.controller('LogoutController', function ($scope, $location, LogonService) {
    LogonService.logout();
    $location.path("/");
});




