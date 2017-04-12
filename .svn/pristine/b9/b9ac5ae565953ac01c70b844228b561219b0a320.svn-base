(function ($) {
    $.Home = {
        loadHomeStartSchoolReport: function () {
            $("#HomeStartSchoolReport_ListContainer").show();
            $.getServerJson("api/StarSchoolReportAPI/GetHomeTargetFinishInformationList",
               null,
              function (result) {
                  $("#HomeStartSchoolTarget_List").datalist("loadData", result);
                  $.Home.loadHomeStartSchoolTargetChart(result);
              });
        },
        loadHomeStartSchoolTargetChart: function (result) {
            var arrName = []; arrMFinish = []; arrMTotal = [];
            $.each(result.rows, function (index, item) {
                arrName.unshift(item.Name);
                var mfinishNum = item.MFinishNumRate;
                arrMFinish.unshift(mfinishNum);
                arrMTotal.unshift(100 - mfinishNum);
            });
            require.config({
                paths: {
                    echarts: '/Content/echarts'
                }
            });
            require(
                [
                    'echarts',
                    'echarts/chart/bar'//,
                    //'echarts/chart/line'
                ],
                function (ec) {
                    myChart = ec.init(document.getElementById('HomeStartSchoolTarget_Chart'));
                    myChart.setOption({
                        tooltip : {
                            trigger: 'axis',
                            axisPointer : {
                                type: 'shadow'
                            },
                            formatter: "{b}<br/>{a}：{c}%"
                        },
                        title: {
                            text: '     指标项名称',
                            subtext: ''
                            , textStyle: {
                                fontSize: 15,
                                fontWeight: 'normal',
                                color: '#404d5b'
                            },
                        },
                        legend: {
                            data: ['完成进度', '未完成进度 ']
                            ,show:false
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                saveAsImage : {show: true}
                            }
                        },
                        calculable: true,
                        grid: {
                            x: 100,
                            x2: 30,
                            y: 25,
                            y2: 30,
                        },
                        xAxis : [
                            {
                                type: 'value'
                                //, axisLabel: {
                                //    show: true,
                                //    interval: 'auto',
                                //    formatter: '{value}%'
                                //}
                            }
                        ],
                        yAxis : [
                            {
                                type: 'category',
                                data: arrName
                                , axisLabel: { show: true, textStyle: { fontSize: 14, color: '#333' }}
                            }
                        ],
                        series : [
                            {
                                name: '已完成',
                                type:'bar',
                                stack: '总量',
                                itemStyle: {
                                    normal: {
                                        label: { show: true, position: 'insideRight', formatter: '{c}%' }
                                        , color: '#67B94B'
                                    }
                                },
                                data: arrMFinish
                            },
                            {
                                name: '未完成',
                                type:'bar',
                                stack: '总量',
                                itemStyle: {
                                    normal: {
                                        label: { show: false, position: 'insideRight' }
                                        , color: '#D3D3D3',
                                    }
                                },
                                data: arrMTotal
                            }
                        ]
                    });
                });
        },
        loadHomeSecureSchoolReport: function () {
            $("#HomeSecureSchoolReport_ListContainer").show();
            $.getServerJson("api/SecureSchoolReportCheckAPI/GetHomeSecureSchoolReportList",
               null,
              function (result) {
                  $("#HomeSecureSchoolReport_List").datalist("loadData", result);
              });
        },
        loadHomeRoutineStudentCredential: function () {
            $("#HomeRoutineStudentCredential_ListContainer").show();
            $.getServerJson("api/RoutineStudentCredentialCheckAPI/GetHomeRoutineStudentCredentialList",
                null,
               function (result) {
                   $("#HomeRoutineStudentCredential_List").datalist("loadData", result);
               });
        },
        loadHomeRoutineTeacherCredential: function () {
            $("#HomeRoutineTeacherCredential_ListContainer").show();
            $.getServerJson("api/RoutineTeacherCredentialCheckAPI/GetHomeRoutineTeacherCredentialList",
                null,
               function (result) {
                   $("#HomeRoutineTeacherCredential_List").datalist("loadData", result);
               });
        },
        loadHomeRoutineInspectorMaterial: function () {
            $("#HomeRoutineInspectorMaterial_ListContainer").show();
            $.getServerJson("api/RoutineInspectorMaterialCheckAPI/GetHomeRoutineInspectorMaterialList",
                null,
               function (result) {
                   $("#HomeRoutineInspectorMaterial_List").datalist("loadData", result);
               });
        },
        moreHomeData: function (n) {
            switch (n) {
                case 1:
                    $.menu.loadUrl("/Secure/SecureSchoolReport");
                    break;
                case 2:
                    $.menu.loadUrl("/Routine/RoutineStudentCredential");
                    break;
                case 3:
                    $.menu.loadUrl("/Routine/RoutineTeacherCredential");
                    break;
                case 4:
                    //$.menu.loadUrl("/Routine/RoutineInspectorMaterial");
                    $.menu.loadUrl("/Star/StarSchoolReport");
                    break;
            }
        },
        dbclickStartSchoolTarget: function (o) {
            var id = $(o).find("span[name='ID']").text();
            $.menu.loadUrl("/Star/StarSchoolReport?tid=" + id);
        }
    };
    $(function () {
        switch ($("#homeRoleID").val()) {
            case "6":
                $.Home.loadHomeStartSchoolReport();
                $.Home.loadHomeSecureSchoolReport();
                $.Home.loadHomeRoutineStudentCredential();
                $.Home.loadHomeRoutineTeacherCredential();
                $.Home.loadHomeRoutineInspectorMaterial();
                break;
            case "8":
                $.Home.loadHomeSecureSchoolReport();
                break;
        }
    });
})(jQuery);