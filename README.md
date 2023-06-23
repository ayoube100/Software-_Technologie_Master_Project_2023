# Fahrradshop Website - Meilenstein 1 

_PTI09420 -Projekt Softwaretechnologie SoSe23- Westsächsische Hoschule Zwickau_

**Projektteam-Mitglieder**     
- Amal Chaouite   
- Ayoub El Kendi  
- Christian Spitzer    

## Inhalt
- Domain Storytelling 
- Event Storming mit Bounded Context
- Context Map
- Event Modelling
- Core Domains
- Aggregates
- Specification By Example - Use Cases

## Domain Storytelling 
![Domain Storytelling für Fahrrad-Website - Meilenstein 1](https://media.github.fh-zwickau.de/user/96/files/a56772d6-8514-47c9-b0b8-c48e59e21c39)

## Event Storming mit Bounded Context

#### Warenkorb
![Warenkorb_Context](https://media.github.fh-zwickau.de/user/96/files/96a25675-e2ab-4d67-9b53-c3871bab2091)
#### Produkt-Katalog
![Podukt-Katalog_Context](https://media.github.fh-zwickau.de/user/96/files/e2e1b3ef-cfda-481f-996d-afdb02976c7a)
#### Bestellung
![Bestellung_Context](https://media.github.fh-zwickau.de/user/96/files/2caa352f-116d-4691-956b-0eb336212f6a)
#### Zahlung
![Zahlung_Context](https://media.github.fh-zwickau.de/user/96/files/6f99c64e-3d73-40cd-acbd-4372fd7ef617)
#### Logistik
![Logistik_Context](https://media.github.fh-zwickau.de/user/96/files/f14867b4-87cd-4f4f-bcfe-1382fdaf5a6e)
#### Lager
![Lager_Context](https://media.github.fh-zwickau.de/user/96/files/c7fb92fa-e7dd-43d4-9403-3bf576d377a3)
#### Lieferung
![Lieferung_Context](https://media.github.fh-zwickau.de/user/96/files/32da3382-968f-42ae-aa2d-2a8c2d30a9ae)
#### Rechnung
![Rechnung_Context](https://media.github.fh-zwickau.de/user/96/files/8a37ad9f-23fd-49f3-95dc-23c389b8a5a4)    

## Context Map
![Fahrrad_Website_Context_Map](https://cdn.discordapp.com/attachments/1103682125095501866/1107645302493691914/image.png)   

## Event Modelling
![EventModelling](https://media.github.fh-zwickau.de/user/96/files/07b5718f-6d55-43f7-ac2e-fae0fda78525)

## Core Domains
![CoreDomains](https://media.github.fh-zwickau.de/user/255/files/9f6b54ba-e152-4c5f-a50d-01fef0a2b023)

## Specification By Example - Use Cases

#### Szenario 1: Normal erfolgreich Durchlauf des Systems
![Sczenario1](https://media.github.fh-zwickau.de/user/96/files/86d9164e-050e-437c-9383-7541071de353)

#### Szenario 2: Artikel schon bezahlt, aber nach einer Störung das Fahrrad ist nicht mehr verfügbar
![Sczenario2](https://media.github.fh-zwickau.de/user/96/files/400d6e34-ecf7-45f4-ad6d-db1b303309d6)

#### Szenario 3: Die für die Registrierung verwendete E-Mail ist ungültig
![Sczenario3](https://media.github.fh-zwickau.de/user/96/files/ac41d3e1-dd00-45fa-bae0-8b85d5a358e8)

#### Szenario 4: Die vom Käufer eingegebenen Zahlungsdaten sind ungültig 
![Sczenario4](https://media.github.fh-zwickau.de/user/96/files/5451bfcf-9634-41f8-866a-bef3bd42a195)

#### Szenario 5: Das Produkt entsprach nicht den Erwartungen des Käufers, was zu einer Rückgabeaufforderung führte 
![Sczenario5](https://media.github.fh-zwickau.de/user/96/files/017fc8f7-9242-4eee-934c-68db07f543a9)

## BestellungSaga workflow
![Untitled-2023-06-22-2225 (2)](https://media.github.fh-zwickau.de/user/96/files/893361ca-6064-447c-bb45-582b393e740a)
