$(function () {
    $('#navigator').load(`${root}/component/navigator`);

    let isbn = getParam('isbn')

    let title = new Vue({
        el          : '#title',
        data        : {
            title: null
        },
        beforeCreate: function () {
            let url = `${root}/getNameByIsbn`
            $.get(url, {
                    isbn: isbn
                },
                function (res) {
                    title.title = res
                }, 'json'
            )
        }
    })

    let book_main = new Vue({
        el          : '#book_main',
        data        : {
            book      : null,
            isBorrowed: false
        },
        beforeCreate: function () {
            let url = `${root}/getBookByIsbn`
            $.get(url, {
                    isbn: isbn
                },
                function (res) {
                    book_main.book = res
                }, 'json'
            )
            $.get(`${root}/isBorrowed`, {
                    isbn: isbn
                },
                function (res) {
                    book_main.isBorrowed = res
                }, 'json'
            )
        }
    })
})
