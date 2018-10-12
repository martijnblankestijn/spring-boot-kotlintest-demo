package com.example.demo

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import io.kotlintest.spring.SpringListener
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.UUID.randomUUID

@ExtendWith(SpringExtension::class)
@Transactional
@SpringBootTest
class ShoppingOrderSpec : WordSpec() {
    override fun listeners() = listOf(SpringListener)
    
    @Autowired
    var orderRepository: OrderRepository? = null
    
    init {
        "Order Entity" should {
            "store and retrieve" {
                val order = ShoppingOrder(randomUUID(), listOf(createOrderLine(), createOrderLine()))
                
                orderRepository!!.save(order)
                
                val saved = orderRepository!!.findById(order.id).get()
                
                saved.id shouldBe order.id
                saved.lines shouldHaveSize 2
            }
        }
    }

    private fun createOrderLine() = OrderLine(randomUUID())
}

interface OrderRepository: JpaRepository<ShoppingOrder, UUID>