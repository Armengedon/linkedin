angular.module('crudaApp').factory('User',User);
User.$inject=['$resource']
function Employee($resource) {
	var resourceUrl = 'api/user/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method: 'PUT'
		}
	});
}