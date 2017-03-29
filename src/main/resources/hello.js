var app=angular.module('hello', [])
  app.controller('home', function($scope, $http) {
  $http.get('/resource/').success(function(data) {
    $scope.greeting = data;
  });
});

