
var app = angular.module('MyApp', []);

app.controller('MyCtrl', function($scope, $http) {

  $http.get("api/pets")
    .success(function(data) {
      $scope.petRepository = data;
    });
 
 $scope.createPet = function(){
     $http({
         method: 'POST',
         url:"api/pets",
         data: {type: $scope.type, name: $scope.name, color: $scope.color, price:$scope.price}
     }).success(function(data){
     $scope.newPet = data;
     });
 };
 
 $scope.updatePet = function(){
     $http({
         method:'PUT',
         url:"api/petupdate/{petId}"+$scope.id,
         data: {type: $scope.type, name: $scope.name, color: $scope.color, price:$scope.price}
     }).success(function(data){
         $cope.updatedPet = data;
     });
 };
 
 $scope.findPet = function(){
   $http.get("api/pets/{petId}"+ $scope.id)
        .success(function(data){
            $scope.onePet = data;
   });
 };
 $scope.deletePet = function (){
   $http.delete("api/pets/{petId}"+$scope.id)
           .success(function(data){
               $scope.deletedPet = data;
  });
 };
 $scope.findbyType = function(){
     $http.get("api/pets/tpye/like/{bytype}"+ $scope.type)
        .success(function(data){
            $scope.bytye = data;
   });
 };
});
