package tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.kyawzinlinn.diceroller.TipTimeLayout
import com.kyawzinlinn.diceroller.ui.theme.DiceRollerTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUiTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20Percent_tip(){
        composeTestRule.setContent {
            DiceRollerTheme {
                TipTimeLayout()
            }
        }
        composeTestRule.onNodeWithText("Bill Amount").performTextInput("10")
        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
            "No node with this text was found."
        )
    }
}