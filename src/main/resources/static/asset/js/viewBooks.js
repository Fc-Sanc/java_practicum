$(function () {
    $('#navigator').load(`${root}/component/navigator`);
    let page             = getParam('page')
    let size             = getParam('size')
    let typeId           = getParam('typeId')
    let searchingKeyword = getParam('keyword')

    let book_list = new Vue({
        el          : '#books',
        data        : {
            books: null
        },
        beforeCreate: function () {
            let url = `${root}/getPageOfBooks`
            $.get(url, {
                    page   : page,
                    size   : size,
                    typeId : typeId,
                    keyword: encodeURI(encodeURI(searchingKeyword))
                },
                function (res) {
                    book_list.books = res
                }, 'json'
            )
        }
    })

    let pagination = new Vue({
        el          : '#pagination',
        data        : {
            number : 0,
            size   : size,
            typeId : typeId,
            page   : page,
            keyword: searchingKeyword
        },
        beforeCreate: function () {
            let url = `${root}/getPageNumber`
            $.get(url, {
                    size  : size,
                    typeId: typeId,
                    keyword: encodeURI(encodeURI(searchingKeyword))
                },
                function (res) {
                    pagination.number = res
                }, 'json'
            )
        }
    })
})
