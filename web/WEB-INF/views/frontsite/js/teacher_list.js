$(function () {
	if (screen.width < 1024) {
		$(document.body).css('width','975pt');
	} 
});
//最新推荐好老师
    var r = 0, e = 0,count;
    $(document).ready(function(){
        count=$('.recoBox .reco').length;
        $(".recoBox .reco:not(:first)").hide();
        $(".recoNum span").click(function() {
			var i = $(this).index();
			e = i;
			if (i >= count) return;
			$(".recoBox .reco").filter(":visible").fadeOut(200).parent().children().eq(i+1).fadeIn(500);
			$(this).toggleClass("cur");
			$(this).siblings().removeAttr("class");
			});
			r = setInterval("showAuto()", 3000);
			$(".recoBox").hover(function(){clearInterval(r)}, function(){r = setInterval("showAuto()", 3000);
		});
    })
    function showAuto(){
        e = e >=(count -1) ?0 : ++e;
        $(".recoNum span").eq(e).trigger('click');
    }



//最新浏览好老师
    var s = 0, c = 0,count1;
    $(document).ready(function(){
        count1=$('.scanBox .scan').length;
        $(".scanBox .scan:not(:first)").hide();
        $(".scanNum span").click(function() {
            var j = $(this).index();
            c = j;
            if (j >= count1) return;
            $(".scanBox .scan").filter(":visible").fadeOut(200).parent().children().eq(j+1).fadeIn(500);
            $(this).toggleClass("cur");
            $(this).siblings().removeAttr("class");
        });
        s = setInterval("showAuto1()", 3000);
        $(".scanBox").hover(function(){clearInterval(s)}, function(){s = setInterval("showAuto1()", 3000);});
    })
    function showAuto1()
    {
        c = c >=(count1 -1) ?0 : ++c;
        $(".scanNum span").eq(c).trigger('click');
    }

//最新入驻好老师
    var x = 0, y = 0,count2;
    $(document).ready(function(){
        count2=$('.joinBox .join').length;
        $(".joinBox .join:not(:first)").hide();
        $(".joinNum span").click(function() {
            var k = $(this).index();
            y = k;
            if (k >= count2) return;
            $(".joinBox .join").filter(":visible").fadeOut(200).parent().children().eq(k+1).fadeIn(500);
            $(this).toggleClass("cur");
            $(this).siblings().removeAttr("class");
        });
        x = setInterval("showAuto2()", 3000);
        $(".joinBox").hover(function(){clearInterval(x)}, function(){x = setInterval("showAuto2()", 3000);});
    })
    function showAuto2()
    {
        y = y >=(count2 -1) ?0 : ++y;
        $(".joinNum span").eq(y).trigger('click');
    }