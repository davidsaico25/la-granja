$(document).ready(function () {
    $("#formCRUD").submit(function (event) {
        $('#btnCreatePresentacionItem').attr('disabled', '');
        $('#loading').show();
        return true;
    });
});