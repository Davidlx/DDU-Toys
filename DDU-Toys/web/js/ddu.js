/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    $("#toySelection").change(function(){
        console.log($("#toySelection").val());
        if ($("#toySelection").val()==0) {
            $(".ToyInput").hide();
            console.log("in zero");
        }else{
            $("#ToyInput").show();
            console.log("not in zero");
        }
    });
});