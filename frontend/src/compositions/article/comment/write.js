import axios from 'axios'
import { BASE_URL } from '@/compositions/global.js'

export const write = async (parent, content, bid) => {
    const { URL, token } = BASE_URL()
    const data = {
        commentParent: parent,
        commentBid: bid,
        commentContent: content,
    }
	const headers = {
		'Authorization' : token
	}
    await axios({
        method: 'post',
        url: URL + `comment/write`,
        headers: headers,
        data: data,
    })
    .catch((err) => {
        alert('댓글을 쓰는데 실패했습니다.')
        return err
    })
}