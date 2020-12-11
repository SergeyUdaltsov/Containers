$(window).ready(function () {
    alert('users')
    getAllUsers();

    $("#cancelButton").click(function () {

        window.history.back();

    });


});

function getAllUsers() {
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/app/users/get/all',
        dataType: 'JSON',
        data: {
        },
        success: function (data) {
            var new_tbody = document.createElement('tbody');
            if (data.length === 0) {
                document.getElementsByTagName("tbody").item(0).parentNode
                    .replaceChild(new_tbody, document.getElementsByTagName("tbody").item(0));
                return;
            }
            $.each(data, function () {

                 var row = document.createElement("tr");
                 emailCell = document.createElement("td");
                 firstNameCell = document.createElement("td");
                 lastNameCell = document.createElement("td");
                 genderCell = document.createElement("td");
                 birthdayCell = document.createElement("td");
                 var date = new Date(0);
                 date.setUTCSeconds(this.birthday);
                 emailNode = document.createTextNode(this.email);
                 firstNameNode = document.createTextNode(this.firstName);
                 lastNameNode = document.createTextNode(this.lastName);
                 genderNode = document.createTextNode(this.gender);
                 birthdayNode = document.createTextNode(date);

                 emailCell.appendChild(emailNode);
                 firstNameCell.appendChild(firstNameNode);
                 lastNameCell.appendChild(lastNameNode);
                 genderCell.appendChild(genderNode);
                 birthdayCell.appendChild(birthdayNode);

                 row.appendChild(emailCell);
                 row.appendChild(firstNameCell);
                 row.appendChild(lastNameCell);
                 row.appendChild(genderCell);
                 row.appendChild(birthdayCell);

                 new_tbody.appendChild(row);

                 document.getElementsByTagName("tbody").item(0).parentNode
                    .replaceChild(new_tbody, document.getElementsByTagName("tbody").item(0));

            });
        },
        error: function (data) {
            if (data.status === 406) {
                alert(vocabulary[language]['exists']);
            }
        }
    });
}

function prepareDate(date) {
    var month = (Object.values(date)[1] < 10)? ("-0" + Object.values(date)[1]) : ("-" + Object.values(date)[1]);

    var day = (Object.values(date)[2] < 10)? ("-0" + Object.values(date)[2]) : ("-" + Object.values(date)[2]);

    var date = Object.values(date)[0] + month + day;
    return date;
}

