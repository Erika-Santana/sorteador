package br.edu.ifsp.dmo1.sorteador.model

import android.widget.Toast

//Classe responsável por realizar os sorteios e definir as estratégias feitas a cada sorteio.
//Essa classe também guarda todos os valores que já foram sorteados evitando que seja sorteado novamente.
class Draw (private val border : Int  = 0) {

    private lateinit var strategy: SorteiosStrategy
    private val history = HashSet<Int>()
    private var index = 0
    init {
        if (border == 0)
            strategy = DefaultLimit
        else
            strategy = DefinedLimit(border)
    }


    /*history.add(number) retorna um valor booleano:

    true se o número foi adicionado com sucesso (ou seja, o número não estava no HashSet ainda);
    false se o número já estava presente no conjunto (o que significa que o número é duplicado).
    Por ser um HashSet, ele já faz essa verificação automaticamente. O HashSet não permite elementos duplicados, então quando você chama add(number), ele verifica internamente se o número já existe:

    Se o número não existe, ele é adicionado ao conjunto, e o método add retorna true.
    Se o número já existe, ele não é adicionado, e o método add retorna false.
    Assim, o do...while é útil aqui para garantir que você receba um número novo a cada chamada da função getNumber().*/

    fun getNumber(): Int{

        var number : Int
        do {
            number = strategy.nextNumber()
            if (history.size == border){

                number = 0
                return number

            }
        }while (!history.add(number))

        /*Na verdade, o do...while vai continuar executando o strategy.nextNumber() até encontrar um número que ainda não exista no HashSet. Ou seja, ele vai repetir enquanto o número for duplicado (ou seja, já estiver no HashSet).

        Portanto:

        A cada iteração, strategy.nextNumber() gera um novo número.
        Esse número é tentado no history.add(number).
        Se for uma duplicata (já existe no HashSet), history.add(number) retorna false, e o loop continua, gerando outro número.
        O loop só para quando encontra um número novo, ou seja, quando history.add(number) retorna true.
        Em resumo, o do...while não para em duplicatas; ele continua até encontrar um número único.*/


        return  number;
    }


    fun getHistory () = ArrayList(history)

    fun getLowBorder() = strategy.getLowBorder()

    fun getHighBorder() = strategy.getHighBorder()

}