//Parte de autocomplete e replace
document.addEventListener("DOMContentLoaded", function () {

    const campoCPF = document.querySelector('input[name="cpf"]');
    const campoTelefone = document.querySelector('input[name="telefone"]');
    const campoCEP = document.querySelector('input[name="cep"]');
    const cepInput = document.querySelector('input[name="cep"');


    // Adicionar eventos de escuta aos campos
    campoCPF.addEventListener('input', function (event) {
        this.value = formatarCPF(event.target.value);
    });

    campoTelefone.addEventListener('input', function (event) {
        this.value = formatarTelefone(event.target.value);
    });

    // Adicionar evento de input ao campo de CEP
    campoCEP.addEventListener('input', function (event) {
        let cep = event.target.value.replace(/\D/g, ''); // Remove caracteres não numéricos
        if (cep.length > 5) { // Adicionar traço após o 5º dígito
            cep = cep.replace(/^(\d{5})(\d)/, '$1-$2');
        }

        // Atualizar valor do campo com o CEP formatado
        this.value = cep;

        if (cep.length === 9) { // Quando o CEP atingir 8 dígitos, chamar a função buscarCEP
            cep = event.target.value.replace(/\D/g, '');
            buscarCEP(cep);
        }
    });
});



// Função para formatar o CPF
function formatarCPF(cpf) {
    cpf = cpf.replace(/\D/g, '');
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
}


// Função para formatar o telefone
function formatarTelefone(telefone) {
    telefone = telefone.replace(/\D/g, '');
    return telefone.replace(/(\d{2})(\d{4,5})(\d{4})/, "($1) $2-$3");
}


// Função para buscar dados do CEP via API
async function buscarCEP(cep) {
    const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
    const data = await response.json();

    if (data.erro) {
        console.log("CEP não encontrado.");
    } else {
        document.querySelector('input[name="endereco"]').value = data.logradouro;
        document.querySelector('input[name="bairro"]').value = data.bairro;
        document.querySelector('input[name="cidade"]').value = data.localidade;
        document.querySelector('input[name="estado"]').value = data.uf;
    }
}

// Configurar evento de input para o campo de CPF
document.querySelector('input[name="cpf"]').addEventListener('input', function (event) {
    this.value = formatarCPF(event.target.value);
});

// Configurar evento de input para o campo de telefone
document.querySelector('input[name="telefone"]').addEventListener('input', function (event) {
    this.value = formatarTelefone(event.target.value);
});

// Configurar evento de blur para o campo de CEP
document.querySelector('input[name="cep"]').addEventListener('blur', function (event) {
    buscarCEP(this.value);
});


document.addEventListener("DOMContentLoaded", function () {
    const nomeBorder = document.getElementById("nome");
    const emailBorder = document.getElementById("emailU");  
    const senhaBorder = document.getElementById("passU");
    const cpfBorder = document.getElementById("cpf");
    const telefoneBorder = document.getElementById("telefone");
    const cepBorder =document.getElementById("cep");
    const cidadeBorder = document.getElementById("cidade");
    const estadoBorder = document.getElementById("estado");
    const enderecoBorder = document.getElementById("endereco");
    const bairroBorder =document.getElementById("bairro");

    adicionarEventos(nomeBorder);
    adicionarEventos(cpfBorder);
    adicionarEventos(telefoneBorder);
    adicionarEventos(cepBorder);
    adicionarEventos(cidadeBorder);
    adicionarEventos(estadoBorder);
    adicionarEventos(enderecoBorder);
    adicionarEventos(bairroBorder);

    emailBorder.addEventListener("focus", function () {
        emailBorder.style.borderColor = "blue";
    });

    emailBorder.addEventListener("blur", function () {
        emailBorder.style.borderColor = "#ccc";
    });


    senhaBorder.addEventListener("focus", function () {
        senhaBorder.style.borderColor = "blue";
    });

    senhaBorder.addEventListener("blur", function () {
        senhaBorder.style.borderColor = "#ccc";
    });
});


function cadastrarUsuario(event) {

    event.preventDefault();

    const nome = document.getElementById("nome");
    const email = document.getElementById("emailU");
    const senha = document.getElementById("passU");
    const cpf = document.getElementById("cpf");
    const telefone = document.getElementById("telefone");
    const cep = document.getElementById("cep");
    const cidade = document.getElementById("cidade");
    const estado = document.getElementById("estado");
    const endereco = document.getElementById("endereco");
    const bairro = document.getElementById("bairro");

    const divNome = document.getElementById("divNome");
    const divEmail = document.getElementById("divEmail");
    const divPass = document.getElementById("divPass");
    const divCPF = document.getElementById("divCPF");
    const divTelefone = document.getElementById("divTelefone");
    const divCEP = document.getElementById("divCEP");
    const divCidade = document.getElementById("divCidade");
    const divEndereco = document.getElementById("divEndereco");
    const divBairro = document.getElementById("divBairro");
    const divEstado = document.getElementById("divEstado");
    const divSucesso = document.getElementById("divSucesso");
    const divErro = document.getElementById("divErro");


    const divNotify = document.getElementById("notify");

    if(!divNotify.checked)
    {
        divNotify.checked=true;
        divNotify.value="não";
    }

    if (nome.value.trim() === '') {
        mostrarAviso(nome, divNome);
        adicionarEventos(nome);
    } else if (email.value.trim() === '' || !validarEmail(email.value)) {
        mostrarAviso(email, divEmail);
        adicionarEventos(email);
    } else if (senha.value.trim() === '') {
        mostrarAviso(senha, divPass);
        adicionarEventos(senha);
    } else if (cpf.value.trim() === '' || !validarCPF(cpf.value)) {
        mostrarAviso(cpf, divCPF);
        adicionarEventos(cpf);
    } else if (telefone.value.trim() === '') {
        mostrarAviso(telefone, divTelefone);
        adicionarEventos(telefone);
    } else if (cep.value.trim() === '') {
        mostrarAviso(cep, divCEP);
        adicionarEventos(cep);
    } else if (cidade.value.trim() === '') {
        mostrarAviso(cidade, divCidade);
        adicionarEventos(cidade);
    } else if (estado.value.trim() === '') {
        mostrarAviso(estado, divEstado);
        adicionarEventos(estado);
    } else if (endereco.value.trim() === '') {
        mostrarAviso(endereco, divEndereco);
        adicionarEventos(endereco);
    } else if (bairro.value.trim() === '') {
        mostrarAviso(bairro, divBairro);
        adicionarEventos(bairro);
    } else {

            const URL = "http://localhost:8080/apis/user/add-controleacesso";

            const token = localStorage.getItem('jwtToken');
            var formU = document.getElementById("formU");
            
            console.log("entrou aqui");
            
            fetch(URL, {
                method: 'POST', 
                headers: {
                    'Authorization': `Bearer ${token}`
                },
                body: new FormData(formU)
            })
            .then(resp => {

                if(resp.status===409)
                {
                    divErro.style.display="block";
                    setTimeout(()=>{
                        divErro.style.display="none";
                        //window.location.href="index.html";
                    },3000);//esconde a div apos 2segundos e pouco
                    console.log('Erro bizarro..');
                }
                else
                {

                    // Todos os campos estão preenchidos
                    divSucesso.style.display="block";
                    setTimeout(()=>{
                        divSucesso.style.display="none";
                        //window.location.href="index.html";
                    },3000);//esconde a div apos 2segundos e pouco
                    console.log('Todos os campos estão preenchidos corretamente');
                }
                
                //alert("Denuncia \u2022"+formDenuncia.elements["denName"].value+"\u2022 Concluida!");
                //window.location.href="cadastroParametrizacao.html";
                return console.log(resp);
            })
            .catch(error => {
                console.error('Erro na requisição:', error);
            });
}
}

function mostrarAviso(elemento, div)
{
    elemento.style.borderColor = "red";
    div.style.display="block";
    setTimeout(()=>{
        div.style.display="none";
    },2200);//esconde a div apos 2segundos e pouco
}

function adicionarEventos(elemento) {
    elemento.addEventListener("focus", function () {
        elemento.style.borderColor = "blue";
        // Código adicional que você pode querer executar quando o elemento recebe foco
    });

    elemento.addEventListener("blur", function () {
        elemento.style.borderColor = "#ccc";
        // Código adicional que você pode querer executar quando o elemento perde foco
    });
}


function validarEmail(email)
{
    return /^[\w+.]+@\w+\.\w{2,}(?:\.\w{2})?$/.test(email);
}

function validarCPF(cpf) {
    // Remove caracteres não numéricos
    cpf = cpf.replace(/[^\d]/g, '');

    // Verifica se o CPF tem 11 dígitos
    if (cpf.length !== 11) {
        return false;
    }

    // Verifica se todos os dígitos são iguais
    if (/^(\d)\1{10}$/.test(cpf)) {
        return false;
    }

    // Calcula o primeiro dígito verificador
    let sum = 0;
    for (let i = 0; i < 9; i++) {
        sum += parseInt(cpf.charAt(i)) * (10 - i);
    }
    let firstDigit = 11 - (sum % 11);
    if (firstDigit > 9) {
        firstDigit = 0;
    }

    // Verifica o primeiro dígito verificador
    if (parseInt(cpf.charAt(9)) !== firstDigit) {
        return false;
    }

    // Calcula o segundo dígito verificador
    sum = 0;
    for (let i = 0; i < 10; i++) {
        sum += parseInt(cpf.charAt(i)) * (11 - i);
    }
    let secondDigit = 11 - (sum % 11);
    if (secondDigit > 9) {
        secondDigit = 0;
    }

    // Verifica o segundo dígito verificador
    if (parseInt(cpf.charAt(10)) !== secondDigit) {
        return false;
    }

    // CPF válido
    return true;
}


