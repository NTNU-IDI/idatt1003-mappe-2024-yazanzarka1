Generert fra Del 1,2 og 3 ved bruk av CHATGPT

### **1. Forberedende Oppgaver**
- [x] **Prosjektoppsett**: Bekreft at prosjektet er riktig satt opp på datamaskinen din, helst i et kjent sted.
- [x] **Versjonskontroll**: Sørg for at prosjektet er lagt til på GitHub/GitLab med versjonskontroll via GitHub Classroom.
- [x] **Del 1 Gjennomgang**:
    - [x] Entitetsklassen for `FoodItem` er fullstendig implementert med riktige felt, konstruktører, tilgangsmetoder og mutatorer.
    - [x] JavaDoc-dokumentasjon er gitt for klassen og alle offentlige metoder.
    - [x] Alle CheckStyle-problemer er løst.
    - [x] Enhetstester er laget for entitetsklassen, med både positive og negative tester.
- [x] **Tilbakemeldingsmøte**: Hold et tilbakemeldingsmøte med lærer eller læringsassistent.

---

### **2. Implementering av Registreringssystem for Matvarer (Kjøleskap/Spiskammer)**
- [x] **Registreringsklasse**:
    - [x] Navngi klassen som representerer registreringen.
    - [x] Velg en passende Java SDK-samling (f.eks. `ArrayList`, `HashMap`, etc.) for å lagre matvarer og begrunn valget i rapporten.

#### **Nødvendige Metoder**:
- [x] **Legg til Matvare**: Metode for å legge til en matvare i registeret. Hvis varen eksisterer, oppdater mengden.
- [x] **Søk Matvare**: Metode for å finne en matvare etter navn.
- [x] **Fjern Matvare Mengde**: Metode for å redusere mengden av en matvare. Hvis den når null, fjern varen.
- [x] **List Utgåtte Varer**: Metode for å liste alle varer som går ut før en gitt dato.
- [x] **Sortert Liste over Varer**: Metode for å returnere alle varer sortert alfabetisk etter navn.

---

### **3. Enhetstester for Registreringsklassen**
- [x] **Enhetstester**: Lag en enhetstestklasse for registreringsklassen.
    - [x] Skriv både positive og negative tester for hver metode.
    - [x] Sørg for god testdekning (100% er ikke nødvendig, men streb etter høy kvalitet).
    - [x] Sjekk at alle tester kjøres uten feil.

---

### **4. Oppdateringer til Applikasjonsklassen**
- [x] **init() Metode**:
    - [x] Sett opp initiale applikasjonskonfigurasjoner, som å instansiere registreringsklassen.
- [x] **start() Metode**:
    - [x] Legg til noen matvarer i registeret (test grunnleggende funksjonalitet).
    - [x] Lag en metode for å skrive ut matvarer fra registeret (forbered for senere brukergrensesnitt).

---

### **5. Implementering av Oppskrifts- og Kokebokklasser**
#### **Oppskriftsklasse**
- [x] **Attributter**:
    - [x] Oppskriftsnavn.
    - [x] Kort beskrivelse av retten.
    - [x] Instruksjoner (tekstfelt).
    - [x] Liste over nødvendige matvarer/ingredienser.
    - [x] Antall porsjoner (standard 4).
    - [x] Velg en passende datastruktur for lagring av ingredienser (f.eks. `ArrayList`, `HashMap`).

#### **Kokebokklasse**
- [x] **Attributter og Metoder**:
    - [x] Implementer rimelige metoder for å oppfylle kravene til oppskriftsstyring.
    - [x] Begrunn valg av datastruktur i rapporten.

#### **Brukerkrav**:
- [x] **Lag en Oppskrift**: Inkluder navn, beskrivelse, instruksjoner og ingredienser (med mengder).
- [x] **Sjekk Ingredienser**: Verifiser om kjøleskapet/spiskammeret har nok ingredienser for en valgt oppskrift.
- [x] **Legg til Oppskrift i Kokeboken**: Lagre oppskrifter i kokeboken for senere tilgang.
- [x] **Foreslå Oppskrifter**: Eventuelt, gi en liste over oppskrifter som kan lages med de nåværende ingrediensene.

---

### **6. Enhetstester for Oppskrifts- og Kokebokklasser**
- [x] **Oppskriftsklasse Tester**: Lag positive og negative enhetstester for all funksjonalitet.
- [x] **Kokebokklasse Tester**: Sørg for god testdekning for kokeboken, dekk alle forventede scenarioer.

---

### **7. Oppdateringer til Applikasjonsklassen**
- [x] **init() Metode**:
    - [x] Initialiser instanser av oppskrifts- og kokebokklassene.
- [x] **start() Metode**:
    - [x] Legg til noen eksempeloppskrifter i kokeboken og test noen funksjoner.

---

### **8. Siste Sjekk**
- [x] **Versjonskontroll**:
    - [x] Sjekk at alle commits er pushet til GitHub/GitLab.
    - [x] Gjennomgå commit-meldingene for klarhet og korthet.
- [x] **Enhetstester**:
    - [x] Sørg for at alle tester har beskrivende navn og følger Arrange-Act-Assert-mønsteret.
    - [x] Test både positive og negative scenarier.
    - [x] Verifiser at alle tester passerer uten feil.
- [x] **Klasseimplementering**:
    - [x] Verifiser at kjøleskap/spiskammer, oppskrift og kokebokklasser oppfyller oppgavebeskrivelsens spesifikasjoner.
- [x] **Kodekvalitet**:
    - [x] Sørg for JavaDoc-dokumentasjon for klasser og metoder.
    - [x] Løs eventuelle CheckStyle- og SonarLint-problemer.
    - [x] Sørg for beskrivende navn på variabler, metoder og klasser.
- [x] **Prosjektstruktur**:
    - [x] Gruppér klassene i logiske pakker.

