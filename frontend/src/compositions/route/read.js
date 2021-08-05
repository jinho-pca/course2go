import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

export const read = async (bid) => {
    var route = [];
    const URL = BASE_URL()
    await axios({
        method: 'post',
        url: URL+'route/read',
        params: {
            bid : bid,
        }
    })
    .then((res) => {
        route = res.data.object;
        return res.data
    })
    .catch((err) => {
        alert('동선을 불러오는데 실패했습니다.')
        return err
    })
    console.log(route);
    return route
}