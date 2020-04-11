$(document).ready(function () {
    var auId=window.location.href.split('?')[1].split('&')[1].split('=')[1];
    var auName = (window.location.href.split('?')[1].split('&')[0].split('=')[1]).replace(/' '/g,'_');
    getCoAuthor();
    function getCoAuthor() {
        getRequest(
            '/author/getCoAuthorById?auId='+auId,
            function (res) {
                var coAuthorList=res.content;
                var auData=[{
                    name: 'Present Author',
                    symbolSize:80,
                    itemStyle: {
                        normal: {
                            color: 'red'
                        }
                    }
                }];
                for(var i=0;i<coAuthorList.length;i++) {
                    auData.push(
                        {
                            name: coAuthorList[i].name,
                            des: coAuthorList[i].institution,
                            symbolSize: 70,
                            itemStyle: {
                                normal: {
                                    color: '#4b565b'
                                }
                            }
                        }
                    )

                }
                var auLink=[];
                for(var j=0;j<coAuthorList.length;j++){
                    auLink.push({
                        source: 'Present Author',
                        target: coAuthorList[j].name,
                        name: 'coWorker'
                    })
                }
                var myChart = echarts.init(document.getElementById('main'));
                var option = {
                    title: { text: '学者关系图谱' },
                    tooltip: {
                        formatter: function (x) {
                            return x.data.des;
                        }
                    },
                    series: [
                        {
                            type: 'graph',
                            layout: 'force',
                            symbolSize: 60,
                            roam: true,
                            edgeSymbol: ['circle', 'arrow'],
                            edgeSymbolSize: [4, 7],
                            edgeLabel: {
                                normal: {
                                    textStyle: {
                                        fontSize: 30
                                    }
                                }
                            },
                            force: {
                                repulsion: 2500,
                                layoutAnimation: false,
                                edgeLength: [10, 100]
                            },
                            draggable: false,
                            itemStyle: {
                                normal: {
                                    color: '#4b565b'
                                }
                            },
                            lineStyle: {
                                normal: {
                                    width: 2,
                                    color: '#4b565b',
                                    curveness:0.2
                                }
                            },
                            edgeLabel: {
                                normal: {
                                    show: true,
                                    formatter: function (x) {
                                        return x.data.name;
                                    }
                                }
                            },
                            label: {
                                normal: {
                                    show: true,
                                    textStyle: {
                                    }
                                }
                            },
                            data: auData,
                            links:auLink,
                        }
                    ]
                };
                myChart.setOption(option);
                myChart.on('click', function (param){
                    var name=param.name;
                    getAuthorByName(name);
                });


            },
            function (error) {
                alert(error);
            });
    }
    function getAuthorByName(auName){
        getRequest(
            '/index/getAuthorIdByName?auName='+auName,
            function (res) {
                var authorId=(res.content)[0].id;
                window.location.href='../Author/authorDetail.html?id='+authorId;
            },
            function (error) {
                alert(error);
            });
    }

});