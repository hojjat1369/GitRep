document.getElementById("foot01").innerHTML =
"<p>&copy;  " + new Date().getFullYear() + " Ubique. All rights reserved.</p>";

document.getElementById("nav01").innerHTML =
	"<ul id='menu'>" +
	"<li><a href='index'>Home</a></li>" +
	"<li><a href='devices'>Device</a></li>" +
	"<li><a href='about'>About</a></li>" +
	"<li><a href='newdevice'>New Device</a></li>" +
	"</ul>";
//
//function setActiveTab(tabId){
//	var root = document.getElementsById(tabId)
//	root.className += ' active';
//}