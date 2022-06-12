const general_url = './api/';

function loadResultGif() {
    let code = $("#codes_select").val();
    $.ajax({
        url: general_url + 'get-giph-by-code/' + code,
        method: 'GET',
        dataType: "text",
        complete: function (gifurl) {
            let img = document.createElement("img");
            img.src = gifurl.responseText;
            let out = document.querySelector("#out");
            out.innerHTML = '';
            out.insertAdjacentElement("afterbegin", img);
        }
    })
}

function loadForSelect() {
    $.ajax({
        url: general_url + 'get-codes',
        method: 'GET',
        complete: function (data) {
            let codesList = JSON.parse(data.responseText);
            let select = document.querySelector("#codes_select");
            select.innerHTML = '';
            for (let i = 0; i < codesList.length; i++) {
                let option = document.createElement("option");
                option.value = codesList[i];
                option.text = codesList[i];
                select.insertAdjacentElement("beforeend", option);
            }
        }
    })
}
