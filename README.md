# Final Project Jayjay

Automation test framework berbasis Java + Gradle yang menggabungkan Web UI testing dan API testing dalam satu repository. Framework ini menggunakan Cucumber dengan format Gherkin, Selenium untuk Web UI, Rest Assured untuk API, dan GitHub Actions untuk CI.

## Tools dan Library

- Java 17
- Gradle 8.x
- Cucumber
- JUnit 5
- Selenium WebDriver
- Rest Assured
- GitHub Actions

## Target Testing

- Web UI: [Demoblaze](https://www.demoblaze.com/)
- API: [Dummy API](https://dummyapi.io/docs)

## Struktur Project

```text
src/test/java/com/test
|-- api
|   |-- client
|   `-- steps
|-- runners
`-- web
    |-- pages
    `-- steps

src/test/resources/features
|-- api
`-- web
```

## Skenario yang Diimplementasikan

### Web UI

- Melihat produk dari home page
- Membuka detail produk
- Menambahkan produk ke cart

### API

- Create User
- Get User by ID
- Update User
- Delete User
- Get List of Tags

## Cara Menjalankan Test

Jalankan semua dependency terlebih dulu melalui Gradle wrapper:

```bash
./gradlew test
```

Jalankan hanya API test:

```bash
./gradlew apiTest
```

Jalankan hanya Web test:

```bash
./gradlew webTest
```

Untuk Windows PowerShell:

```powershell
.\gradlew.bat apiTest
.\gradlew.bat webTest
```

## Konfigurasi Penting

- `DUMMY_API_APP_ID`: optional environment variable untuk override app-id Dummy API.
- `-Ddummy.api.app.id=<value>`: override app-id via Java system property.
- `-Dweb.base.url=<value>`: override base URL web test.
- `-Dapi.base.url=<value>`: override base URL API test.
- `-Dheadless=true|false`: mode browser untuk web test.

## Report

Framework menghasilkan report Cucumber dalam format berikut:

- HTML: `build/reports/cucumber/api/cucumber.html`
- JSON: `build/reports/cucumber/api/cucumber.json`
- HTML: `build/reports/cucumber/web/cucumber.html`
- JSON: `build/reports/cucumber/web/cucumber.json`

## GitHub Actions

Workflow CI berada di `.github/workflows/automation-tests.yml` dan akan berjalan pada:

- `workflow_dispatch`
- `pull_request`

Workflow memisahkan job API test dan Web test, lalu meng-upload artifact report di akhir eksekusi.
