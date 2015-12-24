var apiBaseUrl = "http://mon-bazar.appspot.com/api/";
// TODO : poluting the document scope with apiBaseUrl. We probably can do better.

// declare bazarApp module with its dependencies
var bazarApp = angular.module('bazarApp', [
    'ngRoute',
    'bookControllers',
    'bookServices',
    "xeditable"
]);


bazarApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/books', {
		templateUrl : 'partial-templates/book-list.html',
		controller : 'BookListController'
	}).when('/books/new', {
		templateUrl : 'partial-templates/book-detail.html',
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

// set theme for xeditable
bazarApp.run(function(editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});