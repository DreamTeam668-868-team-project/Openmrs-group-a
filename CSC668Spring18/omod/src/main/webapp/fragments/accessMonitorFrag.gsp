<%
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




<div>
    <br>
<p>
  <label for="amount">Date range:</label> <input type="text" id="amount" style="border: 0; color: #f6931f; font-weight: bold;" size="100"/>
</p>
 
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





