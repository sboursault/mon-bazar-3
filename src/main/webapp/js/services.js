
var bookServices = angular.module('bookServices', [ 'ngResource' ]);

bookServices.factory('Book', [ '$resource', function($resource) {
	return $resource('/api/books/:bookId', {bookId:'@id'}, {
		// extending default actions for the resource
		// documentation : https://docs.angularjs.org/api/ngResource/service/$resource
		create: {method:'POST'},
		update: {method:'PUT'}
	});
} ]);