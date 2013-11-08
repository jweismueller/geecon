geeconApp.factory('ConferenceService', function ($resource) {
    var conferenceService = $resource(constants.serverAddress + 'conferences/:conferenceId', {}, {});
    conferenceService.list = function () {
        return conferenceService.query();
    };
    conferenceService.getById = function (id) {
        return conferenceService.get({ conferenceId: id });
    };
    return conferenceService;
});

geeconApp.factory('TalkService', function ($resource) {
    var talkService = $resource(constants.serverAddress + 'conferences/:conferenceId/talks/:talkId', {}, {});
    talkService.list = function (cId) {
        return talkService.query({conferenceId: cId});
    };
    talkService.getById = function (cId, tId) {
        return talkService.get({ conferenceId: cId, talkId: tId });
    };
    return talkService;
});

geeconApp.factory('RoomAllocationService', function ($resource) {
    var roomService = $resource(constants.serverAddress + 'conferences/:conferenceId/rooms/:roomId', {}, {});
    roomService.list = function (cId, rId) {
        return roomService.query({conferenceId: cId, roomId: rId});
    };
    return roomService;
});

geeconApp.factory('SpeakerAllocationService', function ($resource) {
    var speakerService = $resource(constants.serverAddress + 'conferences/:conferenceId/speaker/:speakerId', {}, {});
    speakerService.list = function (cId, sId) {
        return speakerService.query({conferenceId: cId, speakerId: sId});
    };
    return speakerService;
});

geeconApp.factory('RoomService', function ($resource) {
    var roomService = $resource(constants.serverAddress + 'rooms/:roomId', {}, {});
    roomService.list = function () {
        return roomService.query();
    };
    roomService.getById = function (rId) {
        return roomService.query({roomId: rId});
    };
    return roomService;
});

geeconApp.factory('SpeakerService', function ($resource) {
    var speakerService = $resource(constants.serverAddress + 'speakers/:speakerId', {}, {});
    speakerService.list = function () {
        return speakerService.query();
    };
    speakerService.getById = function (sId) {
        return speakerService.query({speakerId: sId});
    };
    return speakerService;
});

geeconApp.factory('LogonService', function ($resource) {
    var loggedIn = false;
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