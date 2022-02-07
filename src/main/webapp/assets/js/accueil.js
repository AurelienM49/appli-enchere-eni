let radioAchat = document.getElementById('radioAchats');
let radioVente = document.getElementById('radioVentes');

let enchEnCours = document.getElementById('mesEncheresEnCours');
let enchOuverte = document.getElementById('encheresOuvertes');
let enchRemportees = document.getElementById('mesEncheresRemportees');

let ventesEnCours = document.getElementById('mesVentesEnCours');
let ventesNonDebutees = document.getElementById('ventesNonDebutees')
let ventesTerminees = document.getElementById('ventesTerminees')






enchEnCours.setAttribute("disabled", true)
enchEnCours.checked = false;
enchOuverte.setAttribute("disabled", true)
enchOuverte.checked = false;
enchRemportees.setAttribute("disabled", true);
enchRemportees.checked = false;




radioAchat.addEventListener('click', () => {
    ventesEnCours.setAttribute("disabled", true)
    ventesEnCours.checked = false;
    ventesNonDebutees.setAttribute("disabled", true)
    ventesNonDebutees.checked = false;
    ventesTerminees.setAttribute("disabled", true);
    ventesTerminees.checked = false;
    enchEnCours.removeAttribute("disabled", true)
    enchEnCours.checked = false;
    enchOuverte.removeAttribute("disabled", true)
    enchOuverte.checked = false;
    enchRemportees.removeAttribute("disabled", true)
    enchRemportees.checked = false;
})
radioVente.addEventListener('click', () => {
    enchEnCours.setAttribute("disabled", true)
    enchEnCours.checked = false;
    enchOuverte.setAttribute("disabled", true)
    enchOuverte.checked = false;
    enchRemportees.setAttribute("disabled", true);
    enchRemportees.checked = false;
    ventesEnCours.removeAttribute("disabled", true)
    ventesEnCours.checked = false;
    ventesNonDebutees.removeAttribute("disabled", true)
    ventesNonDebutees.checked = false;
    ventesTerminees.removeAttribute("disabled", true)
    ventesTerminees.checked = false;
})