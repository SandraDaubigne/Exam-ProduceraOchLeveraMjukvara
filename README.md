## Dokumentation Tennerr applikation

Se applikationen köras på denna länk: 

Denna dokumentation berör följande punkter:

* [Syfte och projekt](#syfte-och-projekt)
* [Teknisk specifikation](#teknisk-specifikation)
* [Installationsguide](#installationsguide)

### Syfte och projekt

Syftet med detta projekt är att genom en kurs på Teknikhögskolan utbildning javautvecklare 2019-2021 i kursen “producera och leverera mjukvara” jobba mot kund i form av en grupp klasskamrater. 

Beställargruppen (kunden) levereras denna applikation av leverantörgruppen (vi) enligt beställargruppens önskan av applikation. 

Beställargruppens önskan av applikation är en app där arbetsgivare och arbetstagare ska kunna matchas för att få kontakt för olika jobbuppdrag. 

**Kravlista** har tagits fram och från detta har sedan use cases tagit fram och de är följande: 
* Användare ska kunna registrera sig som arbetare eller uppdragsgivare
* Användare ska kunna logga in
* Användare ska kunna ändra sin profil
* Inloggad användare ska se lista på alla jobb
* Uppdragsgivare ska kunna skapa jobbannons
* Uppdragsgivare ska se sina skapade jobb
 

### Teknisk specifikation

I denna applikation används följande: 

**Kodeditor:**
* IntelliJ IDEA

**Backend:**
* Spring Boot med Maven. 
* MySQL databas med Hibernate

**Frontend:** 
* Thymeleaf
* JavaScript
* HTML
* CSS

### Installationsguide

För att kunna köra programmet behöver du ladda ner Koden och sedan installera Maven i projektet om inte detta görs automatisk, det beror lite på vilket program du kör. Får du problem med detta följ denna manualen: 

**Gå till:**  
```
File > Settings > Build,Execution,Deployment> Build Tools > Maven > Importing .
Ändra VM options for importer till  -Didea.maven3.use.compat.resolver
```

**Databas och databashanterare:**

**Ansluta:**
* Det allra enklaste är om du har MAMP databas med root som användarnamn och root som lösenord, samt att du har satt porten i MAMP till 3307. (Detta för att inte krocka med befintlig mySQL workbench eller liknande, då de körs på samma portar. 

* Vill du kan du använda din egen mySQL workbench och då går du in på application.properties filen och byter ut ditt användarnamn och lösenord till det du har och till den port du använder.

**Setup:** 
* Skapa sedan en databas i din databashanterare som hetter tennerr

 
