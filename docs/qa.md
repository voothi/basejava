## Ошибка NPE в реализованном методе get() для массива
### Ошибка
```log
Get r1: uuid1
Size: 3
Exception in thread "main" java.lang.NullPointerException
	at ru.voothi.webapp.storage.ArrayStorage.get(ru.voothi.webapp.storage.ArrayStorage.java:32)
	at ru.voothi.webapp.MainTestArrayStorage.main(ru.voothi.webapp.MainTestArrayStorage.java:21)

Process finished with exit code 1
```


### Код

Класс
https://github.com/voothi/basejava/blob/e8f0bf45ffc8289970c19d48f2a4dcd17030af9e/src/ru.voothi.webapp.storage.ArrayStorage.java

имеет метод,

```java
ru.voothi.webapp.model.Resume get(String uuid) {
    ru.voothi.webapp.model.Resume resume = null;
    for (ru.voothi.webapp.model.Resume element : storage) {
        if (element.getUuid().equals(uuid)) {
            resume = element;
            break;
        }
    }
    return resume;
}
```
который вызывается из метода main() в Классе (для тестирования)
https://github.com/voothi/basejava/blob/d8d4b370e9b9b7075ee3193eb1046fc37f891e5f/src/ru.voothi.webapp.MainTestArrayStorage.java
```java
System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
```

## Решения
- Хранить количество реально добавленных объектов 
    и при переборке массива через for 
        до количества вставленных объектов
- Flag "заполненности" чтобы сначала заполнить нулевые элементы
***
- Синтаксически if (uuid.equals(Optional.ofNullable(element.getUuid).orElse(null)) { ... }
- Переход на ArrayList
