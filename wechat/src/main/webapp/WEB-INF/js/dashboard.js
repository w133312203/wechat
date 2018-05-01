$(function () {
	
	
	
	Highcharts.setOptions({
       
    });
    Highcharts.chart('container1', {
    	colors: ['#a9d2fe', '#afe2aa'],
        chart: {
            type: 'area'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: 'Source: <a href="http://thebulletin.metapress.com/content/c4120650912x74k7/fulltext.pdf">' +
                'thebulletin.metapress.com</a>'
        },
        xAxis: {
            allowDecimals: true,
            type: 'datetime',
            dateTimeLabelFormats: {
            	millisecond: '%H:%M:%S.%L',
                second: '%H:%M:%S',
                minute: '%H:%M',
                hour: '%H:%M',
                day: '%m-%d',
                week: '%m-%d',
                month: '%Y-%m',
                year: '%Y'
            }
        },
        yAxis: {
            title: {
                text: ''
            },
            labels: {
                formatter: function () {
                    return this.value / 1000 + 'k';
                }
            }
        },
        tooltip: {
            pointFormat: '{series.name} produced <b>{point.y:,.0f}</b>',
            plotBackgroundColor: '#f0f'
        },
        plotOptions: {
        	area: {
                pointStart: 1940,
                marker: {
                    enabled: false,
                    symbol: 'circle',
                    radius: 10,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            },
            column:{
            	colorByPoint:true
            }
        },
        series: [{
            name: 'PV',
            data: pvdatas
        },{
            name: 'UV',
            data: [[Date.UTC(2017, 2, 1), null],
                   [Date.UTC(2017, 2, 2), null], 
                   [Date.UTC(2017, 2, 3), null], 
                   [Date.UTC(2017, 2, 4), null], 
                   [Date.UTC(2017, 2, 5), null], 
                   [Date.UTC(2017, 2, 6), null], 
                   [Date.UTC(2017, 2, 7), 6], 
                   [Date.UTC(2017, 2, 8), 10], 
                   [Date.UTC(2017, 2, 9), 22], 
                   [Date.UTC(2017, 2, 10), 55], 
                   [Date.UTC(2017, 2, 11), 135], 
                   [Date.UTC(2017, 2, 12), 209], 
                   [Date.UTC(2017, 2, 13), 340],
                   [Date.UTC(2017, 2, 14), 305],
            	   [Date.UTC(2017, 2, 15), 343],
            	   [Date.UTC(2017, 2, 16), 403],
            	   [Date.UTC(2017, 2, 17), 405],
            	   [Date.UTC(2017, 2, 18), 518],
            	   [Date.UTC(2017, 2, 19), 544],
            	   [Date.UTC(2017, 2, 20), 682],
            	   [Date.UTC(2017, 2, 21), 756],
            	   [Date.UTC(2017, 2, 22), 743],
            	   [Date.UTC(2017, 2, 23), 812],
            	   [Date.UTC(2017, 2, 24), 938],
            	   [Date.UTC(2017, 2, 25), 1045],
            	   [Date.UTC(2017, 2, 26), 1105],
            	   [Date.UTC(2017, 2, 27), 1298],
            	   [Date.UTC(2017, 2, 28), 1304],
            	   [Date.UTC(2017, 2, 29), 1423],
            	   [Date.UTC(2017, 2, 30), 1422],
            	   [Date.UTC(2017, 2, 31), 1534]]
        }]
    });
    
    Highcharts.chart('container2', {
    	colors: ['#a9d2fe'],
        chart: {
            type: 'area'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: 'Source: <a href="http://thebulletin.metapress.com/content/c4120650912x74k7/fulltext.pdf">' +
                'thebulletin.metapress.com</a>'
        },
        xAxis: {
            allowDecimals: true,
            type: 'datetime',
            dateTimeLabelFormats: {
            	
                hour: '%H:%M',
                day: '%m-%d',
                week: '%m-%d',
                month: '%Y-%m',
                year: '%Y'
            }
        },
        yAxis: {
            title: {
                text: ''
            },
            labels: {
                formatter: function () {
                    return this.value;
                }
            }
        },
        tooltip: {
            pointFormat: '{series.name} produced <b>{point.y:,.0f}</b>',
            plotBackgroundColor: '#f0f'
        },
        plotOptions: {
            area: {
                pointStart: 1940,
                marker: {
                    enabled: false,
                    symbol: 'circle',
                    radius: 10,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            },
            column:{
            	colorByPoint:true
            }
        },
        series: [{
            name: 'contacts',
            data: datas
        }]
    });
    $('#container3').highcharts({
    	colors: ['#70c6df','#7fc57f','#6edcb5','#efb466','#e07a78'],
        chart: {
            type: 'bar'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [
                '线下',
                '邮件',
                'SOCIAL',
                '网站',
                '短信'
            ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            valueSuffix: ''
        },
        plotOptions: {
        	bar: {
                dataLabels: {
                    enabled: true
                }
            },
            column: {
                colorByPoint:true
            }
        },
        credits: {
            enabled: false
        },
        series: [{
            name: '30天来源渠道',
            colorByPoint:true,  //或者直接写在这里
            data: channelArrayList
        }]
    });
    
    $('#container4').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: 'Irregular time data in Highcharts JS'
        },
        xAxis: {
        	 allowDecimals: true,
             type: 'datetime',
            dateTimeLabelFormats: { // don't display the dummy year
            	 hour: '%H:%M',
            },
            title: {
                text: ''
            }
        },
        yAxis: {
            title: {
                text: ''
            },
            min: 0
        },
        tooltip: {
            headerFormat: '<b>{series.name}</b><br>',
            pointFormat: '{point.x:%e. %b}: {point.y:.2f} m '
        },
        plotOptions: {
            spline: {
                marker: {
                    enabled: true
                }
            }
        },
        series: [{
            name: 'new contacts',
            data: timeDatas
        }]
    });
    
    $('#container5').highcharts({
    	color:['#201641','#9ac0e5','#91b5d9','#85a6c7'],
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: 0,
            plotShadow: false
        },
        title: {
            text: '发送总数<br>'+counts,
            align: 'center',
            verticalAlign: 'middle',
            y: -10
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                dataLabels: {
                    enabled: true,
                    distance: 20,
                    style: {
                        color: '#666',
                        textShadow: 'none',
                        boxShadow: 'none'
                    }
                },
                center: ['50%', '45%']
            }
        },
        series: [{
            type: 'pie',
            name: '发送数量',
            innerSize: '50%',
            data: [
                ['发送成功',   counts],
                ['发送失败',   fCounts],
                ['软退', sCounts],
                ['硬退',    hCounts],
                {
                	name:'Others',
                	y:0.7,
                	dataLabels: {
                		enabled:false
                	}
                }
            ]
        }]
    });
	
	$('#container6').highcharts({
		color:['#201641','#9ac0e5','#91b5d9','#85a6c7'],
        chart: {
            type: 'funnel',
            marginRight: 100
        },
        title: {
            text: '',
            x: -50
        },
        plotOptions: {
            series: {
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b> ({point.y:,.0f})',
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
                    softConnector: true
                },
                neckWidth: '100px',
                neckHeight: '1px'
            }
        },
        legend: {
            enabled: false
        },
        series: [{
            name: '数量',
            data: [
                ['发送数量', counts],
                ['发送成功', counts-fCounts-sCounts-hCounts],
                ['阅读数', rCounts],
                ['点击数', ckCounts],
                {
                	name:'Others',
                	y:0.7,
                	dataLabels: {
                		enabled:false
                	}
                }
            ]
        }]
    });
    
     $('#container7').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [
                '注册',
                '签到',
                '互动',
                '问答'
            ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            valueSuffix: ''
        },
        plotOptions: {
        	bar: {
                dataLabels: {
                    enabled: true
                }
            },
            column: {
                colorByPoint:true
            }
        },
        credits: {
            enabled: false
        },
        series: [{
            name: '最近会议',
            colorByPoint:true,  //或者直接写在这里
            data: [userCount , signinCount , feedCount , surveyCount ]
        }]
    });
    
    $('#container8').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [
                '1',
                '2',
                '3',
                '4',
                '5',
                '6',
                '7',
                '8',
                '9',
                '10',
                '11',
                '12'
            ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '场次',
            data: [56, 45, 38, 59, 45, 65, 69, 39, 12, 54, 96, 74]
        }]
    });
    
    $('#container9').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [
                '1',
                '2',
                '3',
                '4',
                '5',
                '6',
                '7',
                '8',
                '9',
                '10',
                '11',
                '12'
            ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '人数',
            data: [56, 45, 38, 59, 45, 65, 69, 39, 12, 54, 96, 74]
        }]
    });
    
    $('#container10').highcharts({
        chart: {
            type: 'funnel',
            marginRight: 100
        },
        title: {
            text: '',
            x: -50
        },
        plotOptions: {
            series: {
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b> ({point.y:,.0f})',
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
                    softConnector: true
                },
                neckWidth: '100px',
                neckHeight: '1px'
            }
        },
        legend: {
            enabled: false
        },
        series: [{
            name: '数量',
            data: pipeLinelist
        }]
    });
});