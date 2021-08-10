import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

export const write = async (parent, content, bid) => {
    const { URL } = BASE_URL()
    const data = {
        commentParent: parent,
        commentBid: bid,
        commentContent: content,
    }
    await axios({
        method: 'post',
        url: URL + `comment/read/${bid}`,
        data: data,
    })
    .catch((err) => {
        alert('댓글을 쓰는데 실패했습니다.')
        return err
    })
}