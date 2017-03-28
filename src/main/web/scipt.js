var app = angular.module('HelloWorldApp', [])


   app.controller('HelloWorldController', function($scope) {
       $scope.greeting = "Hello World";
});