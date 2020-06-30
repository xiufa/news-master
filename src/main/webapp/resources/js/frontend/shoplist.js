$(function(){

    var loading=false;
    var pageSize=3;
    var maxItems=100;

    var listUrl='/frontend/listshops';
    var searchDivUrl = '/frontend/listshopspageinfo';

    var pageNum=1;
    var parentId=getQueryString('parentId');

    var selectedParent =false;
    if(parentId){
        selectedParent=true;
    }

    var areaId=null;
    var shopCategoryId=null;
    var shopName='';

    getSearchDivData();

    addItems(pageSize,pageNum);

    function getSearchDivData(){
         var url=searchDivUrl+'?parentId='+parentId;
         $.getJSON(url,function(data){
            if(data.success){
                var shopCategoryList=data.shopCategoryList;
                var areaList=data.areaList;
                var shopList=data.shopList;
                var html='';
                html += '<a href="#" class="button" data-category-id=""> 全部类别  </a>';
								// 遍历店铺类别列表，拼接出a标签集
                shopCategoryList
                        .map(function(item, index) {
                            html += '<a href="#" class="button" data-category-id='
                                    + item.shopCategoryId
                                    + '>'
                                    + item.shopCategoryName
                                    + '</a>';
                        });

                $('#shoplist-search-div').html(html);

                var selectOptions = '<option value="">全部街道</option>';
                								// 获取后台返回过来的区域信息列表
                								var areaList = data.areaList;
                								// 遍历区域信息列表，拼接出option标签集
                								areaList.map(function(item, index) {
                									selectOptions += '<option value="'
                											+ item.areaId + '">'
                											+ item.areaName + '</option>';
                								});
                								// 将标签集添加进area列表里
                								$('#area-search').html(selectOptions);

            }

         });

    }



    function addItems(pageSize,pageIndex){
//        var url=listUrl+'?'+'pageIndex='+pageIndex+'&pageSize='+pageSize+'&parentId='+parentId
//        +'&areaId='+areaId+'&shopCategoryId='+shopCategoryId+'&shopName='+shopName;
        var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
        				+ pageSize + '&parentId=' + parentId + '&areaId=' + areaId
        				+ '&shopCategoryId=' + shopCategoryId + '&shopName=' + shopName;


        loading=true;

        $.getJSON(url,function(data){

            if(data.success){
                maxItems=data.count;
                var shopList=data.shopList;
                var html='';

                shopList.map(function(item,index){
                    html += '' + '<div class="card" data-shop-id="'
                            + item.shopId + '">' + '<div class="card-header">'
                            + item.shopName + '</div>'
                            + '<div class="card-content">'
                            + '<div class="list-block media-list">' + '<ul>'
                            + '<li class="item-content">'
                            + '<div class="item-media">' + '<img src="'
                            + item.shopImg + '" width="44">' + '</div>'
                            + '<div class="item-inner">'
                            + '<div class="item-subtitle">' + item.shopDesc
                            + '</div>' + '</div>' + '</li>' + '</ul>'
                            + '</div>' + '</div>' + '<div class="card-footer">'
                            + '<p class="color-gray">'

//                            + new Date(item.lastEditTime).Format("yyyy-MM-dd")
                            + '更新</p>' + '<span>点击查看</span>' + '</div>'
                            + '</div>';

                });

                $('.list-div').append(html);
                var total=$('.list-div .card').length;
                if(total >= maxItems){
                    $('.infinite-scroll-preloader').hide();
                }else{
                    $('.infinite-scroll-preloader').show();
                }

                pageNum+=1;
                loading=false;

                $.refreshScroller();
            }

        })


    }

    $(document).on('infinite', '.infinite-scroll-bottom', function() {
    		if (loading)
    			return;
    		addItems(pageSize, pageNum);
    	});


    $('.shop-list').on('click', '.card', function(e) {
    		var shopId = e.currentTarget.dataset.shopId;
    		window.location.href = '/frontend/shopdetail?shopId=' + shopId;
    	});


    $('#shoplist-search-div').on('click',function(e){

        if(parentId && selectedParent){
            shopCategoryId=e.target.dataset.categoryId;

            if ($(e.target).hasClass('button-fill')) {
            						$(e.target).removeClass('button-fill');
            						shopCategoryId = '';
            } else {
                $(e.target).addClass('button-fill').siblings()
                        .removeClass('button-fill');
            }

            $('.list-div').empty();
            pageNum=1;
            addItems(pageSize,pageNum);

        }else{
            parentId = e.target.dataset.categoryId;
            					if ($(e.target).hasClass('button-fill')) {
            						$(e.target).removeClass('button-fill');
            						parentId = '';
            					} else {
            						$(e.target).addClass('button-fill').siblings()
            								.removeClass('button-fill');
            					}
            					// 由于查询条件改变，清空店铺列表再进行查询
            					$('.list-div').empty();
            					// 重置页码
            					pageNum = 1;
            					addItems(pageSize, pageNum);

        }

    })




    $('#search').on('change',function(e){
        shopName=e.target.value;
        $('.list-div').empty();
        pageNum=1;
        addItems(pageSize,pageNum);
    })

    $('#area-search').on('change',function(){

        areaId = $('#area-search').val();
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    })

    $('#me').click(function() {
    		$.openPanel('#panel-right-demo');
    	});

    	// 初始化页面
    	$.init();


     // 点击商品的卡片进入该商品的详情页
        $('.list-div').on(
                'click',
                '.card',
                function(e) {
                    var tempShopId = e.currentTarget.dataset.shopId;
                    window.location.href = '/frontend/shopdetail?shopId='
                            + tempShopId;
                });



})