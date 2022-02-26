########################################################################################################################################
Backend Developer Test

Your task is to create a simple Spring Boot service, that is able to calculate any possible land
route from one country to another. The objective is to take a list of country data in JSON format
and calculate the route by utilizing individual countries border information.

Specifications:

● Spring Boot, Maven

● Data link: https://raw.githubusercontent.com/mledoze/countries/master/countries.json

● The application exposes REST endpoint `/routing/{origin}/{destination}` that returns a list of border crossings to get from origin to destination

● Single route is returned if the journey is possible

● Algorithm needs to be efficient

● If there is no land crossing, the endpoint returns `HTTP 400`

● Countries are identified by `cca3` field in country data

● HTTP request sample (land route from Czech Republic to Italy):

`GET /routing/CZE/ITA HTTP/1.0 :`
```
{
"route": ["CZE", "AUT", "ITA"]
}
```
Expected deliveries:
1. Source code
2. Instructions on how to build and run the application

########################################################################################################################################

Test cases in LandRouteApplicationTest.java:
```
1. shouldReturnSingleLandRouteForObviousExample
2. shouldReturn400IfNoLandRoute
3. shouldReturn400IfOriginCountryUnrecognized
4. shouldReturn400IfDestinationCountryUnrecognized
```


########################################################################################################################################

How to run (Maven and JDK11 required): 

A. Open the project in IDE of choosing (copying with maven and spring) and run LandRouteApplicationTest.java

or

B. enter the project's directory in command line tool of choosing and run
`mvn build`

and project will be built and tested.

########################################################################################################################################

My comments:

1. "any possible" part is misleading because the endpoint has to return a "single" land route.
   Does it mean shortest, first found or random? I see the "efficient" part but still...
2. You provided explanation for "cca3" field but none for field with border crossings.
3. Shouldn't you know how to run a project in strictly specified tech stack?

########################################################################################################################################