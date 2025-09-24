#login
txt_Username="//*[@id='username']"
txt_Password="//*[@id='password']"
btn_Subtmit="//*[@id='login-submit']"
lbl_User="//*[@id='loggedas']/a"

#Creation_Champ_personnalise
lnk_Administration="//a[@class='administration']"
lnk_Champ_personnalise="//a[@class='icon icon-custom-fields custom-fields']"
lnk_New_Champ_Personnalise="//div/a[@class='icon icon-add']"
btn_radio_Groupe="//*[@id='type_GroupCustomField']"
btn_Suivant="//*[@id='content']/form/p/input"
select_Format="//select[@id='custom_field_field_format']/option[text()='Texte']"
txt_Nom_Champ="//*[@id='custom_field_name']"
txt_Description_Champ="//*[@id='custom_field_description']"
txt_Longeur_mini="//*[@id='custom_field_min_length']"
txt_Longeur_maxi="//*[@id='custom_field_max_length']"
Btn_Creer="//div[@class='splitcontentleft']/p/input[@name='commit']"

#verification_Creation_Champ_personnalise
lbl_Message="//*[@id='flash_notice']"
lnk_Groupe="//a[@class='icon icon-group groups']"
lnk_Nouveau_Groupe="//a[@class='icon icon-add']"
lbl_creation_Champ="//p/label/span[@class='field-description' and contains(text(), '6283658')]"

# Suppression_Champ_Personnalise
btn_Groupes="//*[@id='tab-GroupCustomField']"
lnk_Supprimer="//td/a[contains(text(), '6283658')]/../following-sibling::td/a[@data-method='delete']"
lbl_Message="//*[@id='flash_notice']"

#Deconnexion
lnk_Deconnexion="//a[@data-method='post']"