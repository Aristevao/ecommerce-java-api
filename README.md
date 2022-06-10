## Summary
This project is a RESTful API based on an ecommerce. Implemented in Java with Spring-boot framework, contemplating the following resources:
- Hexagonal Architecture - Package structure organization
- Correlation ID - Identifier value that is attached to requests for debugging and trace.
- AOP Annotation - The core business logic isn't polluted with transaction code (logs)
- HateOAS - Provide links that will show customers how to navigate its resources.
- ControllerAdvice - Handle exceptions across the whole application
- Amazon S3 - Store and protect any amount of data in AWS

## Description of used resources
### Hexagonal Architecture
The main feature of "[Hexagonal Architecture](https://reflectoring.io/spring-hexagonal/) ”, as opposed to the common layered architecture style, is that the dependencies between our components point “inward”, towards our domain objects.

![image](https://user-images.githubusercontent.com/68197280/172960572-b14700d3-c75d-4d06-b543-a4c65337442b.png)

The hexagon is just a fancy way to describe the core of the application that is made up of domain objects, use cases that operate on them, and input and output ports that provide an interface to the outside world.

### Correlation ID

> Correlation ID in the logs implemented through the [Spring Cloud Sleuth](https://www.baeldung.com/spring-cloud-sleuth-single-application) library.
>
> Correlation ID available in the response header (key: `correlation-id`).

**Spring Cloud Sleuth Library**

This library makes it possible to identify logs pertaining to a specific job, thread, or request. Sleuth integrates effortlessly with logging frameworks like Logback and SLF4J to add unique identifiers that help track and diagnose issues using logs.

**Deinition**

A [Correlation ID](https://www.rapid7.com/blog/post/2016/12/23/the-value-of-correlation-ids/#:~:text=A%20Correlation%20ID%2C%20also%20known,well%20documented%20Enterprise%20Integration%20Pattern.) is a unique identifier value that is attached to requests and messages that allow reference to a particular transaction or event chain. The [Correlation Pattern](https://www.informit.com/articles/article.aspx?p=1398616), which depends on the use of Correlation ID is a well documented Enterprise Integration Pattern. A Correlation ID is defined as a non-standard HTTP header and is part of the Java Messaging Service (JMS). However, attaching a Correlation ID to a request is arbitrary. You don't have to use one. But if you are designing a distributed system that incorporates message queues and asynchronous processing, you will do well to include a Correlation ID in your messages.

### Custom Spring AOP Annotation

AOP stands for Aspect Orientated Programming. Essentially, it is a way for adding behavior to existing code without modifying that code.

For a detailed introduction to AOP, there are articles on AOP [pointcuts](https://www.baeldung.com/spring-aop-pointcut-tutorial) and [advice](https://www.baeldung.com/spring-aop-advice-tutorial). 
The type of AOP that was implemented is annotation driven.
The key here is non-invasiveness. By using annotation meta-data, our core business logic isn't polluted with our transaction code. This makes it easier to reason about, refactor, and to test in isolation.

```java
@Aspect
@Component
public class ExampleAspect {
}
```

### HateOAS
HATEOAS is a restriction that is part of the architecture of REST applications, whose objective is to help clients consume the service without the need for deep prior knowledge of the API. When implemented, the API will provide links that will show customers how to navigate its resources.
> Articles: [What is HateOAS?](https://www.treinaweb.com.br/blog/o-que-e-hateoas) |  [Intro to Spring HATEOAS](https://www.baeldung.com/spring-hateoas-tutorial)

**Example**
```json
{
    "account": {
        "number": 1234,
        "links": 
        [{
                "type": "GET",
                "rel": "self",
                "uri": "api.banco.com.br/usuario/1/conta/1234"
            }, {
                "type": "PUT",
                "rel": "conta_deposito",
                "uri": "api.banco.com.br/usuario/1/conta/1234/deposito"
 }]}}
```

### ControllerAdvice

It allows you to handle exceptions across the whole application, not just to an individual controller. You can think of it as an interceptor of exceptions thrown by methods annotated with @RequestMapping or one of the shortcuts.

> **Implementation**
> 
> ControllerAdvice class to handle expected exceptions;
> EntityError class, which works as a response DTO for errors;

> **Articles**
> Error Handling for REST with Spring - [baeldung](https://www.baeldung.com/exception-handling-for-rest-with-spring)
> 
> Spring Boot @ControllerAdvice - [zetcode](https://zetcode.com/springboot/controlleradvice/)
> 
> Understanding Spring's @ControllerAdvice - medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f

### Amazon S3

**Implementation**
Amazon Simple Storage Service (Amazon S3) is an object storage service. You can store and protect any amount of data for any use case, such as cloud-native applications and mobile apps.

S3 has a very simple structure; each bucket can store any number of objects, which can be accessed using either a SOAP interface or a REST-style API.

> **Articles**
> 
> [AWS S3 with Java](https://www.baeldung.com/aws-s3-java) | Baeldung
> 
> [Performing Operations on Amazon S3 Objects ](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-objects.html#upload-object)| AWS
> 
> [Store each AWS S3 file in a database as a separate row?](https://stackoverflow.com/questions/13096313/store-each-aws-s3-file-in-a-database-as-a-separate-row) | StackOverflow
