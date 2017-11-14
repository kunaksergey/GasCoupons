$(document).ready(function () {
    var urlLogin = '/api/v1/token';

    $("#basket-link").on("click", function (event) {
        var token = $.cookie("coupon-token");
        if (token == null) {
            login_dialog();

        } else {
            console.log($.cookie("coupon-token"));
        }
    });


    $.ajax({
        type: 'GET',
        url: '/api/v1/price/',
        dataType: 'json',
        success: function (data) {
            var priceListDiv = $("#price-content");
            $.each(data, function (index, value) {
                console.log(value);
                priceListDiv.append(
                    '<div class="price-line" data-item="' + value.id + '">' +
                    '<span class="price-name">' + value.product.name + '</span>' +
                    '<span class="price-cost">' + value.price + '</span>' +
                    '<span class="price-link" ><a href="#">add</a></span>' +
                    '</div>');
            });


            // $(".price-link").on("click", function (event) {
            //     console.log();
            //     var priceId = $(event.target).parent().parent().data("item");
            //     console.log(priceId);
            //     if (user == null || password == null) {
            //
            //         $("#page-login").css("display", "block");
            //         $("#sendAuth").on("click", function (event) {
            //             var LOGIN = $("#login").val();
            //             var PASS = $("#pass").val();
            //             $.ajax
            //             ({
            //                 type: 'GET',
            //                 url: urlBasket,
            //                 // headers: {
            //                 //     'Authorization': make_base_auth(LOGIN, PASS)
            //                 // },
            //                 beforeSend: function (xhr) {
            //                     xhr.setRequestHeader('Authorization', make_base_auth(LOGIN, PASS));
            //
            //                 },
            //                 complite: function (event) {
            //                     console.log(event);
            //                 },
            //
            //                 success: function (data) {
            //                     console.log(data);
            //                     console.log("OK");
            //                 },
            //                 error: function (xhr, ajaxOptions, throwError) {
            //                     console.log(xhr);
            //                     console.log("Error");
            //                 },
            //             });
            //
            //         });
            //
            //
            //         return;
            //     }
            //
            //
            // });
        }
    });

    /*******************************************************/
    var login_dialog = function () {
        $("#page-login").css("display", "block");

        $("#login-close").on("click", function (event) {
            $("#page-login").css("display", "none");
        });

        $("#sendAuth").on("click", function (event) {
            $.ajax
            ({
                type: 'POST',
                url: urlLogin,
                data: '{"username":"' + LOGIN + '","password":"' + PASS + '"}',
                contentType: "application/json; charset=utf-8",
                traditional: true,

                success: function (data) {
                    $.cookie("coupon-token", data.token);
                    $("#page-login").css("display", "none");

                },
                error: function (xhr, ajaxOptions, throwError) {
                    $("label[for='login']").html("Error login or password");
                    console.log("Error");
                }
            });

        });

    };
    /*******************************************************/


});

function make_base_auth(user, password) {
    var tok = user + ':' + password;
    var hash = btoa(tok);
    return "Basic " + hash;
}

// $(".price-link").on("click", function (event) {
//     console.log();
//     var priceId = $(event.target).parent().parent().data("item");
//     console.log(priceId);
//     if (user == null || password == null) {
//         $("#page-login").css("display", "block");
//         return;
//     }
//
//
// });
/*************************************************************/
// $(".price-link").on("click", function (event) {
//     console.log();
//     var priceId = $(event.target).parent().parent().data("item");
//     console.log(priceId);
//     if (user == null || password == null) {
//         $("#page-login").css("display", "block");
//         return;
//     }
//
//     $.ajax({
//         type: 'POST',
//         url: '/api/v1/basket/' + priceId + '/count/10',
//
//         success: function (data) {
//             var priceListDiv = $("#price-content");
//             $.each(data, function (index, value) {
//                 console.log(value);
//                 priceListDiv.append(
//                     '<div class="price-line" data-item="' + value.id + '">' +
//                     '<span class="price-name">' + value.product.name + '</span>' +
//                     '<span class="price-cost">' + value.price + '</span>' +
//                     '<span class="price-link" ><a href="#">add</a></span>' +
//                     '</div>'
//                 );
//
//             });
//         }
//     });
// });


/*************************************************************/
// $("#open-popup").on("click", function (event) {
//     $(".popup").css("display", "block");
//     $(".open-popup").hide();
//     $(".sign-up").hide()
// });
//
// $("#close-popup").on("click", function (event) {
//     $(".popup").css("display", "none");
//     $(".open-popup").show();
//
// });
//
// $(".sign-up").on("click", function (event) {
//
//     $(".open-popup").show();
//
// });


       
