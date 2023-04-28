var ss=SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1HnPhhCEN3jHKUCjaGUJBd9FeOEkpsW5SOi6z4W_0qMU/edit#gid=0");
var sheet = ss.getSheetByName("Items");

function doPost(e){
  var date=new Date();
  var id="item"+sheet.getLastRow();
  var itemName=e.parameter.personName;
  var itemAuthor=e.parameter.personActivity;
  var itemPrice=e.parameter.activityData;
  var itemRating=e.parameter.bookRating;
  
  sheet.appendRow([date,id,itemName,itemAuthor,itemPrice,itemRating]);
  return ContentService.createTextOutput("Success").setMimeType(ContentService.MimeType.TEXT);

}

var ss=SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1HnPhhCEN3jHKUCjaGUJBd9FeOEkpsW5SOi6z4W_0qMU/edit#gid=452055235");
var sheet = ss.getSheetByName("Sorted");

function doGet(e){
 var records={};
  var rows = sheet.getRange(2, 1, sheet.getLastRow() - 1,sheet.getLastColumn()).getValues();
      data = [];
  for (var r = 0, l = rows.length; r < l; r++) {
    var row     = rows[r], record  = {};
    record['personName'] = row[2];
    record['personActivity']=row[3];
    record['activityData']=row[4];
    record['itemRating']=row[5];
    
    data.push(record);
    }
  
  records.bookList = data;
  var result=JSON.stringify(records);
  return ContentService.createTextOutput(result).setMimeType(ContentService.MimeType.JSON);
 }
