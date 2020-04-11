/*public js xianhuachneg.com*/
/*Tab Search style*/
function selectsearch(theA,word){
	obj=document.getElementById("selectsearch").getElementsByTagName("a");
	for(var i=0;i< obj.length;i++ ){
		obj[i].className='';
	}
	theA.className='choose';
	//修改搜索input框的id
	if(word=='author'){
		var obj = document.getElementsByClassName("searchbox");
		obj[0].setAttribute("id","search-inputAuthor");
	}else if(word=='publisher'){
		var obj = document.getElementsByClassName("searchbox");
		obj[0].setAttribute("id","search-inputPublisher");
	}else if(word=='title'){
		var obj = document.getElementsByClassName("searchbox");
		obj[0].setAttribute("id","search-inputTitle");
	}else if(word=='year'){
		var obj = document.getElementsByClassName("searchbox");
		obj[0].setAttribute("id","search-inputYear");
	}
	else if(word=='combinatorialSearch'){
		var obj = document.getElementsByClassName("searchbox");
		obj[0].setAttribute("id","search-inputCombinatorialSearch");
	}else if(word=='keywords'){
		var obj = document.getElementsByClassName("searchbox");
		obj[0].setAttribute("id","search-inputKeywords");
	}else if(word=='conference'){
		var obj = document.getElementsByClassName("searchbox");
		obj[0].setAttribute("id","search-inputConference");
	}
}

//INDEX TAB LIST
window.onload = function ()
{
	var oLi = (document.getElementById("Indextab")).getElementsByTagName("li");
	var oUl =(document.getElementById("Indexcontent")).getElementsByTagName("ul");
	for(var i = 0; i < oLi.length; i++)
	{
		oLi[i].index = i;
		oLi[i].onclick = function ()
		{
			for(var n = 0; n < oLi.length; n++) oLi[n].className="";
			this.className = "current";
			for(var n = 0; n < oUl.length; n++) oUl[n].style.display = "none";
			oUl[this.index].style.display = "block"
		}	
	}
}
/*change radio background color*/
function changeColor(arg){  
var rdCount = document.getElementsByName("rdColor").length;  
for(i=1;i<=rdCount;i++){  
document.getElementById("style"+i).style.fontWeight = "normal"; 
document.getElementById("style"+i).style.backgroundColor = "";
document.getElementById("style"+i).style.boxShadow = "none"; 
document.getElementById("style"+i).style.border = "none";
}  
document.getElementById("style"+arg).style.backgroundColor = "#fff5cc";
document.getElementById("style"+arg).style.fontWeight = "bold";
document.getElementById("style"+arg).style.boxShadow = "3px 2px 10px #dedede";
document.getElementById("style"+arg).style.border = "1px #ffe580 solid";
}
