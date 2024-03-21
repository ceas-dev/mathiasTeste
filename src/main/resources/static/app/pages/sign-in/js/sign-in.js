import { signInToken } from '../../../services/firebase-service.js';
import { verifyToken, generateLinkEmailResetPassword,   } from '../../../services/app-service.js';
import { saveToken } from '../../../utils/token-handler.js';

class View {
    #events
    constructor() {
        this.formLogin = document.getElementById('form-login')
        this.inputEmail = document.getElementById('input-email')
        this.inputPassword = document.getElementById('input-password');
        this.btnSignIn = document.getElementById('btn-sign-in');
        this.divAlert = document.getElementById('div-alert');
        this.divSpinner = document.getElementById('div-spinner');
        this.btnRecouvery = document.getElementById('btn-recouvery');
        this.#events = {
            onLoginTry() {
                view.setLoading(true)
                view.setError(null)
            },
            onLoginSucess() {
                view.setLoading(false)
            },
            onLoginErro(error) {
                view.setLoading(false)
                view.setError(error)
            }
        }
    }

    setError(text) {
        this.divAlert.style.display = text ? 'block' : 'none';
        this.divAlert.innerText = text;
    }

    setLoading(loading) {
        if (loading) {
            this.btnSignIn.style.display = 'none';
            this.divSpinner.style.display = 'block';
        } else {
            this.btnSignIn.style.display = 'block';
            this.divSpinner.style.display = 'none';
        }
    }

    getValueEmail = () => this.inputEmail.value;
    getValuePassword = () => this.inputPassword.value;

    setValueEmail(email) {
        this.inputEmail.value = email;
    }

    setValuePassword(password) {
        this.inputPassword.value = password;
    }

    addEventLogin(loginEvent) {
        this.formLogin.addEventListener('submit', event => {
            event.preventDefault();
            if (loginEvent) {
                loginEvent(this.getValueEmail(), this.getValuePassword());
            }
        })
    }

    getEvents() {
        return this.#events;
    }
}

function translateError(error){
    const messages = {
        'auth/invalid-credential':'E-mail ou senha invalido.',
        'auth/user-disabled':'Sua conta está bloqueada, entre encontato com seu superior.'
    }
    const message = messages[error.code];
    return message ? message : error;
}


const view = new View();
view.setValueEmail('carlos.teste@hotmail.com')
view.setValuePassword('12345678');

view.addEventLogin((email, password) => {
    view.getEvents().onLoginTry();
    const interval = setInterval(() => {
        signInToken(email, password)
            .then(token => verifyToken(token))
            .then(tokenInfo => {
                view.getEvents().onLoginSucess();
                alert(JSON.stringify(tokenInfo))
                //saveToken(tokenInfo)
            })
            .catch(error => view.getEvents().onLoginErro(translateError(error)));
        clearInterval(interval)
    }, 1000)
});

view.btnRecouvery.onclick = ()=>{
    sendSign(view.getValueEmail())
    .then(alert)
    .catch(alert)
/*generateLinkEmailResetPassword(view.getValueEmail())
.then(link => {
    alert(link)
    console.log(link)
})
.catch(error => alert(error))*/
}

