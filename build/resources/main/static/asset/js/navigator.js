$(function () {
    let user_info = new Vue({
        el          : '#user_info',
        data        : {
            user: null
        },
        beforeCreate: function () {
            let url = `${root}/getUser`

            $.get(url,
                {},
                function (res) {
                    user_info.user = res;
                }, 'json'
            )
        }
    })
})
