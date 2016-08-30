// JavaScript
<script type="text/javascript">
<!--
function WasedaFestival(event){
  event.preventDefault();
  var res = event.target.result;
  var d = res.split('\n');
  var jsonArray = csv2json(d);
}
 
function csv2json(csvArray){
  var jsonArray = [];
  var items = csvArray[0].split(',');
  for (var i = 1; i < csvArray.length; i++) {
    var a_line = new Object();
    var csvArrayD = csvArray[i].split(',');
    for (var j = 0; j < items.length; j++) {
      a_line[items[j]] = csvArrayD[j];
    }
    jsonArray.push(a_line);
  }
  return jsonArray;
}
// -->
</script>

