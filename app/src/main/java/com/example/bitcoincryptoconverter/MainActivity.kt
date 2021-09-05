package com.example.bitcoincryptoconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bitcoincryptoconverter.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btcButton.setOnClickListener {
            calculateAmount()
        }


    }

    private fun calculateAmount() {
        val amount = binding.amount.text.toString().toDoubleOrNull()

        if (amount == null || amount == 0.0){
            displayCryptoAmount(0.0)
            return
        }

        val cryptoPercentage = when(binding.cryptoOptions.checkedRadioButtonId) {
            R.id.btc -> 0.0000000483992
            else -> 0.000000613
        }
        var cryptoamount = cryptoPercentage * amount

        if (binding.cryptoSwitch.isChecked) {
            cryptoamount = kotlin.math.round(cryptoamount)
        }

        displayCryptoAmount(cryptoamount)
    }

    private fun displayCryptoAmount(cryptoamount: Double) {
//        val formattedCrypto = NumberFormat.getCurrencyInstance().format(cryptoamount)
        val formattedCrypto = NumberFormat.getNumberInstance().format(cryptoamount)
        binding.cryptoResult.text = getString(R.string.crypto_amount_value, formattedCrypto)
    }
}