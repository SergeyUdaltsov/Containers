$(window).ready(function () {
    sendMessage();

    $("#cancelButton").click(function () {

        window.history.back();

    });


});

function sendMessage() {
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/app/message/send',
        dataType: 'JSON',
        data: {
        },
        success: function (data) {

        },
        error: function (data) {
        }
    });
}