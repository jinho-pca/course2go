import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

export const read = async (bid) => {
    var route = [];
    const { URL } = BASE_URL()
    // console.log("read 함수 작동")
    let frm = new FormData();
    frm.append("bid", bid);
    await axios({
        method: 'post',
        url: URL + 'route/read',
        data: frm,
    })
    .then((res) => {
        route = res.data.object;
        return res.data
    })
    .catch((err) => {
        console.log(err)
        alert('동선을 불러오는데 실패했습니다.')
        return err
    })
    // console.log(route);
    // console.log("read 종료");
    return route
}