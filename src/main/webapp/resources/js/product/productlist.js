$(function(){
     var getProductListUrl="/shopadmin/getproductlist?pageIndex=0&pageSize=100";
     var statusUrl = '/shopadmin/modifyproduct';
        getList();

        function getList(){
                 $.getJSON(getProductListUrl,function(data){
                     if (data.success) {
                        handleList(data);

                     }
                })
        }


        function handleList(data){
            var html="";

             data.productList.map(function(item,index){

                                var op;
                                var contraryStatus = 0;
                                if(item.enableStatus==0){
                                    contraryStatus=1;
                                    op="上架";
                                }else{
                                    contraryStatus=0;
                                    op="下架";
                                }

                               html +=  '<div class="row row-product">'
                                + '<div class="col-33 product-name">'
                                + item.productName
                                + '</div>'
                                + '<div class="col-33">'
                                + item.priority
                                + '</div>'

                                + '<div class="col-33"><a href="#" class="edit" data-id="'
                                + item.productId
                                +'" data-status="'
                                + item.enableStatus
                                + '">编辑</a>'


                                + '<a href="#" class="status" data-id="'
                                + item.productId
                                + '" data-status="'
                                + contraryStatus
                                + '">'+op+'</a>'



                                + '<a href="#" class="preview" data-id="'
                                + item.productId
                                + '" data-status"'
                                + item.enableStatus
                                + '">预览</a>'


                               +'</div>'
                                + '</div>';


                                $('.product-wrap').html(html);

                        });



        }

        $('.product-wrap').on(
                    'click', 'a' ,function(e){
                        var target=$(e.currentTarget);
                        if(target.hasClass('edit')){
                            window.location.href = '/shopadmin/productoperation?productId='
                            									+ e.currentTarget.dataset.id;
                        }else if(target.hasClass('status')){
                            changeItemStatus(e.currentTarget.dataset.id,
                            									e.currentTarget.dataset.status);

                        }else if(target.hasClass('preview')){
                            window.location.href = '/frontend/productdetail?productId='
                            									+ e.currentTarget.dataset.id;
                        }


                    }

        )

        function changeItemStatus(id,enableStatus){
            var product = {};
            		product.productId = id;
            		product.enableStatus = enableStatus;
            		$.confirm('确定修改?', function() {

            			$.ajax({
            				url : statusUrl,
            				type : 'POST',
            				data : {
            					productStr : JSON.stringify(product),
            					statusChange : true
            				},
            				dataType : 'json',
            				success : function(data) {
            					if (data.success) {
            						$.toast('操作成功！');
            						getList();
            					} else {
            						$.toast('操作失败！');
            					}
            				}
            			});
            		});

        }


})