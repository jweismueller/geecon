geeconApp.factory('ConferenceService', ['Restangular', 'GeeconDateService', function (Restangular, GeeconDateService) {

    function massageDate(aDate){
        return GeeconDateService.convertJSONToMMDDYYYYDate(aDate);
    };

    return {
        list: function() {
            var baseConferences = Restangular.all("conferences");
            var aPromise = baseConferences.getList().then(function(object){
                return object;
            });
            return aPromise;
        },
        patientDetail: function(id){
            return Restangular.one("conferences", id);
        },
        deletePatient: function(id){
            return Restangular.one("conferences", id).remove();
        }
    };

}]);