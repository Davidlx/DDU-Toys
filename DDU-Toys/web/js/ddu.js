/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    
    checkSelection();
    
    $("#toySelection").change(function(){
       checkSelection();
    });
});

function checkSelection(){
        if ($("#toySelection").val()==0) {
            $("#ToyInput").prop('disabled', false);
            $("#descInput").prop('disabled', false);
            $("#priceInput").prop('disabled', false);
            $("#ageInput").prop('disabled', false);
            $("#picInput").prop('disabled', false);
            $("#sexSelection").prop('disabled', false);
            $("#cateSelection").prop('disabled', false);
            
            updateInputMessage("");
            
        }else{
            $("#ToyInput").prop('disabled', true);
            $("#descInput").prop('disabled', true);
            $("#priceInput").prop('disabled', true);
            $("#ageInput").prop('disabled', true);
            $("#picInput").prop('disabled', true);
            $("#sexSelection").prop('disabled', true);
            $("#cateSelection").prop('disabled', true);
            
            updateInputMessage("Specified by the toy");
            
           
        }
}

function updateInputMessage(msg){
     $("#ToyInput").val(msg);
     $("#descInput").val(msg);
     $("#priceInput").val(msg);
     $("#ageInput").val(msg);
     $("#picInput").val(msg);
     $("#sexSelection").val(msg);
     $("#cateSelection").val(msg);
}
