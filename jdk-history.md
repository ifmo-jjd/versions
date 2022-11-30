## Основные изменения в Java 7

1. Использование try-with-resources
2. Diamond оператор для Generic


    // до 7 версии    
    List<String> list = new ArrayList<String>();        

    // начиная с 7 версии
    List<String> list = new ArrayList<>();

## Основные изменения в Java 8

1. Лямбда-выражения и Stream API
2. Optional контейнер
3. Ссылка на метод
4. Методы по умолчанию в интерфейсах
5. Аннотации Generic типов 
6. DateTime API

## Основные изменения в Java 9

1. Разрешено использовать final переменные в качестве ресурсов в операторе try-with-resources

      
         try (final FileInputStream fileInputStream = new FileInputStream("file.txt")){
            // code
         } catch (IOException e) {
            e.printStackTrace();
         }

2. Private методы (только методы с реализацией) в интерфейсах


         public interface SomeInterface {

             private static void staticVoid(){
                 // вспомогательный static метод
             }

             private void someVoid() {
                 // вспомогательный метод, его можно
                 // вызвать в default методе
             }
         }


3. Система модулей Java

4. Diamond оператор для анонимных классов


         public interface SomeInterface<T> {
             void someVoid1(T t);
             T someVoid2();
         }


         SomeInterface<String> someInterface = new SomeInterface<>() {
            @Override
            public void someVoid1(String s) {
                // реализация метода
            }

            @Override
            public String someVoid2() {
                // реализация метода
                return null;
            }
        }; 

   

## Основные изменения в Java 10

Локальная переменная (var) позволяет не указывать тип данных
   
      int a = 90;  
      long b = 90L;

      var c = 78;
      var x = 78L;  

      var unit1 = new Unit(223, 145);
      var unit2 = new Unit(332, 98);
   
      var army = List.of(unit1, unit2);


## Основные изменения в Java 11

Локальная переменная (var) позволяет не указывать тип данных внутри лямбда-выражений

        boolean test(Integer value);
        
        (x)-> x > 0

        // если указываем тип
        (Integer x)-> x > 0 // до 11 версии
        (var x)-> x > 0 // начиная с 11 версии
        

## Основные изменения в Java 14

1. Switch выражения


       int month = 2;

       String season = switch (month) {
            case 12, 1, 2 -> "Зима";
            case 3, 4, 5 -> "Весна";
            case 6, 7, 8 -> "Лето";
            case 9, 10, 11 -> "Осень";
            default -> "Ошибка ввода";
        };

2. Switch выражения и ключевое слово yield


        int month = 2;

        String season = switch (month) {
            case 12, 1, 2 -> {
                System.out.println(month);
                yield "Зима";
            }
            case 3, 4, 5 -> {
                System.out.println(month);
                yield "Весна";
            }
            case 6, 7, 8 -> {
                System.out.println(month);
                yield "Лето";
            }
            case 9, 10, 11 -> {
               System.out.println(month);
               yield "Осень";
            }
            default -> "Ошибка ввода";
        };


## Основные изменения в Java 15

1. Текстовые блоки `""" """`
2. Sealed типы - в режиме _preview_ на данный момент времени (java 17/18)

## Основные изменения в Java 16
1. instanceof с приведением 



          до 15 версии:
          if (object instanceof Bus) {
             Bus bus = (Bus) object;
             System.out.println(bus.getNum());
          } 
    
    
          начиная с 15 версии:
          if (object instanceof Bus bus) {
             System.out.println(bus.getNum());
          } 

2. Record (записи) - обеспечивают компактный синтаксис для объявления классов


        record Point(int x, int y) { }

Для записей автоматически генерируются:
 
2.1) Для каждого компонента из заголовка генерируется финальное приватное поле и метод чтения: x() для x, y() для y.

2.2) Публичный конструктор с сигнатурой, совпадающей с заголовком класса.        

2.3) Методы equals() и hashCode(), toString()

Конструктор можно определить явно:


        record Point(int x, int y) {
        
            Point(int x, int y) { // обычным способом
                if (x < 0 || x > 100 || y < 0 || y > 100) {
                    throw new IllegalArgumentException("Point coordinates must be between 0 and 100");
                }
                this.x = x;
                this.y = y;
            }

            Point { // или компактным способом
                if (x < 0 || x > 100 || y < 0 || y > 100) {
                  throw new IllegalArgumentException("Point coordinates must be between 0 and 100");
                }
            }
        }


Ограничения записей:

2.1) Записи не могут наследоваться от других классов. Родительским классом для записи всегда является java.lang.Record

2.1) Классы записей являются финальными и не могут быть абстрактными

2.3) Поля записей являются финальными


