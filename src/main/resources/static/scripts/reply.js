let add_comm = document.getElementsByClassName("reply");
let new_comm_body = document.getElementsByClassName("comment_form");
let arr_comm_body = Array.from(new_comm_body);
let arr_button = Array.from(add_comm);

arr_comm_body.forEach(elem => {elem.style.display = 'none'});

for (let i = 0; i< arr_button.length; i++) {
    arr_button[i].addEventListener("click", function () {
        arr_button[i].style.display = 'none';
        arr_comm_body[i].style.display = "flex";
    })
}




