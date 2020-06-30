$(function(){
    var shopId=getQueryString("shopId");

    var url="/shopadmin/getshopmanagementinfo?shopId="+shopId;

    $.getJSON(url,function(data){

        if(data.redirect){
            window.location.href=data.url;
        }else{
            if(data.currentShop.shopId!=undefined && data.currentShop.shopId!=null){
                shopId=data.currentShop.shopId;
            }
            $('#shopInfo').attr('href','/shopadmin/shopoperation?shopId='+shopId);

            $('#productCategoryInfo').attr('href','/shopadmin/productcategoryoperation?shopId='+shopId);

        }

    })


})