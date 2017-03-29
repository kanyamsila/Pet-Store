var app = angular.module('hello', [])
  app.controller('home', function($scope) {
    $scope.greeting = {id: 'xxx', content: 'Hello World!'};
});