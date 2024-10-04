function getToday(){
    let today = new Date();
    let year = today.getFullYear();
    let month = (today.getMonth() + 1).toString().padStart(2, '0');
    let day = today.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
}

function indexAndMyScheduleViewAddHTML(schedule_id, username, title, modification_date) {
    let tempHTML = `
                    <tr>
                        <td id="${schedule_id}">${schedule_id}</td>
                        <th id ="title">
                        <form id="form" action = "/view/scheduleBoardView" method="get">
                            <input name="schedule_id" value="${schedule_id}" style="display: none;">
                            <button type="submit" id ="titleBtn">${title}</button>
                        </form>
                        </th>
                        <th id ="${username}">
                        <form id="form" action = "/view/scheduleBoardView" method="get">
                            <input name="schedule_id" value="${schedule_id}" style="display: none;">
                            <button type="submit" id ="usernameBtn">${username}</button>
                        </form>
                        </th>
                        <td id ="${modification_date}">${modification_date}</td>
                    </tr>`;
    $('#tbody-box').append(tempHTML);
}

function emailChecked(user_email){
    let reg = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
    if(user_email === ''){
        return 0;
    }
    if(!reg.test(user_email)){
        return 1;
    }
}

function writeView() {
    location.href = "/view/writeView";
}

function mySchedule(){
    location.href = "/view/myScheduleView";
}
