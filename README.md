# Illustrating the problem with @Transactional in combination with Kotlin Test

The project is a [Spring Boot 2](https://spring.io/projects/spring-boot) application 
with two entities, ShoppingOrder and OrderLine (to be totally unimaginative). 

There is also a test case `ShoppingOrderSpec` which just tests the mapping by storing and retrieving the Order.

The testcase is configured like this:
```kotlin
@ExtendWith(SpringExtension::class)
@Transactional
@SpringBootTest
class ShoppingOrderSpec : WordSpec() {
    override fun listeners() = listOf(SpringListener)
```
By using the [SpringExtension](https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#integration-testing-annotations-junit-jupiter) 
by Spring to hook into the JUnit 5 engine and Wordspec from [Kotlintest](https://github.com/kotlintest/kotlintest/) to structure the tests 
and do the assertions with Kotlintest.

Running the testcase gives the following stack-trace:

```
2018-10-12 10:54:14.329  INFO 59374 --- [intest-engine-0] com.example.demo.ShoppingOrderSpec       : Started ShoppingOrderSpec in 4.478 seconds (JVM running for 7.421)
org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.example.demo.ShoppingOrder.lines, could not initialize proxy - no Session
	at org.hibernate.collection.internal.AbstractPersistentCollection.throwLazyInitializationException(AbstractPersistentCollection.java:582)
...
  at com.example.demo.ShoppingOrderSpec$1$1.invoke(ShoppingOrderSpec.kt:35)
	at com.example.demo.ShoppingOrderSpec$1$1.invoke(ShoppingOrderSpec.kt:19)
```

So, somehow the `org.springframework.transaction.annotation.Transactional` annotation does not seem to work, 
as removing the annotation, just gives the same response.

Anyone any ideas how to get the `@Transactional` being applied and respected?
