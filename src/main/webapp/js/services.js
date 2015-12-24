
var bookServices = angular.module('bookServices', [ 'ngResource' ])

    .factory('Book', [ '$resource', function($resource) {
        return $resource(apiBaseUrl + 'books/:bookId', {bookId:'@id'}, {
            // extending default actions for the resource
            // documentation : https://docs.angularjs.org/api/ngResource/service/$resource
            create: {method:'POST'},
            update: {method:'PUT'}
        });
    } ]);