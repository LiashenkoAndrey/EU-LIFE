

async function doUploadImage() {
    let input = document.getElementById("uploadImage")
    let formData = new FormData();
    formData.append("file", input.files[0])
    await fetch("/image", {
        method: "POST",
        body: formData
    }).then(response => response.text().then(result => console.log(result)))
}