# Изменчивый Recycler
У нас есть экран с RecyclerView, на нем есть список в два столбца, изначально 15 элементов.

##### Требования:
1. У элемента отображается его номер и кнопка удалить, которая его удаляет, то есть такая плиточка с номером и кнопкой
2. В системе работает что-то асинхронное, которое раз в 5 секунд добавляет новый элемент на случайную позициию
3. Номер элемента все время наращивается
4. Добавления и удаления производятся с анимацией (можно стандартной)
5. Вся эта система поддерживает поворот экрана и продолжает работать после него

##### Требования посложнее:
1. Сделать пулл номеров удаленных элементов и новые добавлять из пула, и если там пусто просто наращивать номер
2. В вертикальном положении сделать две колонки, в горизонтальном четыре


##### Примечания:
1. Задание желательно выполнять на Kotlin.
2. Выполненное задание нужно загрузить на github.

##### [APK файл решения](https://github.com/AndVl1/avito-task/app/release/app-release.apk)