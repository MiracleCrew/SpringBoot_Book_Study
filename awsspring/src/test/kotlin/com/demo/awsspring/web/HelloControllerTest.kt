import com.demo.awsspring.AwsspringApplication
import com.demo.awsspring.web.dto.HelloResponseDto
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(classes = [AwsspringApplication::class])
@AutoConfigureMockMvc
class HelloControllerTest {

    // 프로퍼티선언 + 나중에 초기화
    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun `hello가 리턴된다`() {
        val hello = "hello"
        mvc.perform(get("/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string(hello))
    }

    @Test
    fun `helloDto가 리턴된다`() {
        // given
        val name = "test"
        val amount = 1000

        mvc.perform(
            get("/hello/dto")
                .param("name", name)
                .param("amount", amount.toString())
            )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value(name))
            .andExpect(jsonPath("$.amount").value(amount))
    }
}
