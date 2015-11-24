
var bookServices = angular.module('bookServices', [ 'ngResource' ]);

bookServices.factory('Book', [ '$resource', function($resource) {
	return $resource('/rest/book/:bookId', {bookId:'@id'}, {
		/*query : {
			method : 'GET',
			params : {
				bookId : 'books'
			},
			isArray : true
		}*/
	});
} ]);