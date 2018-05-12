/*!
 * Chart.js
 * http://chartjs.org/
 * Version: 2.7.2
 *
 * Copyright 2018 Chart.js Contributors
 * Released under the MIT license
 * https://github.com/chartjs/Chart.js/blob/master/LICENSE.md
 */
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

jq(function() {
    jq( "#slider-range" ).slider({
      "range": true,
      "min": new Date('2015/01/01').getTime() / 1000,
      "max": new Date().getTime() / 1000,
      "step": 86400,
      "values": [ new Date('2017/04/01').getTime() / 1000, new Date('2017/05/01').getTime() / 1000 ],
      "slide": function( event, ui ) {
        jq( "#amount" ).val( formatDate(new Date(ui.values[ 0 ] *1000)) + " - " + formatDate(new Date(ui.values[ 1 ] *1000)) );
      },
      "stop": function(event, ui) {
//            ui.prop('disabled', true);
            jq( "#slider-range" ).slider({
                disabled: true
              });
              
            sliderDo(formatDate(new Date(ui.values[ 0 ] *1000)), formatDate(new Date(ui.values[ 1 ] *1000)));
        }
    });
    jq( "#amount" ).val( formatDate(new Date(jq( "#slider-range" ).slider( "values", 0 )*1000)) + " - " + formatDate(new Date(jq( "#slider-range" ).slider( "values", 1 )*1000)));
  });