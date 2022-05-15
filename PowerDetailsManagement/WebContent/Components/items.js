$(document).ready(function() {
    if ($('#alertSuccess').text().trim() == "") {
        $('#alertSuccess').hide();
    }

    $('#alertError').hide();
})

// SAVE
$(document).on("click","#btnSave", function(event) {
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // Form validation
    var status = validatePowerDetailsForm(); 
    if (status != true) 
    { 
        $("#alertError").text(status); 
        $("#alertError").show(); 
        return; 
    } 

    // If valid
    $("#formPowerDetails").submit(); 
})

// UPDATE
//to identify the update button we didn't use an id we used a class
$(document).on("click", ".btnUpdate", function(event) 
{ 
    $("#hidpdIDSave").val($(this).closest("tr").find('#hidpdIDUpdate').val()); 
    $("#pdCusName").val($(this).closest("tr").find('td:eq(0)').text()); 
    $("#pdAccNo").val($(this).closest("tr").find('td:eq(1)').text()); 
    $("#pdUnit").val($(this).closest("tr").find('td:eq(2)').text()); 
    $("#pdDate").val($(this).closest("tr").find('td:eq(3)').text());
    $("#pdPay").val($(this).closest("tr").find('td:eq(4)').text());  
}); 


// CLIENT-MODEL================================================================ 
function validatePowerDetailsForm() { 
    // CODE 
    if ($("#pdCusName").val().trim() == "") 
    { 
        return "Insert Item Code."; 
    } 
    
    // NAME 
    if ($("#pdAccNo").val().trim() == "") 
    { 
        return "Insert Item Name."; 
    } 
    
    // PRICE------------------------------- 
    if ($("#pdUnit").val().trim() == "") 
    { 
        return "Insert Item Price."; 
    } 
    
    // is numerical value 
    var tmpPrice = $("#pdUnit").val().trim(); 
    if (!$.isNumeric(tmpPrice)) 
    { 
        return "Insert a numerical value for Item Price."; 
    } 
    
    // convert to decimal price 
    $("#pdUnit").val(parseFloat(tmpPrice).toFixed(2)); 
    
    // DESCRIPTION------------------------ 
    if ($("#pdDate").val().trim() == "") 
    { 
        return "Insert Item Description."; 
    } 
    // pay
    if ($("#pdPay").val().trim() == "") 
    { 
        return "Insert Payment."; 
    } 
    
    
    return true; 
} 
 