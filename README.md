# Dokumentation Tennerr applikation

Se applikationen köras på denna länk: 

Denna dokumentation berör följande punkter:

* [Syfte och projekt](#syfte-och-projekt)
* [Teknisk specifikation](#teknisk-specifikation)
* [Installationsguide](#installationsguide)

## Syfte och projekt

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
 

## Teknisk specifikation

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

## Installationsguide

### **Maven:**

För att kunna köra programmet behöver du ladda ner koden och sedan installera Maven i projektet. Om inte detta görs automatisk, (det beror lite på vilken kodeditor och version av kodeditor du kör) så kan du in IntellJ IDEA följa denna manual för att installera Maven i ditt projekt: 

**Gå till:**  
```
File > Settings > Build,Execution,Deployment> Build Tools > Maven > Importing .
Ändra VM options for importer till  -Didea.maven3.use.compat.resolver
```

### **Databas och databashanterare:**

**Ansluta:**
* Det allra enklaste är om du använder MAMP databashanterare då den inte kräver lösenord. Du kan ladda ner MAMP här: https://www.mamp.info/en/downloads/

* Porten i koden är satt till 3307 så ändra din MAMP port till detta. (Detta för att inte krocka med eventuellt befintlig databashanterare på datorn, då de annars kan krocka då de körs på samma port.)

* Vill du kan du använda egen befintlig databashanterare med lösenord och användrarnamn, så ändrar du från root till ditt eget användarnamn och lösenord inne i application.properties filen:

```
spring.datasource.url=jdbc:mysql://localhost:3307/tennerr?useSSL=false&serverTimezone=UTC

spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
```

**Setup:** 
* Skapa sedan en databas i din databashanterare som hetter tennerr.

 
