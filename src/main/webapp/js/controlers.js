
var bookControllers = angular.module('bookControllers', []);

// without routing
/*bookControllers.controller('BookListController', function($scope, $http) {
        $http.get('api/books').success(function(data) { $scope.books = data });
});*/

// with routing
bookControllers.controller('BookListController', [ '$scope', '$http',
        function($scope, $http) {
            $http.get('api/books').success(function(data) { $scope.books = data.books });
        } ]);

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
			$scope.createBook = function() {
				$scope.book.$create({}, function(book) {
				    //TODO : get the id from the response header
					$(location).attr('href', '#/books/' + book.id);
				});
			};
			$scope.updateBook = function() {
				$scope.book.$update({}, function(book) {
					$(location).attr('href', '#/books/' + book.id);
				});
			};

		} ]);

