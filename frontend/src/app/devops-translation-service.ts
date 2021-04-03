import {Injectable} from "@angular/core";
import {TranslationService} from "path-framework/app/path-framework/service/translation.service";

@Injectable()
export class DevOpsTranslationService extends TranslationService {

    private myTranslations = this.createTranslationMap(this.getDevOpsTranslations());

    public getSupportedLanguageCodes(): string[] {
        return ["en", "de", "it", "nl"];
    }

    protected getTranslation(key: string): string {
        // prefer custom translations
        if (this.myTranslations.get(key) == null) {
            return super.getTranslation(key);
        }
        return this.myTranslations.get(key);
    }

    private getDevOpsTranslations() {
        const languageCode: string = this.getUserLanguage();

        // put additional application translations here
        // if (languageCode === "de") { TODO add language support
        return {
            "Admin": "Admin",
            "AddPermissionRole": "Rolle hinzufügen",
            "BackButtonElement": "Zurück-Button",
            "Color": "Farbe",
            "Continue": "Weiter",
            "CurrentJob": "Aktuell tätig als",
            "Digits": "Anzahl Nachkommastellen",
            "Description": "Beschreibung",
            "DuplicateDataError": "Datensatz ist bereits vorhanden",
            "EditUser": "Benutzer bearbeiten",
            "EditInteractionStep": "Schritt bearbeiten",
            "EditFormRadioButtonListElement": "Radio-Button bearbeiten",
            "EditFormFileUploadElement": "Datei-Upload bearbeiten",
            "EMail": "E-Mail",
            "ExportKey": "Export-Schlüssel",
            "FamilyName": "Nachname",
            "Form": "Formular",
            "FormDateFieldElement": "Datums-Eingabefeld",
            "FormFileUploadElement": "Datei-Upload",
            "FormFileType": "Datei-Typ",
            "FormNumberFieldElement": "Zahlen-Eingabefeld",
            "FormRadioButtonListElement": "Radio-Buttons Eingabefelder",
            "FormRadioButtonValue": "Radio-Button Wert",
            "FormRequiredFieldsErrorMessage": "Nicht alle benötigten Felder wurden ausgefüllt. Bitte prüfen Sie die Eingaben.",
            "FormTextFieldElement": "Text-Eingabefeld",
            "FirstInteractionStep": "Erster Interaktions-Schritt",
            "FirstName": "Vorname",
            "HighestEducation": "Höchster Schulabschluss",
            "Height": "Höhe",
            "Icon": "Icon",
            "InteractionElement": "Interaktions-Element",
            "InteractionElementValue": "Interaktions-Element Wert",
            "InteractionStep": "Interaktions-Schritt",
            "InteractionSteps": "Interaktions-Schritte",
            "MaximumValue": "Maximalwert",
            "MinimumValue": "Mindestwert",
            "Name": "Name",
            "NewPatient": "Neuer Patient",
            "NewUser": "Neuer Benutzer",
            "NewAddress": "Neue Adresse",
            "NewBackButtonElement": "Neuer Zurück-Button",
            "NewButtonElement": "Neuer Button",
            "NewButtonListElement": "Neue Button-Liste",
            "NewFormDateFieldElement": "Neues Datum-Eingabefeld",
            "NewFormFileUploadElement": "Neuer Datei-Upload",
            "NewFormFileType": "Neuer Dateityp",
            "NewFormNumberFieldElement": "Neues Zahlen-Eingabefeld",
            "NewFormRadioButtonListElement": "Neue Radio-Buttons",
            "NewFormRadioButtonValue": "Neuer Radio-Button Wert",
            "NewInteractionElement": "Neues Element",
            "NewFormTextFieldElement": "Neues Text-Eingabefeld",
            "NewTextElement": "Neues Text-Element",
            "NewTileListElement": "Neue Kachel-Liste",
            "NewStudyProgram": "Neuer Studiengang",
            "NextInteractionStep": "Nächster Interaktions-Schritt",
            "NotImplemented": "Funktion nicht implementiert",
            "NotImplementedMessage": "Diese Funktion ist im Prototyp noch nicht implementiert",
            "NotLoggedInErrorMessage": "Sie sind nicht angemeldet. Bitte laden Sie die Applikation neu und melden Sie sich neu an.",
            "NumberOfPersonsInHousehold": "Anzahl Personen im Haushalt",
            "OriginalJob": "Ursprünglicher Beruf",
            "Password": "Passwort",
            "Patient": "Patient",
            "Patients": "Patienten",
            "PermissionRole": "Rolle",
            "RepeatPassword": "Passwort wiederholen",
            "Required": "Muss-Feld",
            "SearchVisible": "Suche sichtbar",
            "SearchRequired": "Suche notwendig",
            "Summary": "Zusammenfassung",
            "SortOrder": "Reihenfolge",
            "StudyProgram": "Studiengang",
            "Text": "Text",
            "TextElement": "Text-Element",
            "Tooltip": "Tooltip",
            "User": "Benutzer",
            "UserPermissionRole": "Benutzerrolle",
            "Users": "Benutzer",
            "Value": "Wert",
            "ValueDescription": "Wert-Beschreibung",
            "Width": "Breite",
            // default translations, TODO remove when language support is added
            "Back": "Zurück",
            "Cancel": "Abbrechen",
            "Delete": "Löschen",
            "DeleteWarningQuestion": "Wollen Sie diesen Datensatz löschen?",
            "Detail": "Detail",
            "Logout": "Abmelden",
            "MainMenu": "Hauptmenü",
            "New": "Neu",
            "NotSignedIn": "Nicht angemeldet",
            "Ok": "OK",
            "Result": "Resultat",
            "Results": "Resultate",
            "Search": "Suche",
            "SearchInputLabel": "Suchbegriff",
            "SearchTextTooShort": "Suchbegriff zu kurz",
            "SignedInAs": "Angemeldet als",
            "Translation": "Übersetzung",
            "Translations": "Übersetzungen",
            "de": "Deutsch",
            "en": "Englisch"
        };
    }

}
