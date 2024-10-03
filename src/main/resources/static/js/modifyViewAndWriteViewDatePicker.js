function setDatePicker() {
    $('input[name="daterange"]').daterangepicker({
        "startDate" : getToday(),
        opens: 'center',
        locale: {
            format: 'YYYY-MM-DD',
            language : 'kr',
            dayNames: ['일', '월', '화', '수', '목', '금', '토'],
            dayNamesMin: ['일','월','화','수','목','금','토'], //달력의 요일 텍스트
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], //달력의 월 부분 텍스트
            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeYear: true, //option값 년 선택 가능
            changeMonth: true,
            showMonthAfterYear:true,
            yearSuffix: '년'
        }
    });
};

