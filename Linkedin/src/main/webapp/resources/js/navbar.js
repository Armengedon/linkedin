var active = "home";
var getTab = function() {
    return active;
};
var setTab = function(name) {
    this.active = name;
};
var initialize = function() {
    var tab = getTab();
    
    if (tab == "home") {
        document.getElementById("home_image").innerHTML = "<img src='/resources/images/home_icon_active.png'>";
        document.getElementById("network_image").innerHTML = "<img src='/resources/images/network_icon_inactive.png'>";
        document.getElementById("jobs_image").innerHTML = "<img src='/resources/images/jobs_icon_inactive.png'>";
        document.getElementById("messages_image").innerHTML = "<img src='/resources/images/messages_icon_inactive.png'>";
        document.getElementById("spam_image").innerHTML = "<img src='/resources/images/spam_icon_inactive.png'>";
    }
    else if (tab == "network") {
        document.getElementById("home_image").innerHTML = "<img src='/resources/images/home_icon_inactive.png'>";
        document.getElementById("network_image").innerHTML = "<img src='/resources/images/network_icon_active.png'>";
        document.getElementById("jobs_image").innerHTML = "<img src='/resources/images/jobs_icon_active.png'>";
        document.getElementById("messages_image").innerHTML = "<img src='/resources/images/messages_icon_active.png'>";
        document.getElementById("spam_image").innerHTML = "<img src='/resources/images/spam_icon_active.png'>";
    }
    else if (tab == "jobs") {
        document.getElementById("home_image").innerHTML = "<img src='/resources/images/home_icon_inactive.png'>";
        document.getElementById("network_image").innerHTML = "<img src='/resources/images/network_icon_inactive.png'>";
        document.getElementById("jobs_image").innerHTML = "<img src='/resources/images/jobs_icon_active.png'>";
        document.getElementById("messages_image").innerHTML = "<img src='/resources/images/messages_icon_inactive.png'>";
        document.getElementById("spam_image").innerHTML = "<img src='/resources/images/spam_icon_inactive.png'>";
    }
    else if (tab == "messages") {
        document.getElementById("home_image").innerHTML = "<img src='/resources/images/home_icon_inactive.png'>";
        document.getElementById("network_image").innerHTML = "<img src='/resources/images/network_icon_inactive.png'>";
        document.getElementById("jobs_image").innerHTML = "<img src='/resources/images/jobs_icon_inactive.png'>";
        document.getElementById("messages_image").innerHTML = "<img src='/resources/images/messages_icon_active.png'>";
        document.getElementById("spam_image").innerHTML = "<img src='/resources/images/spam_icon_inactive.png'>";
    }
    else if (tab == "spam") {
        document.getElementById("home_image").innerHTML = "<img src='/resources/images/home_icon_inactive.png'>";
        document.getElementById("network_image").innerHTML = "<img src='/resources/images/network_icon_inactive.png'>";
        document.getElementById("jobs_image").innerHTML = "<img src='/resources/images/jobs_icon_inactive.png'>";
        document.getElementById("messages_image").innerHTML = "<img src='/resources/images/messages_icon_inactive.png'>";
        document.getElementById("spam_image").innerHTML = "<img src='/resources/images/spam_icon_active.png'>";
    }
    else {
        document.getElementById("home_image").innerHTML = "<img src='/resources/images/home_icon_inactive.png'>";
        document.getElementById("network_image").innerHTML = "<img src='/resources/images/network_icon_inactive.png'>";
        document.getElementById("jobs_image").innerHTML = "<img src='/resources/images/jobs_icon_inactive.png'>";
        document.getElementById("messages_image").innerHTML = "<img src='/resources/images/messages_icon_inactive.png'>";
        document.getElementById("spam_image").innerHTML = "<img src='/resources/images/spam_icon_inactive.png'>";
    }
};