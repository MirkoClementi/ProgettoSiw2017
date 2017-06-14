function myFunction() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("name-filter");
    filter = input.value.toUpperCase();
    ul = document.getElementById("paintings");
    li = ul.getElementsByClassName("pics");
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByClassName("pics")[0];
        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}

