$(document).ready(function () {
    var user;
    var password;
    var urlBasket = '/api/v1/basket/';
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


            $(".price-link").on("click", function (event) {
                console.log();
                var priceId = $(event.target).parent().parent().data("item");
                console.log(priceId);
                if (user == null || password == null) {
                    
                    $("#page-login").css("display", "block");
                    $("#sendAuth").on("click", function (event) {
                        var LOGIN = $("#login").val();
                        var PASS = $("#pass").val();
                        $.ajax
                        ({
                            type: 'GET',
                            url: urlBasket,
                            // headers: {
                            //     'Authorization': make_base_auth(LOGIN, PASS)
                            // },
                            beforeSend: function (xhr){
                                xhr.setRequestHeader('Authorization', make_base_auth(LOGIN, PASS));
                              
                                 },
                            complite:function (event){
                                console.log(event);  
                            },
                            
                            success: function (data) {
                                console.log(data);
                                console.log("OK");
                            },
                            error: function (xhr, ajaxOptions, throwError) {
                                console.log(xhr);
                                console.log("Error");
                            },
                        });
                        
                    });

                    
                    return;
                }


            });
        }
    });
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


       
