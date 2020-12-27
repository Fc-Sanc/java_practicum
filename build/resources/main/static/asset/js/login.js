$(function () {
    let url = `${root}/isLoggedIn`;
    $.get(url,
        {},
        function (res) {
            console.log(res)
            if (res !== -1) {
                console.log(res)
                location.href = `${root}/index`
            }
        },
        'json'
    )

    $('#btn_login').click(function () {
        let url = `${root}/doLogin`
        $.post(url, {
                username: $('#username').val(),
                password: $('#password').val()
            },
            function (res) {
                if (res === 0) {
                    location.href = `${root}/index`
                } else {
                    window.alert('用户名或密码错误')
                }
            }
        )
    })

    $('#btn_to_register').click(function () {
        location.href = `${root}/register`
    })
})
