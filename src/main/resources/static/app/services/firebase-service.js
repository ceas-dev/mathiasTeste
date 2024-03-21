import { 
    auth,
    signInWithEmailAndPassword,
    createUserWithEmailAndPassword
} from "../config/firebase-config.js";

async function signInToken(email, password) {
    const userCredential = await signInWithEmailAndPassword(auth, email, password);
    return await userCredential.user.getIdToken();
}

async function createUser(email, password){
  return createUserWithEmailAndPassword(auth, email, password)
}

export{ signInToken, createUser }