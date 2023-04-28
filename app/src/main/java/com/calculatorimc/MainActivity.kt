package com.calculatorimc

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.calculatorimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner: Spinner = findViewById(R.id.sp_weight_unit)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position)  // item selecionado
                // fazer o que quiser com o item selecionado aqui


                binding.btnCalcular.setOnClickListener {
                    if (selectedItem == "lbs") {
                        Toast.makeText(
                            this@MainActivity,
                            selectedItem.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val weight = binding.etWight1.text.toString().toDouble()
                        val height = binding.etHeigth1.text.toString().toDouble()
                        val resultBMI = weight / (height * height)
                        pesoIdeal(resultBMI)
                        resultBMI.toString()
                        binding.tvResult.setText(String.format("%.2f", resultBMI))
                    }


                }


            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // método não utilizado, mas necessário implementar
            }
        }


    }

    fun pesoIdeal(resultIMC: Double) {
        when {
            resultIMC < 18.5 -> binding.tvPesoIdeal.text = getString(R.string.abaixo_do_peso)
            resultIMC < 25.0 -> binding.tvPesoIdeal.text = getString(R.string.peso_normal)
            resultIMC < 30.0 -> binding.tvPesoIdeal.text = getString(R.string.pr_obesidade)
            resultIMC < 35.0 -> binding.tvPesoIdeal.text = getString(R.string.obesidade_grau_1)
            resultIMC < 40.0 -> binding.tvPesoIdeal.text = getString(R.string.obesidade_grau_2)
            else -> binding.tvPesoIdeal.text = getString(R.string.obesidade_grau_3)
        }
    }


}