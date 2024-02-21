package procedeus.credit.project.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.context.ActiveProfiles
import procedeus.credit.project.entity.Address
import procedeus.credit.project.entity.Customer
import procedeus.credit.project.exception.BusinessException
import procedeus.credit.project.repository.CustomerRepository
import procedeus.credit.project.service.impl.CustomerService
import java.math.BigDecimal
import java.util.Optional
import kotlin.random.Random

@ActiveProfiles("test")
@ExtendWith(MockitoExtension::class)
class CustomerServiceTest {
    @Mock lateinit var customerRepository: CustomerRepository
    @InjectMocks lateinit var customerService: CustomerService

    @Test
    fun `should create customer`(){

        val fakeCustomer: Customer = buildCustomer()
        `when`(customerRepository.save(ArgumentMatchers.any())).thenReturn(fakeCustomer)

        val actual: Customer = customerService.save(fakeCustomer)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(customerRepository, times(1)).save(fakeCustomer)
    }

    @Test
    fun `find customer by id`(){
        val fakeId: Long = Random(10).nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        `when`(customerRepository.findById(fakeId)).thenReturn(Optional.of(fakeCustomer))

        val actual: Customer = customerService.findById(fakeId)

        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(customerRepository, times(1)).findById(fakeId);
    }

    @Test
    fun `should not find customer by invalid id`(){
        val fakeId: Long = Random(10).nextLong()
        `when` (customerRepository.findById(fakeId)).thenReturn(Optional.empty())

        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("ID: '$fakeId' não encontrado")
        verify(customerRepository, times(1)).findById(fakeId);
    }

    fun buildCustomer(
        firstName: String = "Murilo",
        lastName: String = "Rezende",
        cpf: String = "26766153784",
        email: String = "murilorez79@hotmail.com",
        password: String = "123456",
        zipCode: String = "19000000",
        street: String = "Rua do Perigo",
        income: BigDecimal = BigDecimal.valueOf(2500.0),
        id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
        id = id
    )
}
