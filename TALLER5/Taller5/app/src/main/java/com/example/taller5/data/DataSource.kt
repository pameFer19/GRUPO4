package com.example.taller5.data
// Clase que representa a una persona
data class Person(
    val name: String,
    val description: String
)

// Lista de personas
val people = listOf(
    Person(
        "Condolo Byron",
        "Byron Condolo\n10/10/1999\nLucha de los Pobres\n0959977564"
    ),
    Person(
        "Pamela Fernández",
        "Pamela Fernández\n19/05/2000\nEl Inca\n0995377938"
    ),
    Person(
        "Marielena González",
        "Marielena González\n23/08/2003\nLa Gasca\n0959977564"
    ),
    Person(
        "Angelo Lascano",
        "Angelo Lascano\n10/10/1999\nQuitumbe\n0983119233"
    ),
    Person(
        "Ruth Rosero",
        "Ruth Rosero\n28/05/1999\nEntrada al conde\n0982084674"
    ),
    Person(
        "Joan Santamaria",
        "Joan Santamaria\n10/10/1999\nBicentenario\n0984554531"
    ),
    Person(
        "Dennis Trujillo",
        "Dennis Trujillo\n29/06/1999\nLa Ecuatoriana\n0969543205"
    )
)
