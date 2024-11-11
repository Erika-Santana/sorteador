package br.edu.ifsp.dmo1.sorteador

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.sorteador.databinding.ActivityMainBinding
import br.edu.ifsp.dmo1.sorteador.model.Draw
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var draw = Draw()
//Lista pra printar os valores com o index certinho
    private val historicoString: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    override fun onClick(v: View) {
        when (v) {
            binding.botaoSorteio -> {
                val limit: Int = try {
                    binding.valorUsuario.text.toString().toInt()
                } catch (e: NumberFormatException) {
                    -1
                }

                draw = if (limit > 1) {
                    Draw(limit)
                } else {
                    Draw()
                }
                updateUI()
            }

            binding.buttonDraw -> {
                val numeroSorteado = draw.getNumber()

                if (numeroSorteado == 0) {
                    Toast.makeText(this, "Limite de números sorteados foi ultrapassado!", Toast.LENGTH_SHORT).show()
                } else {
                    binding.iniciarSorteio.text = numeroSorteado.toString()
                    addToHistory(numeroSorteado)
                }

                updateListView()
            }
        }
    }

    private fun updateUI() {
        val str = String.format("Intervalo de 1 a %,d.", draw.getHighBorder())
        binding.textviewInterval.text = str
        binding.valorUsuario.text.clear()
        binding.iniciarSorteio.text = getString(R.string.iniciar_o_sorteio)
        historicoString.clear() // Limpa o histórico ao definir novo limite
        updateListView()
    }

    private fun setClickListener() {
        binding.buttonDraw.setOnClickListener(this)
        binding.botaoSorteio.setOnClickListener(this)
    }

    private fun addToHistory(numeroSorteado: Int) {

        historicoString.add("${historicoString.size + 1}º sorteado = $numeroSorteado")
    }

    private fun updateListView() {
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,
            historicoString
        )
        binding.listView.adapter = adapter
    }
}
