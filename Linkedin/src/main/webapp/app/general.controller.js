angular.module("Linkedin").controller("GeneralController", GeneralController);

GeneralController.inject = ['$scope', 'User'];

function GeneralController($scope, User) {
	$scope.users = User.query()
	$scope.user = {};
	$scope.buttonText = "Apply";

	$scope.save = function() {
		User.save($scope.user, function() {
			$scope.users = User.query();
			$scope.user = {};
			$scope.buttonText = "Apply"
		});
	}
	
	$scope.test = function() {
        console.log("Hola");
    };
}