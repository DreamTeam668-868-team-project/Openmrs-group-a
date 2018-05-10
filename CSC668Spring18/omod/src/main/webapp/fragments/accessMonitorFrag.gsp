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
       jq.getJSON('${ ui.actionLink("getUsers") }',
           {
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
                    user.givenName,
                    user.familyName
                ]) 
            };
            document.getElementById("userList").style.display = "block";
            })
    })
});

</script>

<canvas id="myChart"></canvas>
<script>
    function newDate(days) {
  return moment().add(days, 'd');
}

var config = {
  type: 'bar',
  data: {
    labels: [newDate(-4), newDate(-3), newDate(2), newDate(3), newDate(4), newDate(5), newDate(6)],
    datasets: [{
      label: "My First dataset",
      data: [1, 3, 4, 2, 5, 4, 2],
    }]
  },
  options: {
    scales: {
      xAxes: [{
        type: 'time',
        time: {
          displayFormats: {
            'millisecond': 'mm DD ',
            'second': 'MMM DD',
            'minute': 'MMM DD',
            'hour': 'MMM DD',
            'day': 'MMM DD',
            'week': 'MMM DD',
            'month': 'MMM DD',
            'quarter': 'MMM DD',
            'year': 'MMM DD',
          }
        }
      }],
    },
  }
};

var ctx = document.getElementById("myChart").getContext("2d");
new Chart(ctx, config);

</script>
    
<div>
    <br>
<div style="border: 0; width: 1000px">
  <label for="amount">Date range:</label> <input type="text" id="amount" style="border: 0; color: #f6931f; display: inline;white-space:nowrap;" size="100"/>
</div>
 
<div id="slider-range"></div>


<div class="Table" id="userList" hidden>
<h2>The Users</h2>
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

<button id="getUsers" type="button">Get All Users</button>
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





