var home = function() {
    document.getElementById("home_image").innerHTML = "<img src='/resources/images/home_icon_active.png'>";
    document.getElementById("network_image").innerHTML = "<img src='/resources/images/network_icon_inactive.png'>";
    document.getElementById("jobs_image").innerHTML = "<img src='/resources/images/jobs_icon_inactive.png'>";
    document.getElementById("messages_image").innerHTML = "<img src='/resources/images/messages_icon_inactive.png'>";
    document.getElementById("spam_image").innerHTML = "<img src='/resources/images/spam_icon_inactive.png'>";
};

var network = function() {
    document.getElementById("home_image").innerHTML = "<img src='/resources/images/home_icon_inactive.png'>";
    document.getElementById("network_image").innerHTML = "<img src='/resources/images/network_icon_active.png'>";
    document.getElementById("jobs_image").innerHTML = "<img src='/resources/images/jobs_icon_inactive.png'>";
    document.getElementById("messages_image").innerHTML = "<img src='/resources/images/messages_icon_inactive.png'>";
    document.getElementById("spam_image").innerHTML = "<img src='/resources/images/spam_icon_inactive.png'>";
};

var jobs = function() {
    document.getElementById("home_image").innerHTML = "<img src='/resources/images/home_icon_inactive.png'>";
    document.getElementById("network_image").innerHTML = "<img src='/resources/images/network_icon_inactive.png'>";
    document.getElementById("jobs_image").innerHTML = "<img src='/resources/images/jobs_icon_active.png'>";
    document.getElementById("messages_image").innerHTML = "<img src='/resources/images/messages_icon_inactive.png'>";
    document.getElementById("spam_image").innerHTML = "<img src='/resources/images/spam_icon_inactive.png'>";
};

var messages = function() {
    document.getElementById("home_image").innerHTML = "<img src='/resources/images/home_icon_inactive.png'>";
    document.getElementById("network_image").innerHTML = "<img src='/resources/images/network_icon_inactive.png'>";
    document.getElementById("jobs_image").innerHTML = "<img src='/resources/images/jobs_icon_inactive.png'>";
    document.getElementById("messages_image").innerHTML = "<img src='/resources/images/messages_icon_active.png'>";
    document.getElementById("spam_image").innerHTML = "<img src='/resources/images/spam_icon_inactive.png'>";
};

var spam = function() {
    document.getElementById("home_image").innerHTML = "<img src='/resources/images/home_icon_inactive.png'>";
    document.getElementById("network_image").innerHTML = "<img src='/resources/images/network_icon_inactive.png'>";
    document.getElementById("jobs_image").innerHTML = "<img src='/resources/images/jobs_icon_inactive.png'>";
    document.getElementById("messages_image").innerHTML = "<img src='/resources/images/messages_icon_inactive.png'>";
    document.getElementById("spam_image").innerHTML = "<img src='/resources/images/spam_icon_active.png'>";
};