(window.onresize = function () {
    let win_height = $(window).height();
    let win_width = $(window).width();
    let tailoring = $(".tailoring-content");
    if (win_width <= 768){
        tailoring.css({
            "top": (win_height - tailoring.outerHeight())/2,
            "left": 0
        });
    }else{
        tailoring.css({
            "top": (win_height - tailoring.outerHeight())/2,
            "left": (win_width - tailoring.outerWidth())/2
        });
    }
})();

//弹出图片裁剪框
$("#replaceImg").on("click",function () {
    $(".tailoring-container").toggle();
});
//图像上传
function selectImg(file) {
    if (!file.files || !file.files[0]){
        return;
    }
    let reader = new FileReader();
    reader.onload = function (evt) {
        let replaceSrc = evt.target.result;
        //更换cropper的图片
        $('#tailoringImg').cropper('replace', replaceSrc,false);//默认false，适应高度，不失真
    };
    reader.readAsDataURL(file.files[0]);
}
//cropper图片裁剪
$('#tailoringImg').cropper({
    aspectRatio: 1,//默认比例
    preview: '.previewImg',//预览视图
    guides: false,  //裁剪框的虚线(九宫格)
    autoCropArea: 0.5,  //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
    dragCrop: true,  //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
    movable: true,  //是否允许移动剪裁框
    resizable: true,  //是否允许改变裁剪框的大小
    zoomable: true,  //是否允许缩放图片大小
    mouseWheelZoom: true,  //是否允许通过鼠标滚轮来缩放图片
    touchDragZoom: true,  //是否允许通过触摸移动来缩放图片
    rotatable: true,  //是否允许旋转图片
    crop: function(e) {
        // 输出结果数据裁剪图像。
    }
});
//旋转
$(".cropper-rotate-btn").on("click",function () {
    $('#tailoringImg').cropper("rotate", 45);
});
//复位
$(".cropper-reset-btn").on("click",function () {
    $('#tailoringImg').cropper("reset");
});
//换向
let flagX = true;
$(".cropper-scaleX-btn").on("click",function () {
    if(flagX){
        $('#tailoringImg').cropper("scaleX", -1);
        flagX = false;
    }else{
        $('#tailoringImg').cropper("scaleX", 1);
        flagX = true;
    }
});

//裁剪后的处理
$("#sureCut").on("click",function () {
    let tailoringImg = $("#tailoringImg");
    if (tailoringImg.attr("src") == null ){
        return false;
    }else{
        let cas = tailoringImg.cropper('getCroppedCanvas');//获取被裁剪后的canvas
        let base64url = cas.toDataURL('image/png'); //转换为base64地址形式
        $("#finalImg").prop("src",base64url);//显示为图片的形式

        //关闭裁剪框
        closeTailor();
    }
});
//关闭裁剪框
function closeTailor() {
    $(".tailoring-container").toggle();
}