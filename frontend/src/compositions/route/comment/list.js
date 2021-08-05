import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

export const list = async (bid) => {
    var list = [];
    const URL = BASE_URL()
    await axios({
        method: 'post',
        url: URL+'comment/read',
        params: {
            bid : bid,
        }
    })
    .then((res) => {
        list = res.data.object;
        return res.data
    })
    .catch((err) => {
        alert('댓글을 불러오는데 실패했습니다.')
        return err
    })
    console.log(list);
    return list
}