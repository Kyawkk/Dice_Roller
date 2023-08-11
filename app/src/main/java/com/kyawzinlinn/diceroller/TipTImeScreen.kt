package com.kyawzinlinn.diceroller

import android.app.Activity
import android.content.pm.PackageManager
import android.icu.text.NumberFormat
import android.telephony.SmsManager
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipTimeLayout(){
    var amountInput by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }
    val tipAmount = amountInput.toDoubleOrNull() ?: 0.0

    var tipInput by remember{ mutableStateOf("") }
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(tipAmount, tipPercent, roundUp)

    // Replace with your desired message
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(id = R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        )

        EditInputField(
            labelId = R.string.bill_amount,
            value = amountInput,
            leadingIcon = R.drawable.round_money_24,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            onValueChange = {
                amountInput = it
            }
        )

        EditInputField(
            labelId = R.string.how_was_the_service,
            value = tipInput,
            leadingIcon = R.drawable.round_percent_24,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            onValueChange = {
                tipInput = it
            }
        )

        RoundTheTipRow(
            roundUp = roundUp,
            onRoundUpChanged = {
                               roundUp = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
        )

        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))

        /*Button(onClick = {

            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.SEND_SMS
                ) == PackageManager.PERMISSION_GRANTED
            ) {

            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(android.Manifest.permission.SEND_SMS),
                    101
                )
            }

            val phoneNumber = "09694778856" // Replace with the recipient's phone number
            val message = "ဟုတ်ပါပီ"

            try {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(phoneNumber, null, message, null, null)
                Toast.makeText(context, "SMS sent", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Failed to send SMS", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }) {
            Text(text = "Send message")
        }*/
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInputField(
    @StringRes labelId: Int,
    @DrawableRes leadingIcon: Int,
    value: String,
    onValueChange: (String) ->Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
){
    TextField(
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null)},
        label = { Text(text = stringResource(id = labelId))},
        singleLine = true,
        keyboardOptions = keyboardOptions,
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
    )
}

@Composable
fun RoundTheTipRow(
    modifier: Modifier,
    roundUp: Boolean,
    onRoundUpChanged: ((Boolean) -> Unit),
    ){
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = stringResource(id = R.string.round_up_tip)
        )
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

@VisibleForTesting
internal fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String{
    var tip = tipPercent / 100 * amount
    if (roundUp) tip = Math.ceil(tip)
    return java.text.NumberFormat.getCurrencyInstance().format(tip)
}