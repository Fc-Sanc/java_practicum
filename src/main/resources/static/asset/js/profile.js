function updateUser() {
    let password       = $('#update_password').val().trim()
    let passwordRepeat = $('#update_password_repeat').val().trim()
    let gender         = $('#update_gender').val().trim()
    let age            = $('#update_age').val().trim()
    let contactInfo    = $('#update_contact_info').val().trim()
    if (age === '' || contactInfo === '') {
        alert('请填写全部必填项')
    } else {
        if (password !== '') {
            if (passwordRepeat !== password) {
                alert('两次密码输入须相同！')
            } else {
                let updatePassword = `${root}/updatePassword`
                $.post(updatePassword, {
                    password: password
                }, function (res) {
                    if (res) {
                        alert('更新密码成功')
                    } else {
                        alert('更新密码失败')
                    }
                }, 'json')
            }
        }
        let updateUser = `${root}/updateUser`
        $.post(updateUser, {
            gender     : gender,
            age        : age,
            contactInfo: contactInfo
        }, function (res) {
            if (res) {
                alert('更新信息成功')
                location.reload();
            } else {
                alert('更新信息失败')
            }
        }, 'json')
    }
}

$(function () {

    let url = `${root}/isLoggedIn`;
    $.get(url,
        function (res) {
            console.log(res)
            if (res === -1) {
                location.href = `${root}/index`
            }
        }, 'json'
    )

    $('#navigator').load(`${root}/component/navigator`);

    let profile_title = new Vue({
        el          : '#profile_title',
        data        : {
            title: null
        },
        beforeCreate: function () {
            let getUsername = `${root}/getUsername`
            $.get(getUsername,
                function (res) {
                    profile_title.title = res
                }, 'text'
            )
        }
    })

    let user_profile = new Vue({
        el          : '#user_profile',
        data        : {
            user: null
        },
        beforeCreate: function () {
            let getUser = `${root}/getUser`
            $.get(getUser,
                function (res) {
                    user_profile.user = res
                }, 'json'
            )
        }
    })
})
