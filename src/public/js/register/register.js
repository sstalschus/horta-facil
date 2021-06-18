const xhttp = new XMLHttpRequest()

var data = {
  products: []
}

function updateFields(data) {
  document.getElementById("street").value = (data.logradouro)
  document.getElementById("neighborhood").value = (data.bairro)
  document.getElementById("district").value = (data.localidade)
}

function clearFields() {
  document.getElementById("zipcode").value = ("")
  document.getElementById("street").value = ("...")
  document.getElementById("neighborhood").value = ("...")
  document.getElementById("district").value = ("...")
}

function clearAllFields() {
  document.getElementById("zipcode").value = ("")
  document.getElementById("street").value = ("...")
  document.getElementById("neighborhood").value = ("...")
  document.getElementById("district").value = ("...")
  document.getElementById("name").value = ("")
  document.getElementById("email").value = ("")
  document.getElementById("phone").value = ("")
  document.getElementById("product").value = ("")
  document.getElementById("productsSmall").textContent = "Produtos adicionados"
}

function findCep(value) {

  var zipcode = value.replace(/\D/g, '')

  if (zipcode != "") {
    var validZipcode = /^[0-9]{8}$/

    if (validZipcode.test(zipcode)) {

      var url = `https://viacep.com.br/ws/${zipcode}/json/`

      xhttp.open("GET", url, true)

      xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
          var address = JSON.parse(xhttp.response)
          updateFields(address)
          data.address = address
          data.zipcode = zipcode
        }
      }
      xhttp.send()
    }
    else {
      clearFields()
      alert("Formato de CEP inválido.")
    }
  }
  else {
    console.log("Escreva o seu CEP")
  }
}

function addProduct() {
  const product = document.getElementById("product").value
  if (product === null || product === undefined || product === "")
    alert("Para adicionar informe um produto")
  else {
    if (!data.products)
      data.products = []
    data.products.push(product)
    document.getElementById("product").value = ("")
    document.getElementById("productsSmall").textContent = data.products.join()
  }

}

function sendData() {
  if (verifyData()) {
    var url = `http://localhost:8080/farmer/`

    xhttp.open("POST", url, true)

    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8")

    xhttp.onreadystatechange = function () {
      if (xhttp.readyState == 4 && xhttp.status == 200) {
        registerDate()
      } else if ((xhttp.readyState == 4)) {
        alert("Erro interno do servidor")
      }
    }
    xhttp.send(JSON.stringify({
      name: data.name,
      email: data.email,
      phone: data.phone
    })
    )
  }
}

function registerDate() {

  var url = `http://localhost:8080/address/`

  xhttp.open("POST", url, true)

  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8")

  xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      sendAddress()
    } else if ((xhttp.readyState == 4)) {
      alert("Erro interno do servidor")
    }
  }
  xhttp.send(JSON.stringify({
    zipcode: data.zipcode,
    street: data.address.logradouro,
    state: data.address.uf,
    district: data.address.localidade,
    neighborhood: data.address.bairro
  })
  )
}

function sendAddress() {

  var url = `http://localhost:8080/farmer/address/${data.zipcode}/${data.email}/`

  xhttp.open("POST", url, true)

  xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      sendProducts()
    } else if ((xhttp.readyState == 4)) {
      alert("Erro interno do servidor")
    }
  }
  xhttp.send()
}

function sendProducts() {

  var xhr = []

  for (let index = 0; index < data.products.length; index++) {
    xhr[index] = new XMLHttpRequest()
    const product = data.products[index];
    var url = `http://localhost:8080/farmer/product/${product}/${data.email}/`
    xhr[index].open("GET", url, true)

    xhr[index].onreadystatechange = function () {
      if (xhr[index].readyState == 4 && xhr[index].status == 200) {
      } else if ((xhr[index].readyState == 4)) {
        alert("Erro interno do servidor")
      }
    }
    xhr[index].send()

  }
  clearAllFields()
  data = {}
  alert("Horta cadastrada!")
}


function verifyData() {
  data.name = document.getElementById("name").value
  data.email = document.getElementById("email").value
  data.phone = document.getElementById("phone").value
  const regexPhone = /^1\d\d(\d\d)?$|^0800 ?\d{3} ?\d{4}$|^(\(0?([1-9a-zA-Z][0-9a-zA-Z])?[1-9]\d\) ?|0?([1-9a-zA-Z][0-9a-zA-Z])?[1-9]\d[ .-]?)?(9|9[ .-])?[2-9]\d{3}[ .-]?\d{4}$/
  const regexEmail = /\S+@\w+\.\w{2,6}(\.\w{2})?$/
  if (data.name === "" || data.name === null || data.name === undefined) {
    alert("Nome inválido")
    return false
  }
  else if (!data.phone.match(regexPhone)) {
    alert("Telefone inválido")
    return false
  }
  else if (!data.email.match(regexEmail)) {
    alert("Email inválido")
    return false
  }
  else if (!data.address) {
    alert("Endereço inválido")
    return false
  }
  else if (!data.products || data.products.length < 1) {
    alert("Informe ao menos um produto")
    return false
  }
  else {
    return true
  }

}

