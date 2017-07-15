# Performance comparison of Android ORM Frameworks

At the moment there are a lot of ORM-libraries for the Android OS. We reviewed the most popular ones and compared them by performance and some other parameters.
As libraries for comparison, the most popular solutions at the time of writing were chosen: ORMLite, Sugar ORM, Freezer, DBFlow, requery, GreenDAO, ActiveAndroid, Room, Sprinkles. Also in the comparison participated "clean" SQLite - the built-in API for working with SQLite in Android, without the use of ORM, and the popular NoSql solution - Realm.

We considered 3 variants(simple, complex, balanced), and for each of them 4 operations(CRUD).
## Models
```java
public class Library{ 
    String address;
    String name;
}

public class Book{
    String author;
    String title;
    int pagesCount;
    int bookId; 
    Library library;
}
public class Person{
    String firstName;
    String secondName;
    Date birthdayDate;
    String gender;
    long phone;
    Library library;
}
```

## Test cases
* Simple - 1 Library object per 1000 Book objects 
* Complex - 1 Library object for 500 Book objects and 400 Person objects (5 Libraries, 2500 Books, 2000 Persons)
* Balanced - 1 Library object for 50 Book objects and 50 Person objects (50 Libraries, 2500 Books, 2500 Persons)

For each test cache/lazy initialization were turned off, and we perform an average of 10 measurements per operation.
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/screenshots/complex1.jpg?raw=true" align="center" height="640" width="360"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/screenshots/complex2.jpg?raw=true" align="center" height="640" width="360"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/screenshots/simple1.jpg?raw=true" align="center" height="640" width="360"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/screenshots/simple2.jpg?raw=true" align="center" height="640" width="360"></a>

## Results Table

|Library|write(s)|read(s)|update(s)|delete(s)|write(c)|read(c)|update(c)|delete(c)|write(b)|read(b)|update(b)|delete(b)|
|:-----:|:------:|:-----:|:-------:|:-------:|:------:|:-----:|:-------:|:-------:|:------:|:-----:|:-------:|:-------:|
|ORMLite|151|666|122|105|445|3836|857|811|1563|3426|724|728|
|SugarORM|245|842|252|152|1402|4129|1467|1003|2204|4397|1702|1197|
|Freezer|248|5430|240|4797|1337|78982|2221|22104|3255|134942|1887|29515|
|DBFlow|97|757|459|186|360|3534|3124|1044|1129|4653|5204|1268|
|Requery|87|1501|147|129|461|8057|861|802|1368|8002|886|763|
|Realm|151|29|1079|723|698|688|19666|9180|1522|210|21129|10006|
|GreenDAO|81|1238|117|97|357|5552|455|274|598|5905|504|315|
|ActiveAndroid|3123|930|2293|2423|14671|4165|15958|13023|17213|4653|19303|14642|
|Sprinkles|5766|1050|6364|605|25978|4334|65579|2428|27774|4526|37705|2519|
|Room|131|699|170|109|562|3201|717|403|1330|3532|790|507|
|SQLite|50|436|63|80|386|2155|192|284|1146|2313|213|318|

## Histograms

#### Overal
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/overal_simple.png?raw=true" align="center"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/overal_complex.png?raw=true" align="center"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/overal_balanced.png?raw=true" align="center"></a>
#### More detailed (Simple)
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/simple_read.png?raw=true" align="center"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/simple_write.png?raw=true" align="center"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/simple_update.png?raw=true" align="center"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/simple_delete.png?raw=true" align="center"></a>
#### More detailed (Complex)
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/complex_read.png?raw=true" align="center"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/complex_write.png?raw=true" align="center"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/complex_update.png?raw=true" align="center"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/complex_delete.png?raw=true" align="center"></a>
#### More detailed (Balanced)
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/balanced_read.png?raw=true" align="center"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/balanced_write.png?raw=true" align="center"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/balanced_update.png?raw=true" align="center"></a>
<a href="url"><img src="https://github.com/AlexeyZatsepin/Android-ORM-benchmark/blob/master/results/balanced_delete.png?raw=true" align="center"></a>

## Summary
##### ORMLite
It does not save much from the boilerplate code, however one of the fastest libraries out there is built-in caching and delayed initialization, good documentation.
Despite all the advantages, I would not use it, because The main reason for using ORM is getting rid of writing repetitive code, and, therefore, speeding up development, which in other libraries is realized, in my opinion, better.
##### SugarORM
Of the advantages can be noted ease of use, which is not enough in ORMLite, and that's it.
Has a separate drawback associated with the approach to implementation - it is not "friendly" with Instant Run, so when working with it you have to disable it.
##### Freezer
A relatively young library is not widely distributed. Comfortable, but do not make any complicated manipulations with it. In addition, the speed of work, to put it mildly, leaves much to be desired.
##### DBFlow
One of the best in the parameters outlined in this article. One of the fastest write / read operations is the average update / delete speed. According to the parameters presented in Table 2, it is the best on a par with Realm.
In my opinion, is one of the best alternatives.
##### Requery
Quite a controversial decision, in the fall because of problems with installation and documentation (if used without RXJava). If you understand, working with it becomes convenient.
Supports different databases, rich functionality.
##### Realm
A relatively fast and convenient library, all links are simply implemented, which is related to the object orientation of the database, even reading "clean" SQLite even when manually cached for it by reading. Excellent documentation. Is, perhaps, one of the best option for storing data on a mobile device at the moment, the minus can only be an increase in the size of the apk-file by 2.5 MB.
For all ORMs, except for Realm, built-in object caching was disabled, hence the readings.
##### GreenDao
It is flexible and convenient to use one-to-many communication, but very unpleasant code generation, as a result of which your classes will be filled with a bunch of methods and comments. In addition, you need to connect the Gradle plug-in, which greatly increases the build time. Also, developers presented their database - ObjectBox - object database, which they position as the fastest, comparable to GreenDao, ORM.
##### ActiveAndroid
Not very fast, but quite convenient and proven over time solution. Quite popular, but because you can find many articles, solutions to issues and other things, which reduces the appearance of "unresolvable" problems to a minimum.
##### Sprinkles
Each entry is accompanied by an unnecessary sample (SELECT * FROM% s WHERE% s LIMIT 1). From sql-requests too does not rescue. Performance indicators are the worst of the submitted.
Therefore, there is no reason to use it for your projects.
##### Room
An interesting solution presented on Google I / O 2017 as optimal for working with the database on Android OS. Despite the fact that it is necessary to use explicit sql-requests, the library turned out to be quite convenient and I liked it personally. On performance is in the lead, so I would advise you to choose this particular library. Since this solution, submitted by Google, it will quickly become popular, and, therefore, there will be no problems with finding solutions to problems that occur along with it.
##### SQLite
A lot of code, longer in time, but speed and flexibility are still unmatched.

## Сonclusion
Summing up, the most "fast" ORMs were Realm, GreenDAO, ORMLite and Room, but if performance is critical for the project, a "clean" SQLite with a custom cache is still the best choice.
On the other parameters, the best are Realm, DBFlow and GreenDAO.
Thus, for small projects and projects of medium complexity, I recommend using DBFlow or GreenDAO, if the size of apk is not important - Realm. Realm and Room are suitable for large projects. And only if their capabilities for some reason are not enough, or the ORM-approach is not for you, to return to using the built-in API for SQLite.

In addition to those discussed in the article, initially ORMs such as AndrORM, OHibernate, MemoryORM, ORMDroid, StromIO were also involved in the review, which were dropped for one reason or another.

All measurements were made on the Samsung S7 device.
