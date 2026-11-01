# UI integration guide: care professional onboarding

Base URL: `/api/v1`. Send the same Firebase bearer token as NB-Ecom-API. Errors have the shape `{code, message}`; `VALIDATION_FAILED` adds `fields: {name: message}`.

## Status

`DRAFT` and `CHANGES_REQUESTED` are "Documents pending". `OPERATIONS_REVIEW` and `MEDICAL_REVIEW` are "Under review". Then `VERIFIED`, `ACTIVE`, `PAUSED`, `SUSPENDED`, or the closed states `REJECTED`, `WITHDRAWN`, `CLOSED`.

## Public

| Endpoint | Purpose |
|---|---|
| `GET /public/apply-info` | Professions, terms text and version, `draft` |
| `GET /public/providers/{providerId}/credentials` | Registration number, council, confirmed experience. 404 unless `ACTIVE` |

## Applicant (signed in)

| Endpoint | Purpose |
|---|---|
| `GET /provider-application/entry` | `hasApplication`, `status`, `motherAccount`, `blocked`, `reapplyAfter`, `emailVerified`, `phoneVerified` |
| `GET /provider-application/form?profession=` | Sections and questions. Show a question only when `showWhenKey`'s answer is in `showWhenValues` |
| `GET /provider-application` | The latest application, 404 if none. Sensitive values are never returned; `sensitiveSaved` lists those on file |
| `POST /provider-application` | `{profession, termsAccepted, verificationConsent}` |
| `PUT /provider-application/profession` | `{profession}`, draft only. Clears that profession's answers and documents |
| `PATCH /provider-application/answers` | `{answers: {key: value or [values]}}`. Partial autosave; a blank value clears the answer |
| `POST /provider-application/documents/{questionKey}` | Multipart `file`: PDF, JPG or PNG, up to 4 MB. Replaces any earlier file |
| `DELETE /provider-application/documents/{questionKey}` | Removes a file |
| `GET /provider-application/documents/{id}/file` | Downloads the applicant's own file |
| `POST /provider-application/submit` | 409 `APPLICATION_INCOMPLETE` with `missing: [{key, label, section}]` |
| `POST /provider-application/withdraw` | Any time before approval |

Other error codes: `MOTHER_ACCOUNT`, `ALREADY_PROFESSIONAL`, `APPLICATION_EXISTS`, `APPLICATION_BLOCKED`, `REAPPLY_NOT_YET` (with `reapplyAfter`), `ACCOUNT_NOT_REGISTERED`, `CONTACT_NOT_VERIFIED`, `APPLICATION_LOCKED`, `DOCUMENT_TOO_LARGE`, `DOCUMENT_TYPE_NOT_ALLOWED`.

## Setup (status `VERIFIED`)

| Endpoint | Purpose |
|---|---|
| `GET /provider-application/setup` | What's done, plus `missing` and `readyForGoLive` |
| `GET`, `POST /provider-application/setup/agreement` | Read it; sign with `{typedName}`, which must match the legal name. 409 `AGREEMENT_NOT_READY` while signing is off |
| `GET`, `PUT /provider-application/setup/bank` | Masked on read. Saving resets the test deposit to `PENDING` |
| `GET`, `POST /provider-application/setup/training` | Questions; submit `{answers: {id: optionIndex}}` for `{passed, correct, total}` |
| `POST /provider-application/setup/profile-confirmation` | Confirms the profile |

## Dashboard (approved applications)

Sessions come from NB-Ecom-API's `/provider/bookings`, which needs the professional role granted at go-live.

| Endpoint | Purpose |
|---|---|
| `GET`, `POST /provider-workspace/services`; `PATCH /provider-workspace/services/{id}?active=` | The professional's services |
| `GET`, `POST /provider-workspace/slots`; `DELETE /provider-workspace/slots/{id}` | Open slots. 409 `SLOT_OVERLAP` |
| `GET /provider-workspace/earnings` | Fee, total, items. `paidOutTotal` stays 0 until bank payments exist |
| `GET /provider-workspace/product-activity` | Latest 20 suggestions, without the mother's identity |
| `GET /provider-workspace/profile` | Everything on file; bank details masked |
| `GET`, `POST /provider-workspace/change-requests` | `{type: PROFILE, NAME, LICENCE or BANK, values}`. Old details stay until approved |
| `POST /provider-workspace/change-requests/{id}/document` | Required for `NAME` and `LICENCE`; can be retried. Responses carry `documentRequired` and `documentAttached` |

## Staff (`OPERATIONS_ADMIN` or `MEDICAL_REVIEWER` only)

Base path: `/admin/provider-applications`.

| Endpoint | Who | Purpose |
|---|---|---|
| `GET /?status=` | Both | The queue, with flags |
| `GET /{id}` | Both | Full detail, audited. Bank details go to Operations only |
| `GET /documents/{id}/file` | Both | Audited download |
| `POST /{id}/operations-decision` | Operations | `{decision: PASS, CHANGES or REJECT, reason, changes, fraud, checklist}` |
| `POST /{id}/medical-decision` | Medical | `{decision: APPROVE, CHANGES or REJECT, reason, changes, fraud, checklist, confirmedExperienceYears, clearedStages}` |
| `POST /{id}/bank-verification` | Operations | `{passed, reason}` |
| `POST /{id}/recheck-complete` | Operations | Clears the 60-day re-check |
| `POST /{id}/go-live` | Operations | 409 `SETUP_INCOMPLETE` with `missing` |
| `POST /{id}/status` | Operations | `{status: ACTIVE, PAUSED or SUSPENDED, reason}`. Suspending cancels upcoming bookings |
| `GET /change-requests`; `POST /change-requests/{id}/decision` | Operations; Medical may also decide `LICENCE` | `{approve, reason}`. Account numbers are masked for Medical |

Review error codes: `WRONG_REVIEW_STAGE`, `SAME_REVIEWER`, `APPLICATION_NOT_APPROVABLE` (with `flags`), `LICENCE_LAPSED`, `INVALID_STATUS_CHANGE`. A database failure while reading the signed-in user returns 503 `SERVICE_UNAVAILABLE`, not 401.
