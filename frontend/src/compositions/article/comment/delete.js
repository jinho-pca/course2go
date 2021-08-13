import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

export const deleteComment = async (cid) => {
    const { URL } = BASE_URL()
    await axios({
        method: 'post',
        url: URL + `comment/delete`,
        data: {
            cid: cid
        }
    })
    .then((res) => {
        return res
    })
    .catch((err) => {
        alert('댓글을 삭제하는데 실패했습니다.')
        return err
    })
}
