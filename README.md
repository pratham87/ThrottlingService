# ThrottlingService
Throttling Service based on request count in certain time frame.
1. Deploy the service in any web server container. I used tomcat 8. 
2. Hitting the client to POST the request:- http://localhost:8080/ThrottlingService/throttleClient/track : Post the request
3. Hitting the GET service method:- http://localhost:8080/ThrottlingService/aggregate?type=a&duration=1&time=1 : Get the count for request in 'x' ms duration.
4. Time and duration is given in milliseconds (any long value in sorted manner).
5. Sample JSON POST request: { "type": "a", "time": 1 }
6. POST request is provided in request.txt file (src/main/resources)
7. Sample response: {"type":"a","count":2,"time":5}

 
