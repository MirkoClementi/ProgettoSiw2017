function myFunction() {
    var input, filter, ul, li, a, i;
    
    
    input = document.getElementById("nameFilter");
    filter = input.value.toUpperCase();
    li = document.getElementsByClassName("pics");
    for (i = 0; i < li.length; i++) {
    	if(li[i].id.toUpperCase().indexOf(filter)> -1 ){
    		li[i].style.display ="";
    	}else {
            li[i].style.display = "none";
        }
    }

   /* input = document.getElementById("name-filter"); //prendo input
    filter = input.value.toUpperCase();  //UPCASE
    ul = document.getElementsById("paintingContainer");  //prendo i figli di PC
    li = ul.getElementsByClassName("pics");   //filtro PC e prendo solo le immagini
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByClassName("pics")[0]; //prendo la singola immagine
        if (a.id.toUpperCase().indexOf(filter) > -1) { //il controllo va fatto su painting.artist.name
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }*/
}

