
function saveOrUpdateRating(comment_id, isLike) {
    let url= "/rating?comment_id=" + comment_id + "&isLike=" + isLike;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            console.log("request")
            return xmlhttp.responseText;
        }
    }
    xmlhttp.open(
        "POST",
        url,
        true);
    xmlhttp.send();
}

