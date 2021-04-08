# Ports
## service1 
	instance1 6751
	instance2 6752
	eureka 6790
	zuul 7143
## service2
### instance1
    adminport 6749
    httplistener1 6760
    httplistener2 6761
    iiop listener 3921
### instance2
    adminport 6750
    httplistener1 6762
    httplistener2 6763
    iioplistener 62685

# Startup
## Service1 (jars)
### `Eureka`
    ssh -L 6790:localhost:6790 s225123@helios.cs.ifmo.ru -p 2222  
    bash  
    cd /home/s225123/soa_lab3_services/service1  
    java -jar lab3-service1-eureka-0.0.1-SNAPSHOT.jar ru.ifmo.soa.lab3.service1.eureka.Lab3Service1EurekaApplication  
### `Instance1`
    ssh -L 6751:localhost:6751 s225123@helios.cs.ifmo.ru -p 2222  
    bash  
    cd /home/s225123/soa_lab3_services/service1  
    java -jar lab3-service1-0.0.1-SNAPSHOT.jar ru.ifmo.soa.lab3.service1.Lab3Service1Application --server.port=6751   
### `Instance2`
    ssh -L 6752:localhost:6752 s225123@helios.cs.ifmo.ru -p 2222  
    bash  
    cd /home/s225123/soa_lab3_services/service1  
    java -jar lab3-service1-0.0.1-SNAPSHOT.jar ru.ifmo.soa.lab3.service1.Lab3Service1Application --server.port=6752  
### `Zuul`
    ssh -L 7143:localhost:7143 s225123@helios.cs.ifmo.ru -p 2222  
    bash  
    cd /home/s225123/soa_lab3_services/service1  
    java -jar soa-lab3-service1-zuul-0.0.1-SNAPSHOT.jar ru.ifmo.soa.lab3.service1.zuul.Application  
## Service2 (Payara damains)
    ssh -L 6763:localhost:6763 -L 6761:localhost:6761 s225123@helios.cs.ifmo.ru -p 2222  
    bash  
    cd payara5/bin  
    ./asadmin start-domain instance1  
    ./asadmin start-domain instance2  
    cd ../..  
    ./haproxy -f ./haproxy.cfg  
# View
## To  view both `Lab2` and `Lab3`
    ssh -L 7143:localhost:7143 -L 6781:localhost:6781 s225123@helios.cs.ifmo.ru -p 2222  