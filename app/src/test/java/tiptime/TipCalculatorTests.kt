package tiptime

import com.kyawzinlinn.diceroller.calculateTip
import org.junit.Test
import org.junit.Assert.assertEquals
import java.text.NumberFormat

class TipCalculatorTests {
    @Test
    fun test(){
        val amount = 10.0
        val percent = 20.0
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount,percent,false)
        assertEquals(expectedTip,actualTip)
    }
}