const http = new XMLHttpRequest()
var datas = {}

function clearField() {
  document.getElementById("inputZipcode").value = ("")
}

function alertP(dis) {
  document.getElementById("myAlert").style.display = dis;
}

function findCepList() {
  document.getElementById('tbody').innerHTML = ""
  var value = document.getElementById("inputZipcode").value

  var zipcode = value.replace(/\D/g, '')

  if (zipcode != "") {
    var validZipcode = /^[0-9]{8}$/

    if (validZipcode.test(zipcode)) {

      var url = `https://viacep.com.br/ws/${zipcode}/json/`

      http.open("GET", url, true)

      http.onreadystatechange = function () {
        if (http.readyState == 4 && http.status == 200) {
          datas.address = JSON.parse(http.response)
          datas.district = datas.address.localidade
          findFarmers()
        } else if (http.readyState == 4) {
          alertP("block")
        }
      }
      http.send()
    }
    else {
      clearField()
      alert("Formato de CEP inválido.")
    }
  }
  else {
    console.log("Escreva o seu CEP")
  }
}

function findFarmers() {

  var url = `http://localhost:8080/farmer/${datas.district}/`

  http.open("GET", url, true)

  http.onreadystatechange = function () {
    if (http.readyState == 4 && http.status == 200) {
      var cities = JSON.parse(http.response)
      if (cities.length === 0) {
        alertP("block")
      } else {
        datas.cities = cities
        findAll()

      }
    }
  }
  http.send()
}

function findAll() {

  var url = `http://localhost:8080/farmer/`

  http.open("GET", url, true)

  http.onreadystatechange = function () {
    if (http.readyState == 4 && http.status == 200) {
      datas.all = JSON.parse(http.response)
      calculateDistancy()
    }
  }
  http.send()
}

function compileProducts(email) {

  var arrayProducts = []
  var farmerFind = datas.all.find(element =>
    element.email === email
  )

  farmerFind.product.filter(element => {
    arrayProducts.push(element.name)
  })

  return arrayProducts.join()
}

function calculateDistancy() {

  var xhr = []

  for (let index = 0; index < datas.cities.length; index++) {

    xhr[index] = new XMLHttpRequest()

    const city = datas.cities[index];

    var url = `http://localhost:8080/farmer/distance/${datas.address.logradouro || ""} ${datas.address.localidade || ""}/${city[4] || ""} ${city[3] || ""}`

    xhr[index].open("GET", url, true)

    xhr[index].onreadystatechange = function () {
      if (xhr[index].readyState == 4 && xhr[index].status == 200) {
        var response = JSON.parse(xhr[index].response)
        var elements = response.rows[0].elements[0]

        tablePopulate(city[0], city[1], elements.distance.text,
          city[4], city[7], elements.duration.text, compileProducts(city[8]))

      } else if ((xhr[index].readyState == 4)) {
        alert("Erro interno do servidor")
      }
    }
    xhr[index].send()
  }
}

function tablePopulate(horta, phone, distance, street, neighborhood, time, products) {
  var html = `
    <th scope="row"> ${horta}
      <img src="assets/icon.webp" alt="" style="width: 1rem">
      <br>
      <small class="form-text text-muted">Tel: ${phone}</small>
    </th>
    <td>
    <small class="form-text text-muted">Rua: ${street}</small>
    <br>
    <small class="form-text text-muted">Bairro: ${neighborhood}</small>
    </td>
    <td>
    <small class="form-text text-muted">${products}</small>
    </td>
    <td>${distance}
    <br>
    <small class="form-text text-muted">Aprox: ${time}</small>
    </td>
    `
  var table = document.getElementById('tbody')
  var row = document.createElement("tr")
  row.innerHTML = html
  table.append(row)
}
