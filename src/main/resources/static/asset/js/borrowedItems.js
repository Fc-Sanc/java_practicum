$(function () {
    let url = `${root}/isLoggedIn`;

    let page = getParam('page')
    let size = getParam('size')

    $.get(url,
        function (res) {
            console.log(res)
            if (res === -1) {
                location.href = `${root}/index`
            }
        }, 'json'
    )

    $('#navigator').load(`${root}/component/navigator`);

    let borrowed_items = new Vue({
        el          : '#borrowed_items',
        data        : {
            orders: null,
            books : []
        },
        beforeCreate: function () {
            let getOrders = `${root}/getOrders`
            $.get(getOrders, {
                page: page,
                size: size
            }, function (res) {
                borrowed_items.orders = res
                let getBookByIsbn     = `${root}/getBookByIsbn`
                for (let i = 0; i < borrowed_items.orders.length; i++) {
                    $.get(getBookByIsbn, {
                        isbn: borrowed_items.orders[i]['bookIsbn']
                    }, function (res) {
                        borrowed_items.books.push(res)
                    }, 'json')
                }
            }, 'json')
        }
    })

    let pagination = new Vue({
        el          : '#pagination',
        data        : {
            number: 0,
            page  : page,
            size  : size
        },
        beforeCreate: function () {
            let url = `${root}/getOrderPageNumber`
            $.get(url, {
                    size: size,
                },
                function (res) {
                    pagination.number = res
                }, 'json'
            )
        }
    })

})

function returnBook(isbn, name) {
    if (confirm('确认归还《' + name + '》?')) {
        let returnBook = `${root}/returnBook`
        $.get(returnBook, {
            isbn: isbn
        }, function (res) {
            if (res) {
                alert('《' + name + '》归还成功')
                location.reload()
            } else {
                alert('《' + name + '》归还失败')
            }
        }, 'json')
    }
}

function renewBook(isbn, name) {
    if (confirm('确认续借《' + name + '》?')) {
        let renewBook = `${root}/renewBook`
        $.get(renewBook, {
            isbn: isbn
        }, function (res) {
            if (res) {
                alert('《' + name + '》续借成功')
                location.reload()
            } else {
                alert('《' + name + '》续借失败')
            }
        }, 'json')
    }
}
