interface Programeri {
    fun puniIdentitet(): String
    fun oznakaZemlje(): String
}
open class Programer(
    val ime: String,
    val prezime: String,
    val godineIskustva: Int,
    private val zemlja: String,
    val programskiJezici: List<String>
) : Programeri {

    init {
        require(ime.isNotBlank()) { "Ime ne može biti prazno" }
        require(prezime.isNotBlank()) { "Prezime ne može biti prazno" }
        require(godineIskustva >= 0) { "Godine iskustva ne mogu biti negativne" }
        require(zemlja.isNotBlank()) { "Oznaka zemlje ne može biti prazna" }
        require(programskiJezici.isNotEmpty()) { "Lista jezika ne može biti prazna" }
    }
    val normalizovaniJezici: List<String> = programskiJezici.map { it.lowercase() }
    override fun puniIdentitet(): String = "$ime $prezime"

    override fun oznakaZemlje(): String = zemlja

    open fun tipProgramera(): String = "Programer"
}
class BackendProgramer(
    ime: String,
    prezime: String,
    godineIskustva: Int,
    zemlja: String,
    programskiJezici: List<String>,
    val backendFramework: String
) : Programer(ime, prezime, godineIskustva, zemlja, programskiJezici) {

    init {
        require(backendFramework.isNotBlank()) { "Backend framework ne može biti prazan" }
    }

    override fun tipProgramera(): String = "Backend programer"
}
class FrontendProgramer(
    ime: String,
    prezime: String,
    godineIskustva: Int,
    zemlja: String,
    programskiJezici: List<String>,
    val frontendFramework: String
) : Programer(ime, prezime, godineIskustva, zemlja, programskiJezici) {

    init {
        require(frontendFramework.isNotBlank()) { "Frontend framework ne može biti prazan" }
    }

    override fun tipProgramera(): String = "Frontend programer"
}
fun prebrojiJezikeFunkcionalno(programeri: List<Programer>): Map<String, Int> =
    programeri.flatMap { it.normalizovaniJezici }
        .groupingBy { it }
        .eachCount()
fun prebrojiJezikeRucno(programeri: List<Programer>): Map<String, Int> {
    val brojac = mutableMapOf<String, Int>()
    for (programer in programeri) {
        for (jezik in programer.normalizovaniJezici) {
            brojac[jezik] = brojac.getOrDefault(jezik, 0) + 1
        }
    }
    return brojac.toMap()
}
fun prosjecnoIskustvoFunkcionalno(programeri: List<Programer>): Map<String, Double> =
    programeri.flatMap { p -> p.normalizovaniJezici.map { it to p.godineIskustva } }
        .groupBy({ it.first }, { it.second })
        .mapValues { (_, godine) -> godine.average() }

fun prosjecnoIskustvoRucno(programeri: List<Programer>): Map<String, Double> {
    val ukupnoPoJeziku = mutableMapOf<String, Int>()
    val brojProgrameraPoJeziku = mutableMapOf<String, Int>()
    for (programer in programeri) {
        for (jezik in programer.normalizovaniJezici) {
            ukupnoPoJeziku[jezik] = ukupnoPoJeziku.getOrDefault(jezik, 0) + programer.godineIskustva
            brojProgrameraPoJeziku[jezik] = brojProgrameraPoJeziku.getOrDefault(jezik, 0) + 1
        }
    }
    return ukupnoPoJeziku.mapValues { (jezik, ukupno) ->
        ukupno.toDouble() / brojProgrameraPoJeziku[jezik]!!
    }
}
fun filtrirajPoFrameworku(programeri: List<Programer>, framework: String): List<Programer> =
    programeri.filter {
        when (it) {
            is BackendProgramer -> it.backendFramework.equals(framework, ignoreCase = true)
            is FrontendProgramer -> it.frontendFramework.equals(framework, ignoreCase = true)
            else -> false
        }
    }
fun ispisiProgramera(programer: Programer) {
    val framework = when (programer) {
        is BackendProgramer -> programer.backendFramework
        is FrontendProgramer -> programer.frontendFramework
        else -> "N/A"
    }
    val jezici = programer.normalizovaniJezici.sorted().joinToString(", ")
    println("${programer.puniIdentitet()} — ${programer.tipProgramera()} — jezici: $jezici — framework: $framework")
}
fun ispisiSveProgramere(programeri: List<Programer>) {
    println("Lista programera:")
    programeri.forEach { ispisiProgramera(it) }
    println()
}
fun <K, V> ispisiMapu(
    naslov: String,
    mapa: Map<K, V>,
    formatVrijednosti: ((V) -> String)? = null
) {
    println(naslov)
    mapa.forEach { (kljuc, vrijednost) ->
        val tekst = formatVrijednosti?.invoke(vrijednost) ?: vrijednost.toString()
        println("$kljuc -> $tekst")
    }
    println()
}

fun main() {
    val programeri = listOf(
        BackendProgramer("Amila", "Hodžić", 5, "BA", listOf("Kotlin", "Java", "Python"), "Spring Boot"),
        BackendProgramer("Emir", "Softić", 3, "BA", listOf("Java", "Kotlin"), "Ktor"),
        FrontendProgramer("Sara", "Kovač", 4, "HR", listOf("JavaScript", "TypeScript"), "React"),
        FrontendProgramer("Marko", "Petrović", 2, "RS", listOf("JavaScript", "HTML", "CSS"), "Vue.js"),
        BackendProgramer("Anna", "Schmidt", 7, "DE", listOf("Python", "Go"), "Django"),
        FrontendProgramer("Lena", "Müller", 6, "DE", listOf("TypeScript", "JavaScript"), "Angular")
    )

    println("Provjere ispravnosti:")
    check(programeri.size >= 5) { "Lista mora sadržavati najmanje 5 programera" }
    check(programeri.any { it is BackendProgramer }) { "Mora postojati barem jedan backend programer" }
    check(programeri.any { it is FrontendProgramer }) { "Mora postojati barem jedan frontend programer" }
    println("✓ Sve provjere su prošle uspješno!\n")

    ispisiSveProgramere(programeri)

    ispisiMapu("Prebrojavanje jezika (funkcionalno):", prebrojiJezikeFunkcionalno(programeri))
    ispisiMapu("Prebrojavanje jezika (ručno):", prebrojiJezikeRucno(programeri))
    ispisiMapu("Prosječno iskustvo po jeziku (funkcionalno):", prosjecnoIskustvoFunkcionalno(programeri)) {
        "%.2f".format(it)
    }
    ispisiMapu("Prosječno iskustvo po jeziku (ručno):", prosjecnoIskustvoRucno(programeri)) {
        "%.2f".format(it)
    }

    println("Programeri koji koriste Spring Boot:")
    filtrirajPoFrameworku(programeri, "Spring Boot").forEach { ispisiProgramera(it) }
    println()

    println("Programeri koji koriste React:")
    filtrirajPoFrameworku(programeri, "React").forEach { ispisiProgramera(it) }
}