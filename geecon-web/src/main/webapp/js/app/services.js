geeconApp.factory('ConferenceService', function ($resource) {
    var conferenceService = $resource('http://localhost:8080/geecon-rest/conferences/:conferenceId', {}, {});

    conferenceService.list = function () {
        return conferenceService.query();
    };

    conferenceService.getById = function (id) {
        return conferenceService.get({ conferenceId: id });
    };

    return conferenceService;
});

geeconApp.factory('TalkService', function ($resource) {
    var talkService = $resource('http://localhost:8080/geecon-rest/conferences/:conferenceId/talks/:talkId', {}, {});

    talkService.list = function (cId) {
        return talkService.query({conferenceId: cId});
    };

    talkService.getById = function (cId, tId) {
        return talkService.get({ conferenceId: cId, talkId: tId });
    };

    talkService.mySave = function (cId, talk) {
        return talk.$save({ conferenceId: cId });
    };

    return talkService;
});

geeconApp.factory('LogonService', function ($resource) {
    var loggedIn = true;
    return {
        isLoggedIn: function () {
            return loggedIn;
        },
        login: function () {
            loggedIn = true;
        },
        logout: function () {
            loggedIn = false;
        }
    }
});