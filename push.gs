var ss=SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1HnPhhCEN3jHKUCjaGUJBd9FeOEkpsW5SOi6z4W_0qMU/edit#gid=2054347464");
var sheet = ss.getSheetByName("1");

function doPost(e)
{
  var id=sheet.getLastRow();
  var itemName=e.parameter.personName;
  var itemActivity=e.parameter.personActivity;
  var itemData=e.parameter.activityData;
  
  sheet.appendRow([id,itemName,itemActivity,itemData]);
  return ContentService.createTextOutput("Success").setMimeType(ContentService.MimeType.TEXT);
}

function doGet(e)
{
 var records={};
  var rows = sheet.getRange(2, 1, sheet.getLastRow() - 1,sheet.getLastColumn()).getValues();
      data = [];
  for (var r = 0, l = rows.length; r < l; r++)
  {
    var row = rows[r], record  = {};
    record['personName'] = row[1];
    record['personActivity']=row[2];
    record['activityData']=row[3];
    
    data.push(record);
    }
  
  records.fitList = data;
  var result=JSON.stringify(records);
  return ContentService.createTextOutput(result).setMimeType(ContentService.MimeType.JSON);
 }
