var chart;
$(function () {
    $(document).ready(function() {
            chart = new Highcharts.Chart({
             chart: {
                type: 'column'
            }, 
            colors: ['red', '#1aadce','#492970','#f28f43','#77a1e5','#c42525','#a6c96a'

                      ],
            title: {
                text: '电子市场数据图表'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: [
                    '新增',
                    '活跃',
                    '启动次数'
                ]
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '今日',
                data: [3502, 24814,54319]

            }, {
                name: '昨日',
                data: [4572,18484,69982]

            }]
        });
    });
});