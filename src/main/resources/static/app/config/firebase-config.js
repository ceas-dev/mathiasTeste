import { initializeApp } from 'https://www.gstatic.com/firebasejs/10.9.0/firebase-app.js';
import {
    getAuth,
    signInWithEmailAndPassword,
    createUserWithEmailAndPassword,
    sendSignInLinkToEmail
} from 'https://www.gstatic.com/firebasejs/10.9.0/firebase-auth.js';
  

const firebaseConfig = {
  apiKey: "AIzaSyDgUbIaXKgTwENpg3D6p5D_wyirbWXJeUU",
  authDomain: "mathiasadm-b5772.firebaseapp.com",
  databaseURL: "https://mathiasadm-b5772-default-rtdb.firebaseio.com",
  projectId: "mathiasadm-b5772",
  storageBucket: "mathiasadm-b5772.appspot.com",
  messagingSenderId: "309705830758",
  appId: "1:309705830758:web:c4b9fd875dc91cd6bb58a7",
  measurementId: "G-6Z6LL0GDEL"
};

const firebaseApp = initializeApp(firebaseConfig);
const auth = getAuth(firebaseApp);

export {
    firebaseApp,
    auth,
    signInWithEmailAndPassword,
    createUserWithEmailAndPassword,
    sendSignInLinkToEmail
}
