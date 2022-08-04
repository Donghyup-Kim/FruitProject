<%@ page import="kopo.poly.dto.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="kopo.poly.dto.BoardDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="kopo.poly.dto.ProductDTO" %>
<%

    String sell_id = CmmUtil.nvl((String) session.getAttribute("SS_SELL_ID"));

    ProductDTO pDTO = (ProductDTO) request.getAttribute("pDTO");

    if (pDTO==null){

        pDTO = new ProductDTO();
    }


%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>

<script src="/js/jquery-3.6.0.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" crossorigin="anonymous">
<title>Board</title>
<script type="text/javascript">
    $(document).ready(function(){
        var fileTarget = $('.filebox .upload-hidden');

        fileTarget.on('change', function(){
            if(window.FileReader){
                // 파일명 추출
                var filename = $(this)[0].files[0].name;
            }

            else {
                // Old IE 파일명 추출
                var filename = $(this).val().split('/').pop().split('\\').pop();
            };

            $(this).siblings('.upload-name').val(filename);
        });

        //preview image
        var imgTarget = $('.preview-image .upload-hidden');

        imgTarget.on('change', function(){
            var parent = $(this).parent();
            parent.children('.upload-display').remove();

            if(window.FileReader){
                //image 파일만
                if (!$(this)[0].files[0].type.match(/image\//)) return;

                var reader = new FileReader();
                reader.onload = function(e){
                    var src = e.target.result;
                    parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img src="'+src+'" class="upload-thumb"></div></div>');
                }
                reader.readAsDataURL($(this)[0].files[0]);
            }

            else {
                $(this)[0].select();
                $(this)[0].blur();
                var imgSrc = document.selection.createRange().text;
                parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img class="upload-thumb"></div></div>');

                var img = $(this).siblings('.upload-display').find('img');
                img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")";
            }
        });
    });

    //파일 업로드
    function file_upload(input) {
        let title = document.getElementById("pro_title").value;
        let pro_content = document.getElementById("pro_content").value;
        let pro_price = document.getElementById("pro_price").value;
        let pro_type = document.getElementById("pro_type").value;
        let fileUpload = document.getElementById("fileUpload").value;

        if (fileUpload != null || fileUpload != "") {
            console.log("파일 존재하여 파일 포함한 그룹내용 인서트 시작!");
            var form = $('#form')[0];
            let data = new FormData(form);

            var object = {};
            data.forEach((value, key) => object[key] = value);
            var json = JSON.stringify(object);


            $.ajax({
                url: '/img/imgUpload',
                type: "POST",
                enctype: 'multipart/form-data',
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {
                    if (data == 1) {
                        console.log("등록 성공!");
                        swal('등록 성공!', "등록 성공하였습니다.", 'success');
                        location.href = "/Sellpage";
                    } else if (data == 2) {
                        console.log("등록 실패!");
                        swal('등록 실패!', "상품 이미 존재합니다.", 'error');
                    } else {
                        console.log("등록 실패!");
                        swal('등록 실패!', "오류로 인해 등록이 실패하였습니다.", 'warning');
                    }
                },
                error: function () {
                    console.log("아작스 에러");
                }
            });
        }
    }



</script>
<style>
    body {margin: 10px;}
    .where {
        display: block;
        margin: 25px 15px;
        font-size: 11px;
        color: #000;
        text-decoration: none;
        font-family: verdana;
        font-style: italic;
    }

    .filebox input[type="file"] {
        position: absolute;
        width: 1px;
        height: 1px;
        padding: 0;
        margin: -1px;
        overflow: hidden;
        clip:rect(0,0,0,0);
        border: 0;
    }

    .filebox label {
        display: inline-block;
        padding: .5em .75em;
        color: #999;
        font-size: inherit;
        line-height: normal;
        vertical-align: middle;
        background-color: #fdfdfd;
        cursor: pointer;
        border: 1px solid #ebebeb;
        border-bottom-color: #e2e2e2;
        border-radius: .25em;
    }

    /* named upload */
    .filebox .upload-name {
        display: inline-block;
        padding: .5em .75em;
        font-size: inherit;
        font-family: inherit;
        line-height: normal;
        vertical-align: middle;
        background-color: #f5f5f5;
        border: 1px solid #ebebeb;
        border-bottom-color: #e2e2e2;
        border-radius: .25em;
        -webkit-appearance: none; /* 네이티브 외형 감추기 */
        -moz-appearance: none;
        appearance: none;
    }

    /* imaged preview */
    .filebox .upload-display {
        margin-bottom: 5px;
    }

    @media(min-width: 768px) {
        .filebox .upload-display {
            display: inline-block;
            margin-right: 5px;
            margin-bottom: 0;
        }
    }

    .filebox .upload-thumb-wrap {
        display: inline-block;
        width: 54px;
        padding: 2px;
        vertical-align: middle;
        border: 1px solid #ddd;
        border-radius: 5px;
        background-color: #fff;
    }

    .filebox .upload-display img {
        display: block;
        max-width: 100%;
        width: 100% \9;
        height: auto;
    }

    .filebox.bs3-primary label {
        color: #fff;
        background-color: #337ab7;
        border-color: #2e6da4;
    }
    .container1 {
        text-align: center;
    }
    #borderDemo {
        border: 6px outset #005BA4;
    }


</style></head><body>
<article>
    <div id="borderDemo">
        <div class="container1" role="main"><h2>상품 수정</h2></div>

        </br>
        <form name="form" id="form" role="form" action="/pro_update">
            <div class="mb-3">
                <label for="pro_title">제목</label>
                <input type="text" class="form-control" name="pro_title" id="pro_title" placeholder="제목을 입력해 주세요">
            </div>
            <div class="mb-3">
                <label for="pro_content">내용</label>
                <textarea class="form-control" rows="5" name="pro_content" id="pro_content" placeholder="내용을 입력해 주세요"></textarea>
            </div>
            <div class="mb-3">
                <label for="pro_price">가격</label>
                <input type="text" class="form-control" name="pro_price" id="pro_price">
            </div>
            </br>

            <!-- 목록으로 조회하게 하기 -->
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="pro_type" id="pro_type1" value="Fruit" style="width:30px; height:30px; font-size:15px">
                <label class="form-check-label" for="pro_type">Fruit</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="pro_type" id="pro_type" value="Vegetable" style="width:30px; height:30px; font-size:15px">
                <label class="form-check-label" for="pro_type">Vegetable</label>
            </div>
            </br>
            </br>

            <!-- 이미지 파일 업로드 -->
            <div class="filebox bs3-primary preview-image">

                <div class="text">
                    <button type="submit" class="btn btn-sm btn-primary">상품등록</button>
                </div>
            </div>
        </form>
        </br>
        <div>

            <button type="button" class="btn btn-sm btn-primary" id="btnList" onclick="location.href='/index'">목록</button>
        </div>

    </div>
    </div>
</article>
</body></html>
