(function() {
    'use strict';

    /* Controllers */
    var controllers = angular.module('dhis2.controllers', []);


    controllers.controller('Dhis2SettingsCtrl', function($scope, $http, Sync) {


        $scope.sync = function () {
          Sync.get({});
        };


    });


}());
