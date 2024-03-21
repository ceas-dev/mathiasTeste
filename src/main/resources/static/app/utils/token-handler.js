function saveToken(token){
    localStorage.setItem('token', JSON.stringify(token));
}

function getToken(key = ''){
    const token = JSON.parse(localStorage.getItem('token'));
    if(token){
        return key ? token[key] : token;
    }  
}

function deleteTokenSaved(){
    localStorage.removeItem('token');
}

function isExpireToken(){
    const token = getToken();
    if(token){
        return Date.now() > (token.expires * 1000)
    }
}

export {getToken, saveToken, deleteTokenSaved, isExpireToken}
