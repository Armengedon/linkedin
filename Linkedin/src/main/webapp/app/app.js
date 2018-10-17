var app = angular.module('Linkedin',['ui.router', 'nStorage', 'ngResource']);

app.constant('urls', {
	BASE: 'http://localhost:8080',
	USER_DEVICE_API: 'http://localhost:8080/api/user'
});

app.config(['$stateProvider', '$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {
	 
    $stateProvider
        .state('home', {
            url: '/',
            templateUrl: 'partials/list',
            controller:'user.controller',
            controllerAs:'ctrl',
            resolve: {
                users: function ($q, UserService) {
                    console.log('Load all users');
                    var deferred = $q.defer();
                    UserService.loadAllUsers().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                }
            }
        });
    $urlRouterProvider.otherwise('/');
	}]);