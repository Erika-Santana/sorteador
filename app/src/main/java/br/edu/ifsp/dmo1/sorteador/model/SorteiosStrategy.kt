package br.edu.ifsp.dmo1.sorteador.model

import kotlin.random.Random
// Padrão de projeto Strategy é um padrão que define várias estratégias para todas as classes que a implementam e que são executadas durante a eecução da aplicação
//Aqui é uma classe modelo para as outras estratégias

abstract class SorteiosStrategy {

    protected val random = Random.Default
    abstract fun nextNumber() : Int
    abstract fun getLowBorder() : Int
    abstract fun getHighBorder() : Int

}