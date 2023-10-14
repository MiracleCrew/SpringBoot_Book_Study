import com.demo.awsspring.web.dto.HelloResponseDto
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class HelloResponseDtoTest {

    @Test
    fun `롬복 기능 테스트`() {
        // given
        val name = "test"
        val amount = 1000

        // when 
        val dto = HelloResponseDto(name, amount)

        // then
        assertThat(dto.name).isEqualTo(name)
        assertThat(dto.amount).isEqualTo(amount)
    }
}
