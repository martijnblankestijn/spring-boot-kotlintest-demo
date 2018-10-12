package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@Entity
class OrderLine(
    @Id val id: UUID
)

@Entity
class ShoppingOrder(
    @Id val id: UUID, 
    @OneToMany(cascade = [CascadeType.ALL]) val lines: List<OrderLine>
)
