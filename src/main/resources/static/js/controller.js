app.controller('NavbarCtrl', function ($scope, $route) {
    $scope.$route = $route;
});

app.controller('gradeController', function($scope, $route, Grade, Student, Subject) {
    $scope.loadData = function () {
        Grade.query(function(data) {
            $scope.grades = data;
        });
        Student.query(function(data) {
            $scope.students = data;
        });
        Subject.query(function(data) {
            $scope.subjects = data;
        });
    };
    $scope.loadData();
    $scope.grade = new Grade();
    $scope.showForm = function(type, id) {
        $scope.formType = type;
        if (type == "create") {
            $scope.grade = new Grade();
            $scope.formHeading = "Create new grade";
            $('#form').modal('show');
        } else {
            $scope.formHeading = "Edit grade";
            Grade.get({id:id}, function(grade) {
                $scope.grade = grade;
                $('#form').modal('show');
            });
        }
    };
    $scope.delete = function(id) {
        Grade.delete({id:id}, function(res) {
            $scope.loadData();
        });
    };
    $scope.save = function() {
        if ($scope.formType == "edit") {
            $scope.grade.$update(function() {
                $('#form').modal('hide');
                $scope.loadData();
            });
        } else {
            $scope.grade.$save(function() {
                $('#form').modal('hide');
                $scope.loadData();
            });
        }
    };
});

app.controller('studentController', function($scope, $route, Student) {
    $scope.loadData = function () {
        Student.query(function(data) {
            $scope.students = data;
        });
    };
    $scope.loadData();
    $scope.student = new Student();
    $scope.showForm = function(type, id) {
        $scope.formType = type;
        if (type == "create") {
            $scope.student = new Student();
            $scope.formHeading = "Create new student";
            $('#form').modal('show');
        } else {
            $scope.formHeading = "Edit student";
            Student.get({id:id}, function(student) {
                $scope.student = student;
                $('#form').modal('show');
            });
        }
    };
    $scope.save = function() {
        if ($scope.formType == "edit") {
            $scope.student.$update(function() {
                $('#form').modal('hide');
                $scope.loadData();
            });
        } else {
            $scope.student.$save(function() {
                $('#form').modal('hide');
                $scope.loadData();
            });
        }
    };
    $scope.delete = function(id) {
        Student.delete({id:id}, function(res) {
            $scope.loadData();
        });
    };
});

app.controller('subjectController', function($scope, $route, Subject) {
    $scope.loadData = function () {
        Subject.query(function(data) {
            $scope.subjects = data;
        });
    };
    $scope.loadData();
    $scope.subject = new Subject();
    $scope.showForm = function(type, id) {
        $scope.formType = type;
        if (type == "create") {
            $scope.subject = new Subject();
            $scope.formHeading = "Create new subject";
            $('#form').modal('show');
        } else {
            $scope.formHeading = "Edit subject";
            Subject.get({id:id}, function(subject) {
                $scope.subject = subject;
                $('#form').modal('show');
            });
        }
    };
    $scope.save = function() {
        if ($scope.formType == "edit") {
            $scope.subject.$update(function() {
                $('#form').modal('hide');
                $scope.loadData();
            });
        } else {
            $scope.subject.$save(function() {
                $('#form').modal('hide');
                $scope.loadData();
            });
        }
    };
    $scope.delete = function(id) {
        Subject.delete({id:id}, function(res) {
            $scope.loadData();
        });
    };
});

app.controller('dashboardController', function($scope, $route, Report) {
    $scope.loadData = function () {
        Report.query(function(data) {
            $scope.reports = data;
        });
    };
    $scope.loadData();
});


