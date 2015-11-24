var bookApp = angular.module('bookApp', [ 'ngRoute', 'bookControllers', 'bookServices' ]);
// declares a module with its dependencies

bookApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/books', {
		templateUrl : 'partial-templates/book-list.html',
		controller : 'BookListController'
	}).when('/books/new', {
		templateUrl : 'partial-templates/book-form.html',
		controller : 'BookDetailController'
	}).when('/books/:bookId', {
		templateUrl : 'partial-templates/book-detail.html',
		controller : 'BookDetailController'
	}).when('/books/:bookId/edit', {
		templateUrl : 'partial-templates/book-form.html',
		controller : 'BookDetailController'
	}).otherwise({
		redirectTo : '/books'
	});
} ]);