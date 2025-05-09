import java.io.File

// Códigos de color de fondo
const val BG_BLACK = "\u001B[40m"
const val BG_RED = "\u001B[41m"
const val BG_GREEN = "\u001B[42m"
const val BG_YELLOW = "\u001B[43m"
const val BG_BLUE = "\u001B[44m"
const val BG_PURPLE = "\u001B[45m"
const val BG_CYAN = "\u001B[46m"
const val BG_WHITE = "\u001B[47m"
// Colores ANSI básicos
const val RESET = "\u001B[0m"
const val BLACK = "\u001B[30m"
const val RED = "\u001B[31m"
const val GREEN = "\u001B[32m"
const val YELLOW = "\u001B[33m"
const val BLUE = "\u001B[34m"
const val PURPLE = "\u001B[35m"
const val CYAN = "\u001B[36m"
const val WHITE = "\u001B[37m"
const val BOLD = "\u001B[1m"
const val UNDERLINE = "\u001B[4m"

//Función que devolve un enteiro aleatorio de 1 a 6
fun alea6(): Int {
        val cifra = 1..6
        var aleatorio = cifra.random()
    return aleatorio
}

fun main() {

    println("$GREEN")
    println("1. Xogar")
    println("2. Ver traza do último intento")
    println("3. Sair")
    print("opción: ")

    var opcion=readln().toInt()

    val file= File("oMeuFicheiro.txt")
    var numeroSegredo= arrayOf(0,0,0,0)
    var numeroBusacado= arrayOf(0,0,0,0)
    var segredo=""
    var numero="0000"
    var exactos=0
    var coincidencias=0

    when (opcion) {
        1 -> {
            numeroSegredo[0]=alea6()
            numeroSegredo[1] = alea6()
            while (numeroSegredo[0] == numeroSegredo[1]) numeroSegredo[1] = alea6()
            numeroSegredo[2] = alea6()
            while (numeroSegredo[2] == numeroSegredo[1] || numeroSegredo[2] == numeroSegredo [0]) numeroSegredo[2] = alea6()
            numeroSegredo[3] = alea6()
            while (numeroSegredo[3] == numeroSegredo[2] || numeroSegredo[3] == numeroSegredo[1] || numeroSegredo[3] == numeroSegredo [0]) numeroSegredo[3] = alea6()

            print("$BLUE")

            for (i in 0..3) {
                segredo=segredo+numeroSegredo[i]
            }

            File("oMeuFicheiro.txt").writeText("Numero segredo=$segredo\n")

            for (intento in 1..10){
            print("${BLUE}Teclea un número de 4 dixitos (do 1 ao 6) sen números repetidos: ")
                numero=readln()
                exactos=0
                coincidencias=0

                for (posicion in 0..3) {
                    if (numeroSegredo[posicion].toString()==numero[posicion].toString()) exactos++
                }
                for (posicion in 0..3) {
                    for (busqueda in 0..3){
                        if ((numeroSegredo[posicion].toString()==numero[busqueda].toString()) && (numeroSegredo[posicion].toString()!=numero[posicion].toString())) coincidencias++
                    }
                }

                println("${WHITE}Intento$intento: $numero $BG_GREEN${BLACK} $exactos ${RESET}$BG_YELLOW${BLACK} $coincidencias ${RESET}")
                val file = File("OMeuFicheiro.txt").appendText("Intento$intento: $numero Exactos:$exactos Coincidencias:$coincidencias \n")
                if (exactos==4) {
                    println("${BG_CYAN}CONSEGUIDO!! Noraboa!$RESET")
                    break
                }
                }



        }
        2-> {
            if (file.exists()) {
                val contido=File("oMeuFicheiro.txt").readText()
                println(contido)
            }
            else
            {
                println("Xoga unha partida para usar esta opción")
            }
        }
        3-> {
            println("Grazas por xogar")
        }
    }
}