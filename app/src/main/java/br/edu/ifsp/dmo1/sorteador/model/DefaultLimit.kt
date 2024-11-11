package br.edu.ifsp.dmo1.sorteador.model

import kotlin.random.Random

// o object aqui define que essa classe insere o padrão de objeto Singleton
object DefaultLimit : SorteiosStrategy() {
    private val BORDER_DEFAULT = 1000

    override fun getHighBorder(): Int {
       return BORDER_DEFAULT
    }

    override fun nextNumber(): Int {
        ///aqui ele retorna um número random de 1 até 1000
       return Random.nextInt(1, BORDER_DEFAULT + 1);
    }

    override fun getLowBorder(): Int {
        return 1;
    }
}