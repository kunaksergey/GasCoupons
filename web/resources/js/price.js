$(document).ready(function () {
    var urlLogin = '/api/v1/token';
    var urlPrice = '/api/v1/price';
    var urlBasket = '/api/v1/basket';


    $("#basket-link").on("click", function (event) {
        var token = $.cookie("coupon-token");
        if (token == null) {
            login_dialog();
        } else {
            console.log($.cookie("coupon-token"));
            getBasket($.cookie("coupon-token"));
        }
    });


    $.ajax({
        type: 'GET',
        url: urlPrice,
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
            var LOGIN = $("#login").val();
            var PASS = $("#pass").val();
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
                    getBasket(data.token);

                },
                error: function (xhr, ajaxOptions, throwError) {
                    $("label[for='login']").html("Error login or password");
                    console.log("Error");
                }
            });

        });

    };
    /*******************************************************/
    var getBasket = function (token) {
        $.ajax
        ({
            type: 'GET',
            url: urlBasket,
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Accept', 'application/json');
                xhr.setRequestHeader('Authorization', make_token(token));
            },
            success: function (data) {
                show_basket(data);
            },
            error: function (xhr, ajaxOptions, throwError) {
                console.log("Error");
            }
        });
    };
    /*******************************************************/
    //Показываем корзину
    var show_basket = function (data) {
        $("#basket-content").css("display", "block");
        $("#basket-close").on("click", function (event) {
            $("#basket-content").css("display", "none");
        });
        $("#basket-body").html(basketToHtml(data));

    };
    /*******************************************************/

    /*******************************************************/
    //Показываем корзину
    var basketToHtml = function (data) {
        var html = '<table><th>Product</th><th>Station</th><th>Count</th><th>Price</th><th>Summ</th>';
        $.each(data.basketItemDtoList, function (index, value) {
            console.log(value);
            html += '<tr>' +
                '<td>{name}</td>'.replace('{name}',value.price.product.name) +
                '<td>{station}</td>'.replace('{station}',value.price.station.name) +
                '<td>{count}</td>'.replace('{count}',value.count) +
                '<td>{price}</td>'.replace('{price}',value.price.price) +
                '<td>{summ}</td>'.replace('{summ}',value.summ) +
                '</tr>'
        });

        html += '<tr><td colspan="5">' + data.summ + '</td></tr>';
        html += '</table>';
        return html;
    };
    /*******************************************************/


});

function make_base_auth(user, password) {
    var tok = user + ':' + password;
    var hash = btoa(tok);
    return "Basic " + hash;
}

function make_token(token) {
    return "Bearer " + token;
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


       
