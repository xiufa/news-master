$(function(){
        var productId=getQueryString("productId");
        var productCategoryListUrl="/shopadmin/getproductcategorylist";
        var addProductUrl="/shopadmin/addproduct"
        var modifyProductUrl="/shopadmin/modifyproduct"
        var modifyInitUrl="/shopadmin/product?productId="+productId;

        var isEdit= productId ? true : false;
        var MAX_FILE_COUNT=6;
        if(isEdit){
            getProductIdInitInfo(productId);
        }else{
            getProductIdAllInitInfo();
        }

        function getProductIdInitInfo(productId){
             $.getJSON(modifyInitUrl,function(data){
                        if (data.success) {
                                var product = data.product;
                                $('#product-name').val(product.productName);
                                $('#product-desc').val(product.productDesc);
                                $('#priority').val(product.priority);
                                $('#point').val(product.point);
                                $('#normal-price').val(product.normalPrice);
                                $('#promotion-price').val(
                                        product.promotionPrice);
                                // 获取原本的商品类别以及该店铺的所有商品类别列表
                                var optionHtml = '';
                                var optionArr = data.productCategoryList;
                                var optionSelected = product.productCategory.productCategoryId;
                                // 生成前端的HTML商品类别列表，并默认选择编辑前的商品类别
                                optionArr
                                        .map(function(item, index) {
                                            var isSelect = optionSelected === item.productCategoryId ? 'selected'
                                                    : '';
                                            optionHtml += '<option data-value="'
                                                    + item.productCategoryId
                                                    + '"'
                                                    + isSelect
                                                    + '>'
                                                    + item.productCategoryName
                                                    + '</option>';
                                        });
                                $('#category').html(optionHtml);
                        }
                        })

        }


        function getProductIdAllInitInfo(){

            $.getJSON(productCategoryListUrl,function(data){
            if (data.success) {
                var tempHtml="";

                data.productCategoryList.map(function(item,index){
                     tempHtml=tempHtml+"<option data-id="+item.productCategoryId+">"+
                                            item.productCategoryName+"</option>"
                })

                $('#category').html(tempHtml);
                }
            })

        }

//        function  getProductIdInitInfo(productId){
//            $.getJSON()
//
//        }

        $('#submit').click(function(){
            var product={};
            if(isEdit){
                product.productId=productId;
            }

            product.productName=$('#product-name').val();
            product.productCategory = {
                           			productCategoryId : $('#category').find('option').not(function() {
                           				return !this.selected;
                           			}).data('id')
                           		};

            product.priority=$('#priority').val();
            product.normalPrice=$('#normal-price').val();
            product.promotionPrice=$('#promotion-price').val();
            product.productDesc=$('#product-desc').val();
            var productImg = $('#small-img')[0].files[0];

            var formData = new FormData();
            // 添加图片流进表单对象里
            formData.append('productImg', productImg);
            // 将shop json对象转成字符流保存至表单对象key为shopStr的的键值对里
            // console.log("aaaa");
            formData.append('productStr', JSON.stringify(product));
            // 获取表单里输入的验证码
            formData.append('verifyCodeActual',$('#j_captcha').val());
            //TODO 详情图片还未加入formDate

//            $('.detail-img').map(function(item,index){
//
//                if ($('.detail-img')[index].files.length > 0) {
//
//                    formData.append('productImg' + index,
//                            $('.detail-img')[index].files[0]);
//                }
//
//            })

            $('.detail-img').map(
            						function(index, item) {
            							// 判断该控件是否已选择了文件
            							if ($('.detail-img')[index].files.length > 0) {
            								// 将第i个文件流赋值给key为productImgi的表单键值对里
            								formData.append('productImg' + index,
            										$('.detail-img')[index].files[0]);
            							}
            						});

            $.ajax({
                url: (isEdit ? modifyProductUrl : addProductUrl),
                type: 'POST',
                data: formData,
                contentType : false,
                processData : false,
                cache : false,
                success: function(data){
                    if (data.success) {
                        $.toast('提交成功！');

                    } else {
                        $.toast('提交失败！' + data.errMsg);
                    }

                }


            })


        })


        $('.detail-img-div').on('change', '.detail-img:last-child', function() {
        		if ($('.detail-img').length < 6) {
        			$('#detail-img').append('<input type="file" class="detail-img">');
        		}
        	});

//        $('.detail-img-div').on('change','.detail-img:last-child',function(){
//            if($('.detail-img').length<MAX_FILE_COUNT){
//                $('#detail-img').append('<input type="file" class="detail-img">');
//            }
//        });

})