$(function () {
    $('#navigator').load(`${root}/component/navigator`);

    let app = new Vue({
        el  : '#title',
        data: {
            title: '多读书，读好书'
        }
    })

    let type_list = new Vue({
        el          : '#type_list',
        data        : {
            types: null
        },
        beforeCreate: function () {
            let url = `${root}/getTypes`
            $.get(url,
                function (res) {
                    type_list.types = res
                }, 'json'
            )
        }
    })

    let book_list = new Vue({
        el          : '#book_list',
        data        : {
            books: null
        },
        beforeCreate: function () {
            let url = `${root}/getBooksTop12`
            $.get(url,
                function (res) {
                    book_list.books = res
                }, 'json'
            )
        }
    })
})
