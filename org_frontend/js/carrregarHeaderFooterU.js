function carregaHeaderFooterU() {
    const URL = "http://localhost:8080/apis/user/get-parametrizacao";
    const token = localStorage.getItem('jwtToken');
    const tag = document.getElementById("headerUser");
    const tag2 = document.getElementById("footerUser");
    var link = "http://localhost:8080";

    fetch(URL, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    })
    .then(resp => {
        return resp.json();
    })
    .then(json => {
        // Aqui você tem acesso aos dados retornados pela requisição
        link=link+json.logotipoG;
        console.log(link);
       
        // Construção do conteúdo dinâmico com base nos dados recebidos
        let headerContent = `<div class="logo">
                                <img src="${link}" alt="Logo da Empresa"> 
                            </div>
                            <nav class="main-nav">
                                <ul>
                                    <li><a href="#">Serviços</a></li>
                                    <li><a href="#">Cursos</a></li>
                                    <li><a href="#">Realizar doação</a></li>
                                    <li><a href="#">Sobre Nós</a></li>
                                </ul>
                            </nav>
                            <div class="menu-toggle">
                                <input type="checkbox" id="toggle">
                                <label for="toggle" class="toggle-btn">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </label>
                                <div class="menu">
                                    <ul>
                                        <li><a href="#">Serviços</a></li>
                                        <li><a href="#">Cursos</a></li>
                                        <li><a href="#">Realizar doação</a></li>
                                        <li><a href="#">Sobre Nós</a></li>
                                    </ul>
                                </div>
                            </div>`;

        tag.innerHTML = headerContent;

        let footerContent = `<div class="footer-content">
                                <div class="footer-section about">
                                    <h3>Sobre Nós</h3>
                                    <p>Informações sobre a empresa, missão, visão, e valores.</p>
                                </div>
                                <div class="footer-section links">
                                    <h3>Links Úteis</h3>
                                    <ul>
                                        <li><a href="#">Serviços</a></li>
                                        <li><a href="#">Cursos</a></li>
                                        <li><a href="#">Cadastros</a></li>
                                        <li><a href="#">Realizar doação</a></li>
                                        <li><a href="#">Sobre Nós</a></li>
                                    </ul>
                                </div>
                                <div class="footer-section contact">
                                    <h3>Contato</h3>
                                    <p>Email: ${json.email}</p>
                                    <p>Telefone: (99) 99999-9999</p>
                                </div>
                                <div class="footer-section social">
                                    <h3>Redes Sociais</h3>
                                    <div class="social-icons">
                                        <a href="#"><img src="https://www.facebook.com/images/fb_icon_325x325.png" alt="Facebook"></a>
                                        <a href="#"><img src="https://img.freepik.com/vetores-premium/novo-logotipo-do-twitter-x-2023-download-do-vetor-do-logotipo-do-twitter-x_691560-10794.jpg" alt="Twitter"></a>
                                        <a href="#"><img src="https://www.instagram.com/static/images/ico/favicon-192.png/68d99ba29cc8.png" alt="Instagram"></a>
                                    </div>
                                </div>
                            </div>

                            <div class="footer-bottom">
                                <p>&copy; 2024 ${json.razaoSocial}. Todos os direitos reservados.</p>
                            </div>`;

        tag2.innerHTML = footerContent;
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
    });
}
