(function () {
    'use strict';

    /* Controllers */
    var controllers = angular.module('dhis2.controllers', []);

    controllers.controller('Dhis2SettingsCtrl', function ($scope, $http, Sync) {
        $http.get('../dhis2/dhis2-settings')
            .success(function (response) {
                $scope.settings = response;
                $scope.originalSettings = angular.copy($scope.settings);
            })
            .error(function (response) {
                // TODO: error handling
            });

        $scope.sync = function () {
            Sync.get({});
        };

        $scope.submit = function () {
            $http.post('../dhis2/dhis2-settings', $scope.settings)
                .success(function (response) {
                    $scope.settings = response;
                    $scope.originalSettings = angular.copy($scope.configs);
                })
                .error(function (response) {
                    // TODO: error handling
                });
        };
    });


}());
