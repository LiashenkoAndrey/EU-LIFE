
// editor configuration
tinymce.init({

  selector: 'textarea.textarea',

  plugins: [
    'advlist autolink lists link image charmap preview hr anchor pagebreak',
    'searchreplace wordcount visualblocks visualchars code',
    'insertdatetime media nonbreaking table  directionality',
    'emoticons template paste textcolor textpattern codesample'
  ],

  toolbar: 'undo redo |  bold italic sizeselect  fontsizeselect | hr alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | codesample | link | addimg forecolor backcolor emoticons',

  // adding the new button for image upload
  setup: (editor) => {
    editor.ui.registry.addButton('addimg', {
      icon: 'browse',
      onAction: (_) => document.getElementById('uploadImage').click()
    });
  }
});

function getContent() {
  console.log(tinymce.activeEditor.getContent())
}

// adding event listener for invisible form
input = document.getElementById('uploadImage');
input.addEventListener("change", () => {
  doUploadImage()
})


/**
 * Sends an image to the server and returns an url an image
 * @returns {Promise<void>}
 */
async function doUploadImage() {
  // invisible form
  let input = document.getElementById("uploadImage")

  let formData = new FormData();
  formData.append("file", input.files[0])

  await fetch("/image", {
    method: "POST",
    body: formData
  }).then(response => response.text().then(result => {
    tinymce.activeEditor.setContent(tinymce.activeEditor.getContent() + '<img src="/image/' + result + '" alt="image" class="img-fluid" data-action="zoom">')
  }))
}