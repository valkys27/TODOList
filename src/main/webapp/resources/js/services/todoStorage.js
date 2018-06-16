/*global angular */

/**
 * Services that persists and retrieves todos from localStorage or a backend API
 * if available.
 *
 * They both follow the same API, returning promises for all changes to the
 * model.
 */
angular.module('todomvc')
	.factory('todoStorage', function ($http, $injector) {
		'use strict';

		// Detect if an API backend is present. If so, return the API module, else
		// hand off the localStorage adapter
		return $http.get('/api')
			.then(function () {
				return $injector.get('api');
			}, function () {
				return $injector.get('localStorage');
			});
	})

	.factory('api', function ($resource) {
		'use strict';

		var store = {
			todos: [],

			api: $resource('/api/todos/:id', null,
				{
					update: { method:'PUT' }
				}
			),

			clearCompleted: function () {
				var originalTodos = store.todos.slice(0);

				var incompleteTodos = store.todos.filter(function (todo) {
					return !todo.completed;
				});

				angular.copy(incompleteTodos, store.todos);

				return store.api.delete(function () {
					}, function error() {
						angular.copy(originalTodos, store.todos);
					});
			},

			delete: function (todo) {
				var originalTodos = store.todos.slice(0);

				store.todos.splice(store.todos.indexOf(todo), 1);
				return store.api.delete({ id: todo.id },
					function () {
					}, function error() {
						angular.copy(originalTodos, store.todos);
					});
			},

			get: function () {
				return store.api.query(function (resp) {
					angular.copy(resp, store.todos);
				});
			},

			insert: function (todo) {
				var originalTodos = store.todos.slice(0);

				return store.api.save(todo,
					function success(resp) {
						todo.id = resp.id;
						store.todos.push(todo);
					}, function error() {
						angular.copy(originalTodos, store.todos);
					})
					.$promise;
			},

			put: function (todo) {
				return store.api.update({ id: todo.id }, todo)
					.$promise;
			}
		};

		return store;
	})

	.factory('localStorage', function ($q, $http) {
		'use strict';

		var STORAGE_ID = 'todos-angularjs';

        var headers = {'Accept': 'application/json'};

		var store = {
			todos: [],

			_getFromLocalStorage: function () {
				return JSON.parse(localStorage.getItem(STORAGE_ID) || '[]');
			},

			_saveToLocalStorage: function (todos) {
				localStorage.setItem(STORAGE_ID, JSON.stringify(todos));
			},

			clearCompleted: function () {
				var deferred = $q.defer();

				var incompleteTodos = store.todos.filter(function (todo) {
				 	return !todo.completed;
				});

				 angular.copy(incompleteTodos, store.todos);

				store._saveToLocalStorage(store.todos);
                var url = '/todo/clearCompleted';
                $http({
                    method: 'GET',
                    url: url,
                    headers: headers
                }).then(function (response) {
                    deferred.resolve(store.todos);
                }, function (error) {
                    alert("failure")
                });

				return deferred.promise;
			},

			delete: function (todo) {
				var deferred = $q.defer();

				store.todos.splice(store.todos.indexOf(todo), 1);
                var url = '/todo/delete';
                $http({
                    method: 'POST',
					url: url,
                    headers: headers,
					data: todo
                }).then(function (response) {
                    deferred.resolve(store.todos);
                }, function (error) {
                    alert("failure")
                });

				return deferred.promise;
			},

			get: function () {
				var deferred = $q.defer();

                var url = '/todo/getAll';
                $http({
                    method: 'GET',
                    url: url,
                    headers: headers
                }).then(function (response) {
                    angular.copy(response.data, store.todos);
                    deferred.resolve(store.todos);
				}, function (error) {
                	alert("failure")
				});
			},

			insert: function (todo) {
				var deferred = $q.defer();

                var url = '/todo/save';
                $http({
                    method: 'POST',
                    url: url,
                    headers: headers,
                    data: todo
                }).then(function (response) {
                	todo = response.data;
                    store.todos.push(todo);
                    deferred.resolve(store.todos);
                }, function (error) {
                    alert("failure")
                });

				return deferred.promise;
			},

			put: function (todo, index) {
				var deferred = $q.defer();

                var url = '/todo/save';
                $http({
                    method: 'POST',
                    url: url,
                    headers: headers,
                    data: todo
                }).then(function (response) {
                    todo = response.data;
                    store.todos[index] = todo;
                    deferred.resolve(store.todos);
                }, function (error) {
                    alert("failure")
                });

				return deferred.promise;
			}
		};

		return store;
	});
