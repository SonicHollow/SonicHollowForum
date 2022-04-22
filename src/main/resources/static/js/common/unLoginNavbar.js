var a = location.href.split('/');
$("#"+a[a.length - 1])[0].setAttribute("class","active");