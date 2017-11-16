$(document).ready(function () {
    var urlLogin = '/api/v1/token';
    var urlPrice = '/api/v1/price';
    var urlBasket = '/api/v1/basket';

    $("#delete-token").on("click", function (event) {
        $.cookie('coupon-token', '', {expires: -1, path: '/'});
        console.log("удален");
    });

    $("#change-token").on("click", function (event) {
        $.cookie('coupon-token', 'ass');
        console.log("изменен");
    });

    $("#basket-link").on("click", function (event) {
        getBasket();
    });

    $("#login-close").on("click", function (event) {
        $("#page-login").css("display", "none");
    });

    $("#basket-close").on("click", function (event) {
        $("#basket-content").css("display", "none");
    });


    //add action for login page
    $("#sendAuth").on("click", function (event) {
        var LOGIN = $("#login").val();
        var PASS = $("#pass").val();
        console.log("send");
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
                getBasket();

            },
            error: function (xhr, ajaxOptions, throwError) {
                $("label[for='login']").html("Error login or password");
                console.log("Error");
            }
        });

    });


    //Загрузка прайса на главную страницу
    $.ajax({
        type: 'GET',
        url: urlPrice,
        dataType: 'json',
        success: function (data) {
            $("#price-body").html(priceToHtml(data));

            $('a[name="add"]').on("click", function (event) {

                var priceId = $(event.target).data('item');
                // console.log(urlBasket + '/price/{idPrice}/count/20'.replace('{idPrice}', priceId));

                //add in basket
                $.ajax
                ({
                    type: 'POST',
                    url: urlBasket + '/price/{idPrice}/count/20'.replace('{idPrice}', priceId),
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader('Authorization', make_token($.cookie('coupon-token')));
                    },
                    success: function (data) {
                        create_basket_body(data);
                        console.log(data);
                        console.log("add");
                    },
                    error: function (xhr, ajaxOptions, throwError) {
                        login_dialog();
                        console.log("Error");
                    }
                });
            });
        }

    });

  

    /*******************************************************/
    var login_dialog = function () {
        $("#page-login").css("display", "block");
        $("#basket-content").css("display", "none");
    };
    /*******************************************************/
    //Запрашиваем корзину у сервера
    var getBasket = function () {
        $.ajax
        ({
            type: 'GET',
            url: urlBasket,
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Accept', 'application/json');
                xhr.setRequestHeader('Authorization', make_token($.cookie('coupon-token')));
            },
            success: function (data) {
                show_basket(data);
            },
            error: function (xhr, ajaxOptions, throwError) {
                login_dialog();
            }
        });
    };
    /*******************************************************/
    //Показываем корзину
    var show_basket = function (data) {
        $("#basket-content").css("display", "block");
        create_basket_body(data);
    };
    /*******************************************************/

    var create_basket_body = function (data) {
        $("#basket-body").html(basketToHtml(data)); //показываем корзину

        //удаляем 20 единиц
        $('button[name="decrease"]').on("click", function (event) {
            var priceId = $(event.target).data('item');
            
            //decrease(-) price
            $.ajax
            ({
                type: 'PUT',
                url: urlBasket + '/price/{id}/count/-20'.replace('{id}', priceId),
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('Authorization', make_token($.cookie('coupon-token')));
                },
                success: function (data) {
                    create_basket_body(data);
                },
                error: function (xhr, ajaxOptions, throwError) {
                    login_dialog();
                }
            });
        });

        //добавляем 20 единиц
        $('button[name="increase"]').on("click", function (event) {
            var priceId = $(event.target).data('item');

            //increase(+) price
            $.ajax
            ({
                type: 'PUT',
                url: urlBasket + '/price/{id}/count/20'.replace('{id}', priceId),
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('Authorization', make_token($.cookie('coupon-token')));
                },
                success: function (data) {
                    create_basket_body(data);
                },
                error: function (xhr, ajaxOptions, throwError) {
                    login_dialog();
                    console.log("Error");
                }
            });
        });

        // //удаляем позицию из корзины
        $('button[name="del-position"]').on("click", function (event) {
            var priceId = $(event.target).data('item');
            
            //del price
            $.ajax
            ({
                type: 'DELETE',
                url: urlBasket + '/price/{id}'.replace('{id}', priceId),
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('Authorization', make_token($.cookie('coupon-token')));
                },
                success: function (data) {
                    create_basket_body(data);
                },
                error: function (xhr, ajaxOptions, throwError) {
                    login_dialog();
                }
            });
        });
    };

    /*******************************************************/
    //Конвертируем dump прайса в html
    var priceToHtml = function (data) {
        var html = '<table><tr><th>Product</th><th>Station</th><th>Price</th><th></th></tr>';
        $.each(data, function (index, value) {
            console.log(value);

            html += '<tr>' +
                '<td>{name}</td>'.replace('{name}', value.product.name) +
                '<td>{station}</td>'.replace('{station}', value.station.name) +
                '<td>{price}</td>'.replace('{price}', value.price) +
                '<td><a href="#" name="add" data-item={id}>add</a></td>'.replace('{id}', value.id) +
                '</tr>';
        });

        //End each
        html += "</table>";
        return html;
    };
    
    /*******************************************************/
    //Конвертируем dump корзину в html
    var basketToHtml = function (data) {
        var html = '<table><th></th><th>Product</th><th>Station</th><th>Count</th><th>Price</th><th>Summ</th>' +
            '<th colspan="3">Action</th>';
        
        $.each(data.basketItemDtoList, function (index, value) {
            console.log(value);
            html += '<tr>' +
                '<td><input type="hidden" name="id" value="{id}"></td>'.replace('{id}', value.price.id) +
                '<td>{name}</td>'.replace('{name}', value.price.product.name) +
                '<td>{station}</td>'.replace('{station}', value.price.station.name) +
                '<td>{count}</td>'.replace('{count}', value.count) +
                '<td>{price}</td>'.replace('{price}', value.price.price) +
                '<td>{summ}</td>'.replace('{summ}', value.summ) +
                '<td><button name="decrease" data-item="{id}">-</button></td>'.replace('{id}', value.price.id) +
                '<td><button name="increase" data-item="{id}">+</button></td>'.replace('{id}', value.price.id) +
                '<td><button name="del-position" data-item="{id}">X</button></td>'.replace('{id}', value.price.id) +
                '</tr>';
        });

        //End each

        html += '<tr><td colspan="5">Total: {total}</td></tr>'.replace('{total}', data.summ);
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

       
