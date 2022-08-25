# Monitoring Project for Julius Baer

## Information

First I informed myself about Prometheus and Grafana. I learned alot about different types of metrics and the visualization of these.
I read the documentation for the Java client and soon had a good picture for what I was going to do.

## Planning

After the information gathering I started planning. I watched some tutorials on how it would work to run prometheus and grafana on docker and use a RestAPI 
as an endpoint. 

## Realisation 

First I made a yml file for prometheus. There I pulled the prometheus and the grafana prom. I run prometheus on port 9090 and grafana on port 3000. 
After I installed and linked the two I created a Java-Springboot project and made a spring-actuator job in the prometheus file. To connect my java project to prometheus 
In the static_configs I set the target to the spring-actuator job on port 8080. By doing that my prometheus instance could fetch data from the java application.
As soon as that worked I started buildung a restcontroller and made a service to fetch data from an API to visualize data in my HTTP-Endpoint. 
I made a counter to count the requests made to my "Bank"-endpoint. 

