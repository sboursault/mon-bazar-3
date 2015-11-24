//var maCollec = angular.module('maCollecApp', []);
var bookControllers = angular.module('bookControllers', []);

// without routing
// bookControllers.controller('BookListController', function($scope, $http) {
//
// $http.get('rest/books').success(function(data) {
// $scope.books = data;
// });
// });

// with routing
bookControllers.controller('BookListController', [ '$scope', '$http',
		function($scope, $http) {
			$http.get('rest/books').success(function(data) {
				$scope.books = data;
			});
		} ]);
/*
 * bookControllers.controller('BookDetailController', [ '$scope',
 * '$routeParams', '$http', function($scope, $routeParams, $http) {
 * $http.get('rest/book/' + $routeParams.bookId).success(function(data) {
 * $scope.book = data; }); } ]);/
 */

bookControllers.controller('BookDetailController', [ '$scope', '$routeParams',
		'Book', function($scope, $routeParams, Book) {
			if ($routeParams.bookId) {
				 // view or edit book
				$scope.book = Book.get({
					bookId : $routeParams.bookId
				});
			} else {
				// new book
				$scope.book = new Book();
			}
			$scope.saveBook = function() {
				$scope.book.$save({}, function(book) {
					$(location).attr('href', '#/books/' + book.id);
				});
			};
		} ]);

// GET /books -> list of book snapshots (with name and link)
// GET /books/?title=abc -> list of book snapshots containing abc in their title
// (with name and link)
// POST /book -> create book
// GET /book/123 -> get book
// PUT /book/123 -> update book
// DELETE /book/123 -> update book
