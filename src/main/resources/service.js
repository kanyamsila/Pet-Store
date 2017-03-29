var services = angular.module('ngdemo.services', ['ngResource']);

services.factory('Pet', function ($resource) {
    return $resource('/api/pets', {}, {
        query: {
            method: 'GET',
            params: {},
            isArray: false
        }
    });
});