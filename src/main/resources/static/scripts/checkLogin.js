
async function checkLogin() {
    document.getElementById("checking").innerHTML = "<span class=\"loader\" style=\"display: inline-block\" ></span>"
    let login = document.getElementById("login").value
    console.log(login)



    await fetch("/user/check/" + login, {
        method : "GET"
    }).then(response => response.text().then(result => {
        console.log(result)
        setTimeout(function () {
            if (result === 'false') {
                document.getElementById("checking").innerHTML = "<img style='display: inline-block; position: relative; bottom: 7px' src='/img/ok.svg'>"
            } else {
                let wrapper = document.getElementById("checking")
                wrapper.innerHTML = "<img style='display: inline-block;' src='/img/error.svg'><span> Please try an another login</span>"
            }
        }, 1000)

    }))


}

