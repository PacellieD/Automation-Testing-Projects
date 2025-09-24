*** Settings ***
Documentation    Suite de test pour le champ personnalise d'un groupe
Library    SeleniumLibrary
Library    XML
Variables    ../Locators/locator.py

*** Variables ***

*** Keywords ***
Login
    [Arguments]    ${vURL}    ${vBrowser}    ${vUserName}    ${vPassword}      
    Open Browser    ${vURL}    ${vBrowser}
    Maximize Browser Window 
    Input Text    ${txt_Username}    ${vUserName} 
    Input Text    ${txt_Password}   ${vPassword}
    Click Element    ${btn_Subtmit} 
    Wait Until Element Is Visible    ${lbl_User}
    Element Text Should Be    ${lbl_User}    ${vUserName}

Creation Champ personnalise
    [Arguments]    ${vselect_Format}    ${vtxt_Nom_Champ}    ${vtxt_Description_Champ}    ${vtxt_Longeur_mini}    ${vtxt_Longeur_maxi}
    Click Element    ${lnk_Administration}
    Click Element    ${lnk_Champ_personnalise}
    Click Element    ${lnk_New_Champ_Personnalise}
    Click Element    ${btn_radio_Groupe}    
    Click Element    ${btn_Suivant}  
    Select From List By Label    ${select_Format}    ${vselect_Format}
    Input Text    ${txt_Nom_Champ}    ${vtxt_Nom_Champ}
    Input Text    ${txt_Description_Champ}    ${vtxt_Description_Champ} 
    Input Text    ${txt_Longeur_mini}    ${vtxt_Longeur_mini}
    Input Text    ${txt_Longeur_maxi}    ${vtxt_Longeur_maxi} 
    Click Element    ${Btn_Creer}

Verification de la creation du champ Personnalise
     [Arguments]    ${vtxt_Nom_Champ}    
     Wait Until Element Is Visible    ${lbl_Message}
     Element Text Should Be    ${lbl_Message}    Création effectuée avec succès.
     Click Element    ${lnk_Groupe}
     Click Element    ${lnk_Nouveau_Groupe}
     Wait Until Element Is Visible    ${lbl_creation_Champ}
     Element Text Should Be    ${lbl_creation_Champ}    ${vtxt_Nom_Champ}

Suppression Champ personnalise
    [Arguments]
    Click Element    ${lnk_Administration}
    Click Element    ${lnk_Champ_personnalise}
    Click Element    ${btn_Groupes}
    Click Element    ${lnk_Supprimer}
    Handle Alert    Accept
    Wait Until Element Is Visible    ${lbl_Message}
    Element Text Should Be    ${lbl_Message}    Suppression effectuée avec succès.


Logout
    Click Element    ${lnk_Deconnexion}
    Close Browser