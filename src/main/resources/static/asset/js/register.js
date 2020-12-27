$(function () {
    $('#btn_cancel').click(function () {
        location.href = `${root}/login`
    })

    $('#btn_register').click(function () {
        let username        = $('#username').val().trim()
        let password        = $('#password').val().trim()
        let password_repeat = $('#password_repeat').val().trim()
        let gender          = $('#gender').val().trim()
        let age             = $('#age').val().trim()
        let contact_info    = $('#contactInfo').val().trim()
        if (username === ''
            || password === ''
            || gender === ''
            || age === ''
            || contact_info === ''
        ) {
            alert('请填写全部内容')
        } else if (password_repeat !== password) {
            alert('两次密码须相同')
        } else {
            let register = `${root}/doRegister`
            $.post(register, {
                username   : username,
                password   : password,
                age        : age,
                contactInfo: contact_info,
                gender     : gender
            }, function (res) {
                if (res) {
                    alert('注册成功, 请登录')
                    location.href = `${root}/login`
                } else {
                    alert('注册失败, 可更换用户名重试')
                }
            }, 'json')
        }

    })
})
