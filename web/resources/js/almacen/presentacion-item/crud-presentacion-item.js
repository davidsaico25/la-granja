$(document).ready(function () {
    $("#formCRUD").submit(function (event) {
        $('#btnCreatePresentacionItem').attr('disabled', 'disabled');
        $('#loading').show();
        return true;
    });
});