# AdCampaignInfo

This is the AdCampaign Info Service to add new campaign information and fetch active campaigns for partner identifier.

### Development

This is a Spring-Boot project with Gradle as its task runner.

**Build:** `./gradlew build`
**Integration Tests:** `./gradlew integrationTest`
**Run:** `./gradlew bootRun -Dspring.profiles.active=local`

## Run locally
```sh
gradlew bootRun
```
or (Windows)
```sh
java -jar -Dspring.profiles.active=local build\libs\adCampaign-0.0.1-SNAPSHOT.jar
```

### Specification

Swagger specs for the AdCampaignInfo service can be found here: 
https://gist.github.com/mjain25/32baa048afddd11009cc65c5b6ef1628

### This Project connects to H2 DB

localhost url for h2 console 

```sh
http://localhost:8080/h2-console/login.jsp 

JDBC URL : jdbc:h2:mem:testdb;Mode=Oracle
User Name : sa
Password : <<leave blank>>
```
### Hystrix Dashboard localhost

```sh
http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Fad&title=adcampaign

```

### Postman (Rest client) Sample Request to POST new Campaign

```sh
POST /ad HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 363222c9-acca-e2b9-75b8-69927a41ea43

{
	"partner_id": "1",
	"duration": 4000000,
	"ad_content": "Campaign Content"
} 

```

### Postman (Rest client) Sample Request to GET Active Campaign

```sh
GET /ad/{partnerId} HTTP/1.1
Host: localhost:8080
Cache-Control: no-cache
Postman-Token: 44b03d21-f857-d4c8-3f95-37d94c75139d 

```
