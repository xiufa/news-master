
$(function(){

    getlist();
    function getlist(){

        $.ajax({
            url:"/shopadmin/getshoplist",
            type:"get",
            dataType:"json",
            success:function(data){
                if(data.success){
                    handleList(data);
                    handleUser(data);
                }
            }
        })
    }

    function handleUser(data){
        $('#user-name').text(data.user.name);

    }

    function handleList(data){
         var html="";


         data.shopList.map(function(item,index){
         html += '<div class="row row-shop"><div class="col-40">'
         					+ item.shopName + '</div><div class="col-40">'
         					+ shopStatus(item.enableStatus)
         					+ '</div><div class="col-20">'
         					+ goShop(item.enableStatus, item.shopId) + '</div></div>';
      });

        console.log(html);
        $('.shop-wrap').html(html);
    }

    function shopStatus(data){
        if(data==0){
            return '审核中';
        }else if(data==1){
            return '审核通过';
        }else if(data==-1){
            return '非法店铺';
        }
    }

    function goShop(status,shopId){
        if(status==1){
        //TODO 可能有问题: url地址里有个/shop/shopmanage
            return '<a href = "/shopadmin/shopmanagement?shopId='+shopId+'">进入</a>';
        }else{
            return '';
        }

    }


    $('#log-out').click(function() {
        $.ajax({
                    url:"/local/logout",
                    type:"get",
                    dataType:"json",
                    success:function(data){
                        if(data.success){
                           window.open ('/local/login','_self')
                        }
                    }
                })
	});
})




