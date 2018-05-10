<%
    ui.includeJavascript("accessmonitor", "moment.min.js")
    ui.includeJavascript("accessmonitor", "analytics.js")
    ui.includeJavascript("accessmonitor", "Chart.js")
    ui.includeJavascript("accessmonitor", "utils.js")
    ui.includeJavascript("accessmonitor", "date_slider.js")
%>
<script type="text/javascript">

jq(function() { 

     jq('#tableList').dataTable({
            "aaSorting": [],
            "sPaginationType": "full_numbers",
            "bPaginate": true,
            "bAutoWidth": false,
            "bLengthChange": true,
            "bSort": true,
            "bJQueryUI": true
        });

    jq("#getUsers").click(function(){
       jq.getJSON('${ ui.actionLink("getChartData") }',
           {
           start: "2018-05-01",
           end: "2018-05-02"
            })
       .error(function(xhr, status, err) {
            alert('AJAX error ' + err);
        })
        .success(function(users) {
            jq('#userTable').dataTable({
                "aaSorting": [],
                "sPaginationType": "full_numbers",
                "bPaginate": true,
                "bAutoWidth": false,
                "bLengthChange": true,
                "bSort": true,
                "bJQueryUI": true
            });
            var userTable = jq('#userTable').DataTable();
            for (index in users) {
                var user = users[index];
                userTable.fnAddData( [
                    user.id,
                    user.accessingUserId
                ]) 
            };
            document.getElementById("userList").style.display = "block";
            })
    })
});

</script>

<canvas id="myChart"></canvas>
<script>
var data = [{
      x: new moment().add(-10, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-8, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-6, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-4, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-2, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-0, "d"),
      y: Math.random() * 100
    },
  ];
var data1 = [{
      x: new moment().add(-11, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-7, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-4, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-2, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-0, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(1, "d"),
      y: Math.random() * 100
    },
  ];    
var data2 = [{
      x: new moment().add(-10, "m"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-8, "m"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-6, "m"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-4, "m"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-2, "m"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-0, "m"),
      y: Math.random() * 100
    },
  ];  
function sliderDo(s,e) {
    
    if(s==e){
        chart.data.datasets[0].data = data2;
        chart.options.scales = {
            xAxes: [{
            type: 'time',
            time: {
                unit: 'minute',
            }
          }],
        }
    } else {
        if(chart.data.datasets[0].data == data) {
            chart.data.datasets[0].data = data1;
        }else {
            chart.data.datasets[0].data = data;
        }
        
        chart.options.scales = {
            xAxes: [{
            type: 'time',
            time: {
                unit: 'day',
            }
          }],
        }
    }
    chart.update();
}

//function newDate(days) {
//    if(oneDay){
//        return moment().add(days, 'm');
//    } else {
//        return moment().add(days, 'd');
//    }
//}

var config = {
  type: 'bar',
  data: {
      datasets: [{
        data: data,
        label: "Number of Access",
//        borderColor: "#3e95cd",
//        fill: false
      }]
    },
//  data: {
//    labels: [newDate(-4), newDate(-3), newDate(2), newDate(3), newDate(4), newDate(5), newDate(6)],
//    datasets: [{
//      label: "My First dataset",
//      data: [1, 3, 4, 2, 5, 4, 2],
//    }]
//  },
  options: {
    scales: {
      xAxes: [{
        type: 'time',
        distribution: 'linear',
        time: {
            unit: 'day',
        }
      }],
    },
  }
};

var ctx = document.getElementById("myChart").getContext("2d");
var chart = new Chart(ctx, config);
    
        
</script>
    
<div>
    <br>
<div style="border: 0; width: 1000px">
  <label for="amount">Date range:</label> <input type="text" id="amount" style="border: 0; color: #000000; font-weight: bold; display: inline;white-space:nowrap;" size="50"/>
</div>
 
<div id="slider-range"></div>

<br>
<div class="Table" id="userList" hidden>
<table id="userTable"  border="1" class="display" cellspacing="0" width="50%">
<thead>
  <tr>
        <th>Given Name</th>
        <th>Family Name</th>
  </tr>
</thead>
<tbody >
</tbody>
</table>

</div>

<button id="getUsers" type="button">Detail</button>
</div>



<!--<div>

<h1>Tables for $user.givenName $user.familyName</h1>

<div class="Table" id="accessMonitor">
<h2>The Table</h2>
<table id="tableList"  border="1" class="display" cellspacing="0" width="50%">
<thead>
  <tr>
        <th>Table Id</th>
        <th>Entry Date</th>
        <th>Info</th>
  </tr>
</thead>
<tbody >
  <% if (tableitems) { %>
     <% tableitems.each { %>
      <tr>
            <td>${ ui.format(it.id)}</td>
            <td>${ ui.format(it.entryDate)}</td>
            <td>${ ui.format(it.info)}</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td >&nbsp;</td>
    <td >&nbsp;</td>
    <td >&nbsp;</td>
  </tr>
  <% } %>
</tbody>
</table>

</div>
<div>
<form id="createAccessMonitorItemForm" method="post">

    Input Table Item Information: <input id="postType" type="text" name="info"> <br>
    <input type="submit" value="Submit">
</form>
</div>


</div>-->





