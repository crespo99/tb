function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}


function f()
{
    if(document.getElementById("j_idt19:j_idt158:fo:console:0").checked)
    {
        PF('dlg').show();
        PF('dlg2').hide();
        //alert(document.getElementById('j_idt46:j_idt176:fo:poste'));
    }
    else if(document.getElementById("j_idt19:j_idt158:fo:console:1").checked)
    {
        PF('dlg2').show();
        PF('dlg').hide();
        //alert(document.getElementById('j_idt46:j_idt169:fo:poste').value);
    }
    else if(document.getElementById("j_idt19:j_idt158:fo:console:2").checked)
    {

        PF('dlg2').hide();
        PF('dlg').hide();
        /*var x=document.getElementById('j_idt19:j_idt149:fo:poste');
        x.innerHTML="";*/
    }
}
function annuler()
{
    PF('dlg2').hide();
    PF('dlg').hide();
}


