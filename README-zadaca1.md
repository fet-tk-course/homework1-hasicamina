# Zadaća 1A

## Opis projekta
Cilj ovog projekta bio je primijeniti naučene koncepte u Kotlinu kroz modeliranje jednostavnog sistema za organizaciju programerskih meetup događaja. Zadatak obuhvata:
- definisanje interfejsa i hijerarhije klasa,
- rad sa kolekcijama i funkcijama višeg reda,
- izračun i analizu podataka nad listom programera.

## Klasa – Programer
Predstavlja osnovnu klasu koja implementira interfejs Programeri i opisuje zajedničke osobine svih programera.
Sadrži:
- ime i prezime,
- godine iskustva,
- oznaku zemlje,
- skup programskih jezika.

### Provjere ispravnosti:
- ime ne smije biti prazno,
- godine iskustva moraju biti pozitivan broj,
- lista jezika ne smije biti prazna.

Nazivi jezika se normalizuju u mala slova radi konzistentnosti.

## Izvedene klase
- **BackendProgramer** – dodatno sadrži podatak o backend frameworku (npr. Spring Boot).
- **FrontendProgramer** – dodatno sadrži podatak o frontend frameworku (npr. React, Vue.js).

Obje klase nasljeđuju Programer i proširuju ga dodatnim informacijama.

## Funkcionalnosti programa
1. **Prebrojavanje jezika koje koriste programeri**
   - Prva verzija koristi funkcionalni pristup (`groupingBy`, `eachCount`).
   - Druga verzija koristi ručni prolazak kroz listu i brojanje pojavljivanja.

2. **Prosječno iskustvo po jeziku**
   - Računa prosječne godine iskustva za sve programere koji koriste određeni jezik.
   - Implementirano funkcionalno i ručno (bez grupisanja).

3. **Filtriranje po frameworku**
   - Izdvaja programere koji koriste zadani framework, bez obzira na tip (backend ili frontend).

4. **Ispis informacija o programeru**
   - Ispisuje ime i prezime, tip programera, jezike koje koristi i framework.
   - Formatiran i dosljedan prikaz, npr.:  
     `Amila — Backend developer — jezici: kotlin, java — framework: Spring Boot`

5. **Provjere ispravnosti**
   - U glavnom programu dodane su najmanje tri check provjere:
     - da lista sadrži najmanje 5 programera,
     - da postoji barem jedan backend programer,
     - da postoji barem jedan frontend programer.

## Kako pokrenuti program
1. Otvoriti projekt u bilo kojem Kotlin IDE-u (npr. IntelliJ IDEA).
2. Pokrenuti funkciju `main()`.
3. Program će ispisati:
   - Detalje svih programera,
   - Prebrojavanje jezika (funkcionalno i ručno),
   - Prosječno iskustvo po jeziku (funkcionalno i ručno),
   - Filtrirane liste po frameworku.

## Uporedna analiza pristupa
| Funkcija | Funkcionalni pristup | Ručni pristup |
|----------|-------------------|---------------|
| Prebrojavanje jezika | `flatMap + groupingBy + eachCount` | Petlja + `mutableMap` |
| Prosječno iskustvo po jeziku | `flatMap + groupBy + mapValues` | Petlja + sumiranje + brojač |

## Primjeri rezultata programa
**Provjere ispravnosti:** ✓  
Sve provjere su prošle uspješno!

**Lista programera:**
- Amila Hodžić — Backend programer — jezici: java, kotlin, python — framework: Spring Boot
- Emir Softić — Backend programer — jezici: java, kotlin — framework: Ktor
- Sara Kovač — Frontend programer — jezici: javascript, typescript — framework: React
- Marko Petrović — Frontend programer — jezici: css, html, javascript — framework: Vue.js
- Anna Schmidt — Backend programer — jezici: go, python — framework: Django
- Lena Müller — Frontend programer — jezici: javascript, typescript — framework: Angular

**Prebrojavanje jezika (funkcionalno):**
- kotlin -> 2
- java -> 2
- python -> 2
- javascript -> 3
- typescript -> 2
- html -> 1
- css -> 1
- go -> 1

**Prebrojavanje jezika (ručno):**
- kotlin -> 2
- java -> 2
- python -> 2
- javascript -> 3
- typescript -> 2
- html -> 1
- css -> 1
- go -> 1

**Prosječno iskustvo po jeziku (funkcionalno):**
- kotlin -> 4.00
- java -> 4.00
- python -> 6.00
- javascript -> 4.00
- typescript -> 5.00
- html -> 2.00
- css -> 2.00
- go -> 7.00

**Prosječno iskustvo po jeziku (ručno):**
- kotlin -> 4.00
- java -> 4.00
- python -> 6.00
- javascript -> 4.00
- typescript -> 5.00
- html -> 2.00
- css -> 2.00
- go -> 7.00

**Programeri koji koriste Spring Boot:**
- Amila Hodžić — Backend programer — jezici: java, kotlin, python — framework: Spring Boot

**Programeri koji koriste React:**
- Sara Kovač — Frontend programer — jezici: javascript, typescript — framework: React

## Upotreba AI alata
Tokom izrade zadatka korišten je ChatGPT alat u sljedeće svrhe:
- Objašnjenje funkcionalnog pristupa u Kotlinu i korištenje funkcija višeg reda (`groupingBy`, `mapValues`, `flatMap`).
- Optimizacija ručnih rješenja kako bi se smanjio broj eksplicitnih petlji i poboljšala čitljivost koda.
- Provjera sintakse, validacija podataka i formatiranje ispisa radi konzistentnosti i pravilne strukture programa, te pomoć oko grešaka zbog kojih nije bilo moguće pokretanje programa.
- Pomoć pri kreiranju liste programera.

> AI alat nije korišten za direktno generisanje kompletnog rješenja, već kao pomoćni alat za razumijevanje i provjeru tačnosti postojećeg koda.

