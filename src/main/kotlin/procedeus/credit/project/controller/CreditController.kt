package procedeus.credit.project.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import procedeus.credit.project.dto.CreditDto
import procedeus.credit.project.dto.CreditView
import procedeus.credit.project.dto.CreditViewList
import procedeus.credit.project.entity.Credit
import procedeus.credit.project.service.impl.CreditService
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditResource(
    private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody creditDto: CreditDto): String{
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return "Credit: ${credit.creditCode} - Customer: ${credit.customer?.firstName} saved"
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): List<CreditViewList>{
        return this.creditService.findAllByCustomer(customerId).stream()
            .map { credit: Credit -> CreditViewList(credit)  }
            .collect(Collectors.toList())
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(
        @RequestParam(value = "customerId") customerId: Long,
        @PathVariable creditCode: UUID): CreditView {
        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return CreditView(credit)
    }
}