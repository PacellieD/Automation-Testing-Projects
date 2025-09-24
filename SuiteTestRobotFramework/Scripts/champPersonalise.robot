*** Settings ***
Documentation    Suite de test pour le champ personnalise d'un groupe
Library    SeleniumLibrary
Library    XML
Library    DataDriver    ../Ressources/Datapool/datapool.csv
Variables    ../Ressources/locators/locator.py
Resource    ../Ressources/Keywords/keyword.robot
Suite Setup    Login    ${vURL}/login     ${vBrowser}    ${vUserName}   ${vPassword}
Suite Teardown    Logout
Test Template    RQ5.8

*** Variables ***
${vURL}    http://localhost:8083
${vBrowser}    chrome
${vUserName}    6283658
${vPassword}    12345678

*** Test Cases ***
CT1
    [Template]    RQ5.8
    ${vtxt_Nom_Champ}    ${vtxt_Description_Champ}    ${vtxt_Longeur_mini}    ${vtxt_Longeur_maxi}

*** Keywords ***
RQ5.8
    [Arguments]    ${vtxt_Nom_Champ}    ${vtxt_Description_Champ}    ${vtxt_Longeur_mini}    ${vtxt_Longeur_maxi}
    Creation Champ Personnalise    ${vtxt_Nom_Champ}    ${vtxt_Description_Champ}    ${vtxt_Longeur_mini}    ${vtxt_Longeur_maxi}
    Verification de la creation du champ Personnalise    ${vtxt_Nom_Champ}    
    Suppression Champ Personnalise
       

Creation Champ personnalise
    [Arguments]    ${vtxt_Nom_Champ}    ${vtxt_Description_Champ}    ${vtxt_Longeur_mini}    ${vtxt_Longeur_maxi}
    Click Element    ${lnk_Administration}
    Click Element    ${lnk_Champ_personnalise}
    Click Element    ${lnk_New_Champ_Personnalise}
    Click Element    ${btn_radio_Groupe}    
    Click Element    ${btn_Suivant}  
    Click Element    ${select_Format}    
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

