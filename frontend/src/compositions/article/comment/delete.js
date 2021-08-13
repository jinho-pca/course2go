import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

export const deleteComment = async (cid) => {
    const { URL, token } = BASE_URL()
	const headers = {
		'Authorization' : token
	}
    await axios({
        method: 'post',
        url: URL + `comment/delete`,
        headers: headers,
        params: {
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
