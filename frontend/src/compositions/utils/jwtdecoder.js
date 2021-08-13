import jwt from 'jsonwebtoken'
import { BASE_URL } from '@/compositions/global.js'

export const jwtdecoder = () => {
    const { token } = BASE_URL()
    const user = jwt.decode(token.substr(7));
    const myemail = user.userEmail;
    const mynickname = user.userNickname;
    const myimage = user.userImage;
    return { myemail, mynickname, myimage }
}