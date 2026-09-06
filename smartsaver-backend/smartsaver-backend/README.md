# SmartSaver — Backend

Spring Boot API for sparemål-appen.

## Kom i gang

1. Åpne mappen i IntelliJ IDEA (File → Open → velg `smartsaver-backend`)
2. La IntelliJ importere Gradle-prosjektet automatisk (kan ta 1-2 min første gang)
3. Kjør `SmartSaverApplication.java` (grønn play-knapp)
4. Bekreft at det fungerer: åpne http://localhost:8080/h2-console i nettleseren
   - JDBC URL: `jdbc:h2:mem:smartsaver`
   - Username: `sa`, passord: (tomt)

## Neste steg (steg 2 i planen)

Legg til entitetene i `src/main/java/com/dnb/smartsaver/model/`:
- `User.java`
- `Account.java`
- `SavingsGoal.java`

## Struktur

```
src/main/java/com/dnb/smartsaver/
├── controller/    ← REST-endepunkter
├── model/         ← JPA-entiteter
├── repository/    ← Spring Data-repositories
├── service/       ← forretningslogikk
└── config/        ← evt. konfigurasjon (sikkerhet, CORS, osv.)
```
