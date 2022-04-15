function toggleUserInfoCollapseUsername() {
    var elem = document.getElementById("userInfoCollapseUsername");
    if (elem.getAttribute("class") === "collapse") {
        elem.setAttribute("class", "collapsing");
        elem.animate({height:'100%'}, 1000);
        elem.animate({height:'100%'}, 0);
        // elem.setAttribute("class", "collapse show");
    }
    else if (elem.getAttribute("class") === "collapsing")
        elem.setAttribute("class", "collapse show");
    else if (elem.getAttribute("class") === "collapse show") {
        elem.animate({height:'0px'}, 1000);
        // elem.setAttribute("class", "collapse");
    }
}
