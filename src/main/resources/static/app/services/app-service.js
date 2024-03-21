async function verifyToken(token){
    const response = await fetch(`/auth/token/${token}`);
    if(!response.ok){
        throw new Error(response.status);
    }
    return await response.json();
}


async function generateLinkEmailResetPassword(email){
    const response = await fetch(`/auth/reset-password/${email}`)
    if(!response.ok){
        throw new Error(response.status)
    }
    return await response.text();
}

export {verifyToken, generateLinkEmailResetPassword};