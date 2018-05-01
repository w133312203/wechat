$('.contacts-close').click(function () {
    swal({
        title: "是否确定删除",
        text: "删除后将无法恢复，请谨慎操作！",
        //type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "删除",
        closeOnConfirm: false
    }, function () {
        swal("删除成功！", "您已经永久删除了这条信息。");
    });
});