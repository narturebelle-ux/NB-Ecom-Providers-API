# NB-Ecom-Providers-API

Spring Boot 3.3 / Java 21 service for care professional onboarding: the application, staff review, post-approval setup and the professional's dashboard data. It follows the same patterns as NB-Ecom-API. The HTTP contract is in `UI_INTEGRATION_GUIDE.md`.

## How it fits with NB-Ecom-API

Both services use the same Supabase database and the same Firebase project.

- **Own schema:** this service's tables are in `provider_onboarding`, with their own Flyway history. It never migrates `public`; NB-Ecom-API alone does.
- **Shared tables:** classes in `model/shared` map only the columns needed from NB-Ecom-API's tables. If NB-Ecom-API renames or drops one of those columns, this service stops at startup (`ddl-auto: validate`), so check `model/shared` when changing them.
- **Staying hidden:** on approval a `public.providers` row is created with status `UNDER_REVIEW`. NB-Ecom-API lists and books only `VERIFIED` and `ACTIVE`, so mothers can't see or book the professional until go-live sets `ACTIVE` and adds their role.
- **Sign-in:** the UI calls NB-Ecom-API's `POST /auth/firebase` first, which creates the `users` row this service reads.

## Running locally

Copy the values in `.env.example` into your environment, then:

```
./gradlew bootRun      # http://localhost:8086, Swagger at /swagger-ui.html
./gradlew test         # H2 only; no credentials needed
```

Set `REQUIRE_PHONE_VERIFICATION=false` if you are testing without Firebase phone sign-in.

## Before production

- **Settings:** `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `FIREBASE_SERVICE_ACCOUNT_BASE64`, `DATA_ENCRYPTION_KEY` and `RESEND_API_KEY`, plus `SPRING_PROFILES_ACTIVE=prod`. Back up `DATA_ENCRYPTION_KEY`: without it, stored PAN and bank numbers can't be read.
- **Signing is off in prod** until `AGREEMENT_SIGNING_ENABLED=true`. Turn it on only when `public.payout_rates` holds the real fees and `AgreementCatalog` and `TrainingCatalog` hold the final legal text. Also set `AgreementCatalog.IS_DRAFT` to false then.
- **Malware scanning is a no-op** (`MalwareScanner`). Connect a real scanner before staff open files from the public.
- **Test deposits are manual:** Operations sends one and records the result. No payment service is connected.
- **Daily rules** run at 03:30 IST and whenever the service starts. If the host sleeps and nothing wakes it, they wait for the next start, so add a scheduled ping once this is deployed.

## Branching

The same workflow as NB-Ecom-API: see its README.
