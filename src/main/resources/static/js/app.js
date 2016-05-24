var app = angular.module('app', ['ngRoute','ngResource']);

app.config(function($routeProvider){
    $routeProvider
        .when('/grades',{
            templateUrl: '/views/grades.html',
            controller: 'gradeController',
            activetab: 'grades'
        })
        .when('/students',{
            templateUrl: '/views/students.html',
            controller: 'studentController',
            activetab: 'students'
        })
        .when('/subjects',{
            templateUrl: '/views/subjects.html',
            controller: 'subjectController',
            activetab: 'subjects'
        })
        .otherwise({
            templateUrl: '/views/dashboard.html',
            controller: 'dashboardController',
            activetab: 'dashboard'
        }
    );
});

app.factory("Grade", function($resource) {
    return $resource("/api/grades/:id", {id: '@id'}, {
        update: {
            method: 'PUT'
        }
    })
});
app.factory("Student", function($resource) {
    return $resource("/api/students/:id", {id: '@id'}, {
        update: {
            method: 'PUT'
        }
    })
});
app.factory("Subject", function($resource) {
    return $resource("/api/subjects/:id", {id: '@id'}, {
        update: {
            method: 'PUT'
        }
    })
});
app.factory("Report", function($resource) {
    return $resource("/api/reports/:id");
});