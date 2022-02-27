# Trendyol Link Converter

----

## What is Trendyol Link Converter

Trendyol Link Converter is a service that helps others to convert Trendyol.com links between mobile and web applications. 
Web applications use URLs and mobil applications use deeplinks.
Both application use links to redirect specific location inside applications.
When you want to redirect accross applications, you should convert URLs to deeplinks or deeplinks to URLs.

## Key Features

* Converts from web URL to deeplink.

| request | response |
| --------------- | --------------- |
| https://www.trendyol.com/casio/saat-p-19450?boutiqueId=11111&merchantId=999991 | ty://?Page=Home | 
| https://www.trendyol.com/casio/saat-p-19450 | ty://?Page=Product&MerchantId=99999&CampaignId=11111&ContentId=19450 |
| https://www.trendyol.com/sr?q=nike | y://?Page=Search&Query=nike |

* Converts from deeplink to web URL.


| request | response |
| --------------- | --------------- |
| ty://?Page=Favorites | https://www.trendyol.com | 
| ty://?Page=Product&MerchantId=99999&CampaignId=11111&ContentId=19450 | https://www.trendyol.com/brand/name-p-19450?boutiqueId=11111&merchantId=99999 |
| ty://?Page=Product&ContentId=19450 | https://www.trendyol.com/brand/name-p-19450 |

* Record each received request. So that, each conversion history is logged.

## Technology Used

* Java 11
* Spring Boot 
* JPA
* Docker
* Mysql

## External Libraries

* Mapstruct
* Apacha commons
* Lombok
* Springdoc

## Requirements
* Docker version 19.03.8 or higher

## How to run
* Download project from GitHub.
```
git clone https://github.com/DevelopmentHiring/TrendyolCase-OrkunGedik.git
```
* Navigate to project directory
* Run docker compose
````
docker-compose up
````

## Testing
* Open a browser and go to [swagger](http://localhost:8080/api/swagger-ui.html) page.
* Select API and press on the "Try it out" button. 
* Type your input json and press on the "Execute" button.
