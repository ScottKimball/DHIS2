(function () {
    'use strict';

    /* Services */

    var services = angular.module('dhis2.services', ['ngResource']);

    services.factory('Sync', function ($resource) {
        return $resource('../dhis2/sync');
    });

}());
