/*
function showStudents(data) {
console.log(data);
$.each(data,function(data){
            html += "<tr>";
            html += "<td>"+this.name+"</td>";
            html += "<td>"+this.indexno+"</td>";
            html += "<td>"+this.year+"</td>";
            html += "<td>"+this.yearofbirth+"</td>";
            html += "</tr>";
        });

}
*/


function getTableData(table) {
    var data = [];
    table.find('tr').each(function (rowIndex, r) {
        var cols = [];
        $(this).find('th,td').each(function (colIndex, c) {
            cols.push(c.textContent);
        });
        data.push(cols);
    });
    return data;
}











