(function () {
    'use strict';

    /* Services */

    var services = angular.module('dhis2.services', ['ngResource']);

    services.factory('Programs', function ($resource) {
        return $resource('../dhis2/programs');
    });

}());
