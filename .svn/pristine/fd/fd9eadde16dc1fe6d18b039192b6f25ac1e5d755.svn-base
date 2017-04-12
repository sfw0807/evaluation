(function ($) {
    $.IndexUser = {
        showEditWindow: function () {
            $('#IndexUser_EditContainer').modal('show');
        },
        postSave: function () {
            $('#IndexUser_EditForm').bootstrapValidator('validate');
        },
        closeWindow: function () {
            $('#IndexUser_EditContainer').modal('hide');
        },
        saveData: function () {
            $("#IndexUser_EditBtnSave").attr("disabled", true);
            $("#IndexUser_EditForm").ajaxDialogLoading();
            $("#IndexUser_EditForm").getUrlValues().requestType("post")
                .callBackFunction(function (res) {
                    $("#IndexUser_EditForm").ajaxLoadEnd();
                    if (res == "") {
                        $.ymessager.popupTip("保存成功", "success");
                        $.IndexUser.closeWindow();
                    }
                    else {
                        $.ymessager.alert('提示', res, 'info');
                    }
                    $("#IndexUser_EditBtnSave").removeAttr("disabled");
                }).doAjax("api/EduUserAPI/PostPwdSave");
        },
        loadValid: function () {
            $('#IndexUser_EditForm').bootstrapValidator({
                fields: {
                    oldPassword: {
                        validators: {
                            notEmpty: {
                                message: '必填'
                            }
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: '必填'
                            }
                        }
                    },
                    rePassword: {
                        validators: {
                            notEmpty: {
                                message: '必填'
                            },
                            identical: {
                                field: 'password',
                                message: '密码不一致'
                            }
                        }
                    }
                }
            });
        }
    };
    $(function () {
        $.IndexUser.loadValid();
        $('#IndexUser_EditContainer').modal({ backdrop: "static", keyboard: false, show: false })
        .on('hide.bs.modal', function () {
            $('#IndexUser_EditForm').bootstrapValidator('resetForm', true);
        });
        $('#IndexUser_EditForm').on('success.form.bv', function (e) {
            //保存数据
            $.IndexUser.saveData();
        });
    });
})(jQuery);