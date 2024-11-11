package br.edu.ifsp.dmo1.sorteador.model

class DefinedLimit(private val border : Int) : SorteiosStrategy() {

    override fun getLowBorder(): Int {
       return 1
    }

    override fun getHighBorder(): Int {
        return border
    }

    override fun nextNumber(): Int {
        return random.nextInt(1, border + 1)
    }
}