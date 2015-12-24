
var bookControllers = angular.module('bookControllers', [])

    // without routing
    /*.controller('BookListController', function($scope, $http) {
            $http.get('api/books').success(function(data) { $scope.books = data });
    });*/

    // with routing
    .controller('BookListController', [ '$scope', '$http',
        function($scope, $http) {
            $http.get(apiBaseUrl + 'books')
                .success( function(data) { $scope.books = data.books } );
        } ])

    .controller('BookDetailController', [ '$scope', '$routeParams',
		'Book', function($scope, $routeParams, Book) {

			$scope.saveBook = function() {
			    if ($scope.book.id) {
			        $scope.book.$update({}, function(book) {
                        $(location).attr('href', '#/books/' + book.id);
                    });
				} else {
                    $scope.book.$create({}, function(book) {
                        //TODO : get the id from the response header
                        $(location).attr('href', '#/books/' + book.id);
                    });
				}
			};

			$scope.showBookForm = function() {
			    angular.element(document).ready(function () {
			        setTimeout(function () {
			        //$(window).load(function() {
			            $(bookForm).find("button")[0].click()
			        }, 500);

			        // not very nice, but it works
			        // TODO :a better alternative to try is to call "bookForm.$show()",
			        // using the button or the form as context
                    //$scope.bookForm.$show(); doesn't work either.

                    // TODO : documenet.ready + timeout 500 : this is not ninja code !
                });
			}

		    //$scope.edit = false;

			if ($routeParams.bookId) {
				 // view or edit book
				$scope.book = Book.get({ bookId : $routeParams.bookId });
			} else {
				// new book
				$scope.book = new Book();
                $scope.showBookForm();
			}
		} ]);



