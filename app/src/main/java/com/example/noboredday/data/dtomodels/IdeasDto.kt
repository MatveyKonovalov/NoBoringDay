package com.example.noboredday.data.dtomodels

import com.google.gson.annotations.SerializedName

data class IdeasDto(
    @SerializedName("activity")
    val activityENG: String,
    @SerializedName("availability")
    val availability: Float,
    @SerializedName("type")
    val type: String,
    @SerializedName("price")
    val price: Float,
    @SerializedName("accessibility")
    val accessibility: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("kidFriendly")
    val kidFriendly: Boolean,
    @SerializedName("link")
    val link: String,
    @SerializedName("key")
    val key: String,
){
    private val activityRUS = BoredTranslations.translate(activityENG)
}


// Словарь переводов
object BoredTranslations {
    private val translations = mapOf(
        // === Досуг и отдых (relaxation) ===
        "Read a book" to "Почитать книгу",
        "Meditate for 10 minutes" to "Помедитировать 10 минут",
        "Take a nap" to "Вздремнуть",
        "Watch a movie" to "Посмотреть фильм",
        "Listen to music" to "Послушать музыку",
        "Take a bath" to "Принять ванну",
        "Do nothing for 5 minutes" to "Ничего не делать 5 минут",
        "Stretch for 5 minutes" to "Потянуться 5 минут",
        "Sit in the dark for 5 minutes" to "Посидеть в темноте 5 минут",
        "Watch the clouds" to "Посмотреть на облака",

        // === Образование (education) ===
        "Learn a new word" to "Выучить новое слово",
        "Read the news" to "Почитать новости",
        "Watch a documentary" to "Посмотреть документальный фильм",
        "Solve a riddle" to "Решить загадку",
        "Learn a fun fact" to "Узнать интересный факт",
        "Write in your journal" to "Написать в дневник",
        "Plan your week" to "Спланировать неделю",
        "Make a to-do list" to "Составить список дел",
        "Learn a new phrase in a foreign language" to "Выучить новую фразу на иностранном языке",
        "Read a poem" to "Прочитать стихотворение",

        // === Спорт и активность (recreational) ===
        "Go for a walk" to "Пойти на прогулку",
        "Go for a run" to "Пойти на пробежку",
        "Do yoga" to "Заняться йогой",
        "Do 10 push-ups" to "Сделать 10 отжиманий",
        "Do 20 squats" to "Сделать 20 приседаний",
        "Ride a bike" to "Покататься на велосипеде",
        "Play basketball" to "Поиграть в баскетбол",
        "Play football" to "Поиграть в футбол",
        "Play tennis" to "Поиграть в теннис",
        "Go swimming" to "Пойти плавать",
        "Go hiking" to "Сходить в поход",
        "Go to the gym" to "Сходить в спортзал",
        "Jump rope for 5 minutes" to "Попрыгать на скакалке 5 минут",
        "Dance to one song" to "Потанцевать под одну песню",

        // === Творчество (creative) ===
        "Draw something" to "Нарисовать что-нибудь",
        "Write a short story" to "Написать короткий рассказ",
        "Take a photo" to "Сделать фото",
        "Learn to play a song" to "Научиться играть песню",
        "Sing a song" to "Спеть песню",
        "Write a poem" to "Написать стихотворение",
        "Make a craft" to "Сделать поделку",
        "Color in a coloring book" to "Раскрасить раскраску",
        "Practice calligraphy" to "Попрактиковаться в каллиграфии",
        "Take an online art class" to "Пройти онлайн-урок рисования",

        // === Социальная активность (social) ===
        "Call a friend" to "Позвонить другу",
        "Text a friend" to "Написать другу",
        "Video chat with a friend" to "Поговорить с другом по видео",
        "Visit a friend" to "Навестить друга",
        "Play a board game" to "Поиграть в настольную игру",
        "Play video games with friends" to "Поиграть в видеоигру с друзьями",
        "Cook with a friend" to "Приготовить еду с другом",
        "Go to a party" to "Пойти на вечеринку",
        "Join a club" to "Вступить в клуб по интересам",
        "Volunteer" to "Стать волонтёром",

        // === Работа по дому (busywork) ===
        "Clean your room" to "Убрать в комнате",
        "Do the dishes" to "Помыть посуду",
        "Do laundry" to "Постирать бельё",
        "Organize your desk" to "Организовать порядок на столе",
        "Water the plants" to "Полить растения",
        "Take out the trash" to "Вынести мусор",
        "Vacuum the floor" to "Пропылесосить пол",
        "Make your bed" to "Заправить кровать",
        "Fold clothes" to "Сложить одежду",
        "Clean the windows" to "Помыть окна",

        // === Еда и кулинария (cooking) ===
        "Cook a meal" to "Приготовить еду",
        "Bake cookies" to "Испечь печенье",
        "Make a smoothie" to "Сделать смузи",
        "Try a new recipe" to "Попробовать новый рецепт",
        "Make breakfast" to "Приготовить завтрак",
        "Make lunch" to "Приготовить обед",
        "Make dinner" to "Приготовить ужин",
        "Make a sandwich" to "Сделать бутерброд",
        "Prepare a snack" to "Приготовить перекус",
        "Drink a glass of water" to "Выпить стакан воды",

        // === Разное (miscellaneous) ===
        "Look at the stars" to "Посмотреть на звёзды",
        "Plan a trip" to "Спланировать поездку",
        "Write a letter" to "Написать письмо",
        "Solve a puzzle" to "Решить головоломку",
        "Do a crossword" to "Разгадать кроссворд",
        "Play solitaire" to "Сыграть в пасьянс",
        "Check your email" to "Проверить почту",
        "Update your resume" to "Обновить резюме",
        "Set a goal for the week" to "Поставить цель на неделю",
        "Practice mindfulness" to "Попрактиковать осознанность"
    )

    fun translate(activity: String): String {
        return translations[activity] ?: activity  // Если перевода нет, возвращаем оригинал
    }
}